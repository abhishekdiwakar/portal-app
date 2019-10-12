package com.linneaus.portal.web;

import com.linneaus.portal.model.Channel;
import com.linneaus.portal.model.Item;
import com.linneaus.portal.model.youtube.Videos;
import com.linneaus.portal.service.*;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.net.URLCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Log4j2
@Controller
public class MainController {
    @Value("${youtube.parkinson.video.url}")
    private String YOUTUBE_PARKINSON_VIDEO_URL;

    @Value("${feeds.api.url}")
    private static String FEEDS_API_URL;

    @Autowired
    private SecurityService securityService;

    @Autowired
    FacebookService facebookService;

    @Autowired
    SpotifyService spotifyService;

    @Autowired
    GithubService githubService;

    @Autowired
    AuthenticationSuccessHandler successHandler;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AuthenticationFailureHandler failureHandler;

    @Autowired
    private UserService userService;

    @Autowired
    RestTemplate restTemplate;

    @Qualifier("userDetailsServiceImpl")
    @Autowired
    private UserDetailsService userDetailsService;

    @GetMapping("/login")
    public String login(Model model, String logout, HttpServletRequest req) {
        model.addAttribute("facebookUrl", facebookService.getFBAuthUrl());
        model.addAttribute("spotifyUrl", spotifyService.getSpotifyAuthUrl());
        model.addAttribute("githubUrl", githubService.getGithubUrl());
        if (logout != null) {
            model.addAttribute("message", "You have been logged out successfully.");
            securityService.logout();
            return "login";
        }
        return "login";
    }

    @GetMapping("/facebooklogin")
    @ResponseStatus(value = HttpStatus.OK)
    public void facebookLogin(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        String code = req.getParameter("code");
        if (StringUtils.isEmpty(code)) {
            throw new RuntimeException("ERROR: Didn't get code parameter in callback.");
        } else {
            String accessToken = facebookService.getAccessToken(code);
            if (!StringUtils.isEmpty(accessToken)) {
                autoLogin(req, response, "patient1");
            }
        }
    }

    @GetMapping("/githublogin")
    @ResponseStatus(value = HttpStatus.OK)
    public void githubLogin(HttpServletRequest req, HttpServletResponse response) throws IOException, ServletException {
        if (StringUtils.isEmpty(req.getParameter("code"))) {
            autoLogin(req, response, "doc");
        }
    }

    @GetMapping("/spotifylogin")
    @ResponseStatus(value = HttpStatus.OK)
    public void spotifyLogin(@RequestParam("code") String code, HttpServletRequest req, HttpServletResponse response)
            throws IOException, ServletException {
        if (!StringUtils.isEmpty(code)) {
            autoLogin(req, response, "researcher");
        }
    }

    @GetMapping("/doctorHomePage")
    public String doctorHome(ModelMap model, HttpServletRequest req) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", authUser.getUsername());
        model.addAttribute("patientsData", userService.getPatientData(authUser.getUsername()));
        return "docHomePage";
    }

    @GetMapping("/researcherHomePage")
    public String researcherHome(Model model) {
        Channel channels = new Channel();
        try {
            try (XmlReader reader = new XmlReader(new URL(FEEDS_API_URL))) {
                SyndFeed feed = new SyndFeedInput().build(reader);
                List<Item> items = new ArrayList<>();
                for (SyndEntry entry : feed.getEntries()) {
                    items.add(new Item(entry.getTitle(), entry.getDescription().getValue(), entry.getLink(), entry.getComments(), entry.getUri(), entry.getPublishedDate().toString()));
                }
                channels = new Channel(feed.getTitle(), feed.getDescription(), feed.getLink(), feed.getLanguage(), feed.getAuthor(), items);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", authUser.getUsername());
        model.addAttribute("patientsData", userService.getPatientData(authUser.getUsername()));
        model.addAttribute("channels", channels);
        return "researcherPage";
    }

    @GetMapping("/patientHomePage")
    public String patientHome(ModelMap model) {
        model.addAttribute("videos", fetchParkinsonDiseaseVideos());
        return "patientPage";
    }

    private List<Item> fetchParkinsonDiseaseVideos() {
        try {
            HttpEntity<String> entity = new HttpEntity<String>(getHttpHeaders());
            ResponseEntity<Videos> response = restTemplate
                    .exchange(new URLCodec().decode(YOUTUBE_PARKINSON_VIDEO_URL), HttpMethod.GET, entity,
                            new ParameterizedTypeReference<Videos>() {
                            });
            Videos videos = response.getBody();
            videos.getItems();
        } catch (DecoderException exception) {
            log.error("A get request to fetch parkinsons disease videos from you tube failed with exception {}", exception);
        }
        return Collections.emptyList();
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    private void autoLogin(HttpServletRequest request, HttpServletResponse response, String username) throws IOException, ServletException {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        token.setDetails(new WebAuthenticationDetails(request));//if request is needed during authentication
        Authentication auth;
        try {
            auth = authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            try {
                failureHandler.onAuthenticationFailure(request, response, e);
            } catch (IOException | ServletException se) {
              System.out.println(se);
            }
            throw e;
        }
        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);
        successHandler.onAuthenticationSuccess(request, response, auth);
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);
    }
}

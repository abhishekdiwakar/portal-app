package com.linneaus.portal.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;


@Configuration
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        HttpSession session = req.getSession();

        /*Set some session variables*/
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setAttribute("uname", authUser.getUsername());
        session.setAttribute("authorities", authentication.getAuthorities());

        /*Set target URL to redirect*/
        String targetUrl = determineTargetUrl(authentication);
        redirectStrategy.sendRedirect(req, httpServletResponse, targetUrl);
    }

    protected String determineTargetUrl(Authentication authentication) {
        Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (authorities.contains("physician")) {
            return "/doctorHomePage";
        } else if (authorities.contains("researcher")) {
            return "/researcherHomePage";
        } else if (authorities.contains("patientData")) {
            return "/patientHomePage";
        } else
            return "/login";
    }

    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }

}



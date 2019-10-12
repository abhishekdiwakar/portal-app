package com.linneaus.portal.service;

import lombok.extern.log4j.Log4j2;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

@Log4j2
@Service
public class FacebookService {

    @Value("${facebook.app.id}")
    private String FACEBOOK_APPLICATION_ID;

    @Value("${facebook.app.secret}")
    private String FACEBOOK_APPLICATION_SECRET;

    @Value("${facebook.redirect.url}")
    private String FACEBOOK_REDIRECT_URL;

    public String getFBAuthUrl() {
        try {
            return "http://www.facebook.com/dialog/oauth?" + "client_id=" + FACEBOOK_APPLICATION_ID + "&redirect_uri="
                    + URLEncoder.encode(FACEBOOK_REDIRECT_URL, "UTF-8")
                    + "&scope=email";
        } catch (UnsupportedEncodingException exception) {
            log.error("An exception occurred when log in to facebook. Exception = {}", exception.getMessage());
        }
        return "";
    }

    public String getAccessToken(String code) {
        String accessToken = "";
        try {
            accessToken = getFbAccessToken(code);
            log.info("Accesss Token {}", accessToken);
            checkIfTokenValid(accessToken);
            return accessToken;
        } catch (MalformedURLException exception) {
            log.error("An exception occurred while getting the access token from facebook {}", exception.getMessage());
            throw new RuntimeException("Invalid code received " + exception);
        } catch (IOException exception) {
            log.error("An exception occurred while connecting to facebook {}", exception.getMessage());
            throw new RuntimeException("Unable to connect with Facebook " + exception);
        }
    }

    private void checkIfTokenValid(String accessToken) {
        if (accessToken.startsWith("{")) {
            throw new RuntimeException("ERROR: Access Token Invalid: "
                    + accessToken);
        }
    }

    private String getFbAccessToken(String code) throws IOException {
        StringBuffer fbResponse = new StringBuffer();
        URL fbGraphURL = new URL(getFBGraphUrl(code));
        URLConnection fbConnection = fbGraphURL.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(fbConnection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            fbResponse.append(inputLine + "\n");
        }
        in.close();
        JSONObject json = new JSONObject(fbResponse.toString());
        if (json.has("access_token")) {
            return json.getString("access_token").toString();
        } else
            return fbResponse.toString();
    }

    private String getFBGraphUrl(String code) {
        try {
            return "https://graph.facebook.com/oauth/access_token?" + "client_id=" + FACEBOOK_APPLICATION_ID
                    + "&redirect_uri=" + URLEncoder.encode(FACEBOOK_REDIRECT_URL, "UTF-8")
                    + "&client_secret=" + FACEBOOK_APPLICATION_SECRET + "&code=" + code;
        } catch (UnsupportedEncodingException exception) {
            log.error("An exception occurred while getting the fb graph url. Exception = {}", exception.getMessage());
        }
        return "";
    }

}

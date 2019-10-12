package com.linneaus.portal.serviceintegrationlayer;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class SpotifyService {

  @Value("${spotify.application.id}")
  private String SPOTIFY_APPLICATION_ID;

  @Value("${spotify.application.secret}")
  private String SPOTIFY_APPLICATION_SECRET;

  @Value("${spotify.redirect.url}")
  private String SPOTIFY_REDIRECT_URI;


  public String getSpotifyAuthUrl() {
    try {
      return "https://accounts.spotify.com/authorize?client_id=" + SPOTIFY_APPLICATION_ID
          + "&response_type=code&redirect_uri=" + URLEncoder.encode(SPOTIFY_REDIRECT_URI, "UTF-8");
    } catch (UnsupportedEncodingException exception) {
      log.error("An exception occurred while log in through spotify {}", exception);
    }
    return "";
  }

}

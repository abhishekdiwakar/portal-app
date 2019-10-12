package com.linneaus.portal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GithubService {

  @Value("${github.application.id}")
  public static String GITHUB_APPLICATION_ID;
  private static final String GITHUB_URL =
      "https://github.com/login/oauth/authorize?scope=user:email&client_id=" + GITHUB_APPLICATION_ID;

  public String getGithubUrl() {
    return GITHUB_URL;
  }
}

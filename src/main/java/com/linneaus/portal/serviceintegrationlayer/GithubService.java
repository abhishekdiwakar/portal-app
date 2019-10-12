package com.linneaus.portal.serviceintegrationlayer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GithubService {

  @Value("${github.application.id}")
  private String GITHUB_APPLICATION_ID;

  public String getGithubUrl() {
    return "https://github.com/login/oauth/authorize?scope=user:email&client_id=" + GITHUB_APPLICATION_ID;
  }
}

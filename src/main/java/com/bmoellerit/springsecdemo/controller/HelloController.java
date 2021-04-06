package com.bmoellerit.springsecdemo.controller;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Bernd on 28.02.2021.
 * <p>
 * Package com.bmoellerit.springsecdemo.controller
 */
@RestController
public class HelloController {

  private final OAuth2AuthorizedClientService oAuth2AuthorizedClientService;


  private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

  @Autowired
  public HelloController(
      OAuth2AuthorizedClientService oAuth2AuthorizedClientService) {
    this.oAuth2AuthorizedClientService = oAuth2AuthorizedClientService;
  }

  @GetMapping("/hello")
  public ResponseEntity<String> getHello(@RequestHeader HttpHeaders headers,
                                                        HttpEntity<String> httpEntity,
                                                        OAuth2AuthenticationToken oAuth2AuthenticationToken){
    String user = getUser(oAuth2AuthenticationToken);
    return new ResponseEntity<String>("Hello " + user, HttpStatus.OK);
  }

  private String getUser(OAuth2AuthenticationToken oAuth2AuthenticationToken){
    Map userAttributes = null;

    OAuth2AuthorizedClient client = oAuth2AuthorizedClientService
        .loadAuthorizedClient(
            oAuth2AuthenticationToken.getAuthorizedClientRegistrationId(),
            oAuth2AuthenticationToken.getName());
    String userInfoEndpointUri = client.getClientRegistration()
        .getProviderDetails().getUserInfoEndpoint().getUri();

    if (!StringUtils.isEmpty(userInfoEndpointUri)) {
      RestTemplate restTemplate = new RestTemplate();
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + client.getAccessToken()
          .getTokenValue());
      HttpEntity entity = new HttpEntity("", headers);
      ResponseEntity<Map> response = restTemplate
          .exchange(userInfoEndpointUri, HttpMethod.GET, entity, Map.class);
      userAttributes = response.getBody();

    }
    return userAttributes.get("name").toString();
  }
}

package org.example.springsecurityoauthclientsession.Service;

import org.example.springsecurityoauthclientsession.DTO.CustomOAuth2User;
import org.example.springsecurityoauthclientsession.DTO.GoogleResponse;
import org.example.springsecurityoauthclientsession.DTO.NaverResponse;
import org.example.springsecurityoauthclientsession.DTO.OAuth2Response;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        System.out.println(userRequest);
        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);
        System.out.println(oAuth2User.getAttributes());

        String registraionId = userRequest.getClientRegistration().getRegistrationId();
        OAuth2Response oAuth2Response = null;
        if(registraionId.equals("naver")) {
            oAuth2Response = new NaverResponse(oAuth2User.getAttributes());
        }
        else if(registraionId.equals("google")) {
            oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }
        else{
            return null;
        }

        String role = "ROLE_USER";

        return new CustomOAuth2User(oAuth2Response, role);
    }
}

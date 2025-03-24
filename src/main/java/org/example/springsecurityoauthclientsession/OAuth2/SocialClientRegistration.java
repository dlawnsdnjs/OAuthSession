package org.example.springsecurityoauthclientsession.OAuth2;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.oidc.IdTokenClaimNames;
import org.springframework.stereotype.Component;

@Component
public class SocialClientRegistration {
    private final Dotenv dotenv = Dotenv.load();

    public ClientRegistration naverClientRegistration(){
        return ClientRegistration.withRegistrationId("naver")
                .clientId(dotenv.get("OAUTH_NAVER_CLIENT_ID"))
                .clientSecret(dotenv.get("OAUTH_NAVER_CLIENT_SECRET"))
                .redirectUri("http://localhost:8080/login/oauth2/code/naver")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope("name", "email")
                .authorizationUri("https://nid.naver.com/oauth2.0/authorize")
                .tokenUri("https://nid.naver.com/oauth2.0/token")
                .userInfoUri("https://openapi.naver.com/v1/nid/me")
                .userNameAttributeName("response")
                .build();
    }

    public ClientRegistration googleClientRegistration() {
    // properties에서 사용할 땐 생략해도 되지만 custom으로 사용할때는 모두 호출해 줘야 한다.
        return ClientRegistration.withRegistrationId("google")
                .clientId(dotenv.get("OAUTH_GOOGLE_CLIENT_ID"))
                .clientSecret("OAUTH_GOOGLE_CLIENT_SECRET")
                .redirectUri("http://localhost:8080/login/oauth2/code/google")
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .scope("profile", "email")
                .authorizationUri("https://accounts.google.com/o/oauth2/v2/auth")
                .tokenUri("https://www.googleapis.com/oauth2/v4/token")
                .jwkSetUri("https://www.googleapis.com/oauth2/v3/certs")
                .issuerUri("https://accounts.google.com")
                .userInfoUri("https://www.googleapis.com/oauth2/v3/userinfo")
                .userNameAttributeName(IdTokenClaimNames.SUB)
                .build();
    }
}
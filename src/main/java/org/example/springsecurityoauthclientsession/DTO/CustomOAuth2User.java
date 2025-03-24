package org.example.springsecurityoauthclientsession.DTO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.*;

public class CustomOAuth2User implements OAuth2User {
    private final OAuth2Response oauth2Response;
    private final String role;

    public CustomOAuth2User(OAuth2Response oauth2Response, String role) {
        this.oauth2Response = oauth2Response;
        this.role = role;
    }

    @Override
    public Map<String, Object> getAttributes() {
        // naver와 google의 key값이 달라서 일단 비워둔다고 하는데 구현한다면 어떤 식으로 구현하는가?
        // oauth2Response, role을 받는게 아니라 통합된 형태의 변수를 주입받는 방식으로 해결가능
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("username", oauth2Response.getName());
        attributes.put("role", role);
        return null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<GrantedAuthority>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return role;
            }
        });

        return collection;
    }

    @Override
    public String getName() {
        return oauth2Response.getName();
    }

    public String getUsername(){
        return oauth2Response.getProvider()+ " " + oauth2Response.getProviderId();
    }
}

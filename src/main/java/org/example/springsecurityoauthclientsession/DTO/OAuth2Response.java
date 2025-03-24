package org.example.springsecurityoauthclientsession.DTO;

public interface OAuth2Response {
    String getProvider();
    String getProviderId();
    String getEmail();
    String getName();
}

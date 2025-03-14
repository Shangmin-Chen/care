// CustomOAuth2User.java
package com.example.care.config;

import com.example.care.entity.User;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Collection;
import java.util.Map;

public class CustomOAuth2User extends DefaultOAuth2User {

    private final User applicationUser;

    public CustomOAuth2User(Collection<? extends org.springframework.security.core.GrantedAuthority> authorities,
                            Map<String, Object> attributes,
                            String nameAttributeKey,
                            User applicationUser) {
        super(authorities, attributes, nameAttributeKey);
        this.applicationUser = applicationUser;
    }

    @Override
    public String getName() {
        return applicationUser.getEmail();
    }

    public User getApplicationUser() {
        return applicationUser;
    }
}
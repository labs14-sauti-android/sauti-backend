package com.labs.sauti.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_ID).stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/oauth/revoke-token",
                        "/users/user/logout"
                ).authenticated()
                .antMatchers("/actuator/**").hasAnyRole("ADMIN")
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());

        // http.requiresChannel().anyRequest().requiresSecure();
        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
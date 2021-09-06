package br.com.zupacademy.msPropostas.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(authorizeRequests -> authorizeRequests
                .antMatchers(HttpMethod.GET, "/propostas/**").hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.POST, "/propostas").hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.POST, "/cartoes/**").hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.POST, "/biometrias/**").hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.POST, "/bloqueios/**").hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.GET, "/actuator/**").hasAuthority("SCOPE_escopo-proposta")
                .antMatchers(HttpMethod.POST, "/api/**").hasAuthority("SCOPE_escopo-proposta")

                .anyRequest().authenticated()).oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}

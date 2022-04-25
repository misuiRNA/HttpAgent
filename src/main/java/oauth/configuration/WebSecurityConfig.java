package oauth.configuration;

import oauth.authentication.MyAuthenticateProvider;
import oauth.authentication.URLAccessDecisionManager;
import oauth.authentication.URLFilterInvocationSecurityMetadataSource;
import oauth.filter.JWTAuthenticationFilter;
import oauth.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public WebSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/login");
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(new MyAuthenticateProvider(userService));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .authorizeRequests().anyRequest()
                .authenticated()
                .withObjectPostProcessor(objectPostProcessor())
        .and()
            .addFilterBefore(new JWTAuthenticationFilter(userService), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    private ObjectPostProcessor<FilterSecurityInterceptor> objectPostProcessor() {
        return new ObjectPostProcessor<FilterSecurityInterceptor> () {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setAccessDecisionManager(new URLAccessDecisionManager());
                object.setSecurityMetadataSource(new URLFilterInvocationSecurityMetadataSource());
                return object;
            }
        };
    }

}

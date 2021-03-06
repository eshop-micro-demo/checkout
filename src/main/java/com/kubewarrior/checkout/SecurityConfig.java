// package com.kubewarrior.checkout;

// import java.util.Arrays;
// import java.util.Collections;

// import org.springframework.boot.web.servlet.FilterRegistrationBean;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.core.Ordered;
// import org.springframework.http.HttpMethod;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
// import org.springframework.web.cors.CorsConfiguration;
// import org.springframework.web.cors.CorsConfigurationSource;
// import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
// import org.springframework.web.filter.CorsFilter;

// @Configuration
// public class SecurityConfig extends WebSecurityConfigurerAdapter {

//     @Override
//     protected void configure(HttpSecurity http) throws Exception {
//         http.cors().configurationSource(corsConfigurationSource())
//             .and()
//               .authorizeRequests()
//                 // .antMatchers(HttpMethod.GET, "/user/info", "/foos/**")
//                 //   .hasAuthority("SCOPE_dummyscope")
//                 // .antMatchers(HttpMethod.POST, "/foos")
//                 //   .hasAuthority("SCOPE_dummyscope")
//                 .anyRequest()
//                   // .authenticated()
//                   .permitAll()
//             // .and()
//             //   .oauth2ResourceServer()
//             //     .jwt()
//             ;
//     }

//     @Bean
//     public CorsConfigurationSource corsConfigurationSource() {
//         CorsConfiguration configuration = new CorsConfiguration();
//         configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000")); // <-- you may change "*"
//         configuration.setAllowedMethods(Arrays.asList("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
//         configuration.setAllowCredentials(true);
//         configuration.setAllowedHeaders(Arrays.asList(
//                 "Accept", "Origin", "Content-Type", "Depth", "User-Agent", "If-Modified-Since,",
//                 "Cache-Control", "Authorization", "X-Req", "X-File-Size", "X-Requested-With", "X-File-Name"));
//         UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//         source.registerCorsConfiguration("/**", configuration);
//         return source;
//     }

//     @Bean
//     public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
//         FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(corsConfigurationSource()));
//         bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//         return bean;
//     }
// }
package com.profilizer.configuration;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsFilterConfiguration {
	
	private static final String ALLOWED_ORIGINS = CorsConfiguration.ALL;

	private static final String HEADER_X_REQUESTED_WITH = "X-Requested-With";
	
	private static final String[] ALLOWED_METHODS = { HttpMethod.GET.toString(),
													  HttpMethod.OPTIONS.toString(),
													  HttpMethod.HEAD.toString(),
													  HttpMethod.POST.toString(),
													  HttpMethod.PUT.toString(),
													  HttpMethod.PATCH.toString(),
													  HttpMethod.DELETE.toString() };
	
	private static final String[] ALLOWED_HEADERS = { HttpHeaders.ORIGIN,
													  HttpHeaders.ACCEPT,
													  HttpHeaders.AUTHORIZATION,
													  HEADER_X_REQUESTED_WITH,
													  HttpHeaders.CONTENT_TYPE,
													  HttpHeaders.ACCESS_CONTROL_REQUEST_METHOD,
													  HttpHeaders.ACCESS_CONTROL_REQUEST_HEADERS };
	
	@Bean
	public FilterRegistrationBean corsFilter() {	
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin(ALLOWED_ORIGINS);
		configuration.setAllowCredentials(true);
		configuration.setAllowedMethods(Arrays.asList(ALLOWED_METHODS));
		configuration.setAllowedHeaders(Arrays.asList(ALLOWED_HEADERS));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new CorsFilter(source));
		filterRegistrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return filterRegistrationBean;
	}
}

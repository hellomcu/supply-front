package com.supply.front.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


import com.supply.front.interceptor.AuthInterceptorAdapter;


@Configuration
public class WebMvcConfigurer extends WebMvcConfigurerAdapter
{
	// 添加拦截器
	@Override
	public void addInterceptors(InterceptorRegistry registry)
	{
		registry.addInterceptor(new AuthInterceptorAdapter()).addPathPatterns("/front/**");
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry)
	{
		registry.addResourceHandler("/**").addResourceLocations("classpath:/templates/").addResourceLocations("classpath:/static/");
		
	}
}

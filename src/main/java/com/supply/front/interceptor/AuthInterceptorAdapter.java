package com.supply.front.interceptor;

import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.supply.exception.SupplyException;
import com.supply.front.auth.util.JwtUtil;
import com.supply.front.config.ServerConfig;

import io.jsonwebtoken.Claims;

public class AuthInterceptorAdapter extends HandlerInterceptorAdapter
{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception
	{
		if (isAuth(request))
		{
			return true;
		}
		throw new SupplyException("请先登录");
	}

	
	private boolean isAuth(HttpServletRequest request)
	{
		Cookie[] cookies = request.getCookies();
		if (cookies == null || cookies.length == 0)
		{
			return false;
		}
		
		Stream<Cookie> streamCookies = Stream.of(cookies);
		Optional<Cookie> streamCookie = streamCookies.filter(new Predicate<Cookie>()
		{

			@Override
			public boolean test(Cookie t)
			{
				return ServerConfig.TOKEN_HEADER.equals(t.getName());
			}
		}).findFirst();
		if (streamCookie.isPresent())
		{
			Cookie cookie = streamCookie.get();
			String jwtToken = cookie.getValue();
			Claims claims = JwtUtil.parseJWT(jwtToken);
			if (claims != null)
			{
				return true;
			}
		}
		
		return false;
	}
}

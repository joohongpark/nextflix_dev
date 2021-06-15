package com.movie.common.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class CookieUtils {

	private Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
	
	/**
	 * 생성자에서 사용자의 request 를 받아서 
	 * 맵에 저장 
	 * @param req
	 */
	public CookieUtils(HttpServletRequest  req) {		
		Cookie[] cookies = req.getCookies();
		if(cookies != null) {
			for(Cookie c : cookies) {
				cookieMap.put(c.getName(), c);
			}
		}
	}
	
	// Cookie getCookie(String) 
	public Cookie getCookie(String name) {
		return cookieMap.get(name);
	}
	
	// String getValue(String) throws IOE
	public String getValue(String name ) throws IOException   {
		Cookie cookie = cookieMap.get(name);
		if(cookie == null) {
			return null;
		}
		return  URLDecoder.decode(cookie.getValue(), "utf-8");
	}
	
	public boolean exists(String name) {
		return cookieMap.containsKey(name);
	}
	
	public static Cookie createCookie(String name, String value) throws IOException {
		return createCookie(name, value, "/", "",  0);
	}
	
	public static Cookie createCookie(String name, String value, String path, int maxAge) throws IOException {
		return createCookie(name, value, path, "", maxAge);
	}
	
	public static Cookie createCookie(String name, String value
			                        , String path, String domain, int maxAge) throws IOException {
		Cookie c = new Cookie(name, URLEncoder.encode(value,"utf-8"));
		c.setPath(path);
		c.setMaxAge(maxAge);
		c.setDomain(domain);
		return c; 
	}
	
} // class








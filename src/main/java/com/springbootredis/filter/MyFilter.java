package com.springbootredis.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @Author: laizc
 * @Date: Created in 11:08 2020-05-18
 */
@Order(1)
@WebFilter(filterName = "myFilter",urlPatterns = {"/example/index/*"})
public class MyFilter implements Filter{
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		System.out.println("过滤器之前要干啥呢");
		//转发到资源目的地，
		//filterChain.doFilter(servletRequest,servletResponse);
		System.out.println("处理一下服务端返回的response");
	}

	@Override
	public void destroy() {

	}
}

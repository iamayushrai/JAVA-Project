package com.abc.demo.aspect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.abc.demo.dto.UserDTO;

@Component
public class TestInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("user")==null) {
			//response.sendRedirect("http://localhost:8888"+request.getContextPath()+"/login");
			return true;   // false: handled by spring and no processing needed
		}
				
		return true;   // true: send to handler method
	}
	
}

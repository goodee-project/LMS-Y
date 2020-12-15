package gd.fintech.lms;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 운영자 로그은 처리를 위한 필터
@WebFilter(urlPatterns = "/auth/manager/*")
public class LoginManagerFileter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();	// 세션 값 가져오기
		// 세션에 저장된 운영자의 권한(3)이 없으면 초기 로그인 페이지로 리다이렉트
		if(session.getAttribute("accountLevel").equals(3)) {
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/login");
			return;
		}
		chain.doFilter(request, response);
	}	
}

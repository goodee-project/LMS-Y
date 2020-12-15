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

// admin 계층의 로그인 처리를 위한 필터
@WebFilter(urlPatterns = "/auth/admin/*")
public class LoginAdminFileter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("관리자 로그인 필터 실행");
		HttpSession session = ((HttpServletRequest)request).getSession();	// HttpSession 형변환하여 세션 받아오기
		// 세션에 저장된 권리자 권한값(4)이 없으면 초기 로그인 페이지로 리다이렉트
		if(!session.getAttribute("accountLevel").equals(4)) {
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/login");
			return;
		}
		chain.doFilter(request, response);
	}
}

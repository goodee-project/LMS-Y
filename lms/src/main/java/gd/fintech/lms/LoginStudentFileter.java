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

// 학생 로그인 처리를 위한 필터 클래스

@WebFilter(urlPatterns = "/auth/student/*")
public class LoginStudentFileter implements Filter{
	// 인덱스 페이지로 이동하는 요청이 일어날 때 계정권한이 세션에 없으면 로그인 페이지로 이동하는 필터 메소드
	// 매개변수: 서블릿 request, 서블릿 response, 필터 체인(순서)
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// HttpSesion 형변환 후에 세션값 가져오기
		HttpSession session = ((HttpServletRequest)request).getSession();	
		// 학생의 권한값(1)이 세션에 없으면 로그인 페이지로 리다이렉트
		if(session.getAttribute("accountLevel") == null || !session.getAttribute("accountLevel").equals(1)) {
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/login");
			return;
		}
		chain.doFilter(request, response);
	}
}

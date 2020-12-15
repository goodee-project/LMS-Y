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

// 학생 로그인 처리를 위한 필터
@WebFilter
public class LoginStudentFileter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest)request).getSession();	// HttpSesion 형변환 후에 세션값 가져오기
		// 학생의 권한값(1)이 세션에 없으면 로그인 페이지로 리다이렉트
		if(!session.getAttribute("accountLevel").equals(1)) {
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath() + "/login");
			return;
		}
		chain.doFilter(request, response);
	}
}

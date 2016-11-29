package useful;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class InterceptorAuthorization implements Filter {

	private FilterConfig filterConfig = null;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String newRequestPage = ((HttpServletRequest) request).getServletPath();

		if (newRequestPage.endsWith("JSPLogin") ||
			newRequestPage.endsWith("jAddAccount") ||
			newRequestPage.endsWith("sManageAccount") ||			
			newRequestPage.contains("Servlet")	||
			newRequestPage.contains("css")	||
			newRequestPage.contains("img")	||
			((HttpServletRequest) request).getSession().getAttribute("loggedUser") != null) {			        			    
			chain.doFilter(request, response);
		} else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.sendRedirect("JSPLogin");
		}

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void destroy() {		 
	}

}

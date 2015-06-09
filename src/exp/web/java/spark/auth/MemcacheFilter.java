package exp.web.java.spark.auth;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.GsonBuilder;

import net.spy.memcached.MemcachedClient;
import exp.web.java.spark.auth.clients.MemCache;
import exp.web.java.spark.entity.User;
import exp.web.java.spark.exception.UnauthorizedAccessException;

/**
 * SparkDemo - exp.web.java.spark.auth
 *
 * @author Viswanath Lekshmanan
 *
 * Apr 23, 2015
 */

/**
 * <p>
 * Used to validate the auth token from API.<br>
 * Authentication token is extracted from Request Header.<br>
 * @author Viswanath Lekshmanan
 */
public class MemcacheFilter implements Filter {

	private static final String AUTH_TOKEN = "auth_token";
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain filterChain) throws IOException, ServletException {
		try {
			HttpServletRequest request = (HttpServletRequest)req;
			String authToken = request.getHeader(AUTH_TOKEN);
			// Check if there is a token
			if(authToken == null || authToken.isEmpty()) {
				sendUnauthorizedResponse(res);
				return;
			}
			// Validate the auth_token
			MemcachedClient memcachedClient = MemCache.getMemcache();
			User user = (User)memcachedClient.get(authToken);
			if(user == null) {
				sendUnauthorizedResponse(res);
				return;
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		// Token is valid, So chain the request
		filterChain.doFilter(req, res);
	}

	/**
	 * Used to send unauthorized response
	 * @param res
	 * @throws IOException
	 */
	private void sendUnauthorizedResponse(ServletResponse res)
			throws IOException {
		HttpServletResponse response = (HttpServletResponse)res;
		response.getWriter().write(new GsonBuilder().create().toJson(new UnauthorizedAccessException().getResponse()));
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}

package exp.web.java.spark;

import static spark.Spark.before;
import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import net.spy.memcached.MemcachedClient;
import spark.Request;
import spark.Response;
import spark.servlet.SparkApplication;

import com.google.gson.GsonBuilder;

import exp.web.java.spark.auth.clients.MemCache;
import exp.web.java.spark.dto.SimpleDto;
import exp.web.java.spark.entity.User;
import exp.web.java.spark.exception.UnauthorizedAccessException;
import exp.web.java.spark.util.JsonTransformer;

/**
 * SparkDemo -
 *
 * @author Viswanath L
 *
 *         Dec 15, 2014
 */

public class SparkDemo implements SparkApplication {

	/** Key for memcached**/
	private static final String AUTH_TOKEN = "auth_token";

	/** Expiry time for memcached **/
	private static final int EXPIRY_TIME = 60 * 20;
	
	public SparkDemo() {
	}

	public void initialize() {
		
		before("/secure/*", (request, response) -> {
			try {
				String authToken = request.headers(AUTH_TOKEN);
				// Check if there is a token
				if(authToken == null || authToken.isEmpty()) {
					sendUnauthorizedResponse(response);
					return;
				}
				// Validate the auth_token
				MemcachedClient memcachedClient = MemCache.getMemcache();
				User user = (User)memcachedClient.get(authToken);
				if(user == null) {
					sendUnauthorizedResponse(response);
					return;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
		});
		
		post("/login",
				"application/json",
				(Request request, Response response) -> {
					try {
						String authToken = UUID.randomUUID().toString();
						MemcachedClient memcachedClient = MemCache
								.getMemcache();
						
						memcachedClient.set(authToken, EXPIRY_TIME, new User(new Long(
								1), "admin"));
						response.header(AUTH_TOKEN, authToken);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return new SimpleDto("login.success");
				}, new JsonTransformer());

		post("/secure/logout",
				"application/json",
				(Request request, Response response) -> {
					try {
						String authToken = request.headers(AUTH_TOKEN);
						MemcachedClient memcachedClient = MemCache
								.getMemcache();
						memcachedClient.delete(authToken);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return new SimpleDto("logout.success");
				}, new JsonTransformer());

		get("/secure/admin/hello", "application/json", (Request request,
				Response response) -> {
			return new SimpleDto("response.hello");
		}, new JsonTransformer());

		get("/secure/user/hello", "application/json", (Request request,
				Response response) -> {
					return new SimpleDto("response.hello");
		}, new JsonTransformer());

		exception(ClassNotFoundException.class, (Exception e, Request request,
				Response response) -> {
			response.body("404");
		});
	}
	
	/**
	 * Used to send unauthorized response
	 * @param res
	 * @throws IOException
	 */
	private void sendUnauthorizedResponse(Response res)
			throws IOException {
		HttpServletResponse response = (HttpServletResponse)res;
		response.getWriter().write(new GsonBuilder().create().toJson(new UnauthorizedAccessException().getResponse()));
	}


	@Override
	public void init() {
		initialize();
	}

}

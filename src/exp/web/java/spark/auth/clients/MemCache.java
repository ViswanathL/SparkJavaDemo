package exp.web.java.spark.auth.clients;

import java.net.InetSocketAddress;

import exp.web.java.spark.util.Util;
import net.spy.memcached.MemcachedClient;

/**
 * SparkDemo - exp.web.java.spark.auth
 *
 * @author Viswanath Lekshmanan
 *
 * Apr 24, 2015
 */

public class MemCache {

	/**
	 * <p>
	 * Singleton instance of memcache.<br>
	 * Memcache is used to store the client token information
	 */
	private static MemcachedClient memcachedClient;

	/**
	 * Used to get the memcache instance.<br>
	 * @return MemcachedClient
	 */
	public static MemcachedClient getMemcache() {
		try {
			Util util = new Util();
			if(memcachedClient == null) {
				memcachedClient = new MemcachedClient(new InetSocketAddress(util.readConfigProperty("memcache.ip"), Integer.parseInt(util.readConfigProperty("memcache.port"))));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return memcachedClient;
	}

}

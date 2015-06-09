package exp.web.java.spark.entity;

import java.io.Serializable;

/**
 * SparkDemo - exp.web.java.spark.entity
 *
 * @author Viswanath Lekshmanan
 *
 * Apr 23, 2015
 */

public class User implements Serializable {

	private static final long serialVersionUID = 8813011420146002681L;

	private Long id;
	
	private String username;

	public User(Long id, String username) {
		this.id = id;
		this.username = username;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}

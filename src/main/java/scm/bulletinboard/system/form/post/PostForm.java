package scm.bulletinboard.system.form.post;

import java.io.Serializable;

public class PostForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6035558741628182428L;
	
	private Integer id;
	
	private String title;
	
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

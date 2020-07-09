package scm.bulletinboard.system.form.user;

import java.util.Date;

public class UserForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6035558741628182428L;
	
	private Integer id;
	
	private String name;
	
	private String email;
	
	private Date createdFrom;
	
	private Date createdTo;

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getCreatedFrom() {
		return createdFrom;
	}



	public void setCreatedFrom(Date createdFrom) {
		this.createdFrom = createdFrom;
	}



	public Date getCreatedTo() {
		return createdTo;
	}



	public void setCreatedTo(Date createdTo) {
		this.createdTo = createdTo;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

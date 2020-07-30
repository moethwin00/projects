package scm.bulletinboard.system.form.user;

import java.io.Serializable;

/**
 * Form For User Search
 */
public class UserForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	private String email;

	private String createdFrom;

	private String createdTo;

	/**
	 * <h2>${id}</h2>
	 * <p>
	 * ${Getter Method For User Id}
	 * </p>
	 * 
	 * @return ${int}
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * <h2>${id}</h2>
	 * <p>
	 * ${Setter Method For User Id}
	 * </p>
	 * 
	 * @param ${id}
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * <h2>${name}</h2>
	 * <p>
	 * ${Getter Method For User Name}
	 * </p>
	 * 
	 * @return ${name}
	 */
	public String getName() {
		return name;
	}

	/**
	 * <h2>${name}</h2>
	 * <p>
	 * ${Setter Method For User Name}
	 * </p>
	 * 
	 * @param ${name}
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * <h2>${email}</h2>
	 * <p>
	 * ${Getter Method For User Email}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * <h2>${email}</h2>
	 * <p>
	 * ${Setter Method For User Email}
	 * </p>
	 * 
	 * @param ${email}
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * <h2>${createdFrom}</h2>
	 * <p>
	 * ${Getter Method For User Created From}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getCreatedFrom() {
		return createdFrom;
	}

	/**
	 * <h2>${createdFrom}</h2>
	 * <p>
	 * ${Setter Method For User CreatedFrom}
	 * </p>
	 * 
	 * @param ${createdFrom}
	 */
	public void setCreatedFrom(String createdFrom) {
		this.createdFrom = createdFrom;
	}

	/**
	 * <h2>${createdTo}</h2>
	 * <p>
	 * ${Getter Method For User Created To}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getCreatedTo() {
		return createdTo;
	}

	/**
	 * <h2>${createdTo}</h2>
	 * <p>
	 * ${Setter Method For User Created To}
	 * </p>
	 * 
	 * @param ${createdTo}
	 */
	public void setCreatedTo(String createdTo) {
		this.createdTo = createdTo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

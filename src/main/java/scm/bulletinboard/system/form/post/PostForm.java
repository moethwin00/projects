package scm.bulletinboard.system.form.post;

import java.io.Serializable;

/**
 * Form For Post
 */
public class PostForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6035558741628182428L;

	private Integer id;

	private String title;

	private String description;

	/**
	 * <h2>${id}</h2>
	 * <p>
	 * ${Getter Method For Post Id}
	 * </p>
	 * 
	 * @return ${Integer}
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * <h2>${id}</h2>
	 * <p>
	 * ${Setter Method For Post Id}
	 * </p>
	 * 
	 * @param ${id}
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * <h2>${title}</h2>
	 * <p>
	 * ${Getter Method For Post Title}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * <h2>${title}</h2>
	 * <p>
	 * ${Setter Method For Post Title}
	 * </p>
	 * 
	 * @param ${title}
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * <h2>${description}</h2>
	 * <p>
	 * ${Getter Method For Post Description}
	 * </p>
	 * 
	 * @return ${String}
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * <h2>${description}</h2>
	 * <p>
	 * ${Setter Method For Post Description}
	 * </p>
	 * 
	 * @param ${description}
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

package scm.bulletinboard.system.form.post;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import scm.bulletinboard.system.model.User;

/**
 * Form For Post Creation
 */
public class PostCreateForm {
	private Integer id;
	private int status;
	private User user;
	private Integer updatedUserId;
	private Integer deletedUserId;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;

	@NotEmpty(message = "*Title is required")
	@Size(max = 255, message = "*Title must be less than 255")
	private String title;

	@NotEmpty(message = "*Description is required")
	private String description;

	private boolean active;

	/**
	 * <h2>${id}</h2>
	 * <p>
	 * ${Getter Method For Post Id}
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

	/**
	 * <h2>${active}</h2>
	 * <p>
	 * ${Getter Method For Checking Whether Post Active Or Not}
	 * </p>
	 * 
	 * @return ${boolean}
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * <h2>${active}</h2>
	 * <p>
	 * ${Setter Method For Post Active}
	 * </p>
	 * 
	 * @param ${active}
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * <h2>${status}</h2>
	 * <p>
	 * ${Getter Method For Post Status}
	 * </p>
	 * 
	 * @return ${int}
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * <h2>${status}</h2>
	 * <p>
	 * ${Setter Method For Post Status}
	 * </p>
	 * 
	 * @param ${status}
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * <h2>${user}</h2>
	 * <p>
	 * ${Getter Method For User}
	 * </p>
	 * 
	 * @return ${Object}
	 */
	public User getUser() {
		return user;
	}

	/**
	 * <h2>${user}</h2>
	 * <p>
	 * ${Setter Method For User}
	 * </p>
	 * 
	 * @param ${user}
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * <h2>${updatedUserId}</h2>
	 * <p>
	 * ${Getter Method For Updated User Id}
	 * </p>
	 * 
	 * @return ${Integer}
	 */
	public Integer getUpdatedUserId() {
		return updatedUserId;
	}

	/**
	 * <h2>${updatedUserId}</h2>
	 * <p>
	 * ${SetterMethod For Updated User Id}
	 * </p>
	 * 
	 * @param ${updatedUserId}
	 */
	public void setUpdatedUserId(Integer updatedUserId) {
		this.updatedUserId = updatedUserId;
	}

	public Integer getDeletedUserId() {
		return deletedUserId;
	}

	public void setDeletedUserId(Integer deletedUserId) {
		this.deletedUserId = deletedUserId;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Date getDeletedAt() {
		return deletedAt;
	}

	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}

}

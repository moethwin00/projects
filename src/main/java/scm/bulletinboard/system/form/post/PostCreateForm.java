package scm.bulletinboard.system.form.post;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import scm.bulletinboard.system.model.User;

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
	

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getUpdatedUserId() {
		return updatedUserId;
	}

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

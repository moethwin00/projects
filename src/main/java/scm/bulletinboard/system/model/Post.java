package scm.bulletinboard.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "posts")
public class Post {
	private int id;
	private String title;
	private String description;
	private int status;
	private User user;
	private Integer createdUserId;
	private Integer updatedUserId;
	private Integer deletedUserId;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@ManyToOne
	@JoinColumn(name = "create_user_id", foreignKey = @ForeignKey(name="create_user_id_FK"))
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Column
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	public void setCreatedUserId(Integer createdUserId) {
		this.createdUserId = createdUserId;
	}
	@Column(name = "updated_user_id")
	public Integer getUpdatedUserId() {
		return updatedUserId;
	}
	public void setUpdatedUserId(Integer updatedUserId) {
		this.updatedUserId = updatedUserId;
	}
	
	@Column(name = "deleted_user_id")
	public Integer getDeletedUserId() {
		return deletedUserId;
	}
	public void setDeletedUserId(Integer deletedUserId) {
		this.deletedUserId = deletedUserId;
	}
	
	@Column(name = "created_at")
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Column(name = "updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	@Column(nullable = true, name = "deleted_at")
	public Date getDeletedAt() {
		return deletedAt;
	}
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
}

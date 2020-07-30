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

/**
 * Entity for Post
 */
@Entity
@Table(name = "posts")
public class Post {
	private int id;
	private String title;
	private String description;
	private int status;
	private User user;
	private User user2;
	private Integer deletedUserId;
	private Date createdAt;
	private Date updatedAt;
	private Date deletedAt;

	/**
	 * <h2>${id}</h2>
	 * <p>
	 * ${Getter Method For Post Id}
	 * </p>
	 * 
	 * @return ${int}
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public int getId() {
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
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * <h2>${user}</h2>
	 * <p>
	 * ${Getter Method For Created User}
	 * </p>
	 * 
	 * @return ${Object}
	 */
	@ManyToOne
	@JoinColumn(name = "create_user_id", foreignKey = @ForeignKey(name = "create_user_id_FK"))
	public User getUser() {
		return user;
	}

	/**
	 * <h2>${user}</h2>
	 * <p>
	 * ${Setter Method For Created User}
	 * </p>
	 * 
	 * @param ${user}
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * <h2>${user}</h2>
	 * <p>
	 * ${Getter Method For Updated User}
	 * </p>
	 * 
	 * @return ${Object}
	 */
	@ManyToOne
	@JoinColumn(name = "updated_user_id", foreignKey = @ForeignKey(name = "updated_user_id_FK"))
	public User getUser2() {
		return user2;
	}

	/**
	 * <h2>${user2}</h2>
	 * <p>
	 * ${Setter Method For Updated User}
	 * </p>
	 * 
	 * @param ${user2}
	 */
	public void setUser2(User user2) {
		this.user2 = user2;
	}

	/**
	 * <h2>${title}</h2>
	 * <p>
	 * ${Getter Method For Post Title}
	 * </p>
	 * 
	 * @return ${String}
	 */
	@Column
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
	@Column
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
	 * <h2>${status}</h2>
	 * <p>
	 * ${Getter Method For Post Status}
	 * </p>
	 * 
	 * @return ${int}
	 */
	@Column
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
	 * <h2>${deletedUserId}</h2>
	 * <p>
	 * ${Getter Method For Deleted User Id}
	 * </p>
	 * 
	 * @return ${Integer}
	 */
	@Column(name = "deleted_user_id")
	public Integer getDeletedUserId() {
		return deletedUserId;
	}

	/**
	 * <h2>${deletedUserId}</h2>
	 * <p>
	 * ${Setter Method For Deleted User Id}
	 * </p>
	 * 
	 * @param ${deletedUserId}
	 */
	public void setDeletedUserId(Integer deletedUserId) {
		this.deletedUserId = deletedUserId;
	}

	/**
	 * <h2>${createdAt}</h2>
	 * <p>
	 * ${Getter Method For Post Created At}
	 * </p>
	 * 
	 * @return ${Date}
	 */
	@Column(name = "created_at")
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * <h2>${createdAt}</h2>
	 * <p>
	 * ${Setter Method For Post Created At}
	 * </p>
	 * 
	 * @param ${createdAt}
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * <h2>${updatedAt}</h2>
	 * <p>
	 * ${Getter Method For Updated At}
	 * </p>
	 * 
	 * @return ${Date}
	 */
	@Column(name = "updated_at")
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * <h2>${updatedAt}</h2>
	 * <p>
	 * ${Setter Method For Deleted User Id}
	 * </p>
	 * 
	 * @param ${updatedAt}
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * <h2>${deletedAt}</h2>
	 * <p>
	 * ${Getter Method For Deleted At}
	 * </p>
	 * 
	 * @return ${Date}
	 */
	@Column(nullable = true, name = "deleted_at")
	public Date getDeletedAt() {
		return deletedAt;
	}

	/**
	 * <h2>${deletedAt}</h2>
	 * <p>
	 * ${Setter Method For Deleted At}
	 * </p>
	 * 
	 * @param ${deletedAt}
	 */
	public void setDeletedAt(Date deletedAt) {
		this.deletedAt = deletedAt;
	}
}

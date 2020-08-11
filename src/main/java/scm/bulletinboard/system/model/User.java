package scm.bulletinboard.system.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Entity for User
 */
@Entity
@Table(name = "users")
public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String profile;
    private String type;
    private String phone;
    private String address;
    private String dob;
    private int createUserId;
    private int updatedUserId;
    private Integer deletedUserId;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    /**
     * <h2>id</h2>
     * <p>
     * Getter Method For User Id
     * </p>
     * 
     * @return int
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    /**
     * <h2>id</h2>
     * <p>
     * Setter Method For User Id
     * </p>
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * <h2>name</h2>
     * <p>
     * Getter Method For User Name
     * </p>
     * 
     * @return String
     */
    @Column(name = "name")
    public String getName() {
        return name;
    }

    /**
     * <h2>name</h2>
     * <p>
     * Setter Method For User Name
     * </p>
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * <h2>email</h2>
     * <p>
     * Getter Method For User Email
     * </p>
     * 
     * @return String
     */
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    /**
     * <h2>email</h2>
     * <p>
     * Setter Method For User Email
     * </p>
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * <h2>password</h2>
     * <p>
     * Getter Method For User Password
     * </p>
     * 
     * @return String
     */
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    /**
     * <h2>password</h2>
     * <p>
     * Setter Method For User Password
     * </p>
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * <h2>profile</h2>
     * <p>
     * Getter Method For User Profile
     * </p>
     * 
     * @return String
     */
    @Column(name = "profile")
    public String getProfile() {
        return profile;
    }

    /**
     * <h2>profile</h2>
     * <p>
     * Setter Method For User Profile
     * </p>
     * 
     * @param profile
     */
    public void setProfile(String profile) {
        this.profile = profile;
    }

    /**
     * <h2>type</h2>
     * <p>
     * Getter Method For User Type
     * </p>
     * 
     * @return String
     */
    @Column(name = "type")
    public String getType() {
        return type;
    }

    /**
     * <h2>type</h2>
     * <p>
     * Setter Method For User Type
     * </p>
     * 
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * <h2>phone</h2>
     * <p>
     * Getter Method For User Phone
     * </p>
     * 
     * @return String
     */
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    /**
     * <h2>phone</h2>
     * <p>
     * Setter Method For User Phone
     * </p>
     * 
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * <h2>address</h2>
     * <p>
     * Getter Method For User Address
     * </p>
     * 
     * @return String
     */
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    /**
     * <h2>$address</h2>
     * <p>
     * Setter Method For User Address
     * </p>
     * 
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * <h2>dob</h2>
     * <p>
     * Getter Method For User Date Of Birth
     * </p>
     * 
     * @return String
     */
    @Column(nullable = true, name = "dob")
    public String getDob() {
        return dob;
    }

    /**
     * <h2>dob</h2>
     * <p>
     * Setter Method For User Date Of Birth
     * </p>
     * 
     * @param dob
     */
    public void setDob(String dob) {
        this.dob = dob;
    }

    /**
     * <h2>createUserId</h2>
     * <p>
     * Getter Method For Created User Id
     * </p>
     * 
     * @return int
     */
    @Column(name = "create_user_id")
    public int getCreateUserId() {
        return createUserId;
    }

    /**
     * <h2>createdUserId</h2>
     * <p>
     * Setter Method For Created User Id
     * </p>
     * 
     * @param createdUserId
     */
    public void setCreateUserId(int createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * <h2>updatedUserId</h2>
     * <p>
     * Getter Method For Updated User Id
     * </p>
     * 
     * @return int
     */
    @Column(name = "updated_user_id")
    public int getUpdatedUserId() {
        return updatedUserId;
    }

    /**
     * <h2>updatedUserId</h2>
     * <p>
     * Setter Method For Updated User Id
     * </p>
     * 
     * @param updatedUserId
     */
    public void setUpdatedUserId(int updatedUserId) {
        this.updatedUserId = updatedUserId;
    }

    /**
     * <h2>deletedUserId</h2>
     * <p>
     * Getter Method For Deleted User Id
     * </p>
     * 
     * @return Integer
     */
    @Column(name = "deleted_user_id")
    public Integer getDeletedUserId() {
        return deletedUserId;
    }

    /**
     * <h2>deletedUserId</h2>
     * <p>
     * Setter Method For Deleted User Id
     * </p>
     * 
     * @param deletdUserId
     */
    @Column(nullable = true)
    public void setDeletedUserId(Integer deletedUserId) {
        this.deletedUserId = deletedUserId;
    }

    /**
     * <h2>createdAt</h2>
     * <p>
     * Getter Method For Created At
     * </p>
     * 
     * @return Date
     */
    @Column(name = "created_at")
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * <h2>createdAt</h2>
     * <p>
     * Setter Method For Created At
     * </p>
     * 
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * <h2>updatedAt</h2>
     * <p>
     * Getter Method For Updated At
     * </p>
     * 
     * @return Date
     */
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * <h2>updatedAt</h2>
     * <p>
     * Setter Method For Updated At
     * </p>
     * 
     * @param updatedAt
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * <h2>deletedAt</h2>
     * <p>
     * Getter Method For Deleted At
     * </p>
     * 
     * @return Date
     */
    @Column(name = "deleted_at")
    public Date getDeletedAt() {
        return deletedAt;
    }

    /**
     * <h2>deletedAt</h2>
     * <p>
     * Setter Method For Deleted At
     * </p>
     * 
     * @param deletedAt
     */
    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public User(String name, String email, String password, String profile, String type, String phone, String address,
            String dob, int createUserId, int updatedUserId, Date createdAt, Date updatedAt) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.type = type;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.createUserId = createUserId;
        this.updatedUserId = updatedUserId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public User() {
    }

}
package pojo;

public class User {

    private Integer id;
    private String phone;
    private String password;
    private Boolean isAdmin;
    private String email;
    private Integer userType;
    private Integer grade;              // 用户等级
    private String avatar;              // 用户头像
    private Boolean ifReceiveLike;      // 是否接收点赞通知


    public User() {
    }

    public User(Integer id, String phone, String password, Boolean isAdmin, String email, Integer userType, Integer grade, String avatar, Boolean ifReceiveLike) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.isAdmin = isAdmin;
        this.email = email;
        this.userType = userType;
        this.grade = grade;
        this.avatar = avatar;
        this.ifReceiveLike = ifReceiveLike;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return isAdmin
     */
    public Boolean getIsAdmin() {
        return isAdmin;
    }

    /**
     * 设置
     * @param isAdmin
     */
    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取
     * @return userType
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 设置
     * @param userType
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 获取
     * @return grade
     */
    public Integer getGrade() {
        return grade;
    }

    /**
     * 设置
     * @param grade
     */
    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    /**
     * 获取
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取
     * @return ifReceiveLike
     */
    public Boolean getIfReceiveLike() {
        return ifReceiveLike;
    }

    /**
     * 设置
     * @param ifReceiveLike
     */
    public void setIfReceiveLike(Boolean ifReceiveLike) {
        this.ifReceiveLike = ifReceiveLike;
    }

    public String toString() {
        return "Users{id = " + id + ", phone = " + phone + ", password = " + password + ", isAdmin = " + isAdmin + ", email = " + email + ", userType = " + userType + ", grade = " + grade + ", avatar = " + avatar + ", ifReceiveLike = " + ifReceiveLike + "}";
    }
}

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
    private String name;
    private Integer receiveLikeCount;
    private Integer receiveReadCount;
    private Integer fansCount;
    private Integer postCount;
    private Integer mySubscribeCount;
    private Integer myCollectCount;
    private Integer myBoardCount;


    public User() {
    }

    public User(Integer id, String phone, String password, Boolean isAdmin, String email, Integer userType, Integer grade, String avatar, Boolean ifReceiveLike, String name, Integer receiveLikeCount, Integer receiveReadCount, Integer fansCount, Integer postCount, Integer mySubscribeCount, Integer myCollectCount, Integer myBoardCount) {
        this.id = id;
        this.phone = phone;
        this.password = password;
        this.isAdmin = isAdmin;
        this.email = email;
        this.userType = userType;
        this.grade = grade;
        this.avatar = avatar;
        this.ifReceiveLike = ifReceiveLike;
        this.name = name;
        this.receiveLikeCount = receiveLikeCount;
        this.receiveReadCount = receiveReadCount;
        this.fansCount = fansCount;
        this.postCount = postCount;
        this.mySubscribeCount = mySubscribeCount;
        this.myCollectCount = myCollectCount;
        this.myBoardCount = myBoardCount;
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

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return receiveLikeCount
     */
    public Integer getReceiveLikeCount() {
        return receiveLikeCount;
    }

    /**
     * 设置
     * @param receiveLikeCount
     */
    public void setReceiveLikeCount(Integer receiveLikeCount) {
        this.receiveLikeCount = receiveLikeCount;
    }

    /**
     * 获取
     * @return receiveReadCount
     */
    public Integer getReceiveReadCount() {
        return receiveReadCount;
    }

    /**
     * 设置
     * @param receiveReadCount
     */
    public void setReceiveReadCount(Integer receiveReadCount) {
        this.receiveReadCount = receiveReadCount;
    }

    /**
     * 获取
     * @return fansCount
     */
    public Integer getFansCount() {
        return fansCount;
    }

    /**
     * 设置
     * @param fansCount
     */
    public void setFansCount(Integer fansCount) {
        this.fansCount = fansCount;
    }

    /**
     * 获取
     * @return postCount
     */
    public Integer getPostCount() {
        return postCount;
    }

    /**
     * 设置
     * @param postCount
     */
    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    /**
     * 获取
     * @return mySubscribeCount
     */
    public Integer getMySubscribeCount() {
        return mySubscribeCount;
    }

    /**
     * 设置
     * @param mySubscribeCount
     */
    public void setMySubscribeCount(Integer mySubscribeCount) {
        this.mySubscribeCount = mySubscribeCount;
    }

    /**
     * 获取
     * @return myCollectCount
     */
    public Integer getMyCollectCount() {
        return myCollectCount;
    }

    /**
     * 设置
     * @param myCollectCount
     */
    public void setMyCollectCount(Integer myCollectCount) {
        this.myCollectCount = myCollectCount;
    }

    /**
     * 获取
     * @return myBoardCount
     */
    public Integer getMyBoardCount() {
        return myBoardCount;
    }

    /**
     * 设置
     * @param myBoardCount
     */
    public void setMyBoardCount(Integer myBoardCount) {
        this.myBoardCount = myBoardCount;
    }

    public String toString() {
        return "User{id = " + id + ", phone = " + phone + ", password = " + password + ", isAdmin = " + isAdmin + ", email = " + email + ", userType = " + userType + ", grade = " + grade + ", avatar = " + avatar + ", ifReceiveLike = " + ifReceiveLike + ", name = " + name + ", receiveLikeCount = " + receiveLikeCount + ", receiveReadCount = " + receiveReadCount + ", fansCount = " + fansCount + ", postCount = " + postCount + ", mySubscribeCount = " + mySubscribeCount + ", myCollectCount = " + myCollectCount + ", myBoardCount = " + myBoardCount + "}";
    }
}

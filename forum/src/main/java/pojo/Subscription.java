package pojo;

public class Subscription {

    private Integer id;
    private Integer userId;
    private Integer subscribeToUserId;
    private Integer subscribeToBoardId;


    public Subscription() {
    }

    public Subscription(Integer id, Integer userId, Integer subscribeToUserId, Integer subscribeToBoardId) {
        this.id = id;
        this.userId = userId;
        this.subscribeToUserId = subscribeToUserId;
        this.subscribeToBoardId = subscribeToBoardId;
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
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取
     * @return subscribeToUserId
     */
    public Integer getSubscribeToUserId() {
        return subscribeToUserId;
    }

    /**
     * 设置
     * @param subscribeToUserId
     */
    public void setSubscribeToUserId(Integer subscribeToUserId) {
        this.subscribeToUserId = subscribeToUserId;
    }

    /**
     * 获取
     * @return subscribeToBoardId
     */
    public Integer getSubscribeToBoardId() {
        return subscribeToBoardId;
    }

    /**
     * 设置
     * @param subscribeToBoardId
     */
    public void setSubscribeToBoardId(Integer subscribeToBoardId) {
        this.subscribeToBoardId = subscribeToBoardId;
    }

    public String toString() {
        return "Subscription{id = " + id + ", userId = " + userId + ", subscribeToUserId = " + subscribeToUserId + ", subscribeToBoardId = " + subscribeToBoardId + "}";
    }
}

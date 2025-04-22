package pojo;

public class BoardBan {
    private Integer id;
    private Integer banId;
    private Integer boardId;
    private String reason;


    public BoardBan() {
    }

    public BoardBan(Integer id, Integer banId, Integer boardId, String reason) {
        this.id = id;
        this.banId = banId;
        this.boardId = boardId;
        this.reason = reason;
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
     * @return banId
     */
    public Integer getBanId() {
        return banId;
    }

    /**
     * 设置
     * @param banId
     */
    public void setBanId(Integer banId) {
        this.banId = banId;
    }

    /**
     * 获取
     * @return boardId
     */
    public Integer getBoardId() {
        return boardId;
    }

    /**
     * 设置
     * @param boardId
     */
    public void setBoardId(Integer boardId) {
        this.boardId = boardId;
    }

    /**
     * 获取
     * @return reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * 设置
     * @param reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    public String toString() {
        return "BoardBan{id = " + id + ", banId = " + banId + ", boardId = " + boardId + ", reason = " + reason + "}";
    }
}
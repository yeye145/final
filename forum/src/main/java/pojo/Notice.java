package pojo;

import java.time.LocalDateTime;

public class Notice {

    private Integer id;
    private String content;
    private String boardId;
    private LocalDateTime time;


    public Notice() {
    }

    public Notice(Integer id, String content, String boardId, LocalDateTime time) {
        this.id = id;
        this.content = content;
        this.boardId = boardId;
        this.time = time;
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
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取
     * @return boardId
     */
    public String getBoardId() {
        return boardId;
    }

    /**
     * 设置
     * @param boardId
     */
    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    /**
     * 获取
     * @return time
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * 设置
     * @param time
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String toString() {
        return "Notice{id = " + id + ", content = " + content + ", boardId = " + boardId + ", time = " + time + "}";
    }
}

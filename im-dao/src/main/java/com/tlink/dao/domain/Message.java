package com.tlink.dao.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * 
 * @TableName t_lvxin_message
 */
@TableName(value ="t_lvxin_message")
public class Message implements Serializable {
    /**
     * 
     */
    @TableId
    private Long id;

    /**
     * 
     */
    private String action;

    /**
     * 
     */
    private String content;

    /**
     * 
     */
    private String extra;

    /**
     * 
     */
    private String format;

    /**
     * 
     */
    private String receiver;

    /**
     * 
     */
    private String sender;

    /**
     * 
     */
    private String state;

    /**
     * 
     */
    private Long timestamp;

    /**
     * 
     */
    private String title;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     */
    public String getAction() {
        return action;
    }

    /**
     * 
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 
     */
    public String getContent() {
        return content;
    }

    /**
     * 
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 
     */
    public String getExtra() {
        return extra;
    }

    /**
     * 
     */
    public void setExtra(String extra) {
        this.extra = extra;
    }

    /**
     * 
     */
    public String getFormat() {
        return format;
    }

    /**
     * 
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * 
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * 
     */
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    /**
     * 
     */
    public String getSender() {
        return sender;
    }

    /**
     * 
     */
    public void setSender(String sender) {
        this.sender = sender;
    }

    /**
     * 
     */
    public String getState() {
        return state;
    }

    /**
     * 
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * 
     */
    public Long getTimestamp() {
        return timestamp;
    }

    /**
     * 
     */
    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * 
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Message other = (Message) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAction() == null ? other.getAction() == null : this.getAction().equals(other.getAction()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()))
            && (this.getExtra() == null ? other.getExtra() == null : this.getExtra().equals(other.getExtra()))
            && (this.getFormat() == null ? other.getFormat() == null : this.getFormat().equals(other.getFormat()))
            && (this.getReceiver() == null ? other.getReceiver() == null : this.getReceiver().equals(other.getReceiver()))
            && (this.getSender() == null ? other.getSender() == null : this.getSender().equals(other.getSender()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()))
            && (this.getTimestamp() == null ? other.getTimestamp() == null : this.getTimestamp().equals(other.getTimestamp()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAction() == null) ? 0 : getAction().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        result = prime * result + ((getExtra() == null) ? 0 : getExtra().hashCode());
        result = prime * result + ((getFormat() == null) ? 0 : getFormat().hashCode());
        result = prime * result + ((getReceiver() == null) ? 0 : getReceiver().hashCode());
        result = prime * result + ((getSender() == null) ? 0 : getSender().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        result = prime * result + ((getTimestamp() == null) ? 0 : getTimestamp().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", action=").append(action);
        sb.append(", content=").append(content);
        sb.append(", extra=").append(extra);
        sb.append(", format=").append(format);
        sb.append(", receiver=").append(receiver);
        sb.append(", sender=").append(sender);
        sb.append(", state=").append(state);
        sb.append(", timestamp=").append(timestamp);
        sb.append(", title=").append(title);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.domain.Notification
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/26/16 2:02 PM
 *
 */

package com.iusworks.jaguar.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jag_notification")
public class Notifi {

    @Id
    private String id;

    private Short sid; // systemId

    private String uid;

    private Integer datetime;

    private String action;

    private String title;

    private String alert;

    private String storaged;

    private Integer recvAt;

    private Integer recvChan;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Short getSid() {
        return sid;
    }

    public void setSid(Short sid) {
        this.sid = sid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Integer getDatetime() {
        return datetime;
    }

    public void setDatetime(Integer datetime) {
        this.datetime = datetime;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlert() {
        return alert;
    }

    public void setAlert(String alert) {
        this.alert = alert;
    }

    public String getStoraged() {
        return storaged;
    }

    public void setStoraged(String storaged) {
        this.storaged = storaged;
    }

    public Integer getRecvAt() {
        return recvAt;
    }

    public void setRecvAt(Integer recvAt) {
        this.recvAt = recvAt;
    }

    public Integer getRecvChan() {
        return recvChan;
    }

    public void setRecvChan(Integer recvChan) {
        this.recvChan = recvChan;
    }

    @Override
    public String toString() {
        return "Notifi{" +
                "id='" + id + '\'' +
                ", sid=" + sid +
                ", uid='" + uid + '\'' +
                ", datetime=" + datetime +
                ", action='" + action + '\'' +
                ", title='" + title + '\'' +
                ", alert='" + alert + '\'' +
                ", storaged='" + storaged + '\'' +
                ", recvAt=" + recvAt +
                ", recvChan=" + recvChan +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notifi)) return false;

        Notifi notifi = (Notifi) o;

        if (id != null ? !id.equals(notifi.id) : notifi.id != null) return false;
        if (sid != null ? !sid.equals(notifi.sid) : notifi.sid != null) return false;
        if (uid != null ? !uid.equals(notifi.uid) : notifi.uid != null) return false;
        if (datetime != null ? !datetime.equals(notifi.datetime) : notifi.datetime != null) return false;
        if (action != null ? !action.equals(notifi.action) : notifi.action != null) return false;
        if (title != null ? !title.equals(notifi.title) : notifi.title != null) return false;
        if (alert != null ? !alert.equals(notifi.alert) : notifi.alert != null) return false;
        if (storaged != null ? !storaged.equals(notifi.storaged) : notifi.storaged != null) return false;
        if (recvAt != null ? !recvAt.equals(notifi.recvAt) : notifi.recvAt != null) return false;
        return recvChan != null ? recvChan.equals(notifi.recvChan) : notifi.recvChan == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        result = 31 * result + (action != null ? action.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (alert != null ? alert.hashCode() : 0);
        result = 31 * result + (storaged != null ? storaged.hashCode() : 0);
        result = 31 * result + (recvAt != null ? recvAt.hashCode() : 0);
        result = 31 * result + (recvChan != null ? recvChan.hashCode() : 0);
        return result;
    }
}

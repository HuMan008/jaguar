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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Notifi)) return false;

        Notifi that = (Notifi) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sid != null ? !sid.equals(that.sid) : that.sid != null) return false;
        if (uid != null ? !uid.equals(that.uid) : that.uid != null) return false;
        if (datetime != null ? !datetime.equals(that.datetime) : that.datetime != null) return false;
        if (action != null ? !action.equals(that.action) : that.action != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (alert != null ? !alert.equals(that.alert) : that.alert != null) return false;
        return storaged != null ? storaged.equals(that.storaged) : that.storaged == null;

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
        return result;
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
                '}';
    }
}

/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.model.entity.Device
 *
 * cluries <cluries@me.com>,  September 2016
 *
 * LastModified: 9/23/16 4:36 PM
 *
 */

package com.iusworks.jaguar.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.Map;
import java.util.Set;


@Document(collection = "jag_device")
public class Device {

    @Id
    private String id;

    private Short sid; // systemId

    private String did; // DeviceID

    private String uid;  //systemID中对应的UID

    private Byte type;

    private String vouch;

    private Set<String> tags;

    private Set<String> cares;

    private Map<String, String> infos;

    private Map<String, DevicePlatformVoucher> dpv;

    private Date updateAt;

    private Byte state;

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

    public String getDid() {
        return did;
    }

    public void setDid(String did) {
        this.did = did;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getVouch() {
        return vouch;
    }

    public void setVouch(String vouch) {
        this.vouch = vouch;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags = tags;
    }

    public Set<String> getCares() {
        return cares;
    }

    public void setCares(Set<String> cares) {
        this.cares = cares;
    }

    public Map<String, String> getInfos() {
        return infos;
    }

    public void setInfos(Map<String, String> infos) {
        this.infos = infos;
    }

    public Map<String, DevicePlatformVoucher> getDpv() {
        return dpv;
    }

    public void setDpv(Map<String, DevicePlatformVoucher> dpv) {
        this.dpv = dpv;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device)) return false;

        Device device = (Device) o;

        if (id != null ? !id.equals(device.id) : device.id != null) return false;
        if (sid != null ? !sid.equals(device.sid) : device.sid != null) return false;
        if (did != null ? !did.equals(device.did) : device.did != null) return false;
        if (uid != null ? !uid.equals(device.uid) : device.uid != null) return false;
        if (type != null ? !type.equals(device.type) : device.type != null) return false;
        if (vouch != null ? !vouch.equals(device.vouch) : device.vouch != null) return false;
        if (tags != null ? !tags.equals(device.tags) : device.tags != null) return false;
        if (cares != null ? !cares.equals(device.cares) : device.cares != null) return false;
        if (infos != null ? !infos.equals(device.infos) : device.infos != null) return false;
        if (dpv != null ? !dpv.equals(device.dpv) : device.dpv != null) return false;
        if (updateAt != null ? !updateAt.equals(device.updateAt) : device.updateAt != null) return false;
        return state != null ? state.equals(device.state) : device.state == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        result = 31 * result + (did != null ? did.hashCode() : 0);
        result = 31 * result + (uid != null ? uid.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (vouch != null ? vouch.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        result = 31 * result + (cares != null ? cares.hashCode() : 0);
        result = 31 * result + (infos != null ? infos.hashCode() : 0);
        result = 31 * result + (dpv != null ? dpv.hashCode() : 0);
        result = 31 * result + (updateAt != null ? updateAt.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Device{" +
                "id='" + id + '\'' +
                ", sid=" + sid +
                ", did='" + did + '\'' +
                ", uid='" + uid + '\'' +
                ", type=" + type +
                ", vouch='" + vouch + '\'' +
                ", tags=" + tags +
                ", cares=" + cares +
                ", infos=" + infos +
                ", dpv=" + dpv +
                ", updateAt=" + updateAt +
                ", state=" + state +
                '}';
    }
}

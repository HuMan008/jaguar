/*
 * Copyright (C) 2016.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.domain.System
 *
 * cluries <cluries@me.com>,  October 2016
 *
 * LastModified: 9/30/16 2:13 PM
 *
 */

package com.iusworks.jaguar.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "jag_system")
public class System {

    @Id
    private Integer id;

    private String apnsTopic;

    private String apsnCertificate;

    private String apsnCertificatePassword;

    private String lcAppid;

    private String lcAppkey;

    private String lcMasterkey;

    @Override
    public String toString() {
        return "System{" +
                "id=" + id +
                ", apnsTopic='" + apnsTopic + '\'' +
                ", apsnCertificate='" + apsnCertificate + '\'' +
                ", apsnCertificatePassword='" + apsnCertificatePassword + '\'' +
                ", lcAppid='" + lcAppid + '\'' +
                ", lcAppkey='" + lcAppkey + '\'' +
                ", lcMasterkey='" + lcMasterkey + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof System)) return false;

        System system = (System) o;

        if (id != null ? !id.equals(system.id) : system.id != null) return false;
        if (apnsTopic != null ? !apnsTopic.equals(system.apnsTopic) : system.apnsTopic != null) return false;
        if (apsnCertificate != null ? !apsnCertificate.equals(system.apsnCertificate) : system.apsnCertificate != null)
            return false;
        if (apsnCertificatePassword != null ? !apsnCertificatePassword.equals(system.apsnCertificatePassword) : system.apsnCertificatePassword != null)
            return false;
        if (lcAppid != null ? !lcAppid.equals(system.lcAppid) : system.lcAppid != null) return false;
        if (lcAppkey != null ? !lcAppkey.equals(system.lcAppkey) : system.lcAppkey != null) return false;
        return lcMasterkey != null ? lcMasterkey.equals(system.lcMasterkey) : system.lcMasterkey == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (apnsTopic != null ? apnsTopic.hashCode() : 0);
        result = 31 * result + (apsnCertificate != null ? apsnCertificate.hashCode() : 0);
        result = 31 * result + (apsnCertificatePassword != null ? apsnCertificatePassword.hashCode() : 0);
        result = 31 * result + (lcAppid != null ? lcAppid.hashCode() : 0);
        result = 31 * result + (lcAppkey != null ? lcAppkey.hashCode() : 0);
        result = 31 * result + (lcMasterkey != null ? lcMasterkey.hashCode() : 0);
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getApnsTopic() {
        return apnsTopic;
    }

    public void setApnsTopic(String apnsTopic) {
        this.apnsTopic = apnsTopic;
    }

    public String getApsnCertificate() {
        return apsnCertificate;
    }

    public void setApsnCertificate(String apsnCertificate) {
        this.apsnCertificate = apsnCertificate;
    }

    public String getApsnCertificatePassword() {
        return apsnCertificatePassword;
    }

    public void setApsnCertificatePassword(String apsnCertificatePassword) {
        this.apsnCertificatePassword = apsnCertificatePassword;
    }

    public String getLcAppid() {
        return lcAppid;
    }

    public void setLcAppid(String lcAppid) {
        this.lcAppid = lcAppid;
    }

    public String getLcAppkey() {
        return lcAppkey;
    }

    public void setLcAppkey(String lcAppkey) {
        this.lcAppkey = lcAppkey;
    }

    public String getLcMasterkey() {
        return lcMasterkey;
    }

    public void setLcMasterkey(String lcMasterkey) {
        this.lcMasterkey = lcMasterkey;
    }
}

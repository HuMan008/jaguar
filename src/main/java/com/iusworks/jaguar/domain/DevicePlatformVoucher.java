/*
 * Copyright (C) 2017.  Iusworks, Inc - All Rights Reserved
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 * Jaguar com.iusworks.jaguar.domain.DevicePlatformVoucher
 *
 * cluries <cluries@me.com>,  August 2017
 *
 * LastModified: 8/15/17 2:50 PM
 *
 */

package com.iusworks.jaguar.domain;


import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class DevicePlatformVoucher {

    private Date reqTime;

    private Date updatedAt;

    private String voucher;

    private Integer state;

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getVoucher() {
        return voucher;
    }

    public void setVoucher(String voucher) {
        this.voucher = voucher;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DevicePlatformVoucher)) return false;

        DevicePlatformVoucher that = (DevicePlatformVoucher) o;

        if (reqTime != null ? !reqTime.equals(that.reqTime) : that.reqTime != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;
        if (voucher != null ? !voucher.equals(that.voucher) : that.voucher != null) return false;
        return state != null ? state.equals(that.state) : that.state == null;
    }

    @Override
    public int hashCode() {
        int result = reqTime != null ? reqTime.hashCode() : 0;
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (voucher != null ? voucher.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DevicePlatformVoucher{" +
                "reqTime=" + reqTime +
                ", updatedAt=" + updatedAt +
                ", voucher='" + voucher + '\'' +
                ", state=" + state +
                '}';
    }
}

package com.yoval.community.model;

import java.util.Date;

/**
 * Created by Yovanna on 2017-04-12.
 */

public class Service {

    String uid;
    String title;
    String detail;
    int price;
    Date publishedDate;
    Date fromDate;
    Date toDate;


    public Service(String uid, String title, String detail, int price, Date fromDate, Date toDate, Date publishedDate) {
        this.uid= uid;
        this.title = title;
        this.detail = detail;
        this.price = price;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.publishedDate= publishedDate;
    }

    public String getUid() { return uid;}

    public void setUid(String uid) {this.uid = uid; }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getPublishedDate() {return publishedDate;}

    public void setPublishedDate(Date publishedDate) {
        this.publishedDate = publishedDate;
    }
}

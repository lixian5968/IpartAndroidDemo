package com.zongbutech.iparty.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "COUPON".
 */
public class Coupon {

    private Long id;
    private String objectId;
    private String user_id;
    private java.util.Date created_time;
    private java.util.Date updated_time;
    private java.util.Date start_time;
    private java.util.Date end_time;
    private String photo;
    private String state;
    private String amount;
    private String limited_amount;
    private String description;

    public Coupon() {
    }

    public Coupon(Long id) {
        this.id = id;
    }

    public Coupon(Long id, String objectId, String user_id, java.util.Date created_time, java.util.Date updated_time, java.util.Date start_time, java.util.Date end_time, String photo, String state, String amount, String limited_amount, String description) {
        this.id = id;
        this.objectId = objectId;
        this.user_id = user_id;
        this.created_time = created_time;
        this.updated_time = updated_time;
        this.start_time = start_time;
        this.end_time = end_time;
        this.photo = photo;
        this.state = state;
        this.amount = amount;
        this.limited_amount = limited_amount;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public java.util.Date getCreated_time() {
        return created_time;
    }

    public void setCreated_time(java.util.Date created_time) {
        this.created_time = created_time;
    }

    public java.util.Date getUpdated_time() {
        return updated_time;
    }

    public void setUpdated_time(java.util.Date updated_time) {
        this.updated_time = updated_time;
    }

    public java.util.Date getStart_time() {
        return start_time;
    }

    public void setStart_time(java.util.Date start_time) {
        this.start_time = start_time;
    }

    public java.util.Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(java.util.Date end_time) {
        this.end_time = end_time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLimited_amount() {
        return limited_amount;
    }

    public void setLimited_amount(String limited_amount) {
        this.limited_amount = limited_amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

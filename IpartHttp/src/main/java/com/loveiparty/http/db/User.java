package com.loveiparty.http.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "USER".
 */
public class User extends com.loveiparty.http.Bean.BaseHttpBean  {

    private Long id;
    private String avatar;
    private Integer has_change_sex;
    private java.util.Date last_update_time;
    private String nickname;
    private String open_id;
    private java.util.Date register_time;
    private Integer sex;
    private String telephone;
    private Integer user_id;
    private Integer user_type;
    private String objectId;
    private String province;
    private String city;
    private String country;
    private String access_token;
    private Integer platform_id;
    private String thirdparty_account;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(Long id, String avatar, Integer has_change_sex, java.util.Date last_update_time, String nickname, String open_id, java.util.Date register_time, Integer sex, String telephone, Integer user_id, Integer user_type, String objectId, String province, String city, String country, String access_token, Integer platform_id, String thirdparty_account) {
        this.id = id;
        this.avatar = avatar;
        this.has_change_sex = has_change_sex;
        this.last_update_time = last_update_time;
        this.nickname = nickname;
        this.open_id = open_id;
        this.register_time = register_time;
        this.sex = sex;
        this.telephone = telephone;
        this.user_id = user_id;
        this.user_type = user_type;
        this.objectId = objectId;
        this.province = province;
        this.city = city;
        this.country = country;
        this.access_token = access_token;
        this.platform_id = platform_id;
        this.thirdparty_account = thirdparty_account;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getHas_change_sex() {
        return has_change_sex;
    }

    public void setHas_change_sex(Integer has_change_sex) {
        this.has_change_sex = has_change_sex;
    }

    public java.util.Date getLast_update_time() {
        return last_update_time;
    }

    public void setLast_update_time(java.util.Date last_update_time) {
        this.last_update_time = last_update_time;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public java.util.Date getRegister_time() {
        return register_time;
    }

    public void setRegister_time(java.util.Date register_time) {
        this.register_time = register_time;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getUser_type() {
        return user_type;
    }

    public void setUser_type(Integer user_type) {
        this.user_type = user_type;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(Integer platform_id) {
        this.platform_id = platform_id;
    }

    public String getThirdparty_account() {
        return thirdparty_account;
    }

    public void setThirdparty_account(String thirdparty_account) {
        this.thirdparty_account = thirdparty_account;
    }

}

package com.zongbutech.iparty.db;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table "URL_STRING".
 */
public class UrlString {

    private Long id;
    private String UrlType;
    private String Url;

    public UrlString() {
    }

    public UrlString(Long id) {
        this.id = id;
    }

    public UrlString(Long id, String UrlType, String Url) {
        this.id = id;
        this.UrlType = UrlType;
        this.Url = Url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrlType() {
        return UrlType;
    }

    public void setUrlType(String UrlType) {
        this.UrlType = UrlType;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String Url) {
        this.Url = Url;
    }

}

package com.federalbrandsltdtest.pojos;

import io.realm.RealmObject;

import java.util.Arrays;

public class PhotosRealmPojo extends RealmObject {

    private String thumbnailUrl;
    private String url;
    private String title;
    private int id;
    private byte[] byte_thumbnil_url;
    private byte[] byte_url;


    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte[] getByte_thumbnil_url() {
        return byte_thumbnil_url;
    }

    public void setByte_thumbnil_url(byte[] byte_thumbnil_url) {
        this.byte_thumbnil_url = byte_thumbnil_url;
    }

    public byte[] getByte_url() {
        return byte_url;
    }

    public void setByte_url(byte[] byte_url) {
        this.byte_url = byte_url;
    }

    @Override
    public String toString() {
        return "PhotosRealmPojo{" +
                "thumbnailUrl='" + thumbnailUrl + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                ", byte_thumbnil_url=" + Arrays.toString(byte_thumbnil_url) +
                ", byte_url=" + Arrays.toString(byte_url) +
                '}';
    }
}

package com.federalbrandsltdtest.pojos;

import io.realm.RealmObject;

public class PhotosRealmPojo extends RealmObject {

    private String thumbnailUrl;
    private String url;
    private String title;
    private int id;


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

    @Override
    public String toString() {
        return "PhotosRealmPojo{" +
                "thumbnailUrl='" + thumbnailUrl + '\'' +
                ", url='" + url + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}

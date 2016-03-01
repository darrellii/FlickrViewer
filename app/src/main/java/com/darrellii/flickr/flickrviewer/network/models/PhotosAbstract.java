package com.darrellii.flickr.flickrviewer.network.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Key;

import java.util.List;

/**
 * Created by dj on 2/28/16.
 * For Use By Flickr Viewer
 */
public abstract class PhotosAbstract extends GenericJson {
    //<editor-fold defaultstate="collapsed" desc="Attributes">
    @Key("page")
    private Integer page;

    @Key("per_page")
    private Integer perPage;

    @Key("pages")
    private Integer pages;

    @Key("photo")
    private List<Photo> photoList;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public final Integer getPage() {
        return page;
    }

    public final Integer getPerPage() {
        return perPage;
    }

    public final Integer getPages() {
        return pages;
    }

    public final List<Photo> getPhotoList() {
        return photoList;
    }

    @Override
    public Photos clone() {
        return (Photos) super.clone();
    }

    @Override
    public PhotosAbstract set(String fieldName, Object value) {
        return (PhotosAbstract) super.set(fieldName, value);
    }
    //</editor-fold>

}

package com.darrellii.flickr.flickrviewer.network.models;

import com.google.api.client.util.Key;

/**
 * Created by dj on 2/28/16.
 * For Use By Flickr Viewer
 */
public class PhotosContainerTotalIsString extends FlickrResponse {

    @Key("photos")
    private PhotosTotalIsString photos;

    public final PhotosTotalIsString getPhotos() {
        return photos;
    }

    @Override
    public PhotosContainer clone() {
        return (PhotosContainer) super.clone();
    }

    @Override
    public PhotosContainer set(String fieldName, Object value) {
        return (PhotosContainer) super.set(fieldName, value);
    }
}

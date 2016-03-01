package com.darrellii.flickr.flickrviewer.network.models;

import com.google.api.client.util.Key;

/**
 * Created by dj on 2/29/16.
 * For Use By Flickr Viewer
 */
public class PersonContainer extends FlickrResponse {

    @Key("person")
    private Person person;

    public final Person getPerson() {
        return person;
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

package com.darrellii.flickr.flickrviewer.network.requests;

import com.darrellii.flickr.flickrviewer.network.FlickrClient;
import com.darrellii.flickr.flickrviewer.network.models.PhotosContainerTotalIsString;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;

/**
 * Created by dj on 2/28/16.
 * For Use By Flickr Viewer
 */
public class MyPhotosList extends
        FlickrRequest<PhotosContainerTotalIsString> {

    public MyPhotosList(FlickrClient client) {
        super(client, HttpMethods.GET,"",null,PhotosContainerTotalIsString.class);
        setMethod("flickr.people.getPhotos");
    }
    @Key("user_id")
    private String user_id;

    public final MyPhotosList setUserId(String user_id) {
        this.user_id = user_id;
        return this;
    }

}

package com.darrellii.flickr.flickrviewer.network.requests;

import com.darrellii.flickr.flickrviewer.network.FlickrClient;
import com.darrellii.flickr.flickrviewer.network.models.PhotosContainerTotalIsString;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;

/**
 * Created by dj on 2/29/16.
 * For Use By Flickr Viewer
 */
public class FavoritesRequest extends
        FlickrRequest<PhotosContainerTotalIsString> {
    public FavoritesRequest(FlickrClient client) {
        super(client, HttpMethods.GET, "", null, PhotosContainerTotalIsString.class);
        setMethod("flickr.favorites.getList");
    }


    //<editor-fold defaultstate="collapsed" desc="Attributes">
    @Key("extras")
    private String extras;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters And Setters">
    public final String getExtras() {
        return extras;
    }

    public final FavoritesRequest setExtras(String extras) {
        this.extras = extras;
        return this;
    }
    //</editor-fold>

}


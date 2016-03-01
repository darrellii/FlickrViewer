package com.darrellii.flickr.flickrviewer.network.requests;

import com.darrellii.flickr.flickrviewer.network.FlickrClient;
import com.darrellii.flickr.flickrviewer.network.models.PhotosContainer;
import com.google.api.client.http.HttpMethods;
import com.google.api.client.util.Key;

/**
 * Created by dj on 2/27/16.
 * For Use By Flickr Viewer
 */
public class InterestingnessList extends
        FlickrRequest<PhotosContainer> {

    public InterestingnessList(FlickrClient client) {
        super(client,HttpMethods.GET,"",null,PhotosContainer.class);
        setMethod("flickr.interestingness.getList");

    }

    //<editor-fold defaultstate="collapsed" desc="Attributes">
    @Key("extras")
    private String extras;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters And Setters">
    public final String getExtras() {
        return extras;
    }

    public final InterestingnessList setExtras(String extras) {
        this.extras = extras;
        return this;
    }
    //</editor-fold>

}

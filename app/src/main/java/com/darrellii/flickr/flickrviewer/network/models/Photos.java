package com.darrellii.flickr.flickrviewer.network.models;

import com.google.api.client.util.Key;

/**
 * Created by dj on 2/26/16.
 * For Use By Flickr Viewer
 */
public class Photos extends PhotosAbstract {

    //<editor-fold defaultstate="collapsed" desc="Attributes">
    @Key("total")
    private Integer total;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public final Integer getTotal() {
        return total;
    }
    //</editor-fold>

}

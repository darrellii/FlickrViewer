package com.darrellii.flickr.flickrviewer.network.models;

import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

/**
 * Created by dj on 2/28/16.
 * For Use By Flickr Viewer
 */
public class PhotosTotalIsString extends PhotosAbstract {

    //<editor-fold defaultstate="collapsed" desc="Attributes">
    @JsonString
    @Key("total")
    private String total;

    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">
    public final String getTotal() {
        return total;
    }

    //</editor-fold>

}

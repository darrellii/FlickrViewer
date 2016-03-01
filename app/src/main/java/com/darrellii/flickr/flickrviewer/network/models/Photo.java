package com.darrellii.flickr.flickrviewer.network.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

/**
 * Created by dj on 2/26/16.
 * For Use By Flickr Viewer
 */
public class Photo extends GenericJson {

    //<editor-fold defaultstate="collapsed" desc="Attributes">
    @Key("id")
    private String id;

    @Key("secret")
    private String secret;

    @Key("server")
    private String server;

    @Key("farm")
    private Integer farm;

    @Key("iconserver")
    private String iconServer;

    @Key("iconfarm")
    private Integer iconFarm;

    @Key("owner")
    private String owner;

    @Key("username")
    private String ownername;

    @Key("title")
    private String title;

    @JsonString
    @Key("lastupdate")
    private Long lastupdate;
    //</editor-fold>

    //<editor-fold defaultstate="collapsed"  desc="Getters and Setters">
    public final String getId() {
        return id;
    }

    public final String getSecret() {
        return secret;
    }

    public final String getServer() {
        return server;
    }

    public final Integer getFarm() {
        return farm;
    }

    public final String getIconServer() {
        return iconServer;
    }

    public final Integer getIconFarm() {
        return iconFarm;
    }

    public final String getOwner() {
        return owner;
    }

    public final String getOwnername() {
        return ownername;
    }

    public final String getTitle() {
        return title;
    }

    public final Long getLastupdate() {
        return lastupdate;
    }

    @Override
    public Photo clone() {
        return (Photo) super.clone();
    }

    @Override
    public Photo set(String fieldName, Object value) {
        return (Photo) super.set(fieldName, value);
    }
    //</editor-fold>
}


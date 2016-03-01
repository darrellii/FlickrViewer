package com.darrellii.flickr.flickrviewer.network.models;

import com.google.api.client.util.Key;

/**
 * Created by dj on 2/29/16.
 * For Use By Flickr Viewer
 */
public class Person {

    @Key("username")
    private Dummy username;
    @Key("realname")
    private Dummy realname;

    public Dummy getRealname() {
        return realname;
    }

    public void setRealname(Dummy realname) {
        this.realname = realname;
    }

    public Dummy getUsername() {
        return username;
    }

    public void setUsername(Dummy username) {
        this.username = username;
    }


}

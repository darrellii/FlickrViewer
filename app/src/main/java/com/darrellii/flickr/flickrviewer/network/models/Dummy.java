package com.darrellii.flickr.flickrviewer.network.models;

import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonString;
import com.google.api.client.util.Key;

/**
 * Created by dj on 2/29/16.
 * For Use By Flickr Viewer
 */
public class Dummy extends GenericJson {

    @JsonString
    @Key("_content")
    private String _content;

    public String get_content() {
        return _content;
    }

    public void set_content(String _content) {
        this._content = _content;
    }
}

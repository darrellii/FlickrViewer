package com.darrellii.flickr.flickrviewer.network;

import com.darrellii.flickr.flickrviewer.network.requests.FlickrRequest;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.googleapis.services.json.CommonGoogleJsonClientRequestInitializer;

import java.io.IOException;

/**
 * Created by dj on 2/26/16.
 * For Use By Flickr Viewer
 */
public class FlickrRequestInitializer extends CommonGoogleJsonClientRequestInitializer {

    public FlickrRequestInitializer() {
        super();
    }

    @Override
    protected void initializeJsonRequest(AbstractGoogleJsonClientRequest<?> request)
            throws IOException {
        super.initializeJsonRequest(request);
        initializeFlickrRequest((FlickrRequest<?>) request);
    }

    protected void initializeFlickrRequest(FlickrRequest<?> request)
            throws java.io.IOException {
        request.setFormat("json");
        request.setNoJsonCallback(true);
    }

}
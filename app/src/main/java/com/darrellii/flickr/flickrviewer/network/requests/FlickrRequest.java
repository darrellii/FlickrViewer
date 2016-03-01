package com.darrellii.flickr.flickrviewer.network.requests;

import com.darrellii.flickr.flickrviewer.network.FlickrClient;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClientRequest;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.util.Key;

/**
 * Created by dj on 2/26/16.
 * For Use By Flickr Viewer
 *
 * Referenced wuman example
 */
public class FlickrRequest<T> extends AbstractGoogleJsonClientRequest<T> {

    public FlickrRequest(FlickrClient client, String requestMethod,
                         String uriTemplate, Object content, Class<T> responseClass) {
        super(client,
                requestMethod,
                uriTemplate,
                content,
                responseClass);
        setFormat("json");
        setNoJsonCallback(true);
    }


    // <editor-fold defaultstate="collapsed" desc="attributes">

    @Key("method")
    private String method;

    @Key("format")
    private String format;

    @Key("api_key")
    private String apiKey;

    @Key("nojsoncallback")
    private Integer noJsonCallback;
    // </editor-fold>


    //<editor-fold defaultstate="collapsed" desc="Getters and Setters">


    public final FlickrRequest<T> setMethod(String method) {
        this.method = method;
        return this;
    }

    public final FlickrRequest<T> setFormat(String format) {
        this.format = format;
        return this;
    }

    public final FlickrRequest<T> setApiKey(String apiKey) {
        this.apiKey = apiKey;
        return this;
    }

    public final FlickrRequest<T> setNoJsonCallback(Boolean noJsonCallback) {
        this.noJsonCallback = noJsonCallback ? 1 : 0;
        return this;
    }

    @Override
    public FlickrClient getAbstractGoogleClient() {
        return (FlickrClient) super.getAbstractGoogleClient();
    }

    @Override
    public FlickrRequest<T> setDisableGZipContent(boolean disableGZipContent) {
        return (FlickrRequest<T>) super.setDisableGZipContent(disableGZipContent);
    }

    @Override
    public FlickrRequest<T> setRequestHeaders(HttpHeaders headers) {
        return (FlickrRequest<T>) super.setRequestHeaders(headers);
    }

    @Override
    public FlickrRequest<T> set(String fieldName, Object value) {
        return (FlickrRequest<T>) super.set(fieldName, value);
    }
    //</editor-fold>

}


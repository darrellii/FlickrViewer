package com.darrellii.flickr.flickrviewer.network;

import com.darrellii.flickr.flickrviewer.network.requests.FavoritesRequest;
import com.darrellii.flickr.flickrviewer.network.requests.InterestingnessList;
import com.darrellii.flickr.flickrviewer.network.requests.MyPhotosList;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient;
import com.google.api.client.http.HttpRequestInitializer;

import java.io.IOException;

/**
 * Created by dj on 2/26/16.
 * For Use By Flickr Viewer
 */
public class FlickrClient extends AbstractGoogleJsonClient {

    public static final String DEFAULT_ROOT_URL = "https://secure.flickr.com/";

    public static final String DEFAULT_SERVICE_PATH = "services/rest/";


    FlickrClient(Builder builder) {
        super(builder);
    }

    @Override
    protected void initialize(AbstractGoogleClientRequest<?> httpClientRequest) throws IOException {
        super.initialize(httpClientRequest);
    }

    public InterestingnessList getInterestingnessList() throws IOException {
        InterestingnessList request = new InterestingnessList(FlickrClient.this);
        initialize(request);
        return request;
    }

    public MyPhotosList getMyPhotos() throws IOException {
        MyPhotosList request = new MyPhotosList(FlickrClient.this);
        initialize(request);

        return request;
    }

    public FavoritesRequest getMyFavorites() throws IOException {
        FavoritesRequest request = new FavoritesRequest(FlickrClient.this);
        initialize(request);
        return request;
    }


    public static final class Builder extends
            com.google.api.client.googleapis.services.json.AbstractGoogleJsonClient.Builder {

        public Builder(com.google.api.client.http.HttpTransport transport,
                       com.google.api.client.json.JsonFactory jsonFactory,
                       com.google.api.client.http.HttpRequestInitializer httpRequestInitializer) {
            super(transport,
                    jsonFactory,
                    DEFAULT_ROOT_URL,
                    DEFAULT_SERVICE_PATH,
                    httpRequestInitializer,
                    false);
        }

        @Override
        public FlickrClient build() {
            return new FlickrClient(this);
        }

        //<editor-fold defaultstate="collapsed" desc="Builder Methods">
        @Override
        public Builder setRootUrl(String rootUrl) {
            return (Builder) super.setRootUrl(rootUrl);
        }

        @Override
        public Builder setServicePath(String servicePath) {
            return (Builder) super.setServicePath(servicePath);
        }

        @Override
        public Builder setGoogleClientRequestInitializer(
                GoogleClientRequestInitializer googleClientRequestInitializer) {
            return (Builder) super
                    .setGoogleClientRequestInitializer(googleClientRequestInitializer);
        }

        @Override
        public Builder setHttpRequestInitializer(HttpRequestInitializer httpRequestInitializer) {
            return (Builder) super.setHttpRequestInitializer(httpRequestInitializer);
        }

        @Override
        public Builder setApplicationName(String applicationName) {
            return (Builder) super.setApplicationName(applicationName);
        }

        @Override
        public Builder setSuppressPatternChecks(boolean suppressPatternChecks) {
            return (Builder) super.setSuppressPatternChecks(suppressPatternChecks);
        }

        @Override
        public Builder setSuppressRequiredParameterChecks(boolean suppressRequiredParameterChecks) {
            return (Builder) super
                    .setSuppressRequiredParameterChecks(suppressRequiredParameterChecks);
        }

        @Override
        public Builder setSuppressAllChecks(boolean suppressAllChecks) {
            return (Builder) super.setSuppressAllChecks(suppressAllChecks);
        }

        public Builder setFlickrRequestInitializer(
                FlickrRequestInitializer instagramRequestInitializer) {
            return (Builder) super.setGoogleClientRequestInitializer(instagramRequestInitializer);
        }
        //</editor-fold>

    }

}
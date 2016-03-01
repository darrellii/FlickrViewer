package com.darrellii.flickr.flickrviewer.app.loaders;

import android.support.v4.app.FragmentActivity;

import com.darrellii.flickr.flickrviewer.app.FlickrUtil;
import com.darrellii.flickr.flickrviewer.network.FlickrClient;
import com.darrellii.flickr.flickrviewer.network.FlickrRequestInitializer;
import com.darrellii.flickr.flickrviewer.network.OAuth;
import com.darrellii.flickr.flickrviewer.network.models.PhotosContainer;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.util.Lists;

import java.io.IOException;

/**
 * Created by dj on 2/27/16.
 * For Use By Flickr Viewer
 */
public class FlickrInterestingnessLoader extends AsyncResourceLoader<PhotosContainer> {

    private final OAuth oauth;
    private FlickrClient client;

    public FlickrInterestingnessLoader(FragmentActivity activity) {
        super(activity);
        oauth = OAuth.newInstance(activity.getApplicationContext(),
                activity.getSupportFragmentManager());
    }

    @Override
    public PhotosContainer loadResourceInBackground() throws Exception {
        client = new FlickrClient.Builder(OAuth.HTTP_TRANSPORT,
                OAuth.JSON_FACTORY,
                oauth.authorize10a("flickr").getResult())
                .setApplicationName("Flickr Viewer")
                .setFlickrRequestInitializer(new FlickrRequestInitializer())
                .build();
        PhotosContainer photos = client.getInterestingnessList().setExtras("icon_server")
                .execute();

        return photos;
    }

    @Override
    public void updateErrorStateIfApplicable(Result<PhotosContainer> result) {
        PhotosContainer data = result.data;
        result.success = "ok".equals(data.getStat());
        result.errorMessage = result.success ? null :
                (data.getErrorCode() + ": " + data.getErrorMessage());
    }

}

package com.darrellii.flickr.flickrviewer.app.loaders;

import android.support.v4.app.FragmentActivity;

import com.darrellii.flickr.flickrviewer.network.FlickrClient;
import com.darrellii.flickr.flickrviewer.network.FlickrRequestInitializer;
import com.darrellii.flickr.flickrviewer.network.OAuth;
import com.darrellii.flickr.flickrviewer.network.models.PhotosContainerTotalIsString;

/**
 * Created by dj on 2/29/16.
 * For Use By Flickr Viewer
 */
public class FavoritesLoader extends AsyncResourceLoader<PhotosContainerTotalIsString> {

    private final OAuth oauth;
    private FlickrClient client;

    public FavoritesLoader(FragmentActivity activity) {
        super(activity);
        oauth = OAuth.newInstance(activity.getApplicationContext(),
                activity.getSupportFragmentManager());
    }

    @Override
    public PhotosContainerTotalIsString loadResourceInBackground() throws Exception {
        client = new FlickrClient.Builder(OAuth.HTTP_TRANSPORT,
                OAuth.JSON_FACTORY,
                oauth.authorize10a("flickr").getResult())
                .setApplicationName("Flickr Viewer")
                .setFlickrRequestInitializer(new FlickrRequestInitializer())
                .build();
        PhotosContainerTotalIsString photosContainer = client.getMyFavorites().setExtras("icon_server")
                .execute();

        return photosContainer;
    }

    @Override
    public void updateErrorStateIfApplicable(Result<PhotosContainerTotalIsString> result) {
        PhotosContainerTotalIsString data = result.data;
        result.success = "ok".equals(data.getStat());
        result.errorMessage = result.success ? null :
                (data.getErrorCode() + ": " + data.getErrorMessage());
    }

}
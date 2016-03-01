package com.darrellii.flickr.flickrviewer.app.loader_callbacks;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.darrellii.flickr.flickrviewer.app.loaders.AsyncResourceLoader;
import com.darrellii.flickr.flickrviewer.app.loaders.FlickrInterestingnessLoader;
import com.darrellii.flickr.flickrviewer.app.ui.PhotosAdapter;
import com.darrellii.flickr.flickrviewer.network.models.PhotosContainer;

/**
 * Created by dj on 2/29/16.
 * For Use By Flickr Viewer
 */
public class InterestingnessCallbacks implements
        LoaderManager.LoaderCallbacks<AsyncResourceLoader.Result<PhotosContainer>> {
    private FragmentActivity mActivity;
    private PhotosAdapter mAdapter;

    public InterestingnessCallbacks(FragmentActivity activity, PhotosAdapter adapter) {
        mActivity = activity;
        mAdapter = adapter;
    }

    @Override
    public Loader<AsyncResourceLoader.Result<PhotosContainer>> onCreateLoader(int id, Bundle args) {
        return new FlickrInterestingnessLoader(mActivity);
    }

    @Override
    public void onLoadFinished(Loader<AsyncResourceLoader.Result<PhotosContainer>> loader,
                               AsyncResourceLoader.Result<PhotosContainer> result) {
        mAdapter.setData(result.data.getPhotos().getPhotoList(), true);
    }

    @Override
    public void onLoaderReset(Loader<AsyncResourceLoader.Result<PhotosContainer>> loader) {
        mAdapter.setData( null, true);
    }


}

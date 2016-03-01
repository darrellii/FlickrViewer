package com.darrellii.flickr.flickrviewer.app.loader_callbacks;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.darrellii.flickr.flickrviewer.app.loaders.AsyncResourceLoader;
import com.darrellii.flickr.flickrviewer.app.loaders.FlickrMyUserLoader;
import com.darrellii.flickr.flickrviewer.app.ui.PhotosAdapter;
import com.darrellii.flickr.flickrviewer.network.models.Photo;
import com.darrellii.flickr.flickrviewer.network.models.PhotosContainerTotalIsString;

import java.util.List;

/**
 * Created by dj on 2/29/16.
 * For Use By Flickr Viewer
 */
public class MyPhotosCallbacks implements LoaderManager.LoaderCallbacks<AsyncResourceLoader.Result<PhotosContainerTotalIsString>> {

    private FragmentActivity mActivity;
    private PhotosAdapter mAdapter;

    public MyPhotosCallbacks(FragmentActivity activity, PhotosAdapter adapter) {
        mActivity = activity;
        mAdapter = adapter;
    }

    @Override
    public Loader<AsyncResourceLoader.Result<PhotosContainerTotalIsString>> onCreateLoader(int id, Bundle args) {
        return new FlickrMyUserLoader(mActivity);
    }

    @Override
    public void onLoadFinished(Loader<AsyncResourceLoader.Result<PhotosContainerTotalIsString>> loader, AsyncResourceLoader.Result<PhotosContainerTotalIsString> data) {
        List<Photo> photoList =data.data.getPhotos().getPhotoList();
        if(!photoList.isEmpty()){
            String user_id = photoList.get(0).getOwner();
        }
        mAdapter.setData(data.data.getPhotos().getPhotoList(), true);
    }

    @Override
    public void onLoaderReset(Loader<AsyncResourceLoader.Result<PhotosContainerTotalIsString>> loader) {
        mAdapter.setData( null, true);
    }
}

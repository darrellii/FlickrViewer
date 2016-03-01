package com.darrellii.flickr.flickrviewer.app.ui;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;

import com.darrellii.flickr.flickrviewer.app.loaders.AsyncResourceLoader;
import com.darrellii.flickr.flickrviewer.app.loaders.AsyncResourceLoader.Result;
import com.darrellii.flickr.flickrviewer.network.models.FlickrResponse;
import com.google.api.client.util.Preconditions;

/**
 * Created by dj on 2/26/16.
 * For Use By Flickr Viewer
 */
public class PhotosLoadable<T extends FlickrResponse> implements Loadable<T> {

    private final LoaderManager mLoaderManager;
    private final int mLoaderId;
    private final LoaderManager.LoaderCallbacks<AsyncResourceLoader.Result<T>> mCallbacks;

    private boolean mIsLoadingMore;
    private boolean mHasError;
    private boolean mHasMore;

    public PhotosLoadable(LoaderManager loaderManager, int loaderId,
                          LoaderManager.LoaderCallbacks<AsyncResourceLoader.Result<T>> callbacks) {
        super();
        this.mLoaderManager = Preconditions.checkNotNull(loaderManager);
        this.mLoaderId = loaderId;
        this.mCallbacks = callbacks;
    }



    @Override
    public void init() {
        mLoaderManager.initLoader(mLoaderId, Bundle.EMPTY, this);
    }

    @Override
    public boolean isReadyToLoadMore() {
        return mHasMore && !mHasError && !mIsLoadingMore;
    }

    @Override
    public void loadMore() {
        mIsLoadingMore = true;
        mLoaderManager.restartLoader(mLoaderId, null, this);
    }

    @Override
    public void destroy() {
        mLoaderManager.destroyLoader(mLoaderId);
    }

    @Override
    public Loader<Result<T>> onCreateLoader(int id, Bundle args) {
        return mCallbacks.onCreateLoader(id, args);
    }

    @Override
    public void onLoadFinished(Loader<Result<T>> loader, Result<T> result) {
        mHasError = result == null || !result.success;
        mHasMore = false;
        mIsLoadingMore = false;
        mCallbacks.onLoadFinished(loader, result);
    }

    @Override
    public void onLoaderReset(Loader<Result<T>> loader) {
        mHasError = false;
        mHasMore = false;
        mIsLoadingMore = false;
        mCallbacks.onLoaderReset(loader);
    }

}


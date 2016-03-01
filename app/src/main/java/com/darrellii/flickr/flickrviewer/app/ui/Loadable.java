package com.darrellii.flickr.flickrviewer.app.ui;

import android.support.v4.app.LoaderManager;

import com.darrellii.flickr.flickrviewer.app.loaders.AsyncResourceLoader;

/**
 * Created by dj on 2/26/16.
 * For Use By Flickr Viewer
 */
public interface Loadable<T> extends LoaderManager.LoaderCallbacks<AsyncResourceLoader.Result<T>> {

    void init();

    void destroy();

    boolean isReadyToLoadMore();

    void loadMore();
}

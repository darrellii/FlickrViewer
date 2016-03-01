package com.darrellii.flickr.flickrviewer.app.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.util.SparseBooleanArray;
import android.widget.Adapter;
import android.widget.AdapterView;

import com.darrellii.flickr.flickrviewer.app.loaders.AsyncResourceLoader.Result;
import com.google.api.client.util.Preconditions;


/**
 * Created by dj on 2/26/16.
 * For Use By Flickr Viewer
 *
 * Referenced wuman example
 */
public class LoadableDecorator<T> implements LoaderManager.LoaderCallbacks<Result<T>> {


    private final LoaderManager.LoaderCallbacks<Result<T>> mCallbacks;
    private final int mLoaderId;
    private final ListFragment mListFragment;
    private final AdapterView<?> mAdapterView;
    private final SparseBooleanArray mActive = new SparseBooleanArray();

    public LoadableDecorator(LoaderManager.LoaderCallbacks<Result<T>> callbacks, int loaderId,
                             ListFragment listFragment) {
        super();
        this.mCallbacks = Preconditions.checkNotNull(callbacks);
        this.mLoaderId = loaderId;
        this.mListFragment = Preconditions.checkNotNull(listFragment);
        this.mAdapterView = Preconditions.checkNotNull(listFragment.getListView());

        mListFragment.setListShown(false);
    }

    @Override
    public Loader<Result<T>> onCreateLoader(int id, Bundle args) {
        mActive.put(id, true);
        if (mLoaderId == id) {
            mListFragment.setListShown(!isEmpty());
        }
        updateWindowIndeterminateProgress();
        return mCallbacks.onCreateLoader(id, args);
    }

    @Override
    public void onLoadFinished(Loader<Result<T>> loader, Result<T> result) {
        mCallbacks.onLoadFinished(loader, result);
        mActive.delete(loader.getId());
        if (mLoaderId == loader.getId()) {
            if (mListFragment.isResumed()) {
                mListFragment.setListShown(true);
            } else {
                mListFragment.setListShownNoAnimation(true);
            }
            if (!result.success) {
                Snackbar.make(mAdapterView, "Error Loading", Snackbar.LENGTH_SHORT).show();
            }
            mListFragment.setEmptyText("Empty");
        }
        updateWindowIndeterminateProgress();
    }

    @Override
    public void onLoaderReset(Loader<Result<T>> loader) {
        mCallbacks.onLoaderReset(loader);
        mActive.delete(loader.getId());
        if (mLoaderId == loader.getId()) {
            if (mListFragment.isVisible()) {
                mListFragment.setListShown(true);
            }
        }
        updateWindowIndeterminateProgress();
    }

    private void updateWindowIndeterminateProgress() {
        if (mListFragment.isVisible()) {
            mListFragment.getActivity().setProgressBarIndeterminateVisibility(mActive.size() != 0);
        }
    }

    private boolean isEmpty() {
        Adapter adapter = mAdapterView.getAdapter();
        return adapter == null || adapter.isEmpty();
    }

}

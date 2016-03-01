package com.darrellii.flickr.flickrviewer.app.fragment;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.widget.AbsListView;

import com.darrellii.flickr.flickrviewer.R;
import com.darrellii.flickr.flickrviewer.app.MainActivity;
import com.darrellii.flickr.flickrviewer.app.loader_callbacks.FavoritesCallbacks;
import com.darrellii.flickr.flickrviewer.app.loader_callbacks.InterestingnessCallbacks;
import com.darrellii.flickr.flickrviewer.app.loader_callbacks.MyPhotosCallbacks;
import com.darrellii.flickr.flickrviewer.app.ui.Loadable;
import com.darrellii.flickr.flickrviewer.app.ui.LoadableDecorator;
import com.darrellii.flickr.flickrviewer.app.ui.PhotosAdapter;
import com.darrellii.flickr.flickrviewer.app.ui.PhotosLoadable;
import com.darrellii.flickr.flickrviewer.network.models.PhotosContainer;
import com.darrellii.flickr.flickrviewer.network.models.PhotosContainerTotalIsString;

/**
 * Created by dj on 2/27/16.
 * For Use By Flickr Viewer
 */
public class FlickrListFragment extends ListFragment {

    public interface OnImageClickedListener {
        void onImageClicked(String imageUrl);
    }


    PhotosAdapter mAdapter;
    Loadable<PhotosContainer> mLoadableInterestingness;
    Loadable<PhotosContainerTotalIsString> mLoadableMyPhotos;
    Loadable<PhotosContainerTotalIsString> mLoadableFavorites;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        mAdapter = new PhotosAdapter(getActivity());

        switch (getArguments().getInt(getString(R.string.menu_item))){
            case 0:
                mLoadableInterestingness = new PhotosLoadable<>(getLoaderManager(), 0,
                        new LoadableDecorator<>(new InterestingnessCallbacks(getActivity(), mAdapter), 0, this));
                mLoadableInterestingness.init();
                break;
            case 1:
                mLoadableMyPhotos = new PhotosLoadable<>(getLoaderManager(), 0, new MyPhotosCallbacks(getActivity(), mAdapter));
                mLoadableMyPhotos.init();
                break;
            case 2:
                mLoadableFavorites = new PhotosLoadable<>(getLoaderManager(), 0, new FavoritesCallbacks(getActivity(), mAdapter));
                mLoadableFavorites.init();
                break;

        }

        setListAdapter(mAdapter);
        getListView().setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount >= totalItemCount) {
                    if (mLoadableInterestingness != null && mLoadableInterestingness.isReadyToLoadMore()) {
                        mLoadableInterestingness.loadMore();
                    } else if (mLoadableMyPhotos != null && mLoadableMyPhotos.isReadyToLoadMore()) {
                        mLoadableMyPhotos.loadMore();
                    } else if (mLoadableFavorites != null && mLoadableFavorites.isReadyToLoadMore()) {
                        mLoadableFavorites.loadMore();
                    }
                }
            }
        });

    }

    @Override
    public void onDestroy() {
        if (mLoadableMyPhotos != null) mLoadableMyPhotos.destroy();
        if (mLoadableInterestingness != null) mLoadableInterestingness.destroy();
        if (mLoadableFavorites != null) mLoadableFavorites.destroy();
        super.onDestroy();
    }

}

package com.darrellii.flickr.flickrviewer.app.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;

import com.darrellii.flickr.flickrviewer.R;
import com.darrellii.flickr.flickrviewer.app.FlickrUtil;
import com.darrellii.flickr.flickrviewer.app.fragment.FlickrListFragment;
import com.darrellii.flickr.flickrviewer.network.models.PhotosContainerTotalIsString;
import com.darrellii.flickr.flickrviewer.network.models.PhotosContainer;
import com.darrellii.flickr.flickrviewer.network.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by dj on 2/27/16.
 * For Use By Flickr Viewer
 */
public class PhotosAdapter extends ArrayAdapter<Photo> {

    private final LayoutInflater mInflater;

    public PhotosAdapter(Context context) {
        super(context, R.layout.simple_list_item_image);
        mInflater = LayoutInflater.from(context);
    }

    public void setData(List<Photo> photos, boolean clear) {
        if (clear) {
            clear();
        }
        if (photos != null) {
            addAll(photos);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView == null) {
            view = mInflater
                    .inflate(R.layout.simple_list_item_image, parent, false);
            ViewHolder holder = new ViewHolder(
                    (ImageView) view.findViewById(android.R.id.icon1),
                    (ImageView) view.findViewById(android.R.id.icon2));
            view.setTag(holder);
        } else {
            view = convertView;
        }

        ViewHolder holder = (ViewHolder) view.getTag();

        Photo photo = getItem(position);
        final String imageUrl = FlickrUtil.generatePhotoUrl(photo.getFarm(),
                photo.getServer(),
                photo.getId(), photo.getSecret());
        Picasso.with(getContext()).load(imageUrl).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FlickrListFragment.OnImageClickedListener) getContext()).onImageClicked(imageUrl);
            }
        });

        //check for user icon
        Integer c1 = photo.getIconFarm();
        String c2 = photo.getIconServer();
        String c3 = photo.getOwner();
        if (c1 != null && c2 != null && c3 != null) {
            String avatarUrl = FlickrUtil.generateBuddyIcon(c1, c2, c3);
            Picasso.with(getContext()).load(avatarUrl).into(holder.avatarView);
        }

        return view;
    }

    private static final class ViewHolder {

        ViewHolder(ImageView imageView, ImageView avatarView) {
            super();
            this.imageView = imageView;
            this.avatarView = avatarView;
        }

        ImageView imageView;
        ImageView avatarView;
    }

}

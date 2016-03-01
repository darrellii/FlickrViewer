package com.darrellii.flickr.flickrviewer.app;

import android.text.TextUtils;

/**
 * Created by dj on 2/26/16.
 * For Use By Flickr Viewer
 */
public class FlickrUtil {
    private FlickrUtil() {}
    public static final String CONSUMER_KEY = "300441f0fd745bf359f5462f13e7c270";
    public static final String CONSUMER_SECRET = "20d4d6a8ad2cad94";
    public static final String TEMPORARY_TOKEN_REQUEST_URL = "http://m.flickr.com/services/oauth/request_token";
    public static final String AUTHORIZATION_VERIFIER_SERVER_URL = "http://m.flickr.com/services/oauth/authorize";
    public static final String TOKEN_SERVER_URL = "http://m.flickr.com/services/oauth/access_token";
    public static final String REDIRECT_URL = "http://localhost/Callback";

    public static String generatePhotoUrl(int farm, String server, String photoId,
                                           String secret) {
        return String.format("http://farm%s.staticflickr.com/%s/%s_%s_%s.jpg", farm, server,
                photoId, secret, "n");
    }

    public static final String generateBuddyIcon(int iconFarm, String iconServer, String nsid) {
        if (TextUtils.isEmpty(nsid) || TextUtils.isEmpty(iconServer)
                || TextUtils.equals(iconServer, "0")) {
            return "http://www.flickr.com/images/buddyicon.gif";
        } else {
            return String.format("http://farm%s.staticflickr.com/%s/buddyicons/%s.jpg", iconFarm,
                    iconServer, nsid);
        }
    }



}

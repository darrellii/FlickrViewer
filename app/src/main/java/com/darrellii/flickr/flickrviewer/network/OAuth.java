package com.darrellii.flickr.flickrviewer.network;

import android.content.Context;
import android.os.Handler;
import android.support.v4.app.FragmentManager;

import com.darrellii.flickr.flickrviewer.app.FlickrUtil;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.Lists;
import com.google.api.client.util.Preconditions;
import com.wuman.android.auth.AuthorizationDialogController;
import com.wuman.android.auth.AuthorizationFlow;
import com.wuman.android.auth.DialogFragmentController;
import com.wuman.android.auth.OAuthManager;
import com.wuman.android.auth.oauth2.store.SharedPreferencesCredentialStore;

import java.io.IOException;

/**
 * Created by dj on 2/26/16.
 * For Use By Flickr Viewer
 * <p/>
 * Referenced wuman example
 */
public class OAuth {

    public static final JsonFactory JSON_FACTORY = new JacksonFactory();
    public static final HttpTransport HTTP_TRANSPORT = AndroidHttp.newCompatibleTransport();

    private final OAuthManager manager;

    public static OAuth newInstance(Context context,
                                    FragmentManager fragmentManager) {

        ClientParametersAuthentication client = new ClientParametersAuthentication(FlickrUtil.CONSUMER_KEY,
                FlickrUtil.CONSUMER_SECRET);

        // setup credential store
        SharedPreferencesCredentialStore credentialStore =
                new SharedPreferencesCredentialStore(context,
                        "oauth", JSON_FACTORY);
        // setup authorization flow
        AuthorizationFlow.Builder flowBuilder = new AuthorizationFlow.Builder(
                BearerToken.authorizationHeaderAccessMethod(),
                HTTP_TRANSPORT,
                JSON_FACTORY,
                new GenericUrl(FlickrUtil.TOKEN_SERVER_URL),
                client,
                client.getClientId(),
                FlickrUtil.AUTHORIZATION_VERIFIER_SERVER_URL)
                .setScopes(Lists.<String>newArrayList())
                .setCredentialStore(credentialStore);

        // set temporary token request url for 1.0a
        flowBuilder.setTemporaryTokenRequestUrl(FlickrUtil.TEMPORARY_TOKEN_REQUEST_URL);
        AuthorizationFlow flow = flowBuilder.build();

        // setup authorization UI controller
        AuthorizationDialogController controller =
                new DialogFragmentController(fragmentManager) {

                    @Override
                    public String getRedirectUri() throws IOException {
                        return FlickrUtil.REDIRECT_URL;
                    }

                    @Override
                    public boolean isJavascriptEnabledForWebView() {
                        return true;
                    }

                };
        return new OAuth(flow, controller);
    }



    //<editor-fold defaultstate="collapsed" desc="OAuth Manager Methods">
    private OAuth(AuthorizationFlow flow, AuthorizationDialogController controller) {
        Preconditions.checkNotNull(flow);
        Preconditions.checkNotNull(controller);
        this.manager = new OAuthManager(flow, controller);
    }

    public OAuthManager.OAuthFuture<Boolean> deleteCredential(String userId) {
        return deleteCredential(userId, null);
    }

    public OAuthManager.OAuthFuture<Boolean> deleteCredential(String userId, OAuthManager.OAuthCallback<Boolean> callback) {
        return deleteCredential(userId, callback, null);
    }

    public OAuthManager.OAuthFuture<Boolean> deleteCredential(String userId, OAuthManager.OAuthCallback<Boolean> callback,
                                                              Handler handler) {
        return manager.deleteCredential(userId, callback, handler);
    }

    public OAuthManager.OAuthFuture<Credential> authorize10a(String userId) {
        return authorize10a(userId, null);
    }

    public OAuthManager.OAuthFuture<Credential> authorize10a(String userId, OAuthManager.OAuthCallback<Credential> callback) {
        return authorize10a(userId, callback, null);
    }

    public OAuthManager.OAuthFuture<Credential> authorize10a(String userId,
                                                             OAuthManager.OAuthCallback<Credential> callback, Handler handler) {
        return manager.authorize10a(userId, callback, handler);
    }

    public OAuthManager.OAuthFuture<Credential> authorizeImplicitly(String userId) {
        return authorizeImplicitly(userId, null);
    }

    public OAuthManager.OAuthFuture<Credential> authorizeImplicitly(String userId,
                                                                    OAuthManager.OAuthCallback<Credential> callback) {
        return authorizeImplicitly(userId, callback, null);
    }

    public OAuthManager.OAuthFuture<Credential> authorizeImplicitly(String userId,
                                                                    OAuthManager.OAuthCallback<Credential> callback, Handler handler) {
        return manager.authorizeImplicitly(userId, callback, handler);
    }

    public OAuthManager.OAuthFuture<Credential> authorizeExplicitly(String userId) {
        return authorizeExplicitly(userId, null);
    }

    public OAuthManager.OAuthFuture<Credential> authorizeExplicitly(String userId,
                                                                    OAuthManager.OAuthCallback<Credential> callback) {
        return authorizeExplicitly(userId, callback, null);
    }

    public OAuthManager.OAuthFuture<Credential> authorizeExplicitly(String userId,
                                                                    OAuthManager.OAuthCallback<Credential> callback, Handler handler) {
        return manager.authorizeExplicitly(userId, callback, handler);
    }
    //</editor-fold>

}

package com.example.interactor;

import com.example.data.SyncData;
import com.example.presenter.listener.RequestLoginInterface;

/**
 * Author: Oscar Alvarez
 * Date: 04/11/2015.
 */
public class MapsViewInteractor {

    SyncData syncData;
    public MapsViewInteractor(SyncData syncData) {
        this.syncData = syncData;
    }

    public void doLogin(String user, String pass, RequestLoginInterface listener) {
        if (syncData.doLogin(user, pass)) {
            listener.onSuccess("data");
        } else {
            listener.onError();
        }
    }
}

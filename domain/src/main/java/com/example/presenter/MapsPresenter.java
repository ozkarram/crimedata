package com.example.presenter;

import com.example.interactor.MapsViewInteractor;
import com.example.presenter.listener.RequestLoginInterface;
import com.example.presenter.viewinterfaces.MapsViewInterface;

/**
 * Author: Oscar Alvarez
 * Date: 04/11/2015.
 */
public class MapsPresenter extends BasePresenter<MapsViewInterface> {

    private MapsViewInteractor interactor;
    public MapsPresenter(MapsViewInterface viewInterface, MapsViewInteractor interactor) {
        super(viewInterface);
        this.interactor = interactor;
    }

    public void checkValidUser(String user, String pass) {
        interactor.doLogin(user, pass, new RequestLoginInterface() {
            @Override
            public void onSuccess(String retrievedData) {
                getViewInterface().showData(retrievedData);
            }

            @Override
            public void onError() {
                getViewInterface().showError();
            }
        });
    }

}

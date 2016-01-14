package com.example.presenter;

import com.example.presenter.viewinterfaces.BaseViewInterface;

/**
 * Author: Oscar Alvarez
 * Date: 04/11/2015.
 */
public class BasePresenter<V extends BaseViewInterface> {
    protected V view;
    public BasePresenter(V view) {
        this.view = view;
    }

    public V getViewInterface() {
        return view;
    }
}

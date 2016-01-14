package com.example.presenter.viewinterfaces;

import com.example.presenter.BasePresenter;

/**
 * Author: Oscar Alvarez
 * Date: 04/11/2015.
 */
public interface BaseViewInterface<P extends BasePresenter> {
    P getPresenter();
}

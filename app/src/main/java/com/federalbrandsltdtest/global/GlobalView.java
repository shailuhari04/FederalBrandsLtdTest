package com.federalbrandsltdtest.global;

import android.content.Context;

public interface GlobalView {

    void initView();

    void attachBaseContextCustom(Context newBase);

    void showProgress(int sweetAlertType, String title, boolean cancelable);

    void hideProgress();

    void showMessage(String message);
}

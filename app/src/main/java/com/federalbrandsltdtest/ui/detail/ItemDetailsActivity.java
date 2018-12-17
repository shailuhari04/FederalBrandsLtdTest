package com.federalbrandsltdtest.ui.detail;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.federalbrandsltdtest.utils.Util;
import com.federalbrandsltdtest.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ItemDetailsActivity extends AppCompatActivity implements ItemDetailsActivityMVP.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
    }

    @Override
    public void initView() {

    }


    @Override
    public void attachBaseContextCustom(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    public void showProgress(int sweetAlertType, String title, boolean cancelable) {
        Util.showProgressAlert(this, sweetAlertType, title, cancelable);
    }

    @Override
    public void hideProgress() {
        Util.hideProgressAlert();
    }

    @Override
    public void showMessage( String message) {
        Util.customToast(this, message);
    }


}

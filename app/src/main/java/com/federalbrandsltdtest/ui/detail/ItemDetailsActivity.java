package com.federalbrandsltdtest.ui.detail;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.federalbrandsltdtest.utils.Constants;
import com.federalbrandsltdtest.utils.Util;
import com.federalbrandsltdtest.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import java.util.Objects;

public class ItemDetailsActivity extends AppCompatActivity implements ItemDetailsActivityMVP.View {

    private String title, url;
    ImageView imageView;
    TextView tv_title;
    TextView tv_url;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        //view initialization
        initView();

        //getIntent Data
        getIntentData();

        //Data Bind with view
        dataBindWithView();

    }

    private void dataBindWithView() {
        Glide.with(this)
                .load(url)
                .into(imageView);

        tv_title.setText(title);
        tv_url.setText(url);
    }

    private void getIntentData() {
        if (getIntent().getExtras() != null) {
            title = getIntent().getStringExtra(Constants.TITLE);
            url = getIntent().getStringExtra(Constants.URL);
        }
    }

    @Override
    public void initView() {
        imageView = (ImageView) findViewById(R.id.imageView);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_url = (TextView) findViewById(R.id.tv_url);

        //set the toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
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
    public void showMessage(String message) {
        Util.customToast(this, message);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.details_toolbar_menue, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItem = item.getItemId();

        switch (menuItem) {
            case android.R.id.home:
                super.onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

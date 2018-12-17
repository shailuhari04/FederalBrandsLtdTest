package com.federalbrandsltdtest.ui.main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.federalbrandsltdtest.R;
import com.federalbrandsltdtest.adapters.ItemListAdapter;
import com.federalbrandsltdtest.pojos.PhotosRealmPojo;
import com.federalbrandsltdtest.ui.main.core.ItemListPresenter;
import com.federalbrandsltdtest.utils.Util;
import io.realm.Realm;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import java.util.List;

public class ItemListActivity extends AppCompatActivity implements ItemListActivityMVP.View {


    private ItemListPresenter itemListPresenter;
    private Realm realm;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        //View Initialization
        initView();

        if (!checkLocalDataAvailable()) {
            if (Util.isInternet(this)) {
                itemListPresenter.getPhotosApiCall();
            }
        }
    }

    @Override
    public void initView() {
        //recyclerView instance initialize
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        //realm instance initialize
        realm = Realm.getDefaultInstance();

        //presenter instance initialize
        itemListPresenter = new ItemListPresenter(this, realm);
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

    @Override
    public boolean checkLocalDataAvailable() {
        if (realm.where(PhotosRealmPojo.class).count() != 0) {
            return true;
        }
        return false;
    }

    @Override
    public void navigateDataToRecyclerView(List<PhotosRealmPojo> photosRealmPojoList) {
        ItemListAdapter adapter = new ItemListAdapter(this, photosRealmPojoList);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (realm != null) {
            if (realm.isInTransaction()) {
                realm.cancelTransaction();
            }
            realm.close();
            realm = null;
        }
    }
}

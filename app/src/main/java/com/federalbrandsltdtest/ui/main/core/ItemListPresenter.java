package com.federalbrandsltdtest.ui.main.core;

import android.util.Log;
import cn.pedant.SweetAlert.SweetAlertDialog;
import com.federalbrandsltdtest.helper.RealmHelper;
import com.federalbrandsltdtest.pojos.GetPhotosResponsePojo;
import com.federalbrandsltdtest.pojos.PhotosRealmPojo;
import com.federalbrandsltdtest.ui.main.ItemListActivityMVP;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;

import java.util.ArrayList;
import java.util.List;

public class ItemListPresenter implements ItemListActivityMVP.Presenter {

    ItemListModel itemListModel;
    ItemListActivityMVP.View itemListView;
    private Realm realm;
    private static final String TAG = ItemListPresenter.class.getSimpleName();

    public ItemListPresenter(ItemListActivityMVP.View itemListView, Realm realm) {
        this.itemListView = itemListView;
        this.realm = realm;
    }

    @Override
    public void onCreate() {
        //Initialize The Model
        itemListModel = new ItemListModel(this);
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }


    /**
     * get Observable of Photos from loginModel and subscribe it
     */
    @Override
    public void getPhotosApiCall() {
        //init model
        onCreate();

     /*   itemListModel.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getPhotosSubscriber());*/

        itemListModel.getPhotosData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GetPhotosResponsePojo>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        //show loader
                        itemListView.showProgress(SweetAlertDialog.PROGRESS_TYPE, "Loading....", false);
                    }

                    @Override
                    public void onNext(GetPhotosResponsePojo getPhotosResponsePojo) {
                        Log.e("Data", getPhotosResponsePojo.toString());
                        // itemListView.navigateDataToRecyclerView(saveDataInLocal(getPhotosResponsePojo));
                    }

                    @Override
                    public void onError(Throwable e) {
                        itemListView.hideProgress();
                        itemListView.showProgress(SweetAlertDialog.ERROR_TYPE, "Error...", true);
                    }

                    @Override
                    public void onComplete() {
                        itemListView.hideProgress();
                    }
                });


    }

    private Observer<? super GetPhotosResponsePojo> getPhotosDataSubscriber() {
        return new Observer<GetPhotosResponsePojo>() {
            @Override
            public void onSubscribe(Disposable d) {
                //show loader
                itemListView.showProgress(SweetAlertDialog.PROGRESS_TYPE, "Loading....", false);
            }

            @Override
            public void onNext(GetPhotosResponsePojo getPhotosResponsePojo) {
                Log.e("Data", getPhotosResponsePojo.toString());
               // itemListView.navigateDataToRecyclerView(saveDataInLocal(getPhotosResponsePojo));
            }

            @Override
            public void onError(Throwable e) {
                itemListView.hideProgress();
                itemListView.showProgress(SweetAlertDialog.ERROR_TYPE, "Error...", true);
            }

            @Override
            public void onComplete() {
                itemListView.hideProgress();
            }
        };
    }


    /**
     * Subscriber of Observable from getPhotosApiCall and do reflect in view
     */
  /*  private Observer<? super List<GetPhotosResponsePojo>> getPhotosSubscriber() {
        return new Observer<List<GetPhotosResponsePojo>>() {
            @Override
            public void onSubscribe(Disposable d) {
                //show loader
                itemListView.showProgress(SweetAlertDialog.PROGRESS_TYPE, "Loading....", false);
            }

            @Override
            public void onNext(List<GetPhotosResponsePojo> getPhotosResponsePojos) {

                itemListView.navigateDataToRecyclerView(saveDataInLocal(getPhotosResponsePojos));
            }

            @Override
            public void onError(Throwable e) {
                itemListView.hideProgress();
                itemListView.showProgress(SweetAlertDialog.ERROR_TYPE, "Error...", true);
            }

            @Override
            public void onComplete() {
                itemListView.hideProgress();
            }
        };
    }*/


    /**
     * Response Data Save In Realm Database
     */
    @Override
    public List<PhotosRealmPojo> saveDataInLocal(List<GetPhotosResponsePojo> getPhotosResponsePojo) {

        PhotosRealmPojo pojo = new PhotosRealmPojo();
        ArrayList<PhotosRealmPojo> list = new ArrayList<>();

        for (GetPhotosResponsePojo data : getPhotosResponsePojo) {

            pojo.setId(data.getId());
            pojo.setThumbnailUrl(data.getThumbnailUrl());
            pojo.setTitle(data.getTitle());
            pojo.setUrl(data.getUrl());

            RealmHelper helper = new RealmHelper(realm);

            if (helper.savePhoto(pojo)) {
                Log.d(TAG, "Saved PhotoPojo -> " + pojo.toString());
            } else {
                Log.d(TAG, "Failed To Save PhotoPojo -> " + pojo.toString());
            }

            list.add(pojo);
        }

        return list;
    }
}

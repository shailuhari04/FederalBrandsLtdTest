package com.federalbrandsltdtest.ui.main.core;

import android.os.AsyncTask;
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
import io.realm.RealmResults;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class ItemListPresenter implements ItemListActivityMVP.Presenter {

    private ItemListModel itemListModel;
    private ItemListActivityMVP.View itemListView;
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

        itemListModel.getPhotos()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getPhotosSubscriber());
    }


    /**
     * Subscriber of Observable from getPhotosApiCall and do reflect in view
     */
    private Observer<? super List<GetPhotosResponsePojo>> getPhotosSubscriber() {
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
    }


    /**
     * Response Data Save In Realm Database
     */
    @Override
    public List<PhotosRealmPojo> saveDataInLocal(List<GetPhotosResponsePojo> getPhotosResponsePojo) {

        ArrayList<PhotosRealmPojo> list = new ArrayList<>();

        for (GetPhotosResponsePojo data : getPhotosResponsePojo) {
            PhotosRealmPojo pojo = new PhotosRealmPojo();
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
            //Background Task
           // new GetByteImageAsync(data, realm).execute();

            list.add(pojo);
        }

        return list;
    }



    private static class GetByteImageAsync extends AsyncTask<String, Void, String> {

        private GetPhotosResponsePojo data;
        private Realm realm;

        public GetByteImageAsync(GetPhotosResponsePojo data, Realm realm) {
            this.data = data;
            this.realm = realm;
        }

        @Override
        protected String doInBackground(String... String) {
            byte[] thumbnil_url_byte = new byte[0];
            byte[] url_byte = new byte[0];
            try {
                int count = 0;
                URL url_thumbnil = new URL(data.getThumbnailUrl());
                URLConnection conection = url_thumbnil.openConnection();
                conection.setRequestProperty("Connection", "Keep-Alive");
                int lenghtOfFile = conection.getContentLength();
                byte[] dataxxxx = new byte[lenghtOfFile];
                InputStream input = null;
                input = new BufferedInputStream(url_thumbnil.openStream(), 8192);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                while ((count = input.read(dataxxxx)) != -1) {
                    bos.write(dataxxxx, 0, count);
                }

                bos.flush();
                bos.close();
                input.close();
                thumbnil_url_byte = bos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }


            try {
                int count = 0;
                URL url = new URL(data.getUrl());
                URLConnection conection = url.openConnection();
                conection.setRequestProperty("Connection", "Keep-Alive");
                int lenghtOfFile = conection.getContentLength();
                byte[] dataxxxx = new byte[lenghtOfFile];
                InputStream input = null;
                input = new BufferedInputStream(url.openStream(), 8192);

                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                while ((count = input.read(dataxxxx)) != -1) {
                    bos.write(dataxxxx, 0, count);
                }

                bos.flush();
                bos.close();
                input.close();
                url_byte = bos.toByteArray();
            } catch (IOException e) {
                e.printStackTrace();
            }

            PhotosRealmPojo pojo = new PhotosRealmPojo();
            pojo.setId(data.getId());
            pojo.setThumbnailUrl(data.getThumbnailUrl());
            pojo.setTitle(data.getTitle());
            pojo.setUrl(data.getUrl());

            pojo.setByte_url(url_byte);
            pojo.setByte_thumbnil_url(thumbnil_url_byte);

            RealmHelper helper = new RealmHelper(realm);

            if (helper.savePhoto(pojo)) {
                Log.d(TAG, "Saved PhotoPojo -> " + pojo.toString());
            } else {
                Log.d(TAG, "Failed To Save PhotoPojo -> " + pojo.toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String result) {

        }
    }


    /**
     * Retrieve Data From Realm Database
     */
    @Override
    public RealmResults<PhotosRealmPojo> getLocalData() {

        if (realm != null) {
            return realm.where(PhotosRealmPojo.class).findAll();
        }

        return null;
    }
}

package com.federalbrandsltdtest.ui.main.core;

import com.federalbrandsltdtest.pojos.GetPhotosResponsePojo;
import com.federalbrandsltdtest.remote.RetrofitClient;
import com.federalbrandsltdtest.ui.main.ItemListActivityMVP;
import io.reactivex.Observable;

import java.util.List;

public class ItemListModel implements ItemListActivityMVP.Model {

    private ItemListPresenter itemListPresenter;

    public ItemListModel(ItemListPresenter itemListPresenter) {
        this.itemListPresenter = itemListPresenter;
    }

    @Override
    public Observable<List<GetPhotosResponsePojo>> getPhotos() {

        return RetrofitClient.getAPIService().getPhotos();
    }

    @Override
    public Observable<GetPhotosResponsePojo> getPhotosData() {
        return RetrofitClient.getAPIService().getPhotosData();
    }


}

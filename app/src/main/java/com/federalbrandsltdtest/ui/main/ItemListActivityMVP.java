package com.federalbrandsltdtest.ui.main;

import com.federalbrandsltdtest.global.GlobalPresenter;
import com.federalbrandsltdtest.global.GlobalView;
import com.federalbrandsltdtest.pojos.GetPhotosResponsePojo;
import com.federalbrandsltdtest.pojos.PhotosRealmPojo;
import io.reactivex.Observable;

import java.util.List;

public class ItemListActivityMVP {

    /**
     * Model interface which accessed by the Presenter
     * where we are defining abstract methods which are provide Observable/DataSet of Database or Api Response
     */
    public interface Model {
        Observable<List<GetPhotosResponsePojo>> getPhotos();

        Observable<GetPhotosResponsePojo> getPhotosData();
    }


    /**
     * View interface which accessed by the Presenter
     * where we are defining abstract methods which are navigating view or (reflect/changes)
     */
    public interface View extends GlobalView {

        boolean checkLocalDataAvailable();

        void navigateDataToRecyclerView(List<PhotosRealmPojo> getPhotosResponsePojo);
    }


    /**
     * Presenter interface who is middle layer of View and Model
     * where we are defining abstract methods for Communication b\w View and Model
     */
    public interface Presenter extends GlobalPresenter {

        void getPhotosApiCall();

        List<PhotosRealmPojo> saveDataInLocal(List<GetPhotosResponsePojo> getPhotosResponsePojo);

    }
}

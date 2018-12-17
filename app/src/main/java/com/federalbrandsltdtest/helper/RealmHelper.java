package com.federalbrandsltdtest.helper;

import android.support.annotation.NonNull;
import com.federalbrandsltdtest.pojos.PhotosRealmPojo;
import io.realm.Realm;
import io.realm.RealmResults;

import java.util.ArrayList;

public class RealmHelper {

    private Realm realm;
    private boolean saved;

    public RealmHelper(Realm realm) {
        this.realm = realm;
    }

    /**
     * Save the photo pojo
     *
     * @param myPojo
     * @return
     */
    public boolean savePhoto(final PhotosRealmPojo myPojo) {

        if (myPojo == null) {
            saved = false;
        } else {
            realm.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(@NonNull Realm realm) {

                    PhotosRealmPojo pojo = realm.copyToRealm(myPojo);

                    saved = true;

                }
            });
        }
        return saved;
    }

    /**
     * Retrieve Data
     *
     * @return
     */
    public ArrayList<PhotosRealmPojo> retrivePhotos() {
        RealmResults<PhotosRealmPojo> photosRealmResult = realm.where(PhotosRealmPojo.class).findAll();

        return new ArrayList<>(photosRealmResult);
    }

}

package com.federalbrandsltdtest.application;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Realm Initilazation
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("mydb.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.getInstance(realmConfiguration);
        //configuration
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}

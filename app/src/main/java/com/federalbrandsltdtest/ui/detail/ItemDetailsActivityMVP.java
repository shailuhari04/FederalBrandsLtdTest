package com.federalbrandsltdtest.ui.detail;

import com.federalbrandsltdtest.global.GlobalPresenter;
import com.federalbrandsltdtest.global.GlobalView;

public class ItemDetailsActivityMVP {

    /**
     * Model interface which accessed by the Presenter
     * where we are defining abstract methods which are provide Observable/DataSet of Database or Api Response
     */
    interface Model {

    }


    /**
     * View interface which accessed by the Presenter
     * where we are defining abstract methods which are navigating view or (reflect/changes)
     */
    interface View extends GlobalView {

    }


    /**
     * Presenter interface who is middle layer of View and Model
     * where we are defining abstract methods for Communication b\w View and Model
     */
    interface Presenter extends GlobalPresenter {

    }
}

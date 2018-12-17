package com.federalbrandsltdtest.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.Toast;
import cn.pedant.SweetAlert.SweetAlertDialog;

public class Util {

    @SuppressLint("StaticFieldLeak")
    private static SweetAlertDialog pDialog;


    /**
     * Show Message  LENGTH_SHORT
     */
    public static Toast customToast(Context ctx, String message) {
        Toast toast = Toast.makeText(ctx, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return toast;
    }

    /**
     * @param ctx
     * @param message Show Message  LENGTH_LONG
     */
    public static Toast customToastLong(Context ctx, String message) {
        Toast toast = Toast.makeText(ctx, message, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
        return toast;
    }


    /**
     * @param ctx
     * @param sweetAlertType
     * @param titleText
     * @param cancelable     Show Progress Bar (Sweet Alert as progress only)
     */
    public static void showProgressAlert(Context ctx, int sweetAlertType, String titleText, Boolean cancelable) {
        pDialog = new SweetAlertDialog(ctx, sweetAlertType);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText(titleText);
        pDialog.setCancelable(cancelable);
        pDialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sDialog) {
                sDialog.dismissWithAnimation();
            }
        });
        pDialog.show();
    }


    /**
     * Hide Progress Bar (Sweet Alert)
     */
    public static void hideProgressAlert() {
        pDialog.hide();
    }


    /**
     * @param mcontext Internet check
     */
    public static Boolean isInternet(Context mcontext) {
        ConnectivityManager conMgr = (ConnectivityManager) mcontext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = null;
        if (conMgr != null) {
            netInfo = conMgr.getActiveNetworkInfo();
        }
        if (netInfo == null) {

            new SweetAlertDialog(mcontext, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("No Network Connection?")
                    .setContentText("Please Connect The Internet And Try Again.")
                    // .setCancelText("No,cancel plx!")
                    .setConfirmText("OK")
                    .showCancelButton(true)
                    .setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sDialog) {
                            sDialog.cancel();
                        }
                    })
                    .show();


        } else {
            return true;
        }

        return false;
    }
}

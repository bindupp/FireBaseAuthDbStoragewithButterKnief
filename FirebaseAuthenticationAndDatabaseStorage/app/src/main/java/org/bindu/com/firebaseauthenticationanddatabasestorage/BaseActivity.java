package org.bindu.com.firebaseauthenticationanddatabasestorage;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by patel on 12-08-2017.
 */

public class BaseActivity extends AppCompatActivity {

    public ProgressDialog pd;


    protected void showProgress(String msg) {
        if (pd != null && pd.isShowing()) {
            dismissProgress();
        } else {
            pd = getProgressDialog(this, msg);
            pd.show();
        }
    }

    public static ProgressDialog getProgressDialog(Context mCon, String Msg) {

        ProgressDialog pd = new ProgressDialog(mCon);

        if(!BuildConfig.DEBUG){
            pd.setCancelable(false);
        }
        pd.setMessage(Msg);

        return pd;

    }

    protected void dismissProgress() {
        if (pd != null) {
            pd.dismiss();
            pd = null;
        }
    }

    protected void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    // this listener will be called when there is change in firebase user session
    @Override
    public void onBackPressed() {
        return;
    }
}

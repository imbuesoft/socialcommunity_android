package com.prakashgujarati.khantrajputsamaj.commans;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.UiModeManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.FileProvider;

import com.google.android.material.snackbar.Snackbar;
import com.prakashgujarati.khantrajputsamaj.R;
import com.prakashgujarati.khantrajputsamaj.utils.Constant;
import com.prakashgujarati.khantrajputsamaj.utils.PreferenceUtils;
import com.prakashgujarati.khantrajputsamaj.widgets.CustomProgressDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Nikul Thaker on 09-02-2021.
 */
public class BaseActivity extends AppCompatActivity {
    Dialog mProgressDialog;
    public static BaseActivity baseActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseActivity = this;

        //progress dialog
        mProgressDialog = new CustomProgressDialog(this);

    }

    /**
     * show loader
     */
    public void showLoader() {
        try {
            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }
        } catch (Exception e) {

        }
    }

    /**
     * Hide Loader
     */
    public void hideLoader() {
        try {
            if (mProgressDialog.isShowing()) {
                mProgressDialog.dismiss();
            }
        } catch (Exception e) {

        }
    }

    /**
     * Show Error
     */
    public void showError(String msg) {
        if (msg == null) return;
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_LONG).show();
    }

    /**
     * Show alert
     */
    public void showAlert(String msg) {
        if (msg == null) return;
        Toast toast = Toast.makeText(getApplicationContext(), "" + msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void showAlertDialog(String message, Context context) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setCancelable(false);
        AlertDialog dialog = builder.setNegativeButton("OK", (dialogInterface, i) -> dialogInterface.dismiss()).create();
        dialog.show();

    }

    public void showNoInternetDialog(Context context) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.no_internet_layout);
        dialog.setCancelable(false);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            dialog.create();
        }
        dialog.show();

        AppCompatButton btnCancel = dialog.findViewById(R.id.btnCancel);
        AppCompatButton btnTurnOn = dialog.findViewById(R.id.btnTurnOn);

        btnCancel.setOnClickListener(v -> finishAffinity());
        btnTurnOn.setOnClickListener(v -> {
            dialog.dismiss();
            startActivityForResult(new Intent(android.provider.Settings.ACTION_SETTINGS), 0);
        });

    }

    public void hideKeyBoard(View view) {
        if (view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Check Email is valid or not
     *
     * @param email - email
     * @return - true if email is valid else false
     */
    public boolean isEmailValid(String email) {
        String regex =
                "[A-Z0-9a-z]+([._%+-]{1}[A-Z0-9a-z]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


}

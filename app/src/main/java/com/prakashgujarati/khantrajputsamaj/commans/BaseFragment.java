package com.prakashgujarati.khantrajputsamaj.commans;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


public class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    /**
     * to show error messages only in Snackbar
     */
    protected void showError(String msg) {
        ((BaseActivity) getActivity()).showError(msg);
    }

    /**
     * Show Loader
     */
    protected void showLoader() {
        try {
            ((BaseActivity) getActivity()).showLoader();
        } catch (Exception e) {

        }
    }

    /**
     * Show alert
     */
    public void showAlert(String msg) {
        ((BaseActivity) getActivity()).showAlert(msg);
    }

    /*
     * no internat dialog
     * */
    public void showNoInternetDialog(Context context) {
        ((BaseActivity) getActivity()).showNoInternetDialog(context);
    }

    /**
     * Hide Loader
     */
    protected void hideLoader() {
        try {
            ((BaseActivity) getActivity()).hideLoader();
        } catch (Exception e) {

        }
    }

    public void hideKeyBoard(View view) {
        ((BaseActivity) getActivity()).hideKeyBoard(view);
    }

}

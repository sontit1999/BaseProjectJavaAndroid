package com.example.basejavaandroid.base;

import android.app.Activity;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.example.basejavaandroid.R;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseActivity<B extends ViewDataBinding,VM extends BaseViewmodel> extends AppCompatActivity {
    protected B binding;
    protected VM viewmodel;
    public ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewmodel = ViewModelProviders.of(this).get(getViewModel());
        binding = DataBindingUtil.setContentView(this,getLayoutId());
        setBindingViewmodel();
        getData();
        initEvent();
    }

    protected abstract void getData();

    protected abstract void initEvent();

    protected abstract void setBindingViewmodel();

    protected abstract Class<VM> getViewModel();

    protected abstract int getLayoutId();
    public void gotoActivity(Class<?> cls,String key, Bundle bundle){
        Intent intent = new Intent(this,cls);
        intent.putExtra(key,bundle);
        startActivity(intent);
    }
    public void gotoActivityNoData(Class<?> cls){
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }
    //Kiểm tra trạng thái internet
    public Boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfor = connectivityManager.getActiveNetworkInfo();
        boolean isNetworkState = activeNetInfor != null && activeNetInfor.isConnected();
        return isNetworkState;
    }
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void showSnackbar(int Id,String message){
        Snackbar.make(findViewById(Id), message,Snackbar.LENGTH_LONG).show();
    }
    public boolean checkHasPermission(Context context,String permission){
        if(ActivityCompat.checkSelfPermission(context,permission) == PackageManager.PERMISSION_GRANTED){
            return true;
        }else{
            return false;
        }
    }
    public void requestPermissionRuntime(String[] permission,int requestCode){
        ActivityCompat.requestPermissions(this,
                permission,
                requestCode);
    }
    public void loadFragment(Fragment fragment, int IdContainer) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(IdContainer, fragment, null)
                .setReorderingAllowed(true)
                .addToBackStack(null) // name can be null
                .commit();
     }
    public void makeFullScreen() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage("Loading....");
        }

        mProgressDialog.show();
    }
    public void setupTouchUIToDismissKeyboard(View view) {
        setupTouchUIToDismissKeyboard(view, (v, event) -> {
            hideSoftKeyboard(BaseActivity.this);
            return false;
        }, -1);
    }

    public static void setupTouchUIToDismissKeyboard(View view, View.OnTouchListener onTouchListener, final Integer... exceptIDs) {
        List<Integer> ids = new ArrayList<>();
        if (exceptIDs != null)
            ids = Arrays.asList(exceptIDs);

        //Set up touch listener for non-text box views to hide keyboard.
        if(!(view instanceof EditText)) {

            if (!ids.isEmpty() && ids.contains(view.getId()))
            {
                return;
            }

            view.setOnTouchListener(onTouchListener);
        }

        //If a layout container, iterate over children and seed recursion.
        if (view instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {

                View innerView = ((ViewGroup) view).getChildAt(i);

                setupTouchUIToDismissKeyboard(innerView, onTouchListener, exceptIDs);
            }
        }
    }

    /** Hide the Soft Keyboard.*/
    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)  activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE);

        if (inputMethodManager == null)
            return;

        if (activity.getCurrentFocus() != null && activity.getCurrentFocus().getWindowToken() != null)
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}

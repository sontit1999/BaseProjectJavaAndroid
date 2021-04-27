package com.example.basejavaandroid.base;

import android.app.Activity;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Message;
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

public abstract class BaseActivity<B extends ViewDataBinding,VM extends BaseViewmodel> extends AppCompatActivity {
    protected B binding;
    protected VM viewmodel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewmodel = ViewModelProviders.of(this).get(getViewModel());
        binding = DataBindingUtil.setContentView(this,getLayoutId());
        setBindingViewmodel();
        initEvent();
        getData();
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
}

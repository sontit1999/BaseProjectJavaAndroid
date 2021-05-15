 package com.example.basejavaandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.basejavaandroid.base.BaseActivity;
import com.example.basejavaandroid.customview.CenterZoomLayoutManager;
import com.example.basejavaandroid.databinding.ActivityMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewmodel> {
    public static final String FILM_KEY = "film";
    private static final String TAG = "sondz";
    public static final String ACTION_BROADCAST ="sondz";
    public static final String KEY_DATA ="keys";
    private boolean isLoading = false;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "On Recieve broadcase !", Toast.LENGTH_SHORT).show();
                switch (intent.getAction()){
                    case ACTION_BROADCAST :
                        Film data = (Film) intent.getSerializableExtra(KEY_DATA);
                        binding.tvResult.setText("Data gửi từ detail Activity : " + data.getName() );

                        break;
                    default:
                        binding.tvResult.setText("Data chưa lấy được " );
                        break;
                }
            }
        };
        IntentFilter filter = new IntentFilter(ACTION_BROADCAST);
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("sonit","Unregister broadcast");
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void getData() {
        viewmodel.getArrFilm().observe(this, new Observer<List<Film>>() {
            @Override
            public void onChanged(List<Film> films) {
                viewmodel.filmAdapter.setList(films);
                viewmodel.filmAdapter.setCallback(new FilmCallback() {
                    @Override
                    public void onClickFilm(Film film) {
                        Intent intents = new Intent(MainActivity.this, DetailActivity.class);
                        intents.putExtra(FILM_KEY,film);
                        startActivity(intents);
                    }

                    @Override
                    public void onLongClick(Film film) {
                        showAlertDialogDeleteFilm(film);
                    }
                });
            }
        });
    }
    public void showAlertDialogDeleteFilm(Film film){

        AlertDialog alertDialog = new AlertDialog.Builder(this)
        //set icon
                .setIcon(android.R.drawable.ic_delete)
        //set title
                .setTitle("Are you sure to Delete " + film.getName())
        //set message
                .setMessage("Delete will delete forever!")
        //set positive button
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // xoa film
                        viewmodel.filmAdapter.removeItem(film);
                    }
                })
        //set negative button
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //set what should happen when negative button is clicked

                      showToast("Nothing Happened");
                    }
                })
                .show();
    }
    @Override
    protected void initEvent() {
           binding.rvFilm.addOnScrollListener(new RecyclerView.OnScrollListener() {
               @Override
               public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                   if(!recyclerView.canScrollVertically(dy)){
                       Toast.makeText(MainActivity.this, "Hết data rùi nha !", Toast.LENGTH_SHORT).show();
                       getMoreData();
                   }
               }
           });
           regTokenNotifi();
    }

    private void regTokenNotifi() {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();


                        Log.d(TAG, token);
                        Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void getMoreData() {
        if(!isLoading){
            isLoading = true;


            // get data from server
            List<Film> arrFilm = new ArrayList<>();
            arrFilm.add(new Film(7,"Bản Năng Hoang Dại - Voyagers","https://i2.wp.com/motphimle.com/wp-content/uploads/2021/04/Ban-Nang-Hoang-Dai-Voyagers-2021-poster.jpg?fit=600%2C887&ssl=1",1,"Phim nói về tương lai không xa khi nhân loại đang trên bờ vực diệt chủng, một nhóm thanh thiếu niên được nuôi dưỡng để phục vụ cho mục đích khai thác trí tuệ và chấp nhận sai khiến. Họ bắt đầu một chuyến hành trình thám hiểm khai phá một hành tinh xa xôi khác."));
            arrFilm.add(new Film(8,"Bàn Tay Diệt Quỷ - Evil Expeller ","https://www.cgv.vn/media/catalog/product/cache/1/image/c5f0a1eff4c394a251036189ccddaacd/b/t/btdq_main_poster.jpg",2,"Phim nói về võ sĩ sau khi bản thân bỗng nhiên sở hữu “Bàn tay diệt quỷ”, nên võ sĩ MMA Yong Hoo (Park Seo Joon thủ vai) đã dấn thân vào hành trình trừ tà, trục quỷ đối đầu với Giám Mục Bóng Tối (Woo Do Hwan) – tên quỷ Satan đột lốt người."));
            arrFilm.add(new Film(9,"Lật Mặt: 48h","https://media-cdn.laodong.vn/Storage/NewsPortal/2021/4/19/900413/8-1.jpg?w=414&h=276&crop=auto&scale=both",4,"Câu chuyện xoay quanh hành trình trốn chạy bọn xã hội đen của gia đình anh Hiền - một võ sư có xuất thân là tay đấm bốc trong quá khứ. Vì cần một số tiền lớn, anh đã vô tình lọt vào cái bẫy của bọn người xấu nhưng may mắn thoát được. "));
            arrFilm.add(new Film(10,"Trùm Cuối Siêu Đẳng - Boss Level","https://cdnmedia.thethaovanhoa.vn/Upload/3uPkfvAxvuOpUQrmKeiDaA/files/2021/04/B/18/Trum-cuoitop.jpg",3.5f,"Phim nói về sự mắc kẹt trong một vòng lặp thời gian ngay đúng ngày anh ta bị giết chết, một cựu đặc vụ Roy Pulver (Frank Gillo) đã phát hiện ra manh mới về một dự án bí mật của chính phủ có thể giải đáp bí ẩn đằng sau cái chết vô thời hạn của anh ta."));

            int oldCount = viewmodel.filmAdapter.getList().size();
            viewmodel.filmAdapter.addMore(arrFilm);
            isLoading = false;
        }
    }

    @Override
    protected void setBindingViewmodel() {
         binding.setViewmodel(viewmodel);
         binding.rvFilm.setLayoutManager(new CenterZoomLayoutManager(this, LinearLayoutManager.HORIZONTAL,false));
    }

    @Override
    protected Class<MainViewmodel> getViewModel() {
        return MainViewmodel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
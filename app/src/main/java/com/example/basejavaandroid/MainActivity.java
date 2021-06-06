package com.example.basejavaandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
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
        if(!isLoading) {
            isLoading = true;


            // get data from server
            List<Film> list = new ArrayList<>();
            list.add(new Film(1, "Bố già", "https://cdn.tgdd.vn/Files/2020/07/01/1266904/top-phim-chieu-rap-hay-nhat-2020-tich-tien-28.jpg", 2, "Bộ phim xoay quanh về gia đình nhỏ của Ba Sang và Quắn, sống trong một khu xóm lao động nghèo. Ba Sang là một người hay lo chuyện bao đồng, luôn yêu thương con trai nhưng khoảng cách giữa những thế hệ đã khiến cho cả hay xảy ra nhiều mâu thuẫn", "09:00:00 08/5/2021"));
            list.add(new Film(2, "Godzilla Đại Chiến Kong", "https://www.fullphim.net/static/5fe2d564b3fa6403ffa11d1c/6061eb3d14fcbf485386b98a_godzilla-kong-2.jpg", 3, "Hai kẻ thù truyền kiếp Kong và Godzilla sẽ chạm trán trong một trận chiến thế kỉ. Kong và các đồng đội cùng với Jia là một cô gái trẻ mồ côi, sẽ dấn thân vào cuộc thám hiểm đầy hiểm nguy mong tìm ra được ngôi nhà thật sự của mình.", "09:08:08 09/5/2021"));
            list.add(new Film(3, "Song Song", "https://cdn.tgdd.vn/Files/2020/07/01/1266904/top-phim-chieu-rap-hay-nhat-2020-tich-tien-30.jpg", 1, "Vào một đêm mưa bão, Phong (Thuận Phát) đã vô tình chứng kiến cảnh ông Sơn (Tiến Luật) hàng xóm đang cố gắng di chuyển một xác chết. Vì quá sợ hãi, Phong chạy ra khỏi nhà nhưng đã bị xe tải tông chết ngay tại chỗ trước sự bất lực của ông Sơn. Liệu mọi chuyện sẽ diễn ra như thế nào? Mời bạn theo dõi phim để có câu trả lời.", "04:15:20 010/6/2021"));
            list.add(new Film(4, "Siêu Trộm - Way Down", "https://media.voocdn.com/media/image/id/6065776aacc399e5ba8b45d7", 2.5f, "Nhà thám hiểm đại dương Walter Moeland vừa thành công trong việc trục vớt một xác tàu đắm thuộc vùng biển Tây Ban Nha, đồng thời tìm được 3 đồng xu vàng được cho là dẫn đến kho báu “bất tận” được chôn giấu của huyền thoại Sir Francis Drake.", "20:00:05 10/5/2021"));
            list.add(new Film(5, "Cô Gái Trẻ Hứa Hẹn - Promising Young Woman", "https://lh3.googleusercontent.com/proxy/xvlZV6hnejy2d_eLrT1m7tVMErfANW4rcfuXDdzRTcvOTNo9oOyNQ0n4aqxe1Gj0QuByhLLnevilkvhrux-evLz-x3IlQADl46uG_u-oE1O1fJTCaTD9A2YAf3MjepOq-CLKrg", 2.5f, "Trước vụ việc người bạn thân nhất Nina Fisher bị tấn công tình dục và rồi tự sát vì không tìm thấy công lý, Cassandra Cassie Thomas đã gặp phải tổn thương tâm lý nặng nề và bỏ học. Giờ đây, Cassie 30 tuổi lang thang giữa các hộp đêm Ohio, đóng giả làm một “con mồi béo bở” đang say khướt để mời gọi và dạy dỗ những chàng trai ngây ngô hư hỏng.", "15:15:05 7/5/2021"));
            list.add(new Film(6, "Kiều", "https://kenh14cdn.com/thumb_w/660/203336854389633024/2021/4/7/1614643553076225740302655401821542371023678n-1617813417946909536602.jpg", 3, "Bộ phim dựa trên tác phẩm văn học kinh điển cùng tên của đại thi hào Nguyễn Du. Chỉ khi vừa tung ra đoạn trailer ngắn, Kiều đã khiến không ít khán giả phải tò mò và mong chờ. Liệu Kiều trên màn ảnh có thu hút được khán giả không? Mời bạn cùng ra rạp để có được câu trả lời chính xác nhất", "07:00:05 12/5/2021"));

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
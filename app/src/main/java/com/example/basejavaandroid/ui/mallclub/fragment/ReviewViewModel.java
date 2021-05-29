package com.example.basejavaandroid.ui.mallclub.fragment;

import android.os.Build;
import android.text.TextUtils;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.MutableLiveData;

import com.example.basejavaandroid.base.BaseViewmodel;
import com.example.basejavaandroid.model.Review;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReviewViewModel extends BaseViewmodel {
    public MutableLiveData<ArrayList<Review>> arrReview = new MutableLiveData<>();
    public MutableLiveData<ArrayList<Review>> arrMYReview = new MutableLiveData<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getReview(String keyword) {
        ArrayList<Review> list = new ArrayList<>();
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "KFC", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "King BBQ", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Kichi kichi", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Khao Lao", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Ding Tea", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Cộng cà phê", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Gucci", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Bít tết ngon", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Chicken Society", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Toco toco", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        if (TextUtils.isEmpty(keyword)) {
            arrReview.setValue(list);
        } else {
            list = (ArrayList<Review>) list.stream().filter(review -> review.getNameTenant().toLowerCase().contains(keyword.trim().toLowerCase())).collect(Collectors.toList());
            arrReview.setValue(list);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getMYReview(String keyword) {
        ArrayList<Review> list = new ArrayList<>();
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "A", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 200, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "B", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 39, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "C", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 704, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "D", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 519, 1, true));
        if (TextUtils.isEmpty(keyword)) {
            arrMYReview.setValue(list);
        } else {
            list = (ArrayList<Review>) list.stream().filter(review -> review.getNameTenant().toLowerCase().contains(keyword.trim().toLowerCase())).collect(Collectors.toList());
            arrMYReview.setValue(list);
        }
    }
}

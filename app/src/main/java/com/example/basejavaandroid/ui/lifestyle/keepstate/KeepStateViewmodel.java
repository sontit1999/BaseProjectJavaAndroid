package com.example.basejavaandroid.ui.lifestyle.keepstate;

import androidx.lifecycle.MutableLiveData;

import com.example.basejavaandroid.base.BaseViewmodel;
import com.example.basejavaandroid.model.Review;

import java.util.ArrayList;

public class KeepStateViewmodel extends BaseViewmodel {
    int currentPosReview = 0;
    int currentPosMyReview = 0;
    int currentPageReview;
    int currentPageMyReview;
    int totalPageReview;
    int totalPageMyReview;

    MutableLiveData<ActionReview> actionState = new MutableLiveData<>();
    ArrayList<Review> listReview = new ArrayList<>();
    ArrayList<Review> listMyReview = new ArrayList<>();

    public KeepStateViewmodel() {
        getMyReview();
        getReview();
    }

    public void getReview() {
        // call api get data and add to list
        ArrayList<Review> list = new ArrayList<>();
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "KFC", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "King BBQ", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Kichi kichi", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 2, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Khao Lao", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 3, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Ding Tea", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 5, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Cộng cà phê", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 2, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Gucci", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Bít tết ngon", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Chicken Society", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 5, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Toco toco", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "KFC", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "King BBQ", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Kichi kichi", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 2, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Khao Lao", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 3, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Ding Tea", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 5, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Cộng cà phê", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 2, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Gucci", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Bít tết ngon", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Chicken Society", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 5, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Toco toco", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, true));
        listReview.addAll(list);
        actionState.setValue(new ActionReview.ActionAddReview());
    }

    public void getMyReview() {
        // call api get data and add to list
        ArrayList<Review> list = new ArrayList<>();
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "KFC", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "King BBQ", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Kichi kichi", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 2, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Khao Lao", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 3, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Ding Tea", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 5, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Cộng cà phê", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 2, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Gucci", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Bít tết ngon", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Chicken Society", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 5, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Toco toco", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "KFC", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "King BBQ", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Kichi kichi", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 2, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Khao Lao", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 3, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Ding Tea", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 5, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Cộng cà phê", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 2, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Gucci", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Bít tết ngon", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Chicken Society", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 5, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "Toco toco", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 4, true));
        listMyReview.addAll(list);
        actionState.setValue(new ActionReview.ActionAddMyReview());
    }

    static class ActionReview {
        static class ActionAddReview extends ActionReview {

        }

        static class ActionAddMyReview extends ActionReview {

        }
    }
}


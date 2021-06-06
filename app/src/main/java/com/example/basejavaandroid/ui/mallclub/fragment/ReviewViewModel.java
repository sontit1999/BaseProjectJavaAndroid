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
    public boolean isMyReview;
    public ArrayList<Review> listReview = new ArrayList<>();
    boolean isLoading = false;
    MutableLiveData<ActionReview> actionState = new MutableLiveData<>();
    int currentPage = 1;
    int totalPage = 5;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ReviewViewModel() {
        isMyReview = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)

    public void getMoreReview() {
        actionState.setValue(new ActionReview.ActionShowLoading(true));
        currentPage++;
        if (currentPage > totalPage) {
            isLoading = false;
            actionState.setValue(new ActionReview.ActionShowLoading(false));
            return;
        }
        isLoading = false;
        // call api get list review page new and add to list and notifi to adapter
        actionState.setValue(new ActionReview.ActionShowLoading(false));
        ArrayList<Review> list = new ArrayList<>();
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MORE KFC", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MORE King BBQ", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MORE Kichi kichi", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MORE Khao Lao", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MORE Ding Tea", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.addAll(list);
        actionState.setValue(new ActionReview.ActionNotificationDataChange());
    }

    public void getReviewByKeyWord(String keyword) {
        // clear list
        listReview.clear();
        // call api get list by keyword and set to list and notifie adapter
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
        listReview.addAll(list);
        actionState.setValue(new ActionReview.ActionNotificationDataChange());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getReview(String keyword) {
        // call api and getPage 1
        listReview.clear();
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
            listReview.addAll(list);
        } else {
            list = (ArrayList<Review>) list.stream().filter(review -> review.getNameTenant().toLowerCase().contains(keyword.trim().toLowerCase())).collect(Collectors.toList());
            listReview.addAll(list);
        }
        actionState.setValue(new ActionReview.ActionNotificationDataChange());
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getMyReview(String keyword) {
        // call api and getPage 1
        listReview.clear();
        ArrayList<Review> list = new ArrayList<>();
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MY KFC", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MY King BBQ", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MY Kichi kichi", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MY Khao Lao", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MY Ding Tea", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MY Cộng cà phê", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MY Gucci", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MY Bít tết ngon", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MY Chicken Society", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, false));
        list.add(new Review("http://static.demilked.com/wp-content/uploads/2020/03/5e74779735304-japanese-mom-egg-food-art-1-5e73634d343e4__880.jpg", "MY Toco toco", "Maria ozawa", "hi", "12:01 22/5/2021", "Cửa hàng nhìn rất đẹp trang trọng. Đồ ăn ở đây lại ngon nữa.Nói chung tuyệt vời mn đến ủng hộ quán nha", 569, 1, true));
        if (TextUtils.isEmpty(keyword)) {
            listReview.addAll(list);
        } else {
            list = (ArrayList<Review>) list.stream().filter(review -> review.getNameTenant().toLowerCase().contains(keyword.trim().toLowerCase())).collect(Collectors.toList());
            listReview.addAll(list);
        }
        actionState.setValue(new ActionReview.ActionNotificationDataChange());
    }

    public void onCLickLike(int pos) {
        Review review = listReview.get(pos);
        review.setLike(!review.isLike());
        listReview.set(pos, review);
        actionState.setValue(new ActionReview.ActionNotificationReviewUpdate(pos));
    }

    static class ActionReview {
        static class ActionNotificationReviewUpdate extends ActionReview {
            int pos;

            public ActionNotificationReviewUpdate(int pos) {
                this.pos = pos;
            }
        }

        static class ActionNotificationDataChange extends ActionReview {
        }

        static class ActionShowLoading extends ActionReview {
            boolean isShow;

            public ActionShowLoading(boolean isShow) {
                this.isShow = isShow;
            }
        }
    }
}

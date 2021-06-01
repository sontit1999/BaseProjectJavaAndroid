package com.example.basejavaandroid.ui.lifestyle.fragment;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.basejavaandroid.Film;
import com.example.basejavaandroid.FilmAdapter;
import com.example.basejavaandroid.base.BaseViewmodel;

import java.util.ArrayList;
import java.util.List;

public class EventCommonViewmodel extends BaseViewmodel {
    private final MutableLiveData<List<Film>> arrFilm = new MutableLiveData<>();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    public FilmAdapter filmAdapter = new FilmAdapter();
    int totalPage = 5;
    int currentPage = 1;

    public EventCommonViewmodel() {
        List<Film> list = new ArrayList<>();
        list.add(new Film(1, "Bố già", "https://cdn.tgdd.vn/Files/2020/07/01/1266904/top-phim-chieu-rap-hay-nhat-2020-tich-tien-28.jpg", 2, "Bộ phim xoay quanh về gia đình nhỏ của Ba Sang và Quắn, sống trong một khu xóm lao động nghèo. Ba Sang là một người hay lo chuyện bao đồng, luôn yêu thương con trai nhưng khoảng cách giữa những thế hệ đã khiến cho cả hay xảy ra nhiều mâu thuẫn"));
        list.add(new Film(2, "Godzilla Đại Chiến Kong", "https://www.fullphim.net/static/5fe2d564b3fa6403ffa11d1c/6061eb3d14fcbf485386b98a_godzilla-kong-2.jpg", 3, "Hai kẻ thù truyền kiếp Kong và Godzilla sẽ chạm trán trong một trận chiến thế kỉ. Kong và các đồng đội cùng với Jia là một cô gái trẻ mồ côi, sẽ dấn thân vào cuộc thám hiểm đầy hiểm nguy mong tìm ra được ngôi nhà thật sự của mình."));
        list.add(new Film(3, "Song Song", "https://cdn.tgdd.vn/Files/2020/07/01/1266904/top-phim-chieu-rap-hay-nhat-2020-tich-tien-30.jpg", 1, "Vào một đêm mưa bão, Phong (Thuận Phát) đã vô tình chứng kiến cảnh ông Sơn (Tiến Luật) hàng xóm đang cố gắng di chuyển một xác chết. Vì quá sợ hãi, Phong chạy ra khỏi nhà nhưng đã bị xe tải tông chết ngay tại chỗ trước sự bất lực của ông Sơn. Liệu mọi chuyện sẽ diễn ra như thế nào? Mời bạn theo dõi phim để có câu trả lời."));
        list.add(new Film(4, "Siêu Trộm - Way Down", "https://media.voocdn.com/media/image/id/6065776aacc399e5ba8b45d7", 2.5f, "Nhà thám hiểm đại dương Walter Moeland vừa thành công trong việc trục vớt một xác tàu đắm thuộc vùng biển Tây Ban Nha, đồng thời tìm được 3 đồng xu vàng được cho là dẫn đến kho báu “bất tận” được chôn giấu của huyền thoại Sir Francis Drake."));
        list.add(new Film(5, "Cô Gái Trẻ Hứa Hẹn - Promising Young Woman", "https://lh3.googleusercontent.com/proxy/xvlZV6hnejy2d_eLrT1m7tVMErfANW4rcfuXDdzRTcvOTNo9oOyNQ0n4aqxe1Gj0QuByhLLnevilkvhrux-evLz-x3IlQADl46uG_u-oE1O1fJTCaTD9A2YAf3MjepOq-CLKrg", 2.5f, "Trước vụ việc người bạn thân nhất Nina Fisher bị tấn công tình dục và rồi tự sát vì không tìm thấy công lý, Cassandra Cassie Thomas đã gặp phải tổn thương tâm lý nặng nề và bỏ học. Giờ đây, Cassie 30 tuổi lang thang giữa các hộp đêm Ohio, đóng giả làm một “con mồi béo bở” đang say khướt để mời gọi và dạy dỗ những chàng trai ngây ngô hư hỏng."));
        list.add(new Film(6, "Kiều", "https://kenh14cdn.com/thumb_w/660/203336854389633024/2021/4/7/1614643553076225740302655401821542371023678n-1617813417946909536602.jpg", 3, "Bộ phim dựa trên tác phẩm văn học kinh điển cùng tên của đại thi hào Nguyễn Du. Chỉ khi vừa tung ra đoạn trailer ngắn, Kiều đã khiến không ít khán giả phải tò mò và mong chờ. Liệu Kiều trên màn ảnh có thu hút được khán giả không? Mời bạn cùng ra rạp để có được câu trả lời chính xác nhất"));
        arrFilm.postValue(list);
    }

    public MutableLiveData<List<Film>> getArrFilm() {
        return arrFilm;
    }

    public void loadMoreFilm() {
        currentPage++;
        if (currentPage > totalPage) {
            return;
        }
        Log.d("sondz", "load film page = " + currentPage);
        List<Film> list = new ArrayList<>();
        list.add(new Film(1, "Bố già", "https://cdn.tgdd.vn/Files/2020/07/01/1266904/top-phim-chieu-rap-hay-nhat-2020-tich-tien-28.jpg", 2, "Bộ phim xoay quanh về gia đình nhỏ của Ba Sang và Quắn, sống trong một khu xóm lao động nghèo. Ba Sang là một người hay lo chuyện bao đồng, luôn yêu thương con trai nhưng khoảng cách giữa những thế hệ đã khiến cho cả hay xảy ra nhiều mâu thuẫn"));
        list.add(new Film(2, "Godzilla Đại Chiến Kong", "https://www.fullphim.net/static/5fe2d564b3fa6403ffa11d1c/6061eb3d14fcbf485386b98a_godzilla-kong-2.jpg", 3, "Hai kẻ thù truyền kiếp Kong và Godzilla sẽ chạm trán trong một trận chiến thế kỉ. Kong và các đồng đội cùng với Jia là một cô gái trẻ mồ côi, sẽ dấn thân vào cuộc thám hiểm đầy hiểm nguy mong tìm ra được ngôi nhà thật sự của mình."));
        list.add(new Film(3, "Song Song", "https://cdn.tgdd.vn/Files/2020/07/01/1266904/top-phim-chieu-rap-hay-nhat-2020-tich-tien-30.jpg", 1, "Vào một đêm mưa bão, Phong (Thuận Phát) đã vô tình chứng kiến cảnh ông Sơn (Tiến Luật) hàng xóm đang cố gắng di chuyển một xác chết. Vì quá sợ hãi, Phong chạy ra khỏi nhà nhưng đã bị xe tải tông chết ngay tại chỗ trước sự bất lực của ông Sơn. Liệu mọi chuyện sẽ diễn ra như thế nào? Mời bạn theo dõi phim để có câu trả lời."));
        list.add(new Film(4, "Siêu Trộm - Way Down", "https://media.voocdn.com/media/image/id/6065776aacc399e5ba8b45d7", 2.5f, "Nhà thám hiểm đại dương Walter Moeland vừa thành công trong việc trục vớt một xác tàu đắm thuộc vùng biển Tây Ban Nha, đồng thời tìm được 3 đồng xu vàng được cho là dẫn đến kho báu “bất tận” được chôn giấu của huyền thoại Sir Francis Drake."));
        list.add(new Film(5, "Cô Gái Trẻ Hứa Hẹn - Promising Young Woman", "https://lh3.googleusercontent.com/proxy/xvlZV6hnejy2d_eLrT1m7tVMErfANW4rcfuXDdzRTcvOTNo9oOyNQ0n4aqxe1Gj0QuByhLLnevilkvhrux-evLz-x3IlQADl46uG_u-oE1O1fJTCaTD9A2YAf3MjepOq-CLKrg", 2.5f, "Trước vụ việc người bạn thân nhất Nina Fisher bị tấn công tình dục và rồi tự sát vì không tìm thấy công lý, Cassandra Cassie Thomas đã gặp phải tổn thương tâm lý nặng nề và bỏ học. Giờ đây, Cassie 30 tuổi lang thang giữa các hộp đêm Ohio, đóng giả làm một “con mồi béo bở” đang say khướt để mời gọi và dạy dỗ những chàng trai ngây ngô hư hỏng."));
        list.add(new Film(6, "Kiều", "https://kenh14cdn.com/thumb_w/660/203336854389633024/2021/4/7/1614643553076225740302655401821542371023678n-1617813417946909536602.jpg", 3, "Bộ phim dựa trên tác phẩm văn học kinh điển cùng tên của đại thi hào Nguyễn Du. Chỉ khi vừa tung ra đoạn trailer ngắn, Kiều đã khiến không ít khán giả phải tò mò và mong chờ. Liệu Kiều trên màn ảnh có thu hút được khán giả không? Mời bạn cùng ra rạp để có được câu trả lời chính xác nhất"));
        arrFilm.postValue(list);
        isLoading.setValue(false);
    }
}

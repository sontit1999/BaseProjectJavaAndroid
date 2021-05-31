package com.example.basejavaandroid.ui.lifestyle;

import androidx.lifecycle.MutableLiveData;

import com.example.basejavaandroid.base.BaseViewmodel;
import com.example.basejavaandroid.model.Event;

import java.util.ArrayList;

public class EventLongBienViewModel extends BaseViewmodel {
    int totalPageEvent = 5;
    int totalPageEventSave = 5;
    int currentPosEvent = 0;
    int currentPosEventSave = 0;
    int currentPageEvent = 1;
    int currentPageEventSave = 1;
    boolean isEventCommon = true;
    boolean isLoading = false;
    EventLongBienAdapter eventLongBienAdapter = new EventLongBienAdapter();
    MutableLiveData<ArrayList<Event>> arrEvent = new MutableLiveData<>();
    MutableLiveData<ArrayList<Event>> arrEventSave = new MutableLiveData<>();

    public void loadMoreEvent() {
        // call api load event and add to live data
        if (isEventCommon) {
            // load more event common
        } else {
            // load more event Save
        }
    }

    public void loadEvent() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event(1, "url", "Ngày hội tri ân Khách hàng 2021", "12/2/2021 - 22/2/2021", "Sảnh Tương Lai", "Giải đua xe thăng bằng Pushbike đã chính thức trở lại Aeon Mall Hà Đông! Đến với Ander Saro Cup 2...", true));
        events.add(new Event(1, "url", "Ngày hội tri ân Khách hàng 2021", "12/2/2021 - 22/2/2021", "Sảnh Tương Lai", "Giải đua xe thăng bằng Pushbike đã chính thức trở lại Aeon Mall Hà Đông! Đến với Ander Saro Cup 2...", true));
        events.add(new Event(1, "url", "Ngày hội tri ân Khách hàng 2021", "12/2/2021 - 22/2/2021", "Sảnh Tương Lai", "Giải đua xe thăng bằng Pushbike đã chính thức trở lại Aeon Mall Hà Đông! Đến với Ander Saro Cup 2...", true));
        events.add(new Event(1, "url", "Ngày hội tri ân Khách hàng 2021", "12/2/2021 - 22/2/2021", "Sảnh Tương Lai", "Giải đua xe thăng bằng Pushbike đã chính thức trở lại Aeon Mall Hà Đông! Đến với Ander Saro Cup 2...", true));
        events.add(new Event(1, "url", "Ngày hội tri ân Khách hàng 2021", "12/2/2021 - 22/2/2021", "Sảnh Tương Lai", "Giải đua xe thăng bằng Pushbike đã chính thức trở lại Aeon Mall Hà Đông! Đến với Ander Saro Cup 2...", true));
        events.add(new Event(1, "url", "Ngày hội tri ân Khách hàng 2021", "12/2/2021 - 22/2/2021", "Sảnh Tương Lai", "Giải đua xe thăng bằng Pushbike đã chính thức trở lại Aeon Mall Hà Đông! Đến với Ander Saro Cup 2...", true));
        events.add(new Event(1, "url", "Ngày hội tri ân Khách hàng 2021", "12/2/2021 - 22/2/2021", "Sảnh Tương Lai", "Giải đua xe thăng bằng Pushbike đã chính thức trở lại Aeon Mall Hà Đông! Đến với Ander Saro Cup 2...", true));
        events.add(new Event(1, "url", "Ngày hội tri ân Khách hàng 2021", "12/2/2021 - 22/2/2021", "Sảnh Tương Lai", "Giải đua xe thăng bằng Pushbike đã chính thức trở lại Aeon Mall Hà Đông! Đến với Ander Saro Cup 2...", true));
        events.add(new Event(1, "url", "Ngày hội tri ân Khách hàng 2021", "12/2/2021 - 22/2/2021", "Sảnh Tương Lai", "Giải đua xe thăng bằng Pushbike đã chính thức trở lại Aeon Mall Hà Đông! Đến với Ander Saro Cup 2...", true));
        events.add(new Event(1, "url", "Ngày hội tri ân Khách hàng 2021", "12/2/2021 - 22/2/2021", "Sảnh Tương Lai", "Giải đua xe thăng bằng Pushbike đã chính thức trở lại Aeon Mall Hà Đông! Đến với Ander Saro Cup 2...", true));
        if (isEventCommon) {
            ArrayList<Event> listNow = new ArrayList<>();
            if (arrEvent.getValue() != null) {
                listNow.addAll(arrEvent.getValue());
            }
            arrEvent.postValue(listNow);
        } else {
            ArrayList<Event> listNow = arrEvent.getValue();
            listNow.addAll(events);
            arrEvent.postValue(listNow);
        }
    }
}

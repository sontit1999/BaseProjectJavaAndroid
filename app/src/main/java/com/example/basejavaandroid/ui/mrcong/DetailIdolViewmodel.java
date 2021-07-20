package com.example.basejavaandroid.ui.mrcong;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.basejavaandroid.base.BaseViewmodel;
import com.example.basejavaandroid.base.network.BaseApplication;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class DetailIdolViewmodel extends BaseViewmodel {
    String linkIdol;
    ArrayList<String> arrLinkPhoto = new ArrayList<>();
    MutableLiveData<ActionDetailIdol> actionState = new MutableLiveData<>();
    int currentPage = 1;
    int MAX_PAGE = 10;
    boolean isLoading = true;

    public void init(String linkIdol) {
        this.linkIdol = linkIdol;
        getPhotoIdol();
    }

    public void getPhotoIdol() {
        currentPage = 1;
        RequestQueue requestQueue = Volley.newRequestQueue(BaseApplication.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, linkIdol, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("doc", response);
                String linkthumbail;
                Document document = Jsoup.parse(response);
                Elements items = document.select(".entry p img");
                ArrayList<String> arrPhoto = new ArrayList<>();
                for (Element i : items) {
                    linkthumbail = i.attr("src");
                    arrPhoto.add(linkthumbail);
                    Log.d("sondz", linkthumbail);
                }
                arrLinkPhoto.addAll(arrPhoto);
                actionState.setValue(new ActionDetailIdol.ActionDataChange());
                isLoading = false;
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        actionState.setValue(new ActionDetailIdol.ActionLoadFail(error.getMessage()));
                    }
                }
        );
        requestQueue.add(stringRequest);
    }

    public void getMorePhotoIdol() {
        currentPage++;
        if (currentPage > MAX_PAGE) {
            actionState.setValue(new ActionDetailIdol.ActionAllDataLoaded());
            return;
        }
        RequestQueue requestQueue = Volley.newRequestQueue(BaseApplication.getContext());
        String urlPage = linkIdol + currentPage + "/";
        Log.d("loadmore page: ", urlPage);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlPage, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("doc", response);
                String linkthumbail;
                Document document = Jsoup.parse(response);
                Elements items = document.select(".entry p img");
                ArrayList<String> arrPhoto = new ArrayList<>();
                for (Element i : items) {
                    linkthumbail = i.attr("src");
                    arrPhoto.add(linkthumbail);
                    Log.d("sondz", linkthumbail);
                }
                arrLinkPhoto.addAll(arrPhoto);
                actionState.setValue(new ActionDetailIdol.ActionLoadMoreComplete(arrPhoto.size()));
                isLoading = false;
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        actionState.setValue(new ActionDetailIdol.ActionLoadFail(error.getMessage()));
                    }
                }
        );
        requestQueue.add(stringRequest);
    }

    static class ActionDetailIdol {
        static class ActionDataChange extends ActionDetailIdol {

        }

        static class ActionAllDataLoaded extends ActionDetailIdol {

        }

        static class ActionLoadMoreComplete extends ActionDetailIdol {
            int count;

            public ActionLoadMoreComplete(int count) {
                this.count = count;
            }
        }

        static class ActionLoadFail extends ActionDetailIdol {
            String message;

            public ActionLoadFail(String message) {
                this.message = message;
            }
        }
    }
}

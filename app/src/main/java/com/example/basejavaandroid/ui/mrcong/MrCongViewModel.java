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
import com.example.basejavaandroid.base.Constant;
import com.example.basejavaandroid.base.SingleLiveEvent;
import com.example.basejavaandroid.base.network.BaseApplication;
import com.example.basejavaandroid.base.network.BaseInterface;
import com.example.basejavaandroid.base.network.RetrofitClient;
import com.example.basejavaandroid.model.Idol;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class MrCongViewModel extends BaseViewmodel {
    public ArrayList<Idol> arrIdol = new ArrayList<>();
    public SingleLiveEvent<ActionIdol> actionState = new SingleLiveEvent<>();
    private int currentPage = 1;
    public static final int MAX_PAGE  = 500;
    BaseInterface apiManager ;
    boolean isLoading = true;

    public MrCongViewModel() {
        apiManager = RetrofitClient.getInterfaceClient(BaseApplication.getContext());
        getIdol();
    }
    public void getIdol(){
        currentPage = 1;

        RequestQueue requestQueue = Volley.newRequestQueue(BaseApplication.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.BASE_URL + currentPage, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String linkidol, linkthumbail, nameidol;
                Document document = Jsoup.parse(response);
                Elements items = document.select(".item-list");
                ArrayList<Idol> arrIdols = new ArrayList<>();
                for (Element i : items) {
                    linkidol = i.select(".post-thumbnail a").attr("href");
                    linkthumbail = i.select(".post-thumbnail a img").attr("src");
                    nameidol = i.select("h2 a").text();
                    Idol idol = new Idol(nameidol,linkthumbail,linkidol);
                    arrIdol.add(idol);
                    Log.d("Idol", nameidol + "-" + linkthumbail + "-" + linkidol);
                }
                arrIdol.addAll(arrIdols);
                actionState.setValue(new ActionIdol.ActionDataChange());
                isLoading = false;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("sondz",error.getMessage());
            }
        });
        requestQueue.add(stringRequest);
    };
    public void getMoreIdol(){
        currentPage++;
        if(currentPage>MAX_PAGE){
            return;
        }
        RequestQueue requestQueue = Volley.newRequestQueue(BaseApplication.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, Constant.BASE_URL + currentPage, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                String linkidol, linkthumbail, nameidol;
                Document document = Jsoup.parse(response);
                Elements items = document.select(".item-list");
                ArrayList<Idol> arrIdols = new ArrayList<>();
                for (Element i : items) {
                    linkidol = i.select(".post-thumbnail a").attr("href");
                    linkthumbail = i.select(".post-thumbnail a img").attr("src");
                    nameidol = i.select("h2 a").text();
                    Idol idol = new Idol(nameidol,linkthumbail,linkidol);
                    arrIdol.add(idol);
                    Log.d("Idol", nameidol + "-" + linkthumbail + "-" + linkidol);
                }
                arrIdol.addAll(arrIdols);
                actionState.setValue(new ActionIdol.ActionDataChange());
                isLoading = false;
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("sondz",error.getMessage());
            }
        });
        requestQueue.add(stringRequest);
    }
    static class ActionIdol{
        static class ActionDataChange extends ActionIdol{

        }
    }
}

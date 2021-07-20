package com.example.basejavaandroid.ui.mrcong;

import android.util.Log;
import android.widget.Toast;

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
    int currentPage = 1;
    int MAX_PAGE = 500;
    public void init(String linkIdol){
        this.linkIdol = linkIdol;
        getPhotoIdol();
    }
    public void getPhotoIdol(){
        RequestQueue requestQueue = Volley.newRequestQueue(BaseApplication.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,linkIdol, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("doc",response.toString());
                String linkthumbail;
                Document document = Jsoup.parse(response);
                Elements items = document.select(".entry p img");
                ArrayList<String> arrPhoto = new ArrayList<>();
                for (Element i : items)
                {
                    linkthumbail = i.attr("src");
                    arrPhoto.add(linkthumbail);
                }
                arrLinkPhoto.addAll(arrPhoto);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(stringRequest);
    }
    public void getMorePhotoIdol(){
        RequestQueue requestQueue = Volley.newRequestQueue(BaseApplication.getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET,linkIdol, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("doc",response.toString());
                String linkthumbail;
                Document document = Jsoup.parse(response);
                Elements items = document.select(".entry p img");
                ArrayList<String> arrPhoto = new ArrayList<>();
                for (Element i : items)
                {
                    linkthumbail = i.attr("src");
                    arrPhoto.add(linkthumbail);
                }
                arrLinkPhoto.addAll(arrPhoto);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );
        requestQueue.add(stringRequest);
    }
}

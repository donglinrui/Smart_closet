package com.example.a82572.weatherapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.telecom.Call;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Callback;
import okhttp3.Response;

public class Fragment_home extends Fragment {
    RecyclerView recyclerView;
    ClothesAdapter adapter;
    int i;
    private List<Clothes> clothesList= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        recyclerView= (RecyclerView)view.findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager LayoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        //initClothes(26);//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        clothesList=DataSupport.findAll(Clothes.class);
        Log.d("database","count: "+clothesList.size());
        Log.d("database","read_ok");

        for(i=0;i<clothesList.size();i++)
        {
            Log.d("database","clothes:"+clothesList.get(i).getAddress()+" "+clothesList.get(i).getId()+" "+clothesList.get(i).getKeep_warm()+" ");
            //Log.d("database","clothes:"+clothesList.get(i).getKeep_warm()+" "+clothesList.get(i).getMy_kind().toString()+" ");

            Log.d("database","mykind");
        }
        recyclerView.setLayoutManager(LayoutManager);
        adapter=new ClothesAdapter(getActivity(),clothesList);
        recyclerView.setAdapter(adapter);
        Log.d("database","get_view");

        return view;
    }


    void initClothes(int qi_wen){
        clothesList=get_suitclothes(qi_wen);
    }
    private List<Clothes> get_suitclothes(int qi_wen)
    {
        //String best_address="null";
        List<Clothes> all_clothes= DataSupport.findAll(Clothes.class);
        List<Clothes> suit_clothes =DataSupport.findAll(Clothes.class);
        suit_clothes.clear();
        int wucha=3;
        while(suit_clothes.isEmpty()&&wucha<=15)
        {
            for(Clothes c:all_clothes){
                if(  c.getKeep_warm()>=(Math.abs(qi_wen-26)-wucha)  &&  c.getKeep_warm()<=(Math.abs(qi_wen-26)+wucha)  )
                    suit_clothes.add(c);
            }
            wucha=wucha+2;
        }
         if(suit_clothes.isEmpty()) {
             //没有合适的衣物
             Clothes sample =new Clothes();
             sample.setId(0);
             sample.setMy_kind("waitao");
             sample.setLove_value(1);
             sample.setAddress("");
             sample.setKeep_warm(2);
             suit_clothes.add(sample);
         }
        /*else
        {
            int now_love=-1;
            for(Clothes c:suit_clothes){
                if(  c.getLove_value()>now_love  )
                    best_address=c.getAddress();
            }
        }*/
        return suit_clothes;
    }
    /**
     * 将返回的JSON数据解析成Weather实体类
     */
    public static Weather handleWeatherResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 根据天气id请求城市天气信息。
     */
    /*public void requestWeather(final String weatherId) {
        String weatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=bc0418b57b2d4918819d3974ac1285d9";
        HttpUtil.sendOkHttpRequest(weatherUrl, new Callback() {
            @Override..
            public void onResponse(Call call, Response response) throws IOException {
                final String responseText = response.body().string();
                final Weather weather = handleWeatherResponse(responseText);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (weather != null && "ok".equals(weather.status)) {
                            SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                            editor.putString("weather", responseText);
                            editor.apply();
                            showWeatherInfo(weather);
                        } else {
                            Toast.makeText(getContext(),"获取天气信息失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "获取天气信息失败", Toast.LENGTH_SHORT).show();
                        //swipeRefresh.setRefreshing(false);
                    }
                });
            }
        });
    }
    /**
     * 处理并展示Weather实体类中的数据。
     */
    private void showWeatherInfo(Weather weather) {
        String cityName = weather.basic.cityName;
        String updateTime = weather.basic.update.updateTime.split(" ")[1];
        String degree = weather.now.temperature + "℃";
        String weatherInfo = weather.now.more.info;
        /*titleCity.setText(cityName);
        titleUpdateTime.setText(updateTime);
        degreeText.setText(degree);
        weatherInfoText.setText(weatherInfo);
        forecastLayout.removeAllViews();
        for (Forecast forecast : weather.forecastList) {
            View view = LayoutInflater.from(this).inflate(R.layout.forecast_item, forecastLayout, false);
            TextView dateText = (TextView) view.findViewById(R.id.date_text);
            TextView infoText = (TextView) view.findViewById(R.id.info_text);
            TextView maxText = (TextView) view.findViewById(R.id.max_text);
            TextView minText = (TextView) view.findViewById(R.id.min_text);
            dateText.setText(forecast.date);
            infoText.setText(forecast.more.info);
            maxText.setText(forecast.temperature.max);
            minText.setText(forecast.temperature.min);
            forecastLayout.addView(view);
        }
        if (weather.aqi != null) {
            aqiText.setText(weather.aqi.city.aqi);
            pm25Text.setText(weather.aqi.city.pm25);
        }
        String comfort = "舒适度：" + weather.suggestion.comfort.info;
        String carWash = "洗车指数：" + weather.suggestion.carWash.info;
        String sport = "运行建议：" + weather.suggestion.sport.info;
        comfortText.setText(comfort);
        carWashText.setText(carWash);
        sportText.setText(sport);
        weatherLayout.setVisibility(View.VISIBLE);*/
    }
}


package com.example.a82572.weatherapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class fragment_view3 extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.view3,container,false);
        recyclerView= (RecyclerView)view.findViewById(R.id.recycler_view3);
        StaggeredGridLayoutManager LayoutManager=new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(LayoutManager);
        initClothes();
        adapter=new ClothesAdapter(getActivity(),clothesList);
        recyclerView.setAdapter(adapter);
        return view;
    }
    //短袖
    RecyclerView recyclerView;
    ClothesAdapter adapter;
    private List<Clothes> clothesList= new ArrayList<>();
    void initClothes(){
        List<Clothes> all_clothes= DataSupport.findAll(Clothes.class);
        for(Clothes c:all_clothes){
            if(  c.getMy_kind().equals("duanxiu"))
                clothesList.add(c);
        }
    }

}

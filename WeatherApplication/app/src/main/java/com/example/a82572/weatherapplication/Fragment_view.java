package com.example.a82572.weatherapplication;

import android.support.v4.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class Fragment_view extends Fragment {
    private ViewpagerSlide vp;
    private List<Fragment> listf= new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_view,container,false);
        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view,savedInstanceState);
        vp=(ViewpagerSlide)view.findViewById(R.id.page_view);
        getlist();
        BottomViewAdapter my=new BottomViewAdapter(getChildFragmentManager(),listf);
        vp.setAdapter(my);
        vp.setOffscreenPageLimit(3);
    }
    void getlist(){
        listf.add(new fragment_view1());
        listf.add(new fragment_view3());
        listf.add(new fragment_view2());
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Button button=(Button)getActivity().findViewById(R.id.button);
        Button button1=(Button)getActivity().findViewById(R.id.button1);
        Button button2=(Button)getActivity().findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(0);
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vp.setCurrentItem(2);
            }
        });
    }
}
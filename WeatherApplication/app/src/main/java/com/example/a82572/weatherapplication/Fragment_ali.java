package com.example.a82572.weatherapplication;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class Fragment_ali extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view=inflater.inflate(R.layout.fragment_ali,container,false);
        return view;
    }
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Button button_shirt=(Button)getActivity().findViewById(R.id.search_shirt);
        Button button_coat=(Button)getActivity().findViewById(R.id.search_coat);
        button_shirt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"淘宝寻找短袖",Toast.LENGTH_LONG).show();
            }
        });
        button_coat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"淘宝寻找棉袄",Toast.LENGTH_LONG).show();
            }
        });
    }
}
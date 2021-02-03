package com.example.a82572.weatherapplication;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;

import android.widget.BaseAdapter;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

public class Fragment_add extends Fragment {
    ImageButton start_photo;
    EditText keep_warm;
    DropEditText clothes_kind;
    Uri fileUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View view=inflater.inflate(R.layout.fragment_add,container,false);
        DropEditText drop1 = (DropEditText) view.findViewById(R.id.drop_edit);

        drop1.setAdapter(new BaseAdapter() {

            private List<String> mList = new ArrayList<String>() {

                {

                    add("短袖");

                    add("夹克");

                    add("棉袄");

                    add("clothes_type four");

                    add("clothes_type five");

                    add("clothes_type six");

                    add("clothes_type seven");

                }

            };



            @Override

            public int getCount() {

                return mList.size();

            }



            @Override

            public Object getItem(int position) {

                return mList.get(position);

            }



            @Override

            public long getItemId(int position) {

                return position;

            }



            @Override

            public View getView(int position, View convertView, ViewGroup parent) {

                TextView tv = new TextView(view.getContext());

                tv.setText(mList.get(position));

                return tv;

            }

        });
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        Button button=(Button)getActivity().findViewById(R.id.button_add);
         keep_warm=(EditText)getActivity().findViewById(R.id.editText_temperature);
         clothes_kind=(DropEditText)getActivity().findViewById(R.id.drop_edit) ;
        //keep_warm.setText("111");
        start_photo=(ImageButton)getActivity().findViewById(R.id.start_photo);

        start_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent=new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    startActivityForResult(intent,1);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (clothes_kind.getText())
                {
                    case "外套":
                        addClothes("waitao",Integer.valueOf(keep_warm.getText().toString()),1);
                        break;
                    case "棉袄":
                        addClothes("mianao",Integer.valueOf(keep_warm.getText().toString()),1);
                        break;
                    case "短袖":
                        addClothes("duanxiu",Integer.valueOf(keep_warm.getText().toString()),1);
                        break;

                }
                Toast.makeText(getContext(),"增加成功",Toast.LENGTH_LONG).show();
            }
        });


    }
    private void addClothes(String mykind,int keep,int love )
    {
        List<Clothes> all_clothes= DataSupport.findAll(Clothes.class);
        Clothes add_1=new Clothes();
        add_1.setKeep_warm(keep);
        add_1.setAddress("/mnt/sdcard/yigui"+"/"+Integer.toString(all_clothes.size()));
        add_1.setId(all_clothes.size());
        add_1.setLove_value(love);
        add_1.setMy_kind(mykind);
        add_1.save();
    }

    /*public void onActivityResult(int requestCode,int resultCode,Intent data)//接收下一活动数据
    {
        super.onActivityResult(requestCode,resultCode,data);
        Log.d("getphoto","out_main_activity!!!!!!!!!!!1");
        if(requestCode==1)
        {
            Log.d("getphoto","request code right !!!!!!!!!!!1");
            Toast.makeText(getActivity(),data.getData().toString(),Toast.LENGTH_SHORT).show();
            Log.d("MainActivity",data.getData().toString());
            start_photo.setImageURI(data.getData());
        }
    }*/
    private void savePhotoToSD(Bitmap bitmap,File out) {

        //创建输出流缓冲区
        BufferedOutputStream os = null;
        try {
            //设置输出流
            os = new BufferedOutputStream(new FileOutputStream(out));
            //
            //压缩图片，100表示不压缩
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
            //Log.d(TAG, "保存照片完成。");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    //不管是否出现异常，都要关闭流
                    os.flush();
                    os.close();
                    // Log.d(TAG, "刷新、关闭流");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        Log.d("AddClothes","save photo ok");
    }
}
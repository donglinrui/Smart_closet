package com.example.a82572.weatherapplication;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Uri fileUri;//存储图片
    public static final String SD_APP_DIR_NAME = "mnt/sdcard"; //存储程序在外部SD卡上的根目录的名字
    public static final String PHOTO_DIR_NAME = "yigui";    //存储照片在根目录下的文件夹名字
    List<Fragment> list_fragment = new ArrayList<>();
    List<Fragment> listf= new ArrayList<>();
    ViewpagerSlide viewPager;
    private Uri imageUri;
    ImageButton start_photo;
    MenuItem menuItem;
    BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigation_add:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigation_view:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.navigation_ali:
                    viewPager.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


       //创建存储图片文件夹
        File file = new File("/mnt/sdcard/yigui");
        //判断文件夹是否存在，如果不存在就创建，否则不创建
        if (!file.exists()) {
            //通过file的mkdirs()方法创建目录中包含却不存在的文件夹
            file.mkdirs();
        }
        Log.d("start","create file ");
        LitePal.getDatabase();
        Log.d("litepal","create database");
        Clothes t=new Clothes();

        setContentView(R.layout.activity_main);
        viewPager=(ViewpagerSlide)findViewById(R.id.viewpager_launch);

        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        setNavigation();

        /*start_photo=(ImageButton)findViewById(R.id.start_photo);
        start_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outputImage=new File(getExternalCacheDir(),"output_image.jpg");//在缓存目录中创建file对象
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                }catch(IOException e)
                {
                    e.printStackTrace();
                }

                if (Build.VERSION.SDK_INT>=24){
                    imageUri= FileProvider.getUriForFile(MainActivity.this,"com.example.82572.weatherapplication.fileprovider",outputImage);//版本高于7.0，封装的URI
                }else {
                    imageUri=Uri.fromFile(outputImage);//版本低于7.0真实路径URI
                }

                //启动相机程序
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                startActivityForResult(intent,1);
            }
        });*/


    }
    private void crop(Uri uri) {
                // 裁剪图片意图
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(uri, "image/*");
                intent.putExtra("crop", "true");
                 // 裁剪框的比例，1：1
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                 // 裁剪后输出图片的尺寸大小
                intent.putExtra("outputX", 500);
                 intent.putExtra("outputY", 500);

                intent.putExtra("outputFormat", "JPEG");// 图片格式
                intent.putExtra("noFaceDetection", true);// 取消人脸识别
                 intent.putExtra("return-data", true);
                // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
                startActivityForResult(intent, 2);
           }
    public void onActivityResult(int requestCode,int resultCode,Intent data)//接收下一活动数据
    {
        Log.d("getphoto","ok!!!!!!!!!!!1"+requestCode);
        if(requestCode%16==1)
        {
            Log.d("getphoto","ok!!!!!!!!!!!1");
            Uri uri = data.getData();
            crop(uri);
        }
        if(requestCode==2)
        {
            Log.d("getphoto","show00000");
            if (data != null) {
                Log.d("getphoto","show1111111");
                Bitmap bitmap = data.getParcelableExtra("data");
                ImageButton getphoto=(ImageButton)findViewById(R.id.start_photo);
                getphoto.setImageBitmap(bitmap);
            }
        }

    }

    private void setNavigation(){
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                if(menuItem!=null){
                    menuItem.setChecked(false);
                }else {
                    navigation.getMenu().getItem(0).setChecked(false);
                }
                menuItem=navigation.getMenu().getItem(position);
                menuItem.setChecked(true);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        list_fragment.add(new Fragment_home());
        list_fragment.add(new Fragment_add());
        list_fragment.add(new Fragment_view());
        list_fragment.add(new Fragment_ali());
        BottomViewAdapter adapter=new BottomViewAdapter(getSupportFragmentManager(),list_fragment);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
    }
    /*private void savePhotoToSD(Bitmap bitmap,File out) {

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
    }*/
}

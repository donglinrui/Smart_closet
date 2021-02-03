package com.example.a82572.weatherapplication;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import java.util.List;
public class BottomViewAdapter extends FragmentPagerAdapter {
    private List<Fragment>  mfragmentList;
    public BottomViewAdapter(FragmentManager manager,List<Fragment> mfragmentList){
        super(manager);
        this.mfragmentList=mfragmentList;
    }
    @Override
    public Fragment getItem(int position){
        return mfragmentList.get(position);
    }
    @Override
    public int getCount(){
        return mfragmentList.size();
    }
}

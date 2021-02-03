package com.example.a82572.weatherapplication;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.util.List;
public class ClothesAdapter extends RecyclerView.Adapter<ClothesAdapter.ViewHolder> {
    private List<Clothes> mClothesList;
    private Context context;
    static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView clothesImage;
        public TextView clothesType;
        public View clothesView;
        public Button button;
        public ViewHolder(View view){
            super(view);
            clothesView=view;
            clothesImage=(ImageView)view.findViewById(R.id.clothes_image);
            clothesType=(TextView)view.findViewById(R.id.clothes_type);
            button=(Button)view.findViewById(R.id.button_delete);
        }
    }
    public ClothesAdapter(Context context,List<Clothes> clothesList){
        mClothesList=clothesList;
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view=View.inflate(context,R.layout.layout_clothes,null);
        final ViewHolder holder=new ViewHolder(view);
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"DELETE",Toast.LENGTH_LONG).show();
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Clothes clothes=mClothesList.get(position);
        Boolean testi=false;
        if(clothes.getId()!=0&&clothes.getAddress()!="#"&&testi)
        {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(clothes.getAddress(), options);
            options.inSampleSize = calculateInSampleSize(options, 480, 800);
            options.inJustDecodeBounds = false;
            Bitmap bitmap = BitmapFactory.decodeFile(clothes.getAddress(),options);
            holder.clothesImage.setImageBitmap(bitmap);
            holder.clothesType.setText("type:"+clothes.getMy_kind().toString());
        }
        else
        {
            holder.clothesImage.setImageResource(R.drawable.coat);
            holder.clothesType.setText("type:"+clothes.getMy_kind());
        }


    }
    @Override
    public int getItemCount(){
        return mClothesList.size();
    }

    //计算压缩尺寸
    private static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }

        return inSampleSize;
    }

}

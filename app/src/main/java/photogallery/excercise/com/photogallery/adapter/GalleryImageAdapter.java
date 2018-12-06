package photogallery.excercise.com.photogallery.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

import photogallery.excercise.com.photogallery.R;
import photogallery.excercise.com.photogallery.model.Photo;

public class GalleryImageAdapter extends RecyclerView.Adapter<GalleryImageAdapter.ViewHolder> {

    private List<Photo> mPhotoList;
    private Context mContext;

    public GalleryImageAdapter(List<Photo> photoList, Context context) {
        this.mPhotoList = photoList;
        this.mContext = context;
    }

    @Override
    public int getItemCount() {
        int item = 1;
        if (item * 200 > mPhotoList.size()) {
            return mPhotoList.size();
        } else {
            return item * 200;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_display_item, parent, false);
        return new GalleryImageAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryImageAdapter.ViewHolder holder, int position) {
        Photo photo = mPhotoList.get(position);

        Glide.with(mContext).load(photo.getUrl())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.mGalleryImageView);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mGalleryImageView;

        ViewHolder(View view) {
            super(view);
            mGalleryImageView = view.findViewById(R.id.imageView);
        }
    }
}

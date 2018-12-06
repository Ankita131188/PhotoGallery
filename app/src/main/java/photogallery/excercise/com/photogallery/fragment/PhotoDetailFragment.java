package photogallery.excercise.com.photogallery.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import photogallery.excercise.com.photogallery.R;
import photogallery.excercise.com.photogallery.model.Photo;
import photogallery.excercise.com.photogallery.utils.Constants;

/*
    Created by Ankita on 03/12/2018.
    This fragment shows the detail of clicked photo
 */
public class PhotoDetailFragment extends Fragment {
    private List<Photo> mPhotoList = new ArrayList<>();
    private int position;
    private TextView mAlbumIDTextView, mPhotoTitleTextView;
    private ImageView mThumbnailImageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mPhotoList = getArguments().getParcelableArrayList(Constants.PHOTO_DATA);
            position = getArguments().getInt(Constants.PHOTO_POSITION, 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo_detail,
                container, false);
        setupViews(view);
        setData();
        return view;
    }

    private void setupViews(View view) {
        mAlbumIDTextView = view.findViewById(R.id.id_textView);
        mPhotoTitleTextView = view.findViewById(R.id.title_textView);
        mThumbnailImageView = view.findViewById(R.id.thumbnail_imageView);
    }

    private void setData() {
        mAlbumIDTextView.setText(String.valueOf(mPhotoList.get(position).getAlbumId()));
        mPhotoTitleTextView.setText(mPhotoList.get(position).getTitle());
        Glide.with(getContext()).load(mPhotoList.get(position).getThumbanailUrl())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(mThumbnailImageView);
    }
}

package photogallery.excercise.com.photogallery.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import photogallery.excercise.com.photogallery.R;
import photogallery.excercise.com.photogallery.adapter.GalleryImageAdapter;
import photogallery.excercise.com.photogallery.controller.ApiClient;
import photogallery.excercise.com.photogallery.interfaces.ApiService;
import photogallery.excercise.com.photogallery.interfaces.RecyclerViewClickListener;
import photogallery.excercise.com.photogallery.model.Photo;
import photogallery.excercise.com.photogallery.utils.Constants;
import photogallery.excercise.com.photogallery.utils.Utility;

import static android.content.ContentValues.TAG;

/**
 * Created by Ankita on 01/12/2018.
 * This fragment shows the list of photo.
 */

public class PhotoFragment extends Fragment {

    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();
    private ArrayList<Photo> mPhotoList = new ArrayList<>();
    private RecyclerView mPhotoRecyclerView;
    private Utility mUtility;
    private ProgressDialog mProgressDialog;
    private View mSnackBarLayout;

    public PhotoFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configureProgressDialog();
        mUtility = Utility.getmInstance();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photo,
                container, false);
        setupViews(view);
        mPhotoRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        mPhotoRecyclerView.setHasFixedSize(true);
        getAllPhotos();
        return view;
    }

    @Override
    public void onDestroy() {
        mCompositeDisposable.clear();
        super.onDestroy();
    }

    private void setupViews(View view) {
        mPhotoRecyclerView = view.findViewById(R.id.gallery);
        mSnackBarLayout = view.findViewById(android.R.id.content);
    }

    private void configureProgressDialog() {
        mProgressDialog = new ProgressDialog(getContext());
        mProgressDialog.setMessage("Loading...");
        mProgressDialog.show();
    }

    private void getAllPhotos() {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);

        // Fetching all photos
        mCompositeDisposable.add(
                apiService.fetchAllPhotos()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(new DisposableSingleObserver<List<Photo>>() {
                            @Override
                            public void onSuccess(List<Photo> photoList) {
                                mPhotoList.clear();
                                mPhotoList.addAll(photoList);
                                mProgressDialog.dismiss();
                                setGalleryAdapter();
                            }

                            @Override
                            public void onError(Throwable e) {
                                mProgressDialog.dismiss();
                                Log.e(TAG, "onError: " + e.getMessage());
                                mUtility.showError(e, mSnackBarLayout);
                            }
                        })
        );
    }

    private void setGalleryAdapter() {
        GalleryImageAdapter mGalleryAdapter = new GalleryImageAdapter(mPhotoList, getContext());
        mPhotoRecyclerView.setAdapter(mGalleryAdapter);
        onRecyclerViewClick();
    }

    private void onRecyclerViewClick() {
        mPhotoRecyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getContext(),
                new RecyclerViewClickListener.OnItemClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList(Constants.PHOTO_DATA, mPhotoList);
                        bundle.putInt(Constants.PHOTO_POSITION, position);
                        PhotoDetailFragment photoDetailFragment = new PhotoDetailFragment();
                        photoDetailFragment.setArguments(bundle);
                        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.container, photoDetailFragment);
                        fragmentTransaction.addToBackStack(null);
                        fragmentTransaction.commit();
                    }
                }));
    }
}

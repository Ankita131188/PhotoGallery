package photogallery.excercise.com.photogallery.interfaces;

import java.util.List;

import io.reactivex.Single;
import photogallery.excercise.com.photogallery.model.Photo;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {
    @GET("photos")
    Single<List<Photo>> fetchAllPhotos();
}

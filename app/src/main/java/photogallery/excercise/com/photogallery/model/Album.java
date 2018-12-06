package photogallery.excercise.com.photogallery.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ankita on 30/11/2018.
 */

public class Album {

    @Expose
    @SerializedName("UserId")
    private Integer UserId;

    @Expose
    @SerializedName("Id")
    private Integer Id;

    @Expose
    @SerializedName("Title")
    private String Title;
}

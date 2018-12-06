package photogallery.excercise.com.photogallery.model;

import android.os.Parcel;
import android.os.Parcelable;
import photogallery.excercise.com.photogallery.controller.BaseResponse;

/**
 * Created by Ankita on 30/11/2018.
 */

public class Photo extends BaseResponse implements Parcelable {

    private int albumID, ID;
    private String photoTitle, url, thumbanailUrl;

    public Photo()
    {

    }

    private Photo(Parcel in) {
        albumID = in.readInt();
        ID = in.readInt();
        photoTitle = in.readString();
        url = in.readString();
        thumbanailUrl = in.readString();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public Integer getAlbumId() {
        return albumID;
    }

    public void setAlbumId(Integer albumID) {
        this.albumID = albumID;
    }

    public Integer getId() {
        return ID;
    }

    public void setId(Integer id) {
        this.ID = id;
    }

    public String getTitle() {
        return photoTitle;
    }

    public void setTitle(String title) {
        this.photoTitle = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbanailUrl() {
        return thumbanailUrl;
    }

    public void setThumbanailUrl(String thumbanailUrl) {
        this.thumbanailUrl = thumbanailUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(albumID);
        dest.writeInt(ID);
        dest.writeString(photoTitle);
        dest.writeString(url);
        dest.writeString(thumbanailUrl);
    }
}

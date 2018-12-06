package photogallery.excercise.com.photogallery.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import photogallery.excercise.com.photogallery.R;
import photogallery.excercise.com.photogallery.adapter.ViewPagerAdapter;
import photogallery.excercise.com.photogallery.fragment.AlbumFragment;
import photogallery.excercise.com.photogallery.fragment.PhotoFragment;

public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        setupTabIcons();
    }

    private void setupViews() {
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabs);
    }

    private void setupTabIcons() {
        if (mTabLayout != null) {
            if (mTabLayout.getTabAt(0) != null)
                mTabLayout.getTabAt(0).setIcon(R.drawable.photo_icon);
            if (mTabLayout.getTabAt(1) != null)
                mTabLayout.getTabAt(1).setIcon(R.drawable.album_icon);
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PhotoFragment(), getResources().getString(R.string.tab_title_photos));
        adapter.addFragment(new AlbumFragment(), getResources().getString(R.string.tab_title_album));
        viewPager.setAdapter(adapter);
    }
}

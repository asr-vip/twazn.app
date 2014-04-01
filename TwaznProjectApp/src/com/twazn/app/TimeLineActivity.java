package com.twazn.app;



import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;



public class TimeLineActivity extends Activity  {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timeline);
        
        ActivityBar.getInstance().connectToActivity(this);
        ActivityBar.getInstance().pressButtonById(R.id.navTimeline);
        
    	ArrayList<feed> image_details = getListData();
    	final ListView lv1 = (ListView) findViewById(R.id.ListView);
    	lv1.setAdapter(new FeedAdapter(this, image_details));
    	lv1.setOnItemClickListener(new OnItemClickListener() {

    	@Override
    	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
    	Object o = lv1.getItemAtPosition(position);
    	feed newsData = (feed) o;
    	Toast.makeText(TimeLineActivity.this, "Selected :" + " " + newsData,
    	Toast.LENGTH_LONG).show();
    	}

    	});

    	}

    	private ArrayList<feed> getListData() {
    	ArrayList<feed> results = new ArrayList<feed>();
    	feed newsData = new feed();
    	newsData.setUserName("Looo1d45");
    	newsData.setCaption("«·«ﬂ· ⁄‰œÂ„ —ÊÊÊÊÊ⁄… „« ŒÌ· Â ﬂ–« ");
    	newsData.setDate("5 œ");
    	newsData.setImageFeed("http://distilleryimage8.ak.instagram.com/6b37e7c8ac8411e3af9d1246b2d85d90_8.jpg");
    	newsData.setUserImage("http://lh5.ggpht.com/_hepKlJWopDg/TB-_WXikaYI/AAAAAAAAElI/715k4NvBM4w/s144-c/IMG_0075.JPG");
    	results.add(newsData);

    	newsData = new feed();
    	newsData.setCaption("⁄·Ï «”«” «·»«—Õ… »‰—ÊÕ »” „««««Õ’··· ... Ì«—»ÌÂ");
    	newsData.setUserName("UU_kitty");
    	newsData.setDate("3 Ì");
    	newsData.setUserImage("http://lh4.ggpht.com/_4f1e_yo-zMQ/TCe5h9yN-TI/AAAAAAAAXqs/8X2fIjtKjmw/s144-c/IMG_1786.JPG");
    	newsData.setImageFeed("http://distilleryimage8.ak.instagram.com/06e98eacb5f811e3bfe71271cab09a7c_8.jpg");
    	results.add(newsData);

    	newsData = new feed();
    	newsData.setCaption("«·—Ì«÷…  √‰Ì» ÷„Ì— √Ò·≈·≈··≈›");
    	newsData.setUserName("Aminaah_548");
    	newsData.setDate("15 œ");
    	newsData.setUserImage("http://lh3.ggpht.com/_GEnSvSHk4iE/TDSfmyCfn0I/AAAAAAAAF8Y/cqmhEoxbwys/s144-c/_MG_3675.jpg");
    	newsData.setImageFeed("http://lh3.ggpht.com/_GEnSvSHk4iE/TDSfmyCfn0I/AAAAAAAAF8Y/cqmhEoxbwys/s144-c/_MG_3675.jpg");
    	results.add(newsData);

    	newsData = new feed();
    	newsData.setCaption("Life convict can`t claim freedom after 14 yrs: SC");
    	newsData.setUserName("talsmnv");
    	newsData.setDate("7 √”");
    	newsData.setUserImage("http://lh6.ggpht.com/_ZN5zQnkI67I/TCFFZaJHDnI/AAAAAAAABVk/YoUbDQHJRdo/s144-c/P9250508.JPG");
    	newsData.setImageFeed("http://lh6.ggpht.com/_ZN5zQnkI67I/TCFFZaJHDnI/AAAAAAAABVk/YoUbDQHJRdo/s144-c/P9250508.JPG");
    	results.add(newsData);

    	newsData = new feed();
    	newsData.setCaption("Indian Army refuses to share info on soldiers mutilated at LoC");
    	newsData.setUserName("vip.fdj");
    	newsData.setDate("3 Ì");
    	newsData.setUserImage("http://lh4.ggpht.com/_XjNwVI0kmW8/TCOwNtzGheI/AAAAAAAAC84/SxFJhG7Scgo/s144-c/0014.jpg");
    	newsData.setImageFeed("http://lh4.ggpht.com/_XjNwVI0kmW8/TCOwNtzGheI/AAAAAAAAC84/SxFJhG7Scgo/s144-c/0014.jpg");
    	results.add(newsData);

    	newsData = new feed();
    	newsData.setCaption("French soldier stabbed; link to Woolwich attack being probed");
    	newsData.setUserName("Jooovkdn");
    	newsData.setDate("3 Ì");
    	newsData.setUserImage("http://lh6.ggpht.com/_Nsxc889y6hY/TBp7jfx-cgI/AAAAAAAAHAg/Rr7jX44r2Gc/s144-c/IMGP9775a.jpg");
    	newsData.setImageFeed("http://lh6.ggpht.com/_Nsxc889y6hY/TBp7jfx-cgI/AAAAAAAAHAg/Rr7jX44r2Gc/s144-c/IMGP9775a.jpg");
    	results.add(newsData);

    	newsData = new feed();
    	newsData.setCaption("Life convict can`t claim freedom after 14 yrs: SC");
    	newsData.setUserName("7mooood");
    	newsData.setDate("3 Ì");
    	newsData.setUserImage("http://lh6.ggpht.com/_ZN5zQnkI67I/TCFFZaJHDnI/AAAAAAAABVk/YoUbDQHJRdo/s144-c/P9250508.JPG");
    	newsData.setImageFeed("http://lh6.ggpht.com/_ZN5zQnkI67I/TCFFZaJHDnI/AAAAAAAABVk/YoUbDQHJRdo/s144-c/P9250508.JPG");
    	results.add(newsData);

    	newsData = new feed();
    	newsData.setCaption("Dance of Democracy");
    	newsData.setUserName("ajsbhb");
    	newsData.setDate("3 Ì");
    	newsData.setUserImage("http://lh5.ggpht.com/_hepKlJWopDg/TB-_WXikaYI/AAAAAAAAElI/715k4NvBM4w/s144-c/IMG_0075.JPG");
    	newsData.setImageFeed("http://lh5.ggpht.com/_hepKlJWopDg/TB-_WXikaYI/AAAAAAAAElI/715k4NvBM4w/s144-c/IMG_0075.JPG");
    	results.add(newsData);

    	return results;
    	}
    	
    
}




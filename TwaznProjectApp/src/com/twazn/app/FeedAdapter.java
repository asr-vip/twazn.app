package com.twazn.app;

import java.util.ArrayList;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;
import com.twazn.app.R.id;
import com.twazn.app.feed;

public class FeedAdapter extends BaseAdapter {

	private ArrayList listData;
	private Context context;
	private LayoutInflater layoutInflater;
	DisplayMetrics metrics;
	int width;

	public FeedAdapter(Context context, ArrayList listData) {
		this.listData = listData;
		this.context = context;
		layoutInflater = LayoutInflater.from(context);
		DisplayMetrics metrics = context.getResources().getDisplayMetrics();
		 width = metrics.widthPixels;

	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.feed, null);
			holder = new ViewHolder();
			holder.userName = (TextView) convertView
					.findViewById(R.id.userName);
			holder.userImage = (ImageView) convertView
					.findViewById(R.id.userImage);
			holder.imageFeed = (ImageView) convertView
					.findViewById(R.id.imageFeed);

			holder.caption = (TextView) convertView.findViewById(R.id.etEmail);
			holder.time = (TextView) convertView.findViewById(id.time);
			holder.like = (ImageView) convertView.findViewById(R.id.imageLike);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		feed newsItem = (feed) listData.get(position);
		holder.userName.setText(newsItem.getUserName());
		holder.caption.setText(newsItem.getCaption());
		holder.time.setText(newsItem.getDate());

		if (holder.imageFeed != null) {
			Picasso.with(context).load(newsItem.getImageFeed())
					.resize(width, width).into(holder.imageFeed);

			// ImageLoader.getInstance().displayImage(newsItem.getImageFeed(),
			// holder.imageFeed);
			// new
			// ImageDownloaderTask(holder.imageFeed).execute(newsItem.getImageFeed());
		}
		if (holder.userImage != null) {
			new ImageDownloaderTask(holder.userImage).execute(newsItem
					.getUserImage());
		}

		holder.imageFeed.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				holder.like.setVisibility(View.VISIBLE);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    	holder.like.setVisibility(View.GONE);
                    }
                }, 100);

			}
		});
		return convertView;
	}

	static class ViewHolder {
		TextView userName;
		ImageView userImage;
		ImageView imageFeed;
		TextView caption;
		TextView time;
		ImageView like;
	}
}
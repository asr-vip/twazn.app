package com.twazn.app;

import java.util.Date;

public class feed {
private String userName;
private String userImage;
private String imageFeed;
private String caption;
private String date;

public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserImage() {
	return userImage;
}
public void setUserImage(String userImage) {
	this.userImage = userImage;
}
public String getImageFeed() {
	return imageFeed;
}
public void setImageFeed(String imageFeed) {
	this.imageFeed = imageFeed;
}
public String getCaption() {
	return caption;
}
public void setCaption(String caption) {
	this.caption = caption;
}
@Override
public String toString() {
	return "feed [userName=" + userName + ", userImage=" + userImage
			+ ", imageFeed=" + imageFeed + ", caption=" + caption + "]";
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}

}

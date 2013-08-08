package com.timvdalen.gizmooi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Represents a photo in the widget
 */
public class Photo{
	//* The URL to this photo
	private URL url;
	//* The photo's title, for attribution
	private String title;
	//* The photo's owner, for attribution
	private String owner;

	/**
	 * Creates a new Photo based on its attributes
	 * 
	 * @param url
	 * @param title
	 * @param owner
	 */
	public Photo(URL url, String title, String owner){
		this.url = url;
		this.title = title;
		this.owner = owner;
	}
	
	/**
	 * Creates a new Photo based on a Flickr API XML element of the form:
	 * 		<photo title="{title}" ownername="{owner}" url_o="{url}"/>
	 * @param parser
	 * @throws MalformedURLException when the Photo URL is malformed
	 */
	public Photo(XmlPullParser parser) throws MalformedURLException{
		if(!parser.getName().equals("photo")){
			throw new IllegalArgumentException("Passed XML argument isn't a <photo>");
		}
		this.url = new URL(parser.getAttributeValue(null, "url_o"));
		this.title = parser.getAttributeValue(null, "title");
		this.owner = parser.getAttributeValue(null, "ownername");
	}

	public URL getUrl(){
		return url;
	}

	public String getTitle(){
		return title;
	}

	public String getOwner(){
		return owner;
	}

	public void setUrl(URL url){
		this.url = url;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public void setOwner(String owner){
		this.owner = owner;
	}
	
	/**
	 * Gets a Bitmap representation of this Photo
	 * 
	 * @return Bitmap representation of this Photo
	 * @throws IOException if there is a network error
	 */
	public Bitmap getBitmap() throws IOException{
		return BitmapFactory.decodeStream(this.getUrl().openConnection().getInputStream());
	}
}

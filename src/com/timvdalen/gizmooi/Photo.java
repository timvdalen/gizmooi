package com.timvdalen.gizmooi;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import org.xmlpull.v1.XmlPullParser;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;

/**
 * Represents a photo in the widget
 */
public class Photo implements Serializable{
	//* For serialization
	private static final long serialVersionUID = 8941984070066448361L;
	//* The URL to this photo
	private URL url;
	//* The photo's title, for attribution
	private String title;
	//* The photo's owner, for attribution
	private String owner;
	//* The photo's license, for attribution
	private License license;

	/**
	 * Creates a new Photo based on its attributes
	 * 
	 * @param url
	 * @param title
	 * @param owner
	 * @param license
	 */
	public Photo(URL url, String title, String owner, License license){
		this.url = url;
		this.title = title;
		this.owner = owner;
		this.license = license;
	}
	
	/**
	 * Creates a new Photo based on a Flickr API XML element of the form:
	 * 		<photo title="{title}" ownername="{owner}" url_o="{url}" license="{license}"/>
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
		this.license = License.parseInt(Integer.parseInt(parser.getAttributeValue(null, "license")));
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

	public License getLicense(){
		return license;
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
	
	public void setLicense(License license){
		this.license = license;
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
	
	/**
	 * Gets a Bitmap representation of this Photo which fits in the screen
	 * 
	 * @return Bitmap representation of this Photo
	 * @throws IOException if there is a network error
	 */
	public Bitmap getBitmap(DisplayMetrics dm, int sample) throws IOException{
		int size = (dm.heightPixels > dm.widthPixels) ? dm.heightPixels : dm.widthPixels;
		size /= sample;
		
		URL image = new URL("http://timvdalen.nl/projects/gizmooi/image/getimage.php?photo=" + this.getUrl().toString() + "&size=" + Integer.toString(size));
		
		return BitmapFactory.decodeStream(image.openConnection().getInputStream());
	}
}

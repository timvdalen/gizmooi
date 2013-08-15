package com.timvdalen.gizmooi;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;

/**
 * Handles interaction with the Flickr API
 */
public class FlickrAPIHandler{
	// * The API key to use
	private static final String API_KEY = "";

	// * The parameters to send to Flickr
	private static final Map<String, String> values;
	static{
		values = new HashMap<String, String>();
		values.put("group_id", "78249294@N00");
		values.put("license", "4");
		values.put("safe_search", "1");
		values.put("content_type", "1");
		values.put("media", "photo");
		values.put("per_page", "1");
		values.put("extras", "owner_name,url_o");
	}
	
	/**
	 * Returns the API endpoint
	 * @return The API endpoint that is to be used
	 */
	private static String getEndpoint(){
		// Construct API endpoint
		String endpoint = "http://ycpi.api.flickr.com/services/rest/?method=flickr.photos.search";

		for(Map.Entry<String, String> entry : FlickrAPIHandler.values
				.entrySet()){
			endpoint += "&" + entry.getKey() + "=" + entry.getValue();
		}
		endpoint += "&api_key=" + FlickrAPIHandler.API_KEY;

		return endpoint;
	}

	/**
	 * Gets a Photo from Flickr
	 * 
	 * @return The newest Photo available, null if none is available
	 * @throws IOException
	 *             If the connection fails
	 * @throws XmlPullParserException
	 *             If the XML is not parsable
	 */
	public static Photo getPhoto() throws IOException, XmlPullParserException{
		// Download XML
		URL url = new URL(FlickrAPIHandler.getEndpoint());
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		InputStream in = new BufferedInputStream(urlConnection.getInputStream());

		// Parse XML
		XmlPullParser parser = Xml.newPullParser();
		parser.setInput(in, null);

		int eventType = parser.getEventType();
		while(eventType != XmlPullParser.END_DOCUMENT){
			if(eventType == XmlPullParser.START_TAG){
				if(parser.getName().equals("photo")){
					return new Photo(parser);
				}
			}
			eventType = parser.next();
		}

		return null;
	}
}

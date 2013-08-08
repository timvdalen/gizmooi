package com.timvdalen.gizmooi;

import java.io.IOException;

import org.xmlpull.v1.XmlPullParserException;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Runnable that loads a Photo and places it in a widget
 */
public class PhotoLoaderRunnable implements Runnable{
	//* The RemoveViews object to change the Photo of
	private RemoteViews views;
	
	//* The Context to use for creating Intents
	private Context context;
	
	//* The ID of the widget to update
	private int appWidgetId;
	
	//* The AppWidgetManager to update the widget on
	private AppWidgetManager appWidgetManager;

	public PhotoLoaderRunnable(RemoteViews views, Context context, int appWidgetId, AppWidgetManager appWidgetManager){
		this.views = views;
		this.context = context;
		this.appWidgetId = appWidgetId;
		this.appWidgetManager = appWidgetManager;
	}

	@Override
	public void run(){
		try{
			// Grab a Photo
			Photo p = FlickrAPIHandler.getPhoto();
			// Set the image
			this.views.setImageViewBitmap(R.id.imgMooi, p.getBitmap());
			
			// Setup click handler
			Intent intent = new Intent(this.context, ToastShowActivity.class);
			intent.putExtra("title", p.getTitle());
			intent.putExtra("owner", p.getOwner());
			PendingIntent pendingIntent = PendingIntent.getActivity(this.context, 0, intent, 0);
			
			this.views.setOnClickPendingIntent(R.id.imgMooi, pendingIntent);
			
			// Update the widget
			this.appWidgetManager.updateAppWidget(this.appWidgetId, this.views);
		}catch(IOException e){
			// We'll try again tomorrow, just use the Photo that's currently set
			return;
		}catch(XmlPullParserException e){
			// We'll try again tomorrow, just use the Photo that's currently set
			return;
		}

		// TODO: attribution
	}
}


package com.timvdalen.gizmooi;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

/**
 * Widget provider for Gizmooi
 */
public class GizmooiWidgetProvider extends AppWidgetProvider{
	//The Android Lint doesn't detect that I check for the right build version before using function from level 17
	@SuppressLint("NewApi")
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
		final int N = appWidgetIds.length;

		//Loop through all widgets
		for(int i = 0; i < N; i++){
			int appWidgetId = appWidgetIds[i];

			//Decide whether or not we're running on the lockscreen and set the base layout accordingly
			boolean isKeyguard;
			if(Build.VERSION.SDK_INT < 17){
				// Lockscreen widgets aren't supported so this is a homescreen widget
				isKeyguard = false;
			}else{
				Bundle myOptions = appWidgetManager.getAppWidgetOptions(appWidgetId);
				int category = myOptions.getInt(AppWidgetManager.OPTION_APPWIDGET_HOST_CATEGORY, -1);
				isKeyguard = (category == AppWidgetProviderInfo.WIDGET_CATEGORY_KEYGUARD);
			}

			int baseLayout = isKeyguard ? R.layout.lockscreen_layout : R.layout.widget_layout;

			//Get remote to view
			RemoteViews views = new RemoteViews(context.getPackageName(), baseLayout);
			views.setImageViewResource(R.id.imgMooi, R.drawable.ic_launcher);
			
			//Start a Runnable to load a new Photo
			new Thread(new PhotoLoaderRunnable(views, context, appWidgetId, appWidgetManager)).start();
		}
	}
}

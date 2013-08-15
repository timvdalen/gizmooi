package com.timvdalen.gizmooi;

import android.annotation.SuppressLint;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;

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
			
			//Start a Runnable to load a new Photo
			new Thread(new PhotoLoaderRunnable(context, appWidgetId, appWidgetManager)).start();
		}
	}
}

package com.timvdalen.gizmooi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Shows a Toast then closes.
 * 
 * I'd rather not actually start an Activity for this, it feels quite hacky, but there is no other way to handle click events from a widget at this point.
 * Maybe I'll change this to a detail view of the Photo with attribution info.
 */
public class ToastShowActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		
		// Get data from click Intent
		Intent intent = getIntent();
		Photo photo = (Photo) intent.getSerializableExtra("photo");
		
		// Show the attribution data
		String txtToast = photo.getTitle() + " / " + photo.getOwner() + " - " + photo.getLicense();
		
		Toast.makeText(getBaseContext(), txtToast, Toast.LENGTH_SHORT).show();
		
		finish();
	}
}

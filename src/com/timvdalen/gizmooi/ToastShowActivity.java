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
		
		String title = intent.getStringExtra("title");
		String owner = intent.getStringExtra("owner");
		
		String license = "CC-BY";
		
		// Show the attribution data
		String txtToast = title + " / " + owner + " - " + license;
		
		Toast.makeText(getBaseContext(), txtToast, Toast.LENGTH_SHORT).show();
		
		finish();
	}
}

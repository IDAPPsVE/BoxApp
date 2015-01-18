package com.boxapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MenuItem;

import com.boxapp.api.Api;

/**
 * Main Activity class
 */
public abstract class IAActivity extends FragmentActivity {
	public static final String PREFS_NAME = "preferences";

	private static Api api;

	@Override
	protected void onCreate(Bundle bundle) {
		// TODO Auto-generated method stub
		super.onCreate(bundle);
		api = Api.newInstance(this);
		System.out.println("HOLD");
		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);

	}

	public Api getApi() {
		return api;
	}

	/**
	 * set the standard action bar for the application
	 */
	public void setActionBarWithLogo() {
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(true);
		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(true);
		getActionBar().setIcon(
				new ColorDrawable(getResources().getColor(
						android.R.color.transparent)));
	}

	@Override
	public void onUserInteraction() {

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

		overridePendingTransition(android.R.anim.fade_in,
				android.R.anim.fade_out);
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		// getApi().preferencesEditor.putBoolean("needsToCheck", true);
		// getApi().preferencesEditor.commit();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// app icon in action bar clicked; goto parent activity.
			this.finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

}

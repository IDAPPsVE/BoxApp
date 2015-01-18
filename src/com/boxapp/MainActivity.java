package com.boxapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.boxapp.api.Api.NetworkMethod;

public class MainActivity extends IAActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button buttonlogin = (Button) findViewById(R.id.button1);
		buttonlogin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TwitterAsynctask task = new TwitterAsynctask();
				task.execute();
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	// clase destinada a abrir la vista 03 desde la vista principal
	public void VISTA03(View view) {
		Intent i = new Intent(this, vista03.class);
		startActivity(i);
	}

	public void VISTA04(View view) {
		Intent i = new Intent(this, Acti04.class);
		startActivity(i);
	}

	public void DATOSU(View view) {
		Intent i = new Intent(this, Acti04.class);
		startActivity(i);
	}

	public void PRINC(View view) {
		Intent i = new Intent(this, ONE1.class);
		startActivity(i);
	}

	private class TwitterAsynctask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {

			JSONObject json = new JSONObject();
			try {
				json.put("hola", 1);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String jsonString = getApi().makeHttpRequest(NetworkMethod.twitter,
					json).toString();
			System.out.println(jsonString);
			return null;
		}

	}
}

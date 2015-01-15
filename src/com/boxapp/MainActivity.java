package com.boxapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
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
		Intent i=new Intent(this, vista03.class);
		startActivity(i);
	}
	
	public void VISTA04(View view) {
		Intent i=new Intent(this, Acti04.class);
		startActivity(i);
	}
	
	public void DATOSU(View view) {
		Intent i=new Intent(this, Acti04.class);
		startActivity(i);
	}
	
	public void PRINC(View view) {
		Intent i=new Intent(this, ONE1.class);
		startActivity(i);
	}
}

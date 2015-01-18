package com.boxapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class ONE1 extends IAActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one1);
	}
	
	public void WOD(View view) {
		Intent i=new Intent(this, WOD.class);
		startActivity(i);
	}
	

}

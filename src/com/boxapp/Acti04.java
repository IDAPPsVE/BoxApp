package com.boxapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class Acti04 extends IAActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acti04);
	}

	// Inicio de la vista principal de la aplicacion
	public void PRINC1(View view) {
		Intent i=new Intent(this, ONE1.class);
		startActivity(i);
	}




}
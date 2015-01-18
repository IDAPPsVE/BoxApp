package com.boxapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;

public class WOD extends IAActivity implements OnItemSelectedListener {
	String pll = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wod);

		Spinner spi = (Spinner) findViewById(R.id.spinner1);

		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.HORARIOS, android.R.layout.simple_spinner_item);

		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		// Apply the adapter to the spinner
		spi.setAdapter(adapter);
		spi.setOnItemSelectedListener(this);
		spi.setPrompt("Seleccionar clase");

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int psion,
			long id) {

		TableLayout matrizservidor = (TableLayout) findViewById(R.id.matriz);
		Button botonwod = (Button) findViewById(R.id.button1);
		TextView coachname = (TextView) findViewById(R.id.textView4);
		TextView cupos = (TextView) findViewById(R.id.textView3);
		TextView listae = (TextView) findViewById(R.id.textView2);

		// Cadena de caracteres a llamar al servidor
		String[] horas = new String[] { "GREAT", "06:00 am", "07:00 am",
				"08:00 am", "09:00 am", "10:00 am", "11:00 am", "12:00 am",
				"01:00 pm", "02:00 pm", "03:00 pm", "04:00 pm", "05:00 pm",
				"06:00 pm", "07:00 pm", "08:00 pm" };

		// logica de spinner
		if (psion != 0) {

			// llamada de datos a internet
			GetMethodEX test = new GetMethodEX();
			String returned = null;
			try {
				returned = test.getInternetData();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pll = returned;

			matrizservidor.setVisibility(View.VISIBLE);
			botonwod.setVisibility(View.VISIBLE);
			coachname.setVisibility(View.VISIBLE);

			coachname.setText(pll);
			cupos.setText("Cupos disponibles");
			listae.setText(horas[psion]);
		}

		else {
			matrizservidor.setVisibility(View.GONE);
			botonwod.setVisibility(View.GONE);
			coachname.setVisibility(View.GONE);
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
	}

}

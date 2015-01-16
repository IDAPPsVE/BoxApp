package com.boxapp;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;


public class WOD extends Activity implements OnItemSelectedListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wod);
	
		Spinner spi = (Spinner) findViewById(R.id.spinner1);
		
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		R.array.HORARIOS, android.R.layout.simple_spinner_item);
		
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		// Apply the adapter to the spinner
		spi.setAdapter(adapter);
		spi.setOnItemSelectedListener(this);
		spi.setPrompt("Seleccionar clase");
	}
	
	public static String streamToString(InputStream is){
		
		BufferedReader br= null;
		StringBuilder sb=new StringBuilder();
		String line;
		
		try{
			
			br= new BufferedReader(new InputStreamReader(is));
			while((line =br.readLine()) !=null)
				{	
					sb.append(line);	
				} 
				
			}
		
		catch (IOException e) {
			
				e.printStackTrace();
			
			}
		
		finally {
			
			if(br !=null){
				
				try{
					br.close();
				}
				
				catch (IOException e){
					
					e.printStackTrace();
				
				}
			
				
			}
			
		}
		return sb.toString();
		
	}
	
	
	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int psion,long id) {
		
		TableLayout matrizservidor = (TableLayout) findViewById(R.id.matriz);
		Button botonwod = (Button) findViewById(R.id.button1);
		TextView coachname = (TextView) findViewById(R.id.textView4);
		TextView cupos = (TextView) findViewById(R.id.textView3);
		TextView listae = (TextView) findViewById(R.id.textView2);
		
		// Cadena de caracteres a llamar al servidor
		String[] horas = new String[]{"GREAT","06:00 am","07:00 am","08:00 am","09:00 am","10:00 am",
				"11:00 am","12:00 am","01:00 pm","02:00 pm","03:00 pm","04:00 pm","05:00 pm","06:00 pm","07:00 pm","08:00 pm"};
		
		//llamada al servidor
		String url = "https://infra-idappsve.c9.io/MaraBox";
		new CallAPI().execute(url);
		
		
	
		//logica de spinner
		if(psion!=0){
			
			matrizservidor.setVisibility(View.VISIBLE);
			botonwod.setVisibility(View.VISIBLE);
			coachname.setVisibility(View.VISIBLE);
			
			coachname.setText("Coach");
			cupos.setText("Cupos disponibles");
			listae.setText(horas[psion]);
		
		}
		
		else{
			
			matrizservidor.setVisibility(View.GONE);
			botonwod.setVisibility(View.GONE);
			coachname.setVisibility(View.GONE);
			
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
	}

	private class CallAPI extends AsyncTask<String, String, String>{

		@Override
		protected String doInBackground(String... params) {
			String urlString = params[0];
			String resultToDisplay = "";
			InputStream in = null;
			
			try
			{
				URL url = new URL(urlString);
				HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
				in = new BufferedInputStream(urlConnection.getInputStream());
				String resultado= streamToString(in);
				System.out.println(resultado);
				AlertDialog.Builder dialogo1 = new AlertDialog.Builder(getBaseContext());
				dialogo1.setMessage(resultado);
			}
			catch(Exception e)
			{
				return e.getMessage();
			}
			return resultToDisplay;
		}
		
		protected void onPostExecute(String result)
		{
			
		}
	}

	
}


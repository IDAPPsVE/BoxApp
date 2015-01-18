package com.boxapp;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.boxapp.api.Api.NetworkMethod;

public class WOD extends IAActivity implements OnItemSelectedListener {
	String pll = "";
	private Button mConfirm;
	boolean isConfirmed = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wod);

		Spinner spi = (Spinner) findViewById(R.id.spinner1);
		mConfirm = (Button) findViewById(R.id.button1);
		mConfirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!isConfirmed) {
					ConfirmTask task = new ConfirmTask();
					task.execute();

					isConfirmed = true;
				} else {

					CancelTask task = new CancelTask();
					task.execute();

					isConfirmed = false;
				}
			}
		});

		// Create an ArrayAdapter using the string array and a default spinner
		// layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.HORARIOS, R.layout.spinner_item);

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

		RelativeLayout matrizservidor = (RelativeLayout) findViewById(R.id.matriz);
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

	private class CancelTask extends AsyncTask<Void, Void, JSONObject> {
		ProgressDialog progDailog = null;

		protected void onPreExecute() {
			super.onPreExecute();
			// sets the progress dialog
			progDailog = new ProgressDialog(WOD.this);
			progDailog.setMessage("Fetching the data...");
			progDailog.setIndeterminate(true);
			progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progDailog.setCancelable(false);
			progDailog.show();
		}

		@Override
		protected JSONObject doInBackground(Void... params) {

			JSONObject json = new JSONObject();
			try {
				json.put("date", new Date());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject result = getApi().makeHttpRequest(NetworkMethod.confirm,
					json);
			return result;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progDailog.cancel();

			int code;
			try {
				code = result.getInt("code");
				if (code > 0) {
					mConfirm.setBackgroundColor(getResources().getColor(
							R.color.Green));
					mConfirm.setText("CONFIRM");

				} else {
					Toast.makeText(getApplicationContext(),
							result.getString("message"), Toast.LENGTH_LONG)
							.show();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Toast.makeText(
						getApplicationContext(),
						"There was a problem retreiving the data from the server",
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}

		}
	}

	private class ConfirmTask extends AsyncTask<Void, Void, JSONObject> {

		ProgressDialog progDailog = null;

		protected void onPreExecute() {
			super.onPreExecute();
			// sets the progress dialog
			progDailog = new ProgressDialog(WOD.this);
			progDailog.setMessage("Fetching the data...");
			progDailog.setIndeterminate(true);
			progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progDailog.setCancelable(false);
			progDailog.show();
		}

		@Override
		protected JSONObject doInBackground(Void... params) {

			JSONObject json = new JSONObject();
			try {
				json.put("date", new Date());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject result = getApi().makeHttpRequest(NetworkMethod.confirm,
					json);
			return result;
		}

		@Override
		protected void onPostExecute(JSONObject result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			progDailog.cancel();

			int code;
			try {
				code = result.getInt("code");
				if (code > 0) {
					mConfirm.setBackgroundColor(getResources().getColor(
							R.color.Red));
					mConfirm.setText("CANCEL");
				} else {
					Toast.makeText(getApplicationContext(),
							result.getString("message"), Toast.LENGTH_LONG)
							.show();
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Toast.makeText(
						getApplicationContext(),
						"There was a problem retreiving the data from the server",
						Toast.LENGTH_LONG).show();
				e.printStackTrace();
			}

		}
	}

}

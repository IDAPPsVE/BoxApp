package com.boxapp;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.boxapp.api.Api.NetworkMethod;

public class IALoginActivity extends IAActivity {
	private EditText mEmail, mPassword;
	private Button mSubmitButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acti04);
		mEmail = (EditText) findViewById(R.id.editText1);
		mPassword = (EditText) findViewById(R.id.editText2);
		mSubmitButton = (Button) findViewById(R.id.login_submit_button);
		mSubmitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				LoginTask task = new LoginTask();
				task.execute();
			}
		});
	}

	// Inicio de la vista principal de la aplicacion
	public void PRINC1(View view) {
		Intent i = new Intent(this, ONE1.class);
		startActivity(i);
	}

	private class LoginTask extends AsyncTask<Void, Void, JSONObject> {
		ProgressDialog progDailog = null;

		protected void onPreExecute() {
			super.onPreExecute();
			// sets the progress dialog
			progDailog = new ProgressDialog(IALoginActivity.this);
			progDailog.setMessage("Loggin in, Please wait");
			progDailog.setIndeterminate(true);
			progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			progDailog.setCancelable(false);
			progDailog.show();
		}

		@Override
		protected JSONObject doInBackground(Void... params) {

			JSONObject json = new JSONObject();
			try {
				json.put("email", mEmail.getText().toString());
				json.put("password", mPassword.getText().toString());
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONObject result = getApi().makeHttpRequest(NetworkMethod.login,json);
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

					Intent i = new Intent(getApplicationContext(), ONE1.class);
					startActivity(i);
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
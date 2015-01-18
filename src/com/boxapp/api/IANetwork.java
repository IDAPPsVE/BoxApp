package com.boxapp.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import org.json.JSONObject;

public class IANetwork {
	public IANetwork() {
		// TODO Auto-generated constructor stub
	}

	public JSONObject makeHttpRequest(String url, JSONObject json) {

		InputStream in = null;

		String result = null;
		JSONObject jsonResult = null;
		try {

			URL Url = new URL(url);
			// sets the security configuration for No SSL certificate to accept
			// the url.

			HttpsURLConnection
					.setDefaultHostnameVerifier(new NullHostNameVerifier());
			SSLContext context = SSLContext.getInstance("TLS");
			context.init(null,
					new X509TrustManager[] { new NullX509TrustManager() },
					new SecureRandom());
			HttpsURLConnection.setDefaultSSLSocketFactory(context
					.getSocketFactory());
			// makes the connection
			HttpsURLConnection conn = (HttpsURLConnection) Url.openConnection();
			conn.setRequestMethod("POST");
			conn.setReadTimeout(120000);
//			conn.setRequestProperty("Authorization", authToken);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			// send request

			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(json.toString());
			wr.flush();

			// gets the input stream.
			in = conn.getInputStream();
			result = streamToString(in);
			jsonResult = new JSONObject(result);

			return jsonResult;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new JSONObject();
	}

	// Reads an InputStream and converts it to a String.
	public static String streamToString(InputStream is) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(is));
		StringBuilder total = new StringBuilder();
		String line;
		while ((line = r.readLine()) != null) {
			total.append(line);
		}
		return total.toString();
	}

	private class NullHostNameVerifier implements HostnameVerifier {

		@Override
		public boolean verify(String hostname, SSLSession session) {
			// TODO Auto-generated method stub
			System.out.println("Approving  null certificate for " + hostname);
			return true;
		}

	}

	private class NullX509TrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub

		}

		@Override
		public void checkServerTrusted(X509Certificate[] chain, String authType)
				throws CertificateException {
			// TODO Auto-generated method stub

		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}

	}
}

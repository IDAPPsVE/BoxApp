package com.boxapp.api;

import org.json.JSONObject;

import android.content.Context;

public class Api {
	private static Api api;

	private static Context mAppContext;
	private static IANetwork mNetwork;
	private static final String ENDPOINT = "https://infra-idappsve.c9.io/MaraBox/";
	// private static final String ENDPOINT = "http://";

	public static Api getInstance() {
		return api;
	}

	public static Api get(Context c) {
		if (api == null) {
			api = new Api(c.getApplicationContext());
		}
		return api;
	}

	public static enum NetworkMethod {
		twitter
	}

	public static Api newInstance(Context context) {
		if (api == null) {
			api = new Api(context);
			mNetwork = new IANetwork();
		}
		return api;
	}

	public JSONObject makeHttpRequest(NetworkMethod method, JSONObject params) {
		String uri = ENDPOINT;

		switch (method) {
		case twitter:
			uri += "";
			break;
		}

		JSONObject result = mNetwork.makeHttpRequest(uri, params);
		return result;
	}

	private Api(Context appContext) {
		mAppContext = appContext;
	}

}

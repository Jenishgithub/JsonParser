package com.example.jsonparser;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
	private static DefaultHttpClient httpClient = new DefaultHttpClient();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		List<Club> wantClub = getNearestClubs();
		Log.d("jenish hakya", "mah favourite clubs are:" + wantClub);

		String randomData = "";
		randomData = wantClub.get(4).address;
		Log.d("jennsh ", "random address: " + randomData);

	}

	List<Club> getNearestClubs() {
		// YOUR URL GOES HERE
		String getUrl = "http://djs-corner.appspot.com/getClosestClubs?lat=40.7600624&lon=-73.98558";

		List<Club> ret = new ArrayList<Club>();

		HttpResponse response = null;
		HttpGet getMethod = new HttpGet(getUrl);
		try {
			response = httpClient.execute(getMethod);

			// CONVERT RESPONSE TO STRING
			String result = EntityUtils.toString(response.getEntity());

			// CONVERT RESPONSE STRING TO JSON ARRAY
			JSONArray ja = new JSONArray(result);

			// ITERATE THROUGH AND RETRIEVE CLUB FIELDS
			int n = ja.length();
			for (int i = 0; i < n; i++) {
				// GET INDIVIDUAL JSON OBJECT FROM JSON ARRAY

				JSONObject jo = ja.getJSONObject(i);

				// RETRIEVE EACH JSON OBJECT'S FIELDS
				long id = jo.getLong("id");
				String name = jo.getString("name");
				String address = jo.getString("address");
				String country = jo.getString("country");
				String zip = jo.getString("zip");
				double clat = jo.getDouble("lat");
				double clon = jo.getDouble("lon");
				String url = jo.getString("url");
				String number = jo.getString("number");

				// CONVERT DATA FIELDS TO CLUB OBJECT
				Club c = new Club(id, name, address, country, zip, clat, clon,
						url, number);
				ret.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// RETURN LIST OF CLUBS
		return ret;
	}
}

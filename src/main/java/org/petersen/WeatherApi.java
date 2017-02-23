package org.petersen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Wrapper for weather API
 *
 */
public class WeatherApi
{
  private String baseURL = "https://www.metaweather.com/api";
	
  public int getCurrentTemperature() throws IOException {
	// 2475687 is the Where On Earth IDentifier (WOEID) for Portland, Oregon, USA
    String endpoint = "/location/2475687/";
    URL apiURL = new URL(baseURL + endpoint);
    
    // Make the API request
	HttpsURLConnection connection = (HttpsURLConnection) apiURL.openConnection();
	BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	
	// Loop over the connection stream
	String currentLine;
	String responseBody = "";
	while ((currentLine = br.readLine()) != null) {
      responseBody = responseBody + currentLine;
    }
    br.close();
    
    // Now that we have the response body, convert it to JSON
    JSONObject responseJSON = new JSONObject(responseBody);
    JSONArray consolidateWeather = responseJSON.getJSONArray("consolidated_weather");
 
    // Return the most recent current temp in celsius
    return consolidateWeather.getJSONObject(consolidateWeather.length() - 1).getInt("the_temp");
  }
}

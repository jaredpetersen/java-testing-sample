package org.petersen;

/**
 * Personal helper robot
 *
 */
public class Robot
{
  private String name;

  public String checkWeather() {
    WeatherApi weatherApi = new WeatherApi();
    int currentTemperature = weatherApi.getCurrentTemperature();
    return "The current temperature is " + currentTemperature;
  }
  
  public void setName(String newName) {
	this.name = newName;
  }
  
  public String askName() {
    return "My name is " + this.name;
  }
}

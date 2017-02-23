package org.petersen;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for WeatherApiTest
 */
public class WeatherApiTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public WeatherApiTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( WeatherApiTest.class );
    }

    /**
     * Gets the current temperature
     */
    public void testGetCurrentTemperature()
    {
        WeatherApi weatherApi = new WeatherApi();
        int currentTemperature = weatherApi.getCurrentTemperature();
        assertEquals(67, currentTemperature);
    }
}

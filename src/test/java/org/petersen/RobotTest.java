package org.petersen;

import java.lang.reflect.Field;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 * Unit tests for Robot
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Robot.class)
public class RobotTest extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public RobotTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( RobotTest.class );
    }

    /**
     * Returns a string that describes the current weather
     * @throws Exception 
     */
    public void testGetCurrentTemperature() throws Exception
    {
        // Mock the currentWeatherApi
        WeatherApi weatherApiMock = Mockito.mock(WeatherApi.class);
        Mockito.when(weatherApiMock.getCurrentTemperature()).thenReturn(92);
        PowerMockito.whenNew(WeatherApi.class).withAnyArguments().thenReturn(weatherApiMock);

        // Perform the testing
        Robot robot = new Robot();
        String currentWeather = robot.checkWeather();
        assertEquals("The current temperature is " + 92, currentWeather);
    }
    
    /**
     * Sets the name of the robot
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws Exception 
     */
    public void testSetNameReflection() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
    {
        Robot robot = new Robot();
        robot.setName("Jimmy");
        
        // Set the field to public so that we can find the name
        final Field privateName = robot.getClass().getDeclaredField("name");
        privateName.setAccessible(true);
        
        assertEquals("Jimmy", privateName.get(robot));
    }
    
    /**
     * Returns a string that introduces the robot
     * @throws Exception 
     */
    public void testAskNameReflection() throws Exception
    {
    	Robot robot = new Robot();
        
        // Set the field to public so that we can set the name
        final Field privateName = robot.getClass().getDeclaredField("name");
        privateName.setAccessible(true);
        
        privateName.set(robot, "Ricky");
        
        assertEquals("My name is Ricky", robot.askName());
    }
    
    
}

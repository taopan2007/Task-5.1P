package sit707_week5;

import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class WeatherControllerTest {
    private static WeatherController wController;
    private static double[] hourlyTemperatures;

    @BeforeClass
    public static void setUpClass() {
        wController = WeatherController.getInstance();
        int nHours = wController.getTotalHours();
        hourlyTemperatures = new double[nHours];

        // Retrieve all the hours temperatures recorded as for today
        for (int i = 0; i < nHours; i++) {
            hourlyTemperatures[i] = wController.getTemperatureForHour(i + 1);
        }
    }

    @AfterClass
    public static void tearDownClass() {
        wController.close();
    }

    @Test
    public void testStudentIdentity() {
        String studentId = "221508095";
        assertNotNull("Student ID is null", studentId);
    }

    @Test
    public void testStudentName() {
        String studentName = "Tao Pan";
        assertNotNull("Student name is null", studentName);
    }

    @Test
    public void testTemperatureMin() {
        double minTemperature = calculateMinTemperature(hourlyTemperatures);
        assertEquals(wController.getTemperatureMinFromCache(), minTemperature, 0.0);
    }

    @Test
    public void testTemperatureMax() {
        double maxTemperature = calculateMaxTemperature(hourlyTemperatures);
        assertEquals(wController.getTemperatureMaxFromCache(), maxTemperature, 0.0);
    }

    @Test
    public void testTemperatureAverage() {
        double averageTemperature = calculateAverageTemperature(hourlyTemperatures);
        assertEquals(wController.getTemperatureAverageFromCache(), averageTemperature, 0.0);
    }

    private static double calculateMinTemperature(double[] temperatures) {
        double minVal = Double.MAX_VALUE;
        for (double temp : temperatures) {
            if (temp < minVal) {
                minVal = temp;
            }
        }
        return minVal;
    }

    private static double calculateMaxTemperature(double[] temperatures) {
        double maxVal = Double.MIN_VALUE;
        for (double temp : temperatures) {
            if (temp > maxVal) {
                maxVal = temp;
            }
        }
        return maxVal;
    }

    private static double calculateAverageTemperature(double[] temperatures) {
        double sum = 0;
        for (double temp : temperatures) {
            sum += temp;
        }
        return sum / temperatures.length;
    }
}
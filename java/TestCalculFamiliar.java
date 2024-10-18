import GE.Services.CalculFamiliar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCalculFamiliar {

    private CalculFamiliar calculFamiliar;

    @Before
    public void setUp() {
        // Initialize the service before each test
        calculFamiliar = new CalculFamiliar();
    }

    @Test
    public void testCalculateWithStatTrueAndNmbrLessThanThree() {
        // Test case where stat is true and number of children is less than or equal to 3
        int result = calculFamiliar.Calculate(true, 2);
        Assert.assertEquals(8400, result);
    }

    @Test
    public void testCalculateWithStatTrueAndNmbrGreaterThanThree() {
        // Test case where stat is true and number of children is greater than 3
        int result = calculFamiliar.Calculate(true, 5);
        Assert.assertEquals(8820, result); // Expected result 8000 + 600 + (2 * 110)
    }

    @Test
    public void testCalculateWithStatFalseAndNmbrLessThanThree() {
        // Test case where stat is false and number of children is less than or equal to 3
        int result = calculFamiliar.Calculate(false, 2);
        Assert.assertEquals(6600, result);
    }

    @Test
    public void testCalculateWithStatFalseAndNmbrGreaterThanThree() {
        // Test case where stat is false and number of children is greater than 3
        int result = calculFamiliar.Calculate(false, 5);
        Assert.assertEquals(7200, result); // Expected result 6000 + 900 + (2 * 150)
    }
}

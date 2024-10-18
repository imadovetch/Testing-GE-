import GE.Services.CongeService;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class CongeServiceTest {

    @Test
    public void testValidDateWithin2Months() {
        CongeService service = new CongeService();
        assertEquals("The date is valid.", service.checkTime("2024/12/10"));
    }

    @Test
    public void testDateMoreThan2MonthsInFuture() {
        CongeService service = new CongeService();
        assertEquals("The date is more than 2 months in the future.", service.checkTime("2025/01/10")); // More than 2 months in the future
    }

    @Test
    public void testDateInPast() {
        CongeService service = new CongeService();
        assertEquals("The date is in the past.", service.checkTime("2023/10/01")); // Past date
    }

    @Test
    public void testInvalidDateFormat() {
        CongeService service = new CongeService();
        assertEquals("Invalid date format. Please use yyyy/MM/dd.", service.checkTime("10/01/2024")); // Invalid format
    }

    @Test
    public void testExact2MonthsFromNow() {
        CongeService service = new CongeService();
        LocalDate futureDate = LocalDate.now().plusMonths(2);
        String formattedDate = futureDate.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        assertEquals("The date is valid.", service.checkTime(formattedDate)); // Exactly 2 months in the future
    }
}

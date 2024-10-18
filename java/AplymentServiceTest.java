import GE.Services.AplymentService;
import jakarta.xml.bind.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class AplymentServiceTest {

    private AplymentService aplymentService;

    @BeforeEach
    void setUp() {
        aplymentService = new AplymentService();
    }

    @Test
    void validateData_validInput_shouldPass() throws ValidationException {
        assertTrue(aplymentService.validateData(
                "Johnathon Doe",
                "john.doe@example.com",
                "pending",
                LocalDate.of(2023, 10, 1),
                "path/to/cv.pdf",
                "This is a motivation letter"
        ));
    }

    @Test
    void validateData_invalidName_shouldThrowException() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            aplymentService.validateData(
                    "John",  // Invalid name, less than 8 characters
                    "john.doe@example.com",
                    "pending",
                    LocalDate.of(2023, 10, 1),
                    "path/to/cv.pdf",
                    "This is a motivation letter"
            );
        });
        assertEquals("Name must be longer than 8 characters.", exception.getMessage());
    }

    @Test
    void validateData_invalidEmail_shouldThrowException() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            aplymentService.validateData(
                    "Johnathon Doe",
                    "invalid-email",  // Invalid email format
                    "pending",
                    LocalDate.of(2023, 10, 1),
                    "path/to/cv.pdf",
                    "This is a motivation letter"
            );
        });
        assertEquals("Invalid email format.", exception.getMessage());
    }


    @Test
    void validateData_invalidFileExtension_shouldThrowException() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            aplymentService.validateData(
                    "Johnathon Doe",
                    "john.doe@example.com",
                    "pending",
                    LocalDate.of(2023, 10, 1),
                    "path/to/cv.txt",  // Invalid file extension
                    "This is a motivation letter"
            );
        });
        assertEquals("CV must be a .pdf, .doc, or .docx file.", exception.getMessage());
    }

    @Test
    void validateData_emptyMotivationLetter_shouldThrowException() {
        Exception exception = assertThrows(ValidationException.class, () -> {
            aplymentService.validateData(
                    "Johnathon Doe",
                    "john.doe@example.com",
                    "pending",
                    LocalDate.of(2023, 10, 1),
                    "path/to/cv.pdf",
                    ""  // Empty motivation letter
            );
        });
        assertEquals("Motivation letter must not be empty.", exception.getMessage());
    }
}

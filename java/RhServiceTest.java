import GE.DAO.EmployeeDAO;
import GE.Services.UserService;
import GE.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RhServiceTest {

    private UserService userService;
    private EmployeeDAO<User> employeeDAO;

    @BeforeEach
    void setUp() {
        // Mocking EmployeeDAO for User
        employeeDAO = mock(EmployeeDAO.class);
        userService = new UserService(employeeDAO);
    }

    @Test
    void testFindByEmail_RhExists() {
        // Arrange
        Long userId = 1L;
        User mockUser = new User();
        mockUser.setId(userId);
        mockUser.setName("Test User");

        // Mock the behavior of EmployeeDAO
        when(employeeDAO.findById(userId)).thenReturn(mockUser);

        // Act
        User foundUser = userService.findById(userId);

        // Assert
        assertNotNull(foundUser);
        assertEquals(userId, foundUser.getId());
        assertEquals("Test User", foundUser.getName());
    }

    @Test
    void testFindById_UserDoesNotExist() {
        // Arrange
        Long userId = 2L;

        // Mock the behavior of EmployeeDAO to return null when user is not found
        when(employeeDAO.findById(userId)).thenReturn(null);

        // Act
        User foundUser = userService.findById(userId);

        // Assert
        assertNull(foundUser);
    }
}

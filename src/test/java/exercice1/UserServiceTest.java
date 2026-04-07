// src/test/java/exercise1/UserServiceTest.java
package exercice1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository; // Faux UserRepository créé par Mockito

    private UserService userService;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
    }

    @Test
    void testGetUserById_returnsCorrectUser() {
        // ARRANGE : définir ce que le mock doit retourner
        long userId = 1L;
        User mockUser = new User(userId, "Alice", "alice@email.com");
        when(userRepository.findUserById(userId)).thenReturn(mockUser);

        // ACT : appeler la méthode à tester
        User result = userService.getUserById(userId);

        // ASSERT : vérifier le résultat
        assertNotNull(result);
        assertEquals(userId, result.getId());
        assertEquals("Alice", result.getName());

        // VERIFY : vérifier que findUserById a bien été appelé avec le bon argument
        verify(userRepository, times(1)).findUserById(userId);
    }

    @Test
    void testGetUserById_userNotFound_returnsNull() {
        // ARRANGE
        when(userRepository.findUserById(99L)).thenReturn(null);

        // ACT
        User result = userService.getUserById(99L);

        // ASSERT
        assertNull(result);
        verify(userRepository).findUserById(99L);
    }
}
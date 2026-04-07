// src/test/java/exercise3/ProductServiceTest.java
package exercice3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductApiClient productApiClient;

    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = new ProductService(productApiClient);
    }

    // ✅ Scénario 1 : Récupération réussie
    @Test
    void testGetProduct_success() throws ApiException {
        // ARRANGE
        String productId = "PROD-123";
        Product mockProduct = new Product(productId, "Laptop", 999.99);
        when(productApiClient.getProduct(productId)).thenReturn(mockProduct);

        // ACT
        Product result = productService.getProduct(productId);

        // ASSERT
        assertNotNull(result);
        assertEquals("PROD-123", result.getId());
        assertEquals("Laptop", result.getName());
        assertEquals(999.99, result.getPrice());

        // VERIFY
        verify(productApiClient, times(1)).getProduct(productId);
    }

    // ❌ Scénario 2 : Format de données incompatible (produit null retourné)
    @Test
    void testGetProduct_invalidDataFormat_returnsNull() throws ApiException {
        // ARRANGE : l'API retourne null (données corrompues)
        String productId = "PROD-BAD";
        when(productApiClient.getProduct(productId)).thenReturn(null);

        // ACT
        Product result = productService.getProduct(productId);

        // ASSERT
        assertNull(result, "Le résultat devrait être null si l'API retourne null");
        verify(productApiClient).getProduct(productId);
    }

    // 💥 Scénario 3 : Échec d'appel API (exception réseau)
    @Test
    void testGetProduct_apiFailure_throwsException() throws ApiException {
        // ARRANGE : l'API lève une exception
        String productId = "PROD-ERR";
        when(productApiClient.getProduct(productId))
                .thenThrow(new ApiException("Service indisponible"));

        // ACT & ASSERT : vérifier que l'exception remonte bien
        ApiException exception = assertThrows(
                ApiException.class,
                () -> productService.getProduct(productId)
        );

        assertEquals("Service indisponible", exception.getMessage());
        verify(productApiClient).getProduct(productId);
    }

    // 🚫 Scénario 4 : ID invalide (validation côté service)
    @Test
    void testGetProduct_emptyId_throwsIllegalArgument() {
        // ACT & ASSERT
        assertThrows(
                IllegalArgumentException.class,
                () -> productService.getProduct("")
        );

        // VERIFY : l'API ne doit jamais être appelée avec un ID vide
        verifyNoInteractions(productApiClient);
    }
}
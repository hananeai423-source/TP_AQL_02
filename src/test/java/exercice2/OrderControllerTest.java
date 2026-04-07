// src/test/java/exercise2/OrderControllerTest.java
package exercice2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderControllerTest {

    @Mock
    private OrderService orderService; // Mock du service

    @Mock
    private OrderDao orderDao; // Mock du DAO

    private OrderController orderController;

    @BeforeEach
    void setUp() {
        orderController = new OrderController(orderService);
    }

    @Test
    void testCreateOrder_callsServiceWithCorrectOrder() {
        // ARRANGE
        Order order = new Order("ORD-001", "Laptop", 2);

        // ACT
        orderController.createOrder(order);

        // VERIFY : OrderService.createOrder appelé avec le bon objet
        verify(orderService, times(1)).createOrder(order);
    }

    @Test
    void testCreateOrder_serviceCallsDao() {
        // ARRANGE : OrderService réel qui utilise le mock OrderDao
        OrderService realService = new OrderService(orderDao);
        OrderController controller = new OrderController(realService);
        Order order = new Order("ORD-002", "Phone", 1);

        // ACT
        controller.createOrder(order);

        // VERIFY : OrderDao.saveOrder appelé avec le bon objet
        verify(orderDao, times(1)).saveOrder(order);
    }

    @Test
    void testCreateOrder_neverCalledWithNull() {
        // Vérifier que saveOrder n'est jamais appelé si on ne crée pas de commande
        verify(orderDao, never()).saveOrder(any());
    }
}
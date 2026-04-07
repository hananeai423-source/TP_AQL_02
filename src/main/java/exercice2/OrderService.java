package exercice2;

public class OrderService {

        private final OrderDao orderDao;

        public OrderService(OrderDao orderDao) {
            this.orderDao = orderDao;
        }

        public void createOrder(Order order) {
            // Logique métier possible ici (validation, etc.)
            orderDao.saveOrder(order);
        }
}

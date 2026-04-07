
package exercice2;

public class Order {
    private String orderId;
    private String product;
    private int quantity;

    public Order(String orderId, String product, int quantity) {
        this.orderId = orderId;
        this.product = product;
        this.quantity = quantity;
    }

    public String getOrderId() { return orderId; }
    public String getProduct() { return product; }
    public int getQuantity()   { return quantity; }
}
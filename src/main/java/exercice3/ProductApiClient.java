package exercice3;

public interface ProductApiClient {
    Product getProduct(String productId) throws ApiException;
}

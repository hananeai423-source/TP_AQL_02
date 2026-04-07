package exercice3;

public class ProductService {
    private final ProductApiClient productApiClient;

    public ProductService(ProductApiClient productApiClient) {
        this.productApiClient = productApiClient;
    }

    public Product getProduct(String productId) throws ApiException {
        if (productId == null || productId.isEmpty()) {
            throw new IllegalArgumentException("Product ID ne peut pas être vide");
        }
        return productApiClient.getProduct(productId);
    }

}

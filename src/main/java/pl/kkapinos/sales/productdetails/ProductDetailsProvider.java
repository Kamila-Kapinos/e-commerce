package pl.kkapinos.sales.productdetails;

import java.util.Optional;

public interface ProductDetailsProvider {

    public Optional<ProductDetails> load(String productId);

}
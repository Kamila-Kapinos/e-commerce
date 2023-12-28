package pl.kkapinos.productcatalog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CatalogController {

    private ProductCatalog productCatalog;

    CatalogController(ProductCatalog productCatalog) {
        this.productCatalog = productCatalog;
    }

    @GetMapping("/api/products")
    List<Product> allProducts() {
        return productCatalog.allPublishedProducts();
    }

    @GetMapping("/api/products/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable String productId) {
        Optional<Product> productOptional = Optional.ofNullable(productCatalog.loadById(productId));

        return productOptional
                .map(product -> new ResponseEntity<>(product, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/api/products/create")
    public ResponseEntity<String> addProduct(@RequestParam String name, @RequestParam String desc) {
        try {
            String productId = productCatalog.addProduct(name, desc);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product added successfully. Product ID: " + productId);
        } catch (ProductCantBePublishedException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product cannot be published.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add product.");
        }
    }
}

package pl.kkapinos.productcatalog;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

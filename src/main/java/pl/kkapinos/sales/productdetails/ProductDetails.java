package pl.kkapinos.sales.productdetails;

import java.math.BigDecimal;

public class ProductDetails {
    private final String id;
    private final String name;
    private final BigDecimal price;

    private final String type;

    public ProductDetails(String id, String name, BigDecimal price, String type) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getType() {
        return type;
    }

}

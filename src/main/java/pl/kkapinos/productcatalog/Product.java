package pl.kkapinos.productcatalog;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private final String uuid;
    private final String name;
    private final String desc;
    private BigDecimal price;
    private String image;
    private boolean online;
    private String type;

    public Product(UUID uuid, String name, String desc, String type) {
        this.uuid = uuid.toString();
        this.name = name;
        this.desc = desc;
        this.type = type;
    }

    public String getId() {
        return uuid;
    }

    public UUID getUUID() {
        return UUID.fromString(uuid);
    }

    public String getName() {
        return name;
    }

    public void changePrice(BigDecimal newPrice) {
        price = newPrice;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imageKey) {

        image = imageKey;
    }

    public void setOnline(boolean online) {

        this.online = online;
    }

    public boolean getOnline() {
        return online;
    }

    public String getType(){ return type; }
}

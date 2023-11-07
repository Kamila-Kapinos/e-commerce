package pl.kkapinos.payu;

public class OrderCreateResponse {

    private String redirectUri;
    private String orderId;
    private String extOrderId;

    public OrderCreateResponse() {}

    public String getRedirectUri() {
        return redirectUri;
    }

    public OrderCreateResponse setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public OrderCreateResponse setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getExtOrderId() {
        return extOrderId;
    }

    public OrderCreateResponse setExtOrderId(String extOrderId) {
        this.extOrderId = extOrderId;
        return this;
    }
}

package grandmasfood.client.domain.models;


import lombok.Data;

@Data
public class Client {
    private String document;
    private String fullName;
    private String email;
    private String phone;
    private String deliveryAddress;
}


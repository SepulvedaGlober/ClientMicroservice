package grandmasfood.client.domain.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    private String document;
    private String fullName;
    private String email;
    private String phone;
    private String deliveryAddress;
}


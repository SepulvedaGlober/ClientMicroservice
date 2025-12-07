package grandmasfood.client.infrastructure.jpa.entity;

import jakarta.persistence.*;
import lombok.Data;

import static grandmasfood.client.utils.Constants.*;

@Entity
@Table(name = "client")
@Data
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "document", unique = true, nullable = false, updatable = false)
    private String document;
    @Column(name = "full_name", nullable = false, length = MAX_FULL_NAME_LENGTH)
    private String fullName;
    @Column(name = "email", nullable = false, length = MAX_EMAIL_LENGTH)
    private String email;
    @Column(name = "phone", nullable = false, length = MAX_PHONE_LENGTH)
    private String phone;
    @Column(name = "address", nullable = false, length = MAX_ADDRESS_LENGTH)
    private String deliveryAddress;
}

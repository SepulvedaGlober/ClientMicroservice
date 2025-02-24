package grandmasfood.client.infrastructure.jpa.repository;

import grandmasfood.client.infrastructure.jpa.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IClientRepository extends JpaRepository<ClientEntity, Long> {
    Optional<ClientEntity> findByDocument(String document);
}

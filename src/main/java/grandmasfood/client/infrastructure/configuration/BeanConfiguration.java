package grandmasfood.client.infrastructure.configuration;


import grandmasfood.client.domain.api.IClientServicePort;
import grandmasfood.client.domain.spi.IClientPersistencePort;
import grandmasfood.client.domain.usecase.ClientUseCase;
import grandmasfood.client.infrastructure.jpa.adapter.ClientJpaAdapter;
import grandmasfood.client.infrastructure.jpa.mapper.ClientEntityMapper;
import grandmasfood.client.infrastructure.jpa.repository.IClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class BeanConfiguration {

    private final IClientRepository clientRepository;
    private final ClientEntityMapper clientEntityMapper;

    @Bean
    public IClientServicePort clientServicePort() {
        return new ClientUseCase(clientPersistencePort());
    }

    @Bean
    public IClientPersistencePort clientPersistencePort() {
        return new ClientJpaAdapter(clientRepository, clientEntityMapper);
    }

}

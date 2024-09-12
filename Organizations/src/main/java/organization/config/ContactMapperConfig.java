package organization.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import organization.mapper.ContactMapper;

@Configuration
public class ContactMapperConfig {
    @Bean
    public ContactMapper contactMapper() {
        return Mappers.getMapper(ContactMapper.class);
    }

}


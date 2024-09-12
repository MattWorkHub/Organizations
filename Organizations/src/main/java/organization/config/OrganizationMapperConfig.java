package organization.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import organization.mapper.OrganizationMapper;

@Configuration
public class OrganizationMapperConfig {
    @Bean
    public OrganizationMapper organizationMapper() {
        return Mappers.getMapper(OrganizationMapper.class);
    }

}
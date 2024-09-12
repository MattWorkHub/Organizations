package organization.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;
import organization.dto.ContactDto;
import organization.entity.Contact;



@Mapper( nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper( ContactMapper.class );

    ContactDto entityToDto(Contact contact);
    Contact dtoToEntity(ContactDto contactDto);
}
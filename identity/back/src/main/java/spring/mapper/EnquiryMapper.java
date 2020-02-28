package spring.mapper;

import org.mapstruct.Mapper;
import spring.dto.EnquiryDto;
import spring.model.Enquiry;

@Mapper(componentModel = "spring")
public interface EnquiryMapper {

    EnquiryDto enquiryToEnquiryDto(Enquiry enquiry);

    Enquiry dtoToEnquiry(EnquiryDto enquiryDto);
}

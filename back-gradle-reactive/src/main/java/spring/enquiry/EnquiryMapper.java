package spring.enquiry;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnquiryMapper {

    EnquiryDto enquiryToEnquiryDto(Enquiry enquiry);

    Enquiry dtoToEnquiry(EnquiryDto enquiryDto);
}

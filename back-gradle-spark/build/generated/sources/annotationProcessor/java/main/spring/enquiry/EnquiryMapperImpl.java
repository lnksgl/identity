package spring.enquiry;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-03-22T23:51:41+0300",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.2 (Oracle Corporation)"
)
@Component
public class EnquiryMapperImpl implements EnquiryMapper {

    @Override
    public EnquiryDto enquiryToEnquiryDto(Enquiry enquiry) {
        if ( enquiry == null ) {
            return null;
        }

        EnquiryDto enquiryDto = new EnquiryDto();

        return enquiryDto;
    }

    @Override
    public Enquiry dtoToEnquiry(EnquiryDto enquiryDto) {
        if ( enquiryDto == null ) {
            return null;
        }

        Enquiry enquiry = new Enquiry();

        return enquiry;
    }
}

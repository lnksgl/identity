package spring.controller;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.dto.EnquiryDto;
import spring.service.EnquiryService;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/v1/enquiry")
public class EnquiryController {

    @Autowired
    EnquiryService enquiryService;

    @PostMapping
    public void createEnquiry(@RequestBody EnquiryDto enquiryDto) {
        enquiryService.createEnquiry(enquiryDto);
    }
}

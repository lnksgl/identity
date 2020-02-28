package spring.controller;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.Delegate;
import lombok.experimental.FieldDefaults;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.dto.EnquiryDto;
import spring.dto.GroupDto;
import spring.service.EnquiryService;

import java.util.List;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/api/v1/enquiry")
public class EnquiryController {

    EnquiryService enquiryService;

    @PostMapping
    public void createEnquiry(@RequestBody EnquiryDto enquiryDto) {
        enquiryService.createEnquiry(enquiryDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteEnquiry(@PathVariable Long id) {
        enquiryService.deleteEnquiry(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EnquiryDto>> showAllEnquiries() {
        return new ResponseEntity<>(enquiryService.showAllEnquiries(), HttpStatus.OK);
    }
}

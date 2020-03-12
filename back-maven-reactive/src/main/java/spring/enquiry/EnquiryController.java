package spring.enquiry;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

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
    public void deleteEnquiry(@PathVariable Long id) {
        enquiryService.deleteEnquiry(id);
    }

    @GetMapping
    public Flux<EnquiryDto> showAllEnquiries() {
        return enquiryService.showAllEnquiries();
    }
}

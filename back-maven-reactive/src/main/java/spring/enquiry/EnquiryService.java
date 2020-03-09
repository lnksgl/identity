package spring.enquiry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.group.GroupService;
import spring.user.UserService;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"service"})
public class EnquiryService {

    EnquiryRepository enquiryRepository;
    GroupService groupService;
    UserService userService;
    EnquiryMapper enquiryMapper;
    EnquiryMathOperations enquiryMathOperations;

    @Transactional
    public void createEnquiry(EnquiryDto enquiryDto) {
        Enquiry enquiry = mapFromDtoToEnquiry(enquiryDto);
        saveEnquiry(enquiry);

        Flux<Enquiry> enquiryList = readByIdGroups(enquiry.getIdGroups());

        if (checkMinEvaluation(enquiryList)) {
            groupService.updateAverage(enquiryMathOperations.minEvaluation(enquiryList), enquiry.getIdGroups());
        }
    }

    @Transactional
    public void saveEnquiry(Enquiry enquiry) {
        enquiryRepository.save(enquiry);
    }

    private boolean checkMinEvaluation(Flux<Enquiry> enquiries) {
        return Objects.requireNonNull(enquiries.count().block()).intValue() > 25;
    }

    @Transactional
    public void deleteEnquiry(long id) {
        enquiryRepository.deleteById(id);
    }

    @Cacheable
    public Flux<EnquiryDto> showAllEnquiries() {
        return enquiryRepository.findAll().map(this::mapFromEnquiryToDto);
    }

    @Transactional
    public Flux<Enquiry> readByIdGroups(Long id) {
        return enquiryRepository.findByIdGroups(id);
    }

    //refactoring block
    public Enquiry mapFromDtoToEnquiry(EnquiryDto enquiryDto) {
        Enquiry enquiry = new Enquiry();
        enquiry.setIdUsers(Objects.requireNonNull(userService.showUsername(enquiryDto.getUsername()).block()).getId());
        enquiry.setIdGroups(Objects.requireNonNull(groupService.showNameGroup(enquiryDto.getName()).block()).getId());
        enquiry.setCreatedOn(Instant.now());

        return enquiry;
    }

    public EnquiryDto mapFromEnquiryToDto(Enquiry enquiry) {
        return enquiryMapper.enquiryToEnquiryDto(enquiry);
    }
}

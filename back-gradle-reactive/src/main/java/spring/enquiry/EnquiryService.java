package spring.enquiry;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import spring.group.Group;
import spring.group.GroupDto;
import spring.group.GroupService;
import spring.user.User;
import spring.user.UserService;

import java.time.Instant;
import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@AllArgsConstructor
public class EnquiryService {

    EnquiryRepository enquiryRepository;
    GroupService groupService;
    UserService userService;
    EnquiryMapper enquiryMapper;
    EnquiryMathOperations enquiryMathOperations;

    public void createEnquiry(EnquiryDto enquiryDto) {
        Enquiry enquiry = mapFromDtoToEnquiry(enquiryDto);
        saveEnquiry(enquiry);

        if (enquiryRepository.countByIdGroups(enquiry.getIdGroups()) > 25) {
            groupService.updateAverage(0, enquiry.getIdGroups()); //enquiryMathOperations.minEvaluation(enquiryList)
        }
    }

    public void saveEnquiry(Enquiry enquiry) {
        enquiryRepository.save(enquiry).subscribe();
    }

    private boolean checkMinEvaluation(Flux<Enquiry> enquiries) {
        return Objects.requireNonNull(enquiries.count().block()).intValue() > 25;
    }

    public void deleteEnquiry(long id) {
        enquiryRepository.deleteById(id).subscribe();
    }

    public Flux<EnquiryDto> showAllEnquiries() {
        return enquiryRepository.findAll().map(this::mapFromEnquiryToDto);
    }

    public Enquiry mapFromDtoToEnquiry(EnquiryDto enquiryDto) {
        Enquiry enquiry = new Enquiry();
        User user = userService.mapFromMonoUserAverageToUser(userService.showUsername(enquiryDto.getUsername()));
        GroupDto group = groupService.mapFromMonoGroupToDto(groupService.readNameGroup(enquiryDto.getName()));

        enquiry.setIdUsers(user.getId());
        enquiry.setIdGroups(group.getId());
        enquiry.setCreatedOn(Instant.now());

        return enquiry;
    }

    public EnquiryDto mapFromEnquiryToDto(Enquiry enquiry) {
        return enquiryMapper.enquiryToEnquiryDto(enquiry);
    }
}

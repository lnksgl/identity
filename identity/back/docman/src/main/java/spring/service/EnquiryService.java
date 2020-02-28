package spring.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dto.EnquiryDto;
import spring.mapper.GroupMapper;
import spring.model.Enquiry;
import spring.model.User;
import spring.repository.EnquiryRepository;
import spring.repository.GroupRepository;
import spring.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@Transactional(readOnly = true)
@CacheConfig(cacheNames = {"service"})
public class EnquiryService {

    @Autowired
    EnquiryRepository enquiryRepository;
    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public void createEnquiry(EnquiryDto enquiryDto) {
        Enquiry enquiry = new Enquiry();
        enquiry.setIdGroups(groupRepository.findByName(enquiryDto.getName()).get(0).getId());
        enquiry.setIdUsers(userRepository.findByUsername(enquiryDto.getUsername()).get().getId());
        enquiryRepository.save(enquiry);

        groupRepository.updateGroup("0", enquiry.getId());

        List<Enquiry> enquiryList = enquiryRepository.findByIdGroups(enquiry.getIdGroups());
        double[] numbers = new double[enquiryList.size()];
        if (enquiryList.size() > 25) {
            for(int i = 0; i < enquiryList.size() - 1; i++) {
                numbers[i] = Double.parseDouble(userRepository.findById(enquiryList.get(i).getIdUsers()).get().getScanDocument());
            }
            Arrays.sort(numbers);

            groupRepository.updateGroup(String.valueOf(numbers[24]), enquiry.getIdGroups());
        }
    }
}

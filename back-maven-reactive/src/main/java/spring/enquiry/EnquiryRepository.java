package spring.enquiry;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EnquiryRepository extends ReactiveCrudRepository<Enquiry, Long> {

    @Query("select count(*) from enquiry where id = :id")
    Long countByIdGroups(Long id);
}

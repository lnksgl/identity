package spring.enquiry;

import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import spring.group.Group;

import java.util.List;

public interface EnquiryRepository extends ReactiveCrudRepository<Enquiry, Long> {

    @Query("select * from enquiry where id = :id")
    Flux<Enquiry> findByIdGroups(Long id);
}

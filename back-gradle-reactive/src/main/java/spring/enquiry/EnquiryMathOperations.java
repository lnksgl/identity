package spring.enquiry;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import spring.user.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Component
public class EnquiryMathOperations {

    UserRepository userRepository;

    public double minEvaluation(Flux<Enquiry> enquiries) {
        int count = Objects.requireNonNull(enquiries.count().block()).intValue();
        List<Enquiry> list = enquiries.collectList().block();
        double[] evaluations = new double[count];

        for (int i = 0; i < count - 1; i++) {
            evaluations[i] = parseEvaluation(list.get(i).getIdUsers());
        }

        Arrays.sort(evaluations);

        return evaluations[24];
    }

    public double parseEvaluation(Long id) {
        return Double.parseDouble(Objects.requireNonNull(userRepository.findById(id).block()).getScanDocument());
    }

    public double average(String[] evaluations) {
        int sum = 0;

        for (String evaluation : evaluations) {
            sum += Integer.decode(evaluation);
        }

        return sum / (double) evaluations.length;
    }
}

package spring.math;

import lombok.AllArgsConstructor;
import spring.model.Enquiry;
import spring.service.UserService;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
public class MathOperations {

    UserService userService;

    public double minEvaluation(List<Enquiry> enquiryList) {
        double[] evaluations = new double[enquiryList.size()];

        for (int i = 0; i < enquiryList.size() - 1; i++) {
            evaluations[i] = parseEvaluation(enquiryList.get(i).getIdUsers());
        }

        Arrays.sort(evaluations);

        return evaluations[24];
    }

    public double parseEvaluation(Long id) {
        return Double.parseDouble(userService.readSingleUser(id).getScanDocument());
    }

    public double average(String[] evaluations) {
        int sum = 0;

        for (String evaluation : evaluations) {
            sum += Integer.decode(evaluation);
        }

        return sum / (double) evaluations.length;
    }
}

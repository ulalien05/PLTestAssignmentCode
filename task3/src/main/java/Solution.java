import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    private static Layout commonLayout;
    private static Value resultValues;

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        File layout = new File(args[0]);
        File values = new File(args[1]);
        File report = new File("report.json");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(report))) {
            report.createNewFile();
            resultValues = objectMapper.readValue(values, new TypeReference<>() {
            });
            commonLayout = objectMapper.readValue(layout, new TypeReference<>() {
            });
            List<Test> testLayout = commonLayout.getTests();
            List<Test> testValues = resultValues.getValues();
            for (Test v : testValues) {
                List <Test> found = findTestById(testLayout, v.getId());
                if (found != null)
                    found.forEach(t -> t.setValue(v.getValue()));
            }
            Layout result = new Layout(testLayout);
            String json = objectMapper.writeValueAsString(result);
            writer.write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List <Test> findTestById(List<Test> testLayout, int id) {
        if (testLayout.stream().anyMatch(t -> t.getId() == id)) {
            return testLayout.stream().filter(t -> t.getId() == id).collect(Collectors.toList());
        } else {
            return testLayout.stream()
                    .filter(t -> t.getValues() != null)
                    .flatMap(t -> findTestById(t.getValues(), id).stream())
                    .collect(Collectors.toList());
        }
    }
}

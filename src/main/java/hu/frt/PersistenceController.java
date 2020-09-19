package hu.frt;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class PersistenceController {
    @PostMapping("institution-save")
    public void save(@RequestBody EducationalInstitution educationalInstitution){
        System.out.println("institution-save");
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("C:\\Users\\Halacska-NB4\\dev\\educational-institution\\educational_institution.json");
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            objectMapper.writeValue(out, educationalInstitution);
            out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));
            out.write("\r\n");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("institution-read")
    public List<EducationalInstitution> read(@RequestParam String omIdentificationNumber){
        List<EducationalInstitution> educationalInstitutions = new ArrayList<>();

        try {
            File file = new File("C:\\Users\\Halacska-NB4\\dev\\educational-institution\\educational_institution.json");

            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()){
                String nextLine = sc.nextLine();
                ObjectMapper mapper = new ObjectMapper();
                EducationalInstitution value = mapper.readValue(nextLine, EducationalInstitution.class);
                if (value.getInstitutionData().getOmIdentificationNumber().equals(omIdentificationNumber))
                    educationalInstitutions.add(value);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        return educationalInstitutions;
    }
}

package hu.frt;

import com.fasterxml.jackson.databind.ObjectMapper;
import hu.frt.EducationalInstitution;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

@RestController
public class PersistenceController {
    @PostMapping("institution-save")
    public void save(@RequestBody EducationalInstitution educationalInstitution){
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            File file = new File("C:\\Users\\Halacska-NB4\\dev\\educational-institution\\educatuinal_institution.json");
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
}

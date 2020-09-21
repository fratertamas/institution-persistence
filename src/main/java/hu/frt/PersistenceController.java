package hu.frt;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ExampleProperty;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RestController
public class PersistenceController {
    @ApiOperation(value = "Oktatási intézmény adatainak mentése")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "contents",
                    dataTypeClass = EducationalInstitution.class,
                    examples = @io.swagger.annotations.Example(
                            value = {
                                    @ExampleProperty(value = "{\n" +
                                            "  \"headOfInstitutionData\": {\n" +
                                            "    \"firstName\": \"Vargáné Vincze\",\n" +
                                            "    \"phoneNumber\": \"06303316023 \",\n" +
                                            "    \"surname\": \"Tímea\"\n" +
                                            "  },\n" +
                                            "  \"institutionData\": {\n" +
                                            "    \"name\": \"Sennyey Elza Általános Iskola\",\n" +
                                            "    \"numberOfTaskLocation\": 2,\n" +
                                            "    \"omIdentificationNumber\": \"033488\",\n" +
                                            "    \"status\": \"aktív\",\n" +
                                            "    \"taxNumber\": \" 15835231-2-15\",\n" +
                                            "    \"type\": \"Általános iskola\"\n" +
                                            "  }\n" +
                                            "}", mediaType = "application/json")
                            })
            )
    })
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

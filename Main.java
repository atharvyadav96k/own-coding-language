// Auther: Atharv Sanjay Yadav
// Custome Coding langauge

import Evalaturs.Evaluate;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) {
        Path filePath = Paths.get(args[0]);
        try {
            String code = new String(Files.readAllBytes(filePath));
            new Evaluate(code);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

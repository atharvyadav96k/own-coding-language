import Evalaturs.Evaluate;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
public class Main {
    public static void main(String[] args) {
        Path filePath = Paths.get("code.da");
        try {
            String code = new String(Files.readAllBytes(filePath));
            Evaluate e = new Evaluate(code);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

import Evalaturs.Evaluate;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        Path filePath = Paths.get(str);
        try {
            String code = new String(Files.readAllBytes(filePath));
            new Evaluate(code);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        ImageRepository ImageRepository = new ImageRepository();

        // Creating an example image
        List<String> tags = List.of("TF2", "inginiir");
        Path location = Paths.get("C:/Users/radud/Documents/GitHub/AdvancedProgramming_2025/Lab5/HW5/The_Engineer.jpg"); // Update this path
        Image image = new Image("inginer", LocalDate.now(), tags, location.toFile());

        ImageRepository.addImage(image);

        // Displaying the image
        try {
            ImageRepository.displayImage(image);
            System.out.println("Image displayed successfully.");
        } catch (IOException e) {
            System.err.println("Error opening image: " + e.getMessage());
        }
        Shell shell = new Shell();
        shell.start();
    }
}
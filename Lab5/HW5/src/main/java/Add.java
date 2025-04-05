import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class Add {
    public void handleAddCommand(String[] parts) {
        String name = parts[1].toLowerCase();
        LocalDate date = LocalDate.parse(parts[2]);
        List<String> tags = List.of(parts[3].split(","));
        File location = new File(parts[4]);
        ImageRepository.addImage(new Image(name, date, tags, location));
        System.out.println("Image '" + name + "' added.");
    }
}

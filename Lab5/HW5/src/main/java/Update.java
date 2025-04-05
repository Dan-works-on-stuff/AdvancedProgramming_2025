import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;

public class Update {
    public void handleUpdateCommand(String[] parts) {
        String name = parts[1];
        ImageRepository imageRepository = new ImageRepository();
        Image oldImage = imageRepository.getImageByName(name);
        if (oldImage == null) {
            throw new IllegalArgumentException("Image not found: " + name);
        }
        String attribute = parts[2].toLowerCase();
        Image newImage = getImage(parts, attribute, oldImage);
        imageRepository.removeImage(oldImage);
        ImageRepository.addImage(newImage);
        System.out.println("Updated " + attribute + " for '" + name + "'.");
    }
    private Image getImage(String[] parts, String attribute, Image oldImage) {
        String value = parts[3];
        return switch (attribute){
            case "name" -> new Image(value, oldImage.date(), oldImage.tags(), oldImage.location());
            case "date" -> new Image(value, LocalDate.parse(value), oldImage.tags(), oldImage.location());
            case "tags" -> new Image(value, oldImage.date() , Arrays.asList(value.split(",")), oldImage.location());
            case "location" -> new Image(value, oldImage.date(), oldImage.tags(), new File(value));
            default -> throw new IllegalArgumentException("Invalid attribute. Use date, tags, or location.");
        };
    }
}


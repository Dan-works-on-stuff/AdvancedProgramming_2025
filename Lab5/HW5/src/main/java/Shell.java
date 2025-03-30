import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Shell {
    private String command;
    private static final ImageRepository imageRepository = new ImageRepository();

    public void readCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter command: ");
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();
            if (command.isEmpty()) {
                continue;
            }
            String[] parts = command.split("\\s+");
            String key = parts[0].toLowerCase();
            try {
                switch (key) {
                    case "exit":
                        System.out.println("Goodbye!");
                        scanner.close();
                        return;
                    case "add":
                        handleAddCommand(parts);
                        break;
                    case "remove":
                        handleRemoveCommand(parts);
                        break;
                    case "update":
                        handleUpdateCommand(parts);
                        break;
                    default:
                        System.out.println("Unknown command: " + command);
                }
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void handleUpdateCommand(String[] parts) {
        String name = parts[1];
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

    private static Image getImage(String[] parts, String attribute, Image oldImage) {
        String value = parts[3];
        return switch (attribute){
            case "name" -> new Image(value, oldImage.date(), oldImage.tags(), oldImage.location());
            case "date" -> new Image(value, LocalDate.parse(value), oldImage.tags(), oldImage.location());
            case "tags" -> new Image(value, oldImage.date() ,Arrays.asList(value.split(",")), oldImage.location());
            case "location" -> new Image(value, oldImage.date(), oldImage.tags(), new File(value));
            default -> throw new IllegalArgumentException("Invalid attribute. Use date, tags, or location.");
        };
    }

    private void handleRemoveCommand(String[] parts) {
        String name = parts[1];
        Image image = imageRepository.getImageByName(name);
        if (image == null) {
            throw new IllegalArgumentException("Image not found: " + name);
        }
        imageRepository.removeImage(image);
        System.out.println("Removed image: " + name);
    }

    private void handleAddCommand(String[] parts) {
        String name = parts[1].toLowerCase();
        LocalDate date = LocalDate.parse(parts[2]);
        List<String> tags = List.of(parts[3].split(","));
        File location = new File(parts[4]);
        ImageRepository.addImage(new Image(name, date, tags, location));
        System.out.println("Image '" + name + "' added.");
    }
}

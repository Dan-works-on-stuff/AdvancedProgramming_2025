import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class Shell {
    private String command;
    private final ImageRepository imageRepository;
    private final Scanner scanner;
    public Shell() {
        this.scanner = new Scanner(System.in);
        this.imageRepository = new ImageRepository();
    }

    public void start() {
        System.out.println("Image Shell - Commands: add, remove, update, save, load, report, exit");
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
                    case "load":
                        handleLoadCommand(parts);
                        break;
                    case "save":
                        handleSaveCommand(parts);
                        break;
                    case "report":
                        handleReportCommand(parts);
                        break;
                    default:
                        System.out.println("Unknown command: " + command);
                }
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    private void handleReportCommand(String[] parts) {
        String filename = "report.html";
        if (parts.length > 1) {
            filename = parts[1];
        }
        try{
            ImageRepository.generateReport(filename);
            System.out.println("Report generated and opened: " + filename);
        } catch (IOException | TemplateException e  ) {
            throw new IllegalArgumentException("Report generation failed: " + e.getMessage());
        }
    }

    private void handleSaveCommand(String[] parts) {
        try{
            ImageRepository.saveToFile(parts[1]);
            System.out.println("Saved images to: " + parts[1]);
        } catch (IOException e) {
            throw new IllegalArgumentException("Save failed: " + e.getMessage());
        }
    }

    private void handleLoadCommand(String[] parts) {
        try{
            ImageRepository.loadFromFile(parts[1]);
            System.out.println("Loaded images from: " + parts[1]);
        } catch (IOException e){
            throw new IllegalArgumentException("Load failed: " + e.getMessage());
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

    private Image getImage(String[] parts, String attribute, Image oldImage) {
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

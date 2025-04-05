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
        System.out.println("Image Shell - Commands: add, remove, update, save, exit");
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
                        Add add = new Add();
                        add.handleAddCommand(parts);
                        break;
                    case "remove":
                        Remove removeCommand = new Remove();
                        removeCommand.handleRemoveCommand(parts);
                        break;
                    case "update":
                        Update update = new Update();
                        update.handleUpdateCommand(parts);
                        break;
//                    case "load":
//                        Load load = new Load();
//                        load.handleLoadCommand(parts);
//                        break;
                    case "save":
                        Save save = new Save();
                        save.handleSaveCommand(parts);
                        break;
//                    case "report":
////                        handleReportCommand(parts);
//                        break;
                    default:
                        System.out.println("Unknown command: " + command);
                }
            } catch (IllegalArgumentException | DateTimeParseException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

//    private void handleReportCommand(String[] parts) {
//        String filename = "report.html";
//        if (parts.length > 1) {
//            filename = parts[1];
//        }
//        try{
//            ImageRepository.generateReport(filename);
//            System.out.println("Report generated and opened: " + filename);
//        } catch (IOException | TemplateException e  ) {
//            throw new IllegalArgumentException("Report generation failed: " + e.getMessage());
//        }
//    }

}

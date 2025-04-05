import java.io.IOException;

public class Save {
    public void handleSaveCommand(String[] parts) {
        try{
            ImageRepository.saveToFile(parts[1]);
            System.out.println("Saved images to: " + parts[1]);
        } catch (IOException e) {
            throw new IllegalArgumentException("Save failed: " + e.getMessage());
        }
    }
}

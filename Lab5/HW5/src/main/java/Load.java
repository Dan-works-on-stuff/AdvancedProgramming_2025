import java.io.IOException;

public class Load {
    public void handleLoadCommand(String[] parts) {
        try{
            ImageRepository.loadFromFile(parts[1]);
            System.out.println("Loaded images from: " + parts[1]);
        } catch (IOException e){
            throw new IllegalArgumentException("Load failed: " + e.getMessage());
        }
    }
}

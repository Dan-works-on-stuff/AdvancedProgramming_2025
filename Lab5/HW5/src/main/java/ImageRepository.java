import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Repository class to manage images
public class ImageRepository {
    private static final List<Image> images = new ArrayList<>();

    public static void addImage(Image image) {
        images.add(image);
    }

    public void displayImage(Image image) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(String.valueOf(image.location()))); // Create File from String path
        } else {
            throw new UnsupportedOperationException("Desktop not supported");
        }
    }

    // Helper method to retrieve an image by name
    public Image getImageByName(String name) {
        return images.stream()
                .filter(img -> img.name().equals(name))
                .findFirst()
                .orElse(null);
    }

    public void removeImage(Image image) {
        images.remove(image);
    }

    public static void saveToFile(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(new File(filename), images);
    }

    public static void loadFromFile(String filename) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        List<Image> loadedImages = mapper.readValue(new File(filename), new TypeReference<List<Image>>(){});
        images.clear();
        images.addAll(loadedImages);
    }
}
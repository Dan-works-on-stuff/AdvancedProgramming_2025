import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Repository class to manage images
public class ImageRepository {
    private final List<Image> images = new ArrayList<>();

    public void addImage(Image image) {
        images.add(image);
    }

    public void displayImage(Image image) throws IOException {
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            desktop.open(new File(String.valueOf(image.location())));
        } else {
            throw new UnsupportedOperationException("Could not complete operation");
        }
    }

    // Helper method to retrieve an image by name
    public Image getImageByName(String name) {
        return images.stream()
                .filter(img -> img.name().equals(name))
                .findFirst()
                .orElse(null);
    }
}
public class Remove {
    public void handleRemoveCommand(String[] parts) {
        String name = parts[1];
        ImageRepository imageRepository = new ImageRepository();
        Image image = imageRepository.getImageByName(name);
        if (image == null) {
            throw new IllegalArgumentException("Image not found: " + name);
        }
        imageRepository.removeImage(image);
        System.out.println("Removed image: " + name);
    }
}

//package ru.hzerr.fx.framework.core.loader;
//
//import javafx.scene.image.Image;
//import javafx.scene.image.ImageView;
//import ru.hzerr.collections.map.HMap;
//import ru.hzerr.collections.map.HashHMap;
//import ru.hzerr.exception.ResourceLoadException;
//import ru.hzerr.logging.LogManager;
//import ru.hzerr.util.IOTools;
//
//import java.io.IOException;
//
//public class ImageLoader {
//
//    private static final HMap<String, Image> CACHE = new HashHMap<>();
//
//    private ImageLoader() {
//    }
//
//    public static ImageView loadImageView(String name, String extension) throws ResourceLoadException {
//        return loadImageView(name + extension);
//    }
//
//    public static ImageView loadImageView(String imageFullName) throws ResourceLoadException {
//        return loadImageView(imageFullName, 0D, 0D);
//    }
//
//    public static ImageView loadImageView(String imageFullName, double width, double height) throws ResourceLoadException {
//        return loadImageView(imageFullName, width, height, false, false);
//    }
//
//    public static
//    ImageView loadImageView(String imageFullName, double width, double height, boolean preserveRatio) throws ResourceLoadException {
//        return loadImageView(imageFullName, width, height, preserveRatio, false);
//    }
//
//    public static
//    ImageView loadImageView(String imageFullName, double width, double height, boolean preserveRatio, boolean smooth) throws ResourceLoadException {
//        ImageView view = new ImageView(loadImage(imageFullName, width, height, preserveRatio, smooth));
//        view.setFitWidth(width);
//        view.setFitHeight(height);
//        view.setPreserveRatio(preserveRatio);
//        view.setSmooth(smooth);
//        return view;
//    }
//
//    public static Image loadImage(String imageName, String extension) throws ResourceLoadException {
//        return loadImage(imageName + extension);
//    }
//
//    public static Image loadImage(String imageFullName) throws ResourceLoadException {
//        return loadImage(imageFullName, 0);
//    }
//
//    // size - weight and height
//    public static Image loadImage(String imageFullName, double size) throws ResourceLoadException {
//        return loadImage(imageFullName, size, size, false);
//    }
//
//    public static Image loadImage(String imageFullName, double width, double height) throws ResourceLoadException {
//        return loadImage(imageFullName, width, height, false);
//    }
//
//    public static Image loadImage(String imageFullName, double width, double height, boolean preserveRatio) throws ResourceLoadException {
//        return loadImage(imageFullName, width, height, preserveRatio, false);
//    }
//
//    public static Image loadImage(String imageFullName, double width, double height, boolean preserveRatio, boolean smooth) throws ResourceLoadException {
//        if (CACHE.noContainsKey(imageFullName)) {
//            LogManager.getLogger().debug("Finding the " + "/image/" + imageFullName + " file");
//            try {
//                Image image = IOTools.getResourceAsStream("/image/" + imageFullName,
//                        iStream -> {
//                            LogManager.getLogger().debug("/image/" + imageFullName + " has been successfully loaded");
//                            return new Image(iStream, width, height, preserveRatio, smooth);
//                        });
//                return CACHE.putAndGet(imageFullName, image);
//            } catch (IOException io) {
//                throw new ResourceLoadException("Couldn't load image " + imageFullName, io);
//            }
//        } else
//            return CACHE.get(imageFullName);
//    }
//}

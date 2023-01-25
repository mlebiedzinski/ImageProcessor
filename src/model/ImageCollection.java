package model;

import java.util.HashMap;

/**
 * An ImageCollection is a map that includes all images in the program.
 * Key is the name of the image and value is the image.
 */
public class ImageCollection {

  private HashMap<String, Image> imageMap;

  /**
   * This is the constructor for an ImageCollection. it creates a new (hash)map of images.
   */
  public ImageCollection() {
    this.imageMap = new HashMap<String, Image>();
  }

  /**
   * This is a getter method for the ImageCollection map. It's public so other classes can access
   * the map of images.
   *
   * @return map of images
   */
  public HashMap<String, Image> getHashMap() {
    return this.imageMap;
  }

  /**
   * This method updates the existing hashmap by putting a newly created image into the map and
   * setting the key as the destName / whatever the user wanted to call it.
   *
   * @param destName String associated with the image
   * @param newImage new image to be added to the map
   */
  public void updateMap(String destName, Image newImage) {
    this.imageMap.put(destName, newImage);
  }

}

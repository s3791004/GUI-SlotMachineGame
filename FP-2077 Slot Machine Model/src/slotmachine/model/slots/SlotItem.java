package slotmachine.model.slots;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Supporting enum used in the <b>Further Programming Assignment</b>
 * <p>
 * The values in this enum are use to represent a single slot item on a wheel.
 * <p>
 * Each {@link Wheel} is made up of 100 {@link SlotItem} with {@link #count}
 * number of each {@link SlotItem} value. e.g. There are two {@link #GOLD} 
 * and 7 {@link #LIME} on each wheel
 * <p>
 * <b>Note:</b> You are <b>not</b> permitted to change this enum in any way
 * (aside from during your own testing). The submitted enum must exactly match
 * the start up code.
 * 
 * @author Ross Nye
 */
public enum SlotItem
{
   ONE("1", 22, "images/one.png"),
   THREE("3", 18, "images/three.png"),
   FIVE("5", 14, "images/five.png"),
   SEVEN("7", 11, "images/seven.png"),
   LEMON("Lemon", 8, "images/lemon.png"),
   LIME("Lime", 7, "images/lime.png"), 
   BERRY("Berry",6, "images/berry.png"),
   CHERRY("Cherry", 5, "images/cherry.png"),
   MELON("Melon", 4, "images/melon.png"),
   SILVER("Silver Bar", 3, "images/silver.png"),
   GOLD("Gold Bar", 2, "images/gold.png");

   private String label;
   private int count;
   private BufferedImage image;
   
   private SlotItem(String label, int count, String fileName)
   {
      this.label = label;
      this.count = count;
      
      File file = new File(fileName);
      try {
         this.image = ImageIO.read(file);
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   
   public static SlotItem getRandomSlot() {
      return values()[((int) (Math.random() * values().length))];
   }
   
   /**
    * Returns an icon representation of the SlotItem.
    */
   public BufferedImage getImage() {
      return this.image;
   }
   
   public Image getSmallImage() {
      return this.image.getScaledInstance(80, 80, 1);
   }
   
   /**
    * Returns a String representation of the SlotItem, which is the supplied 
    * label.
    */
   @Override
   public String toString()
   {
      return label;
   }
   
   /**
    * @return the amount of the SlotItem on a fully populated wheel.
    */
   public int getCount()
   {
      return count;
   }
}

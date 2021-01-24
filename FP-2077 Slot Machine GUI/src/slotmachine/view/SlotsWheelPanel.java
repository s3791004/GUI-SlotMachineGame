package slotmachine.view;

import java.awt.Color;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JPanel;

//import slotmachine.model.SlotItem;
import slotmachine.model.SlotMachineImpl;
import slotmachine.model.slots.Wheel;
import slotmachine.model.slots.WheelImpl;

// The business end of the GUI. 
@SuppressWarnings("serial")
public class SlotsWheelPanel extends JPanel implements PropertyChangeListener {

   private static final int NUM_SLOTWHEELS = 3;
   private static final int NUM_SLOTLINES = 3;

   private Wheel wheel1;
   private Wheel wheel2;
   private Wheel wheel3;

   public SlotsWheelPanel(GuiCallBack gui, SlotMachineImpl model) {

      gui.addPropertyChangeListener(this);

      wheel1 = WheelImpl.createWheel(1);
      wheel2 = WheelImpl.createWheel(2);
      wheel3 = WheelImpl.createWheel(3);

      setBackground(Color.BLACK);
   }

   @Override
   public void paintComponent(Graphics g) {
      super.paintComponent(g);
      int height = this.getHeight();
      int width = this.getWidth();
      int gridW = width / NUM_SLOTWHEELS;
      int gridH = height / NUM_SLOTLINES;

      int offsetX = 0;
      int offsetY = 0;

      // Conditional here to allow correct positioning and scaling when the window
      // is re-sized
      if (height >= width) {
         offsetY = (gridH / 2) - (gridW / 2);
         g.drawImage(wheel1.getTopSlot().getImage(), offsetX, offsetY, gridW, gridW,
                     this);
         g.drawImage(wheel1.getCentreSlot().getImage(), offsetX, gridH + offsetY,
                     gridW, gridW, this);
         g.drawImage(wheel1.getBottomSlot().getImage(), offsetX, 2 * gridH + offsetY,
                     gridW, gridW, this);

         g.drawImage(wheel2.getTopSlot().getImage(), gridW, offsetY, gridW, gridW,
                     this);
         g.drawImage(wheel2.getCentreSlot().getImage(), gridW, gridH + offsetY,
                     gridW, gridW, this);
         g.drawImage(wheel2.getTopSlot().getImage(), gridW, 2 * gridH + offsetY,
                     gridW, gridW, this);
         //
         g.drawImage(wheel3.getTopSlot().getImage(), 2 * gridW, offsetY, gridW,
                     gridW, this);
         g.drawImage(wheel3.getCentreSlot().getImage(), 2 * gridW, gridH + offsetY,
                     gridW, gridW, this);
         g.drawImage(wheel3.getBottomSlot().getImage(), 2 * gridW,
                     2 * gridH + offsetY, gridW, gridW, this);
      }
      else {
         offsetX = (gridW / 2) - (gridH / 2);
         g.drawImage(wheel1.getTopSlot().getImage(), offsetX, offsetY, gridH, gridH,
                     this);
         g.drawImage(wheel1.getCentreSlot().getImage(), offsetX, gridH + offsetY,
                     gridH, gridH, this);
         g.drawImage(wheel1.getBottomSlot().getImage(), offsetX, 2 * gridH + offsetY,
                     gridH, gridH, this);

         g.drawImage(wheel2.getTopSlot().getImage(), gridW, offsetY, gridH, gridH,
                     this);
         g.drawImage(wheel2.getCentreSlot().getImage(), gridW, gridH + offsetY,
                     gridH, gridH, this);
         g.drawImage(wheel2.getBottomSlot().getImage(), gridW, 2 * gridH + offsetY,
                     gridH, gridH, this);
         //
         g.drawImage(wheel3.getTopSlot().getImage(), 2 * gridW, offsetY, gridH,
                     gridH, this);
         g.drawImage(wheel3.getCentreSlot().getImage(), 2 * gridW, gridH + offsetY,
                     gridH, gridH, this);
         g.drawImage(wheel3.getBottomSlot().getImage(), 2 * gridW,
                     2 * gridH + offsetY, gridH, gridH, this);
      }

      // Diagonals to indicate which slots are line 4 and 5
      g.drawLine(0, gridH / 4, width, height - gridH / 4);
      g.drawLine(0, height - gridH / 4, width, gridH / 4);
   }

   // How the wheels get repainted each time a turn happens
   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      if (evt.getPropertyName().equals("Spin")) {
         Wheel wheel = (Wheel) evt.getNewValue();
         switch (wheel.hashCode()) {
            case 1:
               wheel1 = wheel;
               break;
            case 2:
               wheel2 = wheel;
               break;
            case 3:
               wheel3 = wheel;
               break;
         }
         this.repaint();
      }

   }

}
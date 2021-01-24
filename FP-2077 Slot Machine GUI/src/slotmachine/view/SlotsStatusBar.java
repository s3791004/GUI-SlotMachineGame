package slotmachine.view;

import java.awt.Color;
import java.awt.GridLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import slotmachine.model.PlayerImpl;

// Status bar to show some state changes
@SuppressWarnings("serial")
public class SlotsStatusBar extends JPanel implements PropertyChangeListener {

   private JLabel left;
   private JLabel right;

   private int winnings;

   public SlotsStatusBar(GuiCallBack gui) {

      gui.addPropertyChangeListener(this);

      setLayout(new GridLayout());

      // This state represents the total winnings on this session
      left = new JLabel("Total Winnings on Machine: 0 ");
      left.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      add(left);

      // This represents the current player's id or a prompt to register now
      right = new JLabel("REGISTER NOW! ");
      right.setHorizontalAlignment(SwingConstants.RIGHT);
      right.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      add(right);

   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      if (evt.getPropertyName().equals("Win Result")) {
         winnings += (int) evt.getNewValue();
         left.setText("Total Winnings on Machine: " + winnings);
      }

      if (evt.getPropertyName().equals("Update Player")) {
         PlayerImpl player = (PlayerImpl) evt.getNewValue();
         right.setText(String.format("Player ID: %s ", player.getId()));
      }

      if (evt.getPropertyName().equals("Cash Out")) {
         right.setText("REGISTER NOW! ");
      }

   }

}

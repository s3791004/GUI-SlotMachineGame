package slotmachine.view;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

// Simple panel to show which lines are what slots
@SuppressWarnings("serial")
public class SlotsLineNums extends JPanel {

   public SlotsLineNums() {
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));
      add(new JLabel(" LINE 5 "));
      add(Box.createVerticalGlue());
      add(new JLabel(" LINE 2 "));
      add(Box.createVerticalGlue());
      add(new JLabel(" LINE 1 "));
      add(Box.createVerticalGlue());
      add(new JLabel(" LINE 3 "));
      add(Box.createVerticalGlue());
      add(new JLabel(" LINE 4 "));
      setVisible(true);
   }
}

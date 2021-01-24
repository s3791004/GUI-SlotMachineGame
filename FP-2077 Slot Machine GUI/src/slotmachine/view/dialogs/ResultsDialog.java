package slotmachine.view.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import slotmachine.model.slots.SlotLine;

// Dialog for viewing the result after each spin
@SuppressWarnings("serial")
public class ResultsDialog extends JDialog {

   public ResultsDialog(List<Object[]> q) {

      // This list of an array of objects comes from the pcs fired from a
      // betTotals method. They all get stored as variables for readability
      // and prevent re-use
      Object[] line1 = q.get(0);
      boolean hasBet1 = (boolean) line1[0];
      int lineOutcome1 = (int) line1[1];
      SlotLine slotLine1 = (SlotLine) line1[2];

      Object[] line2 = q.get(1);
      boolean hasBet2 = (boolean) line2[0];
      int lineOutcome2 = (int) line2[1];
      SlotLine slotLine2 = (SlotLine) line2[2];

      Object[] line3 = q.get(2);
      boolean hasBet3 = (boolean) line3[0];
      int lineOutcome3 = (int) line3[1];
      SlotLine slotLine3 = (SlotLine) line3[2];

      Object[] line4 = q.get(3);
      boolean hasBet4 = (boolean) line4[0];
      int lineOutcome4 = (int) line4[1];
      SlotLine slotLine4 = (SlotLine) line4[2];

      Object[] line5 = q.get(4);
      boolean hasBet5 = (boolean) line5[0];
      int lineOutcome5 = (int) line5[1];
      SlotLine slotLine5 = (SlotLine) line5[2];

      setTitle("Results!!!");
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(4, 4, 4, 4);

      // The display of all the lines based on the list supplied in constructor
      gbc.gridx = 0;
      gbc.gridy = 0;
      add(new JLabel("LINE 1: " + (hasBet1 ? "WIN " + lineOutcome1 : "No Bet")),
          gbc);
      gbc.gridx = 1;
      gbc.gridy = 0;
      add(new JLabel(new ImageIcon(slotLine1.getSlot1().getSmallImage())), gbc);
      gbc.gridx = 2;
      gbc.gridy = 0;
      add(new JLabel(new ImageIcon(slotLine1.getSlot2().getSmallImage())), gbc);
      gbc.gridx = 3;
      gbc.gridy = 0;
      add(new JLabel(new ImageIcon(slotLine1.getSlot3().getSmallImage())), gbc);

      gbc.gridx = 0;
      gbc.gridy = 1;
      add(new JLabel("LINE 2: " + (hasBet2 ? "WIN " + lineOutcome2 : "No Bet")),
          gbc);
      gbc.gridx = 1;
      gbc.gridy = 1;
      add(new JLabel(new ImageIcon(slotLine2.getSlot1().getSmallImage())), gbc);
      gbc.gridx = 2;
      gbc.gridy = 1;
      add(new JLabel(new ImageIcon(slotLine2.getSlot2().getSmallImage())), gbc);
      gbc.gridx = 3;
      gbc.gridy = 1;
      add(new JLabel(new ImageIcon(slotLine2.getSlot3().getSmallImage())), gbc);

      gbc.gridx = 0;
      gbc.gridy = 2;
      add(new JLabel("LINE 3: " + (hasBet3 ? "WIN " + lineOutcome3 : "No Bet")),
          gbc);
      gbc.gridx = 1;
      gbc.gridy = 2;
      add(new JLabel(new ImageIcon(slotLine3.getSlot1().getSmallImage())), gbc);
      gbc.gridx = 2;
      gbc.gridy = 2;
      add(new JLabel(new ImageIcon(slotLine3.getSlot2().getSmallImage())), gbc);
      gbc.gridx = 3;
      gbc.gridy = 2;
      add(new JLabel(new ImageIcon(slotLine3.getSlot3().getSmallImage())), gbc);

      gbc.gridx = 0;
      gbc.gridy = 3;
      add(new JLabel("LINE 4: " + (hasBet4 ? "WIN " + lineOutcome4 : "No Bet")),
          gbc);
      gbc.gridx = 1;
      gbc.gridy = 3;
      add(new JLabel(new ImageIcon(slotLine4.getSlot1().getSmallImage())), gbc);
      gbc.gridx = 2;
      gbc.gridy = 3;
      add(new JLabel(new ImageIcon(slotLine4.getSlot2().getSmallImage())), gbc);
      gbc.gridx = 3;
      gbc.gridy = 3;
      add(new JLabel(new ImageIcon(slotLine4.getSlot3().getSmallImage())), gbc);

      gbc.gridx = 0;
      gbc.gridy = 4;
      add(new JLabel("LINE 5: " + (hasBet5 ? "WIN " + lineOutcome5 : "No Bet")),
          gbc);
      gbc.gridx = 1;
      gbc.gridy = 4;
      add(new JLabel(new ImageIcon(slotLine5.getSlot1().getSmallImage())), gbc);
      gbc.gridx = 2;
      gbc.gridy = 4;
      add(new JLabel(new ImageIcon(slotLine5.getSlot2().getSmallImage())), gbc);
      gbc.gridx = 3;
      gbc.gridy = 4;
      add(new JLabel(new ImageIcon(slotLine5.getSlot3().getSmallImage())), gbc);

      gbc.gridx = 0;
      gbc.gridy = 5;
      JButton close = new JButton("Close");
      close.addActionListener(new ActionListener() {

         @Override
         public void actionPerformed(ActionEvent e) {
            dispose();

         }
      });
      add(close, gbc);

      pack();
      setModal(true);
      setLocationRelativeTo(null);
      setVisible(true);
   }

}

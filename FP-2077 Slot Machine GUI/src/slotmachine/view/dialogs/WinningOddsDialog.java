package slotmachine.view.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

import slotmachine.model.slots.SlotItem;

// Very simple hard code of the WinSettings taken from 6 winning lines 
// selected from the WinningSettings.jar
@SuppressWarnings("serial")
public class WinningOddsDialog extends JDialog {

   public WinningOddsDialog() {
      setTitle("Winning Odds");
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(4, 4, 4, 4);

      gbc.gridx = 0;
      gbc.gridy = 0;
      add(new JLabel(new ImageIcon(SlotItem.GOLD.getSmallImage())), gbc);
      gbc.gridx = 1;
      gbc.gridy = 0;
      add(new JLabel(new ImageIcon(SlotItem.GOLD.getSmallImage())), gbc);
      gbc.gridx = 2;
      gbc.gridy = 0;
      add(new JLabel(new ImageIcon(SlotItem.GOLD.getSmallImage())), gbc);
      gbc.gridx = 3;
      gbc.gridy = 0;
      add(new JLabel("Wins: 50x"), gbc);

      gbc.gridx = 0;
      gbc.gridy = 1;
      add(new JLabel(new ImageIcon(SlotItem.GOLD.getSmallImage())), gbc);
      gbc.gridx = 1;
      gbc.gridy = 1;
      add(new JLabel(new ImageIcon(SlotItem.ONE.getSmallImage())), gbc);
      gbc.gridx = 2;
      gbc.gridy = 1;
      add(new JLabel(new ImageIcon(SlotItem.THREE.getSmallImage())), gbc);
      gbc.gridx = 3;
      gbc.gridy = 1;
      add(new JLabel("Wins: 5x"), gbc);

      gbc.gridx = 0;
      gbc.gridy = 2;
      add(new JLabel(new ImageIcon(SlotItem.SILVER.getSmallImage())), gbc);
      gbc.gridx = 1;
      gbc.gridy = 2;
      add(new JLabel(new ImageIcon(SlotItem.SILVER.getSmallImage())), gbc);
      gbc.gridx = 2;
      gbc.gridy = 2;
      add(new JLabel(new ImageIcon(SlotItem.SILVER.getSmallImage())), gbc);
      gbc.gridx = 3;
      gbc.gridy = 2;
      add(new JLabel("Wins: 33x"), gbc);

      gbc.gridx = 0;
      gbc.gridy = 3;
      add(new JLabel(new ImageIcon(SlotItem.SILVER.getSmallImage())), gbc);
      gbc.gridx = 1;
      gbc.gridy = 3;
      add(new JLabel(new ImageIcon(SlotItem.ONE.getSmallImage())), gbc);
      gbc.gridx = 2;
      gbc.gridy = 3;
      add(new JLabel(new ImageIcon(SlotItem.THREE.getSmallImage())), gbc);
      gbc.gridx = 3;
      gbc.gridy = 3;
      add(new JLabel("Wins: 3x"), gbc);

      gbc.gridx = 0;
      gbc.gridy = 4;
      add(new JLabel(new ImageIcon(SlotItem.MELON.getSmallImage())), gbc);
      gbc.gridx = 1;
      gbc.gridy = 4;
      add(new JLabel(new ImageIcon(SlotItem.MELON.getSmallImage())), gbc);
      gbc.gridx = 2;
      gbc.gridy = 4;
      add(new JLabel(new ImageIcon(SlotItem.MELON.getSmallImage())), gbc);
      gbc.gridx = 3;
      gbc.gridy = 4;
      add(new JLabel("Wins: 18x"), gbc);

      gbc.gridx = 0;
      gbc.gridy = 5;
      add(new JLabel(new ImageIcon(SlotItem.CHERRY.getSmallImage())), gbc);
      gbc.gridx = 1;
      gbc.gridy = 5;
      add(new JLabel(new ImageIcon(SlotItem.CHERRY.getSmallImage())), gbc);
      gbc.gridx = 2;
      gbc.gridy = 5;
      add(new JLabel(new ImageIcon(SlotItem.CHERRY.getSmallImage())), gbc);
      gbc.gridx = 3;
      gbc.gridy = 5;
      add(new JLabel("Wins: 16x"), gbc);

      gbc.gridx = 0;
      gbc.gridy = 6;
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

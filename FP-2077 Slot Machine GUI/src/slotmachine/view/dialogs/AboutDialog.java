package slotmachine.view.dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

// Basic dialog for info about the author and where the graphics came from
@SuppressWarnings("serial")
public class AboutDialog extends JDialog {

   public AboutDialog() {
      setTitle("About This Game");
      setLayout(new GridBagLayout());
      GridBagConstraints gbc = new GridBagConstraints();
      gbc.insets = new Insets(4, 4, 4, 4);

      gbc.gridx = 0;
      gbc.gridy = 0;
      add(new JLabel("Name: Andrew Waltos"), gbc);

      gbc.gridx = 0;
      gbc.gridy = 1;
      add(new JLabel("Student Number: s3791004"), gbc);

      gbc.gridx = 0;
      gbc.gridy = 2;
      add(new JLabel("Icons from 'game-icons.net'"), gbc);

      gbc.gridx = 0;
      gbc.gridy = 3;
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

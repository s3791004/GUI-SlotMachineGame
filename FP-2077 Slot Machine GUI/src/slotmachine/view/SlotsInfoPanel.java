package slotmachine.view;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import slotmachine.model.PlayerImpl;
import slotmachine.model.slots.LineNum;

// Info Panel displays and holds a lot of the information for this program to
// run properly
@SuppressWarnings("serial")
public class SlotsInfoPanel extends JPanel implements PropertyChangeListener {

   // Labels stored as members so they can be altered from methods as the state
   // changes
   private JLabel name;
   private JLabel intCredits;
   private JLabel curCredits;
   private JLabel curBet;
   private JLabel line1;
   private JLabel line2;
   private JLabel line3;
   private JLabel line4;
   private JLabel line5;
   private JLabel avatar;

   // ints used in other areas to help validate
   private int creditsAmt;
   private int betAmt;
   private int line1Amt;
   private int line2Amt;
   private int line3Amt;
   private int line4Amt;
   private int line5Amt;

   public SlotsInfoPanel(GuiCallBack gui) {

      gui.addPropertyChangeListener(this);

      // Default 'Unregistered' state when created
      creditsAmt = 0;
      betAmt = 0;
      line1Amt = 0;
      line2Amt = 0;
      line3Amt = 0;
      line4Amt = 0;
      line5Amt = 0;

      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.BLUE));

      name = new JLabel(" UNREGISTERED ");
      add(name);
      intCredits = new JLabel(" Initial Credits: 0 ");
      add(intCredits);
      curCredits = new JLabel(" Current Credits: 0 ");
      add(curCredits);
      curBet = new JLabel(" Current Bet: 0 ");
      add(curBet);
      add(new JLabel(" "));
      add(new JLabel(" CURRENT BETS: "));
      line1 = new JLabel(" LINE 1: 0 ");
      add(line1);
      line2 = new JLabel(" LINE 2: 0 ");
      add(line2);
      line3 = new JLabel(" LINE 3: 0 ");
      add(line3);
      line4 = new JLabel(" LINE 4: 0 ");
      add(line4);
      line5 = new JLabel(" LINE 5: 0 ");
      add(line5);
      
      add(Box.createVerticalGlue());
      avatar = new JLabel(new ImageIcon("images/empty.png"));
      add(avatar);

   }

   public int getCreditsAmt() {
      return creditsAmt;
   }

   public int getBetAmt() {
      return betAmt;
   }
   
   public void setAvatar(String avatar) {
      this.avatar.setIcon(new ImageIcon("images/" + avatar + ".png"));
   }

   @Override
   public void propertyChange(PropertyChangeEvent evt) {
      if (evt.getPropertyName().equals("Update Player")) {
         PlayerImpl player = (PlayerImpl) evt.getNewValue();
         creditsAmt = player.getInitalCredits();

         name.setText(String.format(" Player Name: %s ", player.getName()));
         intCredits.setText(String.format(" Initial Credits: %d ",
                                          player.getInitalCredits()));
         curCredits.setText(String.format(" Current Credits: %d ",
                                          player.getCredits()));
      }

      if (evt.getPropertyName().equals("Cash Out")) {
         name.setText(" UNREGISTERED ");
         creditsAmt = 0;
         betAmt = 0;
         intCredits.setText(" Initial Credits: 0 ");
         curCredits.setText(" Current Credits: 0 ");
         curBet.setText(" Current Bet: 0 ");
         line1.setText(" LINE 1: 0 ");
         line2.setText(" LINE 2: 0 ");
         line3.setText(" LINE 3: 0 ");
         line4.setText(" LINE 4: 0 ");
         line5.setText(" LINE 5: 0 ");
         setAvatar("empty");
      }

      if (evt.getPropertyName().equals("Place Bet")) {
         Object[] arr = (Object[]) evt.getNewValue();
         int amount = (int) arr[0];
         LineNum line = (LineNum) arr[1];
         PlayerImpl player = (PlayerImpl) arr[2];

         switch (line) {
            case LINE1:
               line1Amt = amount;
               line1.setText(String.format(" LINE 1: %d ", line1Amt));
               break;
            case LINE2:
               line2Amt = amount;
               line2.setText(String.format(" LINE 2: %d ", line2Amt));
               break;
            case LINE3:
               line3Amt = amount;
               line3.setText(String.format(" LINE 3: %d ", line3Amt));
               break;
            case LINE4:
               line4Amt = amount;
               line4.setText(String.format(" LINE 4: %d ", line4Amt));
               break;
            case LINE5:
               line5Amt = amount;
               line5.setText(String.format(" LINE 5: %d ", line5Amt));
               break;
         }
         betAmt = player.getBet();
         curBet.setText(String.format(" Current Bet: %d ", betAmt));
      }

      if (evt.getPropertyName().equals("Bet Totals") ||
          evt.getPropertyName().equals("Add Credits")) {
         PlayerImpl player = (PlayerImpl) evt.getNewValue();
         creditsAmt = player.getCredits();
         curCredits.setText(String.format(" Current Credits: %d ", creditsAmt));
      }
   }
}

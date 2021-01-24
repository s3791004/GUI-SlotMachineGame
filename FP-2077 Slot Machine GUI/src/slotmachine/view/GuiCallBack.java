package slotmachine.view;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import slotmachine.model.Player;
import slotmachine.model.slots.LineNum;
import slotmachine.model.slots.SlotLine;
import slotmachine.model.slots.SpinResult;
import slotmachine.model.slots.Wheel;
import slotmachine.view.dialogs.ResultsDialog;

// The beating heart of the GUI. Tells everyone what to do and when
// to do it!!
public class GuiCallBack implements GameCallback {

   // This is for storing the line results as they come in from the lineResult
   // method
   private List<Object[]> q = new LinkedList<>();

   final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

   public void addPropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.addPropertyChangeListener(listener);
   }

   public void removePropertyChangeListener(PropertyChangeListener listener) {
      this.pcs.removePropertyChangeListener(listener);
   }

   @Override
   public void registerPlayer(Player player) {
      pcs.firePropertyChange("Update Player", null, player);
   }

   @Override
   public void cashOutPlayer(Player player) {
      pcs.firePropertyChange("Cash Out", null, player);
   }

   @Override
   public void addCredits(Player player, int credits) {
      pcs.firePropertyChange("Add Credits", null, player);
   }

   @Override
   public void betUpdated(Player player, int amount, LineNum line) {
      pcs.firePropertyChange("Place Bet", null,
                             new Object[] { amount, line, player });
   }

   @Override
   public void betUpdated(Player player, int amount, Collection<LineNum> lines) {
      pcs.firePropertyChange("Update Bet", null,
                             new Object[] { amount, lines, player });
   }

   @Override
   public void turnWheel(Wheel wheel, int turnNum) {
      pcs.firePropertyChange("Spin", null, wheel);
   }

   @Override
   public void spinComplete(SpinResult spinResult) {
      pcs.firePropertyChange("Spin Complete", null, spinResult);
   }

   @Override
   public void lineResult(Player player, boolean hasBet, int lineOutcome,
                          SlotLine slotLine) {
      q.add(new Object[] { hasBet, lineOutcome, slotLine });
   }

   @Override
   public void betTotals(Player player, int total) {
      pcs.firePropertyChange("Bet Totals", null, player);
      new ResultsDialog(q);
      q.clear();
      pcs.firePropertyChange("Win Result", null, total);
      
   }

}

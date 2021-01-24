package slotmachine.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import slotmachine.model.slots.LineNum;
import slotmachine.model.slots.SlotLine;
import slotmachine.model.slots.SlotLineImpl;
import slotmachine.model.slots.SpinResult;
import slotmachine.model.slots.SpinResultImpl;
import slotmachine.model.slots.Wheel;
import slotmachine.model.slots.WheelImpl;
import slotmachine.model.slots.WinSettings;
import slotmachine.model.slots.WinSettingsImpl;
import slotmachine.view.ConsoleLoggerCallback;
import slotmachine.view.GameCallback;

public class SlotMachineImpl implements SlotMachine {

   private List<GameCallback> callbacks;

   private Player player;

   private List<Wheel> wheels;

   private WinSettings winSettings;

   private Map<LineNum, Integer> lineBets;

   public SlotMachineImpl() {
      this.callbacks = new ArrayList<>();
      this.wheels = new ArrayList<>();
      this.wheels.add(WheelImpl.createWheel(1));
      this.wheels.add(WheelImpl.createWheel(2));
      this.wheels.add(WheelImpl.createWheel(3));
      this.winSettings = new WinSettingsImpl();
   }

   /**
    * Method to reset the bets for every line in the {@link lineBets} Map.
    */
   private void resetLineBets() {
      this.lineBets = new HashMap<>();
      for (LineNum ln : LineNum.values()) {
         lineBets.put(ln, 0);
      }
   }

   /**
    * Method to set the bet for a line in the {@link lineBets} Map.
    */
   private void setLineBet(LineNum line, int amount) {
      this.lineBets.replace(line, lineBets.get(line) + amount);
   }

   /**
    * Method to check if a bet exists for a line in the {@link lineBets} Map.
    * 
    * @return true if a the bet is > 0 for the line. i.e a bet exists for a given
    *         line
    * 
    */
   private boolean hasLineBet(LineNum line) {
      return this.lineBets.get(line) > 0;
   }

   /**
    * Method to check if any bets exist in the {@link lineBets} Map.
    * 
    * @return true if any bets are > 0. i.e any bet exists
    * 
    */
   private boolean hasAnyBets() {
      boolean bets = false;
      for (LineNum ln : LineNum.values()) {
         if (lineBets.get(ln) > 0) {
            bets = true;
         }
      }
      return bets;
   }

   @Override
   public int addCallback(GameCallback callback) {
      this.callbacks.add(callback);
      return this.callbacks.size();
   }

   @Override
   public int removeCallback(GameCallback callback) {
      this.callbacks.remove(callback);
      return this.callbacks.size();
   }

   @Override
   public Player registerPlayer(String id, String name, int initialCredits) {
      try {
         this.player = new PlayerImpl(id, name, initialCredits);
         resetLineBets();
         for (GameCallback gcb : callbacks) {
            gcb.registerPlayer(player);
         }
      }
      catch (Exception e) {
         ConsoleLoggerCallback.LOGGER.warning(String
                  .format("Exception thrown by Slot Machine: %s", e.getMessage()));
      }
      return this.player;
   }

   @Override
   public Player cashOut() {
      Player oldPlayer = this.player;
      try {
         if (this.player == null) {
            throw new IllegalStateException("No registered Player!");
         }
         resetBets();
         for (GameCallback gcb : callbacks) {
            gcb.cashOutPlayer(player);
         }
         this.player = null;
      }
      catch (Exception e) {
         ConsoleLoggerCallback.LOGGER.warning(String
                  .format("Exception thrown by Slot Machine: %s", e.getMessage()));
      }
      return oldPlayer;
   }

   @Override
   public void addCredits(int credits)
            throws IllegalStateException, IllegalArgumentException {
      if (this.player == null) {
         throw new IllegalStateException("No registered Player!");
      }
      if (credits < 0) {
         throw new IllegalArgumentException("Credits cannot be negative!");
      }
      this.player.addCredits(credits);
      for (GameCallback gcb : callbacks) {
         gcb.addCredits(this.player, credits);
      }

   }

   @Override
   public void placeBet(int amount)
            throws IllegalArgumentException, IllegalStateException {
      if (this.player == null) {
         throw new IllegalArgumentException("No registered Player!");
      }
      if (amount < 0) {
         throw new IllegalArgumentException("Cannot have a negative Bet!");
      }
      if (amount > this.player.getAvailableCredits()) {
         throw new IllegalArgumentException("Not Enough Credits!");
      }
      if (amount < lineBets.get(LineNum.LINE1)) {
         throw new IllegalArgumentException("Already a higher bet on line#1!");
      }

      player.setBet(amount);
      setLineBet(LineNum.LINE1, amount);

      for (GameCallback gcb : callbacks) {
         gcb.betUpdated(player, amount, LineNum.LINE1);
      }

   }

   @Override
   public void placeBet(int amount, LineNum line)
            throws IllegalArgumentException, IllegalStateException {
      if (this.player == null) {
         throw new IllegalArgumentException("No registered Player!");
      }
      if (amount < 0) {
         throw new IllegalArgumentException("Cannot have a negative Bet!");
      }
      if (amount > this.player.getAvailableCredits()) {
         throw new IllegalArgumentException("Not Enough Credits!");
      }
      if (amount < this.lineBets.get(line)) {
         throw new IllegalArgumentException("Already a higher bet on this line!");
      }
      if (line == null) {
         throw new IllegalArgumentException("No Line Supplied!");
      }

      player.setBet(player.getBet() + amount);
      setLineBet(line, amount);

      for (GameCallback gcb : callbacks) {
         gcb.betUpdated(player, lineBets.get(line), line);
      }

   }

   @Override
   public void placeBet(int amount, Set<LineNum> lines)
            throws IllegalArgumentException, IllegalStateException {
      if (this.player == null) {
         throw new IllegalArgumentException("No registered Player!");
      }
      if (amount < 0) {
         throw new IllegalArgumentException("Cannot have a negative Bet!");
      }
      if (amount * lines.size() > this.player.getAvailableCredits()) {
         throw new IllegalArgumentException("Not Enough Credits!");
      }
      for (LineNum ln : lines) {
         if (amount < lineBets.get(ln)) {
            throw new IllegalArgumentException("Already a higher bet one of the lines!");
         }
      }
      if (lines.isEmpty()) {
         throw new IllegalArgumentException("No Lines Supplied!");
      }

      player.setBet(player.getBet() + (amount * lines.size()));
      for (LineNum ln : lines) {
         setLineBet(ln, amount);
      }
      for (GameCallback gcb : callbacks) {
         gcb.betUpdated(player, amount, lines);
      }

   }

   @Override
   public void placeBet(int amount, LineNum... lines)
            throws IllegalArgumentException, IllegalStateException {
      if (this.player == null) {
         throw new IllegalArgumentException("No registered Player!");
      }
      if (amount < 0) {
         throw new IllegalArgumentException("Cannot have a negative Bet!");
      }
      if (amount * lines.length > this.player.getAvailableCredits()) {
         throw new IllegalArgumentException("Not Enough Credits!");
      }
      for (LineNum ln : lines) {
         if (amount < lineBets.get(ln)) {
            throw new IllegalArgumentException("Already a higher bet one of the lines!");
         }
      }
      if (lines.length == 0) {
         throw new IllegalArgumentException("No Lines Supplied!");
      }

      List<LineNum> lineCollection = Arrays.asList(lines);
      player.setBet(player.getBet() + (amount * lineCollection.size()));
      for (LineNum ln : lines) {
         setLineBet(ln, amount);
         for (GameCallback gcb : callbacks) {
            gcb.betUpdated(player, amount, ln);
         }
      }
   }

   @Override
   public void resetBets() {
      this.player.resetBet();
      resetLineBets();
      placeBet(0, LineNum.values());
   }

   @Override
   public SpinResult spinToWin(int turns, int delay)
            throws IllegalArgumentException, IllegalStateException {
      if (this.player == null) {
         throw new IllegalStateException("No registered Player!");
      }
      if (!hasAnyBets()) {
         throw new IllegalStateException("There are no bets!");
      }
      if (this.player.getBet() > this.player.getCredits()) {
         throw new IllegalStateException("Not enough Credits!");
      }
      if (turns < 1) {
         throw new IllegalArgumentException("Must have at least 1 turn!");
      }
      if (delay < 0 || delay > 2000) {
         throw new IllegalArgumentException("Delay must be between 0 and 2000!");
      }
      SpinResult spinResult = spin(turns, delay);
      for (GameCallback gcb : callbacks) {
         gcb.spinComplete(spinResult);
         Iterator<SlotLine> lineIterator = spinResult.iterator();
         while (lineIterator.hasNext()) {
            SlotLineImpl slotLine = (SlotLineImpl) lineIterator.next();
            gcb.lineResult(player, hasLineBet(slotLine.getLineNum()),
                           winSettings.getWinOdds(slotLine) * this.lineBets
                                    .get(slotLine.getLineNum()),
                           slotLine);
         }
      }
      int betTotals = applySpinResult(spinResult);
      for (GameCallback gcb : callbacks) {
         gcb.betTotals(player, betTotals);
      }
      return spinResult;
   }

   @Override
   public SpinResult spin(int turns, int delay) throws IllegalArgumentException {
      if (turns < 1) {
         throw new IllegalArgumentException("Must have at least 1 turn!");
      }
      if (delay < 0 || delay > 2000) {
         throw new IllegalArgumentException("Delay must be between 0 and 2000!");
      }
      for (int i = 1; i <= turns; i++) {
         for (Wheel wl : wheels) {
            if (wl.hashCode() == 2 && i > turns * 2 / 3) {
               continue;
            }
            if (wl.hashCode() == 3 && i > turns * 1 / 3) {
               continue;
            }
            for (GameCallback gcb : callbacks) {
               gcb.turnWheel(wl.nextSlot(), i);
            }
         }
         if (i < turns) {
            try {
               Thread.sleep(delay);
            }
            catch (InterruptedException e1) {
               System.out.println(e1.getMessage());
            }
         }
      }
      return new SpinResultImpl(wheels.get(0), wheels.get(1), wheels.get(2));
   }

   @Override
   public int applySpinResult(SpinResult spinResult) {
      int win = 0;
      Iterator<SlotLine> lineIterator = spinResult.iterator();
      while (lineIterator.hasNext()) {
         SlotLine slotLine = lineIterator.next();
         int lineWin = winSettings.getWinOdds(slotLine) *
                       this.lineBets.get(slotLine.getLineNum());
         if (this.lineBets.get(slotLine.getLineNum()) > 0 && lineWin > 0) {
            win += lineWin;
         }
      }
      player.applyWin(win);
      if (player.getBet() > player.getCredits()) {
         resetBets();
      }

      return win;
   }

}

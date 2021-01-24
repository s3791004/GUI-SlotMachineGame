package slotmachine.model;

public class PlayerImpl implements Player {

   private String id;
   private String name;
   private int initialCredits;
   private int credits;
   private int bet;

   public PlayerImpl(String id, String name,
                     int initialCredits) throws IllegalArgumentException {
      if (id == null || id.isEmpty()) {
         throw new IllegalArgumentException("ID cannot be empty");
      }
      if (name == null || name.isEmpty()) {
         throw new IllegalArgumentException("Name cannot be empty");
      }
      if (initialCredits < 0) {
         throw new IllegalArgumentException("Credits cannot be negative");
      }
      if (!id.matches("\\d{5}[a-zA-z]")) {
         throw new IllegalArgumentException("ID must be 5 digits followed by one letter");
      }

      this.id = id;
      this.name = name;
      this.initialCredits = initialCredits;
      this.bet = 0;
      this.credits = initialCredits;
   }

   @Override
   public String getId() {
      return this.id;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public int getInitalCredits() {
      return this.initialCredits;
   }

   @Override
   public int getCredits() {
      return this.credits;
   }

   @Override
   public int getAvailableCredits() {
      if (this.bet > 0) {
         return this.credits - this.bet;
      }
      else {
         return this.credits;
      }
   }

   @Override
   public void addCredits(int credits) {
      this.credits += credits;

   }

   @Override
   public int getBet() {
      return this.bet;
   }

   @Override
   public void setBet(int bet) {
      this.bet = bet;

   }

   @Override
   public void resetBet() {
      this.bet = 0;
   }

   @Override
   public void applyWin(int winAmount) {
      if (winAmount > 0) {
         this.credits += winAmount;
      }
      else {
         this.credits -= this.bet;
      }

   }

   @Override
   public String toString() {
      return String.format("Player %s, %s, credits %d, bet %d, available %d\n",
                           this.id, this.name, this.credits,
                           this.bet, this.getAvailableCredits());
   }

}

package slotmachine.model.slots;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpinResultImpl implements SpinResult {
	
	List<SlotLine> lines = new ArrayList<>();

	public SpinResultImpl(Wheel wheel1, Wheel wheel2, Wheel wheel3) {

		lines.add(new SlotLineImpl(LineNum.LINE1, wheel1.getCentreSlot(), wheel2.getCentreSlot(), wheel3.getCentreSlot()));
		lines.add(new SlotLineImpl(LineNum.LINE2, wheel1.getTopSlot(), wheel2.getTopSlot(), wheel3.getTopSlot()));
		lines.add(new SlotLineImpl(LineNum.LINE3, wheel1.getBottomSlot(), wheel2.getBottomSlot(), wheel3.getBottomSlot()));
		lines.add(new SlotLineImpl(LineNum.LINE4, wheel1.getTopSlot(), wheel2.getCentreSlot(), wheel3.getBottomSlot()));
		lines.add(new SlotLineImpl(LineNum.LINE5, wheel1.getBottomSlot(), wheel2.getCentreSlot(), wheel3.getTopSlot()));
	}

	@Override
	public Iterator<SlotLine> iterator() {
		return this.lines.iterator();
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(String.format("Line #2 | %-11s| %-11s| %-11s|\n", lines.get(1).getSlot1(), lines.get(1).getSlot2(), lines.get(1).getSlot3()));
		str.append(String.format("Line #1 | %-11s| %-11s| %-11s|\n", lines.get(0).getSlot1(), lines.get(0).getSlot2(), lines.get(0).getSlot3()));
		str.append(String.format("Line #3 | %-11s| %-11s| %-11s|", lines.get(2).getSlot1(), lines.get(2).getSlot2(), lines.get(2).getSlot3()));
		return str.toString();
	}
}

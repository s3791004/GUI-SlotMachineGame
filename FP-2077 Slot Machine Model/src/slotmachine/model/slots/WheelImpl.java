package slotmachine.model.slots;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class WheelImpl implements Wheel {

	private int wheelNum;
	
	private SlotItem topSlot;
	private SlotItem centreSlot;
	private SlotItem bottomSlot;
	
	private final List<SlotItem> slots;
	
	private Iterator<SlotItem> slotIterator;
	
	private WheelImpl(int wheelNum) {
		// TODO
		this.slots = generateSlots();
		this.slotIterator = slots.iterator();
		
		this.bottomSlot = slotIterator.next();
		this.centreSlot = slotIterator.next();
		this.topSlot = slotIterator.next();
		this.wheelNum = wheelNum;
	}

	public static Wheel createWheel(int wheelNum) {
		return new WheelImpl(wheelNum);
	}
	
	public static List<SlotItem> generateSlots() {
		List<SlotItem> slots = new ArrayList<>();
		for (SlotItem e : SlotItem.values()) {
			for (int i = 0; i < e.getCount(); i++) {
				slots.add(e);
			}
		}
		Collections.shuffle(slots);
		return slots;
	}

	@Override
	public SlotItem getTopSlot() {
		return this.topSlot;
	}

	@Override
	public SlotItem getCentreSlot() {
		return this.centreSlot;
	}

	@Override
	public SlotItem getBottomSlot() {
		return this.bottomSlot;
	}

	@Override
	public Wheel nextSlot() {
		if (!slotIterator.hasNext()) {
			slotIterator = this.slots.iterator();
		}
		this.bottomSlot = this.centreSlot;
		this.centreSlot = this.topSlot;
		this.topSlot = this.slotIterator.next();
		return this;
	}
	
	@Override
	public String toString() {
		return String.format("Wheel #%d: %-11s/%-11s/%-11s", this.wheelNum, this.bottomSlot, this.centreSlot, this.topSlot);
	}
	
	@Override
	public int hashCode() {
		return this.wheelNum;
	}

}

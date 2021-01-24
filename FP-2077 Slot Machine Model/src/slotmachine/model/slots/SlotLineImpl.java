package slotmachine.model.slots;

import java.util.Objects;

public class SlotLineImpl implements SlotLine {

	private LineNum lineNum;
	private SlotItem slot1;
	private SlotItem slot2;
	private SlotItem slot3;

	public SlotLineImpl(LineNum lineNum, SlotItem slot1, SlotItem slot2, SlotItem slot3) {
		this.lineNum = lineNum;
		this.slot1 = slot1;
		this.slot2 = slot2;
		this.slot3 = slot3;
	}

	@Override
	public LineNum getLineNum() {
		return this.lineNum;
	}

	@Override
	public SlotItem getSlot1() {
		return this.slot1;
	}

	@Override
	public SlotItem getSlot2() {
		return this.slot2;
	}

	@Override
	public SlotItem getSlot3() {
		return this.slot3;
	}

	@Override
	public boolean equals(SlotLine slotLine) {
		if (slotLine == null) {
			return false;
		}
		return slot1.equals(slotLine.getSlot1()) &&
				slot2.equals(slotLine.getSlot2()) &&
				slot3.equals(slotLine.getSlot3());
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj instanceof SlotLine) {
			return this.equals((SlotLine) obj);
		} else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.slot1, this.slot2, this.slot3);
	}

	@Override
	public String toString() {
		return String.format("Line #%d | %-10s | %-10s | %-10s |", this.lineNum.getNum(), this.slot1, this.slot2,
				this.slot3);
	}

}

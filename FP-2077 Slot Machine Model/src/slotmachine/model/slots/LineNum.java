package slotmachine.model.slots;

public enum LineNum {

	LINE1(1), 
	LINE2(2), 
	LINE3(3), 
	LINE4(4), 
	LINE5(5);
	
	private int num;
	
	private LineNum(int num) {
		this.num = num;
	}
	
	public int getNum() {
		return this.num;
	}
	
	@Override
	public String toString() {
		return String.format("LINE #%s", this.num);
	}
}

public class ProgramCounter {
	private int pc;
	public ProgramCounter() {
		pc = 0;
	}

	public void set(int value) {
		assert value % 4 == 0;
		this.pc = value;
	}

	public int get() {
		return pc;
	}

	public void reset() {
		pc = 0;
	}
}

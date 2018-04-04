//processor memory
public class Memory extends DataChanges {
	public Memory() {
		super(1000);
	}

	public int cycle(int addr, int wdata, boolean mRead, boolean mWrite) {
		if(mWrite) {
			set(addr, wdata);
		}
		
		if(mRead) {
			return get(addr);
		}

		return 0;
	}
}

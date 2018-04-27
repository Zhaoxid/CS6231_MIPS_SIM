//mips control module
public class Control {

	private boolean rDist;
	private boolean Branch;
	private boolean mRead;
	private boolean MtoR;
	private boolean op1;
	private boolean op0;
	private boolean mWrite;
	private boolean ALUsrc;
	private boolean rWrite;

	public Control(Instruction instruction) {
		short opcode = instruction.getOpcode();

		if(instruction.is_r_type()) {
			rDist = true;
			rWrite = true;
			op1 = true;
		}
		
		else if(instruction.is_i_type()) {
			rWrite = true;
			op1 = true;
			ALUsrc = true;
		}
		
		else if(opcode == 35) {
			mRead = true;
			MtoR = true;
			rWrite = true;
			ALUsrc = true;
		}

		else if(opcode == 43) {
			mWrite = true;
			ALUsrc = true;
		}
		
		else if(opcode == 2) {
			Branch = true;
			op0 = true;
		}
		
		else if(opcode == 4) {
			Branch = true;
			op0 = true;
		}
	}

	public boolean isRegDist() {
		return rDist;
	}

	public boolean isBranch() {
		return Branch;
	}

	public boolean isMemRead() {
		return mRead;
	}

	public boolean isMemtoReg() {
		return MtoR;
	}

	public boolean isOp1() {
		return op1;
	}

	public boolean isOp0() {
		return op0;
	}

	public boolean isMemWrite() {
		return mWrite;
	}

	public boolean isALUsrc() {
		return ALUsrc;
	}

	public boolean isRegWrite() {
		return rWrite;
	}


}

//mips control module
public class Control {

	private boolean RegDist;
	private boolean Branch;
	private boolean MemRead;
	private boolean MemtoReg;
	private boolean op1;
	private boolean op0;
	private boolean MemWrite;
	private boolean ALUsrc;
	private boolean RegWrite;

	public Control(Instruction instruction) {
		short opcode = instruction.getOpcode();

		if(instruction.is_r_type()) {
			RegDist = true;
			RegWrite = true;
			op1 = true;
		}
		
		else if(instruction.is_i_type()) {
			RegWrite = true;
			op1 = true;
			ALUsrc = true;
		}
		
		else if(opcode == 35) {
			MemRead = true;
			MemtoReg = true;
			RegWrite = true;
			ALUsrc = true;
		}

		else if(opcode == 43) {
			MemWrite = true;
			ALUsrc = true;
		}

		else if(opcode == 4) {
			Branch = true;
			op0 = true;
		}
	}

	public boolean isRegDist() {
		return RegDist;
	}

	public boolean isBranch() {
		return Branch;
	}

	public boolean isMemRead() {
		return MemRead;
	}

	public boolean isMemtoReg() {
		return MemtoReg;
	}

	public boolean isOp1() {
		return op1;
	}

	public boolean isOp0() {
		return op0;
	}

	public boolean isMemWrite() {
		return MemWrite;
	}

	public boolean isALUsrc() {
		return ALUsrc;
	}

	public boolean isRegWrite() {
		return RegWrite;
	}


}

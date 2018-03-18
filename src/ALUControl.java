public class ALUControl {
	public static short getControl(boolean ALUOp1, boolean ALUOp0, short funct){
		if(!ALUOp1 && !ALUOp0) {
			return ALU.ADD;
		}
		if(ALUOp0) {
			return ALU.SUBTRACT;
		}
		
		switch(funct & 31) {
		case 0:
			return ALU.ADD;
		case 2:
			return ALU.SUBTRACT;
		case 3:
			return ALU.AND;
		case 4:
			return ALU.OR;
		case 7:
			return ALU.NOR;
		case 10:
			return ALU.SLT;
		case 24:
			return ALU.MULT;
		case 26:
			return ALU.DIV;
		}

		assert false;
		return 0;
	}
}

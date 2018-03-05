import java.util.NoSuchElementException;
import java.util.StringTokenizer;

/**
 * A mips instruction representation
 */
public class Instruction {
	private String repr;
	private short opcode = 0;
	private short funct = 0;
	private short rd = 0;
	private short rs = 0;
	private short rt = 0;
	private short imm = 0;
	private boolean r_type = false;
	private boolean i_type = false;
	private boolean is_exit;
	private boolean is_nop;

	/**
	 * Create a new mips instruction from a line of mips assembly
	 * @param line A line of valid mips assembly, within the supported subset
	 * @throws Exception if the instruction could not be parsed
	 */
	public Instruction(String line) throws Exception {
		repr = line;

		line = line.replaceAll(",", "");
		StringTokenizer tokens = new StringTokenizer(line, " ");
		String op = "", t1 = "", t2 = "", t3 = "";

		op = tokens.nextToken();

		try {
			t1 = tokens.nextToken();
			t2 = tokens.nextToken();
			t3 = tokens.nextToken();
		} catch(NoSuchElementException e) {}

		if(op.equalsIgnoreCase("add")) {
			funct = 0x20;
			r_type = true;
		} else if(op.equalsIgnoreCase("sub")) {
			funct = 0x22;
			r_type = true;
		} else if(op.equalsIgnoreCase("and")) {
			funct = 0x24;
			r_type = true;
		} else if(op.equalsIgnoreCase("or")) {
			funct = 0x25;
			r_type = true;
		} else if(op.equalsIgnoreCase("nor")) {
			funct = 0x27;
			r_type = true;
		} else if(op.equalsIgnoreCase("slt")) {
			funct = 0x2a;
			r_type = true;
		}

		else if(op.equalsIgnoreCase("lw")) {
			opcode = 0x23;
		} else if(op.equalsIgnoreCase("sw")) {
			opcode = 0x2b;
		}

		else if(op.equalsIgnoreCase("beq")) {
			opcode = 4;
		}

		else if(op.equalsIgnoreCase("nop")) {
			is_nop = true;
		}

		else if(op.equalsIgnoreCase("exit")) {
			is_exit = true;
		}


		// Parse additional parameters
		if(opcode == 0x23 || opcode == 0x2b) {
			rt = parseReg(t1);
			if(t2.indexOf('(') != -1) {
				rs = parseWrappedReg(t2);
				imm = parseWrappedOffset(t2);
			} else {
				rs = parseReg(t2);
				imm = 0;
			}
		} else if(r_type) {
			rd = parseReg(t1);
			rs = parseReg(t2);
			rt = parseReg(t3);

		} else if(opcode == 4) {
			rs = parseReg(t1);
			rt = parseReg(t2);
			imm = parseAddr(t3);
		}

	}


	private short parseWrappedOffset(String token) {
		return parseAddr(token.substring(0, token.indexOf('(')));
	}


	private short parseWrappedReg(String token) {
		try {
			return parseReg(token.substring(token.indexOf('(')+1, token.indexOf(')')));
		} catch (Exception e) {
			return 0;
		}
	}

	private short parseAddr(String address) {
		if(address.contains("x")) {
			return Short.parseShort(
					address.substring(address.indexOf('x')+1), 16);
		}
		return Short.parseShort(address);
	}

	/**
	 * Parse a string representation of a mips register into a register number
	 * @param register the string representation, such as "$t0" or "0x3" or "0"
	 * @return the register number
	 * @throws Exception if the string representation could not be parsed
	 */
	private short parseReg(String register) throws Exception {
		if(register.charAt(0) == '$') {
			if(register.equalsIgnoreCase("$zero")) {
				return 0;
			} else if(register.equalsIgnoreCase("$gp")) {
				return 28;
			} else if(register.equalsIgnoreCase("$sp")) {
				return 29;
			} else if(register.equalsIgnoreCase("$fp")) {
				return 30;
			} else if(register.equalsIgnoreCase("$ra")) {
				return 31;
			}

			char prefix = register.charAt(1);
			short number = Short.parseShort(register.substring(2));
			switch(prefix) {
			case 'v':
				number += 2;
				break;
			case 'a':
				number += 4;
				break;
			case 't':
				number += 8;
				if(number >= 16) {
					number += 8;
				}
				break;
			case 's':
				number += 16;
				break;

			default:
				throw new Exception("Invalid register " + register);
			}
			assert register.equals(RegisterFile.name(number));
			return number;
		}

		return parseAddr(register);
	}


	public String getRepr() {
		return repr;
	}


	public short getOpcode() {
		return opcode;
	}


	public short getFunct() {
		return funct;
	}


	public short getRd() {
		return rd;
	}


	public short getRs() {
		return rs;
	}


	public short getRt() {
		return rt;
	}


	public short getImm() {
		return imm;
	}


	public boolean is_r_type() {
		return r_type;
	}


	public boolean isExit() {
		return is_exit;
	}


	public boolean isNop() {
		return is_nop;
	}

	@Override
	public String toString() {
		return String.format("%s", repr);
	}

	public String representation(boolean hex) {
		if(r_type) {
			if(hex) {
				return String.format("%-22s",repr);
			}
			return String.format("%-22s", repr);
		} else {
			if(hex) {
				return String.format("%-22s (OP:0x%x, RS:0x%x, RT:0x%x, IMM:0x%x)", 
						repr, opcode, rs, rt, imm);
			}
			return String.format("%-22s (OP:%d, RS:%d, RT:%d, IMM:%d)", 
					repr, opcode, rs, rt, imm);
		}

	}
}

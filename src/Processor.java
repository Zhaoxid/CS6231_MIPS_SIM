import java.util.ArrayList;
import java.util.List;

public class Processor {

	private ProgramCounter pc;

	private Register register;
	private MemoryFile memory;
	private ALU alu;

	private InstructionStorage instructions;

	public Processor() {
		pc = new ProgramCounter();
		instructions = new InstructionStorage();
		register = new Register();
		alu = new ALU();
		memory = new MemoryFile();
	}

	public void setInstructionSet(List<Instruction> instructions) {
		this.instructions.load(new ArrayList<Instruction>(instructions));
		reset();
	}

	public void reset() {
		pc.reset();
		register.reset();
		memory.reset();
	}

	public void step() {
		Instruction i;
		int alu_out = 0;
		boolean alu_zero = false;
		int data_out = 0;
		int write_data;
		int regData1 = 0;
		int regData2 = 0;
		int new_pc = pc.get();
		int branch_pc;

		if(isDone()) {
			return;
		}

		//5 Stages
		
		//Fetch
		i = instructions.fetch(pc);
		Control control = new Control(i);

		//Decode, set register
		int writeReg = mux(i.getRt(), i.getRd(), control.isRegDist());
		register.setRegisters(i.getRs(), i.getRt(), writeReg);
		regData1 = register.readData1();
		regData2 = register.readData2();
		
		//Execute
		alu.setOperation(ALUControl.getControl(control.isOp1(), control.isOp0(), i.getrOp()),
						 mux(regData2, i.getImm(), control.isALUsrc()), regData1);
		
		alu_out = alu.getOut();	
		alu_zero = alu.isZero();

		//Mem
		data_out = memory.cycle(alu_out, regData2, control.isMemRead(), control.isMemWrite());

		//Writeback
		write_data = mux(alu_out, data_out, control.isMemtoReg());
		register.write(control.isRegWrite(), write_data);


		new_pc += 4; //PC+=4
		branch_pc = new_pc + (i.getImm() << 2);
		new_pc = mux(new_pc, branch_pc, control.isBranch() && alu_zero);
		pc.set(new_pc);
	}

	private int mux(int value1, int value2, boolean getSecond) {
		if(getSecond) {
			return value2;
		}
		return value1;
	}

	//returns true when done
	public boolean isDone() {
		return pc.get() >= instructions.length() || instructions.fetch(pc).isExit();
	}

	public int getPcValue() {
		return pc.get();
	}

	public int[] getRegisters() {
		return register.getRawData();
	}

	public int[] getMemory() {
		return memory.getRawData();
	}

	public List<Integer> getChangedRegisters() {
		return register.getChangedIndices();
	}

	public List<Integer> getChangedMemory() {
		return memory.getChangedIndices();
	}


}

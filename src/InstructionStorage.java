import java.util.List;
import java.util.ListIterator;

//for storing instructions
public class InstructionStorage {
	private Instruction[] instructionList = {};

	public void load(List<Instruction> instructions) {
		
		this.instructionList = new Instruction[instructions.size()];		
		ListIterator<Instruction> it = instructions.listIterator();
		
		for(int i = 0; i < instructions.size(); i++) 
			this.instructionList[i] = it.next();
		
	}

	public Instruction fetch(ProgramCounter pc) {
		return instructionList[pc.get() / 4];
	}

	public int length() {
		return instructionList.length * 4;
	}
}

//register
public class Register extends DataChanges {
	private int rr1;
	private int rr2;
	private int wr;

	public Register() {
		super(32);
	}

	public void setRegisters(int read1, int read2, int write) {
		rr1 = read1;
		rr2 = read2;
		wr = write;
	}

	public int readData1() {
		return get(rr1);
	}

	public int readData2() {
		return get(rr2);
	}

	public void write(boolean RegWrite, int data) {
		if(RegWrite) {
			set(wr, data);
		}
	}

	@Override
	protected int get(int index) {
		if(index == 0) {
			return 0; // $zero register
		} 
		return super.get(index);
	}

	@Override
	protected void set(int index, int value) {
		if(index == 0) {
			return;
		}
		super.set(index, value);
	}

	public static String name(int index) {
		String[] names = {
				"$zero",//0
				"$at",//1
				
				"$v0",//2
				"$v1",//3
				
				"$a0",//4
				"$a1",//5
				"$a2",//6
				"$a3",//7
				
				"$r0",//8 
				"$r1",//9
				"$r2",//10
				"$r3",//11
				"$r4",//12
				"$r5",//13
				"$r6",//14
				"$r7",//15
				"$s0",//16
				
				"$s1",//17
				"$s2",//18
				"$s3",//19
				"$s4",//20
				"$s5",//21
				"$s6",//22
				"$s7",//23
				"$t8",//24
				
				"$t9",//25
				"$k0",//26
				"$k1",//27
				"$gp",//28
				"$sp",//29
				"$fp",//30
				"$ra",//31
				"$one"//32
		};
		return names[index];
	}
}

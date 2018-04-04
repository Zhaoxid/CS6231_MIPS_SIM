import java.util.ArrayList;
import java.util.List;

//Keep track of changed values
public abstract class DataChanges {
	private int[] data;
	private List<Integer> changedFields;

	public DataChanges(int size) {
		data = new int[size];
		reset();
	}

	protected int get(int index) {
		return data[index];
	}

	public void reset() {
		changedFields = new ArrayList<Integer>();
		
		for(int i = 0; i < data.length; i++) 
			data[i] = 0;
	}

	protected void set(int index, int value) {
		removeDups(index);
		data[index] = value;
	}

	public int[] getRawData() {
		return data.clone();
	}

	private void removeDups(int index) {
		if(changedFields.contains(index)) 
			changedFields.remove(changedFields.indexOf(index));
		changedFields.add(0, index);
	}
	
	public List<Integer> getChangedIndex() {
		return new ArrayList<Integer>(changedFields);
	}

}

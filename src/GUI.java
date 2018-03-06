import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 * A gui for displaying instructions, register values and memory values
 * as well as providing means to load instructions and advance the simulation
 */
@SuppressWarnings({"rawtypes", "unchecked"})
public class GUI {
	private JFrame frame;
	private GUIListener listener;
	private String filename;
	private JList instructionList;
	private JList registerList;
	private JList memoryList;
	private JTextField pcPane;


	public GUI() {
		Font font = new Font("Arial", Font.PLAIN, 13);

		instructionList = new JList();
		instructionList.setFont(font);
		JScrollPane instructionPane = new JScrollPane(instructionList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JPanel leftPanel = new JPanel();

		registerList = new JList();
		registerList.setFont(font);
		memoryList = new JList();
		memoryList.setFont(font);
		
		JScrollPane registerPane = new JScrollPane(registerList);
		JScrollPane dataPane = new JScrollPane(memoryList);
		JPanel rightPanel = new JPanel();
		
		JLabel lblNewLabel = new JLabel("Register List");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerPane.setColumnHeaderView(lblNewLabel);
		JPanel topPanel = new JPanel();

		final JFileChooser fileChooser = new JFileChooser(System.getProperty("user.dir"));

		pcPane = new JTextField(20);
		pcPane.setEditable(false);
		pcPane.setBackground(Color.WHITE);
		pcPane.setHorizontalAlignment(SwingConstants.CENTER);
		pcPane.setBorder(new EmptyBorder(0, 0, 0, 5));
		Font pcFont = new Font("Arial", Font.BOLD, 15);
		pcPane.setFont(pcFont);
		JPanel botPanel = new JPanel();


		frame = new JFrame("MIPS Simulator - Team 2");
		frame.getContentPane().add(leftPanel, BorderLayout.CENTER);
		GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addComponent(instructionPane, GroupLayout.PREFERRED_SIZE, 305, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		gl_leftPanel.setVerticalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(instructionPane, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
		);
		
		JLabel lblNewLabel_1 = new JLabel("Instruction List");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		instructionPane.setColumnHeaderView(lblNewLabel_1);
		leftPanel.setLayout(gl_leftPanel);
		frame.getContentPane().add(rightPanel, BorderLayout.EAST);
		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addComponent(registerPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addComponent(dataPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		gl_rightPanel.setVerticalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(registerPane, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
				.addComponent(dataPane, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
		);
		
		JLabel lblNewLabel_2 = new JLabel("Memory List");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		dataPane.setColumnHeaderView(lblNewLabel_2);
		rightPanel.setLayout(gl_rightPanel);
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		JButton chooseButton = new JButton("Choose Input Text File");
		
				chooseButton.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						int returnVal = fileChooser.showOpenDialog(frame);
		
						if (returnVal == JFileChooser.APPROVE_OPTION) {
							filename = fileChooser.getSelectedFile().getPath();
						}
					}
				});
		JButton loadButton = new JButton("Load");
						GroupLayout gl_topPanel = new GroupLayout(topPanel);
						gl_topPanel.setHorizontalGroup(
							gl_topPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_topPanel.createSequentialGroup()
									.addGap(549)
									.addComponent(chooseButton)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(loadButton)
									.addGap(49))
						);
						gl_topPanel.setVerticalGroup(
							gl_topPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_topPanel.createSequentialGroup()
									.addGap(18)
									.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
										.addComponent(chooseButton)
										.addComponent(loadButton))
									.addContainerGap(18, Short.MAX_VALUE))
						);
						topPanel.setLayout(gl_topPanel);
		
				loadButton.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						if(filename != null){
							listener.onLoad(filename);
						}
					}
				});
		frame.getContentPane().add(botPanel, BorderLayout.SOUTH);
		
		JButton stepButton = new JButton("Step");
		
				stepButton.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						listener.onStep();			
					}
				});
		JButton runButton = new JButton("Run");
		
				runButton.addActionListener(new ActionListener() {		
					@Override
					public void actionPerformed(ActionEvent e) {
						listener.onRun();
					}
				});
		JButton stopButton = new JButton("Stop");
		
				stopButton.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						listener.onStop();	
					}
				});
		JButton resetButton = new JButton("Reset");
		
				resetButton.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						listener.onReset();	
					}
				});
		GroupLayout gl_botPanel = new GroupLayout(botPanel);
		gl_botPanel.setHorizontalGroup(
			gl_botPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_botPanel.createSequentialGroup()
					.addGap(55)
					.addComponent(pcPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addGap(241)
					.addComponent(stepButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(runButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(stopButton)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(resetButton)
					.addContainerGap())
		);
		gl_botPanel.setVerticalGroup(
			gl_botPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_botPanel.createSequentialGroup()
					.addContainerGap(10, Short.MAX_VALUE)
					.addGroup(gl_botPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pcPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(resetButton)
						.addComponent(stopButton)
						.addComponent(runButton)
						.addComponent(stepButton))
					.addContainerGap())
		);
		botPanel.setLayout(gl_botPanel);
		frame.setSize(839, 486);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		setPc(0);
	}

	public void setGUIListener(GUIListener listener) {
		this.listener = listener;
	}

	public void setInstructionListModel(ListModel model){
		instructionList.setModel(model);
	}

	public void setRegisterListModel(ListModel model){
		registerList.setModel(model);
	}

	public void setMemoryListModel(ListModel model){
		memoryList.setModel(model);
	}

	public interface GUIListener {
		public void onLoad(String filename);
		public void onStep();
		public void onRun();
		public void onStop();
		public void onReset();
		public void onHex();
		public void onDec();
	}

	public void selectInstruction(int index) {
		instructionList.setSelectedIndex(index);
	}

	public void clearInstructionSelection() {
		instructionList.clearSelection();
	}

	public void setPc(int pc) {
		pcPane.setText("Program Counter: " + pc);
	}
}

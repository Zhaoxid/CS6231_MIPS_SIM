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
import javax.swing.JRadioButton;
import javax.swing.JTextArea;

@SuppressWarnings({"rawtypes", "unchecked"})
public class GUI {
	private JFrame frame;
	private GUIListener listener;
	private String filename;
	private JList instructionList;
	private JList registerList;
	private JList memoryList;
	private JTextField pcPane;
	private JTextArea infoPane;

	public GUI() {
		Font font = new Font("Arial", Font.PLAIN, 13);

		instructionList = new JList();
		instructionList.setFont(font);
		JScrollPane instructionPane = new JScrollPane(instructionList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
			      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JPanel leftPanel = new JPanel();

		registerList = new JList();
		registerList.setValueIsAdjusting(true);
		registerList.setFont(font);
		memoryList = new JList();
		memoryList.setValueIsAdjusting(true);
		memoryList.setFont(font);
		
		JScrollPane registerPane = new JScrollPane(registerList);
		JScrollPane dataPane = new JScrollPane(memoryList);
		JPanel rightPanel = new JPanel();
		
		JLabel lblNewLabel = new JLabel("Register List");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		registerPane.setColumnHeaderView(lblNewLabel);

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
		frame.getContentPane().add(leftPanel, BorderLayout.WEST);
		GroupLayout gl_leftPanel = new GroupLayout(leftPanel);
		gl_leftPanel.setHorizontalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_leftPanel.createSequentialGroup()
					.addComponent(instructionPane, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_leftPanel.setVerticalGroup(
			gl_leftPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(instructionPane, GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
		);
		
		JLabel lblNewLabel_1 = new JLabel("Instruction List");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		instructionPane.setColumnHeaderView(lblNewLabel_1);
		leftPanel.setLayout(gl_leftPanel);
		frame.getContentPane().add(rightPanel, BorderLayout.CENTER);
		
		JButton btnChoose = new JButton("Choose input file");
		
		btnChoose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = fileChooser.showOpenDialog(frame);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					filename = fileChooser.getSelectedFile().getPath();
				}
				if(filename != null){
					listener.onLoad(filename);
				}
			}
		});
		
		JButton stepButton = new JButton("Step");
		
				stepButton.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						listener.onStep();			
					}
				});
		JButton resetButton = new JButton("Reset");
		
				resetButton.addActionListener(new ActionListener() {
		
					@Override
					public void actionPerformed(ActionEvent e) {
						listener.onReset();	
					}
				});
		
		JLabel lblNewLabel_2 = new JLabel("Memory List");
		lblNewLabel_2.setFont(new Font("Lucida Grande", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		dataPane.setColumnHeaderView(lblNewLabel_2);
		
		infoPane = new JTextArea();
		infoPane.setEditable(false);
		
		GroupLayout gl_rightPanel = new GroupLayout(rightPanel);
		gl_rightPanel.setHorizontalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(infoPane, GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
						.addGroup(gl_rightPanel.createSequentialGroup()
							.addGroup(gl_rightPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_rightPanel.createSequentialGroup()
									.addComponent(stepButton)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(resetButton))
								.addComponent(registerPane, GroupLayout.PREFERRED_SIZE, 170, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_rightPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_rightPanel.createSequentialGroup()
									.addGap(10)
									.addComponent(dataPane, GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE))
								.addGroup(gl_rightPanel.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnChoose)))))
					.addContainerGap())
		);
		gl_rightPanel.setVerticalGroup(
			gl_rightPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_rightPanel.createSequentialGroup()
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(registerPane, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
						.addComponent(dataPane, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(infoPane, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_rightPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(stepButton)
						.addComponent(resetButton)
						.addComponent(btnChoose))
					.addGap(16))
		);
		gl_rightPanel.setAutoCreateContainerGaps(true);
		gl_rightPanel.setAutoCreateGaps(true);
		rightPanel.setLayout(gl_rightPanel);
		frame.getContentPane().add(botPanel, BorderLayout.SOUTH);
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
		
		JButton btnHelp = new JButton("Help");
		
		JRadioButton rdbtnForwarding = new JRadioButton("forwarding");
		GroupLayout gl_botPanel = new GroupLayout(botPanel);
		gl_botPanel.setHorizontalGroup(
			gl_botPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_botPanel.createSequentialGroup()
					.addGap(15)
					.addComponent(pcPane, GroupLayout.PREFERRED_SIZE, 210, GroupLayout.PREFERRED_SIZE)
					.addGap(69)
					.addComponent(runButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(stopButton)
					.addGap(17)
					.addComponent(rdbtnForwarding)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnHelp)
					.addGap(46))
		);
		gl_botPanel.setVerticalGroup(
			gl_botPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_botPanel.createSequentialGroup()
					.addContainerGap(10, Short.MAX_VALUE)
					.addGroup(gl_botPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(pcPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(runButton)
						.addComponent(stopButton)
						.addComponent(rdbtnForwarding)
						.addComponent(btnHelp))
					.addContainerGap())
		);
		botPanel.setLayout(gl_botPanel);
		frame.setSize(658, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		setInfo();
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
	
	public void setInfo() {
		infoPane.insert("Forwarding: Enabled \n" + 
						"Stalls: 0 \n" + 
						"Hazards: 0 \n" +
						"These features haven't been implemented yet.\n", 0);
	}
}

package buddy;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TestUI {
	
	private Buddy buddy = new Buddy();

	private JFrame frame;
	private JTextField textFieldPageNum;
	private JTextField textFieldReqSize;
	private JTextField textFieldRelNum;
	private JTextField textFieldGroupNum;
	private JTextField textFieldBeginAddr;
	
	
	
	public static int string_int(String str)
	{
		int rt = 0;
		try {
			rt = Integer.parseInt(str);
		}catch(NumberFormatException e)
		{
			e.printStackTrace();
		}
		return rt;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestUI window = new TestUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u57FA\u4E8E\u4F19\u4F34\u5806\u7B97\u6CD5\u7684\u5185\u5B58\u5206\u914D\u91CA\u653E\u7684\u6A21\u62DF\u5B9E\u73B0");

		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel lblNewLabel = new JLabel("\u5185\u5B58\u7269\u7406\u7A7A\u95F4(MB)");
		
		JLabel lblNewLabel_1 = new JLabel("\u9875\u6846\u5927\u5C0F(KB)");
		
		JLabel lblNewLabel_3 = new JLabel("\u521D\u59CB\u5206\u914D");
		
		JLabel lblNewLabel_4 = new JLabel("\u64CD\u4F5C\u663E\u793A");
		
		JLabel lblNewLabel_5 = new JLabel("\u7A7A\u95F2\u5757\u7EC4");
		
		JLabel lblNewLabel_7 = new JLabel("\u7533\u8BF7\u9875\u6846\u6570");
		
		JLabel lblNewLabel_8 = new JLabel("\u91CA\u653E\u7A7A\u95F4");
		
		JLabel lblNewLabel_9 = new JLabel("\u9875\u6846\u53F7");
		
		JLabel lblNewLabel_10 = new JLabel("\u5757\u7EC4\u53F7");
		
		JComboBox comboBoxMemSize = new JComboBox();
		comboBoxMemSize.setModel(new DefaultComboBoxModel(new String[] {"4", "256", "512"}));
		
		JComboBox comboBoxPageSize = new JComboBox();
		comboBoxPageSize.setModel(new DefaultComboBoxModel(new String[] {"2", "1", "4"}));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		
		textFieldPageNum = new JTextField();
		textFieldPageNum.setColumns(10);
	
		textFieldReqSize = new JTextField();
		textFieldReqSize.setColumns(10);

		textFieldRelNum = new JTextField();
		textFieldRelNum.setColumns(10);
		
		textFieldGroupNum = new JTextField();
		textFieldGroupNum.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		
		JTextArea textAreaFreeGroup = new JTextArea();
		textAreaFreeGroup.setEditable(false);
		scrollPane.setViewportView(textAreaFreeGroup);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		JTextArea textAreaOperationShow = new JTextArea();
		textAreaOperationShow.setLineWrap(true);
		textAreaOperationShow.setEditable(false);
		scrollPane_2.setViewportView(textAreaOperationShow);
		
		JButton btnSetMemPage = new JButton("\u8BBE\u7F6E");
		btnSetMemPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buddy.memsize = string_int((String) comboBoxMemSize.getSelectedItem());
				buddy.pagesize = string_int((String) comboBoxPageSize.getSelectedItem());
				buddy.init_mem(textAreaOperationShow);
				//buddy.printGroupsUI(textAreaFreeGroup);
				buddy.printGroupsUI(textAreaFreeGroup);

				// for test
				//buddy.request_mem(7);
			}
		});
		
		JButton btnInitAlloc = new JButton("\u521D\u59CB\u5206\u914D");
		btnInitAlloc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int begin = string_int((String) textFieldBeginAddr.getText());
				int powsize = string_int((String) textFieldPageNum.getText());
				buddy.init_request_mem(textAreaOperationShow, begin, powsize);
				buddy.printGroupsUI(textAreaFreeGroup);
			}
		});
		
		JButton btnRequestMem = new JButton("\u7533\u8BF7");
		btnRequestMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int reqsize = string_int((String) textFieldReqSize.getText());
				//buddy.request_mem(textAreaOperationShow, reqsize);
				buddy.request_mem(textAreaOperationShow, reqsize);
				//buddy.printGroupsUI(textAreaFreeGroup);
				buddy.printGroupsUI(textAreaFreeGroup);
			}
		});
		
		JButton btnReleaseMem = new JButton("\u91CA\u653E");
		btnReleaseMem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int relnum = string_int((String)textFieldRelNum.getText());
				int groupnum = string_int((String)textFieldGroupNum.getText());
				//buddy.release_mem(textAreaOperationShow, relnum, groupnum);
				buddy.release_mem(textAreaOperationShow, relnum, groupnum);
				buddy.printGroupsUI(textAreaFreeGroup);
			}
		});
		
		JLabel lblNewLabel_2 = new JLabel("\u5F00\u59CB\u5730\u5740");
		
		JLabel lblNewLabel_6 = new JLabel("\u9875\u6846\u6570");
		
		textFieldBeginAddr = new JTextField();
		textFieldBeginAddr.setColumns(10);
		


		

		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel_4, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 270, GroupLayout.PREFERRED_SIZE))
							.addGap(26)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(lblNewLabel_5, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(comboBoxMemSize, GroupLayout.PREFERRED_SIZE, 133, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE)
									.addGap(18)
									.addComponent(comboBoxPageSize, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lblNewLabel_8, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel_3, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE)
										.addComponent(lblNewLabel_7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addGap(64)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_9, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textFieldRelNum, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
											.addGap(18)
											.addComponent(lblNewLabel_10, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textFieldGroupNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
										.addComponent(textFieldReqSize)
										.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
											.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(textFieldBeginAddr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
											.addComponent(lblNewLabel_6, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(textFieldPageNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
									.addGap(52)
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(btnRequestMem, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnInitAlloc, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE)
										.addComponent(btnReleaseMem, GroupLayout.PREFERRED_SIZE, 97, GroupLayout.PREFERRED_SIZE))))
							.addGap(18)
							.addComponent(btnSetMemPage, GroupLayout.PREFERRED_SIZE, 130, GroupLayout.PREFERRED_SIZE)))
					.addGap(26))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(comboBoxMemSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1)
						.addComponent(comboBoxPageSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSetMemPage))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnInitAlloc)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_3)
							.addComponent(textFieldPageNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNewLabel_2)
							.addComponent(lblNewLabel_6)
							.addComponent(textFieldBeginAddr, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblNewLabel_7)
							.addComponent(textFieldReqSize, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnRequestMem))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_8)
								.addComponent(textFieldGroupNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(textFieldRelNum, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblNewLabel_10)
								.addComponent(lblNewLabel_9))
							.addGap(29)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_5)
								.addComponent(lblNewLabel_4)))
						.addComponent(btnReleaseMem))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 376, GroupLayout.PREFERRED_SIZE)))
					.addGap(95))
		);
		

		

		


		frame.getContentPane().setLayout(groupLayout);
	}
}

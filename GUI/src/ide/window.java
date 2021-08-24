package ide;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JRadioButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTextPane;
import java.awt.GridLayout;
import javax.swing.JToolBar;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class window {

	private StringBuilder lines;
	
	private JFrame frmRobotichandIde;
	private JTextPane txtpnErrores;
	private JTextArea LineCounterArea;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window window = new window();
					window.frmRobotichandIde.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public window() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRobotichandIde = new JFrame();
		frmRobotichandIde.setTitle("RoboticHand IDE");
		frmRobotichandIde.setBounds(100, 100, 939, 624);
		frmRobotichandIde.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txtpnErrores = new JTextPane();
		txtpnErrores.setEditable(false);
		txtpnErrores.setText("Errores");
		
		JButton btnNewButton = new JButton("Compilar");
		btnNewButton.setAction(action);
		
		JButton btnNewButton_1 = new JButton("Compilar y ejecutar");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JTextArea FileArea = new JTextArea();
		FileArea.setEditable(false);
		FileArea.setLineWrap(true);
		GroupLayout groupLayout = new GroupLayout(frmRobotichandIde.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(FileArea, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtpnErrores, GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 362, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 401, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtpnErrores, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
						.addComponent(FileArea, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
					.addContainerGap())
		);
		lines = new StringBuilder();
		JTextArea codeArea = new JTextArea();
		codeArea.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					String[] numLastLine = codeArea.getText().split("\n");
					int lineCount = numLastLine.length;
					lines.append("  " + String.valueOf(lineCount) + "   ");
					lines.append("\n");
					LineCounterArea.setText(lines.toString());
		        }
			}
		});
		codeArea.setLineWrap(true);
		scrollPane.setViewportView(codeArea);
		
		LineCounterArea = new JTextArea();
		LineCounterArea.setEditable(false);
		scrollPane.setRowHeaderView(LineCounterArea);
		frmRobotichandIde.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		frmRobotichandIde.setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("File");
		menuBar.add(mntmNewMenuItem);
	}
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Compilar");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			txtpnErrores.setText("hello world");
		}
	}
}

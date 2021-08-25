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

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rtextarea.RTextScrollPane;

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
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class window {

	private StringBuilder lines;
	
	private JFrame frmRobotichandIde;
	private JTextPane txtpnErrores;
	private final Action action = new SwingAction();
	private JPanel panel;

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
	public window(){
		initialize();
		RSyntax();
	}
	
	public void RSyntax(){
		RSyntaxTextArea textArea = new RSyntaxTextArea();
		panel.setLayout(new BorderLayout(0, 0));
		
		RTextScrollPane sp = new RTextScrollPane(textArea);
	    panel.add(sp);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRobotichandIde = new JFrame();
		frmRobotichandIde.getContentPane().setBackground(Color.DARK_GRAY);
		frmRobotichandIde.setBackground(Color.DARK_GRAY);
		frmRobotichandIde.setTitle("RoboticHand IDE");
		frmRobotichandIde.setBounds(100, 100, 939, 624);
		frmRobotichandIde.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txtpnErrores = new JTextPane();
		txtpnErrores.setEditable(false);
		txtpnErrores.setText("Errores");
		
		panel = new JPanel();
		
		JButton btnNewButton = new JButton("Compilar");
		btnNewButton.setAction(action);
		
		JButton btnNewButton_1 = new JButton("Compilar y ejecutar");
		
		JTextArea FileArea = new JTextArea();
		FileArea.setLineWrap(true);
		FileArea.setForeground(Color.LIGHT_GRAY);
		FileArea.setBackground(Color.DARK_GRAY);
		FileArea.setEditable(false);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.LIGHT_GRAY);
		
		
		GroupLayout groupLayout = new GroupLayout(frmRobotichandIde.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(FileArea, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(28)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtpnErrores, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE))
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 406, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtpnErrores, GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE))
						.addComponent(separator, GroupLayout.PREFERRED_SIZE, 530, GroupLayout.PREFERRED_SIZE)
						.addComponent(FileArea, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE))
					.addContainerGap())
		);
		lines = new StringBuilder();
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

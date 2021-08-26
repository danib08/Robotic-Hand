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
import javax.swing.JFileChooser;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.Icon;
import javax.swing.LayoutStyle.ComponentPlacement;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import java.io.File;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Font;

public class window {

	private StringBuilder lines;
	
	private JFrame frmRobotichandIde;
	private JTextPane txtpnErrores;
	private final Action compileAction = new compileAction();
	private JPanel panel;
	private final Action openFile = new openFile();
	private final Action action = new runAction();
	private final JFileChooser fileChooser = new JFileChooser();

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
	/**
	 * Shows code lines in the text area
	 */
	public void RSyntax(){
		RSyntaxTextArea textArea = new RSyntaxTextArea();
		panel.setLayout(new BorderLayout(0, 0));
		
		RTextScrollPane sp = new RTextScrollPane(textArea);
	    panel.add(sp);
	    
	    try {
		     Theme theme = Theme.load(getClass().getResourceAsStream(
		           "/org/fife/ui/rsyntaxtextarea/themes/monokai.xml"));
		     theme.apply(textArea);
		  } catch (IOException ioe) { // Never happens
		     ioe.printStackTrace();
		  }
	}
	
	/**
	 * Open a file and show up in text area
	 */
	public void openFile() {
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRobotichandIde = new JFrame();
		frmRobotichandIde.setFont(new Font("Dialog", Font.PLAIN, 13));
		frmRobotichandIde.setForeground(Color.BLACK);
		frmRobotichandIde.setIconImage(Toolkit.getDefaultToolkit().getImage(window.class.getResource("/icons/robotic-hand.png")));
		frmRobotichandIde.getContentPane().setBackground(Color.DARK_GRAY);
		frmRobotichandIde.setBackground(Color.DARK_GRAY);
		frmRobotichandIde.setTitle("RoboticHand IDE");
		frmRobotichandIde.setBounds(100, 100, 939, 624);
		frmRobotichandIde.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		txtpnErrores = new JTextPane();
		txtpnErrores.setForeground(Color.LIGHT_GRAY);
		txtpnErrores.setBackground(Color.DARK_GRAY);
		txtpnErrores.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		txtpnErrores.setEditable(false);
		txtpnErrores.setText("Errores");
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		
		JButton btnNewButton = new JButton();
		btnNewButton.setAction(compileAction);
		
		JButton btnNewButton_1 = new JButton("Compilar y ejecutar");
		btnNewButton_1.setAction(action);
		
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
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton, GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE))
						.addComponent(txtpnErrores, GroupLayout.DEFAULT_SIZE, 701, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(FileArea, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(txtpnErrores, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
					.addContainerGap())
		);
		lines = new StringBuilder();
		frmRobotichandIde.getContentPane().setLayout(groupLayout);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.DARK_GRAY);
		frmRobotichandIde.setJMenuBar(menuBar);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("File");
		mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem.setAction(openFile);
		mntmNewMenuItem.setForeground(Color.LIGHT_GRAY);
		mntmNewMenuItem.setBackground(Color.DARK_GRAY);
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
	
	// Button actions 
	private class compileAction extends AbstractAction {
		Icon checkIcon = new ImageIcon(window.class.getResource("/icons/check.png"));
		public compileAction() {
			putValue(NAME, "Compilar");
			putValue(SHORT_DESCRIPTION, "Compila el programa");
			putValue( Action.SMALL_ICON, checkIcon );
		}
		public void actionPerformed(ActionEvent e) {
			txtpnErrores.setText("hello world");
		}
	}
	private class openFile extends AbstractAction {
		Icon openIcon = new ImageIcon(window.class.getResource("/icons/open.png"));
		public openFile() {
			putValue(NAME, "Abrir archivo");
			putValue(SHORT_DESCRIPTION, "Abre un archivo");
			putValue( Action.SMALL_ICON, openIcon );
		}
		public void actionPerformed(ActionEvent e) {
			int result = fileChooser.showOpenDialog(frmRobotichandIde);
            if (result==JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    //panel.setPage(file.toURI().toURL());
                } catch(Exception e1) {
                    e1.printStackTrace();
                }
            }
		}
	}
	private class runAction extends AbstractAction {
		Icon runIcon = new ImageIcon(window.class.getResource("/icons/play.png"));
		public runAction() {
			putValue(NAME, "Compilar y ejecutar");
			putValue(SHORT_DESCRIPTION, "Compila y ejecuta el programa");
			putValue( Action.SMALL_ICON, runIcon );
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}

package ide;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
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

import com.robotichand.Interprete.Main;

import org.apache.commons.io.FileUtils;
import java.nio.charset.Charset;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;

import javax.swing.JMenuItem;
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
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.ImageIcon;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.ScrollPaneConstants;

public class window {
	
	private JFrame frmRobotichandIde;
	private JTextArea txtpnErrores;
	private JPanel panel;
	private final Action openFile = new openFile();
	private final Action action = new runAction();
	private final Action compile = new compileAction();
	private RSyntaxTextArea textArea;
	private final JFileChooser fileChooser = new JFileChooser();
	private DefaultMutableTreeNode root;
	private DefaultTreeModel treeModel;
	private File file;
	private RSyntaxTextArea input;
    private RSyntaxTextArea output;
    private PrintStream standardOut;



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
	 * @throws IOException 
	 */
	public window() throws IOException{
		initialize();
		RSyntax();
	}
	/**
	 * Shows code lines in the text area
	 * @throws IOException 
	 */
	public void RSyntax() throws IOException{
		textArea = new RSyntaxTextArea();
                
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
	    BufferedReader in = new BufferedReader(new FileReader(file));
	    String line = in.readLine();
	    while(line != null){
	      textArea.append(line + "\n");
	      line = in.readLine();
	    }
	    in.close();
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
		
		JPanel errorPanel = new JPanel();
		
		errorPanel.setBackground(Color.DARK_GRAY);
		errorPanel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollpane = new JScrollPane(txtpnErrores);
		errorPanel.add(scrollpane);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		
		JButton btnNewButton_1 = new JButton("Compilar y ejecutar");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1.setAction(action);
		
		JButton btnNewButton_1_1 = new JButton("Compilar");
		btnNewButton_1_1.setForeground(Color.WHITE);
		btnNewButton_1_1.setBackground(Color.DARK_GRAY);
		btnNewButton_1_1.setAction(compile);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBackground(Color.LIGHT_GRAY);

        File fileRoot = new File("../Interprete/test/");
        root = new DefaultMutableTreeNode(new FileNode(fileRoot));
        treeModel = new DefaultTreeModel(root);
		
		JTree tree = new JTree(treeModel);
		tree.setShowsRootHandles(true);

        CreateChildNodes ccn = new CreateChildNodes(fileRoot, root);
        new Thread(ccn).start();

		tree.setBackground(Color.DARK_GRAY);
		
	
		GroupLayout groupLayout = new GroupLayout(frmRobotichandIde.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(tree, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnNewButton_1_1, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
							.addGap(7)
							.addComponent(btnNewButton_1, GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE))
						.addComponent(errorPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(separator, GroupLayout.DEFAULT_SIZE, 557, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton_1_1, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(errorPanel, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(tree, GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
					.addContainerGap())
		);
		
		txtpnErrores = new JTextArea();
		errorPanel.add(txtpnErrores);
		txtpnErrores.setForeground(Color.LIGHT_GRAY);
		txtpnErrores.setBackground(Color.DARK_GRAY);
		txtpnErrores.setBorder(new LineBorder(new Color(192, 192, 192), 2));
		txtpnErrores.setEditable(false);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(txtpnErrores));
		
		// keeps reference of standard output stream
        standardOut = System.out;
		
		System.setOut(printStream);
		System.setErr(printStream);

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
		/**
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Guardar");
		mntmNewMenuItem_1.setIcon(new ImageIcon(window.class.getResource("/icons/save.png")));
		mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_1.setForeground(Color.LIGHT_GRAY);
		mntmNewMenuItem_1.setBackground(Color.DARK_GRAY);
		menuBar.add(mntmNewMenuItem_1);*/
		
		// Open file 
		file = new File("../Interprete/test/test.rbg");
		FileNameExtensionFilter filter = new FileNameExtensionFilter(null, "txt", "rbg");
		fileChooser.setFileFilter(filter);
	}
	
	// Button actions 
	private class compileAction extends AbstractAction {
		Icon runIcon = new ImageIcon(window.class.getResource("/icons/search.png"));
		public compileAction() {
			putValue(NAME, "Compilar");
			putValue(SHORT_DESCRIPTION, "Compila el programa");
			putValue( Action.SMALL_ICON, runIcon );
		}
		public void actionPerformed(ActionEvent e) {
			txtpnErrores.setText("Compilado y ejecutado");
			
		}
	}
	private class runAction extends AbstractAction {
		Icon runIcon = new ImageIcon(window.class.getResource("/icons/run.png"));
		public runAction() {
			putValue(NAME, "Compilar y ejecutar");
			putValue(SHORT_DESCRIPTION, "Compila y ejecuta el programa");
			putValue( Action.SMALL_ICON, runIcon );
		}
		public void actionPerformed(ActionEvent e) {
			// txtpnErrores.setText("Compilado y ejecutado");
			Main interprete = new Main();
			try {
				interprete.compile(file);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}
	
	// Open file action
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
                file = fileChooser.getSelectedFile();
                try {
                    String content = FileUtils.readFileToString(file);
                    textArea.setText(content);
                    output.setText("");
                } catch (Exception e1) {
                }  
            }
		}
	}
	
	// Save file action
	private class saveFile extends AbstractAction {
		Icon saveIcon = new ImageIcon(window.class.getResource("/icons/save.png"));
		public saveFile() {
			putValue(NAME, "Guardar");
			putValue(SHORT_DESCRIPTION, "Guardar un archivo");
			putValue( Action.SMALL_ICON, saveIcon);
		}
		public void actionPerformed(ActionEvent e) {
			int value = fileChooser.showSaveDialog(frmRobotichandIde);
            if (value == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try {
                    FileUtils.writeStringToFile(file, textArea.getText(), Charset.forName("UTF-8"));
                    output.setText("");
                } catch (Exception e1) {
                }
            }
		}
	}
}

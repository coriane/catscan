package xp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GroupNameDialog extends JDialog implements ActionListener {

	private final String typedText = null;
	// private JTextField textField;
	// private JFrame dd;

	// private String oldName;
	private String result;
	// private JOptionPane optionPane;

	// private String btnString1 = "Enter";
	// private String btnString2 = "Cancel";
	JTextArea question1, question2;
	JTextField name;
	GroupPanel gpanel;
	PaintPanel ppanel;

	static boolean usePaintPanel = false;

	/**
	 * Returns null if the typed string was invalid; otherwise, returns the
	 * string as the user entered it.
	 */
	public String getValidatedText() {
		return typedText;
	}

	/** Creates the reusable dialog. */
	public GroupNameDialog(JFrame owner, GroupPanel gp) {
		super(owner, true);
		// dd = owner;
		gpanel = gp;

		setTitle("Questions");
		this.setLocation(0, 0);
		// this.setUndecorated(true);
		setResizable(false);

		this.setSize(getToolkit().getScreenSize());

		Container c;
		c = getContentPane();

		GridBagLayout gbl = new GridBagLayout();
		c.setLayout(gbl);

		// setSize(600,600);
		// setResizable(false); //Hinweis im Text beachten
		// Point parloc = owner.getLocation();
		// setLocation(parloc.x + 50, parloc.y + 50);

		JTextArea nameLbl = new JTextArea(
				"\n\nThe first task for you is to find a name for each group that you have created and that you see above. Please chose a short name and do not use more than 1-5 words.\n");
		nameLbl.setLineWrap(true);
		nameLbl.setWrapStyleWord(true);
		nameLbl.setFont(new Font(getFont().getFontName(), Font.BOLD, 12));
		nameLbl.setPreferredSize(new Dimension(190, 100));
		nameLbl.setBackground(getBackground());
		nameLbl.setEditable(false);
		// gbl.setConstraints(nameLbl,mkGBC(0,8,2,2));
		// c.add(nameLbl);

		name = new JTextField("", 30);
		// name.setBorder(new EtchedBorder());

		// name.setColumns(20);
		// name.setRows(1);
		// gbl.setConstraints(name,mkGBC(0,10,2,3));
		// name.setPreferredSize(new Dimension(100,50));
		// name.setMaximumSize(name.getPreferredSize());
		// c.add(name);

		JPanel a = new JPanel(new BorderLayout());
		// a.setPreferredSize(new Dimension(400,100));
		a.add(nameLbl, BorderLayout.CENTER);
		a.add(name, BorderLayout.PAGE_END);

		// JTextArea q1Lbl = new
		// JTextArea("\nThe second task is to describe the behaviour of geographic regions in each group. Please write a description about the behaviour of the geographic regions (the animated icons) that take place in this group.");
		JTextArea q1Lbl = new JTextArea(
				"\nYour second task is to describe the criteria you used when creating the groups. Why did you choose to group the icons as you did?");
		q1Lbl.setLineWrap(true);
		q1Lbl.setWrapStyleWord(true);
		q1Lbl.setFont(new Font(getFont().getFontName(), Font.BOLD, 12));
		q1Lbl.setBackground(getBackground());
		q1Lbl.setPreferredSize(new Dimension(190, 100));
		q1Lbl.setEditable(false);
		// gbl.setConstraints(q1Lbl,mkGBC(0,14,2,2));
		// c.add(q1Lbl);

		question1 = new JTextArea(5, 30);
		question1.setLineWrap(true);
		question1.setWrapStyleWord(true);
		// question1.setBorder(new EtchedBorder());;

		// gbl.setConstraints(question1,mkGBC(0,16,2,3));
		// question1.setPreferredSize(new Dimension(200,100));
		// c.add(question1);

		JPanel b = new JPanel(new BorderLayout());
		b.add(q1Lbl, BorderLayout.NORTH);
		b.add(new JScrollPane(question1), BorderLayout.SOUTH);

		JTextArea q2Lbl = new JTextArea(
				"\n\nYour third task is to name the individual processes that take place in each group. Please name as many as possible.");
		if (usePaintPanel == false) {
			q2Lbl.setText("");
		}
		q2Lbl.setLineWrap(true);
		q2Lbl.setWrapStyleWord(true);
		q2Lbl.setFont(new Font(getFont().getFontName(), Font.BOLD, 12));
		q2Lbl.setPreferredSize(new Dimension(190, 100));
		q2Lbl.setBackground(getBackground());
		q2Lbl.setEditable(false);
		// gbl.setConstraints(q2Lbl,mkGBC(0,21,2,2));
		// c.add(q2Lbl);

		question2 = new JTextArea(5, 30);
		question2.setLineWrap(true);
		question2.setWrapStyleWord(true);
		// question2.setBorder(new EtchedBorder());;
		// gbl.setConstraints(question2,mkGBC(0,23,2,3));
		// question2.setMinimumSize(new Dimension(200,100));
		// c.add(question2);
		// txtbox.add(new JLabel("Draw a picture!"));

		JPanel cc = new JPanel(new BorderLayout());
		if (usePaintPanel) {
			cc.add(q2Lbl, BorderLayout.NORTH);
		}
		cc.add(new JScrollPane(question2), BorderLayout.SOUTH);

		JPanel d = new JPanel();
		d.setPreferredSize(new Dimension(50, 50));

		JPanel fragen = new JPanel(new BorderLayout());
		fragen.add(a, BorderLayout.NORTH);
		fragen.add(b, BorderLayout.CENTER);
		// fragen.add(cc, BorderLayout.SOUTH);

		JPanel zw = new JPanel(new BorderLayout());
		zw.add(fragen, BorderLayout.WEST);
		zw.add(d, BorderLayout.EAST);
		gbl.setConstraints(zw, mkGBC(0, 5, 3, 17));
		c.add(zw);

		ppanel = new PaintPanel();
		ppanel.setPreferredSize(new Dimension((int) getToolkit()
				.getScreenSize().getWidth() - 500, (int) getToolkit()
				.getScreenSize().getHeight() - 400));
		// ppanel.setPreferredSize(new Dimension((int) getToolkit()
		// .getScreenSize().getWidth(), (int) getToolkit().getScreenSize()
		// .getHeight()));
		gbl.setConstraints(ppanel, mkGBC(5, 10, 6, 17));
		// ppanel.setBorder(BorderFactory.createLoweredBevelBorder());
		if (usePaintPanel) {
			c.add(ppanel);
		}

		JButton ok = new JButton("Finish");
		// gbl.setConstraints(ok,mkGBC(9,28,1,1));
		ok.addActionListener(this);
		// c.add(ok);
		// cancel.addActionListener(this);
		// gp.showPanel.remove(gp.emptyPic.picdata);

		JButton rp = new JButton("Reset picture");
		// gbl.setConstraints(rp,mkGBC(8,28,1,1));
		rp.addActionListener(this);
		// c.add(rp);

		JLabel zw3 = new JLabel();
		zw3.setPreferredSize(new Dimension(10, 10));
		JLabel zw4 = new JLabel();
		zw4.setPreferredSize(new Dimension(20, 20));
		JPanel bb = new JPanel(new BorderLayout());
		if (usePaintPanel) {
			bb.add(rp, BorderLayout.EAST);
		}
		bb.add(ok, BorderLayout.WEST);
		bb.add(zw4, BorderLayout.NORTH);
		bb.add(zw3, BorderLayout.CENTER);
		gbl.setConstraints(bb, mkGBC(0, 28, 2, 1));
		c.add(bb);

		// gp.showPanel.setMinimumSize(new Dimension(1100,
		// gp.pix.size()/9*120));

		JLabel nr = new JLabel("Group " + gp.number + "/"
				+ gp.parentFrame.groups.size());
		nr.setFont(new Font(getFont().getFontName(), Font.BOLD, 16));
		nr.setPreferredSize(new Dimension(100, 100));
		c.add(nr);

		gbl.setConstraints(nr, mkGBC(0, 0, 1, 1));
		// gp.showPanel.setLayout(new
		// FlowLayout(15,15,15));//GridLayout(gp.pix.size()/11,10,5,5));
		JScrollPane srp = new JScrollPane(gp.showPanel);
		srp.setPreferredSize(new Dimension((int) getToolkit().getScreenSize()
				.getWidth() - 200, 130));
		gbl.setConstraints(srp, mkGBC(1, 0, 12, 3));
		c.add(srp);

		JTextArea zw2 = new JTextArea(
		// "\nThe forth task is to draw a symbol that represents the behaviour of the two geographic regions in this group. Please use the graphic tablet or the mouse for this task and make sure to draw in the white area. You can delete your drawing and start a new. Please press the FINISH button when you have completed the task.");
				"\nYour third task is to draw a graphic symbol for the group you created. Please use the mouse.");
		zw2.setLineWrap(true);
		zw2.setWrapStyleWord(true);
		zw2.setPreferredSize(new Dimension((int) getToolkit().getScreenSize()
				.getWidth() - 500, 100));
		zw2.setFont(new Font(getFont().getFontName(), Font.BOLD, 12));
		zw2.setBackground(getBackground());
		gbl.setConstraints(zw2, mkGBC(5, 4, 6, 2));
		zw2.setEditable(false);
		if (usePaintPanel) {
			c.add(zw2);
		}

		// cp.add(cancel, BorderLayout.WEST);

		// Make this dialog display it.
		setContentPane(c);
		pack();
		// Handle window closing correctly.
		// setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	/** This method handles events for the text field. */
	public void actionPerformed(ActionEvent e) {

		String cmd = e.getActionCommand();

		if (cmd.equals("Finish")) {

			gpanel.name = name.getText();
			gpanel.question1 = question1.getText();
			gpanel.question2 = question2.getText();
			gpanel.image = ppanel.image;
			System.out.println("image height = " + gpanel.image.getHeight());
			String fileName = FileIO.ResultsDir + DataObject.fileIdentifier
					+ "_" + gpanel.number + ".jpg";
			if (usePaintPanel) {
				try {
					ImageIO.write(ppanel.image, "JPEG", new File(fileName));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			System.out.println("n lines = " + ppanel.lines.size());
			// force participants to type something and draw something unless in
			// demo mode
			boolean paintPanelTest = false;
			if (ppanel.lines.size() >= 2 || (usePaintPanel == false)) {
				paintPanelTest = true;
			}
			if ((gpanel.name.length() > 0 && gpanel.question1.length() > 0 && paintPanelTest)
					|| DataObject.demoMode) {

				removeAll();

				setVisible(false);
			}
		}

		if (cmd.equals("Reset picture")) {
			ppanel.lines = new ArrayList();
			ppanel.current = new ArrayList();
			ppanel.lines.add(ppanel.current);
			ppanel.repaint();

		}

		if (cmd.equals("Cancel")) {
			// result = oldName;
			setVisible(false);
		}
	}

	public String getResult() {
		return result;
	}

	private GridBagConstraints mkGBC(int x, int y, int width, int height) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		gbc.insets = new Insets(1, 1, 1, 1);
		gbc.anchor = GridBagConstraints.WEST;
		return gbc;
	}

}

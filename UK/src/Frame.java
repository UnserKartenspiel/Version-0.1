import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Window.Type;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.SpringLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EtchedBorder;
import java.awt.Cursor;
import java.awt.Dimension;

public class Frame extends JFrame{
	private ActionListener gi;
	
	JPanel handPanel;
	JPanel playedPanel;
	JPanel oHandPanel;
	JPanel oPlayedPanel;
	
	public Frame(GameIntelligence gi) {
		setSize(1000,800);
		setPreferredSize(new Dimension(1000, 800));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.gi = gi;
		setTitle("UK - The Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		oHandPanel = new JPanel();
		oHandPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		oPlayedPanel = new JPanel();
		oPlayedPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		handPanel = new JPanel();
		handPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		handPanel.addMouseListener(gi);
		
		playedPanel = new JPanel();
		playedPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		playedPanel.addMouseListener(gi);
		
		JButton stackButton = new JButton("Stack");
		stackButton.addActionListener(gi);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(oPlayedPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(playedPanel, GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(stackButton)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(handPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(oHandPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(oHandPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(oPlayedPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(stackButton, GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(playedPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(handPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
		);
		getContentPane().setLayout(groupLayout);
	}


}

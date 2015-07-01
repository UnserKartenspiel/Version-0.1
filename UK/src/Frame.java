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

public class Frame extends JFrame{
	private ActionListener gi;

	
	public Frame(GameIntelligence gi) {
		this.gi = gi;
		setTitle("UK - The Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setBounds(100, 100, 544, 301);
		
		JPanel oHandPanel = new JPanel();
		oHandPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel oPlayedPanel = new JPanel();
		oPlayedPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JPanel handPanel = new JPanel();
		handPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel playedPanel = new JPanel();
		playedPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		JButton stackButton = new JButton("Stack");
		stackButton.addActionListener(gi);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(oHandPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addGap(10))
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(oPlayedPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(handPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
								.addContainerGap())
							.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(playedPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addContainerGap()))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(stackButton)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(oHandPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addComponent(oPlayedPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
					.addComponent(stackButton, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(playedPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(handPanel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
		);
		getContentPane().setLayout(groupLayout);
	}
}

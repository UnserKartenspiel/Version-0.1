import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
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
	OverlayPane overlayPane;
	
	public Frame(GameIntelligence gi) {
		setSize(1000,800);
		setPreferredSize(new Dimension(1000, 800));
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.gi = gi;
		setTitle("UK - The Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		overlayPane = new OverlayPane(gi);
		setGlassPane(overlayPane);
		getGlassPane().setVisible(true);
		
		
		oHandPanel = new JPanel();
		oHandPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		oPlayedPanel = new JPanel();
		oPlayedPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		oPlayedPanel.addMouseListener(gi);
		
		handPanel = new JPanel();
		handPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		handPanel.addMouseListener(gi);
		
		playedPanel = new JPanel();
		playedPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		playedPanel.addMouseListener(gi);
		
		JButton stackButton = new JButton("Stack");
		stackButton.addActionListener(gi);
		
		JButton finishButton = new JButton("Fertig");
		finishButton.addActionListener(gi);
		
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(oPlayedPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addComponent(playedPanel, GroupLayout.DEFAULT_SIZE, 964, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(finishButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 764, Short.MAX_VALUE)
							.addComponent(stackButton, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
						.addComponent(handPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
						.addComponent(oHandPanel, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addComponent(oHandPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(oPlayedPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(stackButton, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
						.addComponent(finishButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(playedPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(handPanel, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
					.addGap(10))
		);
		getContentPane().setLayout(groupLayout);
	}
}

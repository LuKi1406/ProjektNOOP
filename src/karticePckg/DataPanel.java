package karticePckg;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tblPckg.Kartice;

public class DataPanel extends JPanel {
	
	private JTextField numberField;
	private JTextField typeField;
	private JTextField pointsField;
	
	private JButton sbmt;
	
	private DataPanelListener dpl;
	private Kartice kartica;
	
	public DataPanel() {
		createComps();
		setLayComps();
		
		Dimension dim=getPreferredSize();
		dim.height=120;
		setPreferredSize(dim);
		setVisible(true);
		
		
	}
	
	
	private void createComps() {
		numberField=new JTextField(20);
		typeField=new JTextField(10);
		pointsField=new JTextField(10);
		
		sbmt=new JButton("Submit");
		
	  sbmt.addActionListener(new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			if(dpl != null) {
				kartica=new Kartice(numberField.getText(),typeField.getText(),pointsField.getText());
				DataPanelEvent dpe=new DataPanelEvent(DataPanel.this,kartica);
				dpl.dataPanelEventOccured(dpe);
				kartica.description();
			}
			resetDataPanel();
			
		}
		  
		  
	  });
	  
	  
		
	}
	
	public Kartice getKartice() {
		return kartica;
		
	}
	
	private void resetDataPanel() {
		numberField.setText("");
		typeField.setText("");
		pointsField.setText("");
	}
	
	private void setLayComps() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc= new GridBagConstraints();
		
		gbc.weightx=0;
		gbc.weighty=0.1;
		
		gbc.gridx=0;
		gbc.gridy=1;
		add(new JLabel("Card number: "),gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		add(numberField,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		add(new JLabel("Card type: "),gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		add(typeField,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		add(new JLabel("Bonus points: "),gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		add(pointsField,gbc);
		
		gbc.gridx=3;
		gbc.gridy=1;
		add(sbmt,gbc);
		
	
	}
	
	public void setDataPanelListener(DataPanelListener lst) {
		this.dpl=lst;
	}


	
	

}

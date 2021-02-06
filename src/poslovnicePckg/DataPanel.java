package poslovnicePckg;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tblPckg.Poslovnice;


public class DataPanel extends JPanel {
	
	private JTextField nameField;
	private JTextField contactField;
	private JTextField addressField;
	private JTextField typeField;
	
	private JButton sbmt;
	
	
	
	private DataPanelListener dpl;
	
	private Poslovnice poslovnica;
	
	public DataPanel() {
		createComp();
		setLayComps();
		
		Dimension dim=getPreferredSize();
		dim.height=220;
		setPreferredSize(dim);
		setVisible(true);
		
		
	}
	
	
	
	private void createComp() {
		nameField=new JTextField(20);
		contactField=new JTextField(20);
		addressField=new JTextField(30);
		typeField=new JTextField(10);
		
		sbmt=new JButton("Submit");
	
		sbmt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				

				if(dpl != null) {
					poslovnica=new Poslovnice(nameField.getText(), contactField.getText(), addressField.getText(),typeField.getText());
					DataPanelEvent dpe = new DataPanelEvent(DataPanel.this, poslovnica);
					dpl.dataPanelEventOccured(dpe);
					poslovnica.description();
					
					
				}

				resetDataPanel();
				
				
			}
			
			
		});
		
	}
	
	public Poslovnice getPoslovnice() {
		return poslovnica;
		
	}
	
	private void resetDataPanel() {
		nameField.setText("");
		typeField.setText("");
		addressField.setText("");
		contactField.setText("");
		
		
	}
	
	private void setLayComps() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc= new GridBagConstraints();
		
		gbc.weightx=0;
		gbc.weighty=0.1;
		
		gbc.gridx=0;
		gbc.gridy=1;
		add(new JLabel("Name: "),gbc);
		
		gbc.gridx=1;
		gbc.gridy=1;
		add(nameField,gbc);
		
		gbc.gridx=0;
		gbc.gridy=2;
		add(new JLabel("Type: "),gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		add(typeField,gbc);
		
		gbc.gridx=0;
		gbc.gridy=3;
		add(new JLabel("Address: "),gbc);
		
		gbc.gridx=1;
		gbc.gridy=3;
		add(addressField,gbc);
		
		gbc.gridx=3;
		gbc.gridy=1;
		add(new JLabel("Contact: "),gbc);
		
		gbc.gridx=4;
		gbc.gridy=1;
		add(contactField,gbc);
		
		gbc.gridx=3;
		gbc.gridy=2;
		add(sbmt,gbc);
		
		
	}

	


	public void setDataPanelListener(DataPanelListener lst) {
		this.dpl=lst;
		
	}

	
	

}





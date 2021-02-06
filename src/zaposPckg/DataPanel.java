package zaposPckg;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import tblPckg.Zaposlenici;



public class DataPanel extends JPanel {
	
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField addressField;
	private JTextField contactField;
	private JTextField poslovnicaField;
	private JTextField datumField;
	private JButton sbmt;
	
	
	
	private DataPanelListener dpl;
	
	private Zaposlenici zaposlenik;
	
	public DataPanel() {
		createComp();
		setLayComps();
		
		Dimension dim=getPreferredSize();
		dim.height=200;
		setPreferredSize(dim);
		setVisible(true);
		
		
	}
	
	public void setDataPanelListener(DataPanelListener lst) {
		this.dpl=lst;
	}
	
	private void createComp() {
		nameField=new JTextField(20);
		surnameField=new JTextField(20);
		addressField=new JTextField(30);
		contactField=new JTextField(10);
		poslovnicaField=new JTextField(10);
		datumField=new JTextField(20);
		sbmt=new JButton("Submit");
	
		sbmt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				

				if(dpl != null) {
					zaposlenik=new Zaposlenici(nameField.getText(), surnameField.getText(), addressField.getText(),contactField.getText(),poslovnicaField.getText(),datumField.getText());
					DataPanelEvent dpe = new DataPanelEvent(DataPanel.this, zaposlenik);
					dpl.dataPanelEventOccured(dpe);
					zaposlenik.description();
					
					
				}

				resetDataPanel();
				
				
			}
			
			
		});
		
	}
	
	public Zaposlenici getZaposlenici() {
		return zaposlenik;
		
	}
	
	private void resetDataPanel() {
		nameField.setText("");
		surnameField.setText("");
		addressField.setText("");
		contactField.setText("");
		poslovnicaField.setText("");
		datumField.setText("");
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
		add(new JLabel("Surname: "),gbc);
		
		gbc.gridx=1;
		gbc.gridy=2;
		add(surnameField,gbc);
		
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
		add(new JLabel("Office ID: "),gbc);
		
		gbc.gridx=4;
		gbc.gridy=2;
		add(poslovnicaField,gbc);
		
		gbc.gridx=3;
		gbc.gridy=3;
		add(new JLabel("Pocetak rada: "),gbc);
		
		gbc.gridx=4;
		gbc.gridy=3;
		add(datumField,gbc);
		
		gbc.gridx=4;
		gbc.gridy=4;
		add(sbmt,gbc);
	}

	
	

}


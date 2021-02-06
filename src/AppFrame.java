

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import karticePckg.KarFrame;
import poslovnicePckg.PosloFrame;
import zaposPckg.ZaposFrame;


public class AppFrame extends JFrame {
	
	private JButton kupci;
	private JButton zaposlenici;
    private JButton kartice;
	private JButton poslovnice;
	
	public AppFrame() {
		
		
		createComps();
		setLayout();
		setSize(500,200);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		
		
	}
	
	private void createComps() {
		kupci=new JButton("Kupci");
		zaposlenici=new JButton("Zaposlenici");
		kartice=new JButton("Kartice");
		poslovnice=new JButton("Poslovnice");
		
	       kupci.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ev) {
				
				MainFrame mnFrm=new MainFrame();
				mnFrm.setVisible(true);
				
				
			}
	    	   
	    	   
	       });
	       
	          zaposlenici.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent eve) {
					ZaposFrame zapoFrm=new ZaposFrame();
					zapoFrm.setVisible(true);
				}
	        	  
	        	  
	          });
	          
	          
	          
	          poslovnice.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent en) {
					PosloFrame posFrm=new PosloFrame();
					posFrm.setVisible(true);
				}
	        	  
	        	  
	          });
	          
	          kartice.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					KarFrame krf=new KarFrame();
					krf.setVisible(true);
				}
	        	  
	        	  
	          });
		
	}
	
	private void setLayout() {
		setLayout(new GridBagLayout());
		GridBagConstraints gbc=new GridBagConstraints();
		
		gbc.gridx=1;
		gbc.gridy=1;
		add(kupci,gbc);
		
		gbc.gridx=2;
		gbc.gridy=1;
		add(zaposlenici,gbc);
		
		gbc.gridx=3;
		gbc.gridy=1;
		add(poslovnice,gbc);
		
		gbc.gridx=4;
		gbc.gridy=1;
		add(kartice,gbc);
		
		
		
		
		
		
		
	}
	

}

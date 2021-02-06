package poslovnicePckg;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;


import tblPckg.Poslovnice;






public class PosloFrame extends JFrame {
	
	
	private DataPanel dataPanel;
	private PresentationPanel presPan;
	private JFileChooser fileChooser;
	private Controller controller;
	private JMenuBar menuBar;
	
	
	public PosloFrame() {
		super("Poslovnice");
		
		setLayout(new BorderLayout());
		createComps();
		
		add(dataPanel,BorderLayout.SOUTH);
		add(presPan,BorderLayout.CENTER);
		setSize(800,700);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		activateApp();
		
		
	}
	
   
	
	private void createComps() {
		dataPanel=new DataPanel();
		presPan=new PresentationPanel();
		controller=new Controller();
		controller.setData4Table(presPan);
		menuBar=createMenuBar();
		setJMenuBar(menuBar);
		fileChooser=new JFileChooser();
		setFileExtension();
		
	}
	
	private void setFileExtension() {
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Poslovnice files (*.prgm)", "prgm");
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
	}
	
	private void activateApp() {
		
     dataPanel.setDataPanelListener(new DataPanelListener() {

		@Override
		public void dataPanelEventOccured(DataPanelEvent dpe) {
			   
			    
				
				Poslovnice poslo =dataPanel.getPoslovnice();
				controller.addNewPoslovnica2DB(poslo);
				controller.showOnPanel(poslo, presPan);
				controller.showPoslovniceData(presPan);
			
		}
    	
    		

    		 
	});

	}
		
	
	private JMenuBar createMenuBar() {
		JMenuBar mb=new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		
		
		JMenuItem save=new JMenuItem("Save data");
		JMenuItem open=new JMenuItem("Import Data");
		
		fileMenu.add(save);
		fileMenu.add(open);
		
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int val = fileChooser.showSaveDialog(PosloFrame.this);

				if (val == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						controller.saveData2File(file);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(PosloFrame.this, "Unable to save data into the file!", "Save error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
				
				
		});
		
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int val = fileChooser.showOpenDialog(PosloFrame.this);

				if (val == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						controller.importData4File(file);
						controller.showPoslovniceData(presPan);
						controller.showImportedDataInTxtPanel(presPan);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(PosloFrame.this, "Unable to read data from the file!", "Open error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			
				
			}
			
			
		});
		
		mb.add(fileMenu);
		return mb;
		
	}
	
		}
	
	
	
	
		




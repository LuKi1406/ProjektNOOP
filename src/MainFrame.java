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

import tblPckg.Kupci;


public class MainFrame extends JFrame {
	
	private DataPanel dataPanel;
	private PresentationPanel presPan;
	private JFileChooser fileChooser;
	private Controller controller;
	private JMenuBar menuBar;
	
	public MainFrame() {
		super("Kupci");
		
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
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Kupci files (*.prgm)", "prgm");
		fileChooser.setFileFilter(filter);
		fileChooser.setAcceptAllFileFilterUsed(false);
	}
	
	private void activateApp() {
		
		dataPanel.setDataPanelListener(new DataPanelListener() {

			@Override
			public void dataPanelEventOccured(DataPanelEvent dpe) {
				int id=dpe.getId();
				String name=dpe.getName();
				String surname=dpe.getSurname();
				String address=dpe.getAddress();
			    String contact=dpe.getContact();
				String numCard=dpe.getNumCard();
				
				Kupci kup=dataPanel.getKupac();
				controller.addNewKupac2DB(kup);
				controller.showOnPanel(kup, presPan);
			    controller.showKupacData(presPan);
				
				
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
				int val = fileChooser.showSaveDialog(MainFrame.this);

				if (val == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						controller.saveData2File(file);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Unable to save data into the file!", "Save error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
				
				
		});
		
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int val = fileChooser.showOpenDialog(MainFrame.this);

				if (val == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					try {
						controller.importData4File(file);
						controller.showKupacData(presPan);
						controller.showImportedDataInTxtPanel(presPan);
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(MainFrame.this, "Unable to read data from the file!", "Open error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			
				
			}
			
			
		});
		
		mb.add(fileMenu);
		return mb;
		
	}
	
	
	
	

}

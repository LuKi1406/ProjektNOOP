package poslovnicePckg;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;


import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import tblPckg.Poslovnice;



public class PresentationPanel extends JPanel {
      
	
	
	private JTextPane txtPan;
	private JScrollPane scPan;
	private StyledDocument doc;
	private SimpleAttributeSet attributes;
	private JTable tbl;
	private List<Poslovnice> poslovnice;
	private JScrollPane tablePanel;
	private AbstractTableModel tableModel;
	
	public PresentationPanel() {
		
		setLayout(new BorderLayout());
		createComps();
		add(tablePanel,BorderLayout.CENTER);
		add(scPan,BorderLayout.SOUTH);
		setVisible(true);
		
	}
	
	public void setDBData(DataBase db) {
		poslovnice=db.getAll4DB();
	}
	
	private void createComps() {
		txtPan=new JTextPane();
		txtPan.setEditable(false);
		doc=txtPan.getStyledDocument();
		txtPan.setBackground(Color.LIGHT_GRAY);
		txtPan.setForeground(Color.BLACK);
		Dimension tdim=txtPan.getPreferredSize();
		tdim.height=100;
		txtPan.setPreferredSize(tdim);
		attributes=new SimpleAttributeSet();
		StyleConstants.setFontFamily(attributes, "Consolas");
		StyleConstants.setFontSize(attributes, 14);
	    attributes.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
		scPan = new JScrollPane(txtPan, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		this.tbl = setTable();
		tablePanel = new JScrollPane(tbl, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	}
		
private JTable setTable() {
		
		JTable tbl = new JTable();
		
			tableModel = new AbstractTableModel() {
			
			String[] colNames = {"id", "name", "contact", "address", "type"};
			
			
			
			@Override
			public String getColumnName(int column) {
				return colNames[column];
			}

			@Override
			public Object getValueAt(int row, int col) {
				
				Poslovnice poslovnica = poslovnice.get(row);
				switch (col) {
				case 0: return poslovnica.getId();
				case 1: return poslovnica.getName();
				case 2: return poslovnica.getContact();
				case 3: return poslovnica.getAddress();
				case 4: return poslovnica.getType();
				

				default:
					throw new IllegalArgumentException("There is no such value for the input data!!!");
				}
			}
			
			@Override
			public int getRowCount() {
				return poslovnice.size();
			}
			
			@Override
			public int getColumnCount() {
				Poslovnice posl=new Poslovnice();
				return posl.getClass().getDeclaredFields().length-1;
			}
		};
		
		tbl.setModel(tableModel);
		return tbl;
	}
	
	
	public void showOnPresPanel(Poslovnice poslovnica) {
		try {
			doc.insertString(doc.getLength(), poslovnica.toString() +"\n\n", attributes);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showDataOnTable() {
		tableModel.fireTableDataChanged();
	}

	public void showImportedDataInTxtPanel(List<Poslovnice> all4db) {
		for(Poslovnice posl : all4db) {
			showOnPresPanel(posl);
		}
		
	}


	

}


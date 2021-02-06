import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


import tblPckg.Kupci;

public class PresentationPanel extends JPanel {
	
	private JTextPane txtPan;
	private JScrollPane scPan;
	private StyledDocument doc;
	private SimpleAttributeSet attributes;
	private JTable tbl;
	private List<Kupci> kupci;
	private JScrollPane tablePanel;
	private AbstractTableModel tableModel;
	
	public PresentationPanel() {
		
		setLayout(new BorderLayout());
		createComps();
		add(tablePanel,BorderLayout.CENTER);
		add(scPan,BorderLayout.SOUTH);
		
		
	}
	
	public void setDBData(DataBase db) {
		kupci=db.getAll4DB();
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
			
			String[] colNames = {"id", "name", "surname", "address", "contact", "numCard"};
			
			
			
			@Override
			public String getColumnName(int column) {
				return colNames[column];
			}

			@Override
			public Object getValueAt(int row, int col) {
				
				Kupci kupac = kupci.get(row);
				switch (col) {
				case 0: return kupac.getId();
				case 1: return kupac.getName();
				case 2: return kupac.getSurname();
				case 3: return kupac.getAddress();
				case 4: return kupac.getContact();
				case 5: return kupac.getNumCard();

				default:
					throw new IllegalArgumentException("There is no such value for the input data!!!");
				}
			}
			
			@Override
			public int getRowCount() {
				return kupci.size();
			}
			
			@Override
			public int getColumnCount() {
				Kupci kup=new Kupci();
				return kup.getClass().getDeclaredFields().length-1;
			}
		};
		
		tbl.setModel(tableModel);
		return tbl;
	}
	
	
	public void showOnPresPanel(Kupci kupac) {
		try {
			doc.insertString(doc.getLength(), kupac.toString() +"\n\n", attributes);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showDataOnTable() {
		tableModel.fireTableDataChanged();
	}

	public void showImportedDataInTxtPanel(List<Kupci> all4db) {
		for(Kupci kup : all4db) {
			showOnPresPanel(kup);
		}
		
	}
	

}

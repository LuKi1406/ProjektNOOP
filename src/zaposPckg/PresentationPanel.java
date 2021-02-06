package zaposPckg;

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



import tblPckg.Zaposlenici;

public class PresentationPanel extends JPanel {
	
	
	private JTextPane txtPan;
	private JScrollPane scPan;
	private StyledDocument doc;
	private SimpleAttributeSet attributes;
	private JTable tbl;
	private List<Zaposlenici> zaposlenici;
	private JScrollPane tablePanel;
	private AbstractTableModel tableModel;
	
	public PresentationPanel() {
		
		setLayout(new BorderLayout());
		createComps();
		add(tablePanel,BorderLayout.CENTER);
		add(scPan,BorderLayout.SOUTH);
		
		
	}
	
	public void setDBData(DataBase db) {
		zaposlenici=db.getAll4DB();
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
			
			String[] colNames = {"id", "name", "surname", "address", "contact", "officeID", "working_since"};
			
			
			
			@Override
			public String getColumnName(int column) {
				return colNames[column];
			}

			@Override
			public Object getValueAt(int row, int col) {
				
				Zaposlenici zaposlenik = zaposlenici.get(row);
				switch (col) {
				case 0: return zaposlenik.getId();
				case 1: return zaposlenik.getName();
				case 2: return zaposlenik.getSurname();
				case 3: return zaposlenik.getAddress();
				case 4: return zaposlenik.getContact();
				case 5: return zaposlenik.getPoslovnica();
				case 6:return zaposlenik.getDatumPocetkaRada();

				default:
					throw new IllegalArgumentException("There is no such value for the input data!!!");
				}
			}
			
			@Override
			public int getRowCount() {
				return zaposlenici.size();
			}
			
			@Override
			public int getColumnCount() {
				Zaposlenici zapo=new Zaposlenici();
				return zapo.getClass().getDeclaredFields().length-1;
			}
		};
		
		tbl.setModel(tableModel);
		return tbl;
	}
	
	
	public void showOnPresPanel(Zaposlenici zaposlenik) {
		try {
			doc.insertString(doc.getLength(), zaposlenik.toString() +"\n\n", attributes);
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void showDataOnTable() {
		tableModel.fireTableDataChanged();
	}

	public void showImportedDataInTxtPanel(List<Zaposlenici> all4db) {
		for(Zaposlenici zapo : all4db) {
			showOnPresPanel(zapo);
		}
		
	}


	

}



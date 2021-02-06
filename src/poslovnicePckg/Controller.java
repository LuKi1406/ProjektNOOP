package poslovnicePckg;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import tblPckg.Poslovnice;



public class Controller {
	
	private DataBase db;
	
	
	public Controller () {
		db=new DataBase();
		
	}
	
	public void addNewPoslovnica2DB(Poslovnice poslovnica) {
		db.setPoslovnica2DB(poslovnica);
	}
	
	public List<Poslovnice> getAllPoslovnice() {
		return db.getAll4DB();
	}
	
	public void showOnPanel(Poslovnice poslo,PresentationPanel presPan) {
		presPan.showOnPresPanel(poslo);
	}
	
	public void setData4Table(PresentationPanel panel) {
		panel.setDBData(db);
	}
	
	public void showPoslovniceData(PresentationPanel panel) {
		panel.showDataOnTable();
	}
	
	public void saveData2File(File file) throws IOException {
		db.saveDB2File(file);
	}
	
	public void importData4File(File file) throws IOException {
		db.readData4File(file);
		int num = db.getAll4DB().get(db.getAll4DB().size()-1).getId();
		Poslovnice.setCounter(num+1);
	}

	public void showImportedDataInTxtPanel(PresentationPanel presPanel) {
		presPanel.showImportedDataInTxtPanel(db.getAll4DB());
	}
	
	public void connect2DB() throws SQLException {
		db.connect();
	}
	public void disconnect4DB() throws SQLException {
		db.disconnect();
	}
	
	public void save2DB() throws SQLException {
		db.save2DB();
	}
	
	public void load4DB() throws SQLException {
		db.load4DataBase();
	}


	
	

	


}



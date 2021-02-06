import java.sql.SQLException;

import tblPckg.Kupci;

public class TestKupci {

	public static void main(String[] args) {
		
		DataBase db=new DataBase();
		Kupci kup1=new Kupci("Josipa","Gligic", "Jeretova 7A", "919332674", "10001");
        Kupci kup2=new Kupci("Ime","Prezime","Adresa 4","98756432","11003");
        
        db.setKupci2DB(kup1);
        db.setKupci2DB(kup2);
        
        try {
			db.connect();
			db.createTable();
			db.save2DB();
		    db.load4DataBase();
		    db.listAll4DB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        try {
			db.disconnect();
			System.out.println("Disconnected!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}

}

package zaposPckg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


import tblPckg.Zaposlenici;

public class DataBase {
	
	private List<Zaposlenici> zaposlenici;
	private Connection con;
	
	public DataBase() {
		zaposlenici=new LinkedList<Zaposlenici>();
		
	}
	
	
	 public void setZaposlenici2DB (Zaposlenici zaposlenik) {
		  zaposlenici.add(zaposlenik);
	  }
	  
	  public List<Zaposlenici> getAll4DB() {
		  return zaposlenici;
	  }
	  
	  
	  public void saveDB2File(File file) throws IOException {
			
			Zaposlenici[] zapo = zaposlenici.toArray(new Zaposlenici[zaposlenici.size()]);
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(zapo); 
			oos.close();
		}
		
		public void readData4File(File file) throws IOException {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			try {
				Zaposlenici[] zapo = (Zaposlenici[]) ois.readObject();
				zaposlenici.clear();
				zaposlenici.addAll(Arrays.asList(zapo));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ois.close();
		}
		
		public void createTable() 
{        
	 String url = "jdbc:sqlite:test.db";
    
    
    String sql = "CREATE TABLE IF NOT EXISTS zaposlenici (\n"
            + "    id integer PRIMARY KEY,\n"
            + "    name text NOT NULL,\n"
            + "    surname text NOT NULL,\n"
            + "    address text,\n"
            + "    contact text,\n"
            + "    officeID text,\n"
            + "    work_since text \n"
            + ");";
    
    try (Connection con = DriverManager.getConnection(url);
            Statement stmt = con.createStatement()) {
       
        stmt.execute(sql);
    } catch (SQLException e) {
        System.out.println(e.getMessage());
    }

		}
		
		public void connect() throws SQLException {
			System.out.println("Connecting to database...");

			try {
				
				String url = "jdbc:sqlite:test.db"; 
				
				con = DriverManager.getConnection(url);
				System.out.println("Connected to -> " + con.toString());
			} catch (Exception e) {
				System.out.println("Could not load driver!!!");
			}
			
		}
		
		public void disconnect() throws SQLException {
			con.close();
			System.out.println("Disconnected from DB....");
		}
		
		public void save2DB() throws SQLException {
			
		
			if(con != null) {
				
				String cntSql = "select count(*) as count from zaposlenici where id = ?";
				String insSql = "insert into zaposlenici (id, name, surname, address, contact, officeID, work_since) values (?,?,?,?,?,?,?)";
				String updSql = "update zaposlenici set name = ?, surname = ?, address = ?, contact = ?, officeID = ?, work_since = ? where id = ?";
				
				PreparedStatement cntStm = con.prepareStatement(cntSql);
				PreparedStatement insrStm = con.prepareStatement(insSql);
				PreparedStatement updStm = con.prepareStatement(updSql);
				
				
				for(Zaposlenici zapo: zaposlenici) {
					int id = zapo.getId();
					String name = zapo.getName();
					String surname = zapo.getSurname();
					String adr=zapo.getAddress();
					String cont=zapo.getContact();
					String office=zapo.getPoslovnica();
					String datum=zapo.getDatumPocetkaRada();
					
					cntStm.setInt(1, id);
					ResultSet result = cntStm.executeQuery();
					result.next();
					
					int cnt = result.getInt(1);
					System.out.println("Cnt -> " + cnt);
					
					if(cnt == 0) {
						System.out.println("Inserting new buyer -> " + id);
						
						int col = 1;
						insrStm.setInt(col++, id);
						insrStm.setString(col++, name);
						insrStm.setString(col++, surname);
						insrStm.setString(col++,adr);
						insrStm.setString(col++, cont);
						insrStm.setString(col++, office);
						insrStm.setString(col++, datum);
						
						insrStm.executeUpdate();
						
					} else {
						System.out.println("Updating employee -> " + id);
						
						int col = 1;
						updStm.setString(col++, name);
						updStm.setString(col++, surname);
						updStm.setString(col++, adr);
						updStm.setString(col++, cont);
						updStm.setString(col++, office);
						updStm.setString(col++, datum);
						updStm.setInt(col++, id);
						
						updStm.executeUpdate();
					}
				}
				
				cntStm.close();
				insrStm.close();
				updStm.close();


			}
			
		}
		
		public void load4DataBase() throws SQLException {
			if(con != null) {
				System.out.println("Loading from DB...");
				String slctSQL = "select id, name, surname, address, contact, officeID, work_since from zaposlenici order by name ";
				PreparedStatement slcStm = con.prepareStatement(slctSQL);
				
				ResultSet slcResult = slcStm.executeQuery();
				zaposlenici.clear();
				while(slcResult.next()) {
					int id = slcResult.getInt(1);
					String name = slcResult.getString(2);
					String surname = slcResult.getString(3);
					String adr=slcResult.getString(4);
					String cont = slcResult.getString(5);
					String office = slcResult.getString(6);
					String datum=slcResult.getString(7);
					Zaposlenici zaposlenik = new Zaposlenici(id, name, surname, adr, cont, office, datum);
					zaposlenici.add(zaposlenik);
				}
				
				slcResult.close();
				slcStm.close();
			}
		}
		
		public void listAll4DB() {
			System.out.println("<<<<<<<<<<<<< Listing all from DB >>>>>>>>>>>>>");
			for(Zaposlenici zapo : zaposlenici) {
				zapo.description();
			}
		}


		
			
		}
	  
	 
	
	
	 
	
	






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


import tblPckg.Kupci;

public class DataBase {
	
	  private List<Kupci> kupci;
	  private Connection con;
	   
	  public DataBase() {
		  kupci=new LinkedList<Kupci>();
	  }
	  
	 
	  
	  public void setKupci2DB (Kupci kupac) {
		  kupci.add(kupac);
	  }
	  
	  public List<Kupci> getAll4DB() {
		  return kupci;
	  }
	  
	  
	  public void saveDB2File(File file) throws IOException {
			
			Kupci[] kup = kupci.toArray(new Kupci[kupci.size()]);
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(kup); 
			oos.close();
		}
		
		public void readData4File(File file) throws IOException {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			try {
				Kupci[] kup = (Kupci[]) ois.readObject();
				kupci.clear();
				kupci.addAll(Arrays.asList(kup));
			} catch (ClassNotFoundException e) {
	
				e.printStackTrace();
			}
			
			ois.close();
		}
		
		public void createTable() 
{        
	 String url = "jdbc:sqlite:test.db";
     
     
     String sql = "CREATE TABLE IF NOT EXISTS kupci (\n"
             + "    id integer PRIMARY KEY,\n"
             + "    name text NOT NULL,\n"
             + "    surname text NOT NULL,\n"
             + "    address text,\n"
             + "    contact text,\n"
             + "    numCard text\n"
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
				
				String cntSql = "select count(*) as count from kupciTable where id = ?";
				String insSql = "insert into kupciTable (id, name, surname, address, contact, numCard) values (?,?,?,?,?,?)";
				String updSql = "update kupciTable set name = ?, surname = ?, address = ?, contact = ?, numCard = ? where id = ?";
				
				PreparedStatement cntStm = con.prepareStatement(cntSql);
				PreparedStatement insrStm = con.prepareStatement(insSql);
				PreparedStatement updStm = con.prepareStatement(updSql);
				
				
				for(Kupci kup: kupci) {
					int id = kup.getId();
					String name = kup.getName();
					String surname = kup.getSurname();
					String adr=kup.getAddress();
					String cont=kup.getContact();
					String num=kup.getNumCard();
					
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
						insrStm.setString(col++, num);
						
						insrStm.executeUpdate();
						
					} else {
						System.out.println("Updating buyer -> " + id);
						
						int col = 1;
						updStm.setString(col++, name);
						updStm.setString(col++, surname);
						updStm.setString(col++, adr);
						updStm.setString(col++, cont);
						updStm.setString(col++, num);
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
				String slctSQL = "select id, name, surname, address, contact, numCard from kupciTable order by name ";
				PreparedStatement slcStm = con.prepareStatement(slctSQL);
				
				ResultSet slcResult = slcStm.executeQuery();
				kupci.clear();
				while(slcResult.next()) {
					int id = slcResult.getInt(1);
					String name = slcResult.getString(2);
					String surname = slcResult.getString(3);
					String adr=slcResult.getString(4);
					String cont = slcResult.getString(5);
					String num = slcResult.getString(6);
					Kupci kupac = new Kupci(id, name, surname, adr, cont, num);
					kupci.add(kupac);
				}
				
				slcResult.close();
				slcStm.close();
			}
		}
		
		public void listAll4DB() {
			
			for(Kupci kup : kupci) {
				kup.description();
			}
		}
	  
	 
	
	
	 
	
	

}

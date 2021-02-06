package poslovnicePckg;

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


import tblPckg.Poslovnice;

public class DataBase {
	

private List<Poslovnice> poslovnice;
	
	private Connection con;
	
	public DataBase() {
		poslovnice=new LinkedList<Poslovnice>();
		
	}
	
	
	 public void setPoslovnica2DB (Poslovnice poslovnica) {
		  poslovnice.add(poslovnica);
	  }
	  
	  public List<Poslovnice> getAll4DB() {
		  return poslovnice;
	  }
	  
	  
	  public void saveDB2File(File file) throws IOException {
			
			Poslovnice[] poslo = poslovnice.toArray(new Poslovnice[poslovnice.size()]);
			FileOutputStream fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(poslo); 
			oos.close();
		}
		
		public void readData4File(File file) throws IOException {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			try {
				Poslovnice[] poslo = (Poslovnice[]) ois.readObject();
				poslovnice.clear();
				poslovnice.addAll(Arrays.asList(poslo));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ois.close();
		}
		
		public void createTable() 
{        
	 String url = "jdbc:sqlite:test.db";
    
    
    String sql = "CREATE TABLE IF NOT EXISTS poslovnice (\n"
            + "    id integer PRIMARY KEY,\n"
            + "    name text NOT NULL,\n"
            + "    contact text NOT NULL,\n"
            + "    address text NOT NULL, \n"
            + "    type text \n"
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
				
				String cntSql = "select count(*) as count from poslovnice where id = ?";
				String insSql = "insert into poslovnice (id, name, contact, address, type) values (?,?,?,?,?)";
				String updSql = "update poslovnice set name = ?, contact = ?, address = ?, type = ? where id = ?";
				
				PreparedStatement cntStm = con.prepareStatement(cntSql);
				PreparedStatement insrStm = con.prepareStatement(insSql);
				PreparedStatement updStm = con.prepareStatement(updSql);
				
				
				for(Poslovnice posl: poslovnice) {
					int id = posl.getId();
					String name = posl.getName();
					String contact=posl.getContact();
					String address=posl.getAddress();
					String type=posl.getType();
					
					
					cntStm.setInt(1, id);
					ResultSet result = cntStm.executeQuery();
					result.next();
					
					int cnt = result.getInt(1);
					System.out.println("Cnt -> " + cnt);
					
					if(cnt == 0) {
						System.out.println("Inserting new card -> " + id);
						
						int col = 1;
						insrStm.setInt(col++, id);
						insrStm.setString(col++, name);
						insrStm.setString(col++, contact);
						insrStm.setString(col++,address);
						insrStm.setString(col++, type);
						
						
						insrStm.executeUpdate();
						
					} else {
						System.out.println("Updating office -> " + id);
						
						int col = 1;
						updStm.setString(col++, name);
						updStm.setString(col++, contact);
						updStm.setString(col++, address);
						updStm.setString(col++, type);
						
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
				String slctSQL = "select id, name, contact, address, type from poslovnice order by id ";
				PreparedStatement slcStm = con.prepareStatement(slctSQL);
				
				ResultSet slcResult = slcStm.executeQuery();
				poslovnice.clear();
				while(slcResult.next()) {
					int id = slcResult.getInt(1);
					String name = slcResult.getString(2);
					String contact = slcResult.getString(3);
					String address=slcResult.getString(4);
					String type=slcResult.getString(5);
					Poslovnice poslovnica = new Poslovnice(id,name, contact, address,type);
					poslovnice.add(poslovnica);
				}
				
				slcResult.close();
				slcStm.close();
			}
		}
		
		public void listAll4DB() {
			
			for(Poslovnice posl:poslovnice) {
				posl.description();
			}
		}
	  
	 
	
	
	 
	
	

}






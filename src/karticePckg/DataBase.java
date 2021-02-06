package karticePckg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

import tblPckg.Kartice;

public class DataBase {
	
	private List<Kartice> kartice;
	
	private Connection con;
	
	public DataBase() {
		kartice=new LinkedList<Kartice>();
		
	}
	
	public void setKartica2DB(Kartice kartica) {
		kartice.add(kartica);
	}
	
	public List<Kartice> getAll4DB() {
		return kartice;
		
	}
	
	
	public void saveDB2File(File file) throws IOException {
		
		Kartice[] kar=kartice.toArray(new Kartice[kartice.size()]);
		
		FileOutputStream fos=new FileOutputStream(file);
		ObjectOutputStream oos=new ObjectOutputStream(fos);
		
		oos.writeObject(kar);
		oos.close();
	}
	
	public void readData4File(File file) throws IOException {
		FileInputStream fis=new FileInputStream(file);
		ObjectInputStream ois=new ObjectInputStream(fis);
		
		try {
			Kartice[] kar=(Kartice[]) ois.readObject();
			kartice.clear();
			kartice.addAll(Arrays.asList(kar));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ois.close();
		
	}
	
	public void createTable() {
		
		 String url = "jdbc:sqlite:test.db";
		    
		    
		    String sql = "CREATE TABLE IF NOT EXISTS kartice (\n"
		            + "    id integer PRIMARY KEY,\n"
		            + "    card_number text NOT NULL,\n"
		            + "    card_points text \n"
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
						
						String cntSql = "select count(*) as count from kartice where id = ?";
						String insSql = "insert into kartice (id, card_number, card_type, card_points) values (?,?,?,?)";
						String updSql = "update kartice set card_number = ?, card_type = ?, card_points = ? where id = ?";
						
						PreparedStatement cntStm = con.prepareStatement(cntSql);
						PreparedStatement insrStm = con.prepareStatement(insSql);
						PreparedStatement updStm = con.prepareStatement(updSql);
						
						for(Kartice kar:kartice) {
							int id=kar.getId();
							String number=kar.getCardNumber();
							String type=kar.getCardType();
							String points=kar.getCardPoints();
							
							cntStm.setInt(1, id);
							ResultSet result=cntStm.executeQuery();
							result.next();
							
							int cnt=result.getInt(1);
							
							if(cnt == 0) {
								int col=1;
								insrStm.setInt(col++, id);
								insrStm.setString(col++, number);
								insrStm.setString(col++, type);
								insrStm.setString(cnt++, points);
								
								insrStm.executeUpdate();
							} else {
								System.out.println("Updating cards --> "+id);
								
								int col=1;
								updStm.setString(col++, number);
								updStm.setString(cnt++, type);
								updStm.setString(cnt++, points);
								
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
					if (con != null) {
				    String slctSQL = "select id, card_number, card_type, card_points from kartice order by id ";
                    PreparedStatement slcStm=con.prepareStatement(slctSQL);
                    
                    ResultSet slcResult=slcStm.executeQuery();
                    kartice.clear();
                    
                    while(slcResult.next()) {
                    	int id=slcResult.getInt(1);
                    	String number=slcResult.getString(2);
                    	String type=slcResult.getString(3);
                    	String points=slcResult.getString(4);
                    	
                    	Kartice kartica=new Kartice (id,number,type,points);
                    	kartice.add(kartica);
                    	
                    	
                    }
                    
                    slcResult.close();
                    slcStm.close();
                    
					}
				}
				
				public void listAll4DB() {
					for(Kartice kar:kartice) {
						kar.description();
					}
				}

}

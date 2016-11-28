package kbr.vaish.database_operations;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kbr.vaish.columns.ColumnNames;

public class OperationsImpl implements DataBaseInter {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://kbrsvrp01/kbrcommondb?user=kbrcommon&password=kbr@123");
OperationsImpl o = new OperationsImpl();
List<ColumnNames> c = new ArrayList<ColumnNames>();


/*o.createTable(con,o.input());*/
/*o.dropTable(con, o.input());*/
/*o.insertValues(con,o.input());*/
/*o.displayTable(con,o.input());*/
/*o.deleteTableRow(con,o.input(),o.input1());*/
o.updateTableField(con,o.input(), o.input1());
con.close();
	}

	
	public String input() {
		System.out.println("enter the tablename");
		String in = new Scanner(System.in).next();
		return in;
	}
	public String input1() {
		System.out.println("enter the column Name");
		String in = new Scanner(System.in).next();
		return in;
	}

	public void createTable(Connection con, String tableName)  {
		try {
			PreparedStatement ps = con.prepareStatement("create table "+tableName+"(id int auto_increment primary key)");
		
			boolean i = ps.execute();
			if(i==true)
			{
				System.out.println("failed to drop the table");
			}
			else
				System.out.println("created successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		
	}


	/*public List settingValues(List<ColumnNames> cn)
	{
		
		return cn;	
	}*/


	public void displayTable(Connection con, String TableName) {
		
		try {
			PreparedStatement ps = con.prepareStatement("select * from "+TableName);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			while(rs.next())
			{
				
				int c=rsmd.getColumnCount();
				PrintStream out = System.out;
		for(int i =1;i<=c;i++)
		{
			 if (i > 1) {
		            out.print(",");
		            }

		            int type = rsmd.getColumnType(i);
		            //System.out.println(type);
		            if (type == Types.VARCHAR || type == Types.CHAR) {
		                out.print(rs.getString(i));
		            } else if(type == Types.DATE){
		                out.print(rs.getDate(i));
		      
		            }
		            else if(type == Types.DOUBLE){
		                out.print(rs.getDouble(i));
		            }else
		                {
		                	out.print(rs.getLong(i));
		                }
		            }
		out.println();
		}
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/*public void updateTableField(Connection con, String field) {
		// TODO Auto-generated method stub
		
	}*/








	public void dropTable(Connection con, String tableName) {
		try {
			PreparedStatement ps = con.prepareStatement("drop table "+tableName);
		//	ps.setString(1, tableName);
			boolean i = ps.execute();
			if(i==true)
			{
				System.out.println("failed to drop the table");
			}
			else
				System.out.println("dropped successfully");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
	
		
	}






	public void deleteTableRow(Connection con, String tableName, String field) {
		// TODO Auto-generated method stub
		try {
			do
			{
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery("select * from "+tableName);
			ResultSetMetaData rsmd = rs.getMetaData();
			PreparedStatement ps = con.prepareStatement("delete from "+tableName+" where "+field+"=?");
			Scanner sc = new Scanner(System.in);
			int c = rsmd.getColumnCount(); 
			int i;
		for(i=1;i<=c;i++)
			{
			String cn = rsmd.getColumnName(i);
			System.out.println(cn);
			if(cn.equals(field))
			{
				System.out.println(i);
				break;
			}
			}
			
			
			int type = rsmd.getColumnType(i);
			System.out.println("enter the row-id u want to remove");
			if(type == Types.VARCHAR || type == Types.CHAR)
			{
				ps.setString(1, sc.next());	
			}
			else if(type == Types.DATE)
			{
				ps.setString(1, sc.next());	
			}
			else if(type == Types.DOUBLE ||type == Types.FLOAT )
			{
				ps.setDouble(1, sc.nextDouble());	
			}else
			{
				ps.setLong(1, sc.nextLong());
			}
			ps.executeUpdate();
			System.out.println("do u want to delete any more rows y/n");
			String s4=sc.next();
			if(s4.startsWith("n"))
			{
				break;
			}
			}while(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public void updateTableField(Connection con, String tableName, String field) {
		// TODO Auto-generated method stub
		try {
			do {
				String id;
				Statement s = con.createStatement();
				ResultSet rs = s.executeQuery("select * from " + tableName);
				ResultSetMetaData rsmd = rs.getMetaData();
				Scanner sc = new Scanner(System.in);
				System.out.println("enter the primary key columnName or unique column u want to update from");
				id=sc.next();
				PreparedStatement ps = con.prepareStatement("update "
						+ tableName + " set " + field + "=? where " + id
						+ "=?");
				final int c = rsmd.getColumnCount();
				int i;
				for(i=1;i<=c;i++)
				{
				String cn = rsmd.getColumnName(i);
				System.out.println(cn);
				if(cn.equals(field))
				{
					System.out.println(i);
					break;
				}
				}
				int type = rsmd.getColumnType(i);
				System.out.println("enter the new value for the given column");
				if(type == Types.VARCHAR || type == Types.CHAR)
				{
					ps.setString(1, sc.next());	
				}
				else if(type == Types.DATE)
				{
					ps.setString(1, sc.next());	
				}
				else if(type == Types.DOUBLE ||type == Types.FLOAT )
				{
					ps.setDouble(1, sc.nextDouble());	
				}else
				{
					ps.setLong(1, sc.nextLong());
				}
				for(i=1;i<=c;i++)
				{
				String cn = rsmd.getColumnName(i);
				System.out.println(cn);
				if(cn.equals(id))
				{
					System.out.println(i);
					break;
				}
				}
				int type1 = rsmd.getColumnType(i);
				System.out.println("enter the row id for updation of specific row");
				if(type1 == Types.VARCHAR || type1 == Types.CHAR)
				{
					ps.setString(2, sc.next());	
				}
				else if(type1 == Types.DATE)
				{
					ps.setString(2, sc.next());	
				}
				else if(type1 == Types.DOUBLE ||type1 == Types.FLOAT )
				{
					ps.setDouble(2, sc.nextDouble());	
				}else
				{
					ps.setLong(2, sc.nextLong());
				}
				ps.executeUpdate();
				System.out.println("do u want to continue updating y/n");
				String co = sc.next();
				if(co.startsWith("n"))
				{
					break;
				}
			} while (true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void insertValues(Connection con, String TableName) {
		// TODO Auto-generated method stub
		try {
			Scanner sc = new Scanner(System.in);
			PreparedStatement ps = con.prepareStatement("select * from "+TableName);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int c=rsmd.getColumnCount();
			System.out.println(c);
			StringBuilder s = new StringBuilder();
			String str = "?,";
			for(int i=1;i<c;i++)
			{
				
				s.append(str);
			}
			s.append('?');
			System.out.println(s);
			ps = con.prepareStatement("insert into "+TableName+" values"+"("+s+")");
			do
			{
				
				
				//PrintStream out = System.out;
				
		for(int i =1;i<=c;i++)
		{
		/*	 if (i > 1) {
		            out.print(",");
		            }*/
System.out.println("insert into the feild : "+rsmd.getColumnName(i)+"("+rsmd.getColumnTypeName(i)+")");
		            int type = rsmd.getColumnType(i);
		            /*System.out.println(type);*/
		        
		            if (type == Types.VARCHAR || type == Types.CHAR) {
		               ps.setString(i,sc.next());
		            } else if(type == Types.DATE){
		                ps.setString(i, sc.next());
		      
		            }
		            else if(type == 93)
		            {
		            	  ps.setString(i, sc.next());
		            }
		            else if(type == Types.DOUBLE){
		               // out.print(rs.getDouble(i));
		            	ps.setFloat(i, sc.nextFloat());
		            }else
		                {
		            	ps.setLong(i, sc.nextLong());
		                	//out.print(rs.getLong(i));
		                }
		          
		            }
		  ps.executeUpdate();
		System.out.println("do you want to insert more values y/n");
		String s1=sc.next();
		if(s1.startsWith("n"))
		{
			break;
		}
		
		}while(true);
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	

}

package kbr.vaish.database_operations;

import java.sql.Connection;
import java.util.List;

import kbr.vaish.columns.ColumnNames;

public interface DataBaseInter {
	public String input();
public void createTable(Connection con,String tableName);
public void insertValues(Connection con,String tableName);
public void displayTable(Connection con,String tableName);  
public void updateTableField(Connection con,String tableName,String field);
/*public void alterTableAdd(Connection con,String TableName,String columnName);*/
public void deleteTableRow(Connection con,String tableName,String field );
public void dropTable(Connection con,String tableName);
/*public void updateTableAllField(Connection con,String tableName,String field);*/
/*public void alterTableModify(Connection con,String TableName,String columnName);
public void alterTableChange(Connection con,String TableName,String columnName);*/

}

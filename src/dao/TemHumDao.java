package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import entity.TemHum;
import util.JDBCUtils;

public class TemHumDao {
	public void addInfo(TemHum temhum) throws SQLException {
		QueryRunner qr=new QueryRunner();
		
		String sql="insert into temhum values(?,?)";
		
		int temperature=temhum.getTemperature();
		int humidity=temhum.getHumidity();
		
		Object [] params={temperature,humidity};
		
		Connection conn=JDBCUtils.getConnection();
		 
		qr.update(conn, sql, params);
		
		JDBCUtils.closeConnection(conn);
	}
}

package service;

import java.sql.SQLException;

import dao.TemHumDao;
import entity.TemHum;
import util.JDBCUtils;

public class TemHumService {
	TemHumDao temHumDao=new TemHumDao();
	
	public void add(TemHum temHum) {
		try {
			JDBCUtils.beginTransaction();
			temHumDao.addInfo(temHum);
			JDBCUtils.commitTransaction();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	    //初始化 c3p0连接池
		private static ComboPooledDataSource ds=new ComboPooledDataSource();
		
		//事务专用链接
		private static Connection conn=null;
		
		//返回链接
		public static Connection getConnection(){
			if(conn!=null) return conn;
			
			try {
				//返回链接对象
				return ds.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		//返回连接池对象   数据源
		public static DataSource getDataSource(){
			return ds;
		}
		
		
		//开启事务
		/*
		 * 1.获得一个Connection  设置他的SetautoCommit(false)
		 * 2.保证我们开启事务/提交事务/回滚事务使用同一个链接  所有定义一个公用的连接对象
		 * 3.确保Dao使用的链接也是公用的?  ****
		 */
		public static void beginTransaction() throws SQLException{
			if(conn!=null){
				throw new SQLException("事务已开启,再开打死你");
			}
			//给事务的专用链接赋值
			conn=getConnection();
			//手动的开启事务/关闭自动提交事务
			conn.setAutoCommit(false);
		}
		
		
		//提交事务
		/*
		 *1.获取 beginTransaction中为公共链接赋的对象
		 *2.调用commit方法
		 *3.归还链接
		 */
		public static void commitTransaction() throws SQLException{
			if(conn==null){
				throw new SQLException("事务未开启,提交打死你");
			}
			//提交事务
			conn.commit();
			//归还链接
			conn.close();
			
			//表示事务已经提交结束,下次调用获取连接 返回的就不是之前的链接
			conn=null;
		}
		
		
		
		//回滚事务
		/*
		 *1.获取 beginTransaction中为公共链接赋的对象
		 *2.调用rollback方法
		 *3.归还链接
		 * 
		 */
		public static void rollbackTransaction() throws SQLException{
			if(conn==null){
				throw new SQLException("事务未开启,回滚打死你");
			}
			//回滚事务
			conn.rollback();
			//归还链接
			conn.close();
			//表示事务已经回滚结束,下次调用获取连接 返回的就不是之前的链接
			conn=null;
		}
		
		
		
		//关闭数据库操作的链接
		public static void closeConnection(Connection con) throws SQLException{
			//你传入的链接不是 事务专用链接   自己关闭
			if(con!=conn){
				con.close();
			}
			
			//如果当前的事务专用链接是null  说明没人开启事务   那我们的链接就肯定是 非事务链接
			//所有必须手动关闭
			if(conn==null){
				con.close();
			}
		}
		
		
		
		
		
}

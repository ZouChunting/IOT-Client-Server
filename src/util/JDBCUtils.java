package util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JDBCUtils {
	    //��ʼ�� c3p0���ӳ�
		private static ComboPooledDataSource ds=new ComboPooledDataSource();
		
		//����ר������
		private static Connection conn=null;
		
		//��������
		public static Connection getConnection(){
			if(conn!=null) return conn;
			
			try {
				//�������Ӷ���
				return ds.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		
		//�������ӳض���   ����Դ
		public static DataSource getDataSource(){
			return ds;
		}
		
		
		//��������
		/*
		 * 1.���һ��Connection  ��������SetautoCommit(false)
		 * 2.��֤���ǿ�������/�ύ����/�ع�����ʹ��ͬһ������  ���ж���һ�����õ����Ӷ���
		 * 3.ȷ��Daoʹ�õ�����Ҳ�ǹ��õ�?  ****
		 */
		public static void beginTransaction() throws SQLException{
			if(conn!=null){
				throw new SQLException("�����ѿ���,�ٿ�������");
			}
			//�������ר�����Ӹ�ֵ
			conn=getConnection();
			//�ֶ��Ŀ�������/�ر��Զ��ύ����
			conn.setAutoCommit(false);
		}
		
		
		//�ύ����
		/*
		 *1.��ȡ beginTransaction��Ϊ�������Ӹ��Ķ���
		 *2.����commit����
		 *3.�黹����
		 */
		public static void commitTransaction() throws SQLException{
			if(conn==null){
				throw new SQLException("����δ����,�ύ������");
			}
			//�ύ����
			conn.commit();
			//�黹����
			conn.close();
			
			//��ʾ�����Ѿ��ύ����,�´ε��û�ȡ���� ���صľͲ���֮ǰ������
			conn=null;
		}
		
		
		
		//�ع�����
		/*
		 *1.��ȡ beginTransaction��Ϊ�������Ӹ��Ķ���
		 *2.����rollback����
		 *3.�黹����
		 * 
		 */
		public static void rollbackTransaction() throws SQLException{
			if(conn==null){
				throw new SQLException("����δ����,�ع�������");
			}
			//�ع�����
			conn.rollback();
			//�黹����
			conn.close();
			//��ʾ�����Ѿ��ع�����,�´ε��û�ȡ���� ���صľͲ���֮ǰ������
			conn=null;
		}
		
		
		
		//�ر����ݿ����������
		public static void closeConnection(Connection con) throws SQLException{
			//�㴫������Ӳ��� ����ר������   �Լ��ر�
			if(con!=conn){
				con.close();
			}
			
			//�����ǰ������ר��������null  ˵��û�˿�������   �����ǵ����ӾͿ϶��� ����������
			//���б����ֶ��ر�
			if(conn==null){
				con.close();
			}
		}
		
		
		
		
		
}

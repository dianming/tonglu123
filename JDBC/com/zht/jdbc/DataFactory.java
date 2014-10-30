package com.zht.jdbc;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DataFactory {

	static Logger logs = Logger.getLogger(DataFactory.class);
	
	private static String URL = "jdbc:mysql://localhost:3306/book?useUnicode=true&amp;characterEncoding=UTF-8&amp;zeroDateTimeBehavior=convertToNull";
	private static String USER = "root";
	private static String PWD = "root";
	
	public static void main(String[] args) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		InputStreamReader isr=new InputStreamReader(new FileInputStream("D:\\wocao.txt"));
		BufferedReader br=new BufferedReader(isr);
		String lineTxt=null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(URL, USER, PWD);
//			int count=0;
			StringBuffer sb=null;
			StringBuffer sb1=null;
			Long initTime = System.currentTimeMillis();
			logs.debug("进入时间"+initTime);
			while((lineTxt=br.readLine())!=null){
				sb = new StringBuffer();
				sb1 = new StringBuffer();
				System.out.println("----->"+lineTxt);
				char[] input = lineTxt.trim().toCharArray();
				for (int i=0;i<input.length;i++) {
					if(Character.toString(input[i]).matches("[\\u4e00-\\u9fa5]")){
						sb.append(input[i]);
					}else{
						sb1.append(input[i]);
					};
				}
//				count++;
				String sql = "insert into indexDatabase (pinyin,hanzi) values(?,?)";
				ps = conn.prepareStatement(sql);
				ps.setString(1,sb1.toString());
				ps.setString(2,sb.toString());
				ps.addBatch(sql);
				logs.debug("===>"+sql);
 				ps.execute();
				ps.close();
			}
			isr.close();
			logs.debug("done"+(System.currentTimeMillis()-initTime));
//			System.out.println(count);
//			rs = ps.executeQuery();
//			while(rs.next()){
//				System.out.println(rs.getInt(1));
//			}
		} catch (Exception e) {
			logs.debug("驱动加载失败", e);
		} finally {
			try {
//				rs.close();
//				ps.close();
				conn.close();
			} catch (SQLException e) {
				logs.debug("关闭数据异常", e);
			}
		}
	}
}

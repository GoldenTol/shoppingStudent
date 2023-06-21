package com.shopping.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
-- SID 확인 명령어 : 
select instance from v$thread;
 
-- http 포트 확인 명령어 : 
select dbms_xdb.gethttpport() from dual;

-- 포트 변경 명령어 : 
-- exec dbms_xdb.sethttpport(변경할포트번호);

-- 리스너 포트 번호 확인 : listener.org 파일
*/

// 모든 Dao들의 슈퍼 클래스 입니다.
public class SuperDao {
	private String driver = "oracle.jdbc.driver.OracleDriver"; 
	// "oracle.jdbc.driver.OracleDriver" ← 오타/대소문자 오타나면 Exception 뜸
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "shopping";
	private String password = "oracle";
	
	protected Connection conn = null;
	// Connect 확장자 sql / 잘 모르겠다 싶을 땐 java.sql

	public SuperDao() {
		// drive 로딩
		try {
			Class.forName(driver);
			// Connection conn = this.getConnection();
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	public Connection getConnection() { // 접속 객체 구하기
		try {
			conn = DriverManager.getConnection(url, id, password);
			// new 객체 만들듯이 객체를 의미있는 객체로 만듬 
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}

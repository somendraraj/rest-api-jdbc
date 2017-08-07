package com.games.listing.apiservice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.games.listing.common.Global;


@RestController
public class ApiService {

	private static final Logger log = LoggerFactory.getLogger(ApiService.class);

	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	public ResponseEntity<String> service(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info("*********Inside Service*********");
		return new ResponseEntity<String>(ApiService.dbReader().toJSONString(), HttpStatus.OK);

	}

	@SuppressWarnings("unchecked")
	static JSONArray dbReader() throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		JSONArray jArray = new JSONArray();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection(Global.JDBC_URL, Global.JDBC_USERNAME, Global.JDBC_PASSWORD);

			Statement stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
			stmt.setFetchSize(Global.DB_FETCH_SIZE);
			ResultSet rs = stmt.executeQuery("select * from `table 8`");
			System.out.println("Fetching result");
			while (rs.next()) {
				JSONObject json = new JSONObject();
				json.put("title", rs.getString(1));
				json.put("platform", rs.getString(2));
				json.put("score", rs.getBigDecimal(3));
				json.put("genre", rs.getString(4));
				json.put("editors_choice", rs.getString(5));
				jArray.add(json);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		log.info("Json Size: " + jArray.size());
		log.info("Json: " + jArray);
		return jArray;
	}

}

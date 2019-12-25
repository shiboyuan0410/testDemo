package com.example.demo.common.controller;

import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public class CommonFileControllerTest {

	@Autowired
	@Qualifier("primaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate_oracle;

	@Autowired
	@Qualifier("secondaryJdbcTemplate")
	protected JdbcTemplate jdbcTemplate_mysql;

	@Test
	public void test() {

		Map<String, Object> queryForMap = jdbcTemplate_oracle.queryForMap("select * from sys_user");

		System.out.println(queryForMap.size());
	}

}

package com.example.ruleengineservice.repository.system;


/**
 * @author gaowenfeng02
 */
public class MysqlFileSystem extends BaseDbFileSystem {
	@Override
	public String databaseType() {
		return "mysql";
	}
}

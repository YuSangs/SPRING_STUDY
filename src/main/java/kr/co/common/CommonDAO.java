package kr.co.common;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CommonDAO {

	@Autowired
	SqlSessionTemplate sqlSession;
	
	public List<Map<String, Object>> list(String statement, Map<String, Object> param) {
		return sqlSession.selectList(statement, param);
	}

	public Map<String, Object> select(String statement, Map<String, Object> param) {
		return sqlSession.selectOne(statement, param);
	}

	public int update(String statement, Map<String, Object> param) {
		return sqlSession.update(statement, param);
	}
	
	public int delete(String statement, Map<String, Object> param) {
		return sqlSession.delete(statement, param);
	}
	
	public int insert(String statement, Map<String, Object> param) {
		return sqlSession.insert(statement, param);
	}
}

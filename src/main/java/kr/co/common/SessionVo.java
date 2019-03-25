package kr.co.common;

public class SessionVo {

	private int login_idx;
	private String login_id;
	private String login_name;
	private String login_auth;
	
	public int getLogin_idx() {
		return login_idx;
	}
	public void setLogin_idx(int login_idx) {
		this.login_idx = login_idx;
	}
	public String getLogin_id() {
		return login_id;
	}
	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}
	public String getLogin_name() {
		return login_name;
	}
	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}
	public String getLogin_auth() {
		return login_auth;
	}
	public void setLogin_auth(String login_auth) {
		this.login_auth = login_auth;
	}
}

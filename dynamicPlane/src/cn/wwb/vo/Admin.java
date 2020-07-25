package cn.wwb.vo;

public class Admin {
	private String tel;
	private String pwd;
	private String name;
	private String path;
	public Admin(String tel, String pwd, String name, String path) {
		super();
		this.tel = tel;
		this.pwd = pwd;
		this.name = name;
		this.path = path;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
}

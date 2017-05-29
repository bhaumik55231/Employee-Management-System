package Model;

import java.io.Serializable;

public class registration  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String first_name;
	private String last_name;
	private String address;
	private Long phone;
	private String email_id;
	private String user_id;
	private String password;
	private String confirmpassword;
	private String user_type;
	private String employee_status;
	private String immediate_manager_id;
	private String manager_hierarchy;
	private String division_name;
	
	public String getConfirmpassword() {
		return confirmpassword;
	}
	public void setConfirmpassword(String confirmpassword) {
		this.confirmpassword = confirmpassword;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getEmail_id() {
		return email_id;
	}
	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public String getEmployee_status() {
		return employee_status;
	}
	public void setEmployee_status(String employee_status) {
		this.employee_status = employee_status;
	}
	public String getImmediate_manager_id() {
		return immediate_manager_id;
	}
	public void setImmediate_manager_id(String immediate_manager_id) {
		this.immediate_manager_id = immediate_manager_id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getPhone() {
		return phone;
	}
	public void setPhone(Long phone) {
		this.phone = phone;
	}
	public String getManager_hierarchy() {
		return manager_hierarchy;
	}
	public void setManager_hierarchy(String manager_hierarchy) {
		this.manager_hierarchy = manager_hierarchy;
	}
	public String getDivision_name() {
		return division_name;
	}
	public void setDivision_name(String division_name) {
		this.division_name = division_name;
	}
	

}

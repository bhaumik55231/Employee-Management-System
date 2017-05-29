package Model;

import java.io.Serializable;

public class directory implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	private int directory_id;
	private String directory_access_permissions;
	private String directory_name;
	private String directory_description;
	private String user_id;
	private String ate_id;
	private String manager_hierarchy;
	private String new_permission;
	private String new_permission_given_by;
	public int getDirectory_id() {
		return directory_id;
	}
	public void setDirectory_id(int directory_id) {
		this.directory_id = directory_id;
	}
	
	public String getDirectory_access_permissions() {
		return directory_access_permissions;
	}
	public void setDirectory_access_permissions(String directory_access_permissions) {
		this.directory_access_permissions = directory_access_permissions;
	}
	public String getDirectory_name() {
		return directory_name;
	}
	public void setDirectory_name(String directory_name) {
		this.directory_name = directory_name;
	}
	public String getDirectory_description() {
		return directory_description;
	}
	public void setDirectory_description(String directory_description) {
		this.directory_description = directory_description;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getAte_id() {
		return ate_id;
	}
	public void setAte_id(String ate_id) {
		this.ate_id = ate_id;
	}
	public String getManager_hierarchy() {
		return manager_hierarchy;
	}
	public void setManager_hierarchy(String manager_hierarchy) {
		this.manager_hierarchy = manager_hierarchy;
	}
	public String getNew_permission() {
		return new_permission;
	}
	public void setNew_permission(String new_permission) {
		this.new_permission = new_permission;
	}
	public String getNew_permission_given_by() {
		return new_permission_given_by;
	}
	public void setNew_permission_given_by(String new_permission_given_by) {
		this.new_permission_given_by = new_permission_given_by;
	}
	
}

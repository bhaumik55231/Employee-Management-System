package Model;

import java.io.Serializable; 
import java.sql.Blob;

public class retrieveFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int srno;
	private Blob file;
	private String file_name;
	private String file_type;
	private String description;
	private String user_id;
	private String director_name;
	private String manager_hierarchy;
	private String ate_id;
	private String access_permission;
	private String uploaded_by;
	private String new_permission;
	private String new_permission_given_by;
	public String getDirector_name() {
		return director_name;
	}
	public void setDirector_name(String director_name) {
		this.director_name = director_name;
	}
	public int getSrno() {
		return srno;
	}
	public void setSrno(int srno) {
		this.srno = srno;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Blob getFile() {
		return file;
	}
	public void setFile(Blob file) {
		this.file = file;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getFile_type() {
		return file_type;
	}
	public void setFile_type(String file_type) {
		this.file_type = file_type;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getManager_hierarchy() {
		return manager_hierarchy;
	}
	public void setManager_hierarchy(String manager_hierarchy) {
		this.manager_hierarchy = manager_hierarchy;
	}
	public String getAte_id() {
		return ate_id;
	}
	public void setAte_id(String ate_id) {
		this.ate_id = ate_id;
	}
	public String getAccess_permission() {
		return access_permission;
	}
	public void setAccess_permission(String access_permission) {
		this.access_permission = access_permission;
	}
	public String getUploaded_by() {
		return uploaded_by;
	}
	public void setUploaded_by(String uploaded_by) {
		this.uploaded_by = uploaded_by;
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

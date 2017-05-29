package Model;

import java.io.Serializable;

public class leaveRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int leave_id;
	private String first_name;
	private String last_name;
	private String leave_type;
	private String leave_description;
	private int leave_available;
	private String leave_status;
	private String user_id;
	private String immediate_manager_id;
	private String date;
	private int days;
	public int getLeave_id() {
		return leave_id;
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

	public void setLeave_id(int leave_id) {
		this.leave_id = leave_id;
	}
	public String getLeave_type() {
		return leave_type;
	}
	public void setLeave_type(String leave_type) {
		this.leave_type = leave_type;
	}
	public String getLeave_description() {
		return leave_description;
	}
	public void setLeave_description(String leave_description) {
		this.leave_description = leave_description;
	}
	public int getLeave_available() {
		return leave_available;
	}
	public void setLeave_available(int leave_available) {
		this.leave_available = leave_available;
	}
	public String getLeave_status() {
		return leave_status;
	}
	public void setLeave_status(String leave_status) {
		this.leave_status = leave_status;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getImmediate_manager_id() {
		return immediate_manager_id;
	}
	public void setImmediate_manager_id(String immediate_manager_id) {
		this.immediate_manager_id = immediate_manager_id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
		
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	
	
}

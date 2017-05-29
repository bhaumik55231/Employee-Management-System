package Model;

import java.io.Serializable;

public class leaveRemainingVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String user_id;
	private int leave_available;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getLeave_available() {
		return leave_available;
	}
	public void setLeave_available(int leave_available) {
		this.leave_available = leave_available;
	}
	
	
}

package DAO;

import java.sql.ResultSet;
import java.sql.Statement; 
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimerTask;

import Model.leaveRemainingVO;
import Model.leaveRequest;
import Model.salaryModel;

public class leaveRequestDAO extends TimerTask{
		
	public void run() {
		System.out.println("Checking Automatically");
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("update leave_remaining set leave_available = leave_available+4");
			ResultSet rs=st.executeQuery("select * from salary");
			while(rs.next()){
				//salaryModel salaryModel=new salaryModel();
				Date date=new Date();
				java.text.SimpleDateFormat sdf =new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String currentTime = sdf.format(date);
				
				String user_id=rs.getString("user_id");
				String first_name=rs.getString("first_name");
				String last_name=rs.getString("last_name");
				String email_id=rs.getString("email_id");
				String division_name=rs.getString("division_name");
				String user_type=rs.getString("user_type");
				String salary_per_month=rs.getString("salary_per_month");
				leaveRequestDAO.generateSalary(currentTime,first_name,last_name,user_id,email_id,division_name,user_type,salary_per_month);
				}
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	    //TODO generate report
	  }
	public void leaveAvailable(leaveRemainingVO leaveRemainingVO) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("INSERT INTO leave_remaining( user_id, leave_available) values('"+leaveRemainingVO.getUser_id()+"','"+leaveRemainingVO.getLeave_available()+"')");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public static void generateSalary(String currentTime,String first_name,String last_name,String user_id,String email_id,String division_name,String user_type,String salary_per_month) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("insert into salary (date,first_name,last_name,user_id,email_id,division_name,user_type,salary_per_month)values('"+currentTime+"','"+first_name+"','"+last_name+"','"+user_id+"','"+email_id+"','"+division_name+"','"+user_type+"','"+salary_per_month+"')");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void forwardLeave(int leave_available, String user_id) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("update leave_remaining set leave_available='"+leave_available+"' where user_id='"+user_id+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void approveLeave(leaveRequest leaveRequest) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("update leave_request set leave_status='approve' where leave_id='"+leaveRequest.getLeave_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void declineLeave(leaveRequest leaveRequest) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("update leave_request set leave_status='decline' where leave_id='"+leaveRequest.getLeave_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public int checkAvailability(leaveRemainingVO leaveRemainingVO) {
		// TODO Auto-generated method stub
		int status=0;
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("SELECT leave_available from leave_remaining where user_id='"+leaveRemainingVO.getUser_id()+"'");
			while(rs.next()){
				status=rs.getInt("leave_available");
			}
			
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return status;
	}
	public String getUserId(leaveRequest leaveRequest) {
		// TODO Auto-generated method stub
		String user_id=null;
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("SELECT user_id from leave_request where leave_id='"+leaveRequest.getLeave_id()+"'");
			while(rs.next()){
				user_id=rs.getString("user_id");
			}
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return user_id;
	}
	public List<leaveRequest> fetchLeaveRequest(leaveRequest leaveRequest) {
		List<leaveRequest> leaveRequestList = new ArrayList<leaveRequest>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from leave_request where immediate_manager_id='"+leaveRequest.getImmediate_manager_id()+"' and leave_status='pending'");
		
		while (rs.next()) {
			leaveRequest leaveRequest1 = new leaveRequest();
			leaveRequest1.setFirst_name(rs.getString("first_name"));
			leaveRequest1.setLast_name(rs.getString("last_name"));
			leaveRequest1.setLeave_type(rs.getString("leave_type"));
			leaveRequest1.setLeave_description(rs.getString("leave_description"));
			leaveRequest1.setDate(rs.getString("date"));
			leaveRequest1.setDays(rs.getInt("days"));
			leaveRequest1.setLeave_id(rs.getInt("leave_id"));
			leaveRequestList.add(leaveRequest1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return leaveRequestList;

	}
	public List<leaveRequest> list_of_leaves(leaveRequest leaveRequest) {
		List<leaveRequest> leaveRequestList = new ArrayList<leaveRequest>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from leave_request where user_id='"+leaveRequest.getUser_id()+"' order by leave_id desc");
		
		while (rs.next()) {
			leaveRequest leaveRequest1 = new leaveRequest();
			leaveRequest1.setLeave_type(rs.getString("leave_type"));
			leaveRequest1.setLeave_description(rs.getString("leave_description"));
			leaveRequest1.setDate(rs.getString("date"));
			leaveRequest1.setDays(rs.getInt("days"));
			leaveRequest1.setLeave_id(rs.getInt("leave_id"));
			leaveRequest1.setLeave_status(rs.getString("leave_status"));
			leaveRequestList.add(leaveRequest1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return leaveRequestList;

	}
	public void updateLeaveAvailibility(leaveRemainingVO leaveRemainingVO) {
		// TODO Auto-generated method stub
		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("UPDATE leave_remaining set leave_available='"+leaveRemainingVO.getLeave_available()+"' where user_id='"+leaveRemainingVO.getUser_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void updateName(leaveRequest leaveRequest) {
		// TODO Auto-generated method stub
		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("UPDATE leave_request set first_name='"+leaveRequest.getFirst_name()+"', last_name='"+leaveRequest.getLast_name()+"' where user_id='"+leaveRequest.getUser_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void insertLeaveRequest(leaveRequest leaveRequest) {
		// TODO Auto-generated method stub
		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("insert into leave_request(date , days, first_name, last_name, leave_type, leave_description,user_id, immediate_manager_id) values ('"+leaveRequest.getDate()+"','"+leaveRequest.getDays()+"','"+leaveRequest.getFirst_name()+"','"+leaveRequest.getLast_name()+"','"+leaveRequest.getLeave_type()+"','"+leaveRequest.getLeave_description()+"','"+leaveRequest.getUser_id()+"','"+leaveRequest.getImmediate_manager_id()+"')");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	
}


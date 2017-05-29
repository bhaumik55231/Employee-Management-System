package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.loginModel;
import Model.registration;



public class registrationDAO {
	public void register(registration reg) {
		// TODO Auto-generated method stub

		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("INSERT INTO registration( first_name, last_name, address, phone_no, email_id, user_id, password, user_type, employee_status, division_name) values('"+reg.getFirst_name()+"','"+reg.getLast_name()+"','"+reg.getAddress()+"','"+reg.getPhone()+"','"+reg.getEmail_id()+"','"+reg.getUser_id()+"','"+reg.getPassword()+"','"+reg.getUser_type()+"','"+reg.getEmployee_status()+"','Information Technology') ");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public String activeOrInactive(loginModel login) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("SELECT employee_status from registration where user_id='"+login.getUser_id()+"'");
			while(rs.next()){
				String status=rs.getString("employee_status");
				if(status.equals("inactive")){
					return status;
				}
				else{
					return status;
				}
			}
			
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return "success";
	}
	public String getAllPermission(registration registration) {
		// TODO Auto-generated method stub
		String new_permission=null;
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM upload_file WHERE user_id='"+registration.getUser_id()+"' and new_permission_given_by IS NOT NULL");
			while(rs.next()){
				new_permission=new_permission+" "+rs.getString("new_permission") + " " +rs.getString("new_permission_given_by") ;
			}
			
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return new_permission;
	}
	public String getImmediateManager(registration registration) {
		// TODO Auto-generated method stub
		String immediate_manager_id=null;
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("SELECT immediate_manager_id from registration where user_id='"+registration.getUser_id()+"'");
			while(rs.next()){
				immediate_manager_id=rs.getString("immediate_manager_id");
			}
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return immediate_manager_id;
	}
	public String getManagerHierarchy(registration registration) {
		// TODO Auto-generated method stub
		String manager_hierarchy=null;
		try{
			Statement st=Dbconn.getConnection().createStatement();
			
			ResultSet rs=st.executeQuery("SELECT manager_hierarchy from registration where user_id='"+registration.getUser_id()+"'");
			
			while(rs.next()){
				manager_hierarchy=rs.getString("manager_hierarchy");
			}
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return manager_hierarchy;
	}
	public String getHierarchyArray(registration registration) {
		// TODO Auto-generated method stub
		String manager_hierarchy=null;
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("SELECT manager_hierarchy from registration where user_id='"+registration.getUser_id()+"'");
			while(rs.next()){
				manager_hierarchy=rs.getString("manager_hierarchy");
			}
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return manager_hierarchy;
	}
	public String getUser(registration registration) {
		// TODO Auto-generated method stub
		String user_id=null;
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("SELECT user_id from registration where immediate_manager_id='"+registration.getImmediate_manager_id()+"'");
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
	public String newManagerHierarchy(registration registration) {
		// TODO Auto-generated method stub
		String manager_hierarchy=null;
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("SELECT manager_hierarchy from registration where user_id='"+registration.getUser_id()+"'");
			while(rs.next()){
				manager_hierarchy=rs.getString("manager_hierarchy");
			}
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return manager_hierarchy;
	}
	public String getManagerName(registration registration) {
		// TODO Auto-generated method stub
		String first_name=null;
		String last_name=null;
		String name=null;
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("SELECT first_name,last_name from registration where user_id='"+registration.getUser_id()+"'");
			while(rs.next()){
				first_name=rs.getString("first_name");
				last_name=rs.getString("last_name");
				name=first_name+" "+last_name;
				return name;
			}
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return name;
	}
	public String validate(registration reg)
	{
		// TODO Auto-generated method stub
		
		
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select email_id,user_id from registration");
			while(rs.next()){
				String validateUserId=rs.getString("user_id");
				String validateEmailId=rs.getString("email_id");
				if(validateEmailId.equals(reg.getEmail_id())){
					return "Email-Id is already registered!";
				}
				else if(validateUserId.equals(reg.getUser_id())){
					return "User-Id is already registered!";
				}
			}
			st.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return "success";
	}
	public List<registration> fetchProfile(registration registration) {
		// Course course = new Course();
		List<registration> profile_data = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from registration where user_id='"+registration.getUser_id()+"'");
		
		while (rs.next()) {
			registration registration1 = new registration();
			registration1.setFirst_name(rs.getString("first_name"));
			registration1.setLast_name(rs.getString("last_name"));
			registration1.setAddress(rs.getString("address"));
			registration1.setPhone(rs.getLong("phone_no"));
			registration1.setEmail_id(rs.getString("email_id"));
			registration1.setPassword(rs.getString("password"));
			registration1.setUser_id(rs.getString("user_id"));
			profile_data.add(registration1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return profile_data;

	}
	public List<registration> fetchOrgzProfile(registration registration) {
		// Course course = new Course();
		List<registration> profile_data = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from registration where user_id='"+registration.getUser_id()+"'");
		
		while (rs.next()) {
			registration registration1 = new registration();
			registration1.setFirst_name(rs.getString("first_name"));
			registration1.setLast_name(rs.getString("last_name"));
			registration1.setUser_id(rs.getString("user_id"));
			registration1.setDivision_name(rs.getString("division_name"));
			registration1.setUser_type(rs.getString("user_type"));
			registration1.setImmediate_manager_id(rs.getString("immediate_manager_id"));
			profile_data.add(registration1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return profile_data;

	}
	public List<registration> getManagerList(registration registration) {
		// Course course = new Course();
		List<registration> profile_data = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from registration where user_id!='"+registration.getUser_id()+"' and user_id!='"+registration.getImmediate_manager_id()+"' and user_type='manager' ");
		
		while (rs.next()) {
			registration registration1 = new registration();
			registration1.setFirst_name(rs.getString("first_name"));
			registration1.setLast_name(rs.getString("last_name"));
			registration1.setUser_id(rs.getString("user_id"));
			profile_data.add(registration1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return profile_data;

	}
	public void updateUserProfile(registration registration) {
		// TODO Auto-generated method stub

		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("UPDATE registration set first_name='"+registration.getFirst_name()+"', last_name='"+registration.getLast_name()+"', address='"+registration.getAddress()+"', phone_no='"+registration.getPhone()+"', email_id='"+registration.getEmail_id()+"', password='"+registration.getPassword()+"' where user_id='"+registration.getUser_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void updateManagerHierarchy(registration registration) {
		// TODO Auto-generated method stub

		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("UPDATE registration set immediate_manager_id='"+registration.getImmediate_manager_id()+"',manager_hierarchy='"+registration.getManager_hierarchy()+"', user_type='"+registration.getUser_type()+"', division_name='"+registration.getDivision_name()+"' where user_id='"+registration.getUser_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void updateSubManagerHierarchy(registration registration) {
		// TODO Auto-generated method stub

		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("UPDATE registration set manager_hierarchy='"+registration.getManager_hierarchy()+"' where user_id='"+registration.getUser_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void updateManagerHierarchy1(registration registration) {
		// TODO Auto-generated method stub

		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("UPDATE registration set immediate_manager_id='"+registration.getImmediate_manager_id()+"',manager_hierarchy='"+registration.getManager_hierarchy()+"' where user_id='"+registration.getUser_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
}
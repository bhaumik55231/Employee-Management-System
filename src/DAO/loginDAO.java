package DAO;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.loginModel;


public class loginDAO {
	public String loginUser(loginModel login)
	{
		// TODO Auto-generated method stub
		
		
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select first_name,user_id,password from registration");
			while(rs.next()){
				String validateUser=rs.getString("user_id");
				String validatePassword=rs.getString("password");
				String firstName=rs.getString("first_name");
				if(validateUser.equals(login.getUser_id()) && validatePassword.equals(login.getPassword())){
					return firstName;
				}
			}
			st.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return "Invalid User Credentials";
	}
	public String loginUser1(loginModel login)
	{
		// TODO Auto-generated method stub
		
		
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select last_name,user_id,password from registration");
			while(rs.next()){
				String validateUser=rs.getString("user_id");
				String validatePassword=rs.getString("password");
				String lastName=rs.getString("last_name");
				if(validateUser.equals(login.getUser_id()) && validatePassword.equals(login.getPassword())){
					return lastName;
				}
			}
			st.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return "Invalid User Credentials";
	}
	
	public String getUserType(loginModel login)
	{
		// TODO Auto-generated method stub
		
		
		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select user_type from registration where user_id = '"+login.getUser_id()+"'");
			
			while(rs.next()){
				String userType=rs.getString("user_type");
				return userType;
			}
			st.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return "Invalid User Credentials";
	}
}

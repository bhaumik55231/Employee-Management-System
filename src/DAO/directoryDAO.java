package DAO;

import java.sql.ResultSet;  
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.directory;
import Model.retrieveFile;
public class directoryDAO {
	public void createDirectory(directory directory) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("INSERT INTO directory( directory_access_permissions, directory_name, directory_description, user_id, manager_hierarchy) values('"+directory.getDirectory_access_permissions()+"','"+directory.getDirectory_name()+"','"+directory.getDirectory_description()+"','"+directory.getUser_id()+"','"+directory.getManager_hierarchy()+"') ");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void enterAte(directory directory) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("update directory set ate_id='"+directory.getAte_id()+"' where directory_id='"+directory.getDirectory_id()+"'");
			//st.executeUpdate("update upload_file set ate_id='"+directory.getAte_id()+"' where directory_name='"+directory.getDirectory_name()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void updateATEinUploadFile(String directory_name,String ate_id) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			//System.out.println("in update DAO 0");
			st.executeUpdate("update upload_file set ate_id='"+ate_id+"' where directory_name='"+directory_name+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void updateATEinUploadFile1(String directory_name,String ate_id) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			//System.out.println("in update DAO 1");
			st.executeUpdate("update upload_file set ate_id='"+ate_id+"' where directory_name='"+directory_name+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void updateAccessPermission(directory directory) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("update directory set directory_access_permissions='"+directory.getDirectory_access_permissions()+"' where directory_id='"+directory.getDirectory_id()+"'");
			st.executeUpdate("update upload_file set access_permission='"+directory.getDirectory_access_permissions()+"' where directory_name='"+directory.getDirectory_name()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void updateAccessPermission1(directory directory) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("update directory set new_permission='"+directory.getNew_permission()+"', new_permission_given_by='"+directory.getNew_permission_given_by()+"' where directory_id='"+directory.getDirectory_id()+"'");
			st.executeUpdate("update upload_file set new_permission='"+directory.getNew_permission()+"',new_permission_given_by='"+directory.getNew_permission_given_by()+"' where directory_name='"+directory.getDirectory_name()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	
	public void updateUploadAte(retrieveFile retrieveFile,String getAllAte) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("update upload_file set ate_id='"+getAllAte+"' where ate_id='"+retrieveFile.getAte_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public String validate(directory directory)
	{
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select directory_name from directory where user_id='"+directory.getUser_id()+"'");
			while(rs.next()){
				String validateDirectoryName=rs.getString("directory_name");
				if(validateDirectoryName.equals(directory.getDirectory_name())){
					return "Directory with same name is already present";
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
	public String getHierarchy(directory directory)
	{
		String manager_hierarchy=null;
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select manager_hierarchy from registration where user_id='"+directory.getUser_id()+"'");
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
	public String getDirectoryName(directory directory)
	{
		String directory_name=null;
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select directory_name from directory where directory_id='"+directory.getDirectory_id()+"'");
			while(rs.next()){
				directory_name=rs.getString("directory_name");
				
			}
			st.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return directory_name;
	}
	public String getAllAte(directory directory)
	{
		String ate_id = null;
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select ate_id from directory where directory_id='"+directory.getDirectory_id()+"'");
			while(rs.next()){
				ate_id=rs.getString("ate_id");
				
				}
			st.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return ate_id;
	}
	public String getAte(directory directory)
	{
		String ate_id = null;
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select ate_id from directory where directory_name='"+directory.getDirectory_name()+"'");
			while(rs.next()){
				ate_id=rs.getString("ate_id");
				
				}
			st.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return ate_id;
	}
	public String getUser_id(directory directory)
	{
		String ate_id = null;
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select user_id from directory where directory_name='"+directory.getDirectory_name()+"'");
			while(rs.next()){
				ate_id=rs.getString("user_id");
				
				}
			st.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return ate_id;
	}
	public String getParticularPermission(directory directory)
	{
		String ate_id = null;
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select directory_access_permissions from directory where directory_id='"+directory.getDirectory_id()+"'");
			while(rs.next()){
				ate_id=rs.getString("directory_access_permissions");
				
				}
			st.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return ate_id;
	}
	public String getPermission(directory directory)
	{
		String ate_id = null;
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select directory_access_permissions from directory where directory_id='"+directory.getDirectory_id()+"'");
			while(rs.next()){
				ate_id=rs.getString("directory_access_permissions");
				
				}
			st.close();
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
			}
			return ate_id;
	}
	public List<directory> getDirectoryList(directory directory) {
		List<directory> directoryList = new ArrayList<directory>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from directory where user_id='"+directory.getUser_id()+"'");
		while (rs.next()) {
			directory rf1 = new directory();
			rf1.setDirectory_id(rs.getInt("directory_id"));
			rf1.setDirectory_name(rs.getString("directory_name"));
			rf1.setDirectory_description(rs.getString("directory_description"));
			rf1.setDirectory_access_permissions(rs.getString("directory_access_permissions"));
			rf1.setUser_id(rs.getString("user_id"));
			directoryList.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return directoryList;
	}
	public List<directory> getDirectory(directory directory) {
		List<directory> directoryList = new ArrayList<directory>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from directory where directory_id='"+directory.getDirectory_id()+"'");
		while (rs.next()) {
			directory rf1 = new directory();
			rf1.setDirectory_id(rs.getInt("directory_id"));
			rf1.setDirectory_name(rs.getString("directory_name"));
			rf1.setDirectory_description(rs.getString("directory_description"));
			rf1.setDirectory_access_permissions(rs.getString("directory_access_permissions"));
			rf1.setUser_id(rs.getString("user_id"));
			directoryList.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return directoryList;
	}
	public List<directory> getAllPublicDirectories(directory directory) {
		List<directory> directoryList = new ArrayList<directory>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from directory where user_id='"+directory.getUser_id()+"'");
		while (rs.next()) {
			directory rf1 = new directory();
			rf1.setDirectory_id(rs.getInt("directory_id"));
			rf1.setDirectory_name(rs.getString("directory_name"));
			rf1.setDirectory_description(rs.getString("directory_description"));
			rf1.setDirectory_access_permissions(rs.getString("directory_access_permissions"));
			rf1.setUser_id(rs.getString("user_id"));
			directoryList.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return directoryList;
	}
	public List<directory> particular_directory(directory directory) {
		List<directory> directoryList = new ArrayList<directory>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from directory where directory_id='"+directory.getDirectory_id()+"'");
		while (rs.next()) {
			directory rf1 = new directory();
			rf1.setDirectory_id(rs.getInt("directory_id"));
			rf1.setDirectory_name(rs.getString("directory_name"));
			rf1.setDirectory_description(rs.getString("directory_description"));
			rf1.setDirectory_access_permissions(rs.getString("directory_access_permissions"));
			rf1.setUser_id(rs.getString("user_id"));
			directoryList.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return directoryList;
	}
}

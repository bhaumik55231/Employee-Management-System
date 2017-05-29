package DAO;
import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import Model.directory;
import Model.retrieveFile;
import Model.uploadFile;


public class uploadDAO {
	public void fileupload(uploadFile uf) {
		try{
			String sql="INSERT INTO upload_file(file,file_name, file_type, description, user_id, directory_name,manager_hierarchy, access_permission, uploaded_by) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement st=(PreparedStatement)Dbconn.getConnection().prepareStatement(sql);
			st.setBlob(1, uf.getFile());
			st.setString(2, uf.getFile_name());
			st.setString(3, uf.getFile_type());
			st.setString(4, uf.getDescription());
			st.setString(5, uf.getUser_id());
			st.setString(6, uf.getDirector_name());
			st.setString(7, uf.getManager_hierarchy());
			st.setString(8, uf.getAccess_permission());
			st.setString(9, uf.getUploaded_by());
			int row=st.executeUpdate();
			if(row>0){
			st.close();
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void fileupload1(uploadFile uf, String ate) {
		try{
			String sql="INSERT INTO upload_file(file,file_name, file_type, description, user_id, directory_name,manager_hierarchy, access_permission,ate_id,uploaded_by) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement st=(PreparedStatement)Dbconn.getConnection().prepareStatement(sql);
			st.setBlob(1, uf.getFile());
			st.setString(2, uf.getFile_name());
			st.setString(3, uf.getFile_type());
			st.setString(4, uf.getDescription());
			st.setString(5, uf.getUser_id());
			st.setString(6, uf.getDirector_name());
			st.setString(7, uf.getManager_hierarchy());
			st.setString(8, uf.getAccess_permission());
			st.setString(9, ate);
			st.setString(10, uf.getUploaded_by());
			int row=st.executeUpdate();
			if(row>0){
			st.close();
		}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public List<retrieveFile> getMaterialFileList(retrieveFile rf) {
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from upload_file where user_id ='"+rf.getUser_id()+"'");
		while (rs.next()) {
			retrieveFile rf1 = new retrieveFile();
			rf1.setSrno(rs.getInt("Sr_no"));
			rf1.setFile(rs.getBlob("file"));
			rf1.setFile_name(rs.getString("file_name"));
			rf1.setFile_type(rs.getString("file_type"));
			rf1.setDescription(rs.getString("description"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDirector_name(rs.getString("directory_name"));
			rf1.setAccess_permission(rs.getString("access_permission"));
			rf1.setUploaded_by(rs.getString("uploaded_by"));
			//System.out.println("access_permission" +rs.getString("access_permission"));
			materiallist.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;
	}
	public List<retrieveFile> getMaterialFileList6(retrieveFile rf) {
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from upload_file where user_id ='"+rf.getUser_id()+"'");
		while (rs.next()) {
			retrieveFile rf1 = new retrieveFile();
			rf1.setSrno(rs.getInt("Sr_no"));
			rf1.setFile(rs.getBlob("file"));
			rf1.setFile_name(rs.getString("file_name"));
			rf1.setFile_type(rs.getString("file_type"));
			rf1.setDescription(rs.getString("description"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDirector_name(rs.getString("directory_name"));
			rf1.setAccess_permission(rs.getString("access_permission"));
			rf1.setUploaded_by(rs.getString("uploaded_by"));
			//System.out.println("access_permission" +rs.getString("access_permission"));
			materiallist.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;
	}
	public List<retrieveFile> getMaterialFileList9(retrieveFile rf) {
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from upload_file where user_id ='"+rf.getUser_id()+"'");
		while (rs.next()) {
			retrieveFile rf1 = new retrieveFile();
			rf1.setSrno(rs.getInt("Sr_no"));
			rf1.setFile(rs.getBlob("file"));
			rf1.setFile_name(rs.getString("file_name"));
			rf1.setFile_type(rs.getString("file_type"));
			rf1.setDescription(rs.getString("description"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDirector_name(rs.getString("directory_name"));
			rf1.setAccess_permission(rs.getString("access_permission"));
			rf1.setUploaded_by(rs.getString("uploaded_by"));
			//System.out.println("access_permission" +rs.getString("access_permission"));
			materiallist.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;
	}
	public List<retrieveFile> getMaterialFileList5(retrieveFile rf) {
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from upload_file where user_id ='"+rf.getUser_id()+"'");
		while (rs.next()) {
			retrieveFile rf1 = new retrieveFile();
			rf1.setSrno(rs.getInt("Sr_no"));
			rf1.setFile(rs.getBlob("file"));
			rf1.setFile_name(rs.getString("file_name"));
			rf1.setFile_type(rs.getString("file_type"));
			rf1.setDescription(rs.getString("description"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDirector_name(rs.getString("directory_name"));
			rf1.setAccess_permission(rs.getString("access_permission"));
			rf1.setUploaded_by(rs.getString("uploaded_by"));
			//System.out.println("access_permission" +rs.getString("access_permission"));
			materiallist.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;
	}
	public List<retrieveFile> getAllPublicMedia(retrieveFile rf) {
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from upload_file where access_permission ='public'");
		while (rs.next()) {
			retrieveFile rf1 = new retrieveFile();
			rf1.setSrno(rs.getInt("Sr_no"));
			rf1.setFile(rs.getBlob("file"));
			rf1.setFile_name(rs.getString("file_name"));
			rf1.setFile_type(rs.getString("file_type"));
			rf1.setDescription(rs.getString("description"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDirector_name(rs.getString("directory_name"));
			rf1.setAccess_permission(rs.getString("access_permission"));
			rf1.setUploaded_by(rs.getString("uploaded_by"));
			materiallist.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;
	}
	public List<retrieveFile> allAteFiles(retrieveFile rf) {
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from upload_file where ate_id like '%"+rf.getAte_id()+"%'");
		while (rs.next()) {
			retrieveFile rf1 = new retrieveFile();
			rf1.setSrno(rs.getInt("Sr_no"));
			rf1.setFile(rs.getBlob("file"));
			rf1.setFile_name(rs.getString("file_name"));
			rf1.setFile_type(rs.getString("file_type"));
			rf1.setDescription(rs.getString("description"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDirector_name(rs.getString("directory_name"));
			rf1.setAccess_permission(rs.getString("access_permission"));
			rf1.setUploaded_by(rs.getString("uploaded_by"));
			materiallist.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;
	}
	public List<retrieveFile> getMaterialFileListMedia(retrieveFile rf) {
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from upload_file where user_id ='"+rf.getUser_id()+"' and access_permission!='private'");
		while (rs.next()) {
			retrieveFile rf1 = new retrieveFile();
			rf1.setSrno(rs.getInt("Sr_no"));
			rf1.setFile(rs.getBlob("file"));
			rf1.setFile_name(rs.getString("file_name"));
			rf1.setFile_type(rs.getString("file_type"));
			rf1.setDescription(rs.getString("description"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDirector_name(rs.getString("directory_name"));
			rf1.setAccess_permission(rs.getString("access_permission"));
			rf1.setUploaded_by(rs.getString("uploaded_by"));
			materiallist.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;
	}
	public List<retrieveFile> getMaterialFileListMedia3(retrieveFile rf) {
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from upload_file where (user_id ='"+rf.getUser_id()+"' AND new_permission IS NULL) or (new_permission!='private' and new_permission_given_by='"+rf.getNew_permission_given_by()+"')");
		while (rs.next()) {
			retrieveFile rf1 = new retrieveFile();
			rf1.setSrno(rs.getInt("Sr_no"));
			rf1.setFile(rs.getBlob("file"));
			rf1.setFile_name(rs.getString("file_name"));
			rf1.setFile_type(rs.getString("file_type"));
			rf1.setDescription(rs.getString("description"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDirector_name(rs.getString("directory_name"));
			rf1.setAccess_permission(rs.getString("access_permission"));
			rf1.setUploaded_by(rs.getString("uploaded_by"));
			materiallist.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;
	}
	public List<directory> getMaterialFileListMedia_change(directory directory) {
		List<directory> materiallist = new ArrayList<directory>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from directory where user_id ='"+directory.getUser_id()+"' and (directory_access_permissions='protected' or directory_access_permissions='default' )");
		while (rs.next()) {
			directory rf1 = new directory();
			rf1.setDirectory_id(rs.getInt("directory_id"));
			rf1.setDirectory_name(rs.getString("directory_name"));
			rf1.setDirectory_description(rs.getString("directory_description"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setManager_hierarchy(rs.getString("manager_hierarchy"));
			rf1.setDirectory_access_permissions(rs.getString("directory_access_permissions"));
			materiallist.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;
	}
	public List<retrieveFile> getMaterialFileList1(retrieveFile rf) {
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from upload_file where manager_hierarchy LIKE '%"+rf.getUser_id()+"/%' and (access_permission = 'public' or access_permission = 'private')");
		while (rs.next()) {
			retrieveFile rf1 = new retrieveFile();
			rf1.setSrno(rs.getInt("Sr_no"));
			rf1.setFile(rs.getBlob("file"));
			rf1.setFile_name(rs.getString("file_name"));
			rf1.setFile_type(rs.getString("file_type"));
			rf1.setDescription(rs.getString("description"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDirector_name(rs.getString("directory_name"));
			rf1.setAccess_permission(rs.getString("access_permission"));
			rf1.setUploaded_by(rs.getString("uploaded_by"));
			materiallist.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;
	}
	public List<directory> getMaterialFileList5(directory directory) {
		List<directory> materiallist = new ArrayList<directory>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from directory where user_id ='"+directory.getUser_id()+"' and (directory_access_permissions='protected' or directory_access_permissions='default' )");
		while (rs.next()) {
			directory rf1 = new directory();
			
			rf1.setDirectory_id(rs.getInt("directory_id"));
			rf1.setDirectory_name(rs.getString("directory_name"));
			rf1.setDirectory_description(rs.getString("directory_description"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setManager_hierarchy(rs.getString("manager_hierarchy"));
			rf1.setDirectory_access_permissions(rs.getString("directory_access_permissions"));
			materiallist.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;
	}
	public List<retrieveFile> getMaterialFileList2(retrieveFile rf) {
		List<retrieveFile> materiallist = new ArrayList<retrieveFile>();
		try{
			Statement stmt1=Dbconn.getConnection().createStatement();
			ResultSet rs = stmt1.executeQuery("select * from upload_file where  manager_hierarchy LIKE '%"+rf.getUser_id()+"/%' and user_id != '"+rf.getUser_id()+"'");
			
		while (rs.next()) {
			
			retrieveFile rf1 = new retrieveFile();
			rf1.setSrno(rs.getInt("Sr_no"));
			rf1.setFile(rs.getBlob("file"));
			rf1.setFile_name(rs.getString("file_name"));
			rf1.setFile_type(rs.getString("file_type"));
			rf1.setDescription(rs.getString("description"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDirector_name(rs.getString("directory_name"));
			rf1.setAccess_permission(rs.getString("access_permission"));
			rf1.setUploaded_by(rs.getString("uploaded_by"));
			materiallist.add(rf1);

		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return materiallist;

	}
	
	public static retrieveFile getMaterial(int materialid) throws ClassNotFoundException, SQLException {
		retrieveFile coursematerial = new retrieveFile();
		Statement st=Dbconn.getConnection().createStatement();
		ResultSet rs = st.executeQuery("select * from upload_file where Sr_no ='"+materialid+"'");
		
		rs.next();
		coursematerial.setSrno(rs.getInt("Sr_no"));
		coursematerial.setFile(rs.getBlob("file"));
		coursematerial.setFile_name(rs.getString("file_name"));
		coursematerial.setFile_type(rs.getString("file_type"));
		coursematerial.setDescription(rs.getString("description"));
		coursematerial.setUser_id(rs.getString("user_id"));
		st.close();
		return coursematerial;
	}
	public void updateDirectory(uploadFile uploadFile) {
		// TODO Auto-generated method stub

		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("UPDATE upload_file set manager_hierarchy='"+uploadFile.getManager_hierarchy()+"' where user_id='"+uploadFile.getUser_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public String getAccessPermission(uploadFile uploadFile) {
		// TODO Auto-generated method stub
		String access_permission=null;
		try{
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select directory_access_permissions from directory where directory_name='"+uploadFile.getDirector_name()+"'");
			while(rs.next()){
				access_permission=rs.getString("directory_access_permissions");
			}
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return access_permission;
		
	}
	public String getAteId(retrieveFile retrieveFile) {
		// TODO Auto-generated method stub
		String ate_id=null;
		try{
			//System.out.println("ATE 1");
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select * from upload_file where ate_id like '%"+retrieveFile.getUser_id()+"%'");
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
}

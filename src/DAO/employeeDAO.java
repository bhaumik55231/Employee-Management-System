package DAO;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Model.directory;
import Model.registration;
import Model.salaryModel;


public class employeeDAO {
	public List<registration> inactive_employee(registration registration) {
		// Course course = new Course();
		List<registration> inactive_employee = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from registration where user_type='"+registration.getUser_type()+"' and employee_status='"+registration.getEmployee_status()+"'");
		
		while (rs.next()) {
			registration rf1 = new registration();
			rf1.setFirst_name(rs.getString("first_name"));
			rf1.setLast_name(rs.getString("last_name"));
			rf1.setEmail_id(rs.getString("email_id"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setPassword(rs.getString("password"));
			inactive_employee.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return inactive_employee;

	}

	public List<registration> fetch_inactive_employee(registration registration) {
		// Course course = new Course();
		List<registration> inactive_employee = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from registration where user_type='employee' and employee_status='inactive'");
		
		while (rs.next()) {
			
			registration rf1 = new registration();
			rf1.setFirst_name(rs.getString("first_name"));
			rf1.setLast_name(rs.getString("last_name"));
			rf1.setEmail_id(rs.getString("email_id"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setPassword(rs.getString("password"));
			inactive_employee.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return inactive_employee;

	}

	public List<registration> fetch_manager(registration registration) {
		// Course course = new Course();
		List<registration> inactive_employee = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from registration where user_type='manager' and employee_status='active'");
		
		while (rs.next()) {
			
			registration rf1 = new registration();
			rf1.setFirst_name(rs.getString("first_name"));
			rf1.setLast_name(rs.getString("last_name"));
			rf1.setEmail_id(rs.getString("email_id"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setPassword(rs.getString("password"));
			inactive_employee.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return inactive_employee;

	}
	public List<registration> fetch_manager1(registration registration) {
		// Course course = new Course();
		List<registration> inactive_employee = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from registration where employee_status='active'");
		
		while (rs.next()) {
			
			registration rf1 = new registration();
			rf1.setFirst_name(rs.getString("first_name"));
			rf1.setLast_name(rs.getString("last_name"));
			rf1.setEmail_id(rs.getString("email_id"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setPassword(rs.getString("password"));
			inactive_employee.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return inactive_employee;

	}
	public List<salaryModel> getSalaryStatement(salaryModel salaryModel) {
		// Course course = new Course();
		List<salaryModel> salary_statement = new ArrayList<salaryModel>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from salary where user_id='"+salaryModel.getUser_id()+"' order by salary_id desc");
		
		while (rs.next()) {
			
			salaryModel rf1 = new salaryModel();
			rf1.setDate(rs.getString("date"));
			rf1.setFirst_name(rs.getString("first_name"));
			rf1.setLast_name(rs.getString("last_name"));
			rf1.setEmail_id(rs.getString("email_id"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDivision_name(rs.getString("division_name"));
			rf1.setSalary_per_month(rs.getDouble("salary_per_month"));
			rf1.setBonus(rs.getDouble("bonus"));
			Double total=rs.getDouble("salary_per_month")+rs.getDouble("bonus");
			rf1.setTotal(total);
			salary_statement.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return salary_statement;

	}
	public List<directory> getProtectedDirectories(directory directory, String immediate_manager_id, String immediate_manager_id1, String immediate_manager_id2, String immediate_manager_id3, String immediate_manager_id4, String immediate_manager_id5) {
		// Course course = new Course();
		List<directory> get_all_protected_directory = new ArrayList<directory>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from directory where (user_id = '"+directory.getUser_id()+"' or manager_hierarchy like '%"+directory.getUser_id()+"/%' or user_id = '"+immediate_manager_id+"' or user_id='"+immediate_manager_id1+"' or user_id='"+immediate_manager_id2+"' or user_id='"+immediate_manager_id3+"' or user_id='"+immediate_manager_id4+"' or user_id='"+immediate_manager_id5+"')and directory_access_permissions='protected'");
		
		while (rs.next()) {
			
			directory rf1 = new directory();
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDirectory_name(rs.getString("directory_name"));
			rf1.setDirectory_description(rs.getString("directory_description"));
			rf1.setDirectory_access_permissions(rs.getString("directory_access_permissions"));
			rf1.setDirectory_id(rs.getInt("directory_id"));
			get_all_protected_directory.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return get_all_protected_directory;

	}
	public List<directory> getProtectedDirectories1(directory directory) {
		// Course course = new Course();
		List<directory> get_all_protected_directory = new ArrayList<directory>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from directory where user_id = '"+directory.getUser_id()+"' and directory_access_permissions='protected'");
		
		while (rs.next()) {
			
			directory rf1 = new directory();
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDirectory_name(rs.getString("directory_name"));
			rf1.setDirectory_description(rs.getString("directory_description"));
			rf1.setDirectory_access_permissions(rs.getString("directory_access_permissions"));
			rf1.setDirectory_id(rs.getInt("directory_id"));
			get_all_protected_directory.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return get_all_protected_directory;

	}
	public List<registration> getAllEmployee(registration registration1) {
		// Course course = new Course();
		List<registration> get_all_employee = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from registration where employee_status='active'");
		
		while (rs.next()) {
			registration registration=new registration();
			registration.setFirst_name(rs.getString("first_name"));
			registration.setLast_name(rs.getString("last_name"));
			registration.setUser_id(rs.getString("user_id"));
			
			get_all_employee.add(registration);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return get_all_employee;

	}
	public List<registration> active_employee(registration registration) {
		// Course course = new Course();
		List<registration> active_employee = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from registration where (user_type='employee' or user_type='manager') and employee_status='"+registration.getEmployee_status()+"'");
		
		while (rs.next()) {
			
			registration rf1 = new registration();
			rf1.setFirst_name(rs.getString("first_name"));
			rf1.setLast_name(rs.getString("last_name"));
			rf1.setEmail_id(rs.getString("email_id"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setPassword(rs.getString("password"));
			active_employee.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		
		return active_employee;

	}
	public List<registration> list_immediate_employees(registration registration) {
		// Course course = new Course();
		List<registration> active_employee = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("select * from registration where immediate_manager_id='"+registration.getUser_id()+"' and salary_generated='yes'");
		while (rs.next()) {
			registration rf1 = new registration();
			rf1.setFirst_name(rs.getString("first_name"));
			rf1.setLast_name(rs.getString("last_name"));
			rf1.setEmail_id(rs.getString("email_id"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setPassword(rs.getString("password"));
			active_employee.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return active_employee;
	}
	public List<registration> promote_employee(registration registration) {
		List<registration> active_employee = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("SELECT * FROM registration WHERE user_type != 'admin' and user_type='employee' AND user_id!='1234'");
		
		while (rs.next()) {
			
			registration rf1 = new registration();
			rf1.setFirst_name(rs.getString("first_name"));
			rf1.setLast_name(rs.getString("last_name"));
			rf1.setEmail_id(rs.getString("email_id"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setPassword(rs.getString("password"));
			rf1.setDivision_name(rs.getString("division_name"));
			rf1.setUser_type(rs.getString("user_type"));
			rf1.setImmediate_manager_id(rs.getString("immediate_manager_id"));
			active_employee.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return active_employee;

	}
	public List<registration> promote_employee1(registration registration) {
		List<registration> active_employee = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("SELECT * FROM registration WHERE user_type != 'admin' AND user_id!='1234'");
		while (rs.next()) {
			registration rf1 = new registration();
			rf1.setFirst_name(rs.getString("first_name"));
			rf1.setLast_name(rs.getString("last_name"));
			rf1.setEmail_id(rs.getString("email_id"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setPassword(rs.getString("password"));
			rf1.setDivision_name(rs.getString("division_name"));
			rf1.setUser_type(rs.getString("user_type"));
			rf1.setImmediate_manager_id(rs.getString("immediate_manager_id"));
			active_employee.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return active_employee;
	}
	public List<registration> fetch_all_employee(registration registration) {
		List<registration> active_employee = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("SELECT * FROM registration WHERE employee_status = 'active' and salary_generated='no'");
		while (rs.next()) {
			registration rf1 = new registration();
			rf1.setFirst_name(rs.getString("first_name"));
			rf1.setLast_name(rs.getString("last_name"));
			rf1.setEmail_id(rs.getString("email_id"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDivision_name(rs.getString("division_name"));
			rf1.setUser_type(rs.getString("user_type"));
			rf1.setImmediate_manager_id(rs.getString("immediate_manager_id"));
			active_employee.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return active_employee;
	}
	public List<salaryModel> fetch_all_employee1(String currentTime1) {
		List<salaryModel> active_employee = new ArrayList<salaryModel>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("SELECT * FROM salary where date like '%-"+currentTime1+"-%'");
		while (rs.next()) {
			salaryModel salaryModel=new salaryModel();
			salaryModel.setFirst_name(rs.getString("first_name"));
			salaryModel.setLast_name(rs.getString("last_name"));
			salaryModel.setDate(rs.getString("date"));
			salaryModel.setDivision_name(rs.getString("division_name"));
			salaryModel.setUser_type(rs.getString("user_type"));
			salaryModel.setSalary_per_month(rs.getFloat("salary_per_month"));
			salaryModel.setUser_id(rs.getString("user_id"));
			salaryModel.setSalary_id(rs.getInt("salary_id"));
			active_employee.add(salaryModel);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return active_employee;
	}
	public List<salaryModel> fetch_all_employee2(salaryModel salaryModel1) {
		List<salaryModel> active_employee = new ArrayList<salaryModel>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("SELECT * FROM salary where salary_id='"+salaryModel1.getSalary_id()+"'");
		while (rs.next()) {
			salaryModel salaryModel=new salaryModel();
			salaryModel.setFirst_name(rs.getString("first_name"));
			salaryModel.setLast_name(rs.getString("last_name"));
			salaryModel.setDate(rs.getString("date"));
			salaryModel.setDivision_name(rs.getString("division_name"));
			salaryModel.setUser_type(rs.getString("user_type"));
			salaryModel.setSalary_per_month(rs.getFloat("salary_per_month"));
			salaryModel.setUser_id(rs.getString("user_id"));
			salaryModel.setSalary_id(rs.getInt("salary_id"));
			active_employee.add(salaryModel);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return active_employee;
	}
	public List<registration> fetch_all_employee_for_payroll(registration registration) {
		List<registration> active_employee = new ArrayList<registration>();
		try{
		Statement stmt1 = Dbconn.getConnection().createStatement();
		ResultSet rs = stmt1.executeQuery("SELECT * FROM registration WHERE user_id = '"+registration.getUser_id()+"'");
		while (rs.next()) {
			registration rf1 = new registration();
			rf1.setFirst_name(rs.getString("first_name"));
			rf1.setLast_name(rs.getString("last_name"));
			rf1.setEmail_id(rs.getString("email_id"));
			rf1.setUser_id(rs.getString("user_id"));
			rf1.setDivision_name(rs.getString("division_name"));
			rf1.setUser_type(rs.getString("user_type"));
			rf1.setImmediate_manager_id(rs.getString("immediate_manager_id"));
			active_employee.add(rf1);
		}
		stmt1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return active_employee;
	}
	public void assignManager(registration registration) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("UPDATE registration set immediate_manager_id='"+registration.getImmediate_manager_id()+"',manager_hierarchy='"+registration.getManager_hierarchy()+"', employee_status='active' where user_id='"+registration.getUser_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void assignManager1(registration registration) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("UPDATE registration set immediate_manager_id='"+registration.getImmediate_manager_id()+"',manager_hierarchy='"+registration.getManager_hierarchy()+"', employee_status='active' where user_id='"+registration.getUser_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public static void updateSalary(salaryModel salaryModel) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("UPDATE salary set salary_per_month='"+salaryModel.getSalary_per_month()+"' where salary_id='"+salaryModel.getSalary_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void makeManager(registration registration) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("UPDATE registration set user_type='manager' where user_id='"+registration.getUser_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void allocateBonus(salaryModel salaryModel, String currentTime1) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("UPDATE salary set bonus='"+salaryModel.getBonus()+"' where user_id='"+salaryModel.getUser_id()+"' and date like '%-"+currentTime1+"-%'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	
	public String findHierarchy(registration registration) {
		// TODO Auto-generated method stub
		String hierarchy=null;
		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select manager_hierarchy from registration where user_id='"+registration.getUser_id()+"' ");
			while(rs.next()){
				hierarchy=rs.getString("manager_hierarchy");
				return hierarchy;
			}
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return null;
	}
	public String getImmediateManager(registration registration) {
		// TODO Auto-generated method stub
		String immediate_manager_id=null;
		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select immediate_manager_id from registration where user_id='"+registration.getUser_id()+"' ");
			while(rs.next()){
				immediate_manager_id=rs.getString("immediate_manager_id");
				return immediate_manager_id;
			}
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return null;
	}
	public String getImmediateManager1(registration registration) {
		// TODO Auto-generated method stub
		String immediate_manager_id=null;
		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select immediate_manager_id from registration where user_id='"+registration.getUser_id()+"' ");
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
	public String getUser_type(registration registration) {
		// TODO Auto-generated method stub
		String immediate_manager_id=null;
		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select user_type from registration where user_id='"+registration.getUser_id()+"' ");
			while(rs.next()){
				immediate_manager_id=rs.getString("user_type");
				
			}
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return immediate_manager_id;
	}
	public String fetchManagerHierarchy(registration registration) {
		// TODO Auto-generated method stub
		String hierarchy=null;
		try{
			
			Statement st=Dbconn.getConnection().createStatement();
			ResultSet rs=st.executeQuery("select manager_hierarchy from registration where user_id='"+registration.getUser_id()+"' ");
			while(rs.next()){
				hierarchy=rs.getString("manager_hierarchy");
				return hierarchy;
			}
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
		return null;
	}
	public void setEmployeeSalary(salaryModel salaryModel) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("INSERT INTO salary( date, first_name, last_name, user_id, email_id, user_type, division_name, salary_per_month) values('"+salaryModel.getDate()+"','"+salaryModel.getFirst_name()+"','"+salaryModel.getLast_name()+"','"+salaryModel.getUser_id()+"','"+salaryModel.getEmail_id()+"','"+salaryModel.getUser_type()+"','"+salaryModel.getDivision_name()+"','"+salaryModel.getSalary_per_month()+"') ");
			st.executeUpdate("Update registration set salary_generated='yes' where user_id='"+salaryModel.getUser_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
	public void updateUserType(registration registration) {
		// TODO Auto-generated method stub
		try{
			Statement st=Dbconn.getConnection().createStatement();
			st.executeUpdate("Update registration set user_type='"+registration.getUser_type()+"' where user_id='"+registration.getUser_id()+"'");
			st.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}	
	}
}

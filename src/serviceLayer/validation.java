package serviceLayer;

import Model.directory;
import Model.loginModel; 
import Model.registration;
import Model.uploadFile;

public class validation {
	public String mandatory(registration reg){
		if(reg.getFirst_name().isEmpty() && reg.getLast_name().isEmpty() && reg.getEmail_id().isEmpty() && reg.getUser_id().isEmpty() && reg.getPassword().isEmpty() && reg.getConfirmpassword().isEmpty()){
			return "All fields are Mandatory";
		}
		else if(reg.getFirst_name().isEmpty() || reg.getFirst_name().trim().length() <= 0){
			return "First name is mandatory!";
		}
		else if(reg.getLast_name().isEmpty() || reg.getLast_name().trim().length() <= 0){
			return "Last name is mandatory!";
		}
		else if(reg.getAddress().isEmpty() || reg.getAddress().trim().length() <= 0){
			return "Address is mandatory!";
		}
		else if(reg.getPhone().SIZE == 10){
			return "Phone No. should be 10 digits long!";
		}
		else if(reg.getEmail_id().isEmpty() || reg.getEmail_id().trim().length() <= 0){
			return "Email Id is mandatory!";
		}
		else if(reg.getUser_id().isEmpty() || reg.getUser_id().trim().length() <= 0){
			return "User Id is mandatory!";
		}
		else if(reg.getPassword().isEmpty() || reg.getPassword().trim().length() <= 0){
			return "Password is mandatory!";
		}
		else if(reg.getConfirmpassword().isEmpty() || reg.getConfirmpassword().trim().length() <= 0){
			return "Confirm Password is mandatory!";
		}
		return "success";
	}
	public String mandatoryDirectory(directory directory){
		if(directory.getDirectory_name().isEmpty() || directory.getDirectory_name().trim().length() <= 0){
			return "Directory Name is Mandatory!";
		}else if(directory.getDirectory_description().isEmpty() || directory.getDirectory_description().trim().length() <= 0){
			return "Directory Description is Mandatory!";
		}
		
		return "success";
		
	}
	
	public String mandatory_login(loginModel login){
		if(login.getUser_id().isEmpty() && login.getPassword().isEmpty()){
			return "All fields are Mandatory";
		}
		else if(login.getUser_id().isEmpty() || login.getUser_id().trim().length() <= 0){
			return "User Id is mandatory!";
		}
		else if(login.getPassword().isEmpty() || login.getPassword().trim().length() <= 0){
			return "Password is mandatory!";
		}
		return "success";
		
		
	}
	public String parameters_login(loginModel login){
		 if(login.getUser_id().matches("[0-9]+")){
				if(login.getPassword().matches("[a-zA-Z]+")){
					return "success";
				}
				
				else{
					return "Password Should be alphabetic only!";
				}
		 		}
		 		else{
		 			return "User Id Should be numeric only!";
		 		}
		
	}
	public String fileupload(uploadFile uf){
		if(uf.getFile_name().isEmpty()){
			return "Please Upload File";
		}
		else if(uf.getDescription().isEmpty() || uf.getDescription().trim().length() <=0){
			return "Please enter file Description!";
		}
		return "success";
		
	}
	public String parameters(registration reg){
		 if(reg.getUser_id().matches("[0-9]+")){
				if(reg.getPassword().matches("[a-zA-Z]+")){
				if(reg.getPassword().equals(reg.getConfirmpassword())){
					return "success";
				}
				else{
					return "Password and Confirm - Password Should be Same!";
				}
				}
				else{
					return "Password Should be alphabetic only!";
				}
				}
		 		else{
		 			return "User Id Should be numeric only!";
		 		}
		
	}
}

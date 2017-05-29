package serviceLayer;
import Model.registration;

public class updateValidation {
	public String mandatory(registration reg){
		if(reg.getFirst_name().isEmpty() && reg.getAddress().isEmpty() && reg.getLast_name().isEmpty() && reg.getEmail_id().isEmpty() && reg.getUser_id().isEmpty() && reg.getPassword().isEmpty() && reg.getConfirmpassword().isEmpty()){
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
		else if(reg.getPassword().isEmpty() || reg.getPassword().trim().length() <= 0){
			return "Password is mandatory!";
		}
		else if(reg.getConfirmpassword().isEmpty() || reg.getConfirmpassword().trim().length() <= 0){
			return "Confirm Password is mandatory!";
		}
		return "success";
		
		
	}
	
	
	public String parameters(registration reg){
		 
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
}

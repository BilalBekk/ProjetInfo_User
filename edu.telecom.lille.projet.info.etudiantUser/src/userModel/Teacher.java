package userModel;


/**
 * Classe Teacher qui herite de User
 * @author Bekkouch Bilal et Cherifi Nassim 
 * @version 06/2016 
 * 
 */


public class Teacher extends User {
	
	public Teacher(String UserLogin, String UserPswd, String Firstname, String Surname, int id) {
		super(UserLogin, UserPswd, Firstname, Surname, id, "Teacher");
		
	}

}

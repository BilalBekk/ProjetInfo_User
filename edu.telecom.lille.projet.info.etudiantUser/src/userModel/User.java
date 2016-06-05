/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package userModel;



/**
 * Cette classe User contient l'ensemble des methodes necessaires.
 * @author Bekkouch Bilal et Cherifi Nassim 
 * @version 06/2016 
 * 
 */
public class User {

	public String UserLogin = "";
	private String UserPswd = "";
	public String Firstname = "";
	public String Surname = "";
	public int id;
	// !!!! Cas particulier : afin de differencier les differents utilisateurs, nous avons mis en place un caractere type. 
	public String type;

	/**
	 * The constructor. Il contient un champ type qui permet de differencier les differentes categories d'utilisateurs
	 */
	public User(String UserLogin, String UserPswd, String Firstname, String Surname,int id, String type) {    
	    this.UserLogin=UserLogin;
	    this.UserPswd=UserPswd;
	    this.Firstname=Firstname;
	    this.Surname=Surname;
	    this.id=id;
	    this.type = type;
	}


	/**
    Recuperer le login de l'utilisateur. 
    @return le login en question.
*/
	public String getUserLogin() {
		return this.UserLogin;
	}

	/**
    Recuperer le type d'utilisateur (Admin, Teacher, Student...)
    @return le type en question.
*/
	public String getType() {
		return this.type;
	} 
	
	/**
    Recuperer l'id de l'utilisateur. 
    @return l'identifiant.
*/
	public int getId() {
		return this.id;
	} 
	
	/**
    Donner un login a un utilisateur. 
    @param newUserLogin : login a remplacer.
*/
	public void setUserLogin(String newUserLogin) {
		this.UserLogin = newUserLogin;
	}

	
	/**
    Recuperer le password de l'utilisateur. 
    @return le mot de passe.
*/
	public String getUserPswd() {
		return this.UserPswd;
	}

	
	/**
    Attribuer un password a un utilisateur. 
    @param newUserPswd : mot de passe a inserer.
*/
	public void setUserPswd(String newUserPswd) {
		this.UserPswd = newUserPswd;
	}

	
	/**
    Recuperer le firstname de l'utilisateur. 
    @return le Firstname.
*/
	public String getFirstname() {
		return this.Firstname;
	}

	/**
    Donner un Firstname a un utilisateur. 
    @param newFirstname : nouveau firstname de l'utilisateur .
*/
	public void setFirstname(String newFirstname) {
		this.Firstname = newFirstname;
	}

	
	/**
    Recuperer le surname de l'utilisateur. 
    @return le surname.
*/
	public String getSurname() {
		return this.Surname;
	}


	/**
    Donner un Surname a un utilisateur. 
    @param newSurname : nouveau surname de l'utilisateur .
*/
	public void setSurname(String newSurname) {
		this.Surname = newSurname;
	}
	
}

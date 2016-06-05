package userModel;


/**
 * Cette classe Student qui herite de User, contient l'ensemble des methodes necessaire a la gestion des etudiants.
 * 
 * @author Bekkouch Bilal et Cherifi Nassim 
 * @version 06/2016 
 * 
 */

public class Student extends User {

	protected int groupId;
	//Deux constructeurs sont visibles et caractérsent 2 cas : le premier correspond au fait que l'etudiant n'appartienne a aucun groupe. Dans le second cas, il appartient a un groupe
	
	public Student(String UserLogin, String UserPswd, String Firstname, String Surname, int id) {
		super(UserLogin, UserPswd, Firstname, Surname, id,"Student");
		this.groupId=-1;
	}
	
	// Si le student appartient à un groupe, on lui rajoute un paramètre groupId qui reprend l'Id du groupe
	public Student(String UserLogin, String UserPswd, String Firstname, String Surname, int id, int groupId) {
		super(UserLogin, UserPswd, Firstname, Surname, id,"Student");
		this.groupId=groupId;
	}
	
	/**
    Recuperer l'identifiant du groupe
    @return le groupID.
*/
	public int getGroup(){
		return this.groupId;
	}
	
	
	/**
    affilier un groupeID a un etudiant
    @param le groupID.
*/
	public void setGroup(int IDgroup){
		this.groupId=IDgroup;
	}
}

package userController;

import userModel.Student;
import userModel.UserDB;
//import userModel.Group;
//import userModel.addAdmin;
/**
 * Cette classe est le controleur d'utilisateurs
 * Elle contient un attribut correspondant a la base de donnees utilisateurs.
 * Elle contient toutes les fonctions de l'interface IUserController.
 * 
 * @author Bekkouch Bilal et Cherifi Nassim 
 * @version 06/2016 
 * 
 */



public class UserController implements IUserController
{

	/**
	 * Contient une instance de base de donnees d'utilisateurs
	 * 
	 */
	private UserDB userDB=null;


	/**
	 * Constructeur de controleur d'utilisateurs creeant la base de donnees d'utilisateurs
	 * 
	 * @param userfile
	 * 		Fichier XML contenant la base de donnees d'utilisateurs
	 */
	public UserController(String userfile){
		UserDB userDB=new UserDB(userfile);
		this.setUserDB(userDB);
	}

	/**
	 * Methode retournant le UserName d'un utilisateur a l'aide de son login
	 * 
	 * @param userLogin
	 * @return 
	 */
	public String getUserName(String userLogin) {
		String resultat = null;
		for(int i=0;i<userDB.getUserList().size();i++)
			if (userDB.getUserList().get(i).getUserLogin().equals(userLogin))
			{
				resultat =userDB.getUserList().get(i).getFirstname()+ userDB.getUserList().get(i).getSurname();

			}
		return resultat;

	}

	/**
	 * Methode retournant le type d'un utilisateur a l'aide de son login et de son mot de passe
	 * 
	 * @param userLogin
	 * @param userPwd
	 * @return 
	 */

	public String getUserClass(String userLogin, String userPwd) {
		String resultat = null;
		for(int i=0;i<userDB.getUserList().size();i++)
			if (userDB.getUserList().get(i).getUserLogin().equals(userLogin)){
				resultat=userDB.getUserList().get(i).getType();
			}
		return resultat;

	}
	/**
	 * Methode retournant le groupe d'un étudiant
	 * 
	 * @param studentLogin
	 * @return groupId
	 */
	public int getStudentGroup(String studentLogin) {
		for(int indice2=0;indice2<userDB.getUserList().size();indice2++){
			if(userDB.getUserList().get(indice2).getType().equals("Student") && ((Student) userDB.getUserList().get(indice2)).getUserLogin()==studentLogin)
				return (int)((Student) userDB.getUserList().get(indice2)).getGroup();
		}
		return 0;

	}

	/**
	 * Methode permettant d'ajouter un teacher a la base de donnees utilisateur et de la sauvegarder
	 * 
	 * @param adminLogin newteacherLogin teacher ID firstname surname pwd
	 * @return true si le teacher a bien ete sauvergarde, faux sinon 
	 */
	public boolean addTeacher(String adminLogin, String newteacherLogin, int teacherID, String firstname, String surname, String pwd) {
		if(userDB.addTeacher(adminLogin, newteacherLogin, teacherID, firstname, surname, pwd))
			return this.saveDB();
		return false;
	}

	/**
	 * Methode permettant d'ajouter un student a la base de donnees utilisateur et de la sauvegarder
	 * 
	 * @param adminLogin newstudentLogin studentID firstname surname pwd
	 * @return true si le student a bien ete sauvergarde, faux sinon 
	 */
	public boolean addStudent(String adminLogin, String newStudentLogin, int studentID, String firstname,
			String surname, String pwd) {
		if(userDB.addStudent(adminLogin, newStudentLogin, studentID, firstname, surname, pwd))
			return this.saveDB();
		return false;
	}

	/**
	 * Methode permettant d'ajouter un admin a la base de donnees utilisateur et de la sauvegarder
	 * 
	 * @param adminLogin newAdminlogin adminID firstname surname pwd
	 * @return true si l'admin a bien ete sauvergarde, faux sinon 
	 */
	public boolean addAdmin(String adminLogin, String newAdminlogin, int adminID, String firstname, String surname,String pwd) {
		if(userDB.addAdmin(adminLogin, newAdminlogin, adminID, firstname, surname, pwd))
			return this.saveDB();
		return false;
	}


	/**
	 * Methode permettant d'ajouter un group a la base de donnees group et de la sauvegarder
	 * 
	 * @param adminLogin groupID
	 * @return true si le group a bien ete sauvergarde, faux sinon 
	 */
	public boolean addGroup(String adminLogin, int groupId) {
		if( userDB.addGroup(adminLogin, groupId))
			return saveDB();
		return false;
	}


	/**
	 * Methode permettant de supprimer un group de la base de donnees group et de la sauvegarder
	 * 
	 * @param adminLogin groupID
	 * @return true si le groupe a bien ete supprime, faux sinon 
	 */
	public boolean removeGroup(String adminLogin, int groupId) {
		if(userDB.removeGroup(adminLogin, groupId))
			return saveDB();
		return false;
	}


	/**
	 * Methode permettant de supprimer un utilisateur de la base de donnees utilisateur et de la sauvegarder
	 * 
	 * @param adminLogin userLogin
	 * @return true si l'utilisateur a bien ete supprime, faux sinon 
	 */
	public boolean removeUser(String adminLogin, String userLogin) {
		if(userDB.removeUser(adminLogin, userLogin))
			return saveDB();
		return false;
	}


	/**
	 * Methode permettant d'associer un etudiant a un groupe en lui attribuant un groupId différent de -1.
	 * 
	 * @param adminLogin studentLogin groupId
	 * @return true si l'association a eu lieu, faux sinon  
	 */
	public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		if(userDB.associateStudToGroup(adminLogin, studentLogin, groupId))
			return saveDB();
		return false;
	}


	/**
    Retourner les user sous forme d'un string
    @return les users en string
	 */
	public String[] usersToString() {
		int taille;
		taille=userDB.getUserList().size();
		String[] string=new String[taille];
		int j=0;
		for(int i=0;i<taille;i++){
			string[j]=userDB.getUserList().get(i).getFirstname() + userDB.getUserList().get(i).getSurname() + string;
			j++;
		}
		return string;
	}


	public String[] usersLoginToString() {
		int taille;
		taille=userDB.getUserGroup().size();
		String[] string=new String[taille];
		int k=0;
		for(int i=0;i<taille;i++){
			string[k]=userDB.getUserList().get(i).getUserLogin() + string;
			k++;
		}
		return string;			
	}


	public String[] studentsLoginToString() {
		int taille;
		taille=userDB.getUserList().size();
		String[] string=new String[taille];
		int k=0;
		for(int i=0;i<taille;i++){
			if(userDB.getUserList().get(i).getType().equals("Student")){
				string[k]=userDB.getUserList().get(i).getUserLogin() + string;
				k++;
			}
		}
		return string;
	}

	/**
    Retourner les groupes sous forme d'un string
    @return les groupes en string
	 */
	public String groupsIdToString() {
		int taille;
		taille=userDB.getUserGroup().size();
		String resultat=null;
		int j=0;
		for(int i=0;i<taille;i++){
			int k = (int)((Student) userDB.getUserList().get(j)).getGroup();
			resultat = Integer.toString(k)+ resultat;
			j++;
		}
		return resultat;

	}


	public String[] groupsToString() {
		int taille;
		taille=userDB.getUserGroup().size();
		String[] liste=new String[taille];
		int j=0;
		for(int i=0;i<this.userDB.getUserGroup().size();i++){
			liste[j]=Integer.toString(i);
			j++;
		}
		return liste;		
	}


	/**
	 * Methode permettant de charger la base de donnees 
	 * 
	 * @return la base de donnes chargée  
	 */
	public boolean loadDB() {

		return userDB.loadDB();
	}

	/**
	 * Methode permettant de sauvegarder la base de donnees 
	 * 
	 * @return la base de donnes sauvegardee  
	 */
	public boolean saveDB() {
		return userDB.save();
	}

	/**
	 * Getter de userDB
	 * 
	 * @return userDB 
	 */
	public UserDB getUserDB() {
		return userDB;
	}

	/**
	 * Set de userDB
	 * 
	 * @return userDB 
	 */
	public void setUserDB(UserDB userDB) {
		this.userDB = userDB;
	}



}


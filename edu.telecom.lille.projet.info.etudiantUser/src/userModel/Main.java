package userModel;

import userController.UserController;
/**
 * Cette classe permet de tester les fonctions du controleur d'utilisateurs.
 * Elle cree une base de donnes de x utilisateurs et les sauvegarde dans le fichier "usersDB.xml". 
 * 
 * @author Bekkouch Bilal et Cherifi Nassim 
 * @version 06/2016 
 * 
 */



public class Main {
	/**
	 * Fonction principale 
	 * 
	 * @param args
	 * 			Les arguments donnees en entree du programme.
	 * 
	 */
	public static void main(String[] args) {
		final String file="usersDB.xml"; //Fichier XML que nous avons créé
		UserController UC=new UserController(file);
		/*UC.addAdmin("su","KF",0001,"Kevin", "Flynn",  "@tron");
		UC.addAdmin("su","KR",0002,"Keanu", "Reeves",  "redpill");
		UC.addTeacher("su","GS",1001,"Grand", "Schtroumpf",  "salsepareille");
		UC.addTeacher("su","MF",1002,"Morgan", "Freeman",  "iknowall");
		UC.addStudent("su","BS",2001,"Buffy", "Summers",  "stake");*/
		//UC.addStudent("su","NL",2002,"Nicolas", "Lepetit",  "prout");
		//UC.removeUser("su", "BS");
		//UC.removeUser("su", "NL");
		//UC.addGroup("su", 3);
		//UC.associateStudToGroup("su","BS", 2);
		UC.removeGroup("su", 3);
		UC.saveDB();
	}
	
	
}

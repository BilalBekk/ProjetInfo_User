package userModel;
import java.util.ArrayList;

/**
 * Cette classe Administrator (qui herite de User) contient l'ensemble des fonctions utilisable par un administrateur.
 * Elle contient une base de 7 methodes dont les fonctions d'ajout (add), de suppression (remove) et les fonctions relatives aux groupes 
 * 
 * @author Bekkouch Bilal et Cherifi Nassim 
 * @version 06/2016 
 * 
 */


public class Administrator extends User {

	public Administrator(String UserLogin, String UserPswd, String Firstname, String Surname, int id) {
		super(UserLogin, UserPswd, Firstname, Surname, id,"Administrator");
	}
	
	/**
    Ajouter un administrateur.
    @param UserLogin : Login de l'admin a ajouter.
    @param UserPswd : Password de l'admin a ajouter.
    @param Firstname : Firstname de l'admin a ajouter.
    @param Surname : Surname de l'admin a ajouter.
    @param id : id de l'admin a ajouter.
 
    @return le nouvel administrateur cree.
*/
	public Administrator addAdmin(String UserLogin, String UserPswd, String Firstname, String Surname, int id){
		return new Administrator(UserLogin, UserPswd, Firstname, Surname, id);
	}
	
	/**
    Ajouter un professeur.
    @param UserLogin : Login du teacher a ajouter.
    @param UserPswd : Password du teacher a ajouter.
    @param Firstname : Firstname du teacher a ajouter.
    @param Surname : Surname du teacher a ajouter.
    @param id : id du teacher a ajouter.
 
    @return le nouveau teacher cree.
*/
	public Teacher addTeacher(String UserLogin, String UserPswd, String Firstname, String Surname, int id){
		return new Teacher(UserLogin, UserPswd, Firstname, Surname, id);
	}
	
	/**
    Ajouter un student.
    @param UserLogin : Login de l'etudiant a ajouter.
    @param UserPswd : Password de l'etudiant a ajouter.
    @param Firstname : Firstname de l'etudiant a ajouter.
    @param Surname : Surname de l'etudiant a ajouter.
    @param id : id de l'etudiant a ajouter.
 
    @return le nouvel etudiant cree.
*/
	public Student addStudent(String UserLogin, String UserPswd, String Firstname, String Surname, int id){
		return new Student(UserLogin, UserPswd, Firstname, Surname, id);
	}
	
	
	/**
    Supprimer un utilisateur.
    @param listUsers : Liste reprenant l'ensemble des utilisateurs.
    @param userLogin : Login de l'utilisateur a supprimer.
 
    @return true si la suppression a ete realisee. False sinon
    Cette fonction est caracterisee par une boucle dans laquelle nous allons comparer les login existants a celui indique. S'ils sont pareils, nous le supprimerons
*/
	public boolean remove(ArrayList<User> listUsers,String userLogin){
		for(int indice=0;indice<listUsers.size();indice++){
			if(listUsers.get(indice).getUserLogin().equals(userLogin)){
				listUsers.remove(indice);
				return true;
			}	
		}
		return false;
	}
	
	
	/**
    Ajouter un groupe.
    @param listGroup.
    @param idGroup : identifiant du groupe a creer.
 
    @return true si la creation est effective. False sinon
    
*/
	public boolean addGroup(int idGroup, ArrayList<Group> listGroup){
		listGroup.add(new Group(idGroup));
		return true;
	}
	
	
	/**
    Associer un etudiant a un groupe.
    @param studentLogin : login de l'etudiant a ajouter au groupe.
    @param idGroup : identifiant du groupe ou l'etudiant sera inserer.
    @param listUser : Liste des utilisateurs
    @param listGroup : liste des groupes presents
    @return true si le student est associe. False sinon
    
    Nous comparons tout d'abord les idGroup dans la liste des groupes. S'il y a similitude, nous verifions que le student existe et nous l'ajoutons au groupe
    
*/
	public boolean associateStudToGroup(String studentLogin, int idGroup, ArrayList<User> listUser,ArrayList<Group> listGroup) {
		for(int indice1=0;indice1<listGroup.size();indice1++){
			if(listGroup.get(indice1).GetGroupId()==idGroup){
				for(int indice2=0;indice2<listUser.size();indice2++){
					if(listUser.get(indice2).getType().equals("Student") && listUser.get(indice2).getUserLogin().equals(studentLogin)){
						((Student) listUser.get(indice2)).setGroup(idGroup);
						listGroup.get(indice1).addStudent(studentLogin);;
						return true;
					}
				}
			}
		}
		return false;
	}
	
	
	
	/**
    Supprimer un groupe.
    @param idGroup : identifiant du groupe ou l'etudiant sera inserer.
    @param listUser : Liste des utilisateurs
    @param listGroup : liste des groupes presents
    @return true si le student est associe. False sinon
    
    Comme pour associateStudToGroup, deux boucles for apparaissent. L'objectif est double ici : supprimer le groupe et enlever l'identifiant du groupe à l'etudiant (en le mettant a -1)
*/
	public boolean removeGroup(int idGroup,ArrayList<User> listUser,ArrayList<Group> listGroup){
		for(int indice1=0;indice1<listGroup.size();indice1++){
			if(listGroup.get(indice1).GetGroupId()==idGroup){
				for(int indice2=0;indice2<listUser.size();indice2++){
						if(listUser.get(indice2).getType().equals("Student") && ((Student) listUser.get(indice2)).getGroup()==idGroup)
							//l'etudiant aura -1 comme IdGroup, autrement dit, il sera sans groupe
							((Student) listUser.get(indice2)).setGroup(-1);
				}
				listGroup.remove(indice1);
				return true;
			}
		}
		return false;
	}
}

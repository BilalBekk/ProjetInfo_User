package userModel;
import java.util.ArrayList;



/**
 * Cette classe Group contient l'ensemble des methodes specifiques a un groupe.
 * 
 * @author Bekkouch Bilal et Cherifi Nassim 
 * @version 06/2016 
 * 
 */



public class Group {
	private int group_ID;
	private ArrayList<String> listStudents; // Pour ne pas faire de conversion à chaque fois, nous avons cree une liste specifique aux etudiants

	public Group(int ID){
		this.group_ID=ID; 
		this.listStudents= new ArrayList<String>();
	}
	
	/**
    Recuperer l'id du groupe. 
    @return le groupe ID.
*/
	public int GetGroupId() {
		return this.group_ID;
	}

	
	/**
    Ajouter un etudiant a la liste
    @param student : Student a ajouter.
    La liste sera alors modifiee
*/
	public void addStudent(String student){
		this.listStudents.add(student);
	}

	
	/**
    Supprimer un etudiant a la liste
    @param student : Student a supprimer.
    La liste sera alors modifiee 
    Dans cette boucle, nous comparons si le login de l'etudiant a modifier est similaire a l'un des logins de la liste. Si oui, nous le supprimerons
*/
	public void removeStudent(Student student){
		for(int indice=0;indice<this.listStudents.size();indice++)
		{
			if(student.getUserLogin().equals(this.listStudents.get(indice)))
				this.listStudents.remove(indice);
		}
	}


}

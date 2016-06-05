package userModel;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
//import javax.xml.bind.DataBindingException;

import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;


/**
 * 
 * Cette classe gere la base de donnees d'utilisateurs. Elle doit permettre de sauvegarder et charger les utilisateurs et les groupes a partir d'un fichier XML. 
 * La structure du fichier XML devra etre la meme que celle du fichier userDB.xml.
 * @see <a href="../../userDB.xml">userDB.xml</a> 
 * 
 * @author Bekkouch Bilal et Cherifi Nassim
 * @version 06/2016 
 * 
 */


public class UserDB {
	/**
	 * 
	 * Le fichier contenant la base de donnees.
	 * 
	 */
	private Element racine;
	private org.jdom2.Document document;
	private String file;
	//Nous creons un super admin capable d'ajouter, supprimer des user et des groupes
	private Administrator superAdmin = new Administrator("su","su","su","su",0);
	// Apres un echec au niveau des Hashmap, nous avons choisi d'implementer deux listes : une de users et une autre de groupes
	protected ArrayList<User> listUsers;
	protected ArrayList<Group> listGroups;
	/**
	 * 
	 * Constructeur de UserDB. 
	 * 
	 * 
	 * @param file
	 * Le nom du fichier qui contient la base de donnees.
	 */
	public UserDB(String file){
		super();
		this.listUsers = new ArrayList<User>();
		this.listGroups = new ArrayList<Group>();
		this.setFile(file);
		this.loadDB();
		
	}
	/**
	 * Getter de listUsers
	 * 
	 * @return La liste d'utilisateurs
	 * 	
	 */
	public ArrayList <User> getUserList()
	{
		return listUsers;
	}
	/**
	 * Getter de listGroups
	 * 
	 * @return La liste de groupe
	 * 	
	 */

	public ArrayList <Group> getUserGroup()
	{
		return listGroups;
	}
	/**
	 * Getter de file
	 * 
	 * @return Le nom du fichier qui contient la base de donnees.
	 * 	
	 */
	
	public String getFile() {
		return file;
	}
	
	/**
	 * Setter de file
	 * 
	 * @param file
	 * 	Le nom du fichier qui contient la base de donnees.
	 */
	public void setFile(String file) {
		this.file = file;
	}
	
	/**
    * loadDB() permet de charger la base de donnees 
    * les differentes boucles et conditions se ressemblent, elle nous permettent tout simplement de parcourir le fichier XML.
    * Dans l'etat actuelle, ce chargement permet de garder la meme que structure que le fichier userDB.xml.
    @return true si bbd a ete correctement chargee.
*/
	public boolean loadDB(){
		SAXBuilder sxb = new SAXBuilder();
		try{
			this.document = sxb.build(new File(this.file)); // On lit le fichier en mémoire
		}catch(Exception e){return false;}  // S'il n'existe pas on retourne false 
		if(this.document!=null){ // Si le document existe 
			this.racine = document.getRootElement(); 
			List<Element> ListUtilisateurs = racine.getChildren(); // Affichage de l'arborescence 
			Iterator<Element> k=ListUtilisateurs.iterator(); 
			while(k.hasNext()){
				Element noeudcourant=(Element)k.next();
				List<Element> ListUtilisateurs2=noeudcourant.getChildren();
				Iterator<Element> k2 =  ListUtilisateurs2.iterator();
				while(k2.hasNext()){

					Element noeudcourant2 =(Element)k2.next();
					// Les différentes conditions ci dessous, nous permettent de charger le fichier XML, selon les différents cas (Group, Admin etc...)
					if(noeudcourant.getName().equals("Groups")){
						
						this.listGroups.add(new Group(Integer.valueOf(noeudcourant2.getChildText("groupId"))));
					}
					
					if(noeudcourant.getName().equals("Administrators")){
					
						this.listUsers.add(new Administrator(
								noeudcourant2.getChildText("login"),
								noeudcourant2.getChildText("pwd"),
								noeudcourant2.getChildText("firstname"),
								noeudcourant2.getChildText("surname"),
								Integer.valueOf((noeudcourant2.getChildText("Id")))));
					}
				// Pour l'étudiant, il faut bien faire attention au cas où l'étudiant appartient à un groupe et au cas contraire	
				if(noeudcourant.getName().equals("Students")){
					int IdGroup=Integer.valueOf((noeudcourant2.getChildText("groupId")));
					this.listUsers.add(new Student(
							noeudcourant2.getChildText("login"),
							noeudcourant2.getChildText("pwd"),
							noeudcourant2.getChildText("firstname"),
							noeudcourant2.getChildText("surname"),
							Integer.valueOf((noeudcourant2.getChildText("Id"))),
							IdGroup));
				//Un étudiant dont l'ID Group est de -1 signifie qu'il n'appartient à aucun groupe.
					if(IdGroup!=-1){
						for(int l=0;l<this.listGroups.size();l++){
							if(IdGroup == this.listGroups.get(l).GetGroupId())
								this.listGroups.get(l).addStudent(noeudcourant2.getChildText("login"));
						}
					}
				}
					if(noeudcourant.getName().equals("Teachers")){
					
						this.listUsers.add(new Teacher(
							noeudcourant2.getChildText("login"),
							noeudcourant2.getChildText("pwd"),
							noeudcourant2.getChildText("firstname"),
							noeudcourant2.getChildText("surname"),
							Integer.valueOf((noeudcourant2.getChildText("Id")))));					 
				}
				}
			}
		}	
		return true;
	}
		
	/**
    Sauvegarder la bdd
    @return true si la bdd a ete correctement chargee
*/
	public boolean save(){
		this.racine = new Element("UserDB");
		this.document = new Document(racine);
		for(int i=0;i<this.listGroups.size();i++){
			this.saveGrouptoDB(this.listGroups.get(i));
		}

		for(int i=0;i<this.listUsers.size();i++){
			this.saveUsertoDB(this.listUsers.get(i));
		}
		try{
			XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
			out.output(document, new FileOutputStream(file));
		}catch(java.io.IOException e){return false;}
		return true;
	}
	
	/**
    Sauvegarder un Utilisateur dans la base de donnees.
    @param utilisateur L'utilisateur a enregistrer.
    !!! l'ajout du caractere "s" vient directement du fait qu'il faut garder la meme structure du fichier XML userDB.xml.
*/	
	private void saveUsertoDB(User utilisateur){
		Element UserType = racine.getChild(utilisateur.getType()+"s");
		if(UserType==null){
			racine.addContent(new Element(utilisateur.getType()+"s"));
			UserType = racine.getChild(utilisateur.getType()+"s");
		}
		Element User = new Element(utilisateur.getType());
		User.addContent(new Element ("login").setText(utilisateur.getUserLogin()));
		User.addContent(new Element ("firstname").setText(utilisateur.getFirstname()));
		User.addContent(new Element ("surname").setText(utilisateur.getSurname()));
		User.addContent(new Element ("pwd").setText(utilisateur.getUserPswd()));
		User.addContent(new Element ("Id").setText(String.valueOf(utilisateur.getId())));
		// Si l'utilisateur est un etudiant, il ne faut oublier d'indiquer son IdGroup
		if(utilisateur.getType().equals("Student"))
			User.addContent(new Element ("groupId").setText(String.valueOf((((Student) utilisateur).getGroup()))));
		UserType.addContent(User);
	}
	
	
	/**
    Sauvegarder un Groupe dans la base de donnees.
    @param group Le groupe a enregistrer.
*/
	private void saveGrouptoDB(Group group){
		Element groupes = racine.getChild("Groups");
		if(groupes==null){  // Il ne faut oublier de traiter le cas ou il n'y a pas de groupe 
			racine.addContent(new Element("Groups"));
			groupes = racine.getChild("Groups");
		}
		Element Group = new Element("Group");
		Group.addContent(new Element ("groupId").setText(String.valueOf(group.GetGroupId())));
		groupes.addContent(Group);
	}
	


	/**
    Ajouter un administrateur.
    @param adminLogin :  Login de l'admin qui a l'autorisation d'ajouter.
    @param newAdminlogin : Login de l'admin à ajouter.
    @param adminID : Id de l'admin à ajouter.
    @param firstname : firstname de l'admin à ajouter.
    @param surname : surname de l'admin à ajouter.
    @param pwd : pwd de l'admin à ajouter.
    @see addAdmin dans la classe Administrator
    @return True quand le superAdmin (su par exemple) à ajouter l'admin. False sinon
*/
	public boolean addAdmin(String adminLogin, String newAdminlogin, int adminID, String firstname, String surname,String pwd) {
		
		for(int indice=0;indice<this.listUsers.size();indice++){
			if(this.listUsers.get(indice).getId()==adminID)
				return false;
		}
		listUsers.add(superAdmin.addAdmin(newAdminlogin, pwd, firstname, surname, adminID));
		return true;
	}
	
	
	/**
    Ajouter un professeur (teacher).
    @param adminLogin :  Login de l'admin qui a l'autorisation d'ajouter (su dans notre cas, le superAdmin).
    @param teacherlogin : Login du teacher a ajouter.
    @param teacherID : Id du teacher a ajouter.
    @param firstname : firstname du teacher a ajouter.
    @param surname : surname du teacher a ajouter.
    @param pwd : pwd du teacher a ajouter.
    @see addTeacher dans la classe Administrator
    @return True quand le superAdmin (su par exemple) a ajouter le professeur. False sinon
*/
	public boolean addTeacher(String adminLogin, String teacherLogin, int teacherID, String firstname, String surname, String pwd)
	{
		// Nous verifions tout d'abord que le teacher ne soit pas dans la BDD
		for(int indice=0;indice<this.listUsers.size();indice++){
			if(this.listUsers.get(indice).getId()==teacherID)
				return false;
		}
		//S'il n'existe pas (ID non present dans la BDD, on ajoute alors le nouveau teacher
		listUsers.add(superAdmin.addTeacher(teacherLogin, pwd, firstname, surname, teacherID));
		return true;
	}
	
	/**
    Ajouter un eleve (student).
    @param adminLogin :  Login de l'admin qui a l'autorisation d'ajouter (su dans notre cas, le superAdmin).
    @param studentlogin : Login du student a ajouter.
    @param studentID : Id du student a ajouter.
    @param firstname : firstname du student a ajouter.
    @param surname : surname du student a ajouter.
    @param pwd : pwd du student a ajouter.
    @see addStudent dans la classe Administrator
    @return True quand le superAdmin (su par exemple) à ajouter le student. False sinon
*/
	public boolean addStudent(String adminLogin, String studentLogin, int studentID, String firstname, String surname, String pwd)
	{
		//Comme dans le cas de addTeacher, nous verifions 
		for(int indice=0;indice<this.listUsers.size();indice++){
			if(this.listUsers.get(indice).getId()==studentID){
				return false;
		}
			}
		listUsers.add(superAdmin.addStudent(studentLogin, pwd, firstname, surname, studentID));
		return true;
	}
	
	
	/**
    Supprimer un utilisateur (admin, teacher ou student).
    @param adminLogin :  Login de l'admin qui a l'autorisation de supprimer (su dans notre cas, le superAdmin).
    @param userlogin : Login de l'utilisateur a supprimer
    @see remove : methode presente dans la classe Administrator.
    @return True quand le superAdmin (su par exemple) à supprimer le user. False sinon
*/
	public boolean removeUser(String adminLogin, String userLogin) {
		return superAdmin.remove(listUsers, userLogin);
	}
	
	
	/**
    Ajouter un groupe
    @param adminLogin :  Login de l'admin qui a l'autorisation d'ajouter (su dans notre cas, le superAdmin).
    @param groupId : Id du group a creer
    @see addGroup : methode presente dans la classe Administrator.
    @return True quand le superAdmin (su par exemple) à creer le groupe. False sinon
*/
	public boolean addGroup(String adminLogin, int groupId) {
		return superAdmin.addGroup(groupId, listGroups);
	}
	
	
	/**
    Supprimer un groupe.
    @param adminLogin :  Login de l'admin qui a l'autorisation de supprimer (su dans notre cas, le superAdmin).
    @param groupId : Id du groupe a supprimer
    @see removeGroup : methode presente dans la classe Administrator.
    @return True quand le superAdmin (su par exemple) à supprimer le groupe. False sinon
*/
	public boolean removeGroup(String adminLogin, int groupId) {
		return superAdmin.removeGroup(groupId, listUsers, listGroups);
	}
	
	public boolean associateStudToGroup(String adminLogin, String studentLogin, int groupId) {
		return superAdmin.associateStudToGroup(studentLogin, groupId, listUsers, listGroups);
	}
	
}


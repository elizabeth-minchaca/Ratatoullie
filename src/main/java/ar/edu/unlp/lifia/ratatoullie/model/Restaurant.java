package ar.edu.unlp.lifia.ratatoullie.model;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@DiscriminatorValue("RESTAURANT")
@Table(name="restaurant")
public class Restaurant implements Commenting{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private long openingDate;
	@Lob
	private Blob image;
	@OneToOne(cascade = CascadeType.ALL, optional = false)
	@JoinColumn(name = "location_id")
	private Location location;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id", foreignKey = @ForeignKey(name = "category_id_fk"))
	private Category category; //One-way association
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany
	private List<Comment> comments; 
	@OneToMany(mappedBy="restaurant",cascade=CascadeType.ALL)
	private List<Menu> menus;
	@ManyToOne
	@JoinColumn(name="ratatoullie_id")
	private Ratatoullie ratatoullie;
	@ManyToOne(cascade=CascadeType.ALL)  
	@JoinColumn(name="owner_id")
	private UserResponsible owner;

	protected Restaurant() {
	}

	/**
	 * Create a {@link Restaurant} and sets a {@link UserResponsible} as its owner.
	 * @param name
	 * @param openigDate
	 * @param location
	 * @param userResponsible
	 * @param image
	 */
	protected Restaurant(String name, long openigDate, Location location, UserResponsible userResponsible, Blob image) {
		setCategory(new Neutral());
		setComments(new ArrayList<Comment>());
		setLocation(location);
		setImage(image);
		setMenus(new ArrayList<Menu>());
		setName(name);
		setOpeningDate(openigDate);
		setOwner(userResponsible);
		setRatatoullie(Ratatoullie.getInstance());
	}	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(long openingDate) {
		this.openingDate = openingDate;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}

	public String getImageBase64(){
		Blob blob = this.getImage();
		byte[] bdata = null;
		try {
			bdata = blob.getBytes(1, (int) blob.length());
		} catch (Exception e) {
			e.printStackTrace();
		}
		String asB64 = "";
		try {
			asB64 = Base64.getEncoder().encodeToString(bdata);
		} catch (NullPointerException e) {

		}
		return asB64;
	}

	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	@Override
	public void comment(Comment comment) {
		getComments().add(comment);
		getOwner().notify(comment);
		updateCategory();
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	public Menu getCourentMenu(){
		if(getMenus().size()>0){
			return getMenus().get(getMenus().size()-1);
		}
		return null;
	}
	/**
	 * Adds a {@link Menu} and sets it as the current one.
	 * @param menu
	 */
	protected void addMenu(Menu menu){
		if(getCourentMenu()!=null){
			getCourentMenu().setEndDate(new Date().getTime());
		}		
		getMenus().add(menu);
	}
	/**
	 * Creates a new {@link Menu} and adds it to a {@link Restaurant}
	 * @param text
	 * @param startDate
	 * @return 
	 */
	public Menu addMenu(String text,String description, long startDate, Blob image){
		Menu menu=new Menu(this, text, description, startDate, image);
		addMenu(menu);
		return menu;
	}

	public UserResponsible getOwner() {
		return owner;
	}
	public void setOwner(UserResponsible owner) {
		this.owner = owner;
	}
	/**
	 * Returns a list of all the {@link Comment} of a {@link Restaurant}, including the ones of its {@link Menu}
	 * @return
	 */
	public List<Comment> getAllComments(){
		ArrayList<Comment> comments= new ArrayList<>();
		comments.addAll(getComments());
		for (Menu menu:getMenus()) {
			for (Comment comment : menu.getComments()) {
				if(!comments.contains(comment)){
					comments.add(comment);
				}
			}			
		}		
		return comments;
	}
	/**
	 * Updates the {@link Category} of the {@link Restaurant}
	 */
	public void updateCategory() {
		getCategory().update(this);

	}
	/**
	 * Returns a list of all the {@link Comment} of a {@link Restaurant}, including the ones of its {@link Menu} in a range of dates.
	 * @return
	 */
	public List<Comment> getAllComments(long initialDate,long finalDate){
		ArrayList<Comment> comments= new ArrayList<Comment>();
		for (Comment comment : getAllComments()) {
			if(comment.getDate()>=initialDate && comment.getDate()<=finalDate){
				comments.add(comment);
			}
		}
		return comments;
	}
	@Override
	public boolean equals(Object obj) {
		return this.getName().compareTo(((Restaurant)obj).getName())==0&&this.getLocation().equals(((Restaurant)obj).getLocation());
	}
	@Override
	public int hashCode() {
		return Objects.hash(getName(),getLocation());
	}
	public List<Benefits> getBenefices() {		
		return getCategory().getBenefices();
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Ratatoullie getRatatoullie() {
		return ratatoullie;
	}
	public void setRatatoullie(Ratatoullie ratatoullie) {
		this.ratatoullie = ratatoullie;
	}	
}
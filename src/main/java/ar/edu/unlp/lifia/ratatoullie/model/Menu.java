package ar.edu.unlp.lifia.ratatoullie.model;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
@Entity
@DiscriminatorValue("MENU")
@Table(name="menu")
public class Menu implements Commenting{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="restaurant_id")
	private Restaurant restaurant;
	private String text;
	private String description;
	@Lob
	private Blob image;
	private long startDate;
	private long endDate;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany
	private List<Comment> comments;	
	@OneToMany(mappedBy="menu")
	private List<Recommendation> recommendations;
	protected Menu() {
	}
	protected Menu(Restaurant restaurant, String text,String description, long startDate, Blob image) {
		setComments(new ArrayList<Comment>());
		setEndDate(0);
		setRecommendations(new ArrayList<Recommendation>());
		setRestaurant(restaurant);		
		setStartDate(startDate);
		setText(text);	
		setImage(image);
		setDescription(description);
	}

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Blob getImage() {
		return image;
	}
	public void setImage(Blob image) {
		this.image = image;
	}
	public long getStartDate() {
		return startDate;
	}
	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}
	public long getEndDate() {
		return endDate;
	}
	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}
	public Restaurant getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	@Override
	public void comment(Comment comment) {
		getComments().add(comment);
		getRestaurant().getOwner().notify(comment);
		getRestaurant().updateCategory();
	}
	@Override
	public Location getLocation() {
		return getRestaurant().getLocation();
	}
	public List<Comment> getComments() {
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	public List<Recommendation> getRecommendations() {
		return recommendations;
	}
	public void setRecommendations(List<Recommendation> recommendations) {
		this.recommendations = recommendations;
	}
	public void addRecommendation(Recommendation recommendation){
		getRecommendations().add(recommendation);
	}	
	/**
	 * Creates a {@link Menu} and adds it in the {@link Restaurant} 
	 * @param restaurant
	 * @param text
	 * @param startDate
	 * @return
	 */
//	public static Menu create(Restaurant restaurant, String text, long startDate){
//		Menu menu = new Menu(restaurant, text, startDate);
//		restaurant.addMenu(menu);
//		return menu;		
//	}
	public static Menu create(Restaurant restaurant, String text, String description, long startDate, Blob image ){
		Menu menu = new Menu(restaurant, text, description, startDate, image);
		restaurant.addMenu(menu);
		return menu;		
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public String getImageBase64(){
	    Blob blob = this.getImage();
		byte[] bdata = null;
		try {
			bdata = blob.getBytes(1, (int) blob.length());
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		String asB64 = "";
		try {
			asB64 = Base64.getEncoder().encodeToString(bdata);
		} catch (NullPointerException e) {
			
		}
	    return asB64;
	}

}

package ar.edu.unlp.lifia.ratatoullie.dto;

import java.util.List;

public class MenuDto {
	private long id;
	private RestaurantMinDto restaurant;
	private String text;
	private long startDate;
	private long endDate;
	private String description;
	private String image;
	private List<CommentDto> comments;
	public MenuDto() {
	}
	public MenuDto(long id, RestaurantMinDto restaurant, String text, String description, String image, long startDate, long endDate,
	List<CommentDto> comments) {
		setId(id);
		setComments(comments);
		setEndDate(endDate);
		
		setRestaurant(restaurant);
		setStartDate(startDate);
		setText(text);
		setDescription(description);
		setImage(image);
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
	public RestaurantMinDto getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantMinDto restaurant) {
		this.restaurant = restaurant;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public List<CommentDto> getComments() {
		return comments;
	}
	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}
}

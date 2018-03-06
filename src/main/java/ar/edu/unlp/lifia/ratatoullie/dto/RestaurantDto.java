package ar.edu.unlp.lifia.ratatoullie.dto;

import java.util.List;


public class RestaurantDto {
	private long id;
	private String name;
	private long openingDate;	
	private LocationDto location;    
	private CategoryDto category;
	private String image;
	private List<CommentDto> comments;	
	private List<MenuDto> menus;
	private UserMinDto owner;
	private MenuDto currentMenu;
	public RestaurantDto() {
	}
	public RestaurantDto(String name,long openingDate, LocationDto location, CategoryDto category,
			UserMinDto owner) {
		setCategory(category);
		setName(name);
		setOpeningDate(openingDate);
		setOwner(owner);
		setLocation(location);		
	}
	public RestaurantDto(String name,long openingDate, LocationDto location, CategoryDto category) {
		setCategory(category);
		setName(name);
		setOpeningDate(openingDate);
		setLocation(location);		
	}
	public RestaurantDto(long id, String name,long openingDate, String image, LocationDto location, CategoryDto category,	
	 List<CommentDto> comments, List<MenuDto> menus, UserMinDto owner, MenuDto currentMenu) {
		setId(id);
		setCategory(category);
		setComments(comments);
		setCurrentMenu(currentMenu);
		setLocation(location);
		setMenus(menus);
		setName(name);
		setOpeningDate(openingDate);
		setOwner(owner);
		setImage(image);
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

	public LocationDto getLocation() {
		return location;
	}
	public void setLocation(LocationDto location) {
		this.location = location;
	}
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
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
	public List<MenuDto> getMenus() {
		return menus;
	}
	public void setMenus(List<MenuDto> menus) {
		this.menus = menus;
	}	
	public UserMinDto getOwner() {
		return owner;
	}
	public void setOwner(UserMinDto owner) {
		this.owner = owner;
	}
	public MenuDto getCurrentMenu() {
		return currentMenu;
	}
	public void setCurrentMenu(MenuDto currentMenu) {
		this.currentMenu = currentMenu;
	}
	
}

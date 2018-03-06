package ar.edu.unlp.lifia.ratatoullie.dto;


public class RestaurantMinDto {
	private long id;
	private String name;
	private LocationDto location;
	private int commenteSize;
	private String image;
	private UserMinDto owner;
	private long openingDate;	
	private CategoryDto category;
	private long idCurrentMenu;
	public RestaurantMinDto() {
	}
	public RestaurantMinDto(long id, String name, LocationDto location, int commentSize, String image){
		setId(id);
		setLocation(location);
		setName(name);
		setCommenteSize(commentSize);
		setImage(image);
	}
	public RestaurantMinDto(long id, String name, LocationDto location, String image, UserMinDto owner,
	long openingDate,CategoryDto category,long idCurrentMenu){
		setId(id);
		setLocation(location);
		setName(name); 
		setImage(image);
		setCategory(category);
		setOwner(owner);
		setOpeningDate(openingDate);
		setIdCurrentMenu(idCurrentMenu);
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
	public LocationDto getLocation() {
		return location;
	}
	public void setLocation(LocationDto location) {
		this.location = location;
	}
	public int getCommenteSize() {
		return commenteSize;
	}
	public void setCommenteSize(int commenteSize) {
		this.commenteSize = commenteSize;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public UserMinDto getOwner() {
		return owner;
	}
	public void setOwner(UserMinDto owner) {
		this.owner = owner;
	}
	public long getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(long openingDate) {
		this.openingDate = openingDate;
	}
	public CategoryDto getCategory() {
		return category;
	}
	public void setCategory(CategoryDto category) {
		this.category = category;
	}
	public long getIdCurrentMenu() {
		return idCurrentMenu;
	}
	public void setIdCurrentMenu(long idCurrentMenu) {
		this.idCurrentMenu = idCurrentMenu;
	}
	
}

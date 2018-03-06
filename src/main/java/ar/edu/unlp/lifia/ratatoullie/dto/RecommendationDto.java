package ar.edu.unlp.lifia.ratatoullie.dto;
/**
 * @author NATA
 *
 */
public class RecommendationDto {
	private UserMinDto adviser;
	private UserMinDto recommended;
	private MenuDto menu;
	private long date;
	private String text;
	public RecommendationDto() {
	}
	public RecommendationDto( UserMinDto adviser, UserMinDto recommended, MenuDto menu, long date, String text) {
		setAdviser(adviser);
		setDate(date);
		setMenu(menu);
		setRecommended(recommended);
		setText(text);
	}	
	public UserMinDto getAdviser() {
		return adviser;
	}
	public void setAdviser(UserMinDto adviser) {
		this.adviser = adviser;
	}
	public UserMinDto getRecommended() {
		return recommended;
	}
	public void setRecommended(UserMinDto recommended) {
		this.recommended = recommended;
	}
	public MenuDto getMenu() {
		return menu;
	}
	public void setMenu(MenuDto menu) {
		this.menu = menu;
	}
	public long getDate() {
		return date;
	}
	public void setDate(long date) {
		this.date = date;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	

}

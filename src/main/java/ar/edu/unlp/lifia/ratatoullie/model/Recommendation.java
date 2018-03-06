package ar.edu.unlp.lifia.ratatoullie.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Recommendation of a {@link Menu}
 * @author NATA
 *
 */
@Entity
@Table(name="recommendation ")
public class Recommendation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@ManyToOne
	@JoinColumn(name="adviser_id")
	private User adviser;
	@ManyToOne
	@JoinColumn(name="recommended_id")
	private User recommended;
	@ManyToOne
	@JoinColumn(name="menu_id")
	private Menu menu;
	private long date;
	private String text;
	public Recommendation() {

	}
	public Recommendation(User adviser, User recommended, Menu menu, long date, String text) {
		setAdviser(adviser);
		setDate(date);
		setMenu(menu);
		setRecommended(recommended);
		setText(text);
	}
	public User getAdviser() {
		return adviser;
	}
	public void setAdviser(User adviser) {
		this.adviser = adviser;
	}
	public User getRecommended() {
		return recommended;
	}
	public void setRecommended(User recommended) {
		this.recommended = recommended;
	}
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}	
}
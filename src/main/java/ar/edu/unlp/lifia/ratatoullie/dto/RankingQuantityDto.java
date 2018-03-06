package ar.edu.unlp.lifia.ratatoullie.dto;

public class RankingQuantityDto {
	private String ranking;
	private int quantity;
	public RankingQuantityDto() {
	}
	public RankingQuantityDto(String ranking,int quantity) {
		setQuantity(quantity);
		setRanking(ranking);
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranting) {
		this.ranking = ranting;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}

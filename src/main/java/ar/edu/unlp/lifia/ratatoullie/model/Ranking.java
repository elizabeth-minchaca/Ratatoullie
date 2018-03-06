package ar.edu.unlp.lifia.ratatoullie.model;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ranking_type")
@Table(name = "ranking")
public abstract class Ranking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ranking_id")
	private Long id;

	public abstract void update(User user);
	
	@Override
	public boolean equals(Object obj) {		
		return obj.getClass().equals(getClass());
	}
	@Override
	public int hashCode() {
		return getClass().getName().hashCode();
	}
	public abstract String getName();
	@Override
	public String toString() {
		return getName();
	}
}
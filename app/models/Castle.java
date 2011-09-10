package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Castle extends Model {

	public enum Target {
		ME, ENEMY		
	}

	public HashMap<String, Integer> resources;

	@OneToMany
	public List<Card> cards;

	public Castle enemyCastle;

	public Castle(HashMap resources) {
		this.resources = resources;
	}

	public void setEnemyCastle(Castle enemyCastle) {
		this.enemyCastle = enemyCastle;
	}

	public Castle addResource(String resourceID, int amount) {
		this.resources.put(resourceID, amount + this.countResource(resourceID));
		return this;
	}

	public Castle removeResource(String resourceID, int amount) {
		this.addResource(resourceID, amount * -1);
		return this;
	}

	public int countResource(String resourceID) {
		if (!this.resources.containsKey(resourceID)) {
			return 0;
		}

		return this.resources.get(resourceID);
	}

	public Castle getEnemyCastle() {
		return this.enemyCastle;
	}

}

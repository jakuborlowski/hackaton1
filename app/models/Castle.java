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

	public Castle addResource(String resourceId, int amount) {
		this.resources.put(resourceId, amount + this.countResource(resourceId));
		return this;
	}

	public Castle removeResource(String resourceId, int amount) {
		this.addResource(resourceId, amount * -1);
		return this;
	}

	public int countResource(String resourceId) {
		if (!this.resources.containsKey(resourceId)) {
			return 0;
		}

		return this.resources.get(resourceId);
	}

	public Castle getEnemyCastle() {
		return this.enemyCastle;
	}
}

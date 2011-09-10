package models;

import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Resource extends Model {

	@OneToMany
	public List<Change> changes = new ArrayList<Change>();

	public String resourceId;
	public String name;
	public Boolean isTransferable;
	public Boolean isStoreable;

	public Resource(String resourceId, String name, Boolean isTransferable,
			Boolean isStoreable) {
		this.resourceId = resourceId;
		this.name = name;
		this.isTransferable = isTransferable;
		this.isStoreable = isStoreable;
	}

	public void update(Castle castle, int amount) {

	}

}
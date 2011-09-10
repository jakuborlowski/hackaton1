package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Castle extends Model {
	
	public HashMap<String, Integer> storage;
	public HashMap<String, Integer> construction;
	
	@OneToMany
	public List<Card> cards;
	
	public Castle(HashMap storage, HashMap construction)
	{
		this.storage = storage;
		this.construction = construction;
	}
	
	public Castle addStorage(String resourceID, int amount)
	{
		this.storage.put(resourceID, amount + this.storage.get(resourceID));
		return this;
	}
	
	public int countStorage(String resourceID)
	{
		return this.storage.get(resourceID);
	}
}

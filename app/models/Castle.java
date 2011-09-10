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
		this.storage.put(resourceID, amount + this.countStorage(resourceID));
		return this;
	}
	
	public int countStorage(String resourceID)
	{
		if (!this.storage.containsKey(resourceID))
		{
			return 0;
		}
		
		return this.storage.get(resourceID);
	}
	
	public Castle addConstruction(String resourceID, int amount)
	{
		this.construction.put(resourceID, amount + this.countConstruction(resourceID));
		return this;
	}
	
	public int countConstruction(String resourceID)
	{
		if (!this.construction.containsKey(resourceID))
		{
			return 0;
		}
		
		return this.construction.get(resourceID);
	}
}

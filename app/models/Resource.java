package models;

import java.util.*;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Resource extends Model {
    
	@OneToMany
    public List<Change> changes = new ArrayList<Change>();
	
    public String resourceID;
    public String name;
    public Boolean isTransferable;
    public Boolean isStoreable;
	
	public Resource(String resourceID, String name, Boolean isTransferable, Boolean isStoreable)
	{
		this.resourceID = resourceID;
		this.name = name;
		this.isTransferable = isTransferable;
		this.isStoreable = isStoreable;
	}
    
    public void update(Castle castle, int amount) {
        
        
    }
    
}
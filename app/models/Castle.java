package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Castle extends Model {
	
	
	//public Map<String, Integer> storage;
	//public Map<String, Integer> castle;
	
	@OneToMany
	public List<Card> cards;
	
	public Castle()
	{
		//this.storage = new HashMap<String, Integer>();
		//this.castle = new HashMap<String, Integer>();
	}
}

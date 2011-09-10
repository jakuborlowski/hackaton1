package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Resource extends Model {
    
    public List<Change> changes;
    public String resourceID;
    public String name;
    public Boolean isTransferable;
    public Boolean isStoreable;
    
    public void update(int amount) {
        
    }
    
}

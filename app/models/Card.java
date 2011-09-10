package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Card extends Model {

    @ManyToMany
    @JoinTable(name="Card_required_changes")
    public List<Change> required;
    @ManyToMany
    @JoinTable(name="Card_operations_changes")        
    public List<Change> operations;

    public boolean isPossible() {

        return true;

    }
}

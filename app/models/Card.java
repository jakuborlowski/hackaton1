package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Card extends Model {

//	@OneToMany
//    public List<Change> required;
//	@OneToMany
//    public List<Change> operations;

    public boolean isPossible() {

        return true;

    }
}
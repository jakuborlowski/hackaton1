package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Card extends Model {

    public List<Change> required;
    public List<Change> operations;

    public boolean isPossible() {

        return true;

    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Change extends Model {
	
	public String resourceID;
	public Castle target;
	public String operation;
	public Double amount;
	
	public boolean isPossible()
	{
		return true;
	}
	
	public void apply()
	{
	
	}
}

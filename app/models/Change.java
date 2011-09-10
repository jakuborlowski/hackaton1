package models;

import java.util.*;
import javax.persistence.*;

import play.db.jpa.*;

@Entity
@Table(name="_change")
public class Change extends Model {

	public String resourceID;

	@Enumerated(value = EnumType.STRING)
	public Castle.Target target;
	
	@Enumerated(value = EnumType.STRING)
	public Operations operation;
	
	public Double amount;

	public Change(String resourceID, Operations operation, Double amount,
			Castle.Target target) {

		this.resourceID = resourceID;
		this.operation = operation;
		this.amount = amount;
		this.target = target;

	}

	public boolean isPossible() {
		return true;
	}

	public void apply(Castle castle) {
		if (this.operation.equals("Add")) {
			this.executeAdd(castle);
		} else if (this.operation.equals("Remove")) {
			this.executeRemove(castle);
		}
		// TODO: Transfer, Multiply
	}

	public void executeAdd(Castle castle) {

		this.getTargetCastle(castle).addResource(this.resourceID,
				this.amount.intValue());

	}

	public void executeRemove(Castle castle) {

		this.getTargetCastle(castle).removeResource(this.resourceID,
				this.amount.intValue());

	}

	public Castle getTargetCastle(Castle castle) {

		switch (this.target) {
		case ENEMY:
			return castle.getEnemyCastle();
		case ME:
		default:
			return castle;
		}

	}

}

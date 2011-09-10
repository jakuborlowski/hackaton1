package models;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Change extends Model {

	public String resourceId;

	public Castle.Target target;
	public Operations operation;
	public Double amount;

	public Change(String resourceID, Operations operation, Double amount,
			Castle.Target target) {

		this.resourceId = resourceID;
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

		this.getTargetCastle(castle).addResource(this.resourceId,
				this.amount.intValue());

	}

	public void executeRemove(Castle castle) {

		this.getTargetCastle(castle).removeResource(this.resourceId,
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

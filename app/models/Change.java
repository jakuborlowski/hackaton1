package models;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Change extends Model {

    public String resourceID;
    /**
     * 0 - my castle
     * 1 - enemy castle
     */
    public int target;
    public String operation;
    public Double amount;

    public Change(String resourceID, String operation, Double amount, int target) {

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

        this.getTargetCastle(castle).addResource(this.resourceID, this.amount.intValue());

    }

    public void executeRemove(Castle castle) {

        this.getTargetCastle(castle).removeResource(this.resourceID, this.amount.intValue());

    }

    public Castle getTargetCastle(Castle castle) {

        switch (this.target) {
            case 1:
                return castle.getEnemyCastle();
            case 0:
            default:
                return castle;
        }

    }
}

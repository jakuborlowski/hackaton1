package models;

import javax.persistence.*;

import play.db.jpa.*;

@Entity
public class Change extends Model {

    public String resourceID;
    public Castle.Target target;
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
        if (this.operation.equals(Operations.ADD)) {
            this.executeAdd(castle);
        } else if (this.operation.equals(Operations.REMOVE)) {
            this.executeRemove(castle);
        }
        else if (this.operation.equals(Operations.MULTIPLY)) {
            this.executeMultiply(castle);
        }
        else if (this.operation.equals(Operations.TRANSFER)) {
            this.executeTransfer(castle);
        }
    }

    public void executeAdd(Castle castle) {

        this.getTargetCastle(castle).addResource(this.resourceID,
                this.amount.intValue());

    }

    public void executeRemove(Castle castle) {

        this.getTargetCastle(castle).removeResource(this.resourceID,
                this.amount.intValue());

    }
    
    public void executeMultiply(Castle castle) {
        
        int from = this.getTargetCastle(castle).countResource(this.resourceID);
        int to = (int)(from*this.amount);
        this.getTargetCastle(castle).addResource(this.resourceID, to-from);
        
    }
    
    public void executeTransfer(Castle castle) {
               
        int taken = this.getSourceCastle(castle).removeResource(this.resourceID, this.amount.intValue());
        this.getTargetCastle(castle).addResource(this.resourceID, taken);
        
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
    
    public Castle getSourceCastle(Castle castle) {

        switch (this.target) {
            case ME:
                return castle.getEnemyCastle();
            case ENEMY:
            default:
                return castle;
        }

    }
}

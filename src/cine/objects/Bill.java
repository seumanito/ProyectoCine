package cine.objects;

public class Bill {
    private double bill;
    private String  serialNumber;

    public Bill(double bill){
        this.utilValBill(bill);
    }

    public double getBill(){
        return this.bill;
    }

    private void utilValBill(double bill){
        if (bill<0){
            throw new IllegalArgumentException(" La cuenta no puede ser negativa. ");
        }else{
            this.bill=bill;
        }
    }
    
}

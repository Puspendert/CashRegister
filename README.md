# Cash Register

Create a cash register class in Java that should be able to accept only $20, $10, $5, $2 and $1 bills. Given the charge and amount of money received, return the change in each denomination that should be given from the cash register. Sometimes when the exact changes couldn't be made, they would tell us. Implement methods in the following class (you are free to change):

```
/**
 * Implement methods with TODO comments.
 * You are free to change anything.
 */
public class CashRegister {

    public static class Cash {
        private int numberOf20;
        private int numberOf10;
        private int numberOf5;
        private int numberOf2;
        private int numberOf1;

        public Cash(int numberOf20, int numberOf10, int numberOf5, int numberOf2, int numberOf1) {
            this.numberOf20 = numberOf20;
            this.numberOf10 = numberOf10;
            this.numberOf5 = numberOf5;
            this.numberOf2 = numberOf2;
            this.numberOf1 = numberOf1;
        }

        public int getNumberOf20() {
            return numberOf20;
        }

        public int getNumberOf10() {
            return numberOf10;
        }

        public int getNumberOf5() {
            return numberOf5;
        }

        public int getNumberOf2() {
            return numberOf2;
        }

        public int getNumberOf1() {
            return numberOf1;
        }
    }

    private Cash cash;

    public CashRegister(Cash cash) {
        this.cash = cash;
    }

    public void add(Cash cash) {
        // TODO add cash
    }

    public Cash getChanges(int chargeAmount, Cash paid) {
        // TODO calculate returned changes or indicating not possible
    }

}
```



Here are some sample results:    
```
`r = new CashRegister( new Cash(1,1,2,5,1));`    
// current state
    $20 1
    $10 1
    $5 2
    $2 5
    $1 1

// add 1 $20 bill.   
`r.add(new Cash(1,0,0,0,0);`   
// current state
    $20 2
    $10 1
    $5 2
    $2 5
    $1 1

// given $10 for charge $2   
`c = r.getChanges(2, new Cash(0,1,0,0,0);`   
// should return
$20 0
$10 0
$5 1
$2 1
$1 1    
// current state
    $20 2
    $10 2
    $5 1
    $2 4
    $1 0

// given $10 for charge $2    
`c = r.getChanges(2, new Cash(0,1,0,0,0);`    
// should return
$20 0
$10 0
$5 0
$2 4
$1 0
// current state    
    $20 2
    $10 3
    $5 1
    $2 0
    $1 0

// given $20 for charge $1      
`c = r.getChanges(1, new Cash(1,0,0,0,0)`    
// not possible
```
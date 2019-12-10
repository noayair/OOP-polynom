package Ex1;
public class ComplexFunction implements function {//hhh
    private Operation op;
    private function left;
    private function right;

    public static void main(String[] args) {
        System.out.println("bla");
    }
    public ComplexFunction(function f){
        this.left = f;
        this.op = Operation.None;
        this.right = null;
    }

    public ComplexFunction(function f1, Operation op, function f2) {
        this.left=f1;
        this.op=op;
        this.right=f2;
    }
    public ComplexFunction(){
        this.left=null;
        this.right=null;
        this.op=null;
    }
    public void set_OP(Operation op) {
        this.op=op;
    }
    public void set_right(function right) {
        this.right=right;
    }
    public void set_left(function left) {
        this.left=left;
    }
    public ComplexFunction(function f1, String op, function f2) {
        this.left = f1;
        this.right = f2;
        switch (op) {
            case "plus":
                this.op = Operation.Plus;
                break;
            case "div":
                this.op = Operation.Divid;
                break;
            case "max":
                this.op = Operation.Max;
                break;
            case "min":
                this.op = Operation.Min;
                break;
            case "comp":
                this.op = Operation.Comp;
                break;
            case "times":
                this.op = Operation.Times;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + op);
        }
    }
    public double f(double x) {
        if (this.right != null) {
            switch (op) {
                case Plus:
                    return ((this.left.f(x)) + (this.right.f(x)));
                case Divid:
                    return ((this.left.f(x)) / (this.right.f(x)));
                case Max:
                    return Math.max((this.left.f(x)), (this.right.f(x)));
                case Min:
                    return Math.min((this.left.f(x)), (this.right.f(x)));
                case Comp:
                    return this.left.f(this.right.f(x));
                case Times:
                    return ((this.left.f(x)) * (this.right.f(x)));
                case None:
                    return this.left.f(x);
                case Error:
                    return 0;
            }

        }
        return this.left.f(x);
    }

    public function initFromString(String s) {
        if(!s.contains("(")){
            Polynom p = new Polynom(s);
            return p;
        }else {
            int start = s.indexOf("(");
            int end = s.length() - 1;
            int psik = findPsik(s);
            this.op = Op(s.substring(0, start));
            // left side
            if (isCf(s.substring(start + 1, psik))) {
                this.left = new ComplexFunction();
                this.left.initFromString(s.substring(start + 1, psik));
            } else {
                this.left = new Polynom(s.substring(start + 1, psik));
            }
            //right side
            if (isCf(s.substring(psik + 1, end))) {
                this.right = new ComplexFunction();
                this.right.initFromString(s.substring(psik + 1, end));
            } else {
                this.right = new Polynom(s.substring(psik + 1, end));
            }
            return this;
        }
    }


    public boolean isCf(String s){
        if( s.charAt(0) == 'p' || s.charAt(0) == 'd' || s.charAt(0)==  'm' || s.charAt(0) == 'c' || s.charAt(0) == 'n'){
            return true;
        }
        return false;
    }
    public int findPsik (String s){
        int count1 =0 ;
        int count2 = 0;
        int i;
        for(i=0; i<s.length(); i++){
            if(s.charAt(i)=='('){
                count1++;
            }
            else if(s.charAt(i) == ')'){
                count2++;
            }
            else if(count1 == 1+count2 && s.charAt(i)== ','){
                break;
            }
        }
        return i;
    }

    public String toString(){
        String s="";
        s+= this.op + "(" + this.left + "," + this.right + ")";
        return s;
    }

    public function copy() {
        ComplexFunction cf=new ComplexFunction();
        cf.right = this.right;
        cf.left = this.left;
        cf.op = this.op;
        return cf;
    }
    public boolean equals(Object obj){
        function f1 = (function) obj;
        for(int i = -200; i <= 200; i++){
            if(f1.f(i) != this.f(i)) return false;
        }
        return true;
    }

    public void plus(function f1){
        ComplexFunction left= new ComplexFunction(this.left, this.op, this.right);
        this.op = Operation.Plus;
        this.left=left;
        this.right=f1;
    }
    public void mul(function f1){
        ComplexFunction left= new ComplexFunction(this.left, this.op, this.right);
        this.op = Operation.Times;
        this.left=left;
        this.right=f1;
    }

    public void div(function f1){
        ComplexFunction left= new ComplexFunction(this.left, this.op, this.right);
        this.op = Operation.Divid;
        this.left=left;
        this.right=f1;
    }

    public void max(function f1){
        ComplexFunction left= new ComplexFunction(this.left, this.op, this.right);
        this.op = Operation.Max;
        this.left=left;
        this.right=f1;
    }

    public void min(function f1){
        ComplexFunction left= new ComplexFunction(this.left, this.op, this.right);
        this.op = Operation.Min;
        this.left=left;
        this.right=f1;
    }

    public void comp(function f1){
        ComplexFunction left= new ComplexFunction(this.left, this.op, this.right);
        this.op = Operation.Comp;
        this.left=left;
        this.right=f1;
    }

    public function left(){
        return this.left;
    }

    public function right(){
        return this.right;
    }
    public Operation Op(String s) {
        switch (s) {
            case "plus":
                return op.Plus;
            case "mul":
                return op.Times;
            case "div":
                return op.Divid;
            case "max":
                return op.Max;
            case "min":
                return op.Min;
            case "comp":
                return op.Comp;
            default:
                return op.Error;
        }
    }
}
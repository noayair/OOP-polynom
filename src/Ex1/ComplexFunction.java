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

    public ComplexFunction(Operation op, function f1, function f2) {
        this.left=f1;
        this.op=op;
        this.right=f2;
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

            Operation op = Op(s.substring(0, start));
            // left side
            function left;
            if (isCf(s.substring(start + 1, psik))) {
                left = this.initFromString(s.substring(start + 1, psik));
            } else {
                left = new Polynom(s.substring(start + 1, psik));
            }
            //right side
            function right;
            if (isCf(s.substring(psik + 1, end))) {
                right = this.initFromString(s.substring(psik + 1, end));
            } else {
                right = new Polynom(s.substring(psik + 1, end));
            }
            return new ComplexFunction(op, left, right);
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
        if(this.op == Operation.None){
            return left.toString();
        }
        String s="";
        String o = OpToString(this.op);
        s+= o + "(" + this.left + "," + this.right + ")";
        return s;
    }

    public function copy() {
        ComplexFunction cf=new ComplexFunction(new Monom(0,0));
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
        ComplexFunction left= new ComplexFunction(this.op, this.left, this.right);
        this.op = Operation.Plus;
        this.left=left;
        this.right=f1;
    }
    public void mul(function f1){
        ComplexFunction left= new ComplexFunction(this.op, this.left, this.right);
        this.op = Operation.Times;
        this.left=left;
        this.right=f1;
    }

    public void div(function f1){
        ComplexFunction left= new ComplexFunction(this.op, this.left, this.right);
        this.op = Operation.Divid;
        this.left=left;
        this.right=f1;
    }

    public void max(function f1){
        ComplexFunction left= new ComplexFunction(this.op, this.left, this.right);
        this.op = Operation.Max;
        this.left=left;
        this.right=f1;
    }

    public void min(function f1){
        ComplexFunction left= new ComplexFunction(this.op, this.left, this.right);
        this.op = Operation.Min;
        this.left=left;
        this.right=f1;
    }

    public void comp(function f1){
        ComplexFunction left= new ComplexFunction(this.op, this.left, this.right);
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

    public String OpToString(Operation op) {
        switch (op) {
            case Plus:
                return "plus";
            case Times:
                return "mul";
            case Divid:
                return "div";
            case Max:
                return "max";
            case Min:
                return "min";
            case Comp:
                return "comp";
            default:
                throw new IllegalStateException("Unexpected value: " + op);
        }
    }
}
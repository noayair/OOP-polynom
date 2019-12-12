package Ex1;

/** This class represents a complex function of type y=g(f1(x), f2(x)), where both f1, f2 are functions (or complex functions),
 * y and x are real numbers and g is an operation: plus, mul, div, max, min, comp (f1(f2(x))).
 *
 * @author Noa Yair and Oriya Kronfeld
 */
public class ComplexFunction implements complex_function {
    private Operation op;
    private function left;
    private function right;

    /**
     * Constructor that get just the left function.
     *
     */
    public ComplexFunction(function f){
        if (f == null){
            throw new RuntimeException("function cant be null");
        }
        this.left = f.copy();
        this.op = Operation.None;
    }

    /** Constructor that get operation, left function and right function.
     *
     */
    public ComplexFunction(Operation op, function f1, function f2) {
        if(f1 == null){
            throw new RuntimeException("function cant be null");
        }
        this.left=f1.copy();
        this.op=op;
        if(f2 == null){
            this.right = null;
        }else {
            this.right = f2.copy();
        }
    }

    /** constructor that get string of operation, left function and right function.
     *
     */
    public ComplexFunction(String op , function f1 , function f2) {
        this.left = f1.copy();
        this.right = f2.copy();
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
            case "mul":
                this.op = Operation.Times;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + op);
        }
    }

    /** Setters and getters
     *
     */
    public void set_OP(Operation op) {
        this.op=op;
    }

    public void set_right(function right) {
        this.right=right;
    }

    public void set_left(function left) {
        this.left=left;
    }

    public Operation getOp() {
        return op;
    }

    public function getLeft() {
        return left;
    }

    public function getRight() {
        return right;
    }

    /**
     * Function that calculate the function value at x.
     * @return the function value at x.
     */
    public double f(double x) {
        if (this.right != null) {
            switch (op) {
                case Plus:
                    return ((this.left.f(x)) + (this.right.f(x)));
                case Divid:
                    if(this.right.f(x) == 0) {
                    throw new IllegalStateException("0 cannot be divided");
                    }
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

    /**
     *  This method returns a function from a given string.
     * @return function.
     */
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

    /**
     * Function that help us to check if there is more operation.
     * @return true if there is, false if is a number.
     */
    public boolean isCf(String s){
        if( s.charAt(0) == 'p' || s.charAt(0) == 'd' || s.charAt(0)==  'm' || s.charAt(0) == 'c' || s.charAt(0) == 'n'){
            return true;
        }
        return false;
    }

    /**
     * Function that help us to find the char ','.
     * @return the location of ',' in the string.
     */
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

    /**
     * Function that prints the complex_function as a string.
     */
    public String toString(){
        if(this.op == Operation.None){
            return left.toString();
        }
        String s="";
        String o = OpToString(this.op);
        s+= o + "(" + this.left + "," + this.right + ")";
        return s;
    }

    /**
     * create a new complex_function that identical to the original complex_function.
     * @return the new complex_function.
     */
    public function copy() {
        function l = this.left.copy();
        function r;
        if (this.right == null){
            return new ComplexFunction(l);
        }else{
            r = this.right.copy();
        }
        return new ComplexFunction(this.op , l , r);
    }

    /**
     * Check if the excepted complex_function is equal to the original complex_function.
     * @param obj - the object(complex_function) we check if is equal to the original complex_function.
     * @return true for equal, false for not equal.
     */
    public boolean equals(Object obj){
        function f1 = (function) obj;
        for(int i = -200; i <= 200; i++){
            if(f1.f(i) != this.f(i)) return false;
        }
        return true;
    }

    /**
     * Function that add the left function to the right function.
     * @param f1 the complex_function which will be added to this complex_function.
     */
    public void plus(function f1){
        ComplexFunction left= new ComplexFunction(this.op, this.left, this.right);
        this.op = Operation.Plus;
        this.left=left;
        this.right=f1;
    }

    /**
     * Function that multiply the left function to the right function.
     * @param f1 the complex_function which will be multiply be this complex_function.
     */
    public void mul(function f1){
        ComplexFunction left= new ComplexFunction(this.op, this.left, this.right);
        this.op = Operation.Times;
        this.left=left;
        this.right=f1;
    }

    /**
     * Function that divid the left function to the right function.
     * @param f1 the complex_function which will be divid this complex_function.
     */
    public void div(function f1){
        ComplexFunction left= new ComplexFunction(this.op, this.left, this.right);
        this.op = Operation.Divid;
        this.left=left;
        this.right=f1;
    }

    /**
     * Function that compare the left function with thw right function and find the maximum between them.
     * @param f1 the complex_function which will be compared with this complex_function - to compute the maximum.
     */
    public void max(function f1){
        ComplexFunction left= new ComplexFunction(this.op, this.left, this.right);
        this.op = Operation.Max;
        this.left=left;
        this.right=f1;
    }

    /**
     * Function that compare the left function with thw right function and find the minimum between them.
     * @param f1 the complex_function which will be compared with this complex_function - to compute the minimum.
     */
    public void min(function f1){
        ComplexFunction left= new ComplexFunction(this.op, this.left, this.right);
        this.op = Operation.Min;
        this.left=left;
        this.right=f1;
    }

    /**
     * Function that make the left and the right functions to a complex function.
     * @param f1 complex function
     */
    public void comp(function f1){
        ComplexFunction left= new ComplexFunction(this.op, this.left, this.right);
        this.op = Operation.Comp;
        this.left=left;
        this.right=f1;
    }

    /**
     *
     * @return the left function.
     */
    public function left(){
        return this.left;
    }

    /**
     *
     * @return the right function.
     */
    public function right(){
        return this.right;
    }

    /**
     * Function that convert the operation to string.
     */
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

    /**
     * Function that convert the operation from string to operation type.
     */
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
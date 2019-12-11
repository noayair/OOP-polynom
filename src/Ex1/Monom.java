package Ex1;
import java.util.Comparator;

/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and b is an integer (summed a none negative).
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Noa Yair and Oriya Kronfeld
 *
 */
public class Monom implements function{
	public static final Monom ZERO = new Monom(0,0);
	public static final Monom MINUS1 = new Monom(-1,0);
	public static final double EPSILON = 0.0000001;
	public static final Comparator<Monom> _Comp = new Monom_Comperator();
	public static Comparator<Monom> getComp() {return _Comp;}
	/**
	 * create the monom by the coefficient and power
	 * @param a - coefficient value
	 * @param b - power value
	 */
	public Monom(double a, int b){
		this.set_coefficient(a);
		this.set_power(b);
	}
	/**
	 * copy constructor
	 * @param ot - new monom that gets the value of the original monom
	 */
	public Monom(Monom ot) {
		this(ot.get_coefficient(), ot.get_power());
	}
	/**
	 * getter of coefficient
	 * @return this coefficient
	 */
	public double get_coefficient() {
		return this._coefficient;
	}
	/**
	 * getter of power
	 * @return this power
	 */
	public int get_power() {
		return this._power;
	}
	/** 
	 * this method returns the derivative monom of this.
	 * @return
	 */
	public Monom derivative() {
		if(this.get_power()==0) {return getNewZeroMonom();}
		return new Monom(this.get_coefficient()*this.get_power(), this.get_power()-1);
	}
	/**
	 * function that calculate the function value at x
	 * @param x - the value of x
	 * @return the function value  at x
	 */
	public double f(double x) {
		double ans=0;
		double p = this.get_power();
		ans = this.get_coefficient()*Math.pow(x, p);
		return ans;
	}

	/**
	 * function that check if the coefficient of the monom is zero
	 *
	 */
	public boolean isZero() {return this.get_coefficient() == 0;}

	/**
	 * Monom(String) function that make monom from String That the user puts in
	 *
	 */
	public Monom(String s) {
		try {
			int t = s.indexOf('x');
			if (t == -1) {
				set_coefficient(Double.parseDouble(s));
				set_power(0);
			}else if (t == 0){
				set_coefficient(1);
				if (s.charAt(s.length()-1) == 'x') {
					set_power(1);
				}else {
					set_power(Integer.parseInt(s.substring(t+2)));
				}
			}else {
				if (s.charAt(0) == '-' && s.charAt(1) == 'x') {
					set_coefficient(-1);
				}else {
					set_coefficient(Double.parseDouble(s.substring(0, t)));
				}
				if (t == s.length()-1) {
					set_power(1);
				}else {
					set_power(Integer.parseInt(s.substring(t+2)));
				}
			}
		}catch(Exception E) {
			System.out.println("Error - the monom is not correct");
		}
	}
	/**
	 * addition between two monoms that have the same power
	 * @param m the monom that we add to the original monom
	 */
	public void add(Monom m) {
		if (this.get_power() == m._power)
			set_coefficient (this.get_coefficient() + m._coefficient); 
	}
	/**
	 * multiply monom by another monom
	 * @param d - monom that we multiply with the original monom
	 */
	public void multipy(Monom d) {
		set_coefficient (this.get_coefficient() * d._coefficient);
		set_power (this.get_power() + d._power);
	}
	/**
	 * prints the polynom as a string
	 */
	public String toString() {
		if (this.get_coefficient() == 0 && this.get_power() == 0) return "0";
		String ans = this.get_coefficient() + "x^" + this.get_power();
		return ans;
	}

	@Override
	public function initFromString(String s) {
		Monom m = new Monom(s);
		function m1 = (function) m;
		return m1;
	}

	/**
	 * check if the object o(monom) and the original monom are equal
	 *
	 */
	public boolean equals(Object o) {
		if(o instanceof ComplexFunction ) return o.equals(this);
		if ((o instanceof Polynom)){
			Polynom p = (Polynom) o;
			if(p.theList.size() > 1) return false;
			Monom p2 = p.theList.getFirst();
			return p2.equals(this);
		}
		Monom m = (Monom) o;
		if (m.get_power() != this.get_power()) return false;
		if (m.get_coefficient() == 0 && this.get_coefficient() == 0) return true;
		if ((Math.abs(this.get_coefficient() - m.get_coefficient())) > Monom.EPSILON) return false;
		return true;
	}

	/**
	 * copy the original monom to a new monom(m).
	 *
	 */
	public function copy() {
		Monom m = new Monom(0,0);
		m._coefficient = this._coefficient;
		m._power = this._power;
		return m;
	}
	/**
	 * setter of coefficient
	 * @param a - the new value of the coefficient
	 */
	private void set_coefficient(double a){
		this._coefficient = a;
	}
	/**
	 * setter of power
	 * @param p - the new value of the power
	 */
	private void set_power(int p) {
		if(p<0) {throw new RuntimeException("ERR the power of Monom should not be negative, got: "+p);}
		this._power = p;
	}
	private static Monom getNewZeroMonom() {return new Monom(ZERO);}
	private double _coefficient; 
	private int _power;

}

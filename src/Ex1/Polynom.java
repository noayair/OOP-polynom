package Ex1;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;


//import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 *
 * @author Noa Yair and Oriya Kronfeld
 *
 */
public class Polynom implements Polynom_able{

	LinkedList<Monom> theList;

	/**
	 * empty constructor
	 */
	public Polynom() {
		LinkedList<Monom> temp = new LinkedList<Monom>();
		theList = temp;
	}
	/**
	 * polynom consreuctor that get polynom in type string and convert it to a list of monoms
	 * @param s - string that we convert to polynom
	 */
	public Polynom(String s) {
		int j = 0;
		LinkedList<Monom> list = new LinkedList<Monom>();
		for (int i = 0; i < s.length();) { // create a new linkedlist of monoms
			if ((s.charAt(i) == '-' || s.charAt(i) == '+') && i != 0) {
				Monom m = new Monom(s.substring(j, i));
				j = i;
				list.add(m);
				i++;
			}else {
				i++;
			}
		}
		if (s.charAt(j) == '+') {
			j++;
		}
		Monom m = new Monom (s.substring(j));
		list.add(m);
		theList = list;
		this.organize();
		this.sort();
	}
	/**
	 *  function that sorts the polynom in the correct order
	 */
	public void sort() { //sort the polynom
		Comparator<Monom> sort = new Monom_Comperator();
		this.theList.sort(sort);
	}
	/**
	 * function that connects monoms with the same power if necessary
	 */
	public void organize() {
		for (int i = 0; i < theList.size(); i++) {
			for (int j = i+1; j < theList.size();) {
				if (theList.get(i).get_power() == theList.get(j).get_power()) {
					theList.get(i).add(theList.get(j));
					theList.remove(j);
					j++;
				}else {
					j++;
				}
			}
			if (theList.get(i).get_coefficient() == 0 && !this.isZero()) {
				theList.remove(i);
			}
		}
	}
	/**
	 *  function that calculate the function value at x
	 * @return the function value  at x
	 */
	@Override
	public double f(double x) {
		double j = 0;
		for (int i = 0; i < theList.size(); i++) {
			Monom m = this.theList.get(i);
			double t = m.f(x);
			j += t;
		}
		return j;
	}
	/**
	 * addition between polynom and monom
	 * @param m1 - the monom we add to the original polynom
	 */
	public void add(Monom m1) { //add monom to polynom
		this.theList.add(m1);
		this.organize();
		this.sort();
	}
	/**
	 * addition between two polynoms
	 * @param p1 - the polynom we add to the original polynom
	 */
	@Override
	public void add(Polynom_able p1) { //add polynom to polynom
		Iterator<Monom> runner = p1.iteretor();
		while(runner.hasNext()) {
			Monom temp = runner.next();
			this.add(temp);
		}
		this.organize();
		this.sort();
	}
	/**
	 * substract polynom from polynom
	 * @param p1 - the polynom we substract from the original polynom
	 */

	public void substract(Polynom_able p1) {
		Monom minus = new Monom ("-1");
		LinkedList<Monom> list = new LinkedList<Monom>();
		Monom m = new Monom (0,0);
		list.add(m);
		if (this.equals(p1)) {
			this.theList = list;
		}
		Polynom_able p = p1.copy();
		p.multiply(minus);
		this.add(p);
		this.organize();
		this.sort();
	}

	public void multiply(Monom m1) { //polynom*monom
		Iterator<Monom> runner = this.theList.iterator();
		while(runner.hasNext()) {
			Monom temp = runner.next();
			temp.multipy(m1);
		}
		this.organize();
		this.sort();
	}
	/**
	 * multiply polynom with polynom
	 * @param p1 - the polynom we multiply with the original polynom
	 */
	@Override
	public void multiply(Polynom_able p1) { //polynom*polynom
		LinkedList<Monom> list = new LinkedList<Monom>(); //new list
		Iterator<Monom> runnerp1 = p1.iteretor(); //iterator for p1
		while(runnerp1.hasNext()) { //while theList is not over
			Monom temp1 = runnerp1.next(); //save the monom in new temp
			Polynom_able copy = this.copy();
			Iterator<Monom> runnerCopy = copy.iteretor(); //iterator for theList
			while (runnerCopy.hasNext()) { //same with p1
				Monom temp = runnerCopy.next();
				temp.multipy(temp1); //
				list.add(temp);
			}
		}
		this.theList = list;
		this.organize();
		this.sort();
	}
	/**
	 * check if the excepted polynom is equal to the original polynom
	 * @param o - the object(polynom) we check if is equal to the original polynom
	 * @return true for equal, false for not equal
	 */
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Polynom_able)) return  false;
		Polynom p1 = (Polynom) o;
		Iterator<Monom> runner = this.theList.iterator();
		Iterator<Monom> runnerp1 = p1.iteretor();
		while(runner.hasNext() && runnerp1.hasNext()) {
			Monom temp = runner.next();
			Monom temp1 = runnerp1.next();
			if (!temp.equals(temp1)) return false;
		}
		return true;
	}
	/**
	 * check if the polynom is zero
	 * @return true for zero, false for not zero
	 */
	@Override
	public boolean isZero() {
		Iterator<Monom> runner = this.theList.iterator();
		while(runner.hasNext()) {
			Monom temp = runner.next();
			if (!temp.isZero()) return false;
		}
		return true;
	}
	/**
	 * operate the bisection method on the polynom
	 * @param x0 - the x under the x-axis
	 * @param x1 - the x above the x-axis
	 * @param eps - the epsilon step value
	 * @return the root of the function
	 */

	@Override
	public double root(double x0, double x1, double eps) {
		if(this.f(x0) * this.f(x1) > 0) throw new RuntimeException();
		if ( x0 > x1) {
			double temp1 = x0;
			x0 = x1;
			x1 = temp1;
		}
		double mid = 0;
		while (x1 - x0 >= eps) {
			mid = (x0 + x1)/2;
			if (this.f(mid) * this.f(x0) == 0) {
				return mid;
			}
			else if (this.f(mid) * this.f(x0) > 0) {
				x0 = mid;
			}else {
				x1 = mid;
			}
		}
		return mid;
	}
	/**
	 * create a new polynom that identical to the original polynom
	 * @return the new polynom
	 */
	@Override
	public Polynom_able copy() { //copy
		Polynom new1 = new Polynom();
		for (int i = 0; i < theList.size(); i++) {
			new1.add(new Monom (this.theList.get(i)));
		}
		return new1;

	}
	/**
	 * the derivative of the polynom
	 * @return the derivative of the polynom
	 */
	@Override
	public Polynom_able derivative() {
		Polynom new1 = new Polynom();
		Iterator<Monom> runner = this.theList.iterator();
		while(runner.hasNext()) {
			Monom temp = runner.next();
			if (temp.get_power() == 0) {
				Monom newMonom = new Monom (0,0);
				new1.add(newMonom);
			}else {
				Monom newMonom = new Monom((temp.get_coefficient() * temp.get_power()),temp.get_power()-1);
				new1.add(newMonom);
			}
		}
		new1.organize();
		new1.sort();
		return new1;
	}
	/**
	 *  Compute Riemann's Integral over this Polynom starting from x0, till x1 using eps size steps
	 *  @param x0 - the start point of the range
	 *  @param x1 - the end point of the range
	 *  @param eps - the epsilon step value
	 *  @return the approximated area above the x-axis below this Polynom and between the [x0,x1] range
	 */
	@Override
	public double area(double x0, double x1, double eps) {
		if ( x0 > x1) {
			double temp1 = x0;
			x0 = x1;
			x1 = temp1;
		}
		double ans = 0;
		double temp2 = x0 + eps;
		while (temp2 < x1) {
			ans += this.f(temp2) *eps;
			temp2 += eps;
		}
		return ans;
	}
	/**
	 * creates a pointer to the polynom's monoms
	 * @return an Iterator (of Monoms) over this Polynom
	 */

	@Override
	public Iterator<Monom> iteretor() {
		Iterator<Monom> runner = this.theList.iterator();
		return runner;
	}
	@Override
	/**
	 * prints the polynom as a string
	 */
	public String toString() {
		Iterator<Monom> runner = this.theList.iterator();
		String ans = "";
		while(runner.hasNext()) {
			Monom temp = runner.next();
			if (temp.get_coefficient() > 0) {
				if (ans == "") {
					ans = temp.toString();
				} else {
					ans += "+";
					ans += temp.toString();
				}
			} else {
				ans += temp.toString();
			}
		}
		return ans;
	}

	@Override
	public function initFromString(String s) {
		Polynom p = new Polynom(s);
		function p1 = (function) p;
		return p1;
	}
}
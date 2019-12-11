package Ex1Testing;

import Ex1.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexFunctionTest {

    @Test
    public void f() {
        Polynom f1 = new Polynom("5x^2+3");
        Monom f2 = new Monom("-x");
        ComplexFunction cf1 = new ComplexFunction(Operation.Plus, f1 , f2);
        ComplexFunction cf2 = new ComplexFunction(Operation.Times , cf1 , f2);
        ComplexFunction cf3 = new ComplexFunction(Operation.Max , cf1 , cf2);
        assertEquals(123 , cf1.f(5) , 0.00000001);
        assertEquals(-135 , cf2.f(3) , 0.00000001);
        assertEquals(4.75 , cf3.f(-0.5) , 0.0000001);
    }

    @Test
    public void initFromString() {
        ComplexFunction cf1 = new ComplexFunction(new Monom(0,0));
        ComplexFunction cf2 = new ComplexFunction(new Monom(0,0));
        ComplexFunction cf3 = new ComplexFunction(new Monom(0,0));
        String s1 = "3+4x-5x^6";
        String s2 = "mul(1.4,5x+3x^2)";
        String s3 = "min(min(div(plus(x,2x^2),3x^3),4x^4),5x^5)";
        String s4 = "div(plus(x,2x^2),3x^3)";
        ComplexFunction c1 = new ComplexFunction(Operation.Plus , new Monom("x") , new Monom("2x^2"));
        ComplexFunction c2 = new ComplexFunction(Operation.Divid , c1 , new Monom("3x^3"));
        ComplexFunction c3 = new ComplexFunction(Operation.Min , c2 , new Monom("4x^4"));
        ComplexFunction c4 = new ComplexFunction(Operation.Min , c3 , new Monom("5x^5"));
        assertEquals(new ComplexFunction(Operation.None , new Polynom(s1) , null) , cf1.initFromString(s1));
        assertEquals(new ComplexFunction(Operation.Times , new Monom("1.4") , new Polynom("5x+3x^2")) , cf2.initFromString(s2));
        assertEquals(c4.toString() , cf3.initFromString(s3).toString());
    }

    @Test
    public void copy() {
        ComplexFunction cf = new ComplexFunction(Operation.Comp , new Monom("3x^3") , new Polynom("4-5x+6x^7"));
        function cf_copy = cf.copy();
        assertEquals(cf.toString() , cf_copy.toString());
    }

    @Test
    public void testEquals() {
        Polynom p = new Polynom("2x");
        ComplexFunction c = new ComplexFunction(Operation.Plus , new Monom("x") , new Monom("x"));
        Monom m = new Monom("2x");
        assertEquals(true , p.equals(c));
//        assertEquals(true , m.equals(p));
    }

    @Test
    public void plus() {
        Monom m = new Monom("4x^5");
        Polynom p = new Polynom("-5+44x");
        ComplexFunction cf1 = new ComplexFunction(Operation.Times , m , p);
        ComplexFunction cf2 = new ComplexFunction(Operation.None , p , null);
        ComplexFunction cf3 = new ComplexFunction(new Monom(0,0));
        cf3.plus(cf1);
        cf3.plus(cf2);
        assertEquals(new ComplexFunction(Operation.Plus, cf1, cf2).toString() , cf3.toString());
    }

    @Test
    public void mul() {
    }

    @Test
    public void div() {
    }

    @Test
    public void max() {
    }

    @Test
    public void min() {
    }

    @Test
    public void comp() {
    }

    @Test
    public void left() {
    }

    @Test
    public void right() {
    }
}
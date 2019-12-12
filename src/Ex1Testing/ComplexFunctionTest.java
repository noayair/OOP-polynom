package Ex1Testing;

import Ex1.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComplexFunctionTest {
    static ComplexFunction empty = new ComplexFunction(new Monom(0,0));


    @Test
    public void f() {
        Polynom f1 = new Polynom("5x^2+3");
        Monom f2 = new Monom("-x");
        ComplexFunction cf1 = new ComplexFunction("plus", f1 , f2);
        ComplexFunction cf2 = new ComplexFunction("div" , cf1 , f2);
        ComplexFunction cf3 = new ComplexFunction("max" , cf1 , cf2);
        assertEquals(123 , cf1.f(5) , 0.00000001);
        assertEquals(-15 , cf2.f(3) , 0.00000001);
        assertEquals(9.5 , cf3.f(-0.5) , 0.0000001);
    }

    @Test
    public void initFromString() {
        String s1 = "3+4x-5x^6";
        String s2 = "mul(1.4,5x+3x^2)";
        String s3 = "min(min(div(plus(x,2x^2),3x^3),4x^4),5x^5)";
        String s4 = "div(plus(x,2x^2),3x^3)";
        ComplexFunction c1 = new ComplexFunction("plus" , new Monom("x") , new Monom("2x^2"));
        ComplexFunction c2 = new ComplexFunction("div" , c1 , new Monom("3x^3"));
        ComplexFunction c3 = new ComplexFunction("min" , c2 , new Monom("4x^4"));
        ComplexFunction c4 = new ComplexFunction("min" , c3 , new Monom("5x^5"));
        assertEquals(new ComplexFunction(new Polynom(s1)) , empty.initFromString(s1));
        assertEquals(new ComplexFunction("mul" , new Monom("1.4") , new Polynom("5x+3x^2")) , empty.initFromString(s2));
        assertEquals(c4.toString() , empty.initFromString(s3).toString());
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
        ComplexFunction c1 = new ComplexFunction(Operation.Times , new Monom("x") , new Monom("x"));
        Monom m = new Monom("2x");
        assertEquals(true , p.equals(c));
        assertEquals(true , m.equals(p));
        assertNotEquals(true , c.equals(c1));
    }

    @Test
    public void plus() {
        ComplexFunction cf1 = new ComplexFunction(new Monom("4x^5"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("-5+44x"));
        ComplexFunction cf3 = new ComplexFunction(Operation.Comp , cf1 , new Polynom("8-x"));
        cf1.plus(cf2);
        cf3.plus(cf2);
        assertEquals(Operation.Plus.toString() , cf1.getOp().toString());
        assertEquals("plus(4.0x^5,-5.0x^0+44.0x^1)" , cf1.toString());
        assertEquals("plus(comp(plus(4.0x^5,-5.0x^0+44.0x^1),8.0x^0-1.0x^1),-5.0x^0+44.0x^1)" , cf3.toString());
    }

    @Test
    public void mul() {
        ComplexFunction cf1 = new ComplexFunction(new Monom("4x^5"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("-5+44x"));
        ComplexFunction cf3 = new ComplexFunction(Operation.Comp , cf1 , new Polynom("8-x"));
        cf1.mul(cf2);
        cf3.mul(cf2);
        assertEquals(Operation.Times.toString() , cf1.getOp().toString());
        assertEquals("mul(4.0x^5,-5.0x^0+44.0x^1)" , cf1.toString());
        assertEquals("mul(comp(mul(4.0x^5,-5.0x^0+44.0x^1),8.0x^0-1.0x^1),-5.0x^0+44.0x^1)" , cf3.toString());
    }

    @Test
    public void div() {
        ComplexFunction cf1 = new ComplexFunction(new Monom("4x^5"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("-5+44x"));
        ComplexFunction cf3 = new ComplexFunction(Operation.Comp , cf1 , new Polynom("8-x"));
        cf1.div(cf2);
        cf3.div(cf2);
        assertEquals(Operation.Divid.toString() , cf1.getOp().toString());
        assertEquals("div(4.0x^5,-5.0x^0+44.0x^1)" , cf1.toString());
        assertEquals("div(comp(div(4.0x^5,-5.0x^0+44.0x^1),8.0x^0-1.0x^1),-5.0x^0+44.0x^1)" , cf3.toString());
    }

    @Test
    public void max() {
        ComplexFunction cf1 = new ComplexFunction(new Monom("4x^5"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("-5+44x"));
        ComplexFunction cf3 = new ComplexFunction(Operation.Comp , cf1 , new Polynom("8-x"));
        cf1.max(cf2);
        cf3.max(cf2);
        assertEquals(Operation.Max.toString() , cf1.getOp().toString());
        assertEquals("max(4.0x^5,-5.0x^0+44.0x^1)" , cf1.toString());
        assertEquals("max(comp(max(4.0x^5,-5.0x^0+44.0x^1),8.0x^0-1.0x^1),-5.0x^0+44.0x^1)" , cf3.toString());
    }

    @Test
    public void min() {
        ComplexFunction cf1 = new ComplexFunction(new Monom("4x^5"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("-5+44x"));
        ComplexFunction cf3 = new ComplexFunction(Operation.Comp , cf1 , new Polynom("8-x"));
        cf1.min(cf2);
        cf3.min(cf2);
        assertEquals(Operation.Min.toString() , cf1.getOp().toString());
        assertEquals("min(4.0x^5,-5.0x^0+44.0x^1)" , cf1.toString());
        assertEquals("min(comp(min(4.0x^5,-5.0x^0+44.0x^1),8.0x^0-1.0x^1),-5.0x^0+44.0x^1)" , cf3.toString());
    }

    @Test
    public void comp() {
        ComplexFunction cf1 = new ComplexFunction(new Monom("4x^5"));
        ComplexFunction cf2 = new ComplexFunction(new Polynom("-5+44x"));
        ComplexFunction cf3 = new ComplexFunction(Operation.Comp , cf1 , new Polynom("8-x"));
        cf1.comp(cf2);
        cf3.comp(cf2);
        assertEquals(Operation.Comp.toString() , cf1.getOp().toString());
        assertEquals("comp(4.0x^5,-5.0x^0+44.0x^1)" , cf1.toString());
        assertEquals("comp(comp(comp(4.0x^5,-5.0x^0+44.0x^1),8.0x^0-1.0x^1),-5.0x^0+44.0x^1)" , cf3.toString());
    }
}
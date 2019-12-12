package Ex1Testing;

import Ex1.Monom;
import Ex1.Polynom;
import Ex1.Polynom_able;
import org.junit.Test;

import static org.junit.Assert.*;

public class PolynomTest {

    @Test
    public void f() {
        Polynom p1 = new Polynom("x");
        Polynom p2 = new Polynom("3.5-7x");
        Polynom p3 = new Polynom("-x+5x^2-3.8x^3");
        Polynom p4 = new Polynom("9");
        assertEquals(17, p1.f(17), 0.000001);
        assertEquals(-24.5, p2.f(4), 0.000001);
        assertEquals(-12.3999999, p3.f(2), 0.00001);
        assertEquals(9, p4.f(30), 0 / 00001);

    }

    @Test
    public void add() {
        Polynom p1 = new Polynom("x");
        Polynom p2 = new Polynom("3.5-7x");
        Polynom p3 = new Polynom("-x+5x^2-3.8x^3");
        Monom m1 = new Monom("8");
        Monom m2 = new Monom("9.5x");
        Monom m3 = new Monom("-x^4");
        p1.add(m1);
        p2.add(m2);
        p3.add(m3);
        assertEquals("8.0x^0+1.0x^1", p1.toString());
        assertEquals("3.5x^0+2.5x^1", p2.toString());
        assertEquals("-1.0x^1+5.0x^2-3.8x^3-1.0x^4", p3.toString());
    }

    @Test
    public void testAdd() {
        Polynom p1 = new Polynom("x");
        Polynom p2 = new Polynom("3.5-7x");
        Polynom p3 = new Polynom("-x+5x^2-3.8x^3");
        Polynom p4 = new Polynom("9");
        p1.add(p2);
        p2.add(p3);
        p4.add(p1);
        assertEquals("3.5x^0-6.0x^1", p1.toString());
        assertEquals("3.5x^0-8.0x^1+5.0x^2-3.8x^3", p2.toString());
        assertEquals("12.5x^0-6.0x^1", p4.toString());
    }

    @Test
    public void substract() {
        Polynom p1 = new Polynom("x");
        Polynom p2 = new Polynom("3.5-7x");
        Polynom p3 = new Polynom("-x+5x^2-3.8x^3");
        Polynom p4 = new Polynom("9");
        p1.substract(p2);
        p2.substract(p3);
        p3.substract(p4);
        assertEquals("-3.5x^0+8.0x^1", p1.toString());
        assertEquals("3.5x^0-6.0x^1-5.0x^2+3.8x^3", p2.toString());
        assertEquals("-9.0x^0-1.0x^1+5.0x^2-3.8x^3", p3.toString());
    }

    @Test
    public void multiply() {
        Polynom p1 = new Polynom("x");
        Polynom p2 = new Polynom("3.5-7x");
        Polynom p3 = new Polynom("-x+5x^2-3.8x^3");
        Monom m1 = new Monom("8");
        Monom m2 = new Monom("9.5x");
        Monom m3 = new Monom("-x^4");
        p1.multiply(m1);
        p2.multiply(m2);
        p3.multiply(m3);
        assertEquals("8.0x^1", p1.toString());
        assertEquals("33.25x^1-66.5x^2", p2.toString());
        assertEquals("1.0x^5-5.0x^6+3.8x^7", p3.toString());
    }

    @Test
    public void testMultiply() {
        Polynom p1 = new Polynom("x");
        Polynom p2 = new Polynom("3.5-7x");
        Polynom p3 = new Polynom("-x+5x^2-3.8x^3");
        Polynom p4 = new Polynom("9");
        p1.multiply(p2);
        p3.multiply(p4);
        assertEquals("3.5x^1-7.0x^2", p1.toString());
        assertEquals("-9.0x^1+45.0x^2-34.199999999999996x^3", p3.toString());
    }

    @Test
    public void testEquals() {
        Polynom p1 = new Polynom("x");
        Polynom p2 = new Polynom("3.5-7x");
        Polynom p3 = new Polynom("3.5-7x");
        assertEquals(true, p2.equals(p3));
        assertEquals(false, p1.equals(p2));
    }

    @Test
    public void isZero() {
        Polynom p1 = new Polynom("0");
        Polynom p2 = new Polynom("5x+4x^3");
        assertEquals(true, p1.isZero());
        assertEquals(false, p2.isZero());
    }

    @Test
    public void root() {
        Polynom p1 = new Polynom("2x+x^2");
        assertEquals(-2, p1.root(-3, -1, 0.000001), 0.000001);

    }

    @Test
    public void copy() {
        Polynom p1 = new Polynom("2x+x^2");
        Polynom_able p2 = p1.copy();
        assertEquals(new Polynom("2x+x^2"), p2);
    }

    @Test
    public void derivative() {
        Polynom p1 = new Polynom("x");
        Polynom p2 = new Polynom("3.5-7x");
        Polynom p3 = new Polynom("-x+5x^2-3.8x^3");
        Polynom p4 = new Polynom("9");
        assertEquals(new Polynom("1"), p1.derivative());
        assertEquals(new Polynom("-7"), p2.derivative());
        assertEquals(new Polynom("-1+10x^1-11.4x^2"), p3.derivative());
        assertEquals(new Polynom("0"), p4.derivative());

    }

    @Test
    public void area() {
        Polynom p1 = new Polynom("2x+x^2");
        assertEquals(70.66666313769439, p1.area(6, 4, 0.0000001), 0.00001);
    }

}
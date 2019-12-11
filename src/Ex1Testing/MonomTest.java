package Ex1Testing;

import Ex1.Monom;
import org.junit.Test;

import static org.junit.Assert.*;

public class MonomTest {

    @Test
    public void derivative() {
        Monom m1 = new Monom ("8");
        Monom m2 = new Monom ("9.5x");
        Monom m3 = new Monom ("-x^4");
        assertEquals(new Monom ("0") , m1.derivative());
        assertEquals(new Monom ("9.5") , m2.derivative());
        assertEquals(new Monom ("-4x^3") ,m3.derivative());

    }

    @Test
    public void f() {
        Monom m1 = new Monom ("10x");
        Monom m2 = new Monom ("4x^2");
        Monom m3 = new Monom ("2x^4");
        assertEquals(30, m1.f(3), 0.00001);
        assertEquals(100 , m2.f(5), 0.00001);
        assertEquals(32 , m3.f(2), 0.00001);
    }

    @Test
    public void isZero() {
        Monom m1 = new Monom ("0");
        Monom m2 = new Monom ("5x");
        assertEquals(true , m1.isZero());
        assertEquals(false , m2.isZero());
    }

    @Test
    public void add() {
        Monom m1 = new Monom ("5x");
        Monom m2 = new Monom ("10x");
        Monom m3 = new Monom ("40x^3");
        Monom m4 = new Monom ("2.5x^3");
        m1.add(m2);
        m2.add(m3);
        m3.add(m4);
        assertEquals("15.0x^1",m1.toString());
        assertEquals("10.0x^1",m2.toString());
        assertEquals("42.5x^3",m3.toString());
    }

    @Test
    public void multipy() {
        Monom m1 = new Monom("0");
        Monom m2 = new Monom ("9.5x");
        Monom m3 = new Monom ("4x^2");
        Monom m4 = new Monom ("40x^3");
        m1.multipy(m2);
        m2.multipy(m3);
        m3.multipy(m4);
        m4.multipy(m1);
        assertEquals("0.0x^1",m1.toString());
        assertEquals("38.0x^3",m2.toString());
        assertEquals("160.0x^5",m3.toString());
        assertEquals("0.0x^4",m4.toString());

    }

    @Test
    public void testEquals() {
        Monom m1 = new Monom("9.5x");
        Monom m2 = new Monom ("9.5x");
        Monom m3 = new Monom ("4x^2");
        assertEquals(true,m1.equals(m2));
        assertEquals(false,m2.equals(m3));
    }

    @Test
    public void copy() {
        Monom m1 = new Monom("43.8x^6");
        assertEquals(new Monom("43.8x^6"),m1.copy());
    }
}
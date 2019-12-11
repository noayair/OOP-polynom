package Ex1;

import java.awt.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class check {
    public static final Range RX=new Range(-10,10);
    public static final Range RY=new Range(-5,10);

    public static void main(String[] args)
    throws IOException
    {
//        Polynom p = new Polynom("x");
//        Polynom p1 = new Polynom("2x");
//        Polynom p2 = new Polynom("3x");
//        ComplexFunction c = new ComplexFunction(p,"plus",p1);
//        ComplexFunction c2 = new ComplexFunction(p,"plus",p);
//        ComplexFunction c1= new ComplexFunction(c,"times",p2);
//        boolean b =p1.equals(c2);
//        System.out.println(c1.toString());
//        System.out.println(c1.copy().toString());
       // System.out.println(b);
        // String s ="max(max(max(max(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),plus(div(+1.0x+1.0,mul(mul(+1.0x+3.0,+1.0x-2.0),+1.0x-4.0)),2.0)),div(plus(-1.0x^4+2.4x^2+3.1,+0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)),-1.0x^4+2.4x^2+3.1),+0.1x^5-1.2999999999999998x+5.0)";

        //  String s = "min(min(x^2,min(4x+5,-2x)),2x+3)";
        //EX1.function c = new EX1.ComplexFunction();
        //      c.initFromString("min(min(x^2,min(4x+5,-2x)),2x+3)");
        //       System.out.println(c.f(-9));
//       complexFunction c = new complexFunction(p,p1,"plus");
//       complexFunction c1 = new complexFunction(c,p2,"plus");
//       System.out.println(c1.toString());
        //System.out.println(c.toString());


       //ComplexFunction x = new ComplexFunction();
//       function x = new ComplexFunction(new Monom(0,0));
//       x = x.initFromString("div(plus(x,2x),3x)");
       // System.out.println(x.toString());
        //complexFunction y = null;
        //y = x.initFromString("plus(x,2x)");
//       String s = "div(plus(x,2x),3x)";
//       EX1.function y = new complexFunction(s);
//       System.out.println(y.toString());
       // LinkedList<function> List = new LinkedList();
//        ComplexFunction cf = new ComplexFunction(new Monom(0,0));
//        Functions_GUI fg = new Functions_GUI();
//        fg.add(new Polynom("2x+3x^2"));
//        fg.add(new ComplexFunction(cf.initFromString("plus(2x,3)")));
        //fg.saveToFile("blabla.txt");
//        fg.initFromFile("C:/Users/Yair/Desktop/function_file.txt");
//        fg.drawFunctions(1000,600,RX,RY,200);
 //       fg.drawFunctions("C:/Users/Yair/Desktop/GUI_params (1).txt");
//        Iterator<function> itr = fg.List.iterator();
//        while(itr.hasNext()){
//            System.out.println(itr.next().toString());
//        }
        //System.out.println(fg.toString());
        // number of line segments to plot
        int n = 100;
        double maxY = 2.0, minY = -2.0;

        // the function y = sin(4x), sampled at n+1 points
        // between x = 0 and x = pi
        double[] x = new double[n+1];
        double[] y = new double[n+1];
        for (int i = 0; i <= n; i++) {
            x[i] = Math.PI * i / n;
            y[i] = Math.sin(4*x[i]);
        }
        // rescale the coordinate system
        StdDraw.setXscale(0, Math.PI);
        StdDraw.setYscale(minY, maxY);

        //////// vertical lines
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        for (int i = 0; i <= n; i=i+10) {
            StdDraw.line(x[i], minY, x[i], maxY);
        }
        //////// horizontal  lines
        for (double i = minY; i <= maxY; i=i+0.5) {
            StdDraw.line(0, i, Math.PI, i);
        }
        //////// x axis
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(0, y[n/2], Math.PI, y[n/2]);
        StdDraw.setFont(new Font("TimesRoman", Font.BOLD, 15));
        for (int i = 0; i <= n; i=i+10) {
            StdDraw.text(x[i]-0.07, -0.07, Integer.toString(i-n/2));
        }
        //////// y axis
        StdDraw.line(x[n/2], minY, x[n/2], maxY);
        for (double i = minY; i <= maxY; i=i+0.5) {
            StdDraw.text(x[n/2]-0.07, i+0.07, Double.toString(i));
        }

        // plot the approximation to the function
        for (int i = 0; i < n; i++) {
            StdDraw.line(x[i], y[i], x[i+1], y[i+1]);
        }
        StdDraw.setPenColor(Color.RED);
        StdDraw.setPenRadius(0.01);
        StdDraw.point(x[n/2], 1);
    }

}

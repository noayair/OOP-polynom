package Ex1;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * This class can hold a collection of functions,
 * write and read the functions to file and show them on GUI.
 *
 * @author Noa Yair and Oriya Kronfeld
 */
public class Functions_GUI implements functions {
    public LinkedList<function> List;
    private ComplexFunction f = new ComplexFunction(new Monom(0,0));
    public static Color[] Colors = {Color.blue, Color.cyan,
            Color.MAGENTA, Color.ORANGE, Color.red, Color.GREEN, Color.PINK};

    /**
     * Constructor
     */
    public Functions_GUI (){
        this.List = new LinkedList<function>();
    }

    /**
     * Read from file.
     * @param file - the file name
     */
    @Override
    public void initFromFile(String file) throws IOException {
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null) {
            //System.out.println(line);
            function f = new ComplexFunction(new Monom(0,0));
            f = f.initFromString(line);
            add(f);
        }
    }

    /**
     * Save to file.
     * @param file - the file name
     */
    @Override
    public void saveToFile(String file) throws IOException {
        Iterator<function> it = this.List.iterator();
        while (it.hasNext()){
            String s = it.next().toString();
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(s + "\n");
            writer.close();
        }
    }

    /**
     * Function that get parameters and draw the functions.
     * @param width - the width of the window - in pixels
     * @param height - the height of the window - in pixels
     * @param rx - the range of the horizontal axis
     * @param ry - the range of the vertical axis
     * @param resolution - the number of samples with in rx: the X_step = rx/resulution
     */
    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {
        StdDraw.setCanvasSize(width, height);
        int size = this.List.size();
        double[] x = new double[resolution+1];
        double[][] yy = new double[size][resolution+1];
        double x_step = (rx.get_max()-rx.get_min())/resolution;
        double x0 = rx.get_min();
        for (int i=0; i<=resolution; i++) {
            x[i] = x0;
            for(int a=0;a<size;a++) {
                yy[a][i] = this.List.get(a).f(x[i]);
            }
            x0+=x_step;
        }
        StdDraw.setXscale(rx.get_min(), rx.get_max());
        StdDraw.setYscale(ry.get_min(), ry.get_max());
        for(int a=0;a<size;a++) {
            int c = a%Colors.length;
            StdDraw.setPenColor(Colors[c]);
            System.out.println(a+") "+Colors[a]+"  f(x)= "+this.List.get(a));
            for (int i = 0; i < resolution; i++) {
                StdDraw.line(x[i], yy[a][i], x[i+1], yy[a][i+1]);
            }
        }
        StdDraw.setPenColor(Color.LIGHT_GRAY);
        for(double d = ry.get_min(); d < ry.get_max(); d++){
            StdDraw.line(rx.get_min() , d , rx.get_max() , d);
        }
        for(double d = rx.get_min(); d < rx.get_max(); d++){
            StdDraw.line(d , ry.get_min() , d , ry.get_max());
        }
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.005);
        StdDraw.line(0 , ry.get_max() , 0 , ry.get_min());
        StdDraw.line(rx.get_max() , 0 , rx.get_min() , 0);
        StdDraw.setPenRadius(0.010);
        String s = "";
        for(int i = (int)ry.get_min(); i < ry.get_max(); i++){
            s = "" + i;
            StdDraw.text(-0.5 , i , s);
        }
        for(int i = (int)rx.get_min(); i < rx.get_max(); i++){
            s = "" + i;
            StdDraw.text(i , -0.5 , s);
        }
    }

    /**
     * Function that read the parameters from a json file.
     * @param json_file - the file with all the parameters for the GUI window.
     */
    @Override
    public void drawFunctions(String json_file) {
        Gson g = new Gson();
        try {
            FileReader our_file = new FileReader(json_file);
            Parameters par = g.fromJson(our_file , Parameters.class);
            Range rx = new Range(par.getRange_X()[0] , par.getRange_X()[1]);
            Range ry = new Range(par.getRange_Y()[0] , par.getRange_Y()[1]);
            drawFunctions(par.getWidth() , par.getHeight() , rx , ry , par.getResolution());
        } catch (FileNotFoundException | IllegalArgumentException | JsonSyntaxException | JsonIOException e) {
            drawFunctions(2000 , 1000 , new Range(-15 , 15) , new Range(-15 , 15) , 1000);
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return this.List.size();
    }

    @Override
    public boolean isEmpty() {
        return this.List.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return this.List.contains(o);
    }

    @Override
    public Iterator<function> iterator() {
        return this.List.iterator();
    }

    @Override
    public Object[] toArray() {
        return this.List.toArray();
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return  (T[]) this.List.toArray(ts);
    }

    @Override
    public boolean add(function function) {
        return this.List.add(function);
    }

    @Override
    public boolean remove(Object o) {
        return this.List.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return this.List.containsAll(collection);
    }

    @Override
    public boolean addAll(Collection<? extends function> collection) {
        return this.List.addAll(collection);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return this.List.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return this.List.retainAll(collection);
    }

    @Override
    public void clear() {
    this.List.clear();
    }
}

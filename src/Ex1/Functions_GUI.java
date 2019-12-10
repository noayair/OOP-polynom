package Ex1;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.io.*;

public class Functions_GUI implements functions {
    public LinkedList<function> List;
    static ComplexFunction f = new ComplexFunction(new Monom(0,0));

    public Functions_GUI (){
        this.List = new LinkedList<function>();
    }
    @Override
    public void initFromFile(String file) throws IOException {
        //this.List.clear();
        /*
        java.util.List<String> sList = null;

        try {
            sList = Files.readAllLines(Paths.get(file));
        } catch (Exception e){

        }
        this.List.clear();
        for (int i = 0; i < sList.size(); i++) {
            this.List.add(f.initFromString(sList.get(i)));
        }
        */
        File ourFile = new File(file);
        Scanner scan = new Scanner(ourFile);
        String s ="";
        while(scan.hasNext()){
            s=scan.nextLine();
                this.List.add(f.initFromString(s));
        }


//        String line;
//        BufferedReader reader = new BufferedReader(new FileReader(file));
//        while ((line = reader.readLine()) != null) {
//            System.out.println(line);
//            function f = (function) new ComplexFunction(new Monom(0,0));
//            f = f.initFromString(line);
//            add(f);
//        }
    }

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

//        File ourFile = new File(file);
//        Iterator it = List.listIterator();
//        while(it.hasNext()){
//
//        }

    @Override
    public void drawFunctions(int width, int height, Range rx, Range ry, int resolution) {

    }

    @Override
    public void drawFunctions(String json_file) {

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
        return new Object[0];
    }

    @Override
    public <T> T[] toArray(T[] ts) {
        return null;
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

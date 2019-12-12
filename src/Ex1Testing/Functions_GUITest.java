package Ex1Testing;

import Ex1.ComplexFunction;
import Ex1.Functions_GUI;
import Ex1.Monom;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class Functions_GUITest {
    public static String[] s = {
            "plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x+5.0)",
            "plus(div(1.0x+1.0,mul(mul(1.0x+3.0,1.0x-2.0),1.0x-4.0)),2.0)",
            "div(plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)",
            "-1.0x^4+2.4x^2+3.1",
            "0.1x^5-1.2999999999999998x+5.0",
            "max(max(max(max(plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x+5.0),plus(div(1.0x+1.0,mul(mul(1.0x+3.0,1.0x-2.0),1.0x-4.0)),2.0)),div(plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)),-1.0x^4+2.4x^2+3.1),0.1x^5-1.2999999999999998x+5.0)",
            "min(min(min(min(plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x+5.0),plus(div(1.0x+1.0,mul(mul(1.0x+3.0,1.0x-2.0),1.0x-4.0)),2.0)),div(plus(-1.0x^4+2.4x^2+3.1,0.1x^5-1.2999999999999998x+5.0),-1.0x^4+2.4x^2+3.1)),-1.0x^4+2.4x^2+3.1),0.1x^5-1.2999999999999998x+5.0)"
    };

    @Test
    public void init_save_drawTest() throws IOException {
        Functions_GUI fg1 = new Functions_GUI();
        Functions_GUI fg2 = new Functions_GUI();
        ComplexFunction cf = new ComplexFunction(new Monom(0,0));
        for (int i = 0; i < s.length; i++) {
            fg1.add(cf.initFromString(s[i]));
        }
        fg1.saveToFile("our_file.txt");
        fg2.initFromFile("our_file.txt");
        for (int i = 0; i < fg1.size(); i++) {
            assertEquals(fg1.List.get(i).toString() , fg2.List.get(i).toString());
        }
        fg2.drawFunctions("GUI_params .txt");
    }
}
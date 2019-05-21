import java.io.FileNotFoundException;
import java.util.ArrayList;

public class main {

    public static void main(String[] args) throws InterruptedException {

    	try {
			Parser parser = new Parser();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}

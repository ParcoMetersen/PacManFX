package map;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import components.Entity;

public class levelLoarder {

    private Entity[][] level;
    private static char[] _egdeCase = {'#', '#', '#', '#'};
    private static List<String> data = new ArrayList<>();

    public levelLoarder() {
        try {
            File myObj = new File("src/main/java/map/level.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }

            this.level = new Entity[data.size()][data.get(0).length()*2];
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
    }

    public void fillLevel() {
        for (int y = 0; y < data.size(); y++) {
            for (int x = 0; x < data.get(y).length()*2; x++) {
                this.level[y][x] = getEntityType(x, y);
            }
        }
    }

    private static int loopingX(int x) {
        if (x > data.get(0).length()-1) {
            return data.get(0).length()-1 - (x%data.get(0).length());
        } else {
            return x;
        }
    }

    private static Entity getEntityType(int x, int y) {
        char current = data.get(y).charAt(loopingX(x));
        
        
        Rectangle rec = new Rectangle(x*16, y*16, 16, 16);
        rec.setFill(Color.WHITE);
        if (current == '-') {
            rec.setFill(Color.BLACK);
        }
        else if (current == 'c') {
            rec.setFill(Color.YELLOW);
        } else if (current == 'p') {
            rec.setFill(Color.PINK);
        } else {
            char[] borders = bordering(loopingX(x), y);
            if (Arrays.compare(borders, _egdeCase) == 0) {
                rec.setFill(Color.GREEN);
            } else {
                rec.setFill(Color.BLUE);
            }
        }
        return new Entity(rec);
    }

    private static char[] bordering(int x, int y) {
        char[] bordering = new char[4];
        if(x > 0) {
            bordering[3] = data.get(y).charAt(x-1);
        } else {
            bordering[3] = '#';
        }
        if (x < data.get(y).length()-1) {
            bordering[1] = data.get(y).charAt(x+1);
        } else {
            bordering[1] = '#';
        }
        if (y > 0) {
            bordering[2] = data.get(y-1).charAt(x);
        }
        if (y < data.size()-1) {
            bordering[0] = data.get(y+1).charAt(x);
        } 

        return bordering;
    }
    private static char[] egdes(int x, int y, List<String> data) {
        char[] egdes = new char[4];
        egdes[4] = data.get(y+1).charAt(x+1);
        egdes[5] = data.get(y-1).charAt(x+1);
        egdes[6] = data.get(y-1).charAt(x-1);
        egdes[7] = data.get(y+1).charAt(x-1);

        return egdes;
    }

    public Entity[] bordering(int x, int y) {
        return new Entity[1];
    }


    public Entity[][] getLevel() {
        return this.level;
    }
    public List<String> getData() {
        return this.data;
    }
}
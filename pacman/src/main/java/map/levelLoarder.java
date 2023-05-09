package map;

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files

import components.Entity;

public class levelLoarder {

    private Entity[][] level;
    private static char[] _egdeCase = {'#', '#', '#', '#'};

    public levelLoarder() {
        try {
            File myObj = new File("src/map/level.txt");
            Scanner myReader = new Scanner(myObj);
            List<String> data = new ArrayList<>();
            while (myReader.hasNextLine()) {
                data.add(myReader.nextLine());
            }
            this.level = new Entity[data.size()][data.get(0).length()*2];
            myReader.close();

            fillLevel(data);
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } 
    }

    private static void fillLevel(List<String> data) {
        for (int y = 0; y < data.size(); y++) {
            for (int x = 0; x < data.get(y).length(); x++) {
                char current = data.get(y).charAt(x);
                if (current == 'c') {

                } else if (current == 'p') {

                } else {
                    char[] borders = bordering(x, y, data);
                    // char[] egdes = egdes(x, y, data);
                    if (Arrays.compare(borders, _egdeCase) == 0) {
                        char[] egdes = egdes(x, y, data);

                    } else {
                        if (borders[1] == '#' || borders[3] == '#') {
                            // vetical
                        } else if (borders[0] == '#' || borders[2] == '#') {
                            // horisondel
                        }
                        if (borders[0] == '#' || borders[1] == '#') {
                            // egde to the top left
                        } else if (borders[0] == '#' || borders[3] == '#') {
                            // egde to the top right
                        }
                        else if (borders[2] == '#' || borders[1] == '#') {
                            // egde to the bottom left
                        }
                        else if (borders[2] == '#' || borders[3] == '#') {
                            // egde to the bottom right
                        }
                    }
                }
            }
        }
    }

    private static Entity getWallType(int x, int y, List<String> data) {
        return new Entity();
    }

    private static char[] bordering(int x, int y, List<String> data) {
        char[] bordering = new char[4];
        bordering[0] = data.get(y+1).charAt(x);
        bordering[1] = data.get(y).charAt(x+1);
        bordering[2] = data.get(y-1).charAt(x);
        bordering[3] = data.get(y).charAt(x-1);
        
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
}
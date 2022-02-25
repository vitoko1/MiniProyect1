import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by Victor Sandoval
 */
public class GuessTheCityLogic {

    private String[] cities_array = new String[100];


    /**
     *
     */
    public GuessTheCityLogic() {
    }

    /**
     * Method to read our cities file.
     */
    public String[] ReadFile() {

        File myObj = new File("cities.txt");

        try {
            Scanner myReader = new Scanner(myObj);
            int i = 0;

            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                cities_array[i] = data;
                i++;
            }
            myReader.close();
            // String test = pickUpRandomCity(cities_array);
            // String underscoreWord = convertToUnderScore(test);// System.out.println("UNDERSCOREWORD" + underscoreWord);

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return cities_array;

    }

    /**
     * Method to select a random city from the list.
     *
     * @param cities_array
     * @return
     */
    public String pickUpRandomCity(String[] cities_array) {

        int random = new Random().nextInt(cities_array.length);
        return cities_array[random];
    }

    /**
     * Method to create our city into a underscore string
     * @param str
     * @return
     */
    public String convertToUnderScore(String str) {

        int len = str.length();

        String underscoreString = "";
        while (len > 0) {

            underscoreString = underscoreString + "_";
            len--;
        }

        return underscoreString;
    }

    /**
     * @param str
     * @return
     */
    private boolean handleScore(String str) {


        return true;
    }


    public String[] getCities_array() {
        return cities_array;
    }

    public void setCities_array(String[] cities_array) {
        this.cities_array = cities_array;
    }


}

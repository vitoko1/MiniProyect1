import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * Created by Victor Sandoval
 */
public class Question {


    private int SCORE = 10;
    private StringBuilder UNDERSCORE = new StringBuilder("");
    private String CITYTOGUESS = "";
    List<String> wrong_letters = new ArrayList<String>();

    public Question() {

    }

    /**
     * the game method (orchestrator).
     */
    public void PlayGuessTheCity() {

        GuessTheCityLogic guessTheCityLogic = new GuessTheCityLogic();

        // Starting the game.

        System.out.println("WELCOME TO GUESS THE CITY!");

        String[] arrayOfCities = guessTheCityLogic.ReadFile();
        // I'm going to work without spaces  in my city to guess
        CITYTOGUESS = guessTheCityLogic.pickUpRandomCity(arrayOfCities).trim().toLowerCase();

        UNDERSCORE = new StringBuilder(guessTheCityLogic.convertToUnderScore(CITYTOGUESS));

        AskToTheUser();
        boolean keePlaying = true;

        while (keePlaying) {
            if (CheckIfWinOrLost() == 1) {
                // it means the user lost.
                System.out.println("YOU LOSE!");
                keePlaying = false;
            } else if (CheckIfWinOrLost() == 2) {
                // it means the user lost.
                System.out.println("YOU WIN! THE CITY WAS : " + UNDERSCORE);

                keePlaying = false;
            } else {
                AskToTheUser();

            }
        }
    }

    /**
     * -Method which generate the dynamic of the game : adding letters to our wrong letters list
     * - Subtract  points and avoid subtracting some point depends on each case,
     * reveal our underscore word if the user find a letter or more than one.
     */
    private void AskToTheUser() {

        Scanner sc = new Scanner(System.in);
        System.out.println("---- : " + CITYTOGUESS);
        System.out.println("You are guessing : " + UNDERSCORE);
        System.out.print("You have guessing : " + wrong_letters.size() + " wrong letters  [");
        for (String str1 : wrong_letters) {
            System.out.print(str1 + " ");
        }
        System.out.print("]");
        System.out.println("");
        System.out.println("Type a Letter ");

        String letter = sc.nextLine().toLowerCase();
        if (InputValidator(letter)) {

            int index1 = CITYTOGUESS.toLowerCase().indexOf(letter.toLowerCase());
            // that means the letter exist at least once in our word.
            boolean test = wrong_letters.contains(letter.toLowerCase());
            if (index1 != -1) {

                List<Integer> auxArray = new ArrayList<Integer>();
                auxArray.add(index1);
                // then we have to take a look if that letter exists  in another position forward
                index1++;
                while (index1 < CITYTOGUESS.length()) {
                    int y = CITYTOGUESS.indexOf(letter, index1);
                    // it means there are no more repetitive letters in the word
                    if (y == -1) {
                        break;
                    } else {
                        // means there at least one more letter in the word
                        auxArray.add(y);
                        index1 = y;
                        index1++;
                    }
                }
                //here I have to reveal the letter guessed from the underscore word

                for (Integer i : auxArray) {
                    UNDERSCORE.setCharAt(i, letter.charAt(0));
                }

            } else if (!test) {
                // it means doesn't exist that letter in our word. We have to discount a point !
                SCORE--;
                wrong_letters.add(letter);
            }
        }

        // }


    }

    /**
     * Input Validator our edges cases
     *
     * @param str
     * @return
     */
    private boolean InputValidator(String str) {

        if (str == null && str.isEmpty()) {
            System.out.println("You have to type at least 1 letter");

            return false;
        } else if (str.length() > 1) {

            System.out.println("Only you can add 1 letter");
            return false;
        } else if (!str.matches("^[a-zA-Z]*$")) {

            // it means the user type again the same wrong character, so we don't discount point.
            if (!wrong_letters.contains(str)) {

                SCORE--;
                wrong_letters.add(str);
                System.out.println("Wrong Letter you get you have lost a point !");
            }

            System.out.println("Only are allowed Alphabetic Letters, no symbols !");
            return false;
        } else if (Contains(UNDERSCORE, str) != -1) {

            System.out.println("This Letter was guessed before! try again with a different letter ");
            return false;
        }


        return true;
    }

    /**
     * This method check if the user win lost or still having possibilities to keep playing
     *
     * @return 1= lost, 2=win,3= keep playing.
     */
    private int CheckIfWinOrLost() {

        if (SCORE == 0) {
            return 1;
        } else if (Contains(UNDERSCORE, "_") == -1) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * Method to give me a hand to use "Constains" with a StringBuffer :)
     *
     * @param sb
     * @param findString
     * @return
     */
    public static int Contains(StringBuilder sb, String findString) {

        /*
         * if the substring is found, position of the match is
         * returned which is >=0, if not -1 is returned
         */
        return sb.indexOf(findString);
    }


}

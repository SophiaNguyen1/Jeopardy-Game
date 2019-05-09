/*
Sophia Nguyen
Jeopardy
Ms. Krasteva
Jan. 16, 2018
This program runs a Jeopardy game.
This trivia game consists of two players and three levels (level 1, level 2, and Final Jeopardy).
5 random categories with 5 questions will be randomly selected for level 1 and level 2.
Each question for each category will be worth a set amount of money and will increase in difficulty as the money value increases in increments (increment of $100 for level 1 and $200 for level 2).
Each player will get their turn to choose a category. The first available question will be displayed and the player can enter their answer.
If the player answers correctly, they will receive the corresponding amount of money in their bank. If they answer it incorrectly, they will lose that amount of money.
Throughout the game, a black box return method (bankUpdate) will be called and the program will pass in the necessary arguments into its parameters so that the method can calculate the new bank value and return it to bank1 or bank2.
Double Jeopardy are special randomly chosen questions on the board that give the player the chance to bet an amount of money before they answer the question.
They can bet up to their bank value or up to the maximum money value on the board if they have less than that value in their bank ($500 for level 1 and $1000 for level 2).
If they answer correctly they will receive their bet in their bank and if they answer incorrectly they will lose that amount.
There is one hidden Double Jeopardy in level 1 and two hidden Double Jeopardy's in level 2.
After a question has been attempted, the corresponding square will disappear from the board.
The players cannot choose a category if there are no more questions in that category.
Level 1 will be finished after the players have cleared the board. The game will continue on to level 2.
Level 2 will be finished after the players have cleared the board. The game will continue on to Final Jeopardy.
In Final Jeopardy, both players must bet an amount from their bank. If they are in debt (negative bank value) they can only bet $0. Otherwise, they may bet up to their bank value.
Their bets will be covered from each other after they have entered it in.
A random Final Jeopardy question will display and each player must answer it. Each player must turn away when the other player is entering their answer. The answers will be covered.
Each player will gain or lose their amount of money bet if they get the question correct or incorrect.
The program will decide who the winner and output a congratulatory message.
The program will automatically sort the high scores (using a method) from greatest to least in a file and array after every game.
The high scores will reset every time the program is rerun. We can choose to clear the high scores file and array from the highScores screen.
We can choose to view the instructions from the instructions menu option.
We can choose to view the top ten high scores from the high scores menu option.

The first screen is the splashScreen. It displays an animation of a finger moving down to press a button.
After the animation has finished, it automatically goes to mainMenu. We cannot go back to splashScreen unless we rerun the program.
The second screen is the mainMenu. It displays menu options and we can choose what we want to do.
We can enter 1 to go to playGame, 2 to go to instructions, 3 to go to highScores, or 4 to go to goodbye.
The third screen is playGame. It executes the Jeopardy gameplay.
After the winner has been declared, we can press any key to continue back to the mainMenu. We must finish the whole Jeopardy game before we can press a key to go back to mainMenu.
The fourth screen is instructions. It displays the instructions of the game.
We can press any key to continue back to the mainMenu.
The fifth screen is highScores. It displays the top ten high scores and their corresponding names. We can choose to clear the file containing the high scores.
We can press any key to continue back to the mainMenu.
The sixth screen is goodbye. It displays ending messages.
We can press any key to close the console and exit the program.
We can use the program over and over again until we choose to exit from the mainMenu.

Variable Dictionary
Name            Type            Description/Purpose
choice          String          stores our main menu option choice (1, 2, 3, or 4)
scores          String[][]      stores the sorted high scores and corresponding names
FILE_NAME       final String    stores the name of the high scores file
name1           String          stores the name of player 1
name2           String          stores the name of player 2
bank1           int             stores player 1's bank
bank2           int             stores player 2's bank
c               Console         reference variable for console class
*/

import java.awt.*; //allows access to the java command libraries
import hsa.*; //allows access to the Console class files
import java.io.*; //allows access to the java io class

public class Jeopardy //Jeopardy class
{
    Console c; //class that allows the output window to be created
    //global variable declarations
    String choice = ""; //stores user's main menu choice
    String[] [] scores = new String [0] [2]; //stores high scores
    final String FILE_NAME = "highScores.txt"; //stores name of high scores file
    String name1 = "", name2 = ""; //stores player names
    int bank1 = 0, bank2 = 0; //stores the players' banks (the amount of money they have)

    /*
    This method is the class constructor. It creates a new Console object, gives it a name, and gives it a size.
    */
    public Jeopardy ()  //class constructor
    {
	c = new Console (30, 80, "Jeopardy"); //creates a new console and names it
    }


    /*
    This method is the title method. It clears the screen and outputs the title.
    */
    private void title ()  //title method
    {
	c.clear (); //clears screen
	c.print (' ', 36); //spacing
	c.println ("Jeopardy"); //title
	c.println (); //spacing
    }


    /*
    This method is the pauseProgram method. It waits for the user to enter any key to continue.
    */
    private void pauseProgram ()  //pauseProgram method
    {
	c.println ("Please enter any key to continue: "); //user prompt
	c.getChar (); //gets any key
    }


    /*
    This method is sortHighScores method. It reads the highScores file into the scores array, adds the 2 new scores, and sorts it all from greatest to least. It writes the updated array to the file. It also creates the highScores file if it does not exist yet.
    Variable Dictionary
    Name        Type            Description/Purpose
    input       BufferedReader  variable used to read the file
    input2      BufferedReader  variable used to read the file again
    counter     int             stores the number of lines in the file
    line        String          stores each line of the file
    output      PrintWriter     variable used to write to the file
    output2     PrintWriter     variable used to write to the file again
    maxIndex    int             stores the maximum number index while sorting array
    temp        String []       stores value for maximum number before swapping values in scores array

    The first while loop enters automatically, and only breaks when the scores have been sorted without any error. It reads the file, stores values in the array, sorts the values, and writes to the file.
    The first try catch structure tries to read the file and store the previous values into the scores array as well as the 2 new values. It also sorts the values in the array from greatest to least, then writes the new array back to the file.
    It catches the error if there is no file named highScores.txt yet, then creates the file and reruns in the while loop until there is no error.
    The second while loop adds to a counter as long as the line read in the file is not null.
    The first for loop runs from 0 to the counter, then stores the values in the file into the scores array.
    The second for loop runs from the first element to the second last element in the scores array. It sets the maxIndex value to the loop value.
    The third for loop (nested in the second one) runs from the element after the first loop element to the last element of the array. It compares each pair of values and stores the greatest number in maxIndex.
    The first if statement inside of the third for loop compares the values of each pair and changes maxIndex to the second value if it is greater than the first value.
    The fourth for loop runs the length of the array and outputs it to the file.
    The second try catch structure occurs in the catch of the first one. It tries to create a new file with the file name and catches any IOException errors.
    */
    private void sortHighScores ()  //sortHighScores method
    {
	//local variable declarations
	BufferedReader input; //buffered reader 1
	BufferedReader input2; //buffered reader 2
	int counter = 0; //counter to count the number of lines in the file
	String line = ""; //stores text in each line of the file
	PrintWriter output; //print writer 1
	PrintWriter output2; //print writer 2
	int maxIndex = 0; //stores the element number of the maximum value while comparing the values of each pair of elements in the unsorted array
	//loop
	while (true)
	{
	    //try catch structure
	    try
	    {
		//reads all of the lines of the file
		input = new BufferedReader (new FileReader (FILE_NAME));
		//while the line is not null
		while ((line = input.readLine ()) != null)
		{
		    //adds 1 to counter
		    counter++;
		}
		//closes file reader
		input.close ();

		//sets the length of scores to 2 more than the counter (old highscores and 2 new highscores)
		scores = new String [counter + 2] [2];

		//reads lines of the file again
		input2 = new BufferedReader (new FileReader (FILE_NAME));
		//loop runs from 0 to counter
		for (int i = 0 ; i < counter ; i++)
		{
		    //stores each name and score in scores array
		    //splits text before and after the space, then automatically stores the first part in scores [i][0] and the second part in scores [i][1]
		    scores [i] = (input2.readLine ()).split (" ");
		}
		//closes file reader
		input2.close ();

		//adds the new highscores and names to the array
		scores [counter] [0] = name1;  //stores player 1's name
		scores [counter] [1] = "" + bank1; //stores player 1's bank
		scores [counter + 1] [0] = name2; //stores player 2's name
		scores [counter + 1] [1] = "" + bank2; //stores player 2's bank

		//sorting the high scores from greatest to least
		//looks at each element starting from the first one, compares it to each element after that, finds the greatest value, then swaps the values
		//for loop runs through first element to second last element of the array
		for (int i = 0 ; i < scores.length - 1 ; i++)
		{
		    //find the maximum element in unsorted array
		    maxIndex = i; //sets maxIndex to current element number
		    //for loop runs through each element after the element of i
		    for (int x = i + 1 ; x < scores.length ; x++)
		    {
			//parses bank values in scores array to integers
			//if the second score is greater compared to the first score
			if (Integer.parseInt (scores [x] [1]) > Integer.parseInt (scores [maxIndex] [1]))
			{
			    //changes the maximum element to the current x loop element
			    maxIndex = x;
			}
		    }
		    //swaps the maximum element with the first element
		    String[] temp = scores [maxIndex];  //declares string array and stores second (maximum) value of swap in temporary string array
		    scores [maxIndex] = scores [i]; //swaps first value into second (maximum) value position
		    scores [i] = temp; //swaps second (maximum) value from temp into the first value position
		}

		//writes to the highscores file
		output = new PrintWriter (new FileWriter (FILE_NAME));
		//loop runs the length of the array
		for (int i = 0 ; i < scores.length ; i++)
		{
		    //prints each corresponding name/score on every line
		    output.println (scores [i] [0] + " " + scores [i] [1]);
		}
		//closes print writer
		output.close ();
		break; //exits the loop

	    }
	    catch (IOException e)  //catches error if there is no highscores file yet
	    {
		//try catch structure
		try
		{
		    output2 = new PrintWriter (new FileWriter (FILE_NAME)); //creates a new PrintWriter and FileWriter object
		    output2.print (""); //prints an empty string
		    output2.close (); //closes the file
		}
		catch (IOException ex)  //catches any IO errors
		{
		}
		//continues in the loop
	    }
	}
    }


    /*
    This method is the bankUpdate return method. It decides how much money to add or subtract from the player's bank depending on whether they answered the question correctly, the value of the question they answered, their current level, whether the question was a Double Jeopardy, and how much they bet if it was a Double Jeopardy. It returns the updated bank value back to playGame to store in either bank1 or bank2.
    Variable Dictionary (Parameter List)
    Name            Type            Description/Purpose
    bank            int             stores the bank of the player (either player 1 or 2)
    correct         boolean         stores whether the player got the question correct (true) or incorrect (false)
    doubleJ         boolean         stores whether the question was double jeopardy (true) or not (false)
    doubleJBet      int             stores the double jeopardy bet
    currentLevel    int             stores the current level (1 or 2)
    counter         int             stores the counter (value of the question)

    The first if statement looks at if the value of doubleJ (double jeopardy) is true. The first option (adding or subtracting the bet from the player's bank) executes if doubleJ is true and the second option (adding or subtracting the money value from the player's bank) executes if doubleJ is false.
    The second if statement (inside of the first if of the first if statement) looks at whether correct is true. If it is correct, the player earns their bet in the bank. Else, if it is incorrect, the player loses their bet from their bank.
    The third if statement (inside of the else of the first if statement) looks at whether correct is true. If it is correct, the player earns the corresponding money in their bank. Else, if it is incorrect, the player loses the corresponding money from their bank.
    */
    //black box return method bankUpdate
    private int bankUpdate (int bank, boolean correct, boolean doubleJ, int doubleJBet, int currentLevel, int counter)
    {
	//if the question was double jeopardy
	if (doubleJ == true)
	{
	    //if player got the question correct
	    if (correct == true)
	    {
		//player earns the amount of money bet
		bank += doubleJBet;
	    }
	    //if player got the question incorrect
	    else
	    {
		//player loses the amount of money bet
		bank -= doubleJBet;
	    }
	}
	//if the question was not double jeopardy
	else
	{
	    //if player got the question correct
	    if (correct == true)
	    {
		//player earns the corresponding amount of money in their bank
		bank += (counter * (currentLevel * 100) + (currentLevel * 100));
	    }
	    //if player got the question incorrect
	    else
	    {
		//player loses the corresponding amount of money in their bank
		bank -= (counter * (currentLevel * 100) + (currentLevel * 100));
	    }
	}
	return bank; //returns updated amount of money
    }


    /*
    This method is the winnerDeclaration return method. It decides which player won the game or if both players won the game. It returns a congratulatory message to the winner(s).
    Variable Dictionary (Parameter List)
    Name            Type            Description/Purpose
    bank1           int             stores player 1's bank
    bank2           int             stores player 2's bank

    The if statement displays that player 1 won the Jeopardy game if player 1's bank is greater than player 2's bank. It displays that player 2 won the Jeopardy game if player 2's bank is greater than player 1's bank. It displays that both players tied for the win if player 1's bank and player 2's bank is equal.
    */
    private String winnerDeclaration (int bank1, int bank2)
    {
	//if player 1 has more money in their bank
	if (bank1 > bank2)
	{
	    //congratulatory message
	    return ("Congratulations, player 1! You have won the Jeopardy game!");
	}


	//if player 2 has more money in their bank
	else if (bank2 > bank1)
	{
	    //congratulatory message
	    return ("Congratulations, player 2! You have won the Jeopardy game!");
	}


	//if player 1 and player 2 have the same amount of money in their bank
	else
	{
	    //congratulatory message
	    return ("Congratulations, player 1 and player 2! You have both won the Jeopardy game!");
	}
    }


    /*
    This method is the categoryGenerator black box return method. It generates a random category number from 0 to 19.
    */
    private int categoryGenerator ()
    {
	return ((int) (Math.random () * 20)); //returns a random number from 0 to 19
    }


    /*
    This method is the splashScreen method. It displays an animation of a finger moving down to press a button. It also creates the highScores file and designs the console.
    Variable Dictionary
    Name        Type            Description/Purpose
    output      PrintWriter     variable used to write to the file
    bgColor     Color           creates a dark blue color for the background
    handColor   Color           creates a peach color for the hand
    handColor2  Color           creates a dark tan color for the hand line

    The first try catch structure tries to create the high scores file (a blank file). It catches any IO errors.
    The first for loop runs from 0 to 250. It moves the hand animation down 250 pixels.
    The second try catch structure tries to delay the animation. It catches any errors.
    */
    public void splashScreen ()  //splashScreen method
    {
	//printwriter variable declaration
	PrintWriter output;

	//color variable declarations
	Color bgColor = new Color (0, 87, 183); //dark blue
	Color handColor = new Color (241, 194, 125); //peach
	Color handColor2 = new Color (198, 134, 66); //dark tan


	//background and text color
	c.setColor (bgColor); //dark blue
	c.fillRect (0, 0, 640, 500); //draws a dark blue background
	c.setTextBackgroundColor (bgColor); //sets background color to dark blue
	c.setTextColor (Color.white); //sets text color to white

	//creates a new, empty highscores file
	//try catch structure
	try
	{
	    output = new PrintWriter (new FileWriter (FILE_NAME)); //creates a new PrintWriter and FileWriter object
	    output.print (""); //prints an empty string
	    output.close (); //closes the file
	}
	catch (IOException e)  //catches any IO errors
	{
	}

	//graphics

	//table
	c.setColor (new Color (139, 69, 19)); //brown
	c.fillRect (0, 350, 640, 400); //table

	//buzzer
	c.setColor (Color.gray); //gray
	c.fillRect (225, 382, 180, 26); //base middle
	c.fillOval (225, 365, 180, 80); //base bottom
	c.setColor (Color.lightGray); //light gray
	c.fillOval (225, 345, 180, 80); //base top
	c.setColor (new Color (178, 26, 26)); //dark red
	c.fillRect (240, 340, 150, 30); //middle buzzer
	c.fillOval (240, 330, 150, 80); //bottom buzzer
	c.setColor (Color.red); //bright red
	c.fillOval (240, 300, 150, 80); //top buzzer
	//loop moves hand 250 pixels down
	for (int i = 0 ; i < 251 ; i++)
	{
	    //erase
	    c.setColor (bgColor); //blue
	    c.fillRect (245, -320 + i, 185, 360); //erase

	    //hand
	    c.setColor (handColor); //peach

	    //arm
	    c.fillArc (226, -320 + i, 80, 205, 285, 180); //left side of forearm
	    c.fillArc (315, -350 + i, 80, 205, 100, 180); //right side of forearm
	    c.fillRect (270, -300 + i, 80, 200); //forearm

	    //index finger
	    c.fillArc (328, -20 + i, 27, 55, 180, 180); //tip of finger
	    c.fillRect (328, -120 + i, 27, 130); //finger

	    //part between index finger and thumb
	    c.fillArc (333, -120 + i, 50, 75, 80, 180); //hand part connecting index finger to middle of index finger and thumb
	    c.fillArc (338, -140 + i, 50, 75, 70, 180); //left hand part connecting index finger and thumb
	    c.fillArc (340, -150 + i, 45, 70, 315, 180); //right hand part connecting index finger and thumb

	    //thumb
	    c.fillArc (345, -200 + i, 50, 70, 120, 180); //hand part connecting forearm to base of thumb
	    c.fillArc (370, -150 + i, 45, 70, 130, 180); //hand part in middle of thumb
	    c.fillArc (380, -115 + i, 45, 45, 150, 180); //thumb

	    //hand middle
	    c.fillOval (260, -200 + i, 115, 125); //middle of hand
	    c.fillArc (230, -190 + i, 55, 110, 245, 180); //left side of middle of hand
	    c.fillRect (250, -95 + i, 100, 30); //hand knuckles base
	    c.fillArc (245, -100 + i, 30, 40, 160, 180); //pinky finger knuckle
	    c.fillArc (272, -92 + i, 30, 40, 160, 180); //ring finger knuckle
	    c.fillArc (300, -85 + i, 30, 40, 170, 180); //middle finger knuckle
	    c.fillOval (318, -70 + i, 20, 10); //middle finger knuckle filler

	    //nail polish
	    c.setColor (new Color (150, 203, 255)); //light blue
	    c.fillArc (333, 10 + i, 18, 30, 180, 180); //index bottom
	    c.fillArc (333, 10 + i, 18, 30, 0, 180); //index top
	    c.fillArc (405, -90 + i, 25, 10, 310, 180); //thumb right
	    c.fillArc (405, -90 + i, 25, 10, 130, 180); //thumb left

	    //index finger line
	    c.setColor (handColor2);
	    c.drawLine (335, -20 + i, 348, -20 + i); //index finger line
	    //try catch structure
	    try
	    {
		Thread.sleep (5); //delay
	    }
	    catch (Exception e)
	    {
	    }

	}
    }


    /*
    This method is the mainMenu method. It displays menu options and gets our input to decide what to do next.

    The first while loop enters automatically. It gets user input for the main menu choice and only breaks if the user enters a proper choice.
    The first if statement errortraps if the user did not enter 1, 2, 3, or 4. It displays an error message, then continues in the loop.
    */
    public void mainMenu ()  //mainMenu method
    {
	title (); //calls title

	//menu options
	c.print (' ', 36);
	c.println ("Main Menu");
	c.print (' ', 34);
	c.println ("1. Play Game");
	c.print (' ', 33);
	c.println ("2. Instructions");
	c.print (' ', 33);
	c.println ("3. High Scores");
	c.print (' ', 37);
	c.println ("4. Exit");

	//while loop
	while (true)
	{
	    c.setCursor (9, 1);
	    c.print (' ', 27);
	    c.println ("Please enter 1, 2, 3, or 4: "); //user prompt
	    c.println ();
	    c.setCursor (10, 40);
	    choice = c.readLine (); //stores user input in choice
	    //if statement errortraps if user does not enter 1, 2, 3, or 4
	    if (!(choice.equals ("1") || choice.equals ("2") || choice.equals ("3") || choice.equals ("4")))
	    {
		//error message
		new Message ("You must enter 1, 2, 3, or 4.");
	    }
	    else
	    {
		break; //breaks from while loop
	    }
	}
    }


    /*
    This method is the instructions method. It displays the instructions of Jeopardy.
    */
    public void instructions ()  //instructions method
    {
	title (); //calls title
	//displays instructions
	c.println ("Instructions");
	c.println ("This program is a 2 player trivia game where you answer trivia questions to earn");
	c.println ("money. It consists of three levels (level 1, level 2, and Final Jeopardy). Each");
	c.println ("player has a bank that shows how much money they have in the game. Each level");
	c.println ("will have 5 random categories with 5 questions each of increasing difficulty and");
	c.println ("money value. Level 1 questions start at $100 and increases in increments of");
	c.println ("$100, and level 2 questions start at $200 and increase in increments of $200.");
	c.println ("Each player interchangably gets their turn to choose a category and answer the");
	c.println ("first available question in that category. If you get the question correct, you earn that amount of money in your bank. If you get it incorrect, you lose that");
	c.println ("amount of money in your bank. (Side note: please do not enter spaces after your answer otherwise it will be seen as incorrect.) Double Jeopardy is a special");
	c.println ("occurence: a random question on the board gives you the opportunity to choose");
	c.println ("how much money you want to bet from your bank. If you correctly answer the");
	c.println ("question, you will receive the money that you bet. If you incorrectly answer the");
	c.println ("question, you will lose the amount of money you bet. Level 1 will have 1 Double Jeopardy and level 2 will have 2 Double Jeopardy's. Level 1 will finish when");
	c.println ("both players have answered all of the questions on the board, and the program");
	c.println ("will continue to level 2. Level 2 will finish when both players have answered");
	c.println ("all of the questions on the board, and the program will continue onto the Final Jeopardy round. Both players must bet an amount of money from their bank, then");
	c.println ("answer a randomly selected question. Players must turn away when the other");
	c.println ("player is betting or answering. The bets and answers will be covered after they are entered. Each player will gain or lose the amount of money they bet if they get the answer correct or incorrect. The program will determine the winner.");
	c.println ();
	pauseProgram (); //calls pauseProgram
    }


    /*
    This method is the highScores method. It displays the top ten high scores. It also clears the highScores file if we choose that we want to clear it.
    Variable Dictionary
    Name            Type            Description/Purpose
    clearChoice     String          stores the user's choice to clear the high scores file
    output          PrintWriter     variable is used to write to the file

    The first if statement checks how many scores there are in the scores array. It outputs all of the scores if there are less than 10 scores. It outputs the top ten scores if there are 10 or more scores.
    The first for loop (in the first option of the first if statement) runs through all of the elements in the array. It prints out each name and score to the screen.
    The second for loop (in the second option of the first if statement) runs through the first ten elements of the scores array and outputs them.
    The first while loop runs automatically and only breaks if the user enters a valid answer for their choice to clear the file.
    The second if statement (inside of the first while loop) errortraps if the user does not enter yes or no.
    The third if statement creates a new, empty high scores file if the user chose to clear it.
    The first try catch statement tries to write a new, empty file and catches any IO errors.
    */
    public void highScores ()  //highScores method
    {
	//local variable declarations
	String clearChoice; //stores user's choice to clear the high scores file

	title (); //calls title
	c.print (' ', 31);
	c.println ("Top 10 High Scores"); //high scores header
	//if there is less than 10 high scores
	if (scores.length < 10)
	{
	    //for loop runs through all of the elements in the array
	    for (int i = 0 ; i < scores.length ; i++)
	    {
		c.print (' ', 35 - (scores [i] [0].length ()));
		c.print ((i + 1) + ". "); //displays the number
		c.println (scores [i] [0] + " $" + scores [i] [1]); //displays the names/scores
	    }
	}
	//if there is 10 or more than 10 high scores
	else
	{
	    //for loop runs through the first 10 elements of the array
	    for (int i = 0 ; i < 10 ; i++)
	    {
		c.print (' ', 35 - (scores [i] [0].length ()));
		c.print ((i + 1) + ". "); //displays the number
		c.println (scores [i] [0] + " $" + scores [i] [1]); //displays the names/scores
	    }
	}


	//while loop
	while (true)
	{
	    c.setCursor (15, 1);
	    //user prompt
	    c.print (' ', 10);
	    c.println ("Would you like to clear the file? Please enter 'yes' or 'no'.");
	    c.println ();
	    c.setCursor (16, 39);
	    clearChoice = c.readLine (); //gets choice from user

	    //if statement to errortrap if the user does not input 'yes' or 'no'
	    if (!(clearChoice.equals ("yes") || clearChoice.equals ("no")))
	    {
		//error message
		new Message ("You must enter 'yes' or 'no'.");
	    }
	    else
	    {
		break; //exits the loop
	    }
	}


	//if the user chose to clear the file
	if (clearChoice.equals ("yes"))
	{
	    //variable declarations
	    PrintWriter output;
	    //try catch structure
	    try
	    {
		output = new PrintWriter (new FileWriter (FILE_NAME)); //creates a new PrintWriter and FileWriter object
		output.print (""); //prints an empty string
		output.close (); //closes the file
	    }
	    catch (IOException e)  //catches any IO errors
	    {
	    }
	    //clears the array
	    scores = new String [0] [2];
	}
	c.println (); //spacing
	c.print (' ', 24);
	pauseProgram (); //calls pauseProgram
    }


    /*
    This method is the playGame method. It executes the Jeopardy gameplay.
    Variable Dictionary
    Name                Type            Description/Purpose
    darkBlue            Color           stores dark blue color
    yellow              Color           stores yellow color
    moneyFont           Font            stores font to draw money values
    categoryFont        Font            stores font to draw categories
    levelFont           Font            stores font to draw level text
    sideNoteFont        Font            stores font to draw side note
    categoryChoiceNum   int             stores the user's category choice
    userAnswer          String          stores the user's answer to the question
    categoryList        String[]        stores the list of categories
    questions           String[][]      stores the list of questions
    answers             String[][]      stores the list of answers
    finalJeopardyQ      String[]        stores the list of final jeopardy questions
    finalJeopardyA      String[]        stores the list of final jeopardy answers
    turn                int             stores the turn (player 1 or 2)
    category            int[]           stores the selected category numbers
    counter             int[]           stores the counters for how many questions have been answered in each category
    finalBet            int             stores the final jeopardy bet (player 1)
    finalBet2           int             stores the final jeopardy bet (player 2)
    finalQuestion       int             stores the final question number
    finalAnswer1        String          stores player 1's final jeopardy answer
    finalAnswer2        String          stores player 2's final jeopardy answer
    doubleJeopardyA     int             stores the randomly generated doubleJeopardy category number (level 1 and 2)
    doubleJeopardyB     int             stores the randomly generated doubleJeopardy question number (level 1 and 2)
    doubleJeopardyC     int             stores the randomly generated doubleJeopardy category number (level 2)
    doubleJeopardyD     int             stores the randomly generated doubleJeopardy question number (level 2)
    doubleJ             boolean         stores whether the question is double jeopardy or not
    doubleJBet          int             stores the double jeopardy bet value

    The first for loop runs from 0 to 4 and initializes all the counters to 0.
    The first while loop runs automatically. It generates a random number for category [1].
    The first if statement (inside the first while loop) breaks from the loop if category [1] is not equal to category [0].
    The second while loop runs automatically. It generates a random number for category [2].
    The second if statement (inside the second while loop) breaks from the loop if the category is not equal to any of the previous categories.
    The third while loop runs automatically. It generates a random number for category [3].
    The third if statement (inside the third while loop) breaks from the loop if the category is not equal to any of the previous categories.
    The fourth while loop runs automatically. It generates a random number for category [4].
    The fourth if statement (inside the fourth while loop) breaks from the loop if the category is not equal to any of the previous categories.
    The fifth while loop runs automatically. It generates a random number for category [5].
    The fifth if statement (inside the fifth while loop) breaks from the loop if the category is not equal to any of the previous categories.
    The sixth while loop runs automatically. It generates a random number for category [6].
    The sixth if statement (inside the sixth while loop) breaks from the loop if the category is not equal to any of the previous categories.
    The seventh while loop runs automatically. It generates a random number for category [7].
    The seventh if statement (inside the seventh while loop) breaks from the loop if the category is not equal to any of the previous categories.
    The eighth while loop runs automatically. It generates a random number for category [8].
    The eighth if statement (inside the eighth while loop) breaks from the loop if the category is not equal to any of the previous categories.
    The ninth while loop runs automatically. It generates a random number for category [9].
    The ninth if statement (inside the ninth while loop) breaks from the loop if the category is not equal to any of the previous categories.
    The tenth while loop runs automatically. It gets player 1's name.
    The tenth if statement (inside of the tenth while loop) errortraps if the name is greater than 40 characters. It breaks from the loop if the name is not greater than 40 characters.
    The eleventh while loop runs automatically. It gets player 2's name.
    There are 2 for loops that runs from 0 to the length of name. It checks if any of the letters is a space and converts it to an underscore.
    There are 2 if statement (inside of the for loops) that checks if each letter is a space and converts it to an underscore. Otherwise, it leaves the letters as is.
    The eleventh if statement (inside of the eleventh while loop) errortraps if the name is greater than 40 characters. It breaks from the loop if the name is not greater than 40 characters.
    The twelfth while loop runs automatically. It contains the gameplay for level 1. It draws the board and both players choose categories and answer questions. The program displays if the players are correct and displays their banks. It breaks only if all the questions have been answered (all the counters are equal to 5).
    The second for loop runs from 0 to 3 and draws the vertical dividers of the board.
    The third for loop runs from 0 to 4 and draws the horizontal dividers of the board.
    The fourth for loop runs from 0 to 4 and draws the draws the money values on the board.
    The fifth for loop runs from 0 to 4 and draws the categories on the board.
    The sixth for loop runs from 1 to 5. The seventh for loop is nested in the eighth and runs from 0 to 4. They cover any questions that have been answered if counter [x] is greater than i. Inside the ninth for loop there is the thirteenth if statement that covers the question if it has been answered.
    The fourteenth if statement breaks from the level 1 (twelfth) while loop if all the counters are equal to 5.
    The fifteenth if statement outputs that it is player 1's turn if turn equals to 1 or player 2's turn if turn equals to 2.
    The thirteenth while loop gets the category choice from the user and errortraps if the user did not enter a valid response. It breaks if the user enters a valid response.
    The first try catch statement is inside the thirteenth while loop. It tries to get the category choice from the user and parses it to an integer. It catches any parsing errors.
    The sixteenth if statement is inside the thirteenth while loop. It displays an error message if the user did not enter a number from 1 to 5 or if there are no more questions in that category.
    The seventeenth if statement looks at if the user discovered the double jeopardy. It sets doubleJ to true and displays double jeopardy messages. It gets a money wager and errortraps it.
    The fourteenth while loop is inside the seventeenth if statement. It gets a money wager from the player and errortraps it. It breaks if the user entered a valid response.
    The second try catch statement is inside the fourteenth while loop. It tries to get a money wager from the player and errortraps it. It catches parsing errors and displays an error message.
    The eighteenth if statement in while loop 14 displays if player 1 or player 2 is betting depending on if the turn is 1 or 2.
    The ninteenth if statement in while loop 14 executes errortraps for player 1 if turn equals 1 or player 2 if turn equals 2.
    The twentieth and twenty-first if statement in while loop 14 errortraps for player 1 and player 2. It displays an error message if the bet is negative, more money than the player's bank, or if it is greater than the top value on the board. It breaks from the loop if there is no error.
    The twenty-second if statement displays messages showing what the user chose and the money they can earn depending on whether it is double jeopardy or not.
    The twenty-third if statement determines if the user's answer matches the correct answer. If it was correct, the program displays the 'correct message' and calls bankUpdate to update (increase) the money. If it was incorrect, the program displays the 'incorrect message' and calls bankUpdate to update (decrease) the money.
    The twenty-fourth and twenty-fifth if statement are in the twenty-third if statement. They call bankUpdate and pass on player 1's information if it is player 1's turn. They call bankUpdate and pass on player 2's information if it is player 2's turn.
    The twenty-sixth if statement changes turn to 2 if turn was previously 1 and changes turn to 1 if turn was previously 2.
    The twenty-seventh if statement displays which player is in the lead depending on which player has more money in their bank.
    The fifteenth while loop generates doubleJeopardyC and doubleJeopardyD values. It breaks if the other 2 doubleJeopardy values are not the same.
    The twenty-eighth if statement determines if doubleJeopardyA is not equal to doubleJeopardyC and if doubleJeopardyB is not equal to doubleJeopardyD. If the condition is met, it breaks from the fifteenth while loop.
    The eighth for loop runs from 0 to 4 and resets the counters to 0.
    The fifteenth while loop runs automatically. It contains the gameplay for level 2. It draws the board and both players choose categories and answer questions. The program displays if the players are correct and displays their banks. It breaks only if all the questions have been answered (all the counters are equal to 5).
    The ninth for loop runs from 0 to 3 and draws the vertical dividers of the board.
    The tenth for loop runs from 0 to 4 and draws the horizontal dividers of the board.
    The eleventh for loop runs from 0 to 4 and draws the draws the money values on the board.
    The twelfth for loop runs from 0 to 4 and draws the categories on the board.
    The thirteenth for loop runs from 1 to 5. The ninth for loop is nested in the eighth and runs from 0 to 4. They cover any questions that have been answered if counter [x] is greater than i. Inside the sixteenth for loop there is the twenty-ninth if statement that covers the question if it has been answered.
    The thirtieth if statement breaks from the level 1 (fifteenth) while loop if all the counters are equal to 5.
    The thirty-first if statement outputs that it is player 1's turn if turn equals to 1 or player 2's turn if turn equals to 2.
    The sixteenth while loop gets the category choice from the user and errortraps if the user did not enter a valid response. It breaks if the user enters a valid response.
    The third try catch statement is inside the sixteenth while loop. It tries to get the category choice from the user and parses it to an integer. It catches any parsing errors.
    The thirty-second if statement is inside the sixteenth while loop. It displays an error message if the user did not enter a number from 1 to 5 or if there are no more questions in that category.
    The thirty-third if statement looks at if the user discovered the double jeopardy. It sets doubleJ to true and displays double jeopardy messages. It gets a money wager and errortraps it.
    The seventeenth while loop is inside the thirty-third if statement. It gets a money wager from the player and errortraps it. It breaks if the user entered a valid response.
    The fourth try catch statement is inside the seventeenth while loop. It tries to get a money wager from the player and errortraps it. It catches parsing errors and displays an error message.
    The thirty-fourth if statement in while loop 17 displays if player 1 or player 2 is betting depending on if the turn is 1 or 2.
    The ninteenth if statement in while loop 17 executes errortraps for player 1 if turn equals 1 or player 2 if turn equals 2.
    The thirty-fifth and thirty-sixth if statement in while loop 17 errortraps for player 1 and player 2. It displays an error message if the bet is negative, more money than the player's bank, or if it is greater than the top value on the board. It breaks from the loop if there is no error.
    The thirty-sixth if statement displays messages showing what the user chose and the money they can earn depending on whether it is double jeopardy or not.
    The thirty-seventh if statement determines if the user's answer matches the correct answer. If it was correct, the program displays the 'correct message' and calls bankUpdate to update (increase) the money. If it was incorrect, the program displays the 'incorrect message' and calls bankUpdate to update (decrease) the money.
    The thirty-eighth and thirty-ninth if statement are in the thirty-seventh if statement. They call bankUpdate and pass on player 1's information if it is player 1's turn. They call bankUpdate and pass on player 2's information if it is player 2's turn.
    The fortieth if statement changes turn to 2 if turn was previously 1 and changes turn to 1 if turn was previously 2.
    The forty-first if statement displays which player is in the lead depending on which player has more money in their bank.
    The eighteenth while loop automatically runs. It gets the final bet from player 1. It breaks when player 1 has entered a valid response.
    The fifth try catch statement is inside the eighteenth while loop. It tries to get the final bet from player 1 and parses it into an integer. It catches any parsing errors and displays an error message.
    The forty-second if statement is inside the fifth try catch statement and errortraps user input. It displays an error message and reruns in the loop if the user bet a negative amount, the user bet more money in their bank, or if the user is in debt and did not bet $0. It breaks from the while loop if the user entered a valid response.
    The ninteenth while loop automatically runs. It gets the final bet from player 2. It breaks when player 2 has entered a valid response.
    The sixth try catch statement is inside the nineteenth while loop. It tries to get the final bet from player 2 and parses it into an integer. It catches any parsing errors and displays an error message.
    The forty-third if statement is inside the sixth try catch statement and errortraps user input. It displays an error message and reruns in the loop if the user bet a negative amount, the user bet more money in their bank, or if the user is in debt and did not bet $0. It breaks from the while loop if the user entered a valid response.
    The forty-fourth if statement displays a 'correct message' to player 1 and adds their bet to their bank if their final answer matched the correct answer. It displays an 'incorrect message' to player 1 and subtracts their bet from their bank if their final answer was wrong.
    The forty-fifth if statement displays a 'correct message' to player 2 and adds their bet to their bank if their final answer matched the correct answer. It displays an 'incorrect message' to player 2 and subtracts their bet from their bank if their final answer was wrong.

    */
    public void playGame ()  //playGame method
    {
	//local variable declarations
	//color declarations
	Color darkBlue = new Color (12, 25, 168); //dark blue
	Color yellow = new Color (239, 207, 62); //yellow
	//font declarations
	Font moneyFont = new Font ("Courier", 1, 30); //money font
	Font categoryFont = new Font ("Courier", 1, 12); //category font
	Font levelFont = new Font ("Courier", 1, 20); //level font
	Font sideNoteFont = new Font ("Courier", 1, 11); //side note font
	char nameLetter = ' '; //stores each character of the names to check if any of them are spaces
	String tempName = ""; //stores the temporary name for name1 and name2 as the program converts spaces to underscores in the names
	int categoryChoiceNum = 0; //stores the category choice that the user chose
	String userAnswer = ""; //stores the player's answer to the question
	String[] categoryList = new String [20]; //string array that stores the categories
	String[] [] questions = new String [20] [5]; //2D string array that stores the questions of the categories
	String[] [] answers = new String [20] [5]; //2D string array that stores the answers to the questions of the categories
	String[] finalJeopardyQ = new String [15]; //string array that stores the final jeopardy questions
	String[] finalJeopardyA = new String [15]; //string array that stores the final jeopardy answers
	int turn = 1; //stores the turn number (1 for player 1, 2 for player 2)
	int[] category = new int [10];  //stores the randomized category values
	int[] counter = new int [5];  //stores the counter for each category to keep track of how many questions have been done
	int finalBet1 = 0, finalBet2 = 0; //stores the players' final jeopardy bets (amount of money they bet)
	int finalQuestion = 0; //stores the randomized final question number
	String finalAnswer1 = "", finalAnswer2 = ""; //stores the players' final jeopardy answers to the question
	int doubleJeopardyA = 0, doubleJeopardyB = 0, doubleJeopardyC = 0, doubleJeopardyD = 0; //stores the randomized numbers for double jeopardy
	boolean doubleJ = false; //stores whether double jeopardy is true or not
	int doubleJBet = 0; //stores the double jeopardy bet

	//counter initialization
	for (int i = 0 ; i < 5 ; i++)
	{
	    counter [i] = 0;
	}

	//name and money reset
	name1 = "";
	name2 = "";
	bank1 = 0;
	bank2 = 0;

	//categories
	categoryList [0] = "ICS";
	categoryList [1] = "Science";
	categoryList [2] = "Animals";
	categoryList [3] = "Literature";
	categoryList [4] = "Movies";
	categoryList [5] = "Food";
	categoryList [6] = "Canada";
	categoryList [7] = "Pop Culture";
	categoryList [8] = "Mythology";
	categoryList [9] = "Space";
	categoryList [10] = "Games";
	categoryList [11] = "Art";
	categoryList [12] = "Landmarks";
	categoryList [13] = "History";
	categoryList [14] = "Geography";
	categoryList [15] = "Music";
	categoryList [16] = "Anatomy";
	categoryList [17] = "Word Origins";
	categoryList [18] = "Sports";
	categoryList [19] = "Cities";

	//questions
	questions [0] [0] = "Who was the Turing programming language named after?";
	questions [0] [1] = "True or false: Ms. Krasteva is the best ICS teacher. ";
	questions [0] [2] = "Who wrote the first algorithm intended to be carried out by a machine?";
	questions [0] [3] = "Fill in the blank. ___________ is the ability of different parts of a program to be executed at the same time. ";
	questions [0] [4] = "What was Java originally called?";

	questions [1] [0] = "Fill in the blank. ________ acid gives lemons, limes, oranges, and grapefruits their distinctive taste. ";
	questions [1] [1] = "What did Marie Curie name the first chemical element she discovered?";
	questions [1] [2] = "What is the full element name of Bk?";
	questions [1] [3] = "What type of cloud is often described as 'cotton-like'?";
	questions [1] [4] = "Fill in the blank. The ________ is the only organ that cannot feel pain.";

	questions [2] [0] = "Fill in the blank. A _________ is a mammal that carries their young in their pouch.";
	questions [2] [1] = "What is the study of bird eggs called?";
	questions [2] [2] = "What insect has the best eyesight? ";
	questions [2] [3] = "Fill in the blank. _______ pollinates banana plants in the wild?";
	questions [2] [4] = "What animal is the symbol for long life in Korea? ";

	questions [3] [0] = "What famous play is this line from: 'A pair of star-cross'd lovers take their life'?";
	questions [3] [1] = "Who was the author of the famous novel 'Dracula'?";
	questions [3] [2] = "Fill in the blank. In Lord of the Flies, _______'s glasses were used to start a fire.";
	questions [3] [3] = "Who is the main protagonist of the famous book series 'The Hunger Games'?";
	questions [3] [4] = "What famous literary character says: 'Neither a borrower nor a lender be'?";

	questions [4] [0] = "In the Disney movie 'Sleeping Beauty', what was the name of the evil Queen? ";
	questions [4] [1] = "What famous movie about a shark was named the first ever 'Blockbuster'? ";
	questions [4] [2] = "What color were the slippers in the original Wizard of Oz movie? ";
	questions [4] [3] = "What was the name of the lion in the Witch and the Wardrobe? ";
	questions [4] [4] = "In the Disney film 'Bambi', what was Bambi's first word? ";

	questions [5] [0] = "What company first condensed soup in 1898? ";
	questions [5] [1] = "What crystalline salt is known as MSG? ";
	questions [5] [2] = "What is the name of the dessert consisting of ice cream and cake topped with browned meringue? ";
	questions [5] [3] = "What is the name of the scale used to measure the spiciness of hot peppers? ";
	questions [5] [4] = "What type of fish does proper caviar come from? ";

	questions [6] [0] = "Fill in the blank. The Canadian flag features a leaf from the _________ tree.";
	questions [6] [1] = "What city is the capital of Canada?";
	questions [6] [2] = "In what year did Canada become a country? Please enter the number.";
	questions [6] [3] = "What is the official summer sport of Canada?";
	questions [6] [4] = "Canada's highest mountain is located in which province or territory?";

	questions [7] [0] = "Who created Mickey Mouse?";
	questions [7] [1] = "Who was the first actor to portray James Bond on screen?";
	questions [7] [2] = "Fill in the blank. The first book in the Harry Potter series is Harry Potter and the ___________'s Stone.";
	questions [7] [3] = "Which actor plays the superhero Thor?";
	questions [7] [4] = "Which famous singer recently released her hit single 'Look What You Made Me Do'?";

	questions [8] [0] = "What was the name of the mythological creature with nine heads? ";
	questions [8] [1] = "In Greek mythology, who was the goddess of love? ";
	questions [8] [2] = "In Egyptian mythology, who was the deity of the sun? ";
	questions [8] [3] = "Fill in the blank. The ________ tree is sacred to Apollo.";
	questions [8] [4] = "In Norse mythology, what did Odin trade an eye for? ";

	questions [9] [0] = "On what planet is a year shorter than a day?";
	questions [9] [1] = "What planet has the most moons? ";
	questions [9] [2] = "During what phase is the moon the least visible from Earth?";
	questions [9] [3] = "Fill in the blank. A ________ is an interstellar cloud of dust.";
	questions [9] [4] = "What term describes the center of gravity of the Earth and the moon?";

	questions [10] [0] = "How many numbers are there on a traditional bingo card? Please enter the number.";
	questions [10] [1] = "What does Sony's handheld games console PSP stand for?";
	questions [10] [2] = "Which Chinese game involves 144 tiles divided into six suits?";
	questions [10] [3] = "The name of which board game translates from Latin as 'I play'?";
	questions [10] [4] = "In which city was the pinball machine invented?";

	questions [11] [0] = "This famous portrait of Lisa Gherardini was painted by Leonardo da Vinci.";
	questions [11] [1] = "Who painted The Persistence of Memory?";
	questions [11] [2] = "What is the last name of the artist best known for his painting of his mother?";
	questions [11] [3] = "What is the name of the artist who painted 'The Scream'?";
	questions [11] [4] = "What was the extravagant style of art and architecture that dominated Europe during most of the 17th century?";

	questions [12] [0] = "Fill in the blank. The ________ Tower is the tallest structure in Paris. ";
	questions [12] [1] = "Fill in the blank. The _______________________ is a tower famous for its unintended tilt. ";
	questions [12] [2] = "Fill in the blank. The _______________________ is the most famous performing arts centre in Australia.";
	questions [12] [3] = "Fill in the blank. This famous clock tower in London, England is commonly known as the ___________.";
	questions [12] [4] = "This famous oval amphitheatre in Rome, Italy is also known as the Flavian Amphitheatre.";

	questions [13] [0] = "Who was the first democratically elected President of Russia?";
	questions [13] [1] = "Fill in the blank. The Battle of _______ was the last battle of the Napoleonic Wars.";
	questions [13] [2] = "How many years did the Hundred Years' War last? Please enter the number.";
	questions [13] [3] = "Fill in the blank. Sir ____________ was the first prime minister of Canada.";
	questions [13] [4] = "Fill in the blank. The __________ political party was banned in Italy after World War II.";

	questions [14] [0] = "In what country can you find Machu Picchu? ";
	questions [14] [1] = "Fill in the blank. The _________ strait separates Russia and Alaska. ";
	questions [14] [2] = "Fill in the blank. The _________ River is the longest river in Canada. ";
	questions [14] [3] = "What country ends with a vowel followed by the letter 'e'?";
	questions [14] [4] = "What is the largest city in the world based on surface area? ";

	questions [15] [0] = "What is the oldest surviving musical instrument?";
	questions [15] [1] = "Which rapper holds the world for most words in a hit single?";
	questions [15] [2] = "Who composed The Four Seasons?";
	questions [15] [3] = "What singer is known as the 'Empress of the Blues'?";
	questions [15] [4] = "Who had a hit in the 1970's with his song 'Piano Man'?";

	questions [16] [0] = "Fill in the blank. ___________ bones are the bones that connect the fingers to the wrists. ";
	questions [16] [1] = "What is the smallest part of the small intestine?";
	questions [16] [2] = "What is the technical name for the voice box?";
	questions [16] [3] = "What is the technical name for the calf? ";
	questions [16] [4] = "What is the technical name for the cheekbone?";

	questions [17] [0] = "Meaning 'dead pledge', it's the word for the loan you take out to buy a home.";
	questions [17] [1] = "This type of orange got its name from Chinese officials who wore robes of a similar color.";
	questions [17] [2] = "This word originates from a Greek word meaning 'wandering stars'.";
	questions [17] [3] = "The name of this accessory comes from old Italian, meaning 'to shield the sun'.";
	questions [17] [4] = "The name of this Tyrolean garment is short for the German word for 'girl dress'.";

	questions [18] [0] = "What NFL Quarterback has been in the most Super Bowls?";
	questions [18] [1] = "A shuttlecock is used in what sport?";
	questions [18] [2] = "How many holes are there in a full round of golf? Please enter the number.";
	questions [18] [3] = "'The Ashes' is the most important trophy in what sport?";
	questions [18] [4] = "In what year were women first allowed to participate in the modern Olympic games? Please enter the number.";

	questions [19] [0] = "What is the capital city of Cuba? ";
	questions [19] [1] = "What is the capital city of Sweden?";
	questions [19] [2] = "What is the capital city of Bulgaria? ";
	questions [19] [3] = "What is the capital city of Solomon Islands?";
	questions [19] [4] = "What is the capital city of Mongolia? ";

	//answers
	answers [0] [0] = "Alan Turing";
	answers [0] [1] = "True";
	answers [0] [2] = "Ada Lovelace";
	answers [0] [3] = "Concurrency";
	answers [0] [4] = "Oak";

	answers [1] [0] = "Citric";
	answers [1] [1] = "Polonium";
	answers [1] [2] = "Berkelium";
	answers [1] [3] = "Cumulus";
	answers [1] [4] = "Brain";

	answers [2] [0] = "Marsupial";
	answers [2] [1] = "Oology";
	answers [2] [2] = "Dragonfly";
	answers [2] [3] = "Bats";
	answers [2] [4] = "Deer";

	answers [3] [0] = "Romeo and Juliet";
	answers [3] [1] = "Bram Stoker";
	answers [3] [2] = "Piggy";
	answers [3] [3] = "Katniss Everdeen";
	answers [3] [4] = "Polonius";

	answers [4] [0] = "Maleficent";
	answers [4] [1] = "Jaws";
	answers [4] [2] = "Silver";
	answers [4] [3] = "Aslan";
	answers [4] [4] = "Bird";

	answers [5] [0] = "Campbell's";
	answers [5] [1] = "Monosodium glutamate";
	answers [5] [2] = "Baked Alaska";
	answers [5] [3] = "Scoville scale";
	answers [5] [4] = "Sturgeon";

	answers [6] [0] = "Maple";
	answers [6] [1] = "Ottawa";
	answers [6] [2] = "1867";
	answers [6] [3] = "Lacrosse";
	answers [6] [4] = "Yukon";

	answers [7] [0] = "Walt Disney";
	answers [7] [1] = "Barry Nelson";
	answers [7] [2] = "Philosopher";
	answers [7] [3] = "Chris Hemsworth";
	answers [7] [4] = "Taylor Swift";

	answers [8] [0] = "Hydra";
	answers [8] [1] = "Aphrodite";
	answers [8] [2] = "Ra";
	answers [8] [3] = "Laurel";
	answers [8] [4] = "Wisdom";

	answers [9] [0] = "Venus";
	answers [9] [1] = "Jupiter";
	answers [9] [2] = "New moon";
	answers [9] [3] = "Nebula";
	answers [9] [4] = "Barycenter";

	answers [10] [0] = "15";
	answers [10] [1] = "Playstation Portable";
	answers [10] [2] = "Mahjong";
	answers [10] [3] = "Ludo";
	answers [10] [4] = "Chicago";

	answers [11] [0] = "Mona Lisa";
	answers [11] [1] = "Salvador Dali";
	answers [11] [2] = "Whistler";
	answers [11] [3] = "Edvard Munch";
	answers [11] [4] = "Baroque";

	answers [12] [0] = "Eiffel";
	answers [12] [1] = "Leaning Tower of Piza";
	answers [12] [2] = "Sydney Opera House";
	answers [12] [3] = "Big Ben";
	answers [12] [4] = "Colosseum";

	answers [13] [0] = "Boris Yeltsin";
	answers [13] [1] = "Wavre";
	answers [13] [2] = "116";
	answers [13] [3] = "John A. Macdonald";
	answers [13] [4] = "Fascist";

	answers [14] [0] = "Peru";
	answers [14] [1] = "Bering";
	answers [14] [2] = "Mackenzie";
	answers [14] [3] = "Mozambique";
	answers [14] [4] = "Hulunbuir";

	answers [15] [0] = "Flute";
	answers [15] [1] = "Eminem";
	answers [15] [2] = "Antonio Vivaldi";
	answers [15] [3] = "Bessie Smith";
	answers [15] [4] = "Billy Joel";

	answers [16] [0] = "Metacarpal";
	answers [16] [1] = "Duodenum";
	answers [16] [2] = "Larynx";
	answers [16] [3] = "Gastrocnemius";
	answers [16] [4] = "Zygomatic arch";

	answers [17] [0] = "Mortgage";
	answers [17] [1] = "Mandarin";
	answers [17] [2] = "Planet";
	answers [17] [3] = "Parasol";
	answers [17] [4] = "Dirndl";

	answers [18] [0] = "Tom Brady";
	answers [18] [1] = "Badminton";
	answers [18] [2] = "18";
	answers [18] [3] = "Cricket";
	answers [18] [4] = "1990";

	answers [19] [0] = "Havana";
	answers [19] [1] = "Stockholm";
	answers [19] [2] = "Sofia";
	answers [19] [3] = "Honiara";
	answers [19] [4] = "Ulaanbaatar";

	//final jeopardy questions
	finalJeopardyQ [0] = "This country's coat of arms features a palm tree and a 19th century American sailing ship.";
	finalJeopardyQ [1] = "A popular food product was born when Jean Naigeon of this city substituted the juice of unripe grapes for vinegar.";
	finalJeopardyQ [2] = "From the Latin for 'at the same time', this 12-letter adjective contains all 5 vowels.";
	finalJeopardyQ [3] = "This planet is named for a Roman god; its only moons are named for the sons of his Greek counterpart.";
	finalJeopardyQ [4] = "The name of this provincial capital city means 'queen' in Latin.";
	finalJeopardyQ [5] = "The Pulitzer Prize Gold Medal features an engraving of this 18th century American Founding Father.";
	finalJeopardyQ [6] = "This famous artist is seen wearing a rebozo in her 1937 'Self-Portrait Dedicated to Leon Trotsky'.";
	finalJeopardyQ [7] = "The desire in his childhood to catch every insect inspired Satoshi Tajiri to create this 1996 game.";
	finalJeopardyQ [8] = "This 6-letter word can mean both a bright light above someone's head & a dark cloud above our heads.";
	finalJeopardyQ [9] = "This classic book begins, 'The pretty little Swiss town of Mayenfeld lies at the foot of a mountain range'.";
	finalJeopardyQ [10] = "This element was discovered extraterrestrially in 1868; it took 27 more years until someone isolated it on Earth.";
	finalJeopardyQ [11] = "With more than 90 million people it's Africa's third most populous country, though it's more than 90% desert.";
	finalJeopardyQ [12] = "The last words spoken by this literary character are 'What's done cannot be undone: to bed, to bed, to bed'.";
	finalJeopardyQ [13] = "Vestibular rehabilitation is one treatment for a condition that is also the title of this 1958 suspense film.";
	finalJeopardyQ [14] = "In Latin the name of this math field meant a pebble used in counting, and the word also has the medical meaning 'stone'.";

	//final jeopardy answers
	finalJeopardyA [0] = "Liberia";
	finalJeopardyA [1] = "Dijon";
	finalJeopardyA [2] = "Simultaneous";
	finalJeopardyA [3] = "Mars";
	finalJeopardyA [4] = "Regina";
	finalJeopardyA [5] = "Benjamin Franklin";
	finalJeopardyA [6] = "Frida Kahlo";
	finalJeopardyA [7] = "Pokemon";
	finalJeopardyA [8] = "Nimbus";
	finalJeopardyA [9] = "Heidi";
	finalJeopardyA [10] = "Helium";
	finalJeopardyA [11] = "Egypt";
	finalJeopardyA [12] = "Lady Macbeth";
	finalJeopardyA [13] = "Vertigo";
	finalJeopardyA [14] = "Calculus";

	//random category generator
	//category 1
	category [0] = categoryGenerator (); //generates random number for category 1
	//while loop (category 2)
	while (true)
	{
	    category [1] = categoryGenerator (); //generates random number for category 2
	    //if statement for if the category number has not already been chosen
	    if (category [1] != category [0])
	    {
		break; //exits the loop
	    }
	}


	//while loop (category 3)
	while (true)
	{
	    category [2] = categoryGenerator (); //generates random number for category 3
	    //if statement for if the category number has not already been chosen
	    if (category [2] != category [0] && category [2] != category [1])
	    {
		break; //exits the loop
	    }
	}


	//while loop (category 4)
	while (true)
	{
	    category [3] = categoryGenerator (); //generates random number for category 4
	    //if statement for if the category number has not already been chosen
	    if (category [3] != category [0] && category [3] != category [1] && category [3] != category [2])
	    {
		break; //exits the loop
	    }
	}


	//while loop (category 5)
	while (true)
	{
	    category [4] = categoryGenerator (); //generates random number for category 5
	    //if statement for if the category number has not already been chosen
	    if (category [4] != category [0] && category [4] != category [1] && category [4] != category [2] && category [4] != category [3])
	    {
		break; //exits the loop
	    }
	}

	//while loop (category 6)
	while (true)
	{
	    category [5] = categoryGenerator (); //generates random number for category 6
	    //if statement for if the category number has not already been chosen
	    if (category [5] != category [0] && category [5] != category [1] && category [5] != category [2] && category [5] != category [3] && category [5] != category [4])
	    {
		break; //exits the loop
	    }
	}

	//while loop (category 7)
	while (true)
	{
	    category [6] = categoryGenerator (); //generates random number for category 7
	    //if statement for if the category number has not already been chosen
	    if (category [6] != category [0] && category [6] != category [1] && category [6] != category [2] && category [6] != category [3] && category [6] != category [4] && category [6] != category [5])
	    {
		break; //exits the loop
	    }
	}

	//while loop (category 8)
	while (true)
	{
	    category [7] = categoryGenerator (); //generates random number for category 8
	    //if statement for if the category number has not already been chosen
	    if (category [7] != category [0] && category [7] != category [1] && category [7] != category [2] && category [7] != category [3] && category [7] != category [4] && category [7] != category [5] && category [7] != category [6])
	    {
		break; //exits the loop
	    }
	}

	//while loop (category 9)
	while (true)
	{
	    category [8] = categoryGenerator (); //generates random number for category 9
	    //if statement for if the category number has not already been chosen
	    if (category [8] != category [0] && category [8] != category [1] && category [8] != category [2] && category [8] != category [3] && category [8] != category [4] && category [8] != category [5] && category [8] != category [6] && category [8] != category [7])
	    {
		break; //exits the loop
	    }
	}

	//while loop (category 10)
	while (true)
	{
	    category [9] = categoryGenerator (); //generates random number for category 10
	    //if statement for if the category number has not already been chosen
	    if (category [9] != category [0] && category [9] != category [1] && category [9] != category [2] && category [9] != category [3] && category [9] != category [4] && category [9] != category [5] && category [9] != category [6] && category [9] != category [7] && category [9] != category [8])
	    {
		break; //exits the loop
	    }
	}

	//random double jeopardy generator (level 1)
	doubleJeopardyA = ((int) (Math.random () * 5 + 1)); //generates random number from 1 to 5 to choose category of double jeopardy
	doubleJeopardyB = ((int) (Math.random () * 5)); //generates random number from 0 to 4 to choose question of double jeopardy

	//first screen in playGame (gets player names)
	title (); //calls title
	c.println ("Please note that any spaces in the names will be converted to underscores due to sorting purposes.");
	//while loop
	while (true)
	{
	    c.setCursor (4, 1);
	    c.println ("What is player 1's name? "); //user prompt
	    c.setCursor (4, 26);
	    name1 = c.readLine (); //gets player 1's name
	    //if name is more than 40 characters
	    if (name1.length () > 40)
	    {
		//error message
		new Message ("Sorry, you cannot enter more than 40 characters in your name.");
	    }
	    else
	    {
		break; //exits the loop
	    }
	}
	//while loop
	while (true)
	{
	    c.setCursor (5, 1);
	    c.println ("What is player 2's name? "); //user prompt
	    c.setCursor (5, 26);
	    name2 = c.readLine (); //gets player 2's name
	    //if name is more than 40 characters
	    if (name2.length () > 40)
	    {
		//error message
		new Message ("Sorry, you cannot enter more than 40 characters in your name.");
	    }
	    else
	    {
		break; //exits the loop
	    }
	}
	//for loop runs through the length of name1
	for (int i = 0 ; i < name1.length () ; i++)
	{
	    //stores each letter of name1 into nameLetter
	    nameLetter = name1.charAt (i);
	    //if the character is a space
	    if (nameLetter == 32)
	    {
		//adds an underscore instead of a space to the temporary name variable
		tempName += "_";
	    }
	    //if the character is not a space
	    else
	    {
		//adds the regular letter to the temporary name variable
		tempName += nameLetter;
	    }
	}
	//transfers temporary name string to the regular name1 string
	name1 = tempName;
	//clears temporary name variable to be used again
	tempName = "";
	//for loop runs through the length of name2
	for (int i = 0 ; i < name2.length () ; i++)
	{
	    //stores each letter of name1 into nameLetter
	    nameLetter = name2.charAt (i);
	    //if the character is a space
	    if (nameLetter == 32)
	    {
		//adds an underscore instead of a space to the temporary name variable
		tempName += "_";
	    }
	    //if the character is not a space
	    else
	    {
		//adds the regular letter to the temporary name variable
		tempName += nameLetter;
	    }
	}
	//transfers temporary name string to the regular name2 string
	name2 = tempName;

	c.println (); //spacing
	pauseProgram (); //calls pauseProgram

	//second screen in playGame (Level 1)
	//while loop
	while (true)
	{
	    title (); //calls title
	    c.setColor (yellow); //sets color to yellow
	    c.setFont (sideNoteFont); //sets font to side note font
	    c.drawString ("*Side note: please do not enter", 5, 15);
	    c.drawString ("any spaces after your answers.", 5, 25);
	    //board graphics
	    //board
	    c.setColor (darkBlue); //dark blue
	    c.fillRect (60, 40, 520, 280); //board base
	    c.setColor (Color.black); //black
	    //board edges
	    c.fillRect (60, 40, 520, 8); //top edge
	    c.fillRect (60, 312, 520, 8); //bottom edge
	    c.fillRect (60, 40, 8, 280); //left edge
	    c.fillRect (572, 40, 8, 280); //right edge
	    //vertical dividers
	    for (int i = 0 ; i < 4 ; i++)
	    {
		c.fillRect (161 + i * 104, 40, 5, 280);
	    }
	    //horizontal dividers
	    for (int i = 0 ; i < 5 ; i++)
	    {
		c.fillRect (60, 90 + i * 45, 520, 5);
	    }
	    //money text on board
	    c.setColor (yellow); //yellow
	    c.setFont (moneyFont); //sets font
	    //for loop runs from 0 to 4
	    for (int i = 0 ; i < 5 ; i++)
	    {
		c.drawString ("$100", 77 + i * 103, 123); //$100
		c.drawString ("$200", 77 + i * 103, 169); //$200
		c.drawString ("$300", 77 + i * 103, 215); //$300
		c.drawString ("$400", 77 + i * 103, 261); //$400
		c.drawString ("$500", 77 + i * 103, 303); //$500
	    }
	    //category text on board
	    c.setFont (categoryFont); //sets font
	    //for loop runs from 0 to 4
	    for (int i = 0 ; i < 5 ; i++)
	    {
		c.drawString (categoryList [category [i]], 73 + i * 102, 72); //first category
	    }

	    //level 1 text
	    c.setFont (levelFont); //sets font
	    c.drawString ("Level 1", 280, 37); //draws text

	    //squares that cover questions that have been answered
	    c.setColor (darkBlue); //dark blue
	    //loop runs from 1 to 5
	    for (int i = 1 ; i < 6 ; i++)
	    {
		//loop runs from 0 to 4
		for (int x = 0 ; x < 5 ; x++)
		{
		    if (counter [x] >= i) //if category x question i has been answered
		    {
			//dark blue cover
			c.fillRect (70 + x * 102, 52 + i * 45, 90, 35);
		    }
		}
	    }

	    //if all the questions on the board have been answered
	    if (counter [0] == 5 && counter [1] == 5 && counter [2] == 5 && counter [3] == 5 && counter [4] == 5)
	    {
		break; //exits the loop (level 1)
	    }


	    //gameplay

	    c.setCursor (17, 1);
	    //if it is player 1's turn
	    if (turn == 1)
	    {
		c.println ("It is player 1's turn. (" + name1 + ")");
	    }
	    //if it is player 2's turn
	    else
	    {
		c.println ("It is player 2's turn. (" + name2 + ")");
	    }
	    //while loop
	    while (true)
	    {
		try
		{
		    c.setCursor (18, 1);
		    c.println ("Please choose a category (1, 2, 3, 4, or 5):"); //user prompt
		    c.setCursor (18, 46);
		    categoryChoiceNum = Integer.parseInt (c.readLine ()); //gets choice, parses category choice into an integer and stores it in categoryChoiceNum
		    //errortraps if the user did not enter 1, 2, 3, 4, or 5
		    if (categoryChoiceNum < 1 || categoryChoiceNum > 5)
		    {
			//error message
			new Message ("You must enter 1, 2, 3, 4, or 5.");
		    }
		    //errortraps if there are no more questions in that category
		    else if (counter [categoryChoiceNum - 1] == 5)
		    {
			//error message
			new Message ("There are no more questions in that category. Please choose a different category.");
		    }
		    else
		    {
			break; //exits the loop
		    }
		}
		catch (Exception e)  //catches parsing errors
		{
		    //error message
		    new Message ("You must enter an integer.");
		}
	    }
	    //if the user discovered the double jeopardy
	    if (categoryChoiceNum == doubleJeopardyA && doubleJeopardyB == counter [categoryChoiceNum - 1])
	    {
		//changes double jeopardy to true
		doubleJ = true;
		//message
		c.println ("You have discovered the Double Jeopardy!");
		//displays players' banks
		c.println ("Player 1's Bank (" + name1 + "): $" + bank1); //player 1's bank
		c.println ("Player 2's Bank (" + name2 + "): $" + bank2); //player 2's bank
		//while loop
		while (true)
		{
		    //try catch statement
		    try
		    {
			c.setCursor (22, 1);
			//user prompt
			if (turn == 1) //if it is player 1's turn
			{
			    c.println ("How much money from your bank would you like to wager, player 1?");
			}
			else //if it is player 2's turn
			{
			    c.println ("How much money from your bank would you like to wager, player 2?");
			}
			c.setCursor (22, 66);
			doubleJBet = Integer.parseInt (c.readLine ()); //gets bet from user, parses it into an integer, and stores it
			//if it is player 1's turn
			if (turn == 1)
			{
			    //if the user entered a negative number
			    if (doubleJBet < 0)
			    {
				//error message
				new Message ("You cannot bet a negative amount. Please enter a positive integer or 0.");
			    }
			    //if the user has a bank less than $500 and bets more than $500
			    else if (bank1 < 500 && doubleJBet > 500)
			    {
				//error message
				new Message ("You can only bet up to $500, since your bank is less than the top value on the board.");
			    }
			    //if the user has a bank greater than $500 and bets higher than their bank
			    else if (bank1 >= 500 && doubleJBet > bank1)
			    {
				//error message
				new Message ("You cannot bet more money than you have in your bank.");
			    }
			    else
			    {
				break; //exits the loop
			    }
			}
			//if it is player 2's turn
			else
			{
			    //if the user entered a negative number
			    if (doubleJBet < 0)
			    {
				//error message
				new Message ("You cannot bet a negative amount. Please enter a positive integer or 0.");
			    }
			    //if the user has a bank less than $500 and bets more than $500
			    else if (bank2 < 500 && doubleJBet > 500)
			    {
				//error message
				new Message ("You can only bet up to $500, since your bank is less than the top value on the board.");
			    }
			    //if the user has a bank greater than $500 and bets higher than their bank
			    else if (bank2 >= 500 && doubleJBet > bank2)
			    {
				//error message
				new Message ("You cannot bet more money than you have in your bank.");
			    }
			    else
			    {
				break; //exits the loop
			    }
			}
		    }
		    catch (Exception e)  //catches errors in parsing
		    {
			//error message
			new Message ("You must enter a proper integer.");
		    }
		}
	    }
	    //if it is double jeopardy
	    if (doubleJ == true)
	    {
		//displays what the user has chosen
		c.println ("You have chosen " + categoryList [category [categoryChoiceNum - 1]] + " and bet $" + (doubleJBet) + ".");
	    }
	    else
	    {
		//displays what the user has chosen
		c.println ("You have chosen " + categoryList [category [categoryChoiceNum - 1]] + " for $" + (counter [categoryChoiceNum - 1] * 100 + 100) + ".");
	    }
	    c.println (); //spacing
	    //displays the question
	    c.println ("The question is: " + (questions [category [categoryChoiceNum - 1]] [counter [categoryChoiceNum - 1]]));
	    c.print ("Answer: "); //answer prompt
	    userAnswer = c.readLine (); //gets answer from user
	    //if the user answers correctly
	    if (userAnswer.equalsIgnoreCase (answers [category [categoryChoiceNum - 1]] [counter [categoryChoiceNum - 1]]))
	    {
		//displays correct message
		c.println ("That is correct! The answer is: " + (answers [category [categoryChoiceNum - 1]] [counter [categoryChoiceNum - 1]]) + ".");
		//if player 1 answered correctly
		if (turn == 1)
		{
		    //calls bankUpdate to calculate the change in money and stores it in bank1
		    bank1 = bankUpdate (bank1, true, doubleJ, doubleJBet, 1, counter [categoryChoiceNum - 1]);
		}
		//if player 2 answered correctly
		else
		{
		    //calls bankUpdate to calculate the change in money and stores it in bank2
		    bank2 = bankUpdate (bank2, true, doubleJ, doubleJBet, 1, counter [categoryChoiceNum - 1]);

		}
	    }
	    //if the user answered incorrectly
	    else
	    {
		//displays incorrect message
		c.println ("Sorry, that was incorrect. The answer is: " + (answers [category [categoryChoiceNum - 1]] [counter [categoryChoiceNum - 1]]) + ".");
		//if player 1 answered incorrectly
		if (turn == 1)
		{
		    //calls bankUpdate to calculate the change in money and stores it in bank1
		    bank1 = bankUpdate (bank1, false, doubleJ, doubleJBet, 1, counter [categoryChoiceNum - 1]);
		}
		//if player 2 answered incorrectly
		else
		{
		    //calls bankUpdate to calculate the change in money and stores it in bank2
		    bank2 = bankUpdate (bank2, false, doubleJ, doubleJBet, 1, counter [categoryChoiceNum - 1]);

		}
	    }
	    counter [categoryChoiceNum - 1]++; //counter increases
	    //resets double jeopardy to false
	    doubleJ = false;
	    c.println (); //spacing
	    //displays players' banks
	    c.println ("Player 1's Bank (" + name1 + "): $" + bank1); //player 1's bank
	    c.println ("Player 2's Bank (" + name2 + "): $" + bank2); //player 2's bank
	    c.println (); //spacing
	    pauseProgram (); //calls pauseProgram

	    //if player 1's turn is over
	    if (turn == 1)
	    {
		//turn changes to player 2
		turn = 2;
	    }
	    //if player 2's turn is over
	    else
	    {
		//turn changes to player 1
		turn = 1;
	    }
	}


	//level 1 ending messages
	c.setCursor (17, 1);
	c.println ("Congratulations! You have cleared the board and completed level 1.");
	c.println ();
	//if player 1 has more money in their bank
	if (bank1 > bank2)
	{
	    //displays that player 1 is winning
	    c.println ("Player 1 (" + name1 + ") is in the lead!");
	}


	//if player 2 has more money in their bank
	else if (bank2 > bank1)
	{
	    //displays that player 2 is winning
	    c.println ("Player 2 (" + name2 + ") is in the lead!");
	}


	//if player 1 and player 2 have the same amount of money in their banks
	else
	{
	    //displays that player 1 and 2 are tied
	    c.println ("Player 1 (" + name1 + ") and Player 2 (" + name2 + ") are tied for the win!");
	}


	//displays players' banks
	c.println ("Player 1's Bank (" + name1 + "): $" + bank1); //player 1's bank
	c.println ("Player 2's Bank (" + name2 + "): $" + bank2); //player 2's bank
	c.println ();
	c.println ("We will now continue on to Level 2.");
	c.println ();
	pauseProgram (); //calls pauseProgram

	//third screen in playGame (level 2)
	//random double jeopardy generator (level 2)
	doubleJeopardyA = ((int) (Math.random () * 5 + 1)); //generates random number from 1 to 5 to choose category of first double jeopardy
	doubleJeopardyB = ((int) (Math.random () * 5)); //generates random number from 0 to 4 to choose question of first double jeopardy
	//while loop
	while (true)
	{
	    doubleJeopardyC = ((int) (Math.random () * 5 + 1)); //generates random number from 1 to 5 to choose category of second double jeopardy
	    doubleJeopardyD = ((int) (Math.random () * 5)); //generates random number from 0 to 4 to choose question of second double jeopardy
	    //if both the double jeopardies are not the same
	    if (!(doubleJeopardyA == doubleJeopardyC && doubleJeopardyB == doubleJeopardyD))
	    {
		break; //exits the loop
	    }
	}
	//counters reset
	for (int i = 0 ; i < 5 ; i++)
	{
	    counter [i] = 0;
	}

	//turn transfers to player 2
	turn = 2;

	//while loop
	while (true)
	{
	    title (); //calls title
	    c.setColor (yellow); //sets color to yellow
	    c.setFont (sideNoteFont); //sets font to side note font
	    c.drawString ("*Side note: please do not enter", 5, 15);
	    c.drawString ("any spaces after your answers.", 5, 25);
	    //board graphics
	    //board
	    c.setColor (darkBlue); //dark blue
	    c.fillRect (60, 40, 520, 280); //board base
	    c.setColor (Color.black); //black
	    //board edges
	    c.fillRect (60, 40, 520, 8); //top edge
	    c.fillRect (60, 312, 520, 8); //bottom edge
	    c.fillRect (60, 40, 8, 280); //left edge
	    c.fillRect (572, 40, 8, 280); //right edge
	    //vertical dividers
	    for (int i = 0 ; i < 4 ; i++)
	    {
		c.fillRect (161 + i * 104, 40, 5, 280);
	    }
	    //horizontal dividers
	    for (int i = 0 ; i < 5 ; i++)
	    {
		c.fillRect (60, 90 + i * 44, 520, 5); //1
	    }
	    //money text on board
	    c.setColor (yellow); //yellow
	    c.setFont (moneyFont); //sets font
	    //$200
	    for (int i = 0 ; i < 5 ; i++)
	    {
		c.drawString ("$200", 77 + i * 103, 123); //$200
		c.drawString ("$400", 77 + i * 103, 169); //$400
		c.drawString ("$600", 77 + i * 103, 213); //$600
		c.drawString ("$800", 77 + i * 103, 258); //$800
		c.drawString ("$1000", 70 + i * 103, 303); //$1000
	    }
	    //category text on board
	    c.setFont (categoryFont); //sets font
	    for (int i = 0 ; i < 5 ; i++)
	    {
		c.drawString (categoryList [category [i + 5]], 73 + i * 102, 72);
	    }

	    //level 2 text
	    c.setFont (levelFont); //sets font
	    c.drawString ("Level 2", 280, 37); //draws text

	    //squares that cover questions that have been answered
	    c.setColor (darkBlue); //dark blue
	    //loop runs from 1 to 5
	    for (int i = 1 ; i < 6 ; i++)
	    {
		//loop runs from 0 to 4
		for (int x = 0 ; x < 5 ; x++)
		{
		    if (counter [x] >= i) //if category x question i has been answered
		    {
			//dark blue cover
			c.fillRect (70 + x * 102, 52 + i * 45, 91, 35);
		    }
		}
	    }

	    //if all the questions on the board have been answered
	    if (counter [0] == 5 && counter [1] == 5 && counter [2] == 5 && counter [3] == 5 && counter [4] == 5)
	    {
		break; //exits the loop (level 2)
	    }

	    //gameplay

	    c.setCursor (17, 1);
	    //if it is player 1's turn
	    if (turn == 1)
	    {
		c.println ("It is player 1's turn. (" + name1 + ")");
	    }
	    //if it is player 2's turn
	    else
	    {
		c.println ("It is player 2's turn. (" + name2 + ")");
	    }
	    //while loop
	    while (true)
	    {
		try
		{
		    c.setCursor (18, 1);
		    c.println ("Please choose a category (1, 2, 3, 4, or 5):"); //user prompt
		    c.setCursor (18, 46);
		    categoryChoiceNum = Integer.parseInt (c.readLine ()); //gets choice, parses category choice into an integer and stores it in categoryChoiceNum
		    //errortraps if the user did not enter 1, 2, 3, 4, or 5
		    if (categoryChoiceNum < 1 || categoryChoiceNum > 5)
		    {
			//error message
			new Message ("You must enter 1, 2, 3, 4, or 5.");
		    }
		    //errortraps if there are no more questions in that category
		    else if (counter [categoryChoiceNum - 1] == 5)
		    {
			//error message
			new Message ("There are no more questions in that category. Please choose a different category.");
		    }
		    else
		    {
			break; //exits the loop
		    }
		}
		catch (Exception e)  //catches parsing errors
		{
		    //error message
		    new Message ("You must enter an integer.");
		}
	    }
	    //if the user discovered the double jeopardy
	    if ((categoryChoiceNum == doubleJeopardyA && doubleJeopardyB == counter [categoryChoiceNum - 1]) || (categoryChoiceNum == doubleJeopardyC && doubleJeopardyD == counter [categoryChoiceNum - 1]))
	    {
		//changes double jeopardy to true
		doubleJ = true;
		//message
		c.println ("You have discovered the Double Jeopardy!");
		//displays players' banks
		c.println ("Player 1's Bank (" + name1 + "): $" + bank1); //player 1's bank
		c.println ("Player 2's Bank (" + name2 + "): $" + bank2); //player 2's bank
		//while loop
		while (true)
		{
		    //try catch statement
		    try
		    {
			c.setCursor (22, 1);
			//user prompt
			if (turn == 1) //if it is player 1's turn
			{
			    c.println ("How much money from your bank would you like to wager, player 1?");
			}
			else //if it is player 2's turn
			{
			    c.println ("How much money from your bank would you like to wager, player 2?");
			}
			c.setCursor (22, 66);
			doubleJBet = Integer.parseInt (c.readLine ()); //gets bet from user, parses it into an integer, and stores it
			//if it is player 1's turn
			if (turn == 1)
			{
			    //if the user entered a negative number
			    if (doubleJBet < 0)
			    {
				//error message
				new Message ("You cannot bet a negative amount. Please enter a positive integer or 0.");
			    }
			    //if the user has a bank less than $1000 and bets more than $1000
			    else if (bank1 < 1000 && doubleJBet > 1000)
			    {
				//error message
				new Message ("You can only bet up to $1000, since your bank is less than the top value on the board.");
			    }
			    //if the user has a bank greater than $1000 and bets higher than their bank
			    else if (bank1 >= 1000 && doubleJBet > bank1)
			    {
				//error message
				new Message ("You cannot bet more money than you have in your bank.");
			    }
			    else
			    {
				break; //exits the loop
			    }
			}
			//if it is player 2's turn
			else
			{
			    //if the user entered a negative number
			    if (doubleJBet < 0)
			    {
				//error message
				new Message ("You cannot bet a negative amount. Please enter a positive integer or 0.");
			    }
			    //if the user has a bank less than $1000 and bets more than $1000
			    else if (bank2 < 1000 && doubleJBet > 1000)
			    {
				//error message
				new Message ("You can only bet up to $1000, since your bank is less than the top value on the board.");
			    }
			    //if the user has a bank greater than $1000 and bets higher than their bank
			    else if (bank2 >= 1000 && doubleJBet > bank2)
			    {
				//error message
				new Message ("You cannot bet more money than you have in your bank.");
			    }
			    else
			    {
				break; //exits the loop
			    }
			}
		    }
		    catch (Exception e)  //catches errors in parsing
		    {
			//error message
			new Message ("You must enter a proper integer.");
		    }
		}
	    }
	    //if it is double jeopardy
	    if (doubleJ == true)
	    {
		//displays what the user has chosen
		c.println ("You have chosen " + categoryList [category [categoryChoiceNum + 4]] + " and bet $" + (doubleJBet) + ".");
	    }
	    else
	    {
		//displays what the user has chosen
		c.println ("You have chosen " + categoryList [category [categoryChoiceNum + 4]] + " for $" + (counter [categoryChoiceNum - 1] * 100 + 100) + ".");
	    }
	    c.println (); //spacing
	    //displays the question
	    c.println ("The question is: " + (questions [category [categoryChoiceNum + 4]] [counter [categoryChoiceNum - 1]]));
	    c.print ("Answer: "); //answer prompt
	    userAnswer = c.readLine (); //gets answer from user
	    //if the user answers correctly
	    if (userAnswer.equalsIgnoreCase (answers [category [categoryChoiceNum + 4]] [counter [categoryChoiceNum - 1]]))
	    {
		//displays correct message
		c.println ("That is correct! The answer is: " + (answers [category [categoryChoiceNum + 4]] [counter [categoryChoiceNum - 1]]) + ".");
		//if player 1 answered correctly
		if (turn == 1)
		{
		    //calls bankUpdate to calculate the change in money and stores it in bank1
		    bank1 = bankUpdate (bank1, true, doubleJ, doubleJBet, 2, counter [categoryChoiceNum - 1]);
		}
		//if player 2 answered correctly
		else
		{
		    //calls bankUpdate to calculate the change in money and stores it in bank2
		    bank2 = bankUpdate (bank2, true, doubleJ, doubleJBet, 2, counter [categoryChoiceNum - 1]);

		}
	    }
	    //if the user answered incorrectly
	    else
	    {
		//displays incorrect message
		c.println ("Sorry, that was incorrect. The answer is: " + (answers [category [categoryChoiceNum + 4]] [counter [categoryChoiceNum - 1]]) + ".");
		//if player 1 answered incorrectly
		if (turn == 1)
		{
		    //calls bankUpdate to calculate the change in money and stores it in bank1
		    bank1 = bankUpdate (bank1, false, doubleJ, doubleJBet, 2, counter [categoryChoiceNum - 1]);
		}
		//if player 2 answered incorrectly
		else
		{
		    //calls bankUpdate to calculate the change in money and stores it in bank2
		    bank2 = bankUpdate (bank2, false, doubleJ, doubleJBet, 2, counter [categoryChoiceNum - 1]);

		}
	    }
	    counter [categoryChoiceNum - 1]++; //counter increases
	    //resets double jeopardy to false
	    doubleJ = false;
	    c.println (); //spacing
	    //displays players' banks
	    c.println ("Player 1's Bank (" + name1 + "): $" + bank1); //player 1's bank
	    c.println ("Player 2's Bank (" + name2 + "): $" + bank2); //player 2's bank
	    c.println (); //spacing
	    pauseProgram (); //calls pauseProgram

	    //if player 1's turn is over
	    if (turn == 1)
	    {
		//turn changes to player 2
		turn = 2;
	    }
	    //if player 2's turn is over
	    else
	    {
		//turn changes to player 1
		turn = 1;
	    }
	}

	//level 2 ending messages
	c.setCursor (17, 1);
	c.println ("Congratulations! You have cleared the board and completed level 2.");
	c.println ();
	//if player 1 has more money in their bank
	if (bank1 > bank2)
	{
	    //displays that player 1 is winning
	    c.println ("Player 1 (" + name1 + ") is in the lead!");
	}


	//if player 2 has more money in their bank
	else if (bank2 > bank1)
	{
	    //displays that player 2 is winning
	    c.println ("Player 2 (" + name2 + ") is in the lead!");
	}


	//if player 1 and player 2 have the same amount of money in their banks
	else
	{
	    //displays that player 1 and 2 are tied
	    c.println ("Player 1 (" + name1 + ") and Player 2 (" + name2 + ") are tied for the win!");
	}


	//displays players' banks
	c.println ("Player 1's Bank (" + name1 + "): $" + bank1); //player 1's bank
	c.println ("Player 2's Bank (" + name2 + "): $" + bank2); //player 2's bank
	c.println ();
	c.println ("We will now continue on to Final Jeopardy.");
	c.println ();
	pauseProgram (); //calls pauseProgram

	//fourth screen in playGame (final jeopardy)
	title (); //calls title
	c.setColor (yellow); //yellow
	c.setFont (new Font ("Courier", 1, 35)); //sets font
	c.drawString ("Final Jeopardy", 170, 50); //draws final jeopardy sign
	c.setCursor (4, 1);
	c.println ("Welcome to Final Jeopardy, the last round of Jeopardy. Please look away when ");
	c.println ("your opponent is placing their wagers or answering the question.");
	c.println ();
	//displays players' banks
	c.println ("Player 1's Bank (" + name1 + "): $" + bank1); //player 1's bank
	c.println ("Player 2's Bank (" + name2 + "): $" + bank2); //player 2's bank
	c.println ();
	//while loop
	while (true)
	{
	    //try catch block
	    try
	    {
		c.setCursor (10, 1);
		//user prompt
		c.println ("How much money from your bank would you like to wager on the final question,");
		c.println ("player 1? Please enter a positive whole number or 0: ");
		c.setCursor (11, 54);
		finalBet1 = Integer.parseInt (c.readLine ()); //gets bet from user, parses it into an integer, and stores it
		//if the user entered a negative number
		if (finalBet1 < 0)
		{
		    //error message
		    new Message ("You cannot bet a negative amount. Please enter a positive integer or 0.");
		}
		//if the user bet more money than they have in their bank
		else if (bank1 >= 0 && finalBet1 > bank1)
		{
		    //error message
		    new Message ("You cannot bet more money than you have in your bank.");
		}
		//if the user is in debt
		else if (bank1 < 0 && finalBet1 != 0)
		{
		    //error message
		    new Message ("You are in debt. You can only bet $0.");
		}
		else
		{
		    break; //exits the loop
		}
	    }
	    catch (Exception e)  //catches errors in parsing
	    {
		//error message
		new Message ("You must enter a proper integer.");
	    }
	}


	//draws block to cover player 1's bet
	c.fillRect (425, 200, 220, 20);
	//while loop
	while (true)
	{
	    try
	    {
		c.setCursor (12, 1);
		//user prompt
		c.println ("How much money from your bank would you like to wager on the final question,");
		c.println ("player 2? Please enter a positive whole number or 0: ");
		c.setCursor (13, 54);
		finalBet2 = Integer.parseInt (c.readLine ()); //gets bet from user, parses it into an integer, and stores it
		//if the user entered a negative number
		if (finalBet2 < 0)
		{
		    //error message
		    new Message ("You cannot bet a negative amount. Please enter a positive integer or 0.");
		}
		//if the user bet more money than they have in their bank
		else if (bank2 >= 0 && finalBet2 > bank2)
		{
		    //error message
		    new Message ("You cannot bet more money than you have in your bank.");
		}
		//if the user is in debt
		else if (bank2 < 0 && finalBet2 != 0)
		{
		    //error message
		    new Message ("You are in debt. You can only bet $0.");
		}
		else
		{
		    break; //exits the loop
		}
	    }
	    catch (Exception e)  //catches errors in parsing
	    {
		//error message
		new Message ("You must enter a proper integer.");
	    }
	}


	//draws block to cover player 2's bet
	c.fillRect (425, 240, 220, 20);
	c.println ();
	//generates random number from 0 to 14
	finalQuestion = ((int) (Math.random () * 15));
	//displays the random question
	c.setCursor (14, 1);
	c.println ("The Final Jeopardy question is: " + finalJeopardyQ [finalQuestion]);
	//prompts player 1 for answer
	c.setCursor (17, 1);
	c.print ("Player 1, what is your answer? ");
	//gets answer from player 1
	finalAnswer1 = c.readLine ();
	//draws block to cover player 1's answer
	c.fillRect (245, 320, 500, 20);
	//prompts player 2 for answer
	c.setCursor (18, 1);
	c.print ("Player 2, what is your answer? ");
	//gets answer from player 2
	finalAnswer2 = c.readLine ();
	//draws block to cover player 2's answer
	c.fillRect (245, 340, 500, 20);
	c.println ();
	//if player 1 got the question correct
	if (finalAnswer1.equalsIgnoreCase (finalJeopardyA [finalQuestion]))
	{
	    //displays message
	    c.println ("Congratulation, player 1. You got the question correct! You answered: " + finalAnswer1 + ".");
	    c.println ("You have earned the amount of money you bet: $" + finalBet1 + ".");
	    //player 1 gains the money they bet in their bank
	    bank1 += finalBet1;
	}


	//if player 1 got the question incorrect
	else
	{
	    //displays message
	    c.println ("Sorry, player 1. You got the question incorrect. You answered: " + finalAnswer1 + ".");
	    c.println ("You have lost the amount of money you bet: $" + finalBet1 + ".");
	    //player 1 loses the money they bet in their bank
	    bank1 -= finalBet1;
	}


	//if player 2 got the question correct
	if (finalAnswer2.equalsIgnoreCase (finalJeopardyA [finalQuestion]))
	{
	    //displays message
	    c.println ("Congratulation, player 2. You got the question correct! You answered: " + finalAnswer2 + ".");
	    c.println ("You have earned the amount of money you bet: $" + finalBet2 + ".");
	    //player 2 gains the money they bet in their bank
	    bank2 += finalBet2;
	}


	//if player 2 got the question incorrect
	else
	{
	    //displays message
	    c.println ("Sorry, player 2. You got the question incorrect. You answered: " + finalAnswer2 + ".");
	    c.println ("You have lost the amount of money you bet: $" + finalBet2 + ".");
	    //player 2 loses the money they bet in their bank
	    bank2 -= finalBet2;
	}


	//displays the correct answer
	c.println ("The correct answer was: " + finalJeopardyA [finalQuestion] + ".");
	//displays players' banks
	c.println ();
	c.println ("Player 1's Bank (" + name1 + "): $" + bank1); //player 1's bank
	c.println ("Player 2's Bank (" + name2 + "): $" + bank2); //player 2's bank

	//congratulations to the winner(s)
	c.println (winnerDeclaration (bank1, bank2));

	c.println ();
	pauseProgram (); //calls pauseProgram
    }


    /*
    This method is the goodbye method. It displays ending messages and exits the program.
    */
    public void goodbye ()  //goodbye method
    {
	title (); //calls title
	//goodbye messages
	c.print (' ', 21);
	c.println ("Thank you for playing my Jeopardy game.");
	c.print (' ', 26);
	c.println ("Programmed by: Sophia Nguyen");
	c.println ();
	c.print (' ', 24);
	pauseProgram (); //calls pauseProgram
	c.close (); //closes console and program
    }


    /*
    This method is the main method. It controls the program and screen flow.
    Variable Dictionary
    Name            Type            Description/Purpose
    j               instance        creates instance variable of Jeopardy class
    */
    public static void main (String[] args)  //main method
    {
	Jeopardy j = new Jeopardy (); //creates instance variable of Jeopardy and constructs a new Jeopardy object
	j.splashScreen (); //executes splashScreen method
	//while loop
	while (true)
	{
	    j.mainMenu (); //executes mainMenu method
	    //if user chose option 1 in mainMenu
	    if (j.choice.equals ("1"))
	    {
		j.playGame (); //executes playGame method
		j.sortHighScores (); //executes sortHighScores method
	    }
	    //if user chose option 2 in mainMenu
	    else if (j.choice.equals ("2"))
	    {
		j.instructions (); //executes instructions method
	    }
	    //if user chose option 3 in mainMenu
	    else if (j.choice.equals ("3"))
	    {
		j.highScores (); //executes highScores method
	    }
	    //if user chose option 4 in mainMenu
	    else
	    {
		break; //exits the loop
	    }
	}
	j.goodbye (); //executes goodbye method
    }
}

//sources
//I used this website to learn about the split string method
//https://www.geeksforgeeks.org/split-string-java-examples/
//I used these websites to learn about exchange sorting techniques
//https://mathbits.com/MathBits/Java/arrays/Exchange.htm
//https://www.javacodex.com/Sorting/Exchange-Sort
//https://www.codingunit.com/exchange-sort-algorithm
//I used these websites to find trivia questions and answers
//https://www.triviaplaying.com/206-Animal-Trivia.htm
//https://www.triviaplaying.com/59_Free_food_trivia.htm
//https://www.triviaplaying.com/274-Mythology-questions.htm
//https://answersafrica.com/movie-trivia-questions-answers.html
//https://www.triviaquestionsnow.com/for/food-drink-trivia?page=6
//https://www.triviaquestionss.com/food-trivia-questions/
//https://www.shortlist.com/food-drink/the-ultimate-food-trivia-quiz/328970
//https://www.usefultrivia.com/science_trivia/index_xvi.html
//https://www.shortlist.com/food-drink/the-ultimate-food-trivia-quiz/328970
//https://www.usefultrivia.com/science_trivia/index_xxii.html
//https://www.ptable.com/
//https://en.wikipedia.org/wiki/Concurrency_(computer_science)
//https://jaxenter.com/know-your-java-trivia-134224.html
//https://www.enotes.com/homework-help/what-ways-have-piggys-glasses-been-used-novel-why-435999
//https://www.triviaquestionsnow.com/for/sports-trivia?page=12
//https://www.usefultrivia.com/literary_trivia/index.html
//http://laffgaff.com/music-trivia-questions-answers/
//http://www.usefultrivia.com/music_trivia/index_ii.html
//http://www.usefultrivia.com/history_trivia/index_vi.html
//http://www.jeopardyanswers.net/jeopardy-word-origins-answers/
//https://www.braingle.com/trivia/quiz.php?id=18770
//https://jeopardyquestions.com/category/word-origins
//https://en.wikipedia.org/wiki/Sydney_Opera_House
//https://en.wikipedia.org/wiki/Big_Ben
//https://en.wikipedia.org/wiki/Eiffel_Tower
//https://en.wikipedia.org/wiki/Leaning_Tower_of_Pisa
//https://en.wikipedia.org/wiki/Colosseum
//https://en.wikipedia.org/wiki/Mona_Lisa
//https://en.wikipedia.org/wiki/The_Scream
//https://www.usefultrivia.com/art_trivia/
//https://www.rd.com/culture/pop-culture-trivia/
//https://quizlet.com/145453646/pop-culture-trivia-quiz-flash-cards/
//https://www.amexessentials.com/2017-pop-culture-quiz/
//https://www.triviacountry.com/196-Artists-arts-trivia.htm
//https://triviabliss.com/categories/games/
//https://trivia.fyi/category/canada-trivia/
//https://www.rd.com/culture/hardest-jeopardy-clues/
//http://j-archive.com/
//https://mobilesyrup.com/2017/11/29/jeopardy-pokemon-question-stumps-contestants/

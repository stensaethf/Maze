/**
 * Maze.java
 * A Java program that reads a file and creates the corresponding maze. Solves
 * the maze if command is given.
 * Created by Frederik Roenn Stensaeth and Javier Moran
 * 04.13.14
 */

 
// Import Java classes
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


// Define class Maze. Can take a parameter: key. Methods include load, print,
//and solve. Contains inner class Square.
public class Maze {
	
	// Declare private variables to the class Maze.
	private List<List<Square>> row_list;
	private int row;
	private int column;
	private int start_col;
	private int start_row;
	private int finish_col;
	private int finish_row;
	
	// Define private class Square to the class Maze. Takes x and y as
	//parameters. Methods include hasTopWall, hasBottomWall, hasRightWall,
	//hasLeftWall, isEnd, isFirst, hasBeen, isPath, visited, setPath, setTop,
	//setBottom, setRight, setLeft, setStart, setEnd, dePath, getColumn, and
	//getRow.
	private class Square {
		
		// Declare private variables to the class Square.
		private boolean top_wall;
		private boolean bottom_wall;
		private boolean left_wall;
		private boolean right_wall;
		private boolean has_been;
		private boolean start;
		private boolean finish;
		private boolean path;
		private int row_n;
		private int col_n;

		// Define constructur of Square. Takes x and y as parameters.
		public Square( int x, int y) {
			
			// Initialize private variables
			top_wall = false;
			bottom_wall = false;
			left_wall = false;
			right_wall = false;
			has_been = false;
			start = false;
			finish = false;
			path = false;
			row_n = y;
			col_n = x;
		}

		// Define mathod hasTopWall. Returns top_wall boolean
		public boolean hasTopWall() {
			return top_wall;
		}

		// Define hasBottomWall method. Returns bottom_wall boolean
		public boolean hasBottomWall() {
			return bottom_wall;
		}

		// Define hasLeftWall method. Returns left_wall boolean
		public boolean hasLeftWall() {
			return left_wall;
		}

		// Define hasRightWall method. Returns right_wall boolean
		public boolean hasRightWall() {
			return right_wall;
		}

		// Define isFirst method. Returns start boolean
		public boolean isFirst() {
			return start;
		}

		// Define isEnd method. Returns finish boolean
		public boolean isEnd() {
			return finish;
		}

		// Define hasBeen method. Returns has_been boolean
		public boolean hasBeen() {
			return has_been;
		}

		// Define isPath method. Returns path boolean
		public boolean isPath() {
			return path;
		}

		// Define setTop method. Sets top_wall true
		public void setTop() {
			top_wall = true;
		}

		// Define setBottom method. Sets bottom_wall true
		public void setBottom() {
			bottom_wall = true;
		}

		// Define setLeft method. Sets left_wall true
		public void setLeft() {
			left_wall = true;
		}

		// Define setRight method. Sets right_wall true
		public void setRight() {
			right_wall = true;
		}

		// Define setStart method. Sets start true
		public void setStart() {
			start = true;
		}

		// Define setEnd method. Sets finish true
		public void setEnd() {
			finish = true;
		}

		// Define visited method. Sets has_been true
		public void visited() {
			has_been = true;
		}

		// Define setPath method. Sets path true
		public void setPath() {
			path = true;
		}

		// Define dePath method. Sets path false
		public void dePath() {
			path = false;
		}

		// Define getColumn method. Returns col_n
		public int getColumn() {
			return col_n;
		}

		// Define getRow method. Returns row_n
		public int getRow() {
			return row_n;
		}

		// Define getNeighbors method. Takes current (a Square) as parameter.
		//Returns a list of all possible neighboring Squares that are 'valid'
		//(not visited, not blocked off by a wall, and are in the maze).
		public List<Square> getNeighbors(Square current) {
			
			// Declare and initialize int variables and list of squares
			int col_n = current.getColumn();
			int row_n = current.getRow();
			List<Square> neighbors = new MysteryListImplementation<Square>();
			// If neighboring square is inside maze execute if body
			if (col_n + 1 < column) {
				// If current square does not have a right wall, neighboring
				//square to the right does not have a left wall, and square
				//to the right has not been visited, then add neighboring
				//square to list
				if (!current.hasRightWall()) {
					if (!row_list.at(row_n).at(col_n + 1).hasLeftWall()) {
						if (!row_list.at(row_n).at(col_n + 1).hasBeen()) {
							neighbors.add(row_list.at(row_n).at(col_n + 1));
						}
					}
				}
			}
			// If neighboring square is inside maze execute if body
			if (col_n - 1 >= 0) {
				// If current square does not have a left wall, neighboring
				//square to the left does not have a right wall, and square
				//to the left has not been visited, then add neighboring square
				//to list
				if (!current.hasLeftWall()) {
					if (!row_list.at(row_n).at(col_n - 1).hasRightWall()) {
						if (!row_list.at(row_n).at(col_n - 1).hasBeen()) {
							neighbors.add(row_list.at(row_n).at(col_n - 1));
						}
					}
				}
			}
			// If neighboring square is inside maze execute if body
			if (row_n + 1 < row) {
				// If current square does not have a bottom wall, neighboring
				//square does not have a top wall, and neighboring square
				//has not been visited, then add neighboring square
				//to list
				if (!current.hasBottomWall()) {
					if (!row_list.at(row_n + 1).at(col_n).hasTopWall()) {
						if (!row_list.at(row_n + 1).at(col_n).hasBeen()) {
							neighbors.add(row_list.at(row_n + 1).at(col_n));
						}
					}
				}
			}
			// If neighboring square is inside maze execute if body
			if (row_n - 1 >= 0) {
				// If current square does not have a top wall, neighboring
				//square to the top does not have a bottom wall, and square
				//to the top has not been visited, then add neighboring square
				//to list
				if (!current.hasTopWall()) {
					if (!row_list.at(row_n - 1).at(col_n).hasBottomWall()) {
						if (!row_list.at(row_n - 1).at(col_n).hasBeen()) {
							neighbors.add(row_list.at(row_n - 1).at(col_n));
						}
					}
				}
			}
			// Return list of valid neighboring squares
			return neighbors;
		}

	}

	// Define constructor of Maze. Takes no parameters. Initializes row_list
	public Maze() {
		row_list = new MysteryListImplementation<List<Square>>();
	}

	// Define alternative constructor of Maze. Takes key (String) as parameter.
	//Checks whether key is correct, if it is not prints an error message
	public Maze(String key) {
		// Check if key is correct
		if (key.compareTo("--solve") != 0) {
			// Print erorr message and exit system
			System.out.println("Usage: java Maze <mazeFile> [--solve]");
			System.exit(1);
		}
		// Initialize row_list
		row_list = new MysteryListImplementation<List<Square>>();
	}

	// Define load method. Takes fileName as parameter. Creates square objects
	//corresponding to description of maze given in fileName and adds them to
	//row_list. Returns nothing.
	public void load(String fileName) {
		// Declare and initialize scanner object
		Scanner reader = null;
		// Try to read fileName. Throw error if file not found and exit system
		try {
			reader = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			System.err.println(e);
			System.exit(1);
		}

		// Declare list, string variable
		String[] command_list;
		String line;
		// Declare and initialize int variable named count
		int count = -1;
		// Execute while loop as long as fileName has next line
		while (reader.hasNextLine()) {
			// Increment count
			count++;
			// Store next line of fileName
			line = reader.nextLine();
			// Execute if body if count is 0 (first line in file)
			if (count == 0) {
				// Split line and store in list
				command_list = line.split(" ");
				// Store values at index 0 and 1 of list
				row =  Integer.parseInt(command_list[1]);
				column = Integer.parseInt(command_list[0]);
			// Execute else if body if count is 1 (second line in file)
			} else if (count == 1) {
				// Split line and store in list
				command_list = line.split(" ");
				// Store values at index 0 and 1 of list
				start_col = Integer.parseInt(command_list[0]);
				start_row = Integer.parseInt(command_list[1]);
			// Execute else if body if count is 2 (third line in file)
			} else if (count == 2) {
				// Split line and store in list
				command_list = line.split(" ");
				// Store values at index 0 and1 of list
				finish_col = Integer.parseInt(command_list[0]);
				finish_row = Integer.parseInt(command_list[1]);
			// Execute else body if count is greater than 2
			} else {
				// Split line and store in list
				command_list = line.split("");
				// If maze description is missing or has too much information
				//print error message and exit system
				if ((command_list.length - 1) != column) {
					System.out.println("Incorrect number of columns.");
					System.exit(1);
				}
				// Declare and inistialize list of Squares
				List<Square> square_list;
				square_list = new MysteryListImplementation<Square>();
				// Loop over every item in command_list
				for (int i = 1; i < command_list.length; i++) {
					// Decalre and initialize Square object
					Square section = new Square(i - 1, count - 3);
					// If first item is invalid, print erorr message and exit
					//system
					if (i == 1) {
						if (!command_list[i].equals("L")) {
							if (!command_list[i].equals("|")) {
								System.out.print("Invalid maze bound. ");
								System.out.println("Maze must have left wall.");
								System.exit(1);
							}
						}
					}
					// If item describing bottom of maze is invalid, print
					//error message and exit system
					if (count == (row + 2)) {
						if (!command_list[i].equals("L")) {
							if (!command_list[i].equals("_")) {
								System.out.print("Invalid maze bound. ");
							    System.out.println("Maze must have bottom wall.");
								System.exit(1);
							}
						}
					}
					// Give properties to Square object depending on
					//description given in file
					if (command_list[i].equals("L")) {
						section.setBottom();
						section.setLeft();
					} else if (command_list[i].equals("_")) {
						section.setBottom();
					} else if (command_list[i].equals("|")) {
						section.setLeft();
					// Print error message and exit system if description
					//is invalid
					} else if (!command_list[i].equals("-")) {
						System.out.print("Invalid maze description ");
						System.out.println("character.");
						System.exit(1);
					}
					// Add square to list
					square_list.add(section);
				}
				// Add list to row_list
				row_list.add(square_list);
			}
		}
		// Print error message and exit system if incorrect number of rows
		if (count != (row + 2)) {
			System.out.println("Incorrect number of rows.");
			System.exit(1);
		}
		// Loop over rows and give right wall property to Squares on right-side
		//edge
		for (int i = 0; i < row; i++) {
			//row_list.at(i).at(0).setLeft();
			row_list.at(i).at(column - 1).setRight();
		}
		// Loop over columns and give top wall property to Squares on top-edge
		for (int i = 0; i < column; i++) {
			row_list.at(0).at(i).setTop();
			//row_list.at(row - 1).at(i).setBottom();
		}
		// Set start and end
		row_list.at(start_row).at(start_col).setStart();
		row_list.at(finish_row).at(finish_col).setEnd();
	}

	// Define print method. Takes no parameters. Prints the maze. Returns
	//nothing.
	public void print() {
		// Declare and initialize string
		String horizontal_edge = "";
		// Loop over columns and make string of correct length
		for (int i = 0; i < column; i++) {
			horizontal_edge += "+-----";
		}
		horizontal_edge += "+";
		// Print string to be top wall of maze
		System.out.println(horizontal_edge);
		// Declare string variables
		String walls2;
		String walls1;
		String b_walls;
		// Loop over lists of Squares in row_list
		for (List<Square> row_l : row_list) {
			// Initialize string variables
			walls2 = "";
			walls1 = "";
			b_walls = "";
			// Loop over Square objects in list of Squares
			for (Square col_l : row_l) {
				// If Square has left wall execute if body
				if (col_l.hasLeftWall()) {
					// Add section with left wall to string
					walls1 += "|     ";
					// If Square is start add section containing S to string
					if (col_l.isFirst()) {
						walls2 += "|  S  ";
					// If Square is end add section containing F to string
					} else if (col_l.isEnd()) {
						walls2 += "|  F  ";
					// If Square is part of path add section containing * to
					//string
					} else if (col_l.isPath()) {
						walls2 += "|  *  ";
					// If Square is not start, end, nor part of path add empty
					//section to string
					} else {
						walls2 += "|     ";
					}
				// If Square does not have left wall execute else body
				} else {
					// Add empty section to string
					walls1 += "      ";
					// If Square is start add section containing S to string
					if (col_l.isFirst()) {
						walls2 += "   S  ";
					// If Square is end add section containing F to string
					} else if (col_l.isEnd()) {
						walls2 += "   F  ";
					// If Square is part of path add section containing * to
					//string
					} else if (col_l.isPath()) {
						walls2 += "   *  ";
					// If Square is not start, end, nor part of path add empty
					//section to string
					} else {
						walls2 += "      ";
					}
				}
				// If Square has bottom wall add section containing wall to
				//string
				if (col_l.hasBottomWall()) {
					b_walls += "+-----";
				// If Square does not have bottom wall add empty section to
				//string
				} else {
					b_walls += "+     ";
				}
			}
			// Add right wall to strings
			walls1 += "|";
			walls2 += "|";
			b_walls += "+";
			// Print strings to make maze
			System.out.println(walls1);
			System.out.println(walls2);
			System.out.println(walls1);
			System.out.println(b_walls);
		}
	}

	// Define solve method. Takes no parameters. Finds how to get from start
	//Square to end Square, if possible. Returns nothing.
	private Stack<Square> getSolution() {
		// Declare and initiallize stack of Squares
		Stack<Square> mazeStack = new MysteryStackImplementation<Square>();
		// Declare Square object
		Square current;
		// Define list of Squares
		List<Square> neighbors;
		// Push start Square on to stack
		mazeStack.push(row_list.at(start_row).at(start_col));
		// Declare and initialize sentinel int variable
		int sentinel = 0;
		// Execute while loop as long as sentinel is 0
		while (sentinel == 0) {
		//while (!mazeStack.isEmpty()) {
	 		// If mazeStack is empty set sentinel to 1
	 		if (mazeStack.isEmpty()) {
	 			sentinel = 1;
	 		}
	 		// If item on top of stack is end Square set sentinel to 1
	 		else if (mazeStack.peek().isEnd()) {
				//mazeStack.clear();
				// Create temporaty stack of squares
				Stack<Square> temp = new MysteryStackImplementation<Square>();
				// Execute while loop as long as mazeStack is not empty
				while (!mazeStack.isEmpty()) {
					// If top item in mazeStack is part of path push it onto
					//temp stack
					if (mazeStack.peek().isPath()) {
						temp.push(mazeStack.pop());
					// If top top item in mazeStack is not part of path pop it
					} else {
						mazeStack.pop();
					}
				}
				// While temp stack is not empty execute while loop
				while (!temp.isEmpty()) {
					// Push top item in temp stack ontp mazeStack
					mazeStack.push(temp.pop());
				}
				// Set sentinel to 1
				sentinel = 1;
			// If end is not reached execute else body
			} else {
				// Initailize current to be top item of stack
				current = mazeStack.peek();
				// Mark current as visited
				current.visited();
				// Mark current as part of path
				current.setPath();
				// Call getNeighbors method on current with current as
				//parameter
				neighbors = current.getNeighbors(current);
				// Neighbors is not empty execute if body
				if (neighbors.length() > 0) {
					// Loop over Squares in neighbors list and push onto stack
					for (Square neighbor : neighbors) {
						mazeStack.push(neighbor);
					}
				// Execute else body is neighbors list is empty
				} else {
					// Set current to not be part of path
					current.dePath();
					// Pop Square from stack
					mazeStack.pop();
				}
			}
	    }
	    // Return mazeStack
	    return mazeStack;
	}

	// Define main function. Takes args as parameter. If correct commandline is
	//given, main function will load a maze and print it, solved or unsolved
	//depending on the commandline given.
	public static void main(String[] args) {
		// If commandline is not valid print usage message and exit system
		if ((args.length < 1) && (args.length > 2)) {
			System.err.println("Usage: java Maze <mazeFile> [--solve]");
			System.exit(1);
		}
		// If args is length 1 execute if body
		if (args.length == 1) {
			// Declare and initialize maze object
			Maze maze = new Maze();
			// Call load method on maze object and give args[0] as parameter
			maze.load(args[0]);
			// Call print method on maze object
			maze.print();
		// If args is length 2 execute else if body
		} else if (args.length == 2) {
			// Declare and initalize maze object
			Maze maze = new Maze(args[1]);
			// Call load method on maze object and give args[0] as parameter
			maze.load(args[0]);
			// Call solve method on maze object
			maze.getSolution();
			// Call print method on maze object
			maze.print();
		}
	}
}
//Ali Kirmani 30115539
//Ibrahim Ahmed 30125006
//Part 1 DFS for Assignment 2
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class p1
{
	//private static char cell[][];
	
	
	private static LinkedStack<cell> structure;
	private static LinkedQueue<cell> trailQueue;
	 static int coordX;
	 static int coordY;
	private static cell start;
	private static boolean moreToSearch = true;
	private static boolean complete = false;
	private static cell currentCell;
	private static cell cheese;
	public static int numRows ;
	public static int numCol;
	private static cell[][] maze;
	private static cell[][] mazeTrail;
	private static boolean flag;
	
	public static int newRow;
	public static int newCol;
	
	
    public static void main(String[] args) throws FileNotFoundException
    /*
     * reading the file in main
     */
    {       

    	  Scanner userInput = new Scanner(System.in);  // Create a Scanner object
    	    
          System.out.println("Enter the number of rows: ");
          numRows = Integer.parseInt(userInput.nextLine());  // Read user input
          System.out.println("Number of rows is: " + numRows);  // Output user input
        
          System.out.println("Enter the number of columns: ");
          numCol = Integer.parseInt(userInput.nextLine());  // Read user input
          System.out.println("Number of columns is: " + numCol);  // Output user input
          
       
        try {
        	 maze = new cell[numRows][numCol];
        	
            File myObj = new File("maze.txt");
            Scanner myReader = new Scanner(myObj);  
            int i = 0;
            int row = i;
            while (myReader.hasNextLine() && row < numRows) 
            {
            
              String data = myReader.nextLine();
              char[]mazeArray = data.toCharArray();

              for (int col = 0; col < numCol; col++) {
                  //char cellType = mazeArray[col];
				  char cellType = col < mazeArray.length ? mazeArray[col] : ' ';
                  maze[row][col] = new cell(row, col, cellType);
              }
              
              row++;
            }
            myReader.close();
          }
           catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        
        
        
        
        structure = new LinkedStack<>();
        trailQueue = new LinkedQueue<> ();
       
         start = startingPoint();
        
        if(start == null) {
        	System.out.println("Mouse not found");
        }
        
 
        
        displayMaze();
        
        start.setVisited(true);
        structure.push(start);
        
        
        p1.DFS();
       
    	
		if (cheese != null) {
			System.out.println("Cheese found");
			displayMaze();
	
		}
		
		else {
	        System.out.println("Cheese not found");
		}
		
    
    }
    
    
    
    
    //finding the starting point

    
    	public static cell startingPoint() {
    	
    		for(int row = 0; row < numRows; row ++) {
    			for (int col = 0; col < numCol; col++ ) {
    				if (maze[row][col].getCellType() == 'm') {
    					return maze[row][col];
    				}
    			}
    		}
    		
    		return null;
    		
    	}
    	

    	//DFS algorithm implementation
    	public static void DFS() {
    	
    		while(!structure.isEmpty() && moreToSearch) {
    			currentCell = structure.pop();
    			trailQueue.enqueue(currentCell);
    			
    			 newRow = currentCell.getRowIndex();
    			 newCol = currentCell.getColumnIndex();
    			
   	
    	        System.out.println("Visiting cell (" + newRow + ", " + newCol + ")");
    	        
				//Enqueues the movement to the trailQueue for future reference
    	        trailQueue.enqueue(maze[newRow][newCol]);

    			//Check if cheese is current cell
    			if (currentCell.getCellType() == 'c') {
    				cheese = currentCell;
    				moreToSearch = false;
    	            System.out.println("Cheese found at (" + newRow + ", " + newCol + ")");

    				break;
    			
    			}
    		
					//Check neighbors/surroundings
    	            checkNeighbors(newRow - 1, newCol, newRow, newCol);
    	            checkNeighbors(newRow + 1, newCol, newRow, newCol);
    	            checkNeighbors(newRow, newCol + 1, newRow, newCol);
    	            checkNeighbors(newRow, newCol - 1, newRow, newCol);
    	      
    	            
    	            displayMazeTrail();
    				
    		}
    		

    	} 
    	

    	
    	/*
    	 * This is for neighbors of the current cell, visits the cell 
    	 * and pushed it onto the stack
    	 */
    	
    	public static void checkNeighbors(int row, int col, int newRow, int newCol) {
    	    if (row >= 0 && col >= 0 && row < numRows && col < numCol) {
    	        cell neighbor = maze[row][col];
    	        if (!neighbor.isVisited() && neighbor.getCellType() != '1') {
    	            
    	        	
    	        	structure.push(neighbor);
    	            neighbor.setVisited(true);
    	            flag = true;

    	            
    	        }
    	         
    	    }
    	}
    	

    	
    	private static void displayMaze() {
    		for(int row = 0; row < numRows; row++ ) {
    			for(int col = 0; col < numCol; col++) {
    				System.out.print(maze[row][col].getCellType());
    				
    			}
    				System.out.println();	
    			
    		}
    		
    	}
    	
    	private static void displayMazeTrail() {
    		

    				
    		mazeTrail = new cell[numRows][numCol]; 
    		for(int row = 0; row < numRows; row++) {
    			for(int col = 0; col < numCol; col++) {
    				mazeTrail[row][col] = maze[row][col]; 
    				
    				
    			}
    		} 
    		
    		cell movement = trailQueue.dequeue();
    		
    		
    		if(newRow > movement.getRowIndex()) {
    			mazeTrail[newRow][newCol].setCellType('v');
    			 //System.out.println("Visiting cell (" + newRow + ", " + newCol + ")");
    		}
    		else if(newRow < movement.getRowIndex()) {
    			mazeTrail[newRow][newCol].setCellType('^');
    			 //System.out.println("Visiting cell (" + newRow + ", " + newCol + ")");
    		}
    		else if(newCol < movement.getColumnIndex()) {
    			mazeTrail[newRow][newCol].setCellType('<');
    			 //System.out.println("Visiting cell (" + newRow + ", " + newCol + ")");
    		}
    		else if(newCol > movement.getColumnIndex()) {
    			mazeTrail[newRow][newCol].setCellType('>');
    			 //System.out.println("Visiting cell (" + newRow + ", " + newCol + ")");
    		}
    		
    		
    	

    		for(newRow = 0; newRow < numRows; newRow++ ) {
    			for(newCol = 0; newCol < numCol; newCol++) {
    				System.out.print(mazeTrail[newRow][newCol].getCellType());
    				
    			}
    				System.out.println();	
    			
    		}
    		
    		
    }


}
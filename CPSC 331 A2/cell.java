

// this class is for initializing the cell structure and setting up everything in the maze

public class cell implements Comparable<cell>
{

	private int rowIndex;
	private int columnIndex;
	private char cellType;
	private boolean visited;
	
	
	public int getRowIndex() {
		return rowIndex;
	}
	
	public int getColumnIndex() {
		return columnIndex;
	}
	
	public cell (int rowIndex, int columnIndex, char cellType) {
		
		this.rowIndex = rowIndex;
		this.columnIndex = columnIndex;
		this.setCellType(cellType);
		this.visited = false;
		
	
	}

	public cell(int row, int col) {
		// TODO Auto-generated constructor stub
		this.rowIndex = row;
		this.columnIndex = col;
	}

	public boolean setVisited(boolean visited) {
		return this.visited = visited;
	}
	
	public boolean getVisited() {
		return visited;
	}
	
	public boolean isVisited() {
		return visited;
		
	}

	public char getCellType() {
		return cellType;
	}

	// will be used for setting the cell to the new characters
	
	public void setCellType(char cellType) {
		this.cellType = cellType;
	}

	@Override
	public int compareTo(cell o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}

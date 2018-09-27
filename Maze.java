import java.util.NoSuchElementException;

class Cell
{
	protected int x, y;
	protected boolean visited;

	protected Cell north = null;
	protected Cell south = null;
	protected Cell east = null;
	protected Cell west = null;

	//	The walls. 
	//	Key: 0 = North, 1 = South, 2 = East, 3 = West
	protected MazeList<Boolean> walls = new MazeList<>();

	public Cell(int x, int y)
	{
		visited = false;
		this.x = x;
		this.y = y;

		//	Make it so the 4 walls exist.
		walls.add(true);
		walls.add(true);
		walls.add(true);
		walls.add(true);
	}
	
	//	This is mostly just for debugging
	public String toString() 
	{ 
		String result = "(" + x + "," + y + ")\t" ; 
		return result;
		
	}
}


public class Maze
{

	//	Some directional constants
	private final int NORTH = 0;
	private final int SOUTH = 1;
	private final int EAST = 2;
	private final int WEST = 3;
	
	protected double endX;
	protected double endY;

	//	A list of cells
	public MazeList<MazeList<Cell>> cells = new MazeList<>();

	//	The dimensions of the maze
	private int size;

	//	Constructor
	public Maze(int size)
	{
		this.size = size;
		Cell newCell;

		//	Fill the list of cells
		for(int i = 0; i < size+2; i++)
		{
			cells.add(new MazeList<>());
			for(int j = 0; j < size+2; j++)
			{
				newCell = new Cell(i, j);
				cells.get(i).add(newCell);
			}
		}

		//	Set the neighbors
		for(int x = 1; x <= size; x++)
		{
			for(int y = 1; y <= size; y++)
			{
				get(x, y).east = get(x+1, y);
				get(x, y).west = get(x-1, y);
				get(x, y).north = get(x, y+1);
				get(x, y).south = get(x, y-1);
				
				
				//System.out.println("Cell at " + x + "," + y + " neighbors generated.");
			}
		}

		//	Set the boarder cells to visited
		for(int x = 0; x < size+2; x++)
		{
			get(x, 0).visited = true;
			get(x, size+1).visited = true;
			get(0, x).visited = true;
			get(size+1, x).visited = true;

		}

		//	deal with the walls
		generate(1, 1);
		
		//	Reset the inner cells' visited status
		for(int x = 1; x <= size; x++)
		{
			for(int y = 1; y <= size; y++)
			{
				get(x, y).visited = false;
			}
		}

		//	Draw the grid
		StdDraw.setXscale(0, size+2);
		StdDraw.setYscale(0, size+2);
		StdDraw.enableDoubleBuffering();
		draw();
	}
	
	
	//	This just simplifies 2D Arrays
	public Cell get(int x, int y)
	{
		
		if(x > size+1 || y > size+1 || x < 0 || y < 0)
			throw new NoSuchElementException("Error: Cell at coordinates (" + x + ", " + y + ") does not exist.");
		
		return(cells.get(x).get(y));
	}

	//	This method generates the maze
	private void generate(int x, int y)
	{
		MazeStack<Cell> gen = new MazeStack<>();

		gen.push(get(x, y));

		Cell current = gen.peek();
		

		while(!gen.isEmpty())
		{
			//System.out.println(current.x + "," + current.y);
			current = gen.pop();
			current.visited = true;

			while(!current.north.visited || !current.south.visited || !current.east.visited || !current.west.visited)
			{
				double r = StdRandom.uniform(4);
				

				if(r == NORTH && !current.north.visited)
				{
					current.walls.set(NORTH, false);
					current.north.walls.set(SOUTH, false);
					gen.push(current);
					gen.push(current.north);
					break;

				}
				else if(r == SOUTH && !current.south.visited)
				{
					current.walls.set(SOUTH, false);
					current.south.walls.set(NORTH, false);
					gen.push(current);
					gen.push(current.south);
					break;
				}
				else if(r == EAST && !current.east.visited)
				{
					current.walls.set(EAST, false);
					current.east.walls.set(WEST, false);
					gen.push(current);
					gen.push(current.east);
					break;
				}

				else if(r == WEST && !current.west.visited)
				{
					current.walls.set(WEST, false);
					current.west.walls.set(EAST, false);
					gen.push(current);
					gen.push(current.west);
					break;
				}
			}
		}
	}

	//	Draws the maze
	private void draw()
	{
		
		//	Set the end point here.
		endX = size/2.0;
		endY = size/2.0;
		
		endX = Math.round(endX);
		endY = Math.round(endY);
		
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledRectangle(0, 0, (size+2), (size+2));
		
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(endX + 0.5, endY + 0.5, 0.375);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(1.5, 1.5, 0.375);

		StdDraw.setPenColor(StdDraw.BLACK);
		for(int x = 1; x <= size; x++)
		{
			for(int y = 1; y <= size; y++)
			{
				if(get(x, y).walls.get(SOUTH))
					StdDraw.line(x, y, x+1, y);
				if(get(x, y).walls.get(NORTH))
					StdDraw.line(x, y+1, x+1, y+1);
				if(get(x, y).walls.get(WEST))
					StdDraw.line(x, y, x, y+1);
				if(get(x, y).walls.get(EAST))
					StdDraw.line(x+1, y, x+1, y+1);

			}
		}

		StdDraw.show();
		StdDraw.pause(1000);

	}
	
	//	Accessor for size
	public int size()
	{
		return size;
	}
}
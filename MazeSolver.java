
public class MazeSolver
{

	private int size;
	private boolean done = false;
	private Maze maze;
	private MazeQueue<Cell> solve = new MazeQueue<>();
	private MazeQueue<Cell> finished = new MazeQueue<>();

	public MazeSolver(Maze maze)
	{

		size = maze.size();
		this.maze = maze;

		solve.offer(maze.get(1, 1));
		solve();
	}

	//	Temporary, delete later.
	public MazeQueue<Cell> getQueue() { return solve; } 

	//	Solve!
	private void solve()
	{
		//	Start with the first cell
		Cell current = solve.peek();


		while(!solve.isEmpty() || done)
		{	
			
			finished.offer(solve.peek());
			current = solve.remove();

			current.visited = true;


			//	If we've reached the center...
			if(current.x == maze.endX && current.y == maze.endY)
			{
				done = true;
				
//				StdDraw.setPenColor(StdDraw.GREEN);
//				while(!finished.isEmpty())
//				{
//					Cell temp = finished.remove();
//					StdDraw.filledCircle(temp.x+0.5, temp.y+0.5, 0.25);
//				}
//				
//				StdDraw.show();
				
				
				break;
			}
			
			StdDraw.setPenColor(StdDraw.BLACK);
			StdDraw.filledCircle(current.x+0.5, current.y+0.5, 0.25);
			StdDraw.show();
			StdDraw.pause(5);

			//	North 
			if(!current.walls.get(0) && !current.north.visited)
			{
				solve.offer(current.north);
			} 
			
			//	East
			if(!current.walls.get(2) &&  !current.east.visited)
			{
				solve.offer(current.east);
			} 

			//	South
			if(!current.walls.get(1) && !current.south.visited)
			{
				solve.offer(current.south);
			} 

			//	West
			if(!current.walls.get(3) && !current.west.visited)
			{
				solve.offer(current.west);

			} 

			double R, G, B = 0;

			if(done) 
			{
				R = 0; G = 255; B = 0;
			} 
			
			else
			{
				double scale = (255 / (maze.size()-2/2));
				
				B = 255 - (scale*(Math.sqrt(Math.pow(current.x-(maze.size()/2), 2) + Math.pow(current.y-(maze.size()/2), 2))));
				R = (scale*(Math.sqrt(Math.pow(current.x-(maze.size()/2), 2) + Math.pow(current.y-(maze.size()/2), 2))));
				G = 0;
				
				//System.out.println("Scale: " + scale + "Colors: " + (int)R + " " + (int)B);
			}

			StdDraw.setPenColor((int)R,(int)G,(int)B);
			
			StdDraw.filledCircle(current.x+0.5, current.y+0.5, 0.25);
			StdDraw.show();
			StdDraw.pause(5);

		}
	}


}


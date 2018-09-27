/**
 * MAZE PROJECT - Paul von Ebers
 *
 * Redone from the given example, using custom-built lists, queues and stacks.
 *
 */

public class MazeProject
{
	
	public static void main(String[] args)
	{
		int n;
		if(args.length > 0)
			n = Integer.parseInt(args[0]);
		else
			n = 50;
		Maze maze = new Maze(n);
		MazeSolver solver = new MazeSolver(maze);
	}

}
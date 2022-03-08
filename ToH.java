
public class ToH {
	public static void Hanoi(int n, char start_rod, char goal_rod, char middle_rod) {
		if(n==1) {
			//if there is only 1 disk then it can be easily moved from start_rod to goal_rod in 1 move
			System.out.println("Move disk " +n+ " from " +start_rod+ " to " +goal_rod);
		}
		else if (n==0) {
			return;
		}
		else {
			//move 'n-1' disks from start_rod to middle_rod with the help of goal_rod
			Hanoi(n-1,start_rod, middle_rod, goal_rod);
			//move n-th disk from start_rod to goal_rod
			System.out.println("Move disk " +n+ " from " +start_rod+ " to " +goal_rod);
			//move 'n-1' disks from middle_rod to goal_rod with the help of start_rod
			Hanoi(n-1, middle_rod, goal_rod,start_rod);
		}
	}

	public static void main(String[] args) {
		int n=3;
		char start='S';
		char middle='M';
		char goal='G';
		Hanoi(n,start,goal,middle);
	}
}



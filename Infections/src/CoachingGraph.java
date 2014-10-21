/**
 * 
 */

/**
 * @author kg
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import Graph.edge;

public class CoachingGraph {

	//class variables
	private final int MAXUSERSIZE = 100;
	User[] userList = new User[MAXUSERSIZE];
	
	//hashmap
//	public Map<String, Coaches> townsMap = new HashMap<String, edge>();

	
	//edge
	private class Coaches{
		int UserID;
		Coaches next;
		
		Coaches(int UserID, Coaches next){
			this.UserID = UserID;
			this.next = next;
		}
	}
	
	//vertex
	private class User{
		int UserID;
		int siteVersion;
		Coaches adjacencyList;
		
		User(int UserID, Coaches startList){
			this.UserID = UserID;
			this.siteVersion = 0;
			this.adjacencyList = startList;
		}
	}

	
	public CoachingGraph(String inputFilename) throws FileNotFoundException {
		
		readGraph(inputFilename); 
	}
	
	private void readGraph(String inputFilename) throws FileNotFoundException{
		
		userList = null;
		
		Scanner input = new Scanner(new File(inputFilename));
		int totalUsers, userID; // info for set up to read in graph
		int userID1, userID2; // info for creating edges between users
		
		
		totalUsers = input.nextInt();
		
		//initialize all users in user vertex
		for(int i = 0; i < totalUsers; i++){
			userList[i] = 
		}
		
	}
	
	public static void main(String[] args) {
	
		System.out.println("hello world");

	}

}


//https://www.unf.edu/~wkloster/Algorithms/chapter4/graph.java

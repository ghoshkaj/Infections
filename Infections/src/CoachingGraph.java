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

public class CoachingGraph {

	//class variables
	private final int maxUserListSize = 101;
	private User[] userList = new User[maxUserListSize];
	private int totalUsers;

	
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
		
		totalUsers = readGraph(inputFilename); 
	}
	
	private int readGraph(String inputFilename) throws FileNotFoundException{
			
		Scanner input = new Scanner(new File(inputFilename));
		int totalUsers, userID; // info for set up to read in graph
		int user1, user2; // info for creating edges between users		
		
		totalUsers = input.nextInt() + 1;
		
		//initialize all users in user vertex
		for(int i = 1; i < totalUsers; i++){
			userList[i] = new User(input.nextInt(), null);
		}
		
		//read in coaching relations
		while(input.hasNext()){
//			user1 = getInt(input);
//			user2 = getInt(input);
			user1 = input.nextInt();
			user2 = input.nextInt();
			
			//add user1 to the front of user2's adjacency list
			//add user2 to the front of user1's adjacency list
			userList[user2].adjacencyList = new Coaches(user1,userList[user2].adjacencyList);
			userList[user1].adjacencyList = new Coaches(user2,userList[user1].adjacencyList);
		}
		
		input.close();
		
		return totalUsers;
	}
	
//	private int getInt(Scanner input){
//		while(!input.hasNextInt()){
//			input.next();
//		}
//		return input.nextInt();
//	}
	
	public void printGraph(){
		
		printGraph(userList, totalUsers);
	}
	
	private void printGraph(User[] userList, int totalUsers){
		
		Coaches temp;

		for(int i = 1; i < totalUsers; i++){
			//print out user name and site version
			System.out.print("User: " + userList[i].UserID);
			System.out.println("\tSite Version: " + userList[i].UserID);
			System.out.print("Coaches/coached by: " );
			
			//go into the linked list and print out members here
			temp = userList[i].adjacencyList;
			while(temp != null){
				System.out.print(" " + temp.UserID);
				temp = temp.next;
			}
			System.out.println();
			System.out.println();
		}
		
	}
	
//	private void 
	
	public static void main(String[] args) throws FileNotFoundException {
			
		CoachingGraph newGraph = new CoachingGraph("/home/kg/git/Infections/src/input.txt");
		
		newGraph.printGraph();

	}

}


//https://www.unf.edu/~wkloster/Algorithms/chapter4/graph.java

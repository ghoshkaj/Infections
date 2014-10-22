/**
 * 
 */

/**
 * @author Kajari Ghosh
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class CoachingGraph {

	//class variables
	private final int maxUserListSize = 101; //max vertices + 1 to account for array index 0
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

	//constructor
	public CoachingGraph(String inputFilename) throws FileNotFoundException {
		
		totalUsers = readGraph(inputFilename); 
	}
	
	//read graph
	private int readGraph(String inputFilename) throws FileNotFoundException{
			
		Scanner input = new Scanner(new File(inputFilename));
		int totalUsers, userID; // info for set up to read in graph
		int user1, user2; // info for creating edges between users		
		
//		totalUsers = input.nextInt() + 1; // so that when using totalUsers as end point for iterating i, i matches with userList index
		totalUsers = getInt(input) + 1;
		
		//initialize all users in userList
		for(int i = 1; i < totalUsers; i++){
			userList[i] = new User(getInt(input), null);
		}
		
		//read in coaching relations
		while(input.hasNext()){

			user1 = getInt(input);
			user2 = getInt(input);
			
			//add user1 to the front of user2's adjacency list
			//add user2 to the front of user1's adjacency list
			userList[user2].adjacencyList = new Coaches(user1,userList[user2].adjacencyList);
			userList[user1].adjacencyList = new Coaches(user2,userList[user1].adjacencyList);
		}
		
		input.close();
		return totalUsers;
	}
	
	//method to ignore text and read in only numbers
	private int getInt(Scanner input){
		while(!input.hasNextInt()){
			input.next();
		}
		return input.nextInt();
	}
	
	//public driver to print graph details
	public void printDetailedGraph(){
		printGraph(userList, totalUsers);
	}
	
	//print graph details
	private void printGraph(User[] userList, int totalUsers){
		
		Coaches temp;

		for(int i = 1; i < totalUsers; i++){
			//print out user name and site version
			System.out.print("User: " + userList[i].UserID);
			System.out.println("\tSite Version: " + userList[i].siteVersion);
			System.out.print("Coaches/coached by: " );
			
			//go into the linked list and print out members here
			temp = userList[i].adjacencyList;
			while(temp != null){
				System.out.print(" " + temp.UserID);
				temp = temp.next;
			}
			System.out.println("\n");
		}
		
	}
	
	//public driver to print graph details
	public void printSiteVersionGraph(){
		printSiteVersionGraph(userList, totalUsers);
	}
	
	//print graph details
	private void printSiteVersionGraph(User[] userList, int totalUsers){
		Coaches temp;
		for(int i = 1; i < totalUsers; i++){
			//print out user name and site version
			System.out.print("User: " + userList[i].UserID);
			System.out.println("\tSite Version: " + userList[i].siteVersion);
		}
		System.out.println("\n");
		
	}
	
	/*******************************************************
	 * QUESTION ONE - TOTAL INFECTION
	 * Depth First Search
	 * Attempt at Breadth First Searh (almost working)
	 *******************************************************/
	
	//public driver for recursive DFS totalInfection method//
	// only infects graph component with vertex userID in it
	public void totalInfectionDFS(int userID, int newSiteVersion){
		boolean[] visited = new boolean[totalUsers];
		totalInfectionDFS(userID, visited, newSiteVersion);
	}
	
	//totalInfection using recursive depth first search//
	// only infects graph component with vertex userID in it
	private void totalInfectionDFS(int userID, boolean[] visited, int siteVersion){
		visited[userID] = true;
		userList[userID].siteVersion = siteVersion;
		Coaches temp = userList[userID].adjacencyList;
		
		while(temp != null){
			if(!visited[temp.UserID]){
				totalInfectionDFS(temp.UserID, visited, siteVersion);
			}	
			temp = temp.next;
		}
	}
	
	
	
	

	
	//public driver for recursive BFS totalInfection method
	// only infects graph component with vertex userID in it
	public void totalInfectionBFS(int userID, int newSiteVersion){
		
		totallyInfectBFS(userID, newSiteVersion);
	}
	
	//totalInfection using recursive breadth first search
	// only infects graph component with vertex userID in it
	private void totallyInfectBFS(int userID, int newSiteVersion){
		
		LinkedList<User> queue = new LinkedList<User>();
	//	Deque<User> fringe = new ArrayDeque<User>();
		boolean[] visited = new boolean[totalUsers];
		visited[userID] = true;
		queue.add(userList[userID]);
		User tempUser = null;

		while(!queue.isEmpty()){

			tempUser = (User) queue.pollFirst();

			if(tempUser != null){ 
				tempUser.siteVersion = newSiteVersion;
			}
			
			Coaches tempCoaches = tempUser.adjacencyList;
			while(tempCoaches.next != null){
				if(!visited[tempCoaches.UserID]){
					visited[userID] = true;
					queue.add(userList[tempCoaches.UserID]);
				}	
				tempCoaches = tempCoaches.next;
			}
		}
	}
		
	/**************************************************************************
	 * QUESTION TWO - LIMITED INFECTION
	 * Possible implementations:
	 *	A) 1) Pre-process graph: i) count the number of graph components 
	 *	   and ii) count the number of vertices in each component
	 *	   2) Use pre-processed information to figure out which components 
	 *	   have closest total vertices to requested number of infected nodes
	 *	   3) Run TotalInfection on those graph components (start it on any 
	 *	   node within the required graph components)
	 *
	 *	B) 1) Run an implementation of limited cost search by modifying
	 *	   limited depth search and assigning all edges a cost of 1
	 *	   2) Using the number of edges to approximate the number of vertices
	 *	   gives a rough approximation of the desired infected nodes
	 *	
	 *	 Here is the implementation of A. It is quite time consuming though:
	 **************************************************************************/
	
	
	
	/* Initialize necessary data structures */
	
	// if every vertex is disconnected, there will be at most the 
	// maxUserListSize, again plus 1 to account for index i = 0
	int[][] graphComponentArray = new int[maxUserListSize][2]; 
	int totalGraphComponents = 0;
	
	
	/*1) Use DFS to count i) number of graph components, ii) starting 
	 *   vertex for each component and iii) number of vertices in each component */
		
	public void DFS(){
		boolean[] visited = new boolean[totalUsers];
		int graphComponents = 0;
		int vertexCount = 1; // + 1 to account for the starting vertex
		
		for(int userID = 1; userID < totalUsers; userID++){
			if(!visited[userID]){
				vertexCount = DFS(userID, visited, vertexCount);
				graphComponents++;
				graphComponentArray[graphComponents][0] = userID;
				graphComponentArray[userID][1]=vertexCount;
				vertexCount = 1;
			}
		}
		totalGraphComponents = graphComponents+1;
		System.out.println("graph components: " + graphComponents);
		
		for (int i = 1; i < totalGraphComponents; i++){
			System.out.print("Component " + i + " start vertex: "+ graphComponentArray[i][0]);
			System.out.println(" Vertex Count: " + graphComponentArray[i][1]);
		}
	
	}
	
	//counter DFS
	private int DFS(int userID, boolean[] visited, int vertexCounter){
		visited[userID] = true;
		Coaches temp = userList[userID].adjacencyList;
		
		while(temp != null){
			if(!visited[temp.UserID]){			
				vertexCounter = DFS(temp.UserID, visited, vertexCounter) + 1;
			}	
			temp = temp.next;
		}
		return vertexCounter;
	}
	
	// 2) limitedInfection
	public void limitedInfection(int n){
		
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
			
		CoachingGraph newGraph = new CoachingGraph("/home/kg/git/Infections/src/input1.txt");
	
		System.out.println("Detailed Graph");
		newGraph.printDetailedGraph();
		
		System.out.println("Site Versions");
		newGraph.printSiteVersionGraph();
		
		newGraph.totalInfectionDFS(3,3);//parameters are (userID, newSiteVersion)
		
		System.out.println("Site Versions after total infection:");
		newGraph.printSiteVersionGraph();

		System.out.println("Running DFS to extract graph pre-processing information");
		newGraph.DFS();

	}

}



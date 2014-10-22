Infections
==========
-----------------
To run the file:
-----------------
1) Download the files in the src folder (CoachingGraph.java, input.txt, input1.txt)
2) Open CoachingGraph.java
3) At the bottom, in the main method, just under the variable declarations, find this line:
     CoachingGraph newGraph = new CoachingGraph("/home/kg/git/Infections/src/input.txt");
4) Edit the path to the input file, so that it reflects the curent location of the files you just downloaded. 
     CoachingGraph newGraph = new CoachingGraph("/home/kg/Downloads/Infections/input.txt");
5) Close CoachingGraph.java
6) Compile and run CoachingGraph.java
7) After it has run from my input file, you can try with an input file of your own. 
   It will wait for you to enter the path and file name.
8) To create your own input file, format it in this way. 
    When listing the coaching relations, please make sure to put spaces between the hyphen and the numbers.
-----------
Total Users: 10

User Names/IDs:
1
2
3
4
5
6
7
8
9
10

Coaching relations:
7 - 3
7 - 2
3 - 4
3 - 5
8 - 6
8 - 9
6 - 1
1 - 9
9 - 10
------------

---------------------------
Comments on implementation
---------------------------
I implemented the graph using adjacency linked lists, because there are millions of users and 
linked lists are better able to handle large numbers, with less storage than adjecency matrices.

I used depth first search for TotalInfections, as that gives us a time complexity of O(|V| + |E|).

For LimitedInfections(ApproximateNumber) I outline two possible solutions:

A) 1) Pre-process graph: 
      i) count the number of graph components 
	    ii) count the number of vertices in each component
	    iii) store the above information and link each graph component to a start vertex for that component
	 2) Use pre-processed information to figure out which components have closest total vertices to requested number of infected nodes
	 3) Run TotalInfection on those graph components (start it on any node within the required graph components)
	    
B) 1) Run an implementation of limited cost search by modifying limited depth search and assigning all edges a cost of 1
	 2) Using the number of edges to approximate the number of vertices gives a rough approximation of the desired infected nodes
	 
The good thing about A is that it can accomplish LimitedInfections(ExactNumber) too.

However, for a graph with millions of users, B may be less costly as it accomplishes both finding and infecting at once.


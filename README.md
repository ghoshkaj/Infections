Infections
==========
-----------------
To run the file:
-----------------
1) Download the files in the src folder (CoachingGraph.java, input.txt, input1.txt)<br>
2) Open CoachingGraph.java<br>
3) At the bottom, in the main method, just under the variable declarations, find this line:<br>
     CoachingGraph newGraph = new CoachingGraph("/home/kg/git/Infections/src/input.txt");<br>
4) Edit the path to the input file, so that it reflects the curent location of the files you just downloaded. <br>
     CoachingGraph newGraph = new CoachingGraph("/home/kg/Downloads/Infections/input.txt");<br>
5) Close CoachingGraph.java<br>
6) Compile and run CoachingGraph.java<br>
7) After it has run from my input file, you can try with an input file of your own. <br>
   It will wait for you to enter the path and file name.<br>
8) To create your own input file, format it in this way. <br>
    When listing the coaching relations, please make sure to put spaces between the hyphen and the numbers.<br>
-----------<br>
Total Users: 10<br>
<br>
User Names/IDs:<br>
1<br>
2<br>
3<br>
4<br>
5<br>
6<br>
7<br>
8<br>
9<br>
10<br>
<br>
Coaching relations:<br>
7 - 3<br>
7 - 2<br>
3 - 4<br>
3 - 5<br>
8 - 6<br>
8 - 9<br>
6 - 1<br>
1 - 9<br>
9 - 10<br>
------------<br>
<br>
---------------------------<br>
Comments on implementation<br>
---------------------------<br>
I implemented the graph using adjacency linked lists, because there are millions of users and 
linked lists are better able to handle large numbers, with less storage than adjecency matrices.
<br>
I used depth first search for TotalInfections, as that gives us a time complexity of O(|V| + |E|).<br>
<br>
For LimitedInfections(ApproximateNumber) I outline two possible solutions:<br>
<br>
A) 1) Pre-process graph: <br>
      i) count the number of graph components <br>
      ii) count the number of vertices in each component<br>
      iii) store the above information and link each graph component to a start vertex for that component<br>
   2) Use pre-processed information to figure out which components have closest total vertices to requested number of infected nodes<br>
   3) Run TotalInfection on those graph components (start it on any node within the required graph components)<br>
	    <br>
B) 1) Run an implementation of limited cost search by modifying limited depth search and assigning all edges a cost of 1<br>
	 2) Using the number of edges to approximate the number of vertices gives a rough approximation of the desired infected nodes<br>
	 <br>
The good thing about A is that it can accomplish LimitedInfections(ExactNumber) too.<br>
<br>
However, for a graph with millions of users, B may be less costly as it accomplishes both finding and infecting at once.<br>


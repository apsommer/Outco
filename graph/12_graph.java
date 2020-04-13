package com.sommerengineering.library.graph;

/**
 *  Homework 13 - Graph
 *
 *  Problem: Graph
 *
 *  Prompt: Create a directed graph class using adjacency list edge
 *          representation.
 *
 *  The Graph will have the following properties:
 *
 *             storage: {HashMap} - the keys represent unique vertex ids {Integer}
 *                      and the values are Lists representing the
 *                      vertex ids of its neighbors.
 *
 *  The Graph will also have the following methods:
 *
 *           addVertex: Method that accepts a vertex id {Integer} and adds the
 *                      vertex to the graph.  Return true if success and false
 *                      if failed.
 *
 *                      Input:    id {Integer}
 *                     Output:    {Boolean}
 *
 *        removeVertex: Method that accepts a vertex id and removes the vertex
 *                      with the matching id. Return true if success and false
 *                      if failed.
 *
 *                      Input:    id {Integer}
 *                     Output:    {Boolean}
 *
 *             addEdge: Method that accepts two vertex ids and creates a
 *                      directed edge from the first vertex to the second.
 *                      Returns true if success and false if failed.
 *
 *                      Input:    id1 {Integer}
 *                      Input:    id2 {Integer}
 *                     Output:    {Boolean}
 *
 *          removeEdge: Method that accepts two vertex id's and removes the
 *                      directed edge from the first vertex to the second.
 *                      Returns true if success and false if failed.
 *
 *                       Input:    id1 {Integer}
 *                       Input:    id2 {Integer}
 *                      Output:    {Boolean}
 *
 *           isVertex:  Method that accepts an id, and returns whether the vertex
 *                      exists in the graph.
 *
 *                       Input:    id {Integer}
 *                      Output:   {Boolean}
 *
 *           neighbors: Method that accepts a vertex id, and returns all of the
 *                      edges of that vertex.
 *
 *                       Input:    id {Integer}
 *                      Output:   {List<Integer>}
 *
 *
 *  Input:  {Void}
 *  Output: {Graph}
 *
 *  Notes:   The notation for Time/Auxiliary Space Complexity for graphs
 *           is slightly different since certain functions depend on
 *           either the number of vertices, edges or even both
 *
 *           O(V) = Linear w/ respect to the number of vertices
 *           O(E) = Linear w/ respect to the number of edges
 *           O(V+E) = Linear w/ respect to the number of vertices * number of edges
 */

import java.util.*;

class Graph {

  public Map<Integer, List<Integer>> storage = new HashMap<>();

  //   Time Complexity: O(1)
  //   Auxiliary Space Complexity: O(1)
  public boolean addVertex(Integer id) {

    storage.put(id, new ArrayList<Integer>());
    return true;
  }

  public boolean removeVertex(Integer id) {

    // check the hashmap for this vertex
    if (storage.containsKey(id)) {

      // remove successful
      storage.remove(id);
      return true;
    }

    // vertex does not exist, therefore it can not be removed
    return false;
  }

  //   Time Complexity: O(1) lookup into hashmap, O(1) to append item to list
  //   Auxiliary Space Complexity: O(1)
  public boolean addEdge(Integer id1, Integer id2) {

    // check both vertices id1 and id2 exist, then add the edge to id1 edge list
    if (storage.containsKey(id1) && storage.containsKey(id2)) {
      storage.get(id1).add(id2);
      return true;
    }

    // vertex id1 does not exist, therefore edge not added
    return false;
  }

  // Time Complexity:
  // Auxiliary Space Complexity:
  public boolean removeEdge(Integer id1, Integer id2) {
    // YOUR WORK HERE
    return true;
  }

  //   Time Complexity:
  //   Auxiliary Space Complexity:
  public boolean isVertex(Integer id) {
    // YOUR WORK HERE
    return true;
  }

  // Time Complexity:
  // Auxiliary Space Complexity:
  public List<Integer> neighbors(Integer id) {
    // YOUR WORK HERE
    return new ArrayList<Integer>();
  }

}


////////////////////////////////////////////////////////////
///////////////  DO NOT TOUCH TEST BELOW!!!  ///////////////
////////////////////////////////////////////////////////////

// use the Main class to run the test cases
class Main {
  private int[] testCount;

  // an interface to perform tests
  public interface Test {
    public boolean execute();
  }

  public static void main(String[] args) {

    int[] testCount = {0, 0};
    System.out.println("Graph Class");

    assertTest(testCount, "able to create an instance", new Test() {
      public boolean execute() {
        Graph graph = new Graph();
        return graph instanceof Graph;
      }
    });

    assertTest(testCount, "has storage field", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.getClass().getDeclaredField("storage");
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "storage property initialized to a hash map", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          return graph.getClass().getDeclaredField("storage").getType().isAssignableFrom(Map.class) ||
                 graph.getClass().getDeclaredField("storage").getType().isAssignableFrom(HashMap.class);
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "storage property initialized as empty", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          return graph.storage.size() == 0;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Graph addVertex method");

    assertTest(testCount, "has addVertex method", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.getClass().getMethod("addVertex", new Class[] { Integer.class });
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "is able to add a vertex", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.addVertex(5);
          return graph.storage.size() == 1;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "vertices store an list or set of connections", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.addVertex(5);
          return graph.storage.get(5) instanceof List ||
                 graph.storage.get(5) instanceof ArrayList;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "is able to add two vertices", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.addVertex(5);
          graph.addVertex(10);
          return graph.storage.size() == 2 &&
                 graph.storage.get(10) instanceof List ||
                 graph.storage.get(10) instanceof ArrayList;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "does not add in duplicate vertex", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.addVertex(5);
          graph.addVertex(5);
          return graph.storage.size() == 1 && graph.storage.get(5).size() == 0;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Graph removeVertex method");

    assertTest(testCount, "has removeVertex method", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.getClass().getMethod("removeVertex", new Class[] { Integer.class });
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "able to remove a vertex within graph", new Test() {
      public boolean execute() {
        Graph graph = new Graph();
        graph.addVertex(5);
        graph.removeVertex(5);
        return graph.storage.size() == 0 && graph.storage.get(5) == null;
      }
    });

    assertTest(testCount, "does not remove vertex that does not exist", new Test() {
      public boolean execute() {
        Graph graph = new Graph();
        graph.addVertex(5);
        boolean result = graph.removeVertex(10);
        return graph.storage.size() == 1 && graph.storage.get(5) != null;
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Graph addEdge method");

    assertTest(testCount, "has addEdge method", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.getClass().getMethod("addEdge", new Class[] { Integer.class, Integer.class });
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "able to create an edge between two vertices that exist", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.addVertex(5);
          graph.addVertex(10);
          graph.addEdge(5, 10);
          return graph.storage.get(5).size() == 1 &&
                 graph.storage.get(10).size() == 0;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "addVertex returns true if vertices are added", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.addVertex(5);
          graph.addVertex(10);
          return graph.addEdge(5, 10) == true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "does not create an edge when one of the vertices does not exist", new Test() {
      public boolean execute() {
        Graph graph = new Graph();
        graph.addVertex(5);
        boolean result = graph.addEdge(5, 10);
        return graph.storage.get(5).size() == 0 && result == false;
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Graph removeEdge method");

    assertTest(testCount, "has removeEdge method", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.getClass().getMethod("removeEdge", new Class[] { Integer.class, Integer.class });
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "able to remove an edge between two vertices", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.addVertex(5);
          graph.addVertex(10);
          graph.addEdge(5, 10);
          graph.removeEdge(5, 10);
          return graph.storage.get(5).size() == 0 && graph.storage.size() == 2;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "returns true if able to remove an edge", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.addVertex(5);
          graph.addVertex(10);
          graph.addEdge(5, 10);
          return graph.removeEdge(5, 10) == true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "does not remove edge when edge does not exist", new Test() {
      public boolean execute() {
        try{
          Graph graph = new Graph();
          graph.addVertex(5);
          graph.addVertex(10);
          graph.addEdge(5, 10);
          graph.removeEdge(6, 10);
          return graph.storage.get(5) != null && graph.storage.get(5).size() == 1;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "returns false if edge does not exist", new Test() {
      public boolean execute() {
        try{
          Graph graph = new Graph();
          graph.addVertex(5);
          graph.addVertex(10);
          graph.addEdge(5, 10);
          return graph.removeEdge(6, 10) == false;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Graph isVertex method");

    assertTest(testCount, "has isVertex method", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.getClass().getMethod("isVertex", new Class[] { Integer.class });
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "returns true when a vertex exists", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.addVertex(5);
          graph.isVertex(5);
          return graph.isVertex(5) == true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "returns false when a vertex does not exist'", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.addVertex(5);
          return graph.isVertex(10) == false;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");


    testCount[0] = 0;
    testCount[1] = 0;
    System.out.println("Graph neighbors method");

    assertTest(testCount, "has neighbors method", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.getClass().getMethod("neighbors", new Class[] { Integer.class });
          return true;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "returns null if the vertex does not exist", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          return graph.neighbors(5) == null;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "returns an empty array if vertex has no edges", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.addVertex(5);
          return graph.neighbors(5).size() == 0;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    assertTest(testCount, "returns neighbors if edges exist for a vertex", new Test() {
      public boolean execute() {
        try {
          Graph graph = new Graph();
          graph.addVertex(5);
          graph.addVertex(10);
          graph.addVertex(15);
          graph.addVertex(20);
          graph.addEdge(5, 10);
          graph.addEdge(5, 15);
          graph.addEdge(5, 20);
          return graph.neighbors(5).size() == 3;
        } catch (Exception e) {
          e.printStackTrace();
          return false;
        }
      }
    });

    System.out.println("PASSED: " + testCount[0] + " / " + testCount[1] + "\n\n");
  }


  // function for checking if arrays contain same elements
  // (do not need to be in the same order)
  private static boolean arraysMatching(Integer[] arr1, Integer[] arr2) {
    if (arr1.length != arr2.length) {
      return false;
    } else {
      Arrays.sort(arr1);
      Arrays.sort(arr2);

      for (int i = 0 ; i < arr1.length ; i++) {
        if (arr1[i] != arr2[i]) {
          return false;
        }
      }

      return true;
    }
  }

  // do not edit below, this is to wrap the test and check for exceptions
  private static void assertTest(int[] count, String name, Test test) {
    String pass = "false";
    count[1]++;

    try {
      if (test.execute()) {
        pass = " true";
        count[0]++;
      }
    } catch(Exception e) {}
    String result = "  " + (count[1] + ")   ").substring(0, 5) + pass + " : " + name;
    System.out.println(result);
  }
}

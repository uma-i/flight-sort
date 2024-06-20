import java.lang.reflect.Array;
import java.util.*;

public class Graph {
    private int numNode;
    private LinkedList<Edge> [] adjacency;
    private ArrayList<String> nodes;
    public Graph(){
        numNode = 10;
        adjacency = new LinkedList[10];
        nodes = new ArrayList<String>(10);
        for(int i=0;i<10;++i){
            nodes.add("");
        }
    }

    public Graph(int N){
        numNode = N;
        adjacency = new LinkedList[N];
        for(int i=0;i<N; i++){
            adjacency[i]=new LinkedList<Edge>();
        }
        nodes = new ArrayList<String>(N);
        for(int i=0;i<N;++i){
            nodes.add("");
        }
    }

    public void addEdge(String src, String dest, int time, int cost){
        Edge e = new Edge(src,dest,cost,time);

        boolean sInArr=nodes.contains(src);//check if in the list of all nodes
        boolean dInArr=nodes.contains(dest);
        addToAdjacencyList(src, e, sInArr);
        e = new Edge(dest,src,cost,time);// undirected graph
        addToAdjacencyList(dest, e, dInArr);
    }

    private void addToAdjacencyList(String name, Edge e, boolean inArr) {
        int srcIndex;
        if(inArr){//check the list first
            srcIndex=nodes.indexOf(name);
            adjacency[srcIndex].add(e); // add to linked list
        }
        else{// create new node in adjacency list
            for(int i=0; i<numNode; i++){//loop through array to find empty place to store node
                if(nodes.get(i).equals("")){
                    nodes.set(i, name);
                    adjacency[i].add(e);//add to linked list
                    break;
                }
            }
        }
    }

    public int getNumNode(){
        return numNode;
    }

    public ArrayList<String> getNodes(){
        return nodes;
    }

    public Edge getEdge(String src, String dest){
        int srcIndex=nodes.indexOf(src);
        int destIndex =0;
        for(int i=0; i<numNode;i++){
            if((adjacency[srcIndex].get(i)).getDest().equals(dest)){
                //adjacency[srcIndex].indexOf(dest);
                //check index of destination
                destIndex = i;
            }
        }
        return adjacency[srcIndex].get(destIndex);
    }

    public ArrayList<Edge> getAdjacent(String src) {
        Edge e = new Edge();
        ArrayList<Edge> adjNodes = new ArrayList<>();
        int srcIndex=nodes.indexOf(src);
        int destIndex = 0;

        for(int i=0; i<adjacency[srcIndex].size();i++){
            adjNodes.add(adjacency[srcIndex].get(i));
        }
        return adjNodes;
    }


    public List<Path> DFS(String source, String dest){
        Stack<String> path = new Stack<>();
        return DFSRecursive(source, dest, path, 0, 0);
    }

    public List<Path> DFSRecursive(String curr, String dest, Stack<String> path, int pathTotalCost, int pathTotalTime){
        path = (Stack<String>) path.clone();
        path.push(curr);
        //System.out.println(curr + " : " + dest);
        if (curr.equals(dest)) {
            ArrayList<Path> ret2 = new ArrayList<>();
            ret2.add(new Path(path, pathTotalCost, pathTotalTime));
            //System.out.println("found path: " + path + " dist: " + pathTotalCost);
            return ret2;
        }

        ArrayList<Edge> adjList = getAdjacent(curr);
        ArrayList<Path> ret = new ArrayList<>();

        for (Edge e : adjList) {
            String next = e.getDest();
            if (!path.contains(next)) {
                ret.addAll(DFSRecursive(next, dest, path, pathTotalCost + e.getCost(), pathTotalTime + e.getTime()));
            }
        }
        return ret;
    }

    public void printPaths(List<Path> pathsToPrint, String timeOrCost){ //Sorts and then prints 3 paths
        int arr[]= new int[3];
        List<Path> pathsInOrder = pathsToPrint;
        Arrays.fill(arr, Integer.MAX_VALUE);

        if(timeOrCost.equals("C")){
            for(int i=0; i< pathsToPrint.size(); i++){
                if(pathsToPrint.get(i).getCost()<arr[2]){
                    arr[2] = pathsToPrint.get(i).getCost();
                    Arrays.sort(arr);
                }
            }
            for(int i=0; i< 3; i++){ // check what paths match with the weights in the array
                for(int j=0;j<pathsToPrint.size();j++)
                    if(arr[i]==pathsToPrint.get(j).getCost())
                        pathsInOrder.set(i,pathsToPrint.get(j));
            }
            for(int j=0;j<pathsToPrint.size(); j++){
                System.out.println(pathsInOrder.get(j));
            }
        }
        else{
            for(int i=0; i< pathsToPrint.size(); i++){
                if(pathsToPrint.get(i).getTime()<arr[2]){
                    arr[2] = pathsToPrint.get(i).getTime();
                    Arrays.sort(arr);
                }
            }
            for(int i=0; i< pathsToPrint.size(); i++){
                    if(arr[i]==pathsToPrint.get(i).getTime())
                        pathsInOrder.set(i,pathsToPrint.get(i));
            }
            for(int j=0;j<pathsToPrint.size(); j++){
                System.out.println(pathsInOrder.get(j));
            }
        }
    }

}

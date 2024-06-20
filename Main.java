import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.Stack;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Input files: ");
        String flightDataFileName = in.next();
        String flightPlansFileName = in.next();
        String OutputFileName = in.next();
        // call functions to get the paths
        Graph g = new Graph();
        try {
            g = parseDataFile(flightDataFileName);
        }
        catch(FileNotFoundException e){
            System.out.println("Flight data file is invalid");
        }
        try {
            findPaths(g, flightPlansFileName, OutputFileName);
        }
        catch(FileNotFoundException e){
            System.out.println("Flight plans file is invalid");
        }
        //create output file using paths
    }

    public static Graph parseDataFile(String flightDataFile) throws FileNotFoundException {
        File flightData = new File(flightDataFile);
        Scanner readFile = new Scanner(flightData);
        int numFlights = Integer.parseInt(readFile.nextLine());
        //create graph w/edges from file
        Graph g = new Graph(numFlights);
        //loop through each line to get data from file
        String line = "";
        for(int i=0; i<numFlights;i++){
            line = readFile.nextLine();
            int l = line.indexOf('|');
            String source = line.substring(0,l);
            String dest = line.substring(l+1, line.indexOf('|',l+1));
            l = line.indexOf('|',l+1);
            int cost = Integer.parseInt(line.substring(l+1, line.indexOf('|',l+1)));
            l = line.indexOf('|',l+1);
            int time = Integer.parseInt(line.substring(l+1));
            g.addEdge(source, dest, time, cost);//add edge for each line
        }
        return g;
    }



    public static void findPaths(Graph g, String flightPlansFile, String OutputFileName) throws FileNotFoundException{
        // traverse graph
        // create array
        int cost[] = new int[g.getNumNode()];
        int time[] = new int[g.getNumNode()];
        Boolean visited[] = new Boolean[g.getNumNode()];

        // parse file
        File flightPlans = new File(flightPlansFile);
        Scanner readFile = new Scanner(flightPlans);
        int numPlans = Integer.parseInt(readFile.nextLine());
        String line,source = "",dest = "",timeorcost="";

        File outfile = new File(OutputFileName);

        PrintStream stream = new PrintStream(outfile);
        System.setOut(stream);// print to this file

        for(int i=0; i<numPlans;i++){
            line = readFile.nextLine();
            int l = line.indexOf('|');
            source = line.substring(0,l);
            dest = line.substring(l+1, line.indexOf('|',l+1));
            l = line.indexOf('|',l+1);
            timeorcost = line.substring(l+1);

            int j=i+1;
            System.out.println("Flight "+ j +": " + source + ", " + dest);
            List<Path> Paths = g.DFS(source, dest);
            g.printPaths(Paths, timeorcost);
        }

    }




}

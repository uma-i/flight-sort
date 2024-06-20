public class Edge {
    private int time, cost;
    private String source, destination;

    public Edge(){
    }

    public Edge(String src, String dest, int c, int t){
        source = src;
        destination = dest;
        cost = c;
        time = t;
    }

    public boolean equals(Edge e){
        return e.source.equals(source) && e.destination.equals(destination) && e.time == time && e.cost == cost;
    }

    public String getSource(){
        return source;
    }

    public String getDest(){
        return destination;
    }

    public int getTime(){ return time; }

    public int getCost(){
        return cost;
    }
}

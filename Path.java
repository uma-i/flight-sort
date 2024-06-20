import java.util.Stack;

public class Path {
    Stack<String> path;
    int pathCost;
    int pathTime;

    public Path() {
        path = new Stack<String>();
        pathCost = 0;
        pathTime = 0;
    }

    public Path(Stack<String> p, int c, int t) {
        path = p;
        pathCost = c;
        pathTime = t;
    }

    public Stack<String> getStack() {
        return path;
    }

    public int getCost() {
        return pathCost;
    }

    public int getTime() {
        return pathTime;
    }

    public String toString(){
        String p = "";
        for(int i=0; i<path.size();i++){
            if(i<path.size()-1){
                p += path.get(i) + " -> ";
            }
            else{
                p += path.get(i);
                p+= ". Time: " + pathTime + " Cost: " + pathCost;
            }
        }
        p+="\n";

        //return path.toString() + " : " + pathCost + " : " + pathTime;
        return p;
    }
}

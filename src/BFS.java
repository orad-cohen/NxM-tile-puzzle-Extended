import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Arrays;
public class BFS {


    static boolean list;
    static int[][] goal;
    static Hashtable<String, String> ClosedList;
    static Hashtable<String, String> OpenedList;
    static Queue<stateNode> _que;


    public static void BFS_Search(int[][] Domain, int[][] Goal,boolean _list){
        list = _list;
        goal = Goal;
        ClosedList = new Hashtable<>();
        OpenedList = new Hashtable<>();
        _que = new LinkedList<>();
        stateNode root = new stateNode(Domain,Goal);
        _que.add(root);
        OpenedList.put(Arrays.deepToString(root._state),"start");
        while(!_que.isEmpty()){
            if(list){
                System.out.println(OpenedList.toString());
            }
            stateNode _toDevelop = _que.poll();
            OpenedList.remove(Arrays.deepToString(_toDevelop._state));
            ClosedList.put(Arrays.deepToString(_toDevelop._state), "Visited");
            _toDevelop.DevelopChildren();
            for(stateNode Child:_toDevelop.children){
                if(Child==null) continue;
                if(stateNode.isGoal(Child)){
                    String solution = Child._path;
                    String mat = Arrays.deepToString(Child._state);
                    String goals = Arrays.deepToString(goal);
                    System.out.println(mat + "\n"+goals+"\n"+solution);
                    return;
                }
                else{
                    isDeveloped(Child);
                }
            }

        }

    }

    public static void isDeveloped(stateNode state){
        if(!(ClosedList.containsKey(Arrays.deepToString(state._state)) || OpenedList.containsKey(Arrays.deepToString((state._state))))){
            addToList(state);
        }
    }


    public static void addToList(stateNode state){

        _que.add(state);
        OpenedList.put(Arrays.deepToString(state._state),"opened");

    }





}

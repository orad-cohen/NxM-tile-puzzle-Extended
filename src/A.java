import java.util.*;
import java.util.PriorityQueue;

public class A {
    static Hashtable<String, stateNode> ClosedList;
    static Hashtable<String, stateNode> OpenedList;
    static int[][] goal;
    static Queue<stateNode> _que;
    static boolean List;

    public static void A_Search(int[][] Domain, int[][] Goal,boolean _list){

        List=_list;
        PriorityQueue<stateNode> _Pque = new PriorityQueue<>(10,new nodeCompare());
        goal = Goal;
        ClosedList = new Hashtable<>();
        OpenedList = new Hashtable<>();
        stateNode root = new stateNode(Domain,Goal);
        _Pque.add(root);
        OpenedList.put(Arrays.deepToString(root._state),root);

        while(!_Pque.isEmpty()){

            stateNode _toDevelop = _Pque.remove();
            OpenedList.remove(Arrays.deepToString(_toDevelop._state));
            ClosedList.put(Arrays.deepToString(_toDevelop._state), _toDevelop);
            _toDevelop.DevelopChildren();
            for(stateNode Child : _toDevelop.children){
                if(Child==null) continue;
                if(stateNode.isGoal(Child)){
                    stateNode.result=Child;
                    return;
                }
                if(isDeveloped(Child)) {continue;}
                _Pque.add(Child);
                OpenedList.put(Arrays.deepToString(Child._state),Child);


            }


        }
        System.out.println("stop");





    }
    public static boolean isDeveloped(stateNode state){
        if(OpenedList.containsKey(Arrays.deepToString((state._state)))){
            ClosedList.put(Arrays.deepToString(state._state),state);
            return true;
        }
        if(ClosedList.containsKey(Arrays.deepToString(state._state))){
            return true;
        }

        return false;
    }




}

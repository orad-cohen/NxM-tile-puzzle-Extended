import java.util.*;

public class DFBnB {
    static int[][] Goal;
    static double threshold;
    static stateNode result;
    public static void DFBnB_Search (int[][] Domain, int[][] goal,boolean list){
        threshold = States(goal.length,goal[0].length);
        Goal=goal;
        stateNode root = new stateNode(Domain,goal);
        Stack<stateNode> _stack = new Stack<>();
        HashMap<String,stateNode> LoopAvoid = new HashMap<>();
        root.CalculateHeuristic();
        _stack.add(root);
        LoopAvoid.put(Arrays.deepToString(root._state), root);

        while(!_stack.isEmpty()){
            if(list){
                System.out.println(_stack.peek());
            }
            stateNode _toDevelop = _stack.pop();
            if(_toDevelop.isOut){
                LoopAvoid.remove(Arrays.deepToString(_toDevelop._state));
            }
            else{
                _toDevelop.DevelopChildren();
                _toDevelop.children.sort(new nodeCompare());
                boolean _ThresholdPassed = false;
                ArrayList<stateNode> toDelete = new ArrayList<>();
                for(stateNode Child : _toDevelop.children){
                    if(Child==null) continue;
                    if(_ThresholdPassed){
                        toDelete.add(Child);
                        continue;
                    }
                    if(Child.Fn>=threshold){
                        toDelete.add(Child);
                        _ThresholdPassed = true;
                        continue;
                    }
                    else if(LoopAvoid.containsKey(Arrays.deepToString(Child._state))){
                        stateNode twinChild = LoopAvoid.get(Arrays.deepToString(Child._state));
                        if(twinChild.isOut){
                            toDelete.add(Child);
                        }
                        else{
                            if(twinChild.Fn<=Child.Fn){
                                toDelete.add(Child);
                            }
                            else{
                                LoopAvoid.remove(Arrays.deepToString(twinChild._state));
                                _stack.remove(twinChild);
                                continue;
                            }
                        }
                    }
                    else if(stateNode.isGoal(Child)){
                        threshold = Child.Fn;
                        result = Child;
                        toDelete.add(Child);
                        _ThresholdPassed=true;

                    }


                }

                _toDevelop.children.removeAll(toDelete);
                Collections.reverse(_toDevelop.children);
                for(stateNode child : _toDevelop.children){
                    _stack.add(child);
                    LoopAvoid.put(Arrays.deepToString(child._state),child);
                }


            }
        }
        System.out.println(result._path);
    }

    public static double States(int a, int b){
        int spots = a*b;
        double states=1;
        for(int i = spots;i!=0;i--){
            states*=i;
        }
        return states*5;

    }
}

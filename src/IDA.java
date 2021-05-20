import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

public class IDA {

    static double _threshold = 0;
    static int[][] Goal;


    public static void IDA_Search(int [][] Domain, int[][] _goal, boolean list){
        Goal=_goal;
        stateNode root = new stateNode(Domain,_goal);
        root.CalculateHeuristic();
        _threshold = root.Fn;
        double Min = Double.POSITIVE_INFINITY;
        for(;;){
            Stack<stateNode> _stack = new Stack<>();
            HashMap<String,stateNode> LoopAvoid = new HashMap<>();
            _stack.add(root);
            LoopAvoid.put(Arrays.deepToString(root._state), root);

            while(!_stack.isEmpty()){
                stateNode _toDevelop = _stack.pop();
                if(_toDevelop.isOut){
                    LoopAvoid.remove(Arrays.deepToString(_toDevelop._state));
                }
                else{
                    _toDevelop.isOut=true;
                    _stack.add(_toDevelop);
                    _toDevelop.DevelopChildren();
                    for(stateNode Child : _toDevelop.children){
                        if(Child==null) continue;
                        System.out.println(_threshold);
                        if(Child.Fn>_threshold) {
                            Min = Math.min(Min, Child.Fn);
                            continue;
                        }
                        if(LoopAvoid.containsKey(Arrays.deepToString(Child._state))){
                            stateNode gTag = LoopAvoid.get(Arrays.deepToString(Child._state));
                            if(gTag.isOut){
                                continue;
                            }
                            else{
                                if(gTag.Fn>Child.Fn){
                                    _stack.remove(gTag);
                                    LoopAvoid.remove(Arrays.deepToString(gTag._state));
                                }
                                else{
                                    continue;
                                }
                            }

                        }
                        if(stateNode.isGoal(Child)){
                            System.out.println(Child._path);
                            return;
                        }
                        else{
                            _stack.add(Child);
                            LoopAvoid.put(Arrays.deepToString(Child._state),Child);
                        }
                    }
                }

            }
            _threshold=Min;

        }

    }



}



import java.util.Arrays;
import java.util.HashMap;

public class DFID {

    static int depth = 1;
    static boolean fail = false;
    static int[][] goal;
    static stateNode Result;
    public static void DFID_Search(int[][] Domain, int[][] Goal,boolean open){
        goal = Goal;
        stateNode root = new stateNode(Domain,Goal);
        HashMap<String, String> LoopAvoidance = new HashMap<>();
        for (int i = 0; i >=0; i++){
            int result = LimitedDFS(root,0,(HashMap<String, String>) LoopAvoidance.clone());
            if(fail){
                return;
                //failure
            }
            else if(result==depth){
                depth++;
            }
            else{
                String solution = Result._path;
                String mat = Arrays.deepToString(Result._state);
                String goals = Arrays.deepToString(goal);
                System.out.println(mat + "\n"+goals+"\n"+solution);
                return;
                //do victory shit.
            }
        }
    }

    public static int LimitedDFS(stateNode CurState, int cutoff,HashMap<String,String> Table)
    {
        if(stateNode.isGoal(CurState)){
            return cutoff;
        }
        else if(cutoff==depth){
            return  cutoff;
        }
        Table.put(Arrays.deepToString(CurState._state), "visited");
        CurState.DevelopChildren();
        int Res= cutoff;
        for (stateNode Child : CurState.children){
            if(Child==null) continue;
            if(Table.containsKey(Arrays.deepToString(Child._state))){
                continue;
            }
            Res = LimitedDFS(Child, cutoff+1,Table);
            if(Res==depth){
                continue;
            }
            else if(Res!=depth){
                break;
            }
            if(fail){
                return depth;
            }

        }

        return Res;




    }






}

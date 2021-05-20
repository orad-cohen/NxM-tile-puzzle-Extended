import java.util.Comparator;

public class nodeCompare implements Comparator<stateNode> {
    public int compare(stateNode A, stateNode B) {
        if(A==null) return -1;
        if(B==null) return 1;
        if(A.Fn>B.Fn){
            return 1;
        }
        else if(A.Fn<B.Fn){
            return -1;
        }
        return 0;
    }


}

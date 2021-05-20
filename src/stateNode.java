import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class stateNode{
        int[][] _state;
        String _path = "";
        int Pre = 0;
        boolean isOut = false;
        double Cost;
        double HeuristicCost;
        double Fn;
        static stateNode result = null;

        int depth=0;
        static int Developed = 0;
        static boolean TwoMissing = false;
        static int [][] Goal;
        List<stateNode> children = new ArrayList<>();


        public stateNode(int [][] state,String path,double cost,int depth,int pre){
            this.depth=depth+1;
            Pre = pre;
            _state = state;
            _path = path+"-";
            Developed++;
            Cost+=cost;
            CalculateHeuristic();
        }

        public stateNode(int[][] state, int[][] _Goal){
            Goal = _Goal;
            Developed++;
            _state = DeepCopy(state);
            Cost =0;
        }
        

    public void DevelopChildren(){
        int a = -1;
        int b = -1;
        int c = -1;
        int d = -1;
        int n = this._state.length;
        int m = this._state[0].length;
        for (int o = 0; o < n; o++) {
            for (int p = 0; p < m; p++) {
                if(_state[o][p]==0){
                    if(a==-1){
                        a=o;
                        b=p;
                    }
                    else{
                        c=o;
                        d=p;
                    }
                }
            }
        }
        n--;
        m--;
        int z = a-c;
        int g = b-d;

        if(d==-1){
            ApplySingle(a,b,n,m);
        }
        else{
            TwoMissing= true;
            //Vertical
            if(z==-1&&g==0){
                if(a+b==0){
                    children.add(moveTwoLeft(this, a, b,c,d));
                    children.add(moveLeft(this, a, b));
                    children.add(moveLeft(this, c, d));
                    children.add(moveUp(this, c, d));
                }
                else if(a+b==m+n-1){
                    children.add(moveTwoRight(this, a, b,c,d));
                    children.add(moveRight(this, a, b));
                    children.add(moveDown(this, a, b));
                    children.add(moveRight(this, c, d));

                }
                else if(a==0&b==m){
                    children.add(moveTwoRight(this, a, b,c,d));
                    children.add(moveRight(this, a, b));
                    children.add(moveUp(this, c, d));
                    children.add(moveRight(this, c, d));
                }
                else if(c==n&&d==0){
                    children.add(moveTwoLeft(this, a, b,c,d));
                    children.add(moveLeft(this, a, b));
                    children.add(moveDown(this, a, b));
                    children.add(moveLeft(this, c, d));
                }
                else if(b==0){
                    children.add(moveTwoLeft(this, a, b,c,d));
                    children.add(moveLeft(this, a, b));
                    children.add(moveDown(this, a, b));
                    children.add(moveLeft(this, c, d));
                    children.add(moveUp(this, c, d));
                }
                else if(d==m){
                    children.add(moveTwoRight(this, a, b,c,d));
                    children.add(moveRight(this, a, b));
                    children.add(moveDown(this, a, b));
                    children.add(moveUp(this, c, d));
                    children.add(moveRight(this, c, d));
                }
                else if(a==0){
                    children.add(moveTwoLeft(this, a, b,c,d));
                    children.add(moveTwoRight(this, a, b,c,d));
                    children.add(moveLeft(this, a, b));
                    children.add(moveRight(this, a, b));
                    children.add(moveLeft(this, c, d));
                    children.add(moveUp(this, c, d));
                    children.add(moveRight(this, c, d));
                }
                else if(c==n){
                    children.add(moveTwoLeft(this, a, b,c,d));
                    children.add(moveTwoRight(this, a, b,c,d));
                    children.add(moveLeft(this, a, b));
                    children.add(moveRight(this, a, b));
                    children.add(moveDown(this, a, b));
                    children.add(moveLeft(this, c, d));
                    children.add(moveRight(this, c, d));


                }
                else{
                    children.add(moveTwoLeft(this, a, b,c,d));
                    children.add(moveTwoRight(this, a, b,c,d));
                    children.add(moveLeft(this, a, b));
                    children.add(moveRight(this, a, b));
                    children.add(moveDown(this, a, b));
                    children.add(moveLeft(this, c, d));
                    children.add(moveUp(this, c, d));
                    children.add(moveRight(this, c, d));
                }

            }
            // Horizontal
            else if(g==-1&&z==0) {
                if (a + b == 0) {
                    children.add(moveTwoUp(this, a, b, c, d));
                    children.add(moveUp(this, a, b));
                    children.add(moveLeft(this, c, d));
                    children.add(moveUp(this, c, d));

                } else if (a + b == m + n - 1) {
                    children.add(moveTwoDown(this, a, b, c, d));
                    children.add(moveRight(this, a, b));
                    children.add(moveDown(this, a, b));
                    children.add(moveDown(this, c, d));


                } else if (a == n && b == 0) {
                    children.add(moveTwoDown(this, a, b, c, d));
                    children.add(moveDown(this, a, b));
                    children.add(moveLeft(this, c, d));
                    children.add(moveDown(this, c, d));

                }
                else if(b==m-1&&a==0){
                    children.add(moveTwoUp(this, a, b, c, d));
                    children.add(moveUp(this, a, b));
                    children.add(moveRight(this, a, b));
                    children.add(moveUp(this, c, d));

                }
                else if(a==0){
                    children.add(moveTwoUp(this, a, b, c, d));
                    children.add(moveUp(this, a, b));
                    children.add(moveRight(this, a, b));
                    children.add(moveLeft(this, c, d));
                    children.add(moveUp(this, c, d));

                }
                else if(a==n){
                    children.add(moveTwoDown(this, a, b, c, d));
                    children.add(moveRight(this, a, b));
                    children.add(moveDown(this, a, b));
                    children.add(moveLeft(this, c, d));
                    children.add(moveDown(this, c, d));
                }
                else if(b==m-1){
                    children.add(moveTwoUp(this, a, b, c, d));
                    children.add(moveTwoDown(this, a, b, c, d));
                    children.add(moveUp(this, a, b));
                    children.add(moveRight(this, a, b));
                    children.add(moveDown(this, a, b));
                    children.add(moveUp(this, c, d));
                    children.add(moveDown(this, c, d));
                }
                else if(b==0){
                    children.add(moveTwoUp(this, a, b, c, d));
                    children.add(moveTwoDown(this, a, b, c, d));
                    children.add(moveUp(this, a, b));
                    children.add(moveDown(this, a, b));
                    children.add(moveLeft(this, c, d));
                    children.add(moveUp(this, c, d));
                    children.add(moveDown(this, c, d));
                }
                else{
                    children.add(moveTwoUp(this, a, b, c, d));
                    children.add(moveTwoDown(this, a, b, c, d));
                    children.add(moveUp(this, a, b));
                    children.add(moveRight(this, a, b));
                    children.add(moveDown(this, a, b));
                    children.add(moveLeft(this, c, d));
                    children.add(moveUp(this, c, d));
                    children.add(moveDown(this, c, d));

                }


            }
            else{
                ApplySingle(a, b, n, m);
                ApplySingle(c,d,n,m);
            }

        }



        }
        public void ApplySingle(int a, int b, int n, int m){
            if (a + b == 0) {
                stateNode state = stateNode.moveLeft( this , a, b);
                children.add(moveUp(this, a, b));

            } else if (a == 0 && b == m) {
                children.add(moveUp(this, a, b));
                children.add(moveRight(this, a, b));

            } else if (a == n && b == 0) {
                children.add(moveLeft(this, a, b));
                children.add(moveDown(this, a, b));

            } else if (a == n && b == m) {
                children.add(moveRight(this, a, b));
                children.add(moveDown(this, a, b));

            } else if (a == 0) {
                children.add(moveLeft(this, a, b));
                children.add(moveUp(this, a, b));
                children.add(moveRight(this, a, b));

            } else if (a == n) {
                children.add(moveLeft(this, a, b));
                children.add(moveRight(this, a, b));
                children.add(moveDown(this, a, b));



            } else if (b == 0) {
                children.add(moveLeft(this, a, b));
                children.add(moveUp(this, a, b));
                children.add(moveDown(this, a, b));

            } else if (b == m) {
                children.add(moveUp(this, a, b));
                children.add(moveRight(this, a, b));
                children.add(moveDown(this, a, b));

            } else {
                children.add(moveLeft(this, a, b));
                children.add(moveUp(this, a, b));
                children.add(moveRight(this, a, b));
                children.add(moveDown(this, a, b));

            }

        }

    public static  stateNode moveTwoLeft(stateNode curState, int a , int b, int i, int j){
        if(curState.Pre==8){
            return null;
        }
        int[][] mat1 = DeepCopy(curState._state);
        mat1[a][b] = mat1[a][b+1];
        mat1[i][j] = mat1[i][j+1];
        mat1[a][b+1] = 0;
        mat1[i][j+1] = 0;
        return new stateNode(mat1,curState._path+ mat1[a][b] +"&"+mat1[i][j]+"L", curState.Cost+7, curState.depth,5);
    }
    public static  stateNode moveTwoUp(stateNode curState, int a , int b, int i, int j){
        if(curState.Pre==7){
            return null;
        }
        int[][] mat1 = DeepCopy(curState._state);
        mat1[a][b] = mat1[a+1][b];
        mat1[i][j] = mat1[i+1][j];
        mat1[a+1][b] = 0;
        mat1[i+1][j] = 0;
        return new stateNode(mat1,curState._path+ mat1[a][b] +"&"+mat1[i][j]+"U",curState.Cost+6,curState.depth,6);
    }
    public static  stateNode moveTwoDown(stateNode curState, int a , int b, int i, int j){
        if(curState.Pre==6){
            return null;
        }
        int[][] mat1 = DeepCopy(curState._state);
        mat1[a][b] = mat1[a-1][b];
        mat1[i][j] = mat1[i-1][j];
        mat1[a-1][b] = 0;
        mat1[i-1][j] = 0;
        return new stateNode(mat1,curState._path+ mat1[a][b] +"&"+mat1[i][j]+"D",curState.Cost+6,curState.depth,7);
    }
    public static  stateNode moveTwoRight(stateNode curState, int a , int b, int i, int j){
        if(curState.Pre==5){
            return null;
        }
        int[][] mat1 = DeepCopy(curState._state);
        mat1[a][b] = mat1[a][b-1];
        mat1[i][j] = mat1[i][j-1];
        mat1[a][b-1] = 0;
        mat1[i][j-1] = 0;
        return new stateNode(mat1,curState._path+ mat1[a][b] +"&"+mat1[i][j]+"R",curState.Cost+7,curState.depth,8);
    }



    public static stateNode moveLeft(stateNode curState, int a , int b){
        if(curState.Pre==2){
            return null;
        }
        int[][] mat1 = DeepCopy(curState._state);
        mat1[a][b] = mat1[a][b+1];
        mat1[a][b+1] = 0;
        return new stateNode(mat1,curState._path+ mat1[a][b] +"L",curState.Cost+5,curState.depth,1);
    }
    public static stateNode moveRight(stateNode curState, int a , int b){
        if(curState.Pre==1){
            return null;
        }
        int[][] mat1 = DeepCopy(curState._state);
        mat1[a][b] = mat1[a][b-1];
        mat1[a][b-1] = 0;

        return new stateNode(mat1,curState._path+ mat1[a][b] +"R",curState.Cost+5,curState.depth,2);
    }
    public static stateNode moveUp(stateNode curState, int a , int b){
        if(curState.Pre==4){
            return null;
        }
        int[][] mat1 = DeepCopy(curState._state);
        mat1[a][b] = mat1[a+1][b];
        mat1[a+1][b] = 0;

        return new stateNode(mat1,curState._path+ mat1[a][b] +"U",curState.Cost+5,curState.depth,3);

    }
    public static stateNode moveDown(stateNode curState, int a , int b){
        if(curState.Pre==3){
            return null;
        }
        int[][] mat1 = DeepCopy(curState._state);
        mat1[a][b] = mat1[a-1][b];
        mat1[a-1][b] = 0;

        return new stateNode(mat1,curState._path+ mat1[a][b] +"D",curState.Cost+5,curState.depth,4);
    }

    static int[][] DeepCopy (int[][] mat){

        int[][] _newMat = new int[mat.length][mat[0].length];
        for (int i = 0; i < _newMat.length; i++) {
            _newMat[i] = Arrays.copyOf(mat[i], mat[i].length);
        }
        return _newMat;
        }

    @Override
    public String toString() {
        return _path+" : " +Fn;

    }
    public void CalculateHeuristic(){
            double sum =0;
            int n=_state.length;
            int m=_state[0].length;

        for (int i = 0; i <n ; i++) {
            for (int j = 0; j < m; j++) {
                nestedloop:
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < m; l++) {
                        if(_state[i][j]==Goal[k][l]){
                            if(TwoMissing){
                                sum+= 2.3*(Math.sqrt(Math.pow(i-k, 2)+Math.pow(j-l,2)));
                            }
                            else{
                                sum+=  5*(Math.sqrt(Math.pow(i-k, 2)+Math.pow(j-l,2)));
                            }


                            break nestedloop;
                        }
                    }

                }

            }
        }
        HeuristicCost=sum;
        Fn=HeuristicCost+Cost;
    }

    public static boolean isGoal(stateNode state){
        for (int i = 0; i <Goal.length ; i++) {
            for (int j = 0; j <Goal[0].length ; j++) {
                if(Goal[i][j]!=state._state[i][j]){
                    return false;
                }
            }
        }
        return true;
    }



}

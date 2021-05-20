import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Ex1 {

    public static void main(String[] args) {
        try{
            File f= new File("input.txt");
            Scanner sc = new Scanner(f);
            String AlgoType = sc.nextLine();
            String _TimePar = sc.nextLine();
            String _ListPar = sc.nextLine();
            String _BoardSize=sc.nextLine();
            sc.useDelimiter(",");
            int m = Integer.parseInt(_BoardSize.substring(0, _BoardSize.indexOf('x')));
            int n = Integer.parseInt(_BoardSize.substring(_BoardSize.indexOf('x')+1));
            int[][] board = new int[m][n];
            String _Board="";
            for(int i = 0; i<m; i++){
                _Board+=sc.nextLine()+',';
            }
            int Pre = 0;
            int Next = _Board.indexOf(',');

            for (int i = 0; i<m; i++){
                for (int j = 0; j < n; j++){
                    String s = _Board.substring(Pre,Next);
                    if(s.equals("_")){

                        board[i][j] = 0;
                        Pre=Next+1;
                        Next=_Board.indexOf(',',Next+1);
                    }
                    else{
                        board[i][j] = Integer.parseInt(s);
                        Pre=Next+1;
                        Next=_Board.indexOf(',',Next+1);
                    }

                }

            }
            sc.nextLine();
            int[][] goal = new int[m][n];
            String _Goal="";
            for(int i = 0; i<m; i++){
                _Goal+=sc.nextLine()+',';
            }
            Pre = 0;
            Next = _Goal.indexOf(',');

            for (int i = 0; i<m; i++){
                for (int j = 0; j < n; j++){
                    String s = _Goal.substring(Pre,Next);
                    if(s.equals("_")){
                        goal[i][j] = 0;
                        Pre=Next+1;
                        Next=_Goal.indexOf(',',Next+1);
                    }
                    else{
                        goal[i][j] = Integer.parseInt(s);
                        Pre=Next+1;
                        Next=_Goal.indexOf(',',Next+1);
                    }

                }

            }
            boolean open = _ListPar.equals("with open")? true:false;
            long start=0,end=0;
            start = System.currentTimeMillis();
            if(AlgoType.equals("BFS")){
                BFS.BFS_Search(board, goal,open);
            }
            else if(AlgoType.equals("DFID")){
                 DFID.DFID_Search(board,goal,open);
            }
            else if(AlgoType.equals("A*")){
                A.A_Search(board,goal,open);
            }
            else if(AlgoType.equals("IDA*")){
                IDA.IDA_Search(board,goal,open);
            }
            else if(AlgoType.equals("DFBnB")){
                DFBnB.DFBnB_Search(board,goal,open);
            }



            FileWriter output = new FileWriter("output.txt");
            long finish = System.currentTimeMillis();
            long timeElapsed = finish - start;

            if(stateNode.result==null){
                output.write("no path\nnum"+stateNode.Developed);
            }
            else{
                stateNode shortest = stateNode.result;
                output.write(shortest._path.substring(0,shortest._path.length()-2)+"\n");
                output.write("Num: "+stateNode.Developed+'\n');
                output.write("Cost: "+ shortest.Cost+'\n');

            }
            if(_TimePar.equals("with time")){
                output.write(""+ timeElapsed/100);
            }
            output.close();


        }
        catch (Exception e){

            System.out.println(e);

        }

    }
}

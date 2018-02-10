/**
 * FileName:Main.java
 * @author zhanggw
 * @date 2018年2月10日 上午9:41:41
 */
package games;

import java.util.List;
import java.util.Scanner;

/**
 * @ClassName Main
 * @author zhanggw
 * @date 2018年2月10日 上午9:41:41
 */
public class Main {
    public static void main(String[] args) {
        // 棋盘初始化
        int capacity = 10;
        ChessBoard chessBoard = new ChessBoard(capacity);
        
        // 下棋人初始化和准备
        Chessable machine = new ChessMachine("电脑");
        machine.joinChess(chessBoard);
        
        Chessable person = new ChessPerson("貂蝉");
        person.joinChess(chessBoard);
        
        // 开始下棋
        chessBoard.start();
        
        Scanner scanner = new Scanner(System.in);
        // 未分出胜负则一直继续
        while(chessBoard.getChessWinner()==null){
            Chessable chessObj = chessBoard.getWaiChessable();
            
            // 获取人输入坐标
            System.out.println(chessObj.getChessName()+"请输入下棋坐标x,y:");
            Piece piece = null;
            if(chessObj instanceof ChessPerson){
                String line = scanner.next();
                int x = Integer.parseInt(line.split(",")[0].trim());
                int y = Integer.parseInt(line.split(",")[1].trim());
                piece = new Piece(x,y);
            }else if(chessObj instanceof ChessMachine){
                // 获取电脑输入坐标
                ChessMachine chessMachine = (ChessMachine)chessObj;
                piece = chessMachine.processSmallDistancePiece();
            }
            
            System.out.println(chessObj.getChessName()+"下棋坐标:"+piece);
            String chessRet = chessObj.chess(piece);
            
            if("fail".equals(chessRet)){
                // 无子可下，对方赢了
                List<Chessable> chessObjList = chessBoard.getChessObj();
                chessObjList.remove(chessObj);
                chessBoard.setChessWinner(chessObjList.get(0));
            }else if("5kill".equals(chessRet)){
                // 5子成线，得10分，可继续下棋
                chessObj.increaseScore(10);
                System.out.println(chessObj.getChessName()+"五子成线，得10分!");
                chessBoard.setWaitChessObj(chessObj);
            }else if("repeat".equals(chessRet)){
                System.out.println("落棋点已有棋子，请重新落子!");
                chessBoard.setWaitChessObj(chessObj);
            }else{
                // 对方继续
                chessBoard.pass(chessObj);
            }
            
            chessBoard.show();
        }
        scanner.close();
    }
}
/**
 * FileName:ChessBoard.java
 * @author zhanggw
 * @date 2018年2月10日 上午9:21:43
 */
package games;

import java.util.ArrayList;
import java.util.List;


/**
 * 棋盘类
 * @author zhanggw
 * @date 2018年2月10日 上午9:21:43
 */
public class ChessBoard {
    private int capacity;  // 棋盘容量
    private String[][] chessBoardArray; // 存储棋盘数组
    private List<Chessable> chessObj = new ArrayList<Chessable>(); // 所有下棋人
    private Chessable waitChessObj; // 等待下棋的人
    private Chessable chessWinner; //赢了的下棋人
    
    public ChessBoard(){}
    
    public ChessBoard(int capacity){
        init(capacity);
    }
    
    /**
     * 初始化棋盘
     */
    public void init(int capacity){
        this.capacity = capacity;
        chessBoardArray = new String[capacity][capacity];
        
        for(int i=0;i<capacity;i++){
            for(int j=0;j<capacity;j++){
                this.chessBoardArray[i][j] = "+";
            }
        }
    }
    
    /**
     * 当前下棋人已下完棋
     */
    public void pass(Chessable currentChess){
        int index = this.chessObj.indexOf(currentChess);
        this.waitChessObj = (index==0)?this.chessObj.get(1):this.chessObj.get(0);
    }
    
    public void start(){
        if(chessObj==null || chessObj.size()<2){
            System.out.println("棋局缺人，不能开始！");
            return;
        }
        
        // 随即指定谁开始
        int firstChessIndex = (int)Math.round(Math.random());
        this.waitChessObj = chessObj.get(firstChessIndex);
        System.out.println(waitChessObj.getChessName()+"先开始下棋！");
        
        show();
    }
    
    /**
     * 展示棋盘
     */
    public void show(){
        for(int i=0;i<chessBoardArray.length;i++){
            for(int j=0;j<chessBoardArray[i].length;j++){
                if((j+1)==capacity){
                    System.out.println(chessBoardArray[i][j]);
                }else{
                    System.out.print(chessBoardArray[i][j]);
                }
            }
        }
        for(Chessable chess:chessObj){
            System.out.print(chess.getChessName()+"得分："+chess.getScore()+"    ");
        }
        System.out.println();
    }
    
    /**
     * 获取当前棋盘容量
     */
    public int getCapacity(){
        return this.capacity;
    }
    
    /**
     * 获取当前棋局
     */
    public String[][] getChessBoardArray(){
        return this.chessBoardArray;
    }
    
    /**
     * 下棋人加入棋盘
     */
    public void join(Chessable chessObj){
        this.chessObj.add(chessObj);
    }
    
    /**
     * 获取等待下棋的人
     */
    public Chessable getWaiChessable(){
        return this.waitChessObj;
    }

    public List<Chessable> getChessObj() {
        return chessObj;
    }

    public void setChessObj(List<Chessable> chessObj) {
        this.chessObj = chessObj;
    }

    public Chessable getWaitChessObj() {
        return waitChessObj;
    }

    public void setWaitChessObj(Chessable waitChessObj) {
        this.waitChessObj = waitChessObj;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setChessBoardArray(String[][] chessBoardArray) {
        this.chessBoardArray = chessBoardArray;
    }
    
    public Chessable getChessWinner() {
        return chessWinner;
    }

    public void setChessWinner(Chessable chessWinner) {
        this.chessWinner = chessWinner;
    }
}

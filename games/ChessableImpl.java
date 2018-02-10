/**
 * FileName:ChessableImpl.java
 * @author zhanggw
 * @date 2018年2月10日 下午5:10:45
 */
package games;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChessableImpl
 * @author zhanggw
 * @date 2018年2月10日 下午5:10:45
 */
public abstract class ChessableImpl implements Chessable{
    private String name; // 下棋人名
    private String symbol; // 下棋占位符 
    private String[][] chessBoardArray; // 整个棋盘
    private int score = 0; // 得分
    private int capacity; // 棋盘大小
    
    public ChessableImpl(){}
    
    public ChessableImpl(String name) {
        this.name = name;
    }
    
    @Override
    public void joinChess(ChessBoard chessBoard){
        // 本对象加入棋盘的下棋人集合中
        chessBoard.getChessObj().add(this);
        
        // 本对象存储整个棋盘数组
        setChessBoardArray(chessBoard.getChessBoardArray());
        
        this.capacity = chessBoard.getCapacity();
    }
    
    @Override
    public void storageChessBoardArray(String[][] chessBoardArray) {
        this.chessBoardArray = chessBoardArray;
    }

    @Override
    public String chess(Piece piece) {
        if(piece == null){
            return "fail";
        }
        
        if(this.chessBoardArray!=null){
            if(checkRepeat(piece)){
                return "repeat";
            }
            
            int x = piece.getX();
            int y = piece.getY();
            this.chessBoardArray[10-y][x-1]= this.symbol;
            
            boolean checkGetScore = checkGetScore(piece);
            if(checkGetScore){
                return "5kill";
            }
        }
        return "ok";
    }

    @Override
    public boolean checkGetScore(Piece currentPiece) {
        // 找出所有已下棋子
        List<Piece> chessedPiece = new ArrayList<Piece>();
        for(int i=0;i<chessBoardArray.length;i++){
            for(int j=0;j<chessBoardArray[i].length;j++){
                if(chessBoardArray[i][j].equals(this.symbol)){
                    Piece piece = new Piece(i, j);
                    chessedPiece.add(piece);
                }
            }
        }
        if(chessedPiece.size()<5){
            return false;
        }
        
        // 调整坐标
        currentPiece = new Piece(10-currentPiece.getY(), currentPiece.getX()-1);
        
        // 找到与刚下棋子临近的棋子
        List<Piece> oneStepPieces = Util.getOneStepPieces(chessedPiece,currentPiece);
        for(Piece tmp:oneStepPieces){
            List<Piece> linePieces = Util.getLinePieces(chessedPiece,currentPiece,tmp);
            
            // 获取到一条成线性的棋子
            if(linePieces.size()>=5){
                for(int i=0;i<5;i++){
                    this.chessBoardArray[linePieces.get(i).getX()][linePieces.get(i).getY()] = "+";
                }
                return true;
            }
        }
        
        return false;
    }

    @Override
    public String getChessName() {
        return this.name;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public void increaseScore(int score) {
        this.score += score;
    }
    
    @Override
    public boolean checkRepeat(Piece piece) {
        if(this.chessBoardArray[10-piece.getY()][piece.getX()-1].equals("+")){
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }
    
    public void setSymbol(String symbol){
        this.symbol = symbol;
    }
    
    public String[][] getChessBoardArray() {
        return chessBoardArray;
    }

    public void setChessBoardArray(String[][] chessBoardArray) {
        this.chessBoardArray = chessBoardArray;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

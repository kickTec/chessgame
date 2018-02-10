/**
 * FileName:Util.java
 * @author zhanggw
 * @date 2018年2月10日 下午7:56:35
 */
package games;

import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName Util
 * @author zhanggw
 * @date 2018年2月10日 下午7:56:35
 */
public class Util {
    
    /**
     *  从pieces棋子集合中获取距离指定piece棋子最近的所有棋子集合
     */
    public static List<Piece> getOneStepPieces(List<Piece> pieces,Piece piece){
        List<Piece> pieceList = new ArrayList<Piece>();
        
        for(Piece tmp:pieces){
            if(Math.abs(tmp.getX() - piece.getX())<=1 & Math.abs(tmp.getY()-piece.getY()) <=1){
                if(tmp.getX() !=piece.getX() || tmp.getY() != piece.getY()){
                    pieceList.add(tmp);
                }
            }
        }
        return pieceList;
    }
    
    /**
     * 从chessedPieces棋子集合中获取，与onePiece、twoPiece成线性关系的所有棋子
     */
    public static List<Piece> getLinePieces(List<Piece> chessedPieces, Piece onePiece, Piece twoPiece) {
        List<Piece> linePieces = new ArrayList<Piece>();
        linePieces.add(onePiece);
        linePieces.add(twoPiece);
        
        int xAdd = twoPiece.getX() - onePiece.getX();
        int yAdd = twoPiece.getY() - onePiece.getY();
        
        // 获取下一个连线棋子
        Piece currentPiece = twoPiece;
        Piece nextPiece = new Piece(currentPiece.getX()+xAdd, currentPiece.getY()+yAdd);
        while(chessedPieces.contains(nextPiece)){
            linePieces.add(nextPiece);
            currentPiece = nextPiece;
            nextPiece = new Piece(currentPiece.getX()+xAdd, currentPiece.getY()+yAdd);
        }
        
        return linePieces;
    }
    
    
    public static Piece getUnUsedPiece(String[][] chessBoardArray, Piece piece) {
        for(int i=0;i<chessBoardArray.length;i++){
            for(int j=0;j<chessBoardArray[i].length;j++){
                if( "+".equals(chessBoardArray[i][j])){
                    Piece newPiece = new Piece(i,j);
                    if(Math.abs(newPiece.getX() - piece.getX())<=1 & Math.abs(newPiece.getY()-piece.getY()) <=1){
                        if(newPiece.getX() !=piece.getX() || newPiece.getY() != piece.getY()){
                            return newPiece;
                        }
                    }
                }
            }
        }
        
        return null;
    }
}

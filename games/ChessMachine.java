/**
 * FileName:ChessMachine.java
 * @author zhanggw
 * @date 2018年2月10日 下午5:19:43
 */
package games;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ChessMachine
 * @author zhanggw
 * @date 2018年2月10日 下午5:19:43
 */
public class ChessMachine extends ChessableImpl{
    
    public ChessMachine(){
        // 设置默认落子的符号
        super.setSymbol("☆");
    }
    
    public ChessMachine(String name){
        super(name);
        super.setSymbol("☆");
    }
    
    /**
     * 随即落子
     */
    public Piece processRandomPiece(){
        int capacity = super.getCapacity();
        Piece randomPiece = new Piece();
        
        // 获取与当前所有棋子不重复的坐标
        do {
            int x = ((int)Math.ceil((Math.random()+1)*capacity)-capacity);
            int y = ((int)Math.ceil((Math.random()+1)*capacity)-capacity);
            randomPiece.setX(x);
            randomPiece.setY(y);
        } while (checkRepeat(randomPiece));
        
        return randomPiece;
    }
    
    /**
     * 靠近落子
     */
    public Piece processSmallDistancePiece(){
        Piece piece = null;
        
        // 获取所有落子点
        List<Piece> machinePieces = new ArrayList<Piece>();
        for(int i=0;i<super.getChessBoardArray().length;i++){
            for(int j=0;j<super.getChessBoardArray()[i].length;j++){
                if(super.getChessBoardArray()[i][j].equals(this.getSymbol())){
                    machinePieces.add(new Piece(i, j));
                }
            }
        }
        
        // 获取靠近落子点的空白位置
        for(Piece tmp:machinePieces){
            piece = Util.getUnUsedPiece(super.getChessBoardArray(),tmp);
            
            // 调整x,y坐标，适应人的习惯
            if(piece!=null){
                int oldX = piece.getX();
                int oldY = piece.getY();
                piece.setX(oldY+1);
                piece.setY(super.getCapacity() - oldX);
            }
        }
        
        // 未落子，则随即选择一个落子点
        if(machinePieces.size()==0 || piece==null){
            piece = processRandomPiece();
        }

        return piece;
    }
}

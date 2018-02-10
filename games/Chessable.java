/**
 * FileName:ChessNotify.java
 * @author zhanggw
 * @date 2018年2月10日 上午9:24:04
 */
package games;

/**
 *  下棋能力接口
 * @author zhanggw
 * @date 2018年2月10日 上午9:24:04
 */
public interface Chessable{
    /**
     * 获取下棋人名称
     */
    public String getChessName();
    
    /**
     * 加入棋局
     */
    public void joinChess(ChessBoard chessBoard);
    
    /**
     * 存储整个棋盘
     */
    public void storageChessBoardArray(String[][] chessBoardArray);
    
    /**
     * 进行下棋
     * @param chessBoardInfo 当前棋盘信息
     * @return 下棋操作结果，fail:认输或无子可下,ok:下好棋了
     */
    public String chess(Piece pieces);

    /**
     * 获取下棋人得分
     */
    public int getScore();
    
    /**
     * 增加得分
     */
    public void increaseScore(int score);
    
    /***
     * 根据当前所下棋子检查是否得分，若不属于得分规则，返回false，轮到下一个人
     * 若属于得分规则，返回true，下一次下棋还是自己
     */
    public boolean checkGetScore(Piece piece);
    
    /**
     * 检查棋子是否重复
     */
    public boolean checkRepeat(Piece piece);
}

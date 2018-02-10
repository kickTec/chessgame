/**
 * FileName:ChessPerson.java
 * @author zhanggw
 * @date 2018年2月10日 下午5:23:14
 */
package games;

/**
 * @ClassName ChessPerson
 * @author zhanggw
 * @date 2018年2月10日 下午5:23:14
 */
public class ChessPerson extends ChessableImpl{
    public ChessPerson(){
        super.setSymbol("●");
    }
    
    public ChessPerson(String name){
        super(name);
        super.setSymbol("●");
    }
}

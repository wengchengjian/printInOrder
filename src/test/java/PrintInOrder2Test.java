import com.wcj.demo.PrintInOrder2;

/**
 * @author wengchengjian
 * @date 2022/7/19-16:51
 */
public class PrintInOrder2Test {
    public static void main(String[] args) {
        new Thread(new PrintInOrder2()).start();
    }
}

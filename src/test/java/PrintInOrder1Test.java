import com.wcj.demo.PrintInOrder1;

/**
 * @author wengchengjian
 * @date 2022/7/19-16:19
 */

class PrintInOrder1Test {
    public static void main(String[] args) {
        new Thread(new PrintInOrder1()).start();
    }
}
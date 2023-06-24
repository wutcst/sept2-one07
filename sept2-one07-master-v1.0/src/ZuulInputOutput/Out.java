/**
 * 该类用于处理输入
 *
 * @author Dian Tu
 */
package ZuulInputOutput;

import java.util.List;

public class Out {

    public Out() { }

    public void print(List<String> msg) {
        msg
            .forEach(System.out::println);
    }

    /**
     * Print a message
     * @param str the message to print
     */
    public void print(String str) {
        System.out.print(str);
    }

    /**
     * Print a message with a carriage return
     *
     * @param str the message to print
     */
    public void println(String str) {
        System.out.println(str);
    }
}

import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public interface MenuAction {
    public void execute(Scanner inline, Queue<Customer> customers);
}

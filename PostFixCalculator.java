import java.math.BigDecimal;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.math.BigInteger;

public class PostFixCalculator {
    private List<String> expression = new ArrayList<String>();
    private Deque<BigInteger> stack = new ArrayDeque<BigInteger>();

    public PostFixCalculator(List<String> postfix) {expression = postfix;}


    public BigDecimal result()
    {
        for(int i = 0; i != expression.size(); ++i)
        {
            // Determine if current element is digit or not
            if(Character.isDigit(expression.get(i).charAt(0)))
            {   BigInteger numBig = new BigInteger(expression.get(i));
                stack.addLast(numBig);
                //stack.addLast(Double.parseDouble(expression.get(i)));
            }
            else
            {
                BigInteger tempResult = BigInteger.valueOf(0);
                BigInteger temp;
                switch(expression.get(i)) {
                    case "+": temp = stack.removeLast();
                        tempResult = stack.removeLast().add(temp);
                        break;

                    case "-": temp = stack.removeLast();
                        tempResult = stack.removeLast().subtract(temp);
                        break;

                    case "*": temp = stack.removeLast();
                        tempResult = stack.removeLast().multiply(temp);
                        break;

                    case "/": temp = stack.removeLast();
                        tempResult = stack.removeLast().divide(temp);
                        break;
                }
                stack.addLast(tempResult);
            }
        }
        return new BigDecimal(stack.removeLast()).setScale(0, BigDecimal.ROUND_HALF_UP);
    }
}

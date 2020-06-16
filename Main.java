package calculator;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {

        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        LinkedList<String> list2 = new LinkedList<>();
        for (; ; ) {
            BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
            String data = sc.readLine().replaceAll("", "")
                    .replaceAll(" {2}", "")
                    .replaceAll(" {3}", "")
                    .replaceAll(" {4}", "")
                    .replaceAll("[+]{3}", "+")
                    .replaceAll("[+]{5}", "+")
                    .replaceAll("[-]{2}", "+")
                    .replaceAll("[-]{3}", "-");


            String[] expression = data.split(" ");
            String[] expression4 = data.split("");
            int count = 0;
            int count2 = 0;
            for (int i=0; i <expression4.length; i++) {
                if (expression4[i].matches("\\(")) {
                    count++;
                }
                if (expression4[i].matches("\\)")) {
                    count++;
                }
            }
            for (int i=0; i <expression.length; i++) {
                if (expression[i].matches("\\*\\*") |
                        expression[i].matches("\\*\\*\\*") |
                        expression[i].matches("//") |
                        expression[i].matches("///")) {
                    count2++;
                }
            }

            if (count%2 != 0 | count2>=1) {
                System.out.println("Invalid expression");
                continue;
            }

            String data2 = data.replaceAll(" ", "");
            String[] blocks = data.split("=");
            String[] blocks2 = data2.split("");
            Pattern p = Pattern.compile("[*+-]");
            Matcher m = p.matcher(data);

            if (blocks2[0].isEmpty()) {
                continue;
            } else if (blocks[0].equals("/command")) {
                System.out.println("Unknown command");
            } else if (blocks[0].equals("/exit")) {
                System.out.println("Bye!");
                System.exit(0);
            } else if (blocks2[0].equals("-")) {
                System.out.println(blocks2[0] + blocks2[1] + blocks2[2]);
            } else if (data.matches("(.*)\\w+\\s+=\\s+[0-9][0-9]*$")) {
                String[] blocks3 = data.split(" ");
                List<String> list4 = Arrays.asList(blocks3);
                if (list4.size() == 3) {
                    try {
                        map.put(list4.get(0), list4.get(2));
                        list2.add(list4.get(0));
                    } catch (Exception e) {
                        System.out.println("Invalid assignment");
                    }
                }
            } else if (m.find()) {
                String data4 = data.replaceAll(" ", "");
                String[] expression2 = data4.split("");
                List<String> list3 = Arrays.asList(expression2);

                LinkedList<String> list = new LinkedList<>(list3);
                if (map.containsKey(expression2[0])) {
                    for (String s : list2) {
                        for (int i = 0; i < list.size(); i++) {
                            if (list.get(i).equals(s)) {
                                list.set(i, map.get(list.get(i)).toString());
                            }
                        }
                    }
                    String arr ="";
                    for (String s : list) {
                        arr += s;
                    }
                    PostFixConverter pc = new PostFixConverter(arr);
                    PostFixCalculator calc = new PostFixCalculator(pc.getPostfixAsList());
                    System.out.println(calc.result());
                } else if (expression[0].matches("^[0-9][0-9]*$")) {
                    String[] expression3 = data.split(" ");
                    String arr2 ="";
                    for (String s : expression3) {
                        arr2 += s;
                    }
                    PostFixConverter pc = new PostFixConverter(arr2);
                    PostFixCalculator calc = new PostFixCalculator(pc.getPostfixAsList());
                    System.out.println(calc.result());
                } else if (expression[0].matches("[0-9]")) {
                    String[] expression3 = data.split(" ");
                    String arr2 ="";
                    for (String s : expression3) {
                        arr2 += s;
                    }
                    PostFixConverter pc = new PostFixConverter(arr2);
                    PostFixCalculator calc = new PostFixCalculator(pc.getPostfixAsList());
                    System.out.println(calc.result());
                }
            } else if (data2.matches("(.*)\\w+")) {
                String k = map.get(blocks[0]);
                System.out.println(k);
            } else if (blocks.length == 1) {
                System.out.println(blocks[0]);
            }
        }
    }
}

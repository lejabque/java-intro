public class Sum {
    public static void main(String[] args) {
        int sum_result = 0;
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            int j = 0; // проход по символам строки
            while (j < s.length()) {
                char c = s.charAt(j);
                String new_num = "";
                if (c == '-' && j + 1 < s.length()) { // случай с отрицательным числом отдельно
                    if (Character.isDigit(s.charAt(j + 1))) {
                        new_num += c;
                        j++;
                        c = s.charAt(j);
                    }
                }
                if (Character.isDigit(c)) {
                    while (Character.isDigit(c) && j < s.length()) {
                        new_num += c;
                        j++;
                        if (j < s.length()) {
                            c = s.charAt(j);
                        }
                    }
                    sum_result += Integer.parseInt(new_num);
                } else {
                    j++;
                }
            }
        }
        System.out.println(sum_result);
    }
}
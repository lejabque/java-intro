public class Sum {
    public static void main(String[] args) {
        int sum_result = 0;
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            int num_begin = 0; //
            int num_end = 0; // индекс конца числа (+1)
            while (num_begin < s.length()) {
                if (!Character.isWhitespace(s.charAt(num_begin))) {
                    num_end = num_begin;
                    while (num_end < s.length() && !Character.isWhitespace(s.charAt(num_end))) {
                        num_end++;
                    }
                    sum_result += Integer.parseInt(s.substring(num_begin, num_end));
                    num_begin = num_end;
                } else {
                    num_begin++;
                }
            }
        }
        System.out.println(sum_result);
    }
}
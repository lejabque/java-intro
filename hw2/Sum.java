public class Sum {
    public static void main(String[] args) {
        int sum_result = 0;
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            int num_start = 0; // индекс начала числа
            int num_end = 0; // индекс конца числа
            while (num_start < s.length()) {
                if (!Character.isWhitespace(s.charAt(num_start))) {
                    num_end = num_start;
                    while (num_end < s.length() && !Character.isWhitespace(s.charAt(num_end)) ) {
                        num_end++;
                    }
                    sum_result += Integer.parseInt(s.substring(num_start, num_end));
                    num_start = num_end;
                } else {
                    num_start++;
                }
            }
        }
        System.out.println(sum_result);
    }
}
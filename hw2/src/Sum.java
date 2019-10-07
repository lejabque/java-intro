public class Sum {
    public static void main(String[] args) {
        int sumResult = 0;
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            int beginIndex = 0;
            int endIndex = 0;
            while (beginIndex < s.length()) {
                if (!Character.isWhitespace(s.charAt(beginIndex))) {
                    endIndex = beginIndex;
                    while (endIndex < s.length() && !Character.isWhitespace(s.charAt(endIndex))) {
                        endIndex++;
                    }
                    sumResult += Integer.parseInt(s.substring(beginIndex, endIndex));
                    beginIndex = endIndex;
                } else {
                    beginIndex++;
                }
            }
        }
        System.out.println(sumResult);
    }
}
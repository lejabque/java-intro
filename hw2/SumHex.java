public class SumHex {
    public static void main(String[] args) {
        int sumResult = 0;
        for (int i = 0; i < args.length; i++) {
            String s = args[i].toLowerCase();
            int beginIndex = 0;
            int endIndex = 0;
            while (beginIndex < s.length()) {
                if (!Character.isWhitespace(s.charAt(beginIndex))) {
                    boolean hex = false;
                    if (s.charAt(beginIndex) == '0' && beginIndex + 1 < s.length() && s.charAt(beginIndex + 1) == 'x') {
                        hex = true;
                        beginIndex += 2;
                    }
                    endIndex = beginIndex;
                    while (endIndex < s.length() && !Character.isWhitespace(s.charAt(endIndex))) {
                        endIndex++;
                    }
                    if (hex) {
                        sumResult += (int) Long.parseLong(s.substring(beginIndex, endIndex), 16);
                    } else {
                        sumResult += Integer.parseInt(s.substring(beginIndex, endIndex));

                    }
                    beginIndex = endIndex;
                } else {
                    beginIndex++;
                }
            }
        }
        System.out.println(sumResult);
    }
}
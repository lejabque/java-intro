public class SumHex {
    public static void main(String[] args) {
        int sumResult = 0;
        for (int i = 0; i < args.length; i++) {
            String s = args[i].toLowerCase();
            int index = 0;
            while (index < s.length()) {
                if (!Character.isWhitespace(s.charAt(index))) {
                    int beginIndex = index;
                    while (index < s.length() && !Character.isWhitespace(s.charAt(index))) {
                        index++;
                    }
                    if (beginIndex + 1 < s.length() && s.charAt(beginIndex) == '0' && s.charAt(beginIndex + 1) == 'x') {
                        sumResult += Integer.parseUnsignedInt(s.substring(beginIndex + 2, index), 16);
                    } else {
                        sumResult += Integer.parseInt(s.substring(beginIndex, index));
                    }
                } else {
                    index++;
                }
            }
        }
        System.out.println(sumResult);
    }
}

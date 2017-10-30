class Solution {
    public int calPoints(String[] ops) {
        if (ops[0].equals("C")) return 0;
        int res = 0;
        for (int i = 0; i < ops.length; i++) {
            if (ops[i].equals("C")) {
                ops[i] = "N";
                String pre = "";
                for (int j = i - 1; j >= 0; j--) {
                    if (ops[j] != "N") {
                        pre = ops[j];
                        ops[j] = "N";
                        break;                     
                    }
                }
                res -= Integer.parseInt(pre);
            }
            else if (ops[i].equals("D")) {
                String pre = "";
                for (int j = i - 1; j >= 0; j--) {
                    if (ops[j] != "N") {
                        pre = ops[j];
                        break;
                    }
                }
                int temp = 2 * Integer.parseInt(pre);
                ops[i] = String.valueOf(temp);
                res += temp;
            }
            else if (ops[i].equals("+")) {
                String a = "", b = "";
                for (int j = i - 1; j >= 0; j --) {
                    if (ops[j] != "N") {
                        a = ops[j];
                        for (int k = j - 1; k >= 0; k--) {
                            if (ops[k] != "N") {
                                b = ops[k];
                                break;
                            }
                        }
                        break;
                    }
                }
                int temp = Integer.parseInt(a) + Integer.parseInt(b);
                ops[i] = String.valueOf(temp);
                res += temp;
            }
            else
                res += Integer.parseInt(ops[i]);
                
        }
        return res;
    }
}

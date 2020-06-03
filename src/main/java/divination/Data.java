package divination;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private static final Map<Integer, Integer> MAP = new HashMap<Integer, Integer>();
    private static final Map<String, String> 神煞 = new HashMap<String, String>();
    static {
//        Data.MAP.put(0, 2);
//        Data.MAP.put(1, 3);
//        Data.MAP.put(2, 4);
//        Data.MAP.put(3, 5);
//        Data.MAP.put(4, 6);
//        Data.MAP.put(5, 7);
//        Data.MAP.put(6, 8);
//        Data.MAP.put(7, 9);
//        Data.MAP.put(8, 10);
//        Data.MAP.put(9, 11);
//        Data.MAP.put(10, 0);
//        Data.MAP.put(11, 1);
//        Data.MAP.put(12, 2);
        Data.MAP.put(0, -45);
        Data.MAP.put(1, -15);
        Data.MAP.put(2, 15);
        Data.MAP.put(3, 45);
        Data.MAP.put(4, 75);
        Data.MAP.put(5, 105);
        Data.MAP.put(6, 135);
        Data.MAP.put(7, 165);
        Data.MAP.put(8, 195);
        Data.MAP.put(9, 225);
        Data.MAP.put(10, 255);
        Data.MAP.put(11, 285);
        Data.MAP.put(12, 315);
        // 天乙贵人:甲戊并牛羊，乙己鼠猴乡，丙丁猪鸡位，壬癸蛇兔藏，庚辛逢虎马，此是贵人方。〇
        // 天乙贵人:甲戊庚牛羊，乙己鼠猴乡，丙丁猪鸡位，壬癸蛇兔藏，六辛逢虎马，此是贵人方。×
        Data.神煞.put("001", "天乙贵人");
        Data.神煞.put("007", "天乙贵人");
        Data.神煞.put("401", "天乙贵人");
        Data.神煞.put("407", "天乙贵人");
        Data.神煞.put("100", "天乙贵人");
        Data.神煞.put("108", "天乙贵人");
        Data.神煞.put("500", "天乙贵人");
        Data.神煞.put("508", "天乙贵人");
        Data.神煞.put("209", "天乙贵人");
        Data.神煞.put("211", "天乙贵人");
        Data.神煞.put("309", "天乙贵人");
        Data.神煞.put("311", "天乙贵人");
        Data.神煞.put("803", "天乙贵人");
        Data.神煞.put("805", "天乙贵人");
        Data.神煞.put("903", "天乙贵人");
        Data.神煞.put("905", "天乙贵人");
        Data.神煞.put("602", "天乙贵人");
        Data.神煞.put("606", "天乙贵人");
        Data.神煞.put("702", "天乙贵人");
        Data.神煞.put("706", "天乙贵人");
    }
    public static int get(int key) {
        return Data.MAP.get(key);
    }
    public static String get神煞(int 天干, int 地支) {
        String key = 天干 + (地支 < 10 ? "0" : "") + 地支;
        String value = Data.神煞.get(key);
        return value == null ? "" : value;
    }
    /**
     * 正月生者见丁，二月生者见申，三月生者见壬，四月生者见辛，五月生者见亥，六月生者见甲，
     * 七月生者见癸，八月生者见寅，九月生者见丙，十月生者见乙，十一月生者见巳，十二月生者见庚。
     * @param month
     * @return
     */
    public static int get天德贵人1(int month) {
        if (month == 1) {
            return 3;
        }
        if (month == 3) {
            return 8;
        }
        if (month == 4) {
            return 7;
        }
        if (month == 6) {
            return 0;
        }
        if (month == 7) {
            return 9;
        }
        if (month == 9) {
            return 2;
        }
        if (month == 10) {
            return 1;
        }
        if (month == 12) {
            return 6;
        }
        return -1;
    }
    public static int get天德贵人2(int month) {
        if (month == 2) {
            return 8; // 
        }
        if (month == 5) {
            return 11; // 
        }
        if (month == 8) {
            return 2; // 
        }
        if (month == 11) {
            return 5; // 
        }
        return -1;
    }
    /**
     * 寅、午、戌月出生，四柱中见天干丙；
     * 申、子、辰月出生，四柱中见天干壬；
     * 亥、卯、未月出生，四柱中见天干甲；
     * 巳、酉、丑月出生，四柱天干见庚者均为月德贵人。
     * @param month
     * @return
     */
    public static int get月德贵人1(int month) {
        if (month == 3 || month == 6 || month == 10 ) {
            return 2;
        }
        if (month == 8 || month == 0 || month == 4 ) {
            return 8;
        }
        if (month == 11 || month == 3 || month == 7 ) {
            return 0;
        }
        if (month == 5 || month == 9 || month == 1 ) {
            return 6;
        }
        return -1;
    }
}

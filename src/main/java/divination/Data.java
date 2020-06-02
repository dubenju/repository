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
}

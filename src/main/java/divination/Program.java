package divination;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import divination.utils.JulianDay;
import divination.utils.Lauar;
import divination.utils.SolarTerm;

public class Program {
    private static final DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    private LocalDateTime 日期;
    private Lauar 农历;
    private LocalDateTime 前一个节气;
    private LocalDateTime 后一个节气;

    private int jdn;
    private int[] 天干 = new int[4];
    private int[] 地支 = new int[4];
    private int 性别;
    private int   胎元天干;
    private int   胎元地支;
    private int   命宫天干;
    private int   命宫地支;
    private int[] 大运天干 = new int[10];
    private int[] 大运地支 = new int[10];
    private int[] 小运天干 = new int[100];
    private int[] 小运地支 = new int[100];
    private int[] 流年天干 = new int[100];
    private int[] 流年地支 = new int[100];
    
    public Program(String 日期, int 性别) {
        this.性别 = 性别;
        this.日期 = LocalDateTime.parse(日期,df);
        this.农历 = new Lauar(this.日期.getYear(), this.日期.getMonthValue(), this.日期.getDayOfMonth());
        this.jdn = JulianDay.getJDN(this.日期.getYear(), this.日期.getMonthValue(), this.日期.getDayOfMonth());
        this.set节气();

        this.天干[0] = FourPillars.getYear天干(this.农历.get()[0]);
        this.地支[0] = FourPillars.getYear地支(this.农历.get()[0]);
        // 十二月建即：正月建寅，二月建卯，三月建辰，四月建巳，五月建午，六月建未，七月建申，八月建酉，九月建戌，十月建亥，十一月建子，十二月建丑。
        // 正月：立春。二月：惊蛰。三月：清明。四月：立夏。五月：芒种。六月：小暑。七月：立秋。八月：白露。九月：寒露。十月：立冬。十一月：大雪。十二月：小寒。
        this.地支[1] = FourPillars.getMonth地支(this.日期);
        this.天干[1] = (((this.地支[1] - 2) < 0) ? (this.地支[1] + 10) : (this.地支[1] - 2)) + FourPillars.getMonth天干(this.天干[0]);
        if (this.天干[1] >= 10) this.天干[1] -= 10;
        this.天干[2] = (jdn - 1) % 10;
        this.地支[2] = (jdn + 1) % 12;
        this.天干[3] = (FourPillars.getHour天干(this.天干[2]) + FourPillars.getHour地支(this.日期.getHour())) % 10;
        this.地支[3] = FourPillars.getHour地支(this.日期.getHour());

        set流年();
        set胎元();
        set命宫();
    }

    private void set节气() {
        int key = this.农历.get()[1] - 1;
        LocalDateTime a = JulianDay.getDateTime(2451545.5D + 8.0D /24.0D + SolarTerm.jiaoCal(365.2422 * (this.日期.getYear() - 2000) - 50, Data.get(key), 0));
        int cmp = this.日期.compareTo(a);
        if (cmp < 0) {
            this.后一个节气 = a;
            this.前一个节气 = JulianDay.getDateTime(2451545.5D + 8.0D /24.0D + SolarTerm.jiaoCal(365.2422 * (this.日期.getYear() - 2000) - 50, Data.get(key - 1), 0));
        } else if (cmp > 0) {
            this.前一个节气 = a;
            this.后一个节气 = JulianDay.getDateTime(2451545.5D + 8.0D /24.0D + SolarTerm.jiaoCal(365.2422 * (this.日期.getYear() - 2000) - 50, Data.get(key + 1), 0));
        } else {
            this.后一个节气 = a;
            this.前一个节气 = JulianDay.getDateTime(2451545.5D + 8.0D /24.0D + SolarTerm.jiaoCal(365.2422 * (this.日期.getYear() - 2000) - 50, Data.get(key - 1), 0));
        }
    }
    public void set流年() {
        int 天干 = this.天干[0];
        int 地支 = this.地支[0];
        for (int i = 0; i < 100; i ++) {
            天干 ++;
            if (天干 >= 10) { 天干 -= 10;}
            地支 ++;
            if (地支 >= 12) { 地支 -= 12;}
            this.流年天干[i] = 天干;
            this.流年地支[i] = 地支;
        }
    }
    public void set胎元() {
        this.胎元天干 = this.天干[1] + 1;
        if (this.胎元天干 >= 10) { this.胎元天干 -= 10;}
        this.胎元地支 = this.地支[1] + 3;
        if (this.胎元地支 >= 12) { this.胎元地支 -= 12;}
    }
    /**
     * 命宫起法：以子位为正月， 亥为二月， 戌为三月， 逆数至丑为十二月止。  然后把生时落在生月支上， 顺数至卯， 卯就为命宫。 命宫之天干，按生年起月法定出。
     * 如1998年五月十七日寅时生人， 按子位正月， 亥为二月， 戌为三月， 酉为四月， 申为五月， 
     * 然后在申上起寅时， 酉上起卯时， 则酉为命宫支。再按1998戊寅年起月法， 其酉位天干为辛， 则辛酉为命宫。
     */
    public void set命宫() {
        this.命宫地支 = 0;
//        System.out.println(地支.class.getEnumConstants()[this.命宫地支] + "," + this.农历.get()[1]);
        for (int i = 0; i < this.农历.get()[1] - 1; i ++) {
            this.命宫地支 --;
            if (this.命宫地支 < 0) { this.命宫地支 += 12;}
//            System.out.println("A" + 地支.class.getEnumConstants()[this.命宫地支]);
        }
        int max = this.地支[3] <= 3 ? 3 - this.地支[3] : 15 - this.地支[3];
//        System.out.println("max=" + max);
        for (int i = 0; i < max; i ++) {
//            if (this.命宫地支 == 3) break; 
            this.命宫地支 ++;
            if (this.命宫地支 >= 12) { this.命宫地支 -= 12;}
//            System.out.println("B" + 地支.class.getEnumConstants()[this.命宫地支]);
        }
        this.命宫天干 = ((((this.命宫地支 - 2) < 0) ? (this.命宫地支 + 10) : (this.命宫地支 - 2)) + FourPillars.getMonth天干(this.天干[0])) % 10;
    }
    public int get大运() {
        Duration duration = null;
        if ((this.天干[0] %2 == 0 && this.性别 == 1) ||
             this.天干[0] %2 != 0 && this.性别 != 1) {
            // 顺数
            duration = Duration.between(this.日期, this.后一个节气);
        } else {
            // 逆数
            duration = Duration.between(this.前一个节气, this.日期);
        }
//        System.out.println(duration.toHours());
        double days = duration.toHours() /24.0D;
        int 大运 = (int) (days / 3);
        if ((days - 大运 * 3) >= 2 ) {
            大运 ++;
        }

        if ((this.天干[0] %2 == 0 && this.性别 == 1) ||
            this.天干[0] %2 != 0 && this.性别 != 1) {
            int 天干 = this.天干[1];
            int 地支 = this.地支[1];
            for (int i = 0; i < 10; i ++) {
                天干 ++;
                if (天干 >= 10) { 天干 -= 10;}
                地支 ++;
                if (地支 >= 12) { 地支 -= 12;}
                this.大运天干[i] = 天干;
                this.大运地支[i] = 地支;
            }
            天干 = this.天干[3];
            地支 = this.地支[3];
            for (int i = 0; i < 100; i ++) {
                天干 ++;
                if (天干 >= 10) { 天干 -= 10;}
                地支 ++;
                if (地支 >= 12) { 地支 -= 12;}
                this.小运天干[i] = 天干;
                this.小运地支[i] = 地支;
            }
        } else {
            int 天干 = this.天干[1];
            int 地支 = this.地支[1];
            for (int i = 0; i < 10; i ++) {
                天干 --;
                if (天干 < 0) { 天干 += 10;}
                地支 --;
                if (地支 < 0) { 地支 += 12;}
                this.大运天干[i] = 天干;
                this.大运地支[i] = 地支;
            }
            天干 = this.天干[3];
            地支 = this.地支[3];
            for (int i = 0; i < 100; i ++) {
                天干 --;
                if (天干 < 0) { 天干 += 10;}
                地支 --;
                if (地支 < 0) { 地支 += 12;}
                this.小运天干[i] = 天干;
                this.小运地支[i] = 地支;
            }
        }
        return 大运;
    }

    /**
     * 甲乙生人子午中，
     * 丙丁鸡兔定亨通；
     * 戊己两干临四季，
     * 庚辛寅亥禄丰隆；
     * 壬癸巳申偏喜美，
     * 值此应当福气钟；
     * 更须贵格来相扶，
     * 候封万户到三公
     * 以年干或日干为准，四柱地支见者为是。
     * 年干或日干为甲乙，四柱地支见子或午；
     * 年干或日干为丙丁，四柱地支见卯或酉；
     * 年干或日干为戊己，四柱地支中见辰、戌、丑、未；
     * 年干或日干为庚辛，四柱地支见寅或亥；
     * 年干或日干为壬癸，四柱地支见巳或申均为太极贵人。
     * @return
     */
    private boolean is太极贵人() {
        boolean result = false;
        if (this.天干[0] == 0 || this.天干[3] == 0 ||
            this.天干[0] == 1 || this.天干[3] == 1) {
            if (this.地支[0] == 0 || this.地支[1] == 0 || this.地支[2] == 0 || this.地支[3] == 0 ||
                this.地支[0] == 6 || this.地支[1] == 6 || this.地支[2] == 6 || this.地支[3] == 6) {
                result = true;
            }
        }
        if (this.天干[0] == 2 || this.天干[3] == 2 ||
            this.天干[0] == 3 || this.天干[3] == 3) {
            if (this.地支[0] == 3 || this.地支[1] == 3 || this.地支[2] == 3 || this.地支[3] == 3 ||
                this.地支[0] == 9 || this.地支[1] == 9 || this.地支[2] == 9 || this.地支[3] == 9) {
                result = true;
            }
        }
        if (this.天干[0] == 4 || this.天干[3] == 4 ||
            this.天干[0] == 5 || this.天干[3] == 5) {
            if (this.地支[0] == 1 || this.地支[1] == 1 || this.地支[2] == 1 || this.地支[3] == 1 ||
                this.地支[0] == 4 || this.地支[1] == 4 || this.地支[2] == 4 || this.地支[3] == 4 ||
                this.地支[0] == 7 || this.地支[1] == 7 || this.地支[2] == 7 || this.地支[3] == 7 ||
                this.地支[0] ==10 || this.地支[1] ==10 || this.地支[2] ==10 || this.地支[3] ==10) {
                result = true;
            }
        }
        if (this.天干[0] == 6 || this.天干[3] == 6 ||
            this.天干[0] == 7 || this.天干[3] == 7) {
            if (this.地支[0] == 2 || this.地支[1] == 2 || this.地支[2] == 2 || this.地支[3] == 2 ||
                this.地支[0] ==11 || this.地支[1] ==11 || this.地支[2] ==11 || this.地支[3] ==11) {
                result = true;
            }
        }
        if (this.天干[0] == 8 || this.天干[3] == 8 ||
            this.天干[0] == 9 || this.天干[3] == 9) {
            if (this.地支[0] == 5 || this.地支[1] == 5 || this.地支[2] == 5 || this.地支[3] == 5 ||
                this.地支[0] == 8 || this.地支[1] == 8 || this.地支[2] == 8 || this.地支[3] == 8) {
                result = true;
            }
        }
        return result;
    }
    private boolean is天德贵人() {
        boolean result = false;
        int a = Data.get天德贵人1(this.农历.get()[1]);
        if (a > 0) {
            if (this.天干[0] == a || this.天干[1] == a || this.天干[2] == a || this.天干[3] == a) {
                result = true;
            }
        }
        int b = Data.get天德贵人2(this.农历.get()[1]);
        if (b > 0) {
            if (this.地支[0] == b || this.地支[1] == b || this.地支[2] == b || this.地支[3] == b) {
                result = true;
            }
        }
        return result;
    }
    private boolean is月德贵人() {
        boolean result = false;
        int a = Data.get月德贵人1(this.地支[1]);
        if (a > 0) {
            if (this.天干[0] == a || this.天干[1] == a || this.天干[2] == a || this.天干[3] == a) {
                result = true;
            }
        }
        return result;
    }
    /**
     * 天上三奇：甲戊庚。甲日戊月庚年。
     * 人中三奇：壬癸辛。壬日癸月辛年。
     * 地下三奇：乙丙丁。乙日丙月丁年。
     * @return
     */
    private int is三奇贵人() {
        if ((this.天干[0] == 0 && this.天干[1] == 4 && this.天干[2] == 6 ) ||
            (this.天干[1] == 0 && this.天干[2] == 4 && this.天干[3] == 6 )) {
            return 3;
        }
        if ((this.天干[0] == 7 && this.天干[1] == 8 && this.天干[2] == 9 ) ||
            (this.天干[1] == 7 && this.天干[2] == 8 && this.天干[3] == 9 )) {
            return 1;
        }
        if ((this.天干[0] == 1 && this.天干[1] == 2 && this.天干[2] == 3 ) ||
            (this.天干[1] == 1 && this.天干[2] == 2 && this.天干[3] == 3 )) {
            return 2;
        }
        return -1;
    }
    public String toString() {
        StringBuilder buf = new StringBuilder();
        buf.append(df.format(this.日期));
        buf.append("\n");
        buf.append(男女.class.getEnumConstants()[this.性别]);
        buf.append("\n");
        buf.append(this.农历.getLunarDay());
        buf.append("\n");
//        buf.append(this.农历.get()[0]);
//        buf.append("\n");
//        buf.append(this.农历.get()[3]);
//        buf.append(this.农历.get()[1]);
//        buf.append("\n");
//        buf.append(this.农历.get()[2]);
//        buf.append("\n");
        buf.append(天干.class.getEnumConstants()[this.天干[0]]);
        buf.append(地支.class.getEnumConstants()[this.地支[0]]);
        buf.append(" ");
        buf.append(天干.class.getEnumConstants()[this.天干[1]]);
        buf.append(地支.class.getEnumConstants()[this.地支[1]]);
        buf.append(" ");
        buf.append(天干.class.getEnumConstants()[this.天干[2]]);
        buf.append(地支.class.getEnumConstants()[this.地支[2]]);
        buf.append(" ");
        buf.append(天干.class.getEnumConstants()[this.天干[3]]);
        buf.append(地支.class.getEnumConstants()[this.地支[3]]);
        buf.append("\n胎元:");
        buf.append(天干.class.getEnumConstants()[this.胎元天干]);
        buf.append(地支.class.getEnumConstants()[this.胎元地支]);
        buf.append("\n命宫:");
        buf.append(天干.class.getEnumConstants()[this.命宫天干]);
        buf.append(地支.class.getEnumConstants()[this.命宫地支]);
        buf.append("\n");
        buf.append("神煞\n");
        buf.append("年柱" + Data.get神煞(this.天干[0], this.地支[0]));
        buf.append("\n");
        buf.append("日柱" + Data.get神煞(this.天干[3], this.地支[3]));
        buf.append("\n");
        buf.append("胎元" + Data.get神煞(this.胎元天干, this.胎元地支));
        buf.append("\n");
        buf.append("命宫" + Data.get神煞(this.命宫天干, this.命宫地支));
        buf.append("\n");
        buf.append(is太极贵人() ? "太极贵人" : "");
        buf.append("\n");
        buf.append(is天德贵人() ? "天德贵人" : "");
        buf.append("\n");
        buf.append(is月德贵人() ? "月德贵人" : "");
        buf.append("\n");
        buf.append(is三奇贵人() > 0 ? "三奇贵人" : "");
        buf.append("\n");
        buf.append(this.天干[0] %2 == 0 ? "阳年" : "阴年");
        buf.append("\n");
        
        int 大运 = this.get大运();
        for (int i = 0; i < 10; i ++) {
            buf.append((大运 < 10 ? "0" : "") + 大运 + "岁大运");
            buf.append(天干.class.getEnumConstants()[this.大运天干[i]]);
            buf.append(地支.class.getEnumConstants()[this.大运地支[i]]);
            buf.append("\n");
            大运 += 10;
        }
        for (int i = 0; i < 100; i ++) {
            buf.append(((i + 1) < 10 ? "00" : ((i + 1) < 100 ? "0" : "")) + (i + 1) + "岁小运:");
            buf.append(天干.class.getEnumConstants()[this.小运天干[i]]);
            buf.append(地支.class.getEnumConstants()[this.小运地支[i]]);
            buf.append(",");
            buf.append((this.日期.getYear() + i + 1) + "年,流年:");
            buf.append(天干.class.getEnumConstants()[this.流年天干[i]]);
            buf.append(地支.class.getEnumConstants()[this.流年地支[i]]);
            buf.append("\n");
        }
        buf.append("\n");
        return buf.toString();
    }
    public static void main(String[] args) {
        String 日期 = "2020/06/01 12:00:00";
        日期 = "1998/06/09 04:00:00";
        日期 = "1977/07/08 11:00:00";
        int 性别 = 1; // 男
        Program proc = new Program(日期, 性别);
        System.out.println(proc);
    }

}

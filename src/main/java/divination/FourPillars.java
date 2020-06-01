/**
 * 
 */
package divination;

import divination.utils.JulianDay;
import divination.utils.Moon;
import divination.utils.SolarTerm;

/**
 * @author benju
 *
 */
public class FourPillars {
    public static int getYear天干(int year) {
        int iYear = year;
        if (iYear == 0) {
            iYear = 1;
        }
        /*
         * 公元前2年：己未
         * 公元前1年：庚申8
         * 公元元年：辛酉9
         * 公元2年：壬戌10
         * 公元3年：癸亥11
         * 公元4年：甲子0
         */
        int result = ((iYear < 0) ? (iYear - 3) : (iYear - 4) ) % 10;
        if (result < 0) result += 10;
        return result;
    }
    public static int getYear地支(int year) {
        int iYear = year;
        if (iYear == 0) {
            iYear = 1;
        }
        /*
         * 公元前2年：己未
         * 公元前1年：庚申8
         * 公元元年：辛酉9
         * 公元2年：壬戌10
         * 公元3年：癸亥11
         * 公元4年：甲子0
         */
        int result = ((iYear < 0) ? (iYear - 3) : (iYear - 4) ) % 12;
        if (result < 0) result += 12;
        return result;
    }  

    /**
     * 年上起月法
     * 甲己之年丙作首，
     * 乙庚之岁戊为头，
     * 丙辛之岁寻庚上，
     * 丁壬壬寅顺水流，
     * 若问戊癸何处起，
     * 甲寅之上好追求。
     * 
     * @param month
     * @return Month天干
     */
    public static int getMonth天干(int year天干) {
        int result = -1;
        switch(year天干) {
        case 0:
        case 5:
            result = 2;
            break;
        case 1:
        case 6:
            result = 4;
            break;
        case 2:
        case 7:
            result = 6;
            break;
        case 3:
        case 8:
            result = 8;
            break;
        case 4:
        case 9:
            result = 0;
            break;
        }
        return result;
    }
    /**
     * 日上起时
     * 甲己还加甲，
     * 乙庚丙作初，
     * 丙辛从戊起，
     * 丁壬庚子居，
     * 戊癸何方发，
     * 壬子是真途。
     * 
     * @param month
     * @return Month天干
     */
    public static int getHour天干(int day天干) {
        int result = -1;
        switch(day天干) {
        case 0:
        case 5:
            result = 0;
            break;
        case 1:
        case 6:
            result = 2;
            break;
        case 2:
        case 7:
            result = 4;
            break;
        case 3:
        case 8:
            result = 6;
            break;
        case 4:
        case 9:
            result = 8;
            break;
        }
        return result;
    }
    public static int getHour地支(int hour) {
        int result = -1;
        switch(hour) {
        case 23:
        case 0:
            result = 0;
            break;
        case 1:
        case 2:
            result = 1;
            break;
        case 3:
        case 4:
            result = 2;
            break;
        case 5:
        case 6:
            result = 3;
            break;
        case 7:
        case 8:
            result = 4;
            break;
        case 9:
        case 10:
            result = 5;
            break;
        case 11:
        case 12:
            result = 6;
            break;
        case 13:
        case 14:
            result = 7;
            break;
        case 15:
        case 16:
            result = 8;
            break;
        case 17:
        case 18:
            result = 9;
            break;
        case 19:
        case 20:
            result = 10;
            break;
        case 21:
        case 22:
            result = 11;
            break;
        }
        return result;
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(2020 + ":" + 天干.class.getEnumConstants()[getYear天干(2020)] + 地支.class.getEnumConstants()[getYear地支(2020)]);
//        for (int i = -12; i <= 12; i ++) {
//            System.out.println(i + ":" + 天干.class.getEnumConstants()[getYear天干(i)] + 地支.class.getEnumConstants()[getYear地支(i)]);
//        }
//        System.out.println(_24SolarTerms.getSolatName(1800, "055"));
        // JD 0指定为UT时间B.C.4713年1月1日12:00到UC时间B.C.4713年1月2日12:00的24小时
        System.out.println("-4713/01/01:" + JulianDay.getJDN2(-4713, 1, 1) + "," + JulianDay.getDate(0));
        System.out.println("-1976/01/01:" + JulianDay.getJDN2(-1976, 10, 21) + "," + JulianDay.getDate(1000000));
        System.out.println("0100/01/01:" + JulianDay.getJDN2(100, 1, 1) + "," + JulianDay.getDate(1757585));
        System.out.println("1700/01/01:" + JulianDay.getJDN2(1700, 1, 1) + "," + JulianDay.getDate(2341973));
        System.out.println("1900/01/01:" + JulianDay.getJDN2(1900, 1, 1) + "," + JulianDay.getDate(2415021));
        int jdn = JulianDay.getJDN(2000, 1, 1);
        System.out.println("2000/01/01:" + jdn + 天干.class.getEnumConstants()[(jdn - 1) % 10] + 地支.class.getEnumConstants()[(jdn + 1) % 12]);// 2451545
        System.out.println("2000/01/01:" + JulianDay.getJDN2(2000, 1, 1) + "," + JulianDay.getDate(2451545));
        jdn = JulianDay.getJDN(2020, 5, 31);
        System.out.println("2020/05/31:" + jdn + 天干.class.getEnumConstants()[(jdn - 1) % 10] + 地支.class.getEnumConstants()[(jdn + 1) % 12]);// 2459001
        System.out.println("2020/05/31:" + JulianDay.getJDN2(2020, 5, 31) + "," + JulianDay.getDate(2459001));
        System.out.println("2000/01/01 18:00:00:" + JulianDay.getJD(2000, 1, 1, 18, 0, 0)); // 2451545.25
        System.out.println("2020/05/31:星期" + 星期.class.getEnumConstants()[JulianDay.getW1(2020, 5, 31)]);// 日
        int[] solarTerms = SolarTerm.getSolarTerm(2020, 6, 1);
        System.out.println("2020/06/01:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 6, 1));
        solarTerms = SolarTerm.getSolarTerm(2020, 2, 4); // 立春
        System.out.println("2020/02/04立春315°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 2, 4));
        solarTerms = SolarTerm.getSolarTerm(2020, 2, 19); // 雨水
        System.out.println("2020/02/19雨水330°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 2, 19));
        solarTerms = SolarTerm.getSolarTerm(2020, 3, 5); // 惊蛰
        System.out.println("2020/03/05惊蛰345°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 3, 5));
        solarTerms = SolarTerm.getSolarTerm(2020, 3, 20); // 春分
        System.out.println("2020/03/20春分0°  :" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 3, 20));
        solarTerms = SolarTerm.getSolarTerm(2020, 4, 1); //
        System.out.println("2020/04/01春分-清明:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 4, 1));
        solarTerms = SolarTerm.getSolarTerm(2020, 4, 4); // 清明
        System.out.println("2020/04/04清明15°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 4, 4));
        solarTerms = SolarTerm.getSolarTerm(2020, 4, 19); // 谷雨
        System.out.println("2020/04/19谷雨30°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 4, 19));
        solarTerms = SolarTerm.getSolarTerm(2020, 5, 5); // 立夏
        System.out.println("2020/05/05立夏45° :" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 5, 5));
        solarTerms = SolarTerm.getSolarTerm(2020, 5, 20); // 小满
        System.out.println("2020/05/20小满60°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 5, 20));
        solarTerms = SolarTerm.getSolarTerm(2020, 6, 5); // 芒种
        System.out.println("2020/06/05芒种75°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 6, 5));
        solarTerms = SolarTerm.getSolarTerm(2020, 6, 21); // 夏至
        System.out.println("2020/06/21夏至90°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 6, 21));
        solarTerms = SolarTerm.getSolarTerm(2020, 7, 6); // 小暑
        System.out.println("2020/07/06小暑105°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 7, 6));
        solarTerms = SolarTerm.getSolarTerm(2020, 7, 22); // 大暑
        System.out.println("2020/07/22大暑120°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 7, 22));
        solarTerms = SolarTerm.getSolarTerm(2020, 8, 7); // 立秋
        System.out.println("2020/08/07立秋135°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 8, 7));
        solarTerms = SolarTerm.getSolarTerm(2020, 8, 22); // 处暑
        System.out.println("2020/08/22处暑150°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 8, 22));
        solarTerms = SolarTerm.getSolarTerm(2020, 9, 7); // 白露
        System.out.println("2020/09/07白露165°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 9, 7));
        solarTerms = SolarTerm.getSolarTerm(2020, 9, 22); // 秋分
        System.out.println("2020/09/22秋分180°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 9, 22));
        solarTerms = SolarTerm.getSolarTerm(2020, 10, 8); // 寒露
        System.out.println("2020/10/8寒露195°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 10, 8));
        solarTerms = SolarTerm.getSolarTerm(2020, 10, 23); // 霜降
        System.out.println("2020/10/23霜降210°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 10, 23));
        solarTerms = SolarTerm.getSolarTerm(2020, 11, 7); // 立冬
        System.out.println("2020/11/07立冬225°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 11, 7));
        solarTerms = SolarTerm.getSolarTerm(2020, 11, 22); // 小雪
        System.out.println("2020/11/22小雪240°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 11, 22));
        solarTerms = SolarTerm.getSolarTerm(2020, 12, 7); // 大雪
        System.out.println("2020/12/7大雪255°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 21, 7));
        solarTerms = SolarTerm.getSolarTerm(2020, 12, 21); // 冬至
        System.out.println("2020/12/21冬至270°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2020, 12, 21));
        solarTerms = SolarTerm.getSolarTerm(2021, 1, 5); // 小寒285°
        System.out.println("2021/01/05小寒285°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2021, 1, 5));
        solarTerms = SolarTerm.getSolarTerm(2021, 1, 20); // 大寒300°
        System.out.println("2021/01/20大寒300°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2021, 1, 20));
        solarTerms = SolarTerm.getSolarTerm(2021, 2, 3); // 立春315°
        System.out.println("2021/02/03立春315°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2021, 2, 3));
        solarTerms = SolarTerm.getSolarTerm(2021, 2, 18); // 雨水330°
        System.out.println("2021/02/18雨水330°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2021, 2, 18));
        solarTerms = SolarTerm.getSolarTerm(2021, 3, 5); // 惊蛰345°
        System.out.println("2021/03/05惊蛰345°:" + solarTerms[0] + "," + solarTerms[1] + (solarTerms.length > 2 ? "," + solarTerms[2] : "") + "=" + Moon.MoonAge(2021, 3, 5));

        SolarTerm.testSolarTerm();
        System.out.println(SolarTerm.getSolarTerm(2020, 270));
    }

}

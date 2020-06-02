package divination.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class JulianDay {
    public static double getJDN(int year, int month, int day, int hour, int minute, int second) {
//        BigDecimal v60 = new BigDecimal(60);
//        BigDecimal v24 = new BigDecimal(24);
//        BigDecimal s = new BigDecimal(second).divide(v60, 10, BigDecimal.ROUND_HALF_UP);
//        BigDecimal m = s.add(new BigDecimal(minute)).divide(v60, 10, BigDecimal.ROUND_HALF_UP);
//        BigDecimal h = m.add(new BigDecimal(hour)).divide(v24, 10, BigDecimal.ROUND_HALF_UP);
//        System.out.println(h.doubleValue());
        return ((second / 60.0D + minute) / 60.0D + hour) / 24.0D + getJDN(year, month, day);
//        return h.doubleValue() + getJDN(year, month, day);
    }
    /**
     * 儒略日数,Julian Day Number，JDN
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int getJDN(int year, int month, int day) {
        return getJDNbyGregorian(year, month, day);
    }
    public static int getJDN2(int year, int month, int day) {
        int y = year;
        if (y < 0) {
            y ++;
        }
        int m = month;
        if (m <= 2) {
            y--;
            m += 12;
        }
        int a = y / 100;
        int b = 2 - a + (int) (a / 4);
        return (int) ((int) (365.25 * (y + 4716)) + (int) (30.6001 * (m + 1)) + day + 0.5000115740D + b - 1524.5);
    }
    public static LocalDate getDate(int jdn) {
        double djdn = jdn + 0.5D;
        int z = (int) djdn;
//        System.out.println("z=" + z);
        double f = djdn - z;
//        System.out.println("f=" + f);
        int a1 = (int) ((z-1867216.25) / 36524.25);
        int a = z + 1 + a1 - ((int) (a1 /4));
        int b = a + 1524;
        int c = (int) ((b - 122.1) / 365.25);
        int d = (int) (365.25 * c);
        int e = (int) ((b-d) / 30.6001);
        int day = (int) (b - d - ((int) (30.6001 * e)) + f);
        int m = -1;
        if (e < 14) {
            m = e -1;
        } else if (e == 14 || e == 15) {
            m = e - 13;
        }
        int y = -1;
        if (m > 2) {
            y = c - 4716;
        } else if (m == 1 || m == 2) {
            y = c - 4715;
        }
        if (y < 0) {
            y --;
        }
//        System.out.println(y + "/" + m + "/" + day);
        return LocalDate.of(y, m, day);
    }
    public static LocalDateTime getDateTime(double jdn) {
        double djdn = jdn; // + 0.5D;
        int z = (int) djdn;
        double f = djdn - z;
        int a1 = (int) ((z-1867216.25) / 36524.25);
        int a = z + 1 + a1 - ((int) (a1 /4));
        int b = a + 1524;
        int c = (int) ((b - 122.1) / 365.25);
        int d = (int) (365.25 * c);
        int e = (int) ((b-d) / 30.6001);
        int day = (int) (b - d - ((int) (30.6001 * e)) + f);
        int m = -1;
        if (e < 14) {
            m = e -1;
        } else if (e == 14 || e == 15) {
            m = e - 13;
        }
        int y = -1;
        if (m > 2) {
            y = c - 4716;
        } else if (m == 1 || m == 2) {
            y = c - 4715;
        }
        if (y < 0) {
            y --;
        }
        double time = f * 24;
        int hour = (int)time;
        double fm = time - hour;
        time = fm * 60;
        int minute = (int) time;
        double fs = time - minute;
        time = fs * 60;
        int second = (int) time;
        double fms = time - second;
        int nsecond = (int) (fms * 1000000000);
//        System.out.println(y + "/" + m + "/" + day + " " + hour + ":" + minute + ":" + second + "." + nsecond);
        return LocalDateTime.of(y, m, day, hour, minute, second, nsecond);
    }
    /**
     * W1   0   1   2   3   4   5   6
     * Day of the week Sun Mon Tue Wed Thu Fri Sat
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static int getW1(int year, int month, int day) {
        return (getJDNbyGregorian(year, month, day) + 1) % 7;
    }

    /**
     * 儒略日期Julian date，JD
     * 2000/01/01 18:00:00 JD = 2451545.25
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static double getJD(int year, int month, int day, int hour, int minute,int second) {

        BigDecimal result = new BigDecimal(getJDN(year, month, day))
            .add(new BigDecimal(hour - 12).divide(new BigDecimal(24), 10, BigDecimal.ROUND_HALF_UP))
            .add(new BigDecimal(minute).divide(new BigDecimal(1440), 10, BigDecimal.ROUND_HALF_UP))
            .add(new BigDecimal(second).divide(new BigDecimal(86400), 10, BigDecimal.ROUND_HALF_UP));
        return result.doubleValue();
    }
    /**
     * 由于儒略日数字位数太多，国际天文学联合会于1973年采用简化儒略日（MJD），其定义为MJD = JD - 2400000.5。
     * MJD相应的起点是1858年11月17日世界时0时。
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static double getMJD(int year, int month, int day, int hour, int minute,int second) {
        return getJD(year, month, day, hour, minute, second) - 2400000.5;
    }
    /**
     * 在1582年10月15日之后实行格里高利历，规定每400年97闰，平均年长度为365.2425天。
     * @param year
     * @param month
     * @param day
     * @return
     */
    private static int getJDNbyGregorian(int year, int month, int day) {
        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;
        int jdn = day + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
        return jdn;
    }
    /**
     * 1582年10月4日以前，公历规定每4年设置一个闰年，平均年长度365.25天，这期间的公历称为儒略历。
     * @param year
     * @param month
     * @param day
     * @return
     */
    private static int getJDNbyJulian(int year, int month, int day) {
        int a = (14 - month) / 12;
        int y = year + 4800 - a;
        int m = month + 12 * a - 3;
        int jdn = day + (153 * m + 2 / 5) + 365 * y + y / 4 -32083;
        return jdn;
    }
}

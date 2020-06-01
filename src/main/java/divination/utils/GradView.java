package divination.utils;
import java.util.Calendar;
public class GradView {
    
    private int[] data1;  
    private String[] data2;  
  
    public GradView(int year, int month) {  
        Calendar calendar = Calendar.getInstance();  
        calendar.set(year, month - 1, 1);// 当月1日  
        init(calendar);  
  
    }  
  
    private void init(Calendar calendar) {  
        int weekOfFirstDay = calendar.get(Calendar.DAY_OF_WEEK);// 第一天的星期  
  
        data1 = new int[42];  
        data2 = new String[42];  
        Lauar lauar;  
        calendar.add(Calendar.DATE, -(weekOfFirstDay - 1));  
        for (int i = 0; i < data1.length; i++) {  
            data1[i] = calendar.get(Calendar.DAY_OF_MONTH);  
            lauar = new Lauar(calendar);  
  
            String data;  
            if (null != (data = lauar.getLunarHoliday())) {  
                data2[i] = data;  
            } else if (null != (data = lauar.getHoliday())) {  
                data2[i] = data;  
            } else if (null != (data = lauar.getSoralTerm())) {  
                data2[i] = data;  
            } else {  
                data2[i] = lauar.getLunarDayOfMonth();  
                if ("初一".equals(data2[i])) {  
                    data2[i] = lauar.getLunarMonth();  
                }  
            }  
  
            calendar.add(Calendar.DATE, 1);  
        }  
    }  
  
    public static void main(String[] args) {  
        GradView view = new GradView(2020, 6);  
        for (int i = 0; i < view.data1.length; i++) {  
            System.out.print((view.data1[i]< 9 ? " " : "" ) + view.data1[i] + ".");  
            System.out.print(view.data2[i] + "\t");  
            if ((i + 1) % 7 == 0)  
                System.out.println();  
        }  
    } 
}

import javax.swing.*;
import java.awt.*;
import java.util.StringTokenizer;

//класс, отображающий диаграмму
public class Diagram extends JFrame {
    //строка с величинами углов для диаграммы
    String sChart;

    public Diagram(String sChart) {
        this.sChart = sChart;
        //задание границ графической формы
        setBounds(640, 100,390,360);
        setVisible(true);
    }

    public void paint(Graphics g) {
        //величина угла сектора
        int angleFromChart;
        //величина угла от начала отсчёта до последнего заполненного сектора
        int prevAngle = 0;
        //числа, соответствующие значению цвета в rgb
        int rColor, gColor, bColor;
        //токенайзер, разбивающий строку по заданному разделителю
        StringTokenizer st = new StringTokenizer(sChart, ", ");
        //считывание всех величин углов из строки
        while(st.hasMoreElements())
        {
            //случайный выбор цвета для сектора
            rColor = (int)(255 * Math.random());
            gColor = (int)(255 * Math.random());
            bColor = (int)(255 * Math.random());
            //задание цвета для сектора
            g.setColor(new Color(rColor,
                    gColor, bColor));
            //получение элемента строки, соответствующего величине угла одного сектора
            String angle = (String)st.nextElement();
            //получение величины угла сектора из строки
            angleFromChart = Integer.parseInt(angle) ;
            //создание сектора, начиная от угла последнего заполненного сектора, с величиной считанного угла
            g.fillArc(50, 50, 200, 200,
                    prevAngle,
                    angleFromChart);
            //обновление величины угла от начала отсчёта до последнего заполненного сектора
            prevAngle += angleFromChart;
        }
    }
}

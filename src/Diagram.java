import javax.swing.*;
import java.awt.*;
import java.util.StringTokenizer;

public class Diagram extends JFrame {

    String sChart;

    public Diagram(String sChart) throws HeadlessException {
        this.sChart = sChart;
        setBounds(640, 100,390,360);
        setVisible(true);
    }

    public void paint(Graphics g) {
        int angleFromChart = 0;
        int prevAngle = 0;
        int rColor, gColor, bColor;
        Dimension dimAppWndDimension = getSize();

        g.setColor(Color.white);
        g.fillRect(0, 0,
                dimAppWndDimension.width  - 1,
                dimAppWndDimension.height - 1);
        g.setColor(Color.black);
        g.drawRect(0, 0,
                dimAppWndDimension.width  - 1,
                dimAppWndDimension.height - 1);
        StringTokenizer st =
                new StringTokenizer(sChart, ", ");
        while(st.hasMoreElements())
        {
            rColor = (int)(255 * Math.random());
            gColor = (int)(255 * Math.random());
            bColor = (int)(255 * Math.random());
            g.setColor(new Color(rColor,
                    gColor, bColor));
            String angle =
                    (String)st.nextElement();
            angleFromChart = Integer.parseInt(angle) ;
            g.fillArc(50, 50, 200, 200,
                    prevAngle,
                    angleFromChart);
            prevAngle += angleFromChart;
        }
    }
}

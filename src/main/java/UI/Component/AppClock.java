package UI.Component;

import java.util.Date;
import javax.swing.JLabel;
import java.text.SimpleDateFormat;
import org.jetbrains.annotations.NotNull;

/* Customized clock for BNMOStore Runnable */
public class AppClock implements Runnable {
    private SimpleDateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private JLabel dateLabel;
    private JLabel timeLabel;

    /**
     * Create runnable of clock that provide realtime date and time, customized for BNMOStore app
     * @param dateLabel Label for date (formate)
     * @param timeLabel Label for time
    */
    public AppClock(@NotNull JLabel dateLabel, @NotNull JLabel timeLabel) {
        this.dateFormat = new SimpleDateFormat("EEEEEEEEE, dd MMMMMMMMMM yyyy");
        this.timeFormat = new SimpleDateFormat("HH:mm:ss");

        this.dateLabel = dateLabel;
        this.timeLabel = timeLabel;
    }

    private String getDate() {
        return this.dateFormat.format(new Date(), new StringBuffer(), new java.text.FieldPosition(0)).toString();
    }

    private String getTime() {
        return this.timeFormat.format(new Date(), new StringBuffer(), new java.text.FieldPosition(0)).toString();
    }

    public void run(){
        while(true){
            this.dateLabel.setText(getDate());
            this.timeLabel.setText(getTime());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }        
    }
}

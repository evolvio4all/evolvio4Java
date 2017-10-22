package evolvio.ui.view;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class WindowStarter {
    @Autowired
    public WindowStarter(@Value("${width}") int width, @Value("${height}") int height) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Evolution simulator");
        frame.setVisible(true);
    }
}

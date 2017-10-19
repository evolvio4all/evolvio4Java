package evolvio.render;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

@Service
public class WindowStarter {
    @Autowired
    public WindowStarter(@Value("${width}") int width, @Value("${height}") int height) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

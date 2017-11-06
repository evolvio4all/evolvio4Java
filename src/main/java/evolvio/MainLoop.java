package evolvio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Service
public class MainLoop {
    private static final long SECONDS_TO_NANOS = (long) 1E9;
    private final ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(1);
    private long frameDelayNanos;

    public MainLoop(@Value("${fps}") int fps) {
        requestFps(60);
        requestFps(fps);
        nextFrame();
    }

    public void requestFps(int fps) {
        fps = Math.min(120, fps);
        fps = Math.max(1, fps);
        this.frameDelayNanos = SECONDS_TO_NANOS / fps;
    }

    private void nextFrame() {
        long startFrameTime = System.nanoTime();
        update();
        render();
        long endFrameTime = System.nanoTime();
        long frameTime = endFrameTime - startFrameTime;
        long delay = frameDelayNanos - frameTime;
        long validDelay = Math.max(0, delay);
        executor.schedule(this::nextFrame, validDelay, TimeUnit.NANOSECONDS);
    }

    private void update() {
    }

    private void render() {
    }
}

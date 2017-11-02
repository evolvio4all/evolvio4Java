package evolvio.resources;

import java.io.Closeable;

public interface Resource extends Closeable {
    void bind();
}

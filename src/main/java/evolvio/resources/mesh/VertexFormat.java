package evolvio.resources.mesh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VertexFormat {
    private final List<VertexSubFormat> format;

    public VertexFormat(VertexSubFormat... datums) {
        List<VertexSubFormat> tempFormat = new ArrayList<>();
        for (VertexSubFormat datum : datums) {
            if (!tempFormat.contains(datum)) {
                tempFormat.add(datum);
            }
        }
        this.format = Collections.unmodifiableList(tempFormat);
    }

    public List<VertexSubFormat> getFormat() {
        return format;
    }
}

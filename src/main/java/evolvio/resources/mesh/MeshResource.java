package evolvio.resources.mesh;

import evolvio.resources.Resource;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.List;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public class MeshResource implements Resource {
    private final int vao;
    private final int vertexBuffer;
    private final int indexBuffer;
    private final int vertexSubFormatCount;
    private final int vertexCount;

    public MeshResource(FloatBuffer vertices, VertexFormat vertexFormat, IntBuffer indices) {
        this.vao = createVertexArrayObject();
        GL30.glBindVertexArray(vao);
        this.vertexBuffer = createVertexBuffer(vertices);
        this.indexBuffer = createIndexBuffer(indices);
        this.vertexSubFormatCount = vertexFormat.getFormat().size();
        this.vertexCount = indices.remaining();
        setVertexFormat(vertexFormat);
        GL30.glBindVertexArray(0);
    }

    private int createVertexArrayObject() {
        return GL30.glGenVertexArrays();
    }

    private int createVertexBuffer(FloatBuffer data) {
        int vertexBuffer = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBuffer);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
        return vertexBuffer;
    }

    private int createIndexBuffer(IntBuffer data) {
        int indexBuffer = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
        GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, data, GL15.GL_STATIC_DRAW);
        return indexBuffer;
    }

    private void setVertexFormat(VertexFormat vertexFormat) {
        List<VertexSubFormat> format = vertexFormat.getFormat();
        for (int subFormatIndex = 0; subFormatIndex < format.size(); subFormatIndex++) {
            VertexSubFormat subFormat = format.get(subFormatIndex);
            GL20.glVertexAttribPointer(subFormatIndex, subFormat.getElementCount(), GL11.GL_FLOAT, false, 0, 0);
        }
    }

    public int getVertexCount() {
        return vertexCount;
    }

    @Override
    public void bind() {
        GL30.glBindVertexArray(vao);
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBuffer);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
        for (int i = 0; i < vertexSubFormatCount; i++) {
            GL20.glEnableVertexAttribArray(i);
        }
    }

    @Override
    public void close() throws IOException {
        GL30.glDeleteVertexArrays(vao);
        GL15.glDeleteBuffers(vertexBuffer);
        GL15.glDeleteBuffers(indexBuffer);
    }
}

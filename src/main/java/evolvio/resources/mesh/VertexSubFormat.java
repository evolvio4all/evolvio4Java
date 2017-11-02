package evolvio.resources.mesh;

public enum VertexSubFormat {
    Position2D(2),
    Position3D(3),
    TexCoords(2),
    Normal2D(2),
    Normal3D(3);
    private final int elementCount;

    VertexSubFormat(int elementCount) {
        this.elementCount = elementCount;
    }

    public int getElementCount() {
        return elementCount;
    }
}

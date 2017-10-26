/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvio.models;

/**
 *
 * @author quentin
 */
public class RawModel {

    private final int vaoId;
    private final int iId;
    private final int vertexCount;

    public RawModel(int vaoId, int vertexCount, int iId) {
        this.vaoId = vaoId;
        this.vertexCount = vertexCount;
        this.iId = iId;
    }

    public int getVaoId() {
        return vaoId;
    }

    public int getVertexCount() {
        return vertexCount;
    }

    public int getiId() {
        return iId;
    }

}

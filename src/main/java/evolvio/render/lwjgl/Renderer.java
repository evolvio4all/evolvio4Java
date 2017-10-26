/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvio.render.lwjgl;

import evolvio.entity.Entity;
import evolvio.models.RawModel;
import evolvio.models.TexturedModel;
import evolvio.render.lwjgl.shaders.StaticShader;
import evolvio.textures.Texture;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 *
 * @author quentin
 */
public class Renderer {

    public Renderer() {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
    }

    public void prepare() {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
        GL11.glClearColor(0, 0, 0, 1);
    }

    public void render(Entity entity, StaticShader shader, Camera cam) {
        TexturedModel texModel = entity.getModel();
        RawModel model = texModel.getModel();
        GL11.glEnableClientState(GL11.GL_VERTEX_ARRAY);
        GL30.glBindVertexArray(model.getVaoId());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);

        Texture texture = texModel.getTexture();

        shader.setTextureSampler(0);
        shader.setProjectionMatrix(cam.getProjection());
        shader.setViewMatrix(cam.getView());
        shader.setTransformationMatrix(entity.getTransformationMatrix());
        shader.setTexture(texture);

        texture.bind(0);

        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, model.getiId());
        GL11.glDrawElements(GL11.GL_TRIANGLES, model.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
        GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, 0);
        GL11.glDisableClientState(GL11.GL_VERTEX_ARRAY);
    }
}

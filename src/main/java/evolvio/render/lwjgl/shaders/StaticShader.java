/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvio.render.lwjgl.shaders;

import evolvio.render.lwjgl.Light;
import evolvio.textures.Texture;
import org.joml.Matrix4f;

/**
 *
 * @author Quentin
 */
public class StaticShader extends Shader {

    private static final String SHADER_NAME = "default";

    private int transpormationMatrixLocation;
    private int projectionMatrixLocation;
    private int viewMatrixLocation;
    private int textureSamplerLocation;
    private int lightPositionLocation;
    private int lightColorLocation;
    private int shineDamperLocation;
    private int reflectivityLocation;

    public StaticShader() {
        super(SHADER_NAME);
    }

    @Override
    protected void bindAttributes() {
        bindAttribute(0, "position");
        bindAttribute(1, "textureCoords");
        bindAttribute(2, "normal");
    }

    @Override
    protected void getAllUniformLocations() {
        textureSamplerLocation = getUniformLocation("textureSampler");
        transpormationMatrixLocation = getUniformLocation("transformationMatrix");
        projectionMatrixLocation = getUniformLocation("projectionMatrix");
        viewMatrixLocation = getUniformLocation("viewMatrix");
        lightPositionLocation = getUniformLocation("lightPosition");
        lightColorLocation = getUniformLocation("lightColor");
        shineDamperLocation = getUniformLocation("shineDamper");
        reflectivityLocation = getUniformLocation("reflectivity");
    }

    public void setTextureSampler(int sampler) {
        loadInt(textureSamplerLocation, sampler);
    }

    public void setTransformationMatrix(Matrix4f mat) {
        loadMatrix(transpormationMatrixLocation, mat);
    }

    public void setViewMatrix(Matrix4f mat) {
        loadMatrix(viewMatrixLocation, mat);
    }

    public void setProjectionMatrix(Matrix4f mat) {
        loadMatrix(projectionMatrixLocation, mat);
    }

    public void setLight(Light light) {
        loadVector(lightPositionLocation, light.getPosition());
        loadVector(lightColorLocation, light.getColor());
    }

    public void setTexture(Texture texture) {
        loadFloat(shineDamperLocation, texture.getShineDamper());
        loadFloat(reflectivityLocation, texture.getReflectivity());
    }

}

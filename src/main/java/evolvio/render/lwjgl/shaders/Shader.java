/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvio.render.lwjgl.shaders;

import evolvio.io.FileUtil;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

/**
 *
 * @author Quentin
 */
public abstract class Shader {

    private int programId;
    private int vShaderId;
    private int fShaderId;

    public Shader(String shaderName) {
        try {
            vShaderId = loadShader("./res/shader/" + shaderName + ".vs", GL20.GL_VERTEX_SHADER);
            fShaderId = loadShader("./res/shader/" + shaderName + ".fs", GL20.GL_FRAGMENT_SHADER);

            programId = GL20.glCreateProgram();
            GL20.glAttachShader(programId, vShaderId);
            GL20.glAttachShader(programId, fShaderId);

            bindAttributes();

            GL20.glLinkProgram(programId);
            GL20.glValidateProgram(programId);

            getAllUniformLocations();
        } catch (IOException ex) {
            Logger.getLogger(Shader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void start() {
        GL20.glUseProgram(programId);
    }

    public void stop() {
        GL20.glUseProgram(0);
    }

    public void cleanUp() {
        stop();
        GL20.glDetachShader(programId, vShaderId);
        GL20.glDetachShader(programId, fShaderId);

        GL20.glDeleteShader(vShaderId);
        GL20.glDeleteShader(fShaderId);

        GL20.glDeleteProgram(programId);
    }

    protected abstract void bindAttributes();

    protected void bindAttribute(int attribute, String varName) {
        GL20.glBindAttribLocation(programId, attribute, varName);
    }

    protected abstract void getAllUniformLocations();

    protected int getUniformLocation(String uniformName) {
        return GL20.glGetUniformLocation(programId, uniformName);
    }

    protected void loadFloat(int location, float value) {
        GL20.glUniform1f(location, value);
    }

    protected void loadInt(int location, int value) {
        GL20.glUniform1i(location, value);
    }

    protected void loadVector(int loaction, Vector3f vec) {
        GL20.glUniform3f(loaction, vec.x, vec.y, vec.z);
    }

    protected void loadBoolean(int location, boolean value) {
        float toLoad = value ? 1 : 0;
        GL20.glUniform1f(location, toLoad);
    }

    protected void loadMatrix(int location, Matrix4f value) {
        FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
        value.get(buffer);
        GL20.glUniformMatrix4fv(location, false, buffer);
    }

    private int loadShader(String file, int shaderType) throws IOException {
        int shaderId = GL20.glCreateShader(shaderType);
        String shaderSource;
        shaderSource = FileUtil.readAllLines(file);
        GL20.glShaderSource(shaderId, shaderSource);
        GL20.glCompileShader(shaderId);
        if (GL20.glGetShaderi(shaderId, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE) {
            Logger.getLogger(Shader.class.getName()).log(Level.SEVERE, GL20.glGetShaderInfoLog(shaderId));
            throw new IllegalStateException("Could not compile Shader");
        }
        return shaderId;
    }
}

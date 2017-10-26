/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvio.entity;

import evolvio.models.TexturedModel;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector3fc;

/**
 *
 * @author Quentin
 */
public class Entity {

    private TexturedModel model;

    private final Vector3f position;
    private final Vector3f rotation;
    private float scale;

    public Entity(TexturedModel model, Vector3f position, Vector3f rotation, float scale) {
        this.model = model;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Entity(TexturedModel model) {
        this.model = model;
        this.position = new Vector3f(0);
        this.rotation = new Vector3f(0);
        this.scale = 1;
    }

    public Matrix4f getTransformationMatrix() {
        Matrix4f matrix4f = new Matrix4f()
                .translate(position)
                .scale(scale);
        matrix4f.rotate((float) Math.toRadians(rotation.x), 1, 0, 0);
        matrix4f.rotate((float) Math.toRadians(rotation.y), 0, 1, 0);
        matrix4f.rotate((float) Math.toRadians(rotation.z), 0, 0, 1);
        return matrix4f;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public TexturedModel getModel() {
        return model;
    }

    public void setModel(TexturedModel model) {
        this.model = model;
    }

    public float x() {
        return position.x();
    }

    public float y() {
        return position.y();
    }

    public float z() {
        return position.z();
    }

    public void setPosition(float x, float y, float z) {
        position.set(x, y, z);
    }

    public float rx() {
        return rotation.x();
    }

    public float ry() {
        return rotation.y();
    }

    public float rz() {
        return rotation.z();
    }

    public void setRotation(float rx, float ry, float rz) {
        rotation.set(rx, ry, rz);
    }

    public void rotate(float x, float y, float z) {
        rotation.add(x, y, z);
    }

    public float distance(Vector3fc v) {
        return position.distance(v);
    }

    public float distance(float x, float y, float z) {
        return position.distance(x, y, z);
    }

    public float distanceSquared(Vector3fc v) {
        return position.distanceSquared(v);
    }

    public float distanceSquared(float x, float y, float z) {
        return position.distanceSquared(x, y, z);
    }
}

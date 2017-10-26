/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evolvio.render.lwjgl;

import org.joml.Matrix4f;
import org.joml.Vector3f;

/**
 *
 * @author Quentin
 */
public class Camera {

    private Vector3f position = new Vector3f(0);
    private float pitch;
    private float yaw;
    private float roll;
    private float camspeed = 0.02f;

    private float fov = 70;
    private float nearPlane = 0.1f;
    private float farPlane = 1000;

    private float width;
    private float height;

    private Matrix4f projection;

    public Camera(float width, float height) {
        this.width = width;
        this.height = height;
        projection = new Matrix4f();
        updateProjection();
    }

    public void move(float x, float y, float z) {
        position.add(x * camspeed, y * camspeed, z * camspeed);
    }

    public Matrix4f getView() {
        Matrix4f view = new Matrix4f();
        view.rotate((float) Math.toRadians(pitch), 1, 0, 0);
        view.rotate((float) Math.toRadians(yaw), 0, 1, 0);
        view.rotate((float) Math.toRadians(roll), 0, 0, 1);
        view.translate(position.negate(new Vector3f()));
        return view;
    }

    public float getFarPlane() {
        return farPlane;
    }

    private void updateProjection() {
        float aspect = width / height;
        projection.identity();
        projection.perspective((float) Math.toRadians(fov), aspect, nearPlane, farPlane);
    }

    public void setFov(float fov) {
        this.fov = fov;
        updateProjection();
    }

    public void setNearPlane(float nearPlane) {
        this.nearPlane = nearPlane;
        updateProjection();
    }

    public void setFarPlane(float farPlane) {
        this.farPlane = farPlane;
        updateProjection();
    }

    public void setWidth(float width) {
        this.width = width;
        updateProjection();
    }

    public void setHeight(float height) {
        this.height = height;
        updateProjection();
    }

    public Matrix4f getProjection() {
        return projection;
    }

}

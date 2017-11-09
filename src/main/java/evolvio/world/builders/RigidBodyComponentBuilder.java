package evolvio.world.builders;

import com.bulletphysics.collision.shapes.BoxShape;
import com.bulletphysics.collision.shapes.CapsuleShape;
import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.ConeShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.google.gson.JsonObject;
import evolvio.world.WorldObjectComponent;
import evolvio.world.WorldObjectComponentBuilder;
import evolvio.world.components.RigidBodyComponent;

import javax.vecmath.Vector3f;

public class RigidBodyComponentBuilder implements WorldObjectComponentBuilder {
    @Override
    public WorldObjectComponent build(JsonObject json) {
        float mass = getFloatOrDefault(json, "mass", 1);
        MotionState ms = new DefaultMotionState();
        CollisionShape cs = createShape(json);
        RigidBody rb = new RigidBody(mass, ms, cs);
        return new RigidBodyComponent(rb);
    }

    private CollisionShape createShape(JsonObject json) {
        String collisionShapeName = getStringOrDefault(json, "shape", "sphere");
        switch (collisionShapeName) {
            case "box":
                return createBox(json);
            case "capsule":
                return createCapsule(json);
            case "cone":
                return createCone(json);
            case "sphere":
                return createSphere(json);
            default:
                System.out.println("Unrecognised shape name: " + collisionShapeName);
                return null;
        }
    }

    private CollisionShape createBox(JsonObject json) {
        float width = getFloatOrDefault(json, "width", 1);
        float height = getFloatOrDefault(json, "height", 1);
        float depth = getFloatOrDefault(json, "depth", 1);
        Vector3f extents = new Vector3f(width, height, depth);
        return new BoxShape(extents);
    }

    private CollisionShape createCapsule(JsonObject json) {
        float radius = getFloatOrDefault(json, "radius", 1);
        float height = getFloatOrDefault(json, "height", 1);
        return new CapsuleShape(radius, height);
    }

    private CollisionShape createCone(JsonObject json) {
        float radius = getFloatOrDefault(json, "radius", 1);
        float height = getFloatOrDefault(json, "height", 1);
        return new ConeShape(radius, height);
    }

    private CollisionShape createSphere(JsonObject json) {
        float radius = getFloatOrDefault(json, "radius", 1);
        return new SphereShape(radius);
    }
}

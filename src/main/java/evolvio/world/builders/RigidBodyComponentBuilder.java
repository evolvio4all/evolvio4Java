package evolvio.world.builders;

import com.bulletphysics.collision.shapes.CollisionShape;
import com.bulletphysics.collision.shapes.SphereShape;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.linearmath.DefaultMotionState;
import com.bulletphysics.linearmath.MotionState;
import com.google.gson.JsonObject;
import evolvio.world.WorldObjectComponent;
import evolvio.world.WorldObjectComponentBuilder;
import evolvio.world.components.RigidBodyComponent;

public class RigidBodyComponentBuilder implements WorldObjectComponentBuilder {
    @Override
    public WorldObjectComponent build(JsonObject json) {
        float mass = json.get("mass").getAsFloat();
        MotionState ms = new DefaultMotionState();
        CollisionShape cs = createShape(json);
        RigidBody rb = new RigidBody(mass, ms, cs);
        return new RigidBodyComponent(rb);
    }

    private CollisionShape createShape(JsonObject json) {
        String collisionShapeName = json.get("shape").getAsString();
        switch (collisionShapeName) {
            case "sphere":
                return createSphere(json);
            default:
                System.out.println("Unrecognised shape name: " + collisionShapeName);
                return null;
        }
    }

    private CollisionShape createSphere(JsonObject json) {
        float radius = json.get("radius").getAsFloat();
        return new SphereShape(radius);
    }
}

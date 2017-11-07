package evolvio.world;

import com.bulletphysics.collision.broadphase.BroadphaseInterface;
import com.bulletphysics.collision.broadphase.Dispatcher;
import com.bulletphysics.collision.broadphase.SimpleBroadphase;
import com.bulletphysics.collision.dispatch.CollisionConfiguration;
import com.bulletphysics.collision.dispatch.CollisionDispatcher;
import com.bulletphysics.collision.dispatch.DefaultCollisionConfiguration;
import com.bulletphysics.dynamics.DynamicsWorld;
import com.bulletphysics.dynamics.RigidBody;
import com.bulletphysics.dynamics.SimpleDynamicsWorld;
import com.bulletphysics.dynamics.constraintsolver.ConstraintSolver;
import com.bulletphysics.dynamics.constraintsolver.SequentialImpulseConstraintSolver;
import evolvio.world.components.RigidBodyComponent;
import org.springframework.stereotype.Component;

@Component
public class World {
    private final DynamicsWorld dynamicsWorld;

    public World() {
        CollisionConfiguration collisionConfiguration = new DefaultCollisionConfiguration();
        Dispatcher dispatcher = new CollisionDispatcher(collisionConfiguration);
        BroadphaseInterface pairCache = new SimpleBroadphase();
        ConstraintSolver constraintSolver = new SequentialImpulseConstraintSolver();
        this.dynamicsWorld = new SimpleDynamicsWorld(dispatcher, pairCache, constraintSolver, collisionConfiguration);
    }

    public void addObject(WorldObject object) {
        RigidBody rigidBody = getRigidBody(object);
        if (rigidBody != null) {
            dynamicsWorld.addRigidBody(rigidBody);
        }
    }

    public void remove(WorldObject object) {
        RigidBody rigidBody = getRigidBody(object);
        if (rigidBody != null) {
            dynamicsWorld.removeRigidBody(rigidBody);
        }
    }

    public void update(float timestep) {
        dynamicsWorld.stepSimulation(timestep);
    }

    private RigidBody getRigidBody(WorldObject object) {
        RigidBodyComponent rigidBodyComponent = object.getComponent(RigidBodyComponent.class);
        if (rigidBodyComponent != null) {
            return rigidBodyComponent.getRigidBody();
        }
        return null;
    }
}

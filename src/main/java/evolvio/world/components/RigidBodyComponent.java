package evolvio.world.components;

import com.bulletphysics.dynamics.RigidBody;
import evolvio.world.WorldObjectComponent;

public class RigidBodyComponent implements WorldObjectComponent {
    private final RigidBody rigidBody;

    public RigidBodyComponent(RigidBody rigidBody) {
        this.rigidBody = rigidBody;
    }

    public RigidBody getRigidBody() {
        return rigidBody;
    }
}

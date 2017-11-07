package evolvio.world;

import com.google.gson.JsonObject;
import evolvio.world.builders.RigidBodyComponentBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WorldObjectComponentLibrary {
    private final Map<String, WorldObjectComponentBuilder> namedComponentBuilders = new HashMap<>();

    public WorldObjectComponentLibrary() {
        namedComponentBuilders.put("rb", new RigidBodyComponentBuilder());
    }

    public WorldObjectComponent create(JsonObject json) {
        String name = json.get("name").getAsString();
        if (name == null) {
            System.out.println("A name is required");
            return null;
        }
        WorldObjectComponentBuilder builder = namedComponentBuilders.get(name);
        if (builder == null) {
            System.out.println("Name: " + name + " is not recognised");
            return null;
        }
        return builder.build(json);
    }
}

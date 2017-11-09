package evolvio.world;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import evolvio.world.builders.RigidBodyComponentBuilder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class WorldObjectComponentLibrary {
    private final Map<String, WorldObjectComponentBuilder> namedComponentBuilders = new HashMap<>();

    public WorldObjectComponentLibrary() {
        namedComponentBuilders.put("RigidBody", new RigidBodyComponentBuilder());
    }

    public WorldObjectComponent create(JsonObject json) {
        JsonElement nameElement = json.get("name");
        if (nameElement == null) {
            System.out.println("A name is required");
            return null;
        }
        String name = nameElement.getAsString();
        WorldObjectComponentBuilder builder = namedComponentBuilders.get(name);
        if (builder == null) {
            System.out.println("Name: " + name + " is not recognised");
            return null;
        }
        return builder.build(json);
    }
}

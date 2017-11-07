package evolvio.world;

import com.google.gson.JsonObject;

public interface WorldObjectComponentBuilder {
    WorldObjectComponent build(JsonObject json);
}

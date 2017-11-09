package evolvio.world;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface WorldObjectComponentBuilder {
    WorldObjectComponent build(JsonObject json);

    default boolean getBooleanOrDefault(JsonObject json, String name, boolean defaultValue) {
        JsonElement element = json.get(name);
        return (element == null) ? defaultValue : element.getAsBoolean();
    }

    default char getCharOrDefault(JsonObject json, String name, char defaultValue) {
        JsonElement element = json.get(name);
        return (element == null) ? defaultValue : element.getAsCharacter();
    }

    default byte getByteOrDefault(JsonObject json, String name, byte defaultValue) {
        JsonElement element = json.get(name);
        return (element == null) ? defaultValue : element.getAsByte();
    }

    default short getShortOrDefault(JsonObject json, String name, short defaultValue) {
        JsonElement element = json.get(name);
        return (element == null) ? defaultValue : element.getAsShort();
    }

    default int getIntOrDefault(JsonObject json, String name, int defaultValue) {
        JsonElement element = json.get(name);
        return (element == null) ? defaultValue : element.getAsInt();
    }

    default long getLongOrDefault(JsonObject json, String name, long defaultValue) {
        JsonElement element = json.get(name);
        return (element == null) ? defaultValue : element.getAsLong();
    }

    default float getFloatOrDefault(JsonObject json, String name, float defaultValue) {
        JsonElement element = json.get(name);
        return (element == null) ? defaultValue : element.getAsFloat();
    }

    default double getDoubleOrDefault(JsonObject json, String name, double defaultValue) {
        JsonElement element = json.get(name);
        return (element == null) ? defaultValue : element.getAsDouble();
    }

    default String getStringOrDefault(JsonObject json, String name, String defaultValue) {
        JsonElement element = json.get(name);
        return (element == null) ? defaultValue : element.getAsString();
    }
}

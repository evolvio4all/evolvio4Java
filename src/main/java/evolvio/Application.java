package evolvio;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);
    }

    @Autowired
    public Application(@Value("${value}") String value) {
        System.out.println(value);
        ValueClass v = new Gson().fromJson(value, ValueClass.class);
        System.out.println(v);
        System.out.println(v.comps[0]);
        System.out.println(v.comps[0].name);
        System.out.println(v.comps[0].key);
        System.out.println(v.comps[0].key2);
    }

    private static class ValueClass {
        private Comp[] comps;
    }

    private static class Comp {
        private String name;
        private String key;
        private String key2;
    }
}

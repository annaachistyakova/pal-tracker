package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.TreeMap;

@RestController
public class EnvController {

    private String PORT;
    private String MEMORY_LIMIT;
    private String CF_INSTANCE_INDEX;
    private String CF_INSTANCE_ADDR;

    public EnvController(@Value("${PORT:NOT SET}") String port,
                         @Value("${MEMORY_LIMIT:NOT SET}") String memory_limit,
                         @Value("${CF_INSTANCE_INDEX:NOT SET}") String cf_ii,
                         @Value("${CF_INSTANCE_ADDR:NOT SET}") String cf_ia){
        this.PORT = port;
        this.MEMORY_LIMIT = memory_limit;
        this.CF_INSTANCE_INDEX = cf_ii;
        this.CF_INSTANCE_ADDR = cf_ia;
    }


    @GetMapping("/env")
    public Map<String, String> getEnv() {
        Map<String, String> temp = new TreeMap<String, String>();
        temp.put("PORT", PORT);
        temp.put("MEMORY_LIMIT", MEMORY_LIMIT);
        temp.put("CF_INSTANCE_INDEX", CF_INSTANCE_INDEX);
        temp.put("CF_INSTANCE_ADDR", CF_INSTANCE_ADDR);

        return temp;
    }
}

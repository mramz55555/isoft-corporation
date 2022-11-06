package com.isoft.auditings;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AppInfo implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        Map<String, String> info = new HashMap<>();
        info.put("Name", "Isoft co");
        info.put("Description", "Isoft corporation website");
        info.put("Version", "0.0.1-SNAPSHOT");
        info.put("Email", "info@isoft@gmail.com");
        info.put("mobile", "(+98)211234414");
        builder.withDetail("app info", info);
    }
}

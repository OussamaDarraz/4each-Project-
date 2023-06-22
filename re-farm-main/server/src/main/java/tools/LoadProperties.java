package tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {
    private Properties props;

    public LoadProperties(String file) {
        try {
            props = new Properties();
            InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(file);
            props.load(in);
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getProp(String propName) {
        return props.getProperty(propName);
    }
}

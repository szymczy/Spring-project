package pl.ue.project.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:datasource.json")
@ConfigurationProperties
public class DatasourceProperties {
    private String url;
    private String driverClassName;
    private String databaseUser;
    private String password;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url.replace("\"", "")
                .replace(",", "");
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName.replace("\"", "")
                .replace(",", "");
    }

    public String getDatabaseUser() {
        return databaseUser;
    }

    public void setDatabaseUser(String databaseUser) {
        this.databaseUser = databaseUser.replace("\"", "")
                .replace(",", "");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password.replace("\"", "")
                .replace(",", "");
    }
}

package pl.ue.project.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DatasourceConfiguration {
    private final DatasourceProperties datasourceProperties;

    @Bean
    public DataSource datasource() {
        return DataSourceBuilder.create()
                .url(datasourceProperties.getUrl())
                .driverClassName(datasourceProperties.getDriverClassName())
                .username(datasourceProperties.getDatabaseUser())
                .password(datasourceProperties.getPassword())
                .build();
    }
}

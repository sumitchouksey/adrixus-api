package adrixus.com.datasource.config.util;


import com.nidavellir.book.configurations.hibernate.DataSourceConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "data-sources.platform")
@RefreshScope
public class DataSourceConfig extends DataSourceConfiguration {
}

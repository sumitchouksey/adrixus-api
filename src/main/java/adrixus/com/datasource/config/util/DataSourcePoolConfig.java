package adrixus.com.datasource.config.util;


import com.nidavellir.book.configurations.hibernate.DatasourcePoolConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

@Component
@RefreshScope
@ConfigurationProperties(prefix = "data-sources.platform.pool-configuration")
public class DataSourcePoolConfig extends DatasourcePoolConfiguration {
}

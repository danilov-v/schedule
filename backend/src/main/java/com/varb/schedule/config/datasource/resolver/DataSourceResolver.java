package com.varb.schedule.config.datasource.resolver;

import com.varb.schedule.config.datasource.resolver.listener.DataSourceReader;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataSourceResolver {
    private List<DataSourceReader> readers = new ArrayList<>();
    private List<String> checkedPropNames = new ArrayList<>();

    private DataSourceResolver(){}

    public static DataSourceResolverResult use(DataSourceReader dataSourceReader) {
        DataSourceResolver dataSourceResolver = new DataSourceResolver();
        dataSourceResolver.addReader(dataSourceReader);
        return dataSourceResolver.new DataSourceResolverResult();
    }

    private void addReader(DataSourceReader dataSourceReader) {
        readers.add(dataSourceReader);
    }

    public class DataSourceResolverResult {
        public DataSourceResolverResult ifEmptyThen(DataSourceReader dataSourceReader) {
            addReader(dataSourceReader);
            return this;
        }

        public String getResult(ConfigurableEnvironment environment) {
            assert !readers.isEmpty();

            for (DataSourceReader reader : readers) {
                reader.setEnvironment(environment);

                Optional<String> prop = reader.getPathToDbFromProp();
                checkedPropNames.addAll(reader.getCheckedPropNames());
                if (prop.isPresent()) {
                    return reader.formatJdbcUrl(prop.get());
                }
            }

            throw new IllegalStateException("Не найден путь к файлу базы данных в свойствах [\n"
                    + String.join(",", checkedPropNames)
                    + "\n]");
        }
    }


}

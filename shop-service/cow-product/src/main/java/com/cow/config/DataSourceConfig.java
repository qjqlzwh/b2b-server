package com.cow.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.zaxxer.hikari.HikariDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import io.seata.rm.datasource.xa.DataSourceProxyXA;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

//    @Autowired
//    DataSourceProperties dataSourceProperties;
//
//    @Autowired
//    DataSource dataSource;

    @Bean
    @Primary
    public DataSource dataSource(DataSourceProperties dataSourceProperties) {

        HikariDataSource dataSource = dataSourceProperties.initializeDataSourceBuilder().type(HikariDataSource.class).build();
        if (StringUtils.hasText(dataSourceProperties.getName())) {
            dataSource.setPoolName(dataSourceProperties.getName());
        }

        // DataSourceProxy for AT mode
         return new DataSourceProxy(dataSource);

        // DataSourceProxyXA for XA mode
//        return new DataSourceProxyXA(dataSource);
    }

//    @Bean
//    public SqlSessionFactoryBean sqlSessionFactoryBean(){
//        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
//        sqlSessionFactoryBean.setDataSource(dataSourceProxy());
//        return sqlSessionFactoryBean;
//    }
//
//    @Bean
//    @ConditionalOnBean(DataSource.class)
//    public DataSource dataSourceProxy() {
//        return new DataSourceProxy(dataSource);
//    }

//    @Bean
//    @ConfigurationProperties(prefix = "spring.datasource")
//    public DruidDataSource druidDataSource() {
//        return new DruidDataSource();
//    }
//
//    /**
//     * 需要将 DataSourceProxy 设置为主数据源，否则事务无法回滚
//     *
//     * @param druidDataSource The DruidDataSource
//     * @return The default datasource
//     */
//    @Primary
//    @Bean("dataSource")
//    public DataSource dataSource(DruidDataSource druidDataSource) {
//        return new DataSourceProxy(druidDataSource);
//    }
}

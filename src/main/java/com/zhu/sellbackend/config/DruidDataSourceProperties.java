package com.zhu.sellbackend.config;

import lombok.Data;

import java.util.Properties;

/**
 * @Author: QianQianQian
 * @Date: 2019/9/29 下午1:46
 * @Description:
 */
@Data
public class DruidDataSourceProperties {
    private Boolean testWhileIdle = true;
    private Boolean testOnBorrow;
    private String validationQuery = "SELECT 1";
    private Boolean useGlobalDataSourceStat;
    private String filters;
    private Long timeBetweenLogStatsMillis;
    private Integer maxSize;
    private Boolean clearFiltersEnable;
    private Boolean resetStatEnable;
    private Integer notFullTimeoutRetryCount;
    private Integer maxWaitThreadCount;
    private Boolean failFast;
    private Boolean phyTimeoutMillis;
    private Long minEvictableIdleTimeMillis = 300000L;
    private Long maxEvictableIdleTimeMillis;
    private Integer initialSize = 5;
    private Integer minIdle = 5;
    private Integer maxActive = 20;
    private Long maxWait = 60000L;
    private Long timeBetweenEvictionRunsMillis = 60000L;
    private Boolean poolPreparedStatements = true;
    private Integer maxPoolPreparedStatementPerConnectionSize = 20;
    private Properties connectionProperties = new Properties() {{
        put("druid.stat.mergeSql", "true");
        put("druid.stat.slowSqlMillis", "5000");
    }};
    private String driverClassName;
    private String url;
    private String username;
    private String password;


    public Properties buildProperties() {

        Properties prop = new Properties();
        notNullAdd(prop,"url",this.url);
        notNullAdd(prop,"username",this.username);
        notNullAdd(prop,"password",this.password);
        notNullAdd(prop,"driverClassName",this.driverClassName);
        notNullAdd(prop,"initialSize", this.initialSize);
        notNullAdd(prop,"maxActive", this.maxActive);
        notNullAdd(prop,"minIdle",this.minIdle);
        notNullAdd(prop,"maxWait", this.maxWait);
        notNullAdd(prop,"poolPreparedStatements", this.poolPreparedStatements);

        notNullAdd(prop,"maxPoolPreparedStatementPerConnectionSize",this.maxPoolPreparedStatementPerConnectionSize);

        notNullAdd(prop,"validationQuery",this.validationQuery);
//        notNullAdd(prop,"validationQueryTimeout",this.validationQuery);
        notNullAdd(prop,"testOnBorrow",this.testOnBorrow);
//        notNullAdd(prop,"testOnReturn", this.teonr);
        notNullAdd(prop,"testWhileIdle", this.testWhileIdle);
        notNullAdd(prop,"timeBetweenEvictionRunsMillis", this.timeBetweenEvictionRunsMillis);
        notNullAdd(prop,"minEvictableIdleTimeMillis",this.minEvictableIdleTimeMillis);
        notNullAdd(prop,"filters", this.filters);
        return prop;
    }

    private void notNullAdd(Properties properties, String key, Object value) {
        if (value != null) {
            properties.put("druid." + key, value.toString());
        }
    }
}

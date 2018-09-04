package com.wangxin.spring.boot.demo.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.github.pagehelper.PageHelper;

/**
 * @Author:jzwx
 * @Desicription: datasource 配置类
 * @Date:Created in 2018-05-15 9:42
 * @Modified By:
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.wangxin.spring.boot.demo.mapper", sqlSessionTemplateRef = "dynamicSqlSessionTemplate")
public class DynamicDatasourceConfiguration {

    @Autowired
    private PagehelperConfig            pagehelperConfig;

    @Autowired
    private TestOneDataSourcePropConfig testOneDataSourcePropConfig;

    @Autowired
    private TestTwoDataSourcePropConfig testTwoDataSourcePropConfig;

    @Autowired
    private MybatisPropConfig           mybatisPropConfig;

    /**
     * 获取test1数据源
     *
     * @return
     */
    @Bean(name = "testOneDataSource")
    public DataSource testOneDataSource() throws Exception {
        //RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, "spring.datasource");
        Properties props = new Properties();
        props.put("driverClassName", testOneDataSourcePropConfig.getDriverClassName());
        props.put("url", testOneDataSourcePropConfig.getUrl());
        props.put("username", testOneDataSourcePropConfig.getUsername());
        props.put("password", testOneDataSourcePropConfig.getPassword());
        props.put("type", testOneDataSourcePropConfig.getType());

        props.put("initialSize", testOneDataSourcePropConfig.getInitialSize());
        props.put("minIdle", testOneDataSourcePropConfig.getMinIdle());
        props.put("maxActive", testOneDataSourcePropConfig.getMaxActive());

        props.put("maxWait", testOneDataSourcePropConfig.getMaxWait());

        props.put("validationQuery", testOneDataSourcePropConfig.getValidationQuery());
        props.put("testWhileIdle", testOneDataSourcePropConfig.getTestWhileIdle());
        props.put("testOnBorrow", testOneDataSourcePropConfig.getTestOnBorrow());
        props.put("testOnReturn", testOneDataSourcePropConfig.getTestOnReturn());

        //打开PSCache，并且指定每个连接上PSCache的大小
        props.put("poolPreparedStatements",
            testOneDataSourcePropConfig.getPoolPreparedStatements());
        props.put("maxPoolPreparedStatementPerConnectionSize",
            testOneDataSourcePropConfig.getMaxPoolPreparedStatementPerConnectionSize());
        return DruidDataSourceFactory.createDataSource(props);
    }

    /**
     * 获取test2数据源
     *
     * @return
     */
    @Bean(name = "testTwoDataSource")
    public DataSource testTwoDataSource() throws Exception {
        //RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, "spring.datasource");
        Properties props = new Properties();
        props.put("driverClassName", testTwoDataSourcePropConfig.getDriverClassName());
        props.put("url", testTwoDataSourcePropConfig.getUrl());
        props.put("username", testTwoDataSourcePropConfig.getUsername());
        props.put("password", testTwoDataSourcePropConfig.getPassword());
        props.put("type", testTwoDataSourcePropConfig.getType());

        props.put("initialSize", testTwoDataSourcePropConfig.getInitialSize());
        props.put("minIdle", testTwoDataSourcePropConfig.getMinIdle());
        props.put("maxActive", testTwoDataSourcePropConfig.getMaxActive());

        props.put("maxWait", testTwoDataSourcePropConfig.getMaxWait());

        props.put("validationQuery", testTwoDataSourcePropConfig.getValidationQuery());
        props.put("testWhileIdle", testTwoDataSourcePropConfig.getTestWhileIdle());
        props.put("testOnBorrow", testTwoDataSourcePropConfig.getTestOnBorrow());
        props.put("testOnReturn", testTwoDataSourcePropConfig.getTestOnReturn());

        //打开PSCache，并且指定每个连接上PSCache的大小
        props.put("poolPreparedStatements",
            testTwoDataSourcePropConfig.getPoolPreparedStatements());
        props.put("maxPoolPreparedStatementPerConnectionSize",
            testTwoDataSourcePropConfig.getMaxPoolPreparedStatementPerConnectionSize());
        return DruidDataSourceFactory.createDataSource(props);
    }

    /**
     * 动态数据源: 通过AOP在不同数据源之间动态切换
     * @return
     * @throws Exception
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dataSource() throws Exception {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(testOneDataSource());

        //配置多数据源
        Map<Object, Object> dsMap = new HashMap<>();
        dsMap.put("testOneDataSource",testOneDataSource());
        dsMap.put("testTwoDataSource",testTwoDataSource());
        dynamicDataSource.setTargetDataSources(dsMap);
        return dynamicDataSource;
    }

    /**
     * 配置事物管理器
     *
     * @param: dataSource
     * @return PlatformTransactionManager
     * @throws Exception
     */
    @Bean(name = "dynamicTransactionManager")
    public PlatformTransactionManager dynamicTransactionManager(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     * 构建session工厂
     * @param: dataSource
     * @return SqlSessionFactory
     * @throws Exception
     */
    @Bean(name = "dynamicSqlSessionFactory")
    public SqlSessionFactory dynamicSqlSessionFactory(@Qualifier("dynamicDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        //         RelaxedPropertyResolver resolver = new RelaxedPropertyResolver(env, "mybatis");
        //指定基包
        sqlSessionFactoryBean.setTypeAliasesPackage(mybatisPropConfig.getTypeAliasesPackage());
        //指定xml文件位置
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources(mybatisPropConfig.getMapperLocations()));

        //mybatis分页
        sqlSessionFactoryBean.setPlugins(new Interceptor[] { getPageHelper() });
        return sqlSessionFactoryBean.getObject();

    }

    /**
     * 配置session模板
     *
     * @param sqlSessionFactory
     * @return SqlSessionTemplate
     */
    @Bean(name = "dynamicSqlSessionTemplate")
    public SqlSessionTemplate dynamicSqlSessionTemplate(@Qualifier("dynamicSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 构建pageHelper
     *
     * @return
     */
    private PageHelper getPageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties props = new Properties();
        props.setProperty("dialect", pagehelperConfig.getDialect());
        props.setProperty("params", pagehelperConfig.getParams());
        props.setProperty("offsetAsPageNum", pagehelperConfig.getOffsetAsPageNum());
        props.setProperty("rowBoundsWithCount", pagehelperConfig.getRowBoundsWithCount());
        props.setProperty("pageSizeZero", pagehelperConfig.getPageSizeZero());
        props.setProperty("reasonable", pagehelperConfig.getReasonable());
        props.setProperty("supportMethodsArguments", pagehelperConfig.getSupportMethodsArguments());
        props.setProperty("returnPageInfo", pagehelperConfig.getReturnPageInfo());
        pageHelper.setProperties(props);
        return pageHelper;
    }
}

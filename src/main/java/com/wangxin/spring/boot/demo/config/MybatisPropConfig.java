package com.wangxin.spring.boot.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:jzwx
 * @Desicription: Mybatis属性配置
 * @Date:Created in 2018-05-15 9:49
 * @Modified By:
 */
@Configuration
@ConfigurationProperties(prefix = "mybatis")
public class MybatisPropConfig {
    /** dataobjtct包地址 */
    private String typeAliasesPackage;

    /** mapper地址 */
    private String mapperLocations;

    /**
     * Getter method for property <tt>typeAliasesPackage</tt>.
     *
     * @return property value of typeAliasesPackage
     */
    public String getTypeAliasesPackage() {
        return typeAliasesPackage;
    }

    /**
     * Setter method for property <tt>counterType</tt>.
     *
     * @param typeAliasesPackage value to be assigned to property typeAliasesPackage
     */
    public void setTypeAliasesPackage(String typeAliasesPackage) {
        this.typeAliasesPackage = typeAliasesPackage;
    }

    /**
     * Getter method for property <tt>mapperLocations</tt>.
     *
     * @return property value of mapperLocations
     */
    public String getMapperLocations() {
        return mapperLocations;
    }

    /**
     * Setter method for property <tt>counterType</tt>.
     *
     * @param mapperLocations value to be assigned to property mapperLocations
     */
    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }
}

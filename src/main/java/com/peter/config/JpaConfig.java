package com.peter.config;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

/**
 * Created by hbowang on 8/17/16.
 */
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(basePackageClasses = ApplicationMain.class)
public class JpaConfig implements TransactionManagementConfigurer{
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        return null;
    }
}

package com.example.seafood.config;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement //开启事务
@MapperScan("com.example.seafood.mappers")
public class MyBatisConfig {

    //引入分页插件
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        // 创建MyBatis插件链
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 创建分页插件对象
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        // 配置分页插件对象
        // 设置请求的页面大于最大页后操作， true调回到首页，false 继续请求  默认false
        paginationInnerInterceptor.setOverflow(true);
        // 设置最大单页限制数量，默认 500 条，-1 不受限制
        paginationInnerInterceptor.setMaxLimit(1000L);
        // 设置数据库类型，如果有多数据源可以不配具体类型, 否则都建议配上具体的 DbType
        paginationInnerInterceptor.setDbType((DbType.MYSQL));
        // 如果配置多个插件, 切记分页最后添加
        interceptor.addInnerInterceptor(paginationInnerInterceptor);
        return interceptor;
    }
}

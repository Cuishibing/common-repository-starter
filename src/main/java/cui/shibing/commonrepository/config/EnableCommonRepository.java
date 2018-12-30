package cui.shibing.commonrepository.config;

import org.springframework.context.annotation.Import;

import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.iml.JpaCommonRepositoryImpl;

import java.lang.annotation.*;

/**
 * 表明是否启用CommonRepository自动配置功能
 * value值为CommonRepository实现类，默认为cui.shibing.commonrepository.JpaCommonRepositoryImpl
 * 
 * @see cui.shibing.commonrepository.iml.JpaCommonRepositoryImpl
 * */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(CommonRepositoryAutoConfiguration.class)
public @interface EnableCommonRepository {
    Class<? extends CommonRepository> value() default JpaCommonRepositoryImpl.class;
}

package cui.shibing;

import cui.shibing.commonrepository.CommonRepository;
import cui.shibing.commonrepository.CommonRepositoryImpl;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(CommonRepositoryAutoConfiguration.class)
public @interface EnableCommonRepository {
    Class<? extends CommonRepository> value() default CommonRepositoryImpl.class;
}

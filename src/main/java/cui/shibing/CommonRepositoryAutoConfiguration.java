package cui.shibing;

import cui.shibing.commonrepository.CommonRepositoryImpl;
import cui.shibing.commonrepository.CommonRepositoryImpl1;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.autoconfigure.AutoConfigurationPackages;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.util.ClassUtils;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@ConditionalOnBean(EntityManager.class)
@AutoConfigureAfter(HibernateJpaAutoConfiguration.class)
public class CommonRepositoryAutoConfiguration implements BeanFactoryAware, ImportBeanDefinitionRegistrar, EnvironmentAware, ResourceLoaderAware {

    private ResourceLoader resourceLoader;
    private Environment environment;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }


    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(
                false);
        scanner.setEnvironment(this.environment);
        scanner.setResourceLoader(this.resourceLoader);
        scanner.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        List<String> packages = AutoConfigurationPackages.get(this.beanFactory);
        Set<Class<?>> entityClazzes = new HashSet<>();
        packages.forEach(p -> {
            Set<BeanDefinition> candidateComponents = scanner.findCandidateComponents(p);
            candidateComponents.forEach(c -> {
                try {
                    entityClazzes.add(ClassUtils.forName(c.getBeanClassName(), resourceLoader.getClassLoader()));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            });
        });
        entityClazzes.forEach(clazz -> {
            BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(CommonRepositoryImpl1.class);
            beanDefinitionBuilder.addConstructorArgValue(clazz);
//            beanDefinitionBuilder.addConstructorArgReference("entityManagerFactory");
            AbstractBeanDefinition rawBeanDefinition = beanDefinitionBuilder.getRawBeanDefinition();
            rawBeanDefinition.setLazyInit(true);
            registry.registerBeanDefinition(clazz.getSimpleName(), rawBeanDefinition);
        });

    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}

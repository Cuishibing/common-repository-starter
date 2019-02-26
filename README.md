# common-repository-starter
基于Spring JPA的通用Repository自动配置,单表操作都可以通过一个通用的CommonRepository完成,避免写很多重复代码。
目前实现了JPA，MyBatis实现还未完成

对于每一个被@Entity注解注释的类都生成一个对应的通用Repository对象
## 配置
+ pom引入依赖

首先引入spring boot parent依赖,然后引入本项目
````
<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.1.1.RELEASE</version>
</parent>

<dependency>
    <groupId>cui.shibing</groupId>
    <artifactId>common-repository-starter</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
````
+ 启动类配置

给启动类加上@EnableCommonRepository注解,参数为通用Repository实现类,如果不加参数就使用默认的CommonRepositoryImpl
````
@EnableCommonRepository(CustomCommonRepositoryImpl.class)
public class App {
    
}
````
## 使用
````
// 注入通用Repository,注意使用名称注入,名称为对应的实体类名
@Resource(name = "Person")
private CommonRepository<Person,Integer> personCommonRepository;

@GetMapping("persons")
public List<Person> persons() {
    return personCommonRepository.findAll();
}
````
## 扩展
+ 重写默认行为

如果要重写或者修改默认实现则可以在启动类注解的参数中指定自定义的实现类
````
@EnableCommonRepository(CustomCommonRepositoryImpl.class)
````
自定义的类继承默认的实现类然后重写其中的方法即可
+ 增加通用方法

    + 新增通用方法接口继承默认接口,在方法中新增自己的方法
    ````
    @NoRepositoryBean
    public interface CustomCommonRepository<T,ID> extends CommonRepository<T,ID> {
        T echo(T t);
    }
    ````
    + 实现新增的方法
    ````
    public class CustomCommonRepositoryImpl<T, ID> extends CommonRepositoryImpl<T, ID> implements CustomCommonRepository<T, ID> {
        public CustomCommonRepositoryImpl(Class<T> domainClass) {
            super(domainClass);
        }
    
        @Override
        public T echo(T t) {
            return t;
        }
    }
    ````
    + 使用的时候注入自己新定义的接口
    ````
    @Resource(name = "Person")
    private CustomCommonRepository<Person, Integer> equalRedPackageCRepository;
    ````
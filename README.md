# common-repository-starter
基于JPA的通用Repository自动配置,单表操作都可以通过一个通用的CommonRepository完成,避免写很多重复代码
## pom文件配置
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

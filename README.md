# StudentManagePro
这是一个数据库的大作业，正好最近需要复习一下前后端的知识，所以上传到这里，记录自己曾经做过的项目，顺便复习一下


#### 一、总体设计

前端设计采用的是 web 技术。整个项目为 Springboot+VueElement 前后端分离。Springboot 负责实现后端对数据库进行操作，vue 以及 Element 则负责前端页面的展示。后端 Springboot 使用到的技术有：maven、mybatis-plus、lombok、代码生成器等等。前端使用到的工具为 node.js、vue2、element-ui、axios、vue-router、vuex。主要功能如下

1. 实现对表数据的查询、增加、编辑、删除功能。
2. 页面实现分页功能
3. 实现用户登陆退出功能。
4. 使用动态路由实现不同用户登录不同页面功能。如果登录者为超级管理员，那么可以访问所有的页面。如果登陆者为管理员，不能访问只有超级管理员才能访问的管理员管理界面，其它的页面都可以访问。如果登陆者为普通用户则只能访问首页。

#### 二、分析

前端设计可以采用很多方式，如用户界面，APP 或网页等等，这里因为我对网页比较感兴趣，所以就选择了网页的形式进行展示。在实现网页展示的过程中我学习到了许多方法，也在过程中更换掉了许多方法，比如刚开始的 mybatis 框架到后面使用的 mybatisplus 框架，刚开始的 maven 项目到后面的springboot 项目等等，这都让我了解到了 web 技术的更新换代。尽管现在有很多简洁清晰的框架，但这里我想要先说一下最初实践思路的变化，来做一个总结和回顾。

实现对基本表的增删改查操作，那么首先需要通过 java 语言操作数据库中的数据。开始的时候我了解到了 JDBC 这个东西，它是使用 Java 语言操作关系型数据库的一套 API。它的主要思想就是：第一步：编写 Java 代码，第二步：Java 代码将 SQL 发送到 MySQL 服务端，第三步：MySQL 服务端接收到 SQL 语句并执行该 SQL 语句，第四步：将 SQL 语句执行的结果返回给 Java 代码。在学过 JDBC 代码后，发现通过 JDBC 编写的代码分析十分冗长，所以又更换至 Mybatis 优化进行优化：编写 MyBatis 核心配置文件，即 resources 目录下创建mybatis 的配置文件 mybatis-config.xml，以此替换连接信息，解决硬编码问题；编写 SQL映射文件，统一管理 sql 语句，解决硬编码问题。使用过 mybatis 后，我又发现了一个便捷的工具，那就是 mybatisplus，它直接定义了一系列的函数，可以通过这些函数直接调用对应的 sql 语句。

当可以通过 Java 代码成功的访问到数据库时，就出现了一个问题，如何允许应用程序重复使用一个现有的数据库连接，而不是再重新建立一个数据库连接池。这就可以使用数据库连接池进行解决，连接池是在一开始就创建好了一些连接（Connection）对象存储起来。用户需要连接数据库时，不需要自己创建连接，而只需要从连接池中获取一个连接进行使用，使用完毕后再将连接对象归还给连接池，这样就可以起到资源重复使用的作用，也节省了频繁创建连接销毁所花费的时间，从而提升了系统响应的速度。数据库连接池有 Druid。

随后我学习了基于项目对象模型(POM)概念的项目管理工具：Maven，它可以通过一小段描述信息来管理项目的构建、报告和文档。它不仅提供了一套标准化的项目结构与标准化的构建流程（编译，测试，打包等），还提供了一套依赖管理机制。依赖管理可以十分方便地管理项目所依赖的第三方资源（jar 包、插件），大大简化了下载对应的依赖包（jar 包）的流程。

在能够通过 java 代码操作数据库时，下一步应该能够将数据传送到网页上。这样就学习到了 B/S 架构：Browser/Server，浏览器/服务器 架构模式，它的特点是，客户端只需要浏览器，应用程序的逻辑和数据都存储在服务器端。浏览器只需要请求服务器，获取 Web资源，服务器把 Web 资源发送给浏览器即可。 整个 Web 的访问过程如下所示：

1. 浏览器发送一个请求到服务端，去请求所需要的相关资源;
2. 资源分为动态资源和静态资源,动态资源可以是使用 Java 代码按照 Servlet 和 JSP的规范编写的内容
3. 在 Java 代码可以进行业务处理也可以从数据库中读取数据;
4. 拿到数据后，把数据交给 HTML 页面进行展示,再结合 CSS 和 JavaScript 展示效果更好;
5. 服务端将静态资源响应给浏览器;
6. 浏览器将这些资源进行解析;
7. 解析后将效果展示在浏览器，用户就可以看到最终的结果。

浏览器发送请求给服务器，服务器响应数据给浏览器，这整个过程都需要遵守一定的规则，即 HTTP 协议。Web 服务器（Tomcat 服务器）则负责解析 HTTP 协议，解析请求数据，并发送响应数据。这样的话就可以实现整个网页流程。

在了解这些基础之上，我学习了另一种更便捷的方式去搭建 web 项目那就是Springboot+VueElement。springboot 负责实现后端的数据库操作，vue 以及 Element 则负责前端页面的展示。

#### 三、Springboot 后端设计

Springboot 是在 Spring 的基础上提供的一套全新的开源框架，其目的是为了简化Spring 应用的搭建和开发过程。就像 maven 使用依赖的方法整合了所有的 jar 包，Springboot 类似整合了所有的框架。后端代码编写主要包括：

1. 实体类开发（domain）————使用 Lombok 快速制作实体类
2. 数据层开发(dao)————整合 MyBatisPlus，制作数据层测试
3. 服务层开发(service)————基于 MyBatisPlus 制作业务层测试类
4. 控制层开发(controller)开发————功能如下： 列表、新增、修改、删除、分页、查询

下面是我将以表 dept（major、sp 等与 dept 表类似）为例讲述后端开发

##### 1、实体类开发(domain)

表 dept 的结构为

```sql
CREATE TABLE `dept` (
`Deptno` CHAR(6) NOT NULL,
`Deptname` VARCHAR(20) NOT NULL,
`Deptnum` INT DEFAULT NULL,
`Deptlc` VARCHAR(20) DEFAULT NULL,
`DeptA` VARCHAR(20) DEFAULT NULL,
PRIMARY KEY (`Deptno`),
UNIQUE KEY `Deptname` (`Deptname`)
) ENGINE=INNODB DEFAULT CHARSET=utf8mb3;
```

根据上述的表结构，可以制作对应的实体类。实体类的开发可以自动通过工具手工生成 get/set 方法，然后覆盖 toString()方法，方便调试，等等。不过由于我们要创建 dept、major、class、student、sp、party等一系列表的实体类，这一套操作过于繁琐，所以这里采用一个小工具 lombok 来简化开发。Lombok 是一个 Java 类库，提供了一组注解，简化 POJO 实体类开发，Springboot目前默认集成了 lombok 技术，并提供了对应的版本控制，所以只需要提供对应的坐标即可，在 pom.xml 中添加 lombok 的坐标。

```xml
<dependency>
	<groupId>org.projectlombok</groupId>
	<artifactId>lombok</artifactId>
	<optional>true</optional>
</dependency>
```

使用 lombok 可以通过一个注解@Data 完成一个实体类对应的 getter，setter，toString，equals，hashCode 等操作的快速添加，注解@AllArgsConstructor 实现所有有参构造注解，NoArgsConstructor 实现所有无参构造。如表 dept 对于的实体类 Dept的代码如下：

```java
package com.gpl.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * <p>
 * 
 * </p>
 *
 * @author gpl
 * @since 2023-01-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="Dept对象", description="")
public class Dept implements Serializable {
    private static final long serialVersionUID = 1L;
    @TableId("Deptno")
    private String deptno;
    @TableField("Deptname")
    private String deptname;
    @TableField("Deptnum")
    private Integer deptnum;
    @TableField("Deptlc")
    private String deptlc;
    @TableField("DeptA")
    private String depta;
}
```

运用这种方法我们可以快速创建出dept、major、class、student、sp、party这一系列表的实体类Dept、Major、Classes、Student、Sp、Party。这些实体类均在domain包中。

##### 2、数据层开发(dao)

数据层开发采用了MyBatisPlus框架，MyBaitsPlus是Mybaits的增强工具，在MyBaits的基础上只做增强，不做改变。为简化开发、提高效率而生。使用MyBaitsPlus需要在pom.xml中导入对应的starter和generator，我导入的如下所示

```xml
<dependency>
	<groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-boot-starter</artifactId>
    <version>3.4.1</version>
</dependency>
<dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-generator</artifactId>
    <version>3.4.1</version>
</dependency>
```

同时需要在application.yml (此处我删除了默认的application.properties文件，更改成了application.yml 文件)，配置文件中添加端口信息以及数据库的相关配置，默认端口号为80，链接的是本机的my1数据库资源，用户名为root，密码为123456，如下所示：

```yaml
erver:
  port: 80

#数据库数据源
spring:
  datasource:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://localhost:3306/my1?serverTimezone=UTC
      username: root
      password: 123456
```

除此之外，我还在application.yml 文件中设置了配置相关的日志信息。

```yaml
#日志
logging:
  file:
    name: log/log.log
  level:
    root: info
    gpl: debug
```

完成上述操作时，使用MP的标准通用接口BaseMapper加速开发，在创建的DeptMapper接口上指定@Mapper，代码中使用@Mapper这个注解表示这是一个mybatis的mapper类。表dept对于的mapper接口DeptMapper如下所示

```java
ackage com.gpl.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.gpl.domain.Dept;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author gpl
 * @since 2023-01-25
 */
@Mapper
public interface DeptMapper extends BaseMapper<Dept> {
//    IPage pageC(Page<Dept> page);
    IPage pageCC(IPage<Dept> page, @Param(Constants.WRAPPER) Wrapper wrapper);
}
```

DeptMapper中IPage pageCC(IPage<Dept> page, @Param(Constants.WRAPPER) Wrapper wrapper)是自定义的一个分页查询方法，需要在对应的xml文件中写出相应的sql查询语句对应xml文件中的代码如下，其中id为pageCC的select查询语句即为pageCC 对应sql语句。

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.gpl.mapper.DeptMapper">

    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="com.gpl.domain.Dept">
        <id column="Deptno" property="deptno" />
        <result column="Deptname" property="deptname" />
        <result column="Deptnum" property="deptnum" />
        <result column="Deptlc" property="deptlc" />
        <result column="DeptA" property="depta" />
    </resultMap>
    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        Deptno, Deptname, Deptnum, Deptlc, DeptA
    </sql>
    <select id="pageCC" resultType="com.gpl.domain.Dept">
        select * from dept ${ew.customSqlSegment}
    </select>
</mapper>
```

##### 3. 服务层开发(service)

下面是service层的开发，使用标准通用接口IService加速开发

```java
package com.gpl.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gpl.domain.Dept;
import com.baomidou.mybatisplus.extension.service.IService;
/**
 * <p>
 *  服务类
 * </p>
 *
 * @author gpl
 * @since 2023-01-25
 */
public interface DeptService extends IService<Dept> {
    IPage pageCC(IPage<Dept> page, Wrapper wrapper);
}
```

编写接口相应的实现类，使用@Service标注业务层组件。pageCC方法会调用DeptMapper中相应的pageCC实现sql查询。

```java
package com.gpl.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.gpl.domain.Dept;
import com.gpl.mapper.DeptMapper;
import com.gpl.service.DeptService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author gpl
 * @since 2023-01-25
 */
@Service
public class DeptServiceImpl extends ServiceImpl<DeptMapper, Dept> implements DeptService {
    @Resource
    private DeptMapper deptMapper;
    @Override
    public IPage pageCC(IPage<Dept> page, Wrapper wrapper) {
        return deptMapper.pageCC(page,wrapper);
    }
}
```

##### 4. 控制层开发(Controller)

下面是Controller层的开发，使用@RestController标注控制层组件，使用@RequestMapping("/dept")标记访问路径。

```java
import com.gpl.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author gpl
 * @since 2023-01-25
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;
    @GetMapping("/list")
    public List<Dept> list(){
        return deptService.list();
    }
    //新增
    @PostMapping("/save")
    public Result save(@RequestBody Dept dept){
        return deptService.save(dept)? Result.suc():Result.fail();
    }
    //更新
    @PostMapping("/update")
    public Result update(@RequestBody Dept dept){
        return deptService.updateById(dept)?Result.suc():Result.fail();
    }
    //删除
    @GetMapping("/del")
    public Result del(@RequestParam String deptno){
        return deptService.removeById(deptno)?Result.suc():Result.fail();
    }
    @PostMapping("/listPage")
    public Result listPage(@RequestBody QueryPageParam query){
        HashMap param = query.getParam();
        String Deptname = (String)param.get("deptname");
        Page<Dept> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<Dept> lambdaQueryWrapper = new LambdaQueryWrapper();
        if(StringUtils.isNotBlank(Deptname) && !"null".equals(Deptname)){
            lambdaQueryWrapper.like(Dept::getDeptname,Deptname);
        }
        IPage result = deptService.pageCC(page,lambdaQueryWrapper);
        System.out.println("total=="+result.getTotal());
        return Result.suc(result.getRecords(),result.getTotal());
    }
}
```

控制层中的listPage为分页查询代码，调用的是service曾的PageCC代码。除此之外应该注意的是必须添加相应的分页拦截器以及编写相应的分页类，分页拦截器放置在了common包下，具体代码如下

```java
package com.gpl.common;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class MybatisPlusConfig {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
```

分页类也放置在了common包下

```java
package com.gpl.common;

import lombok.Data;
import java.util.HashMap;
@Data
public class QueryPageParam {
    //默认
    private static int PAGE_SIZE=5;
    private static int PAGE_NUM=1;
    private int pageSize=PAGE_SIZE;
    private int pageNum=PAGE_NUM;
    //封装的数据
    private HashMap param = new HashMap();
}
```

##### 5、返给前端数据的封装

其中，Result为返回体信息，即返给前端数据的封装，我放置在了common包下，具体代码如下

```java
package com.gpl.common;

import lombok.Data;
//import sun.jvm.hotspot.oops.ObjectHeap;
@Data
public class Result {
    private int code;//编码 200/400
    private String msg;//成功/失败
    private Long total;//总记录数
    private Object data;//数据
    public static Result fail(){
        return result(400,"失败",0L,null);
    }
    public static Result suc(){
        return result(200,"成功",0L,null);
    }
    public static Result suc(Object data){
        return result(200,"成功",0L,data);
    }
    public static Result suc(Object data,Long total){
        return result(200,"成功",total,data);
    }
    private static Result result(int code,String msg,Long total,Object data){
        Result res = new Result();
        res.setData(data);
        res.setMsg(msg);
        res.setCode(code);
        res.setTotal(total);
        return res;
    }
}
```

##### 6、代码生成器

 上述表的建立十分的复杂冗长，为了进一步简化操作，可以使用代码生成器生成相应的代码。首先加入依赖

```xml
       <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-generator</artifactId>
            <version>3.4.1</version>
        </dependency>
        <dependency>
            <groupId>org.freemarker</groupId>
            <artifactId>freemarker</artifactId>
            <version>2.3.30</version>
        </dependency>
        <dependency>
            <groupId>com.spring4all</groupId>
            <artifactId>spring-boot-starter-swagger</artifactId>
            <version>1.5.1.RELEASE</version>
        </dependency>

```

再编写相应的获取⽣成器代码，如下所示。

```java
package com.gpl.common;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CodeGenerator {
    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输⼊" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输⼊正确的" + tip + "！");
    }
    /**
     * 操作步骤：
     * 1.修改数据源包括地址密码信息，对应代码标记：⼀、 下同
     * 2.模块配置，可以修改包名
     * 3.修改模板（这步可忽略）
     * @param args
     */
    public static void main(String[] args) {
        // 代码⽣成器
        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir")+"/springbootPro";
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("gpl");
        gc.setOpen(false);
        gc.setSwagger2(true); //实体属性 Swagger2 注解
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setServiceName("%sService");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        // ⼀、修改数据源
        dsc.setUrl("jdbc:mysql://localhost:3306/my1?useUnicode=true&characterEncoding=UTF8&useSSL=false");
                // dsc.setSchemaName("public");
                dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);
        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        // ⼆、模块配置
        pc.setParent("com.gpl")
                .setEntity("domain")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setController("controller");
        mpg.setPackageInfo(pc);
        // ⾃定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };
        // 如果模板引擎是 freemarker
        String templatePath = "templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";
        // ⾃定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // ⾃定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // ⾃定义输出⽂件名
                return projectPath + "/src/main/resources/mapper/" + pc.getModuleName()
                        + "/" + tableInfo.getEntityName() + "Mapper" +
                        StringPool.DOT_XML;
            }
        });
        /*
         cfg.setFileCreate(new IFileCreate() {
         @Override
         public boolean isCreate(ConfigBuilder configBuilder, FileType fileType,
        String filePath) {
         // 判断⾃定义⽂件夹是否需要创建
         checkDir("调⽤默认⽅法创建的⽬录，⾃定义⽬录⽤");
         if (fileType == FileType.MAPPER) {
         // 已经⽣成 mapper ⽂件判断存在，不想重新⽣成返回 false
         return !new File(filePath).exists();
         }
         // 允许⽣成模板⽂件
         return true;
         }
         });
         */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();
        // 配置⾃定义输出模板
        //指定⾃定义模板路径，注意不要带上.ftl/.vm, 会根据使⽤的模板引擎⾃动识别
        // 三、修改模板
        /*templateConfig.setEntity("templates/entity2.java");
        templateConfig.setService("templates/service2.java");
        templateConfig.setController("templates/controller2.java");
        templateConfig.setMapper("templates/mapper2.java");
        templateConfig.setServiceImpl("templates/serviceimpl2.java");*/
        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // strategy.setSuperEntityClass("你⾃⼰的⽗类实体,没有就不⽤设置!");
        //strategy.setSuperEntityClass("BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共⽗类
        //strategy.setSuperControllerClass("BaseController");
        // strategy.setSuperControllerClass("你⾃⼰的⽗类控制器,没有就不⽤设置!");
        // 写于⽗类中的公共字段
        // strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英⽂逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        //strategy.setTablePrefix(pc.getModuleName() + "_");
        // 忽略表前缀tb_,⽐如说tb_user,直接映射成user对象
        // 四、注意是否要去掉表前缀
        //strategy.setTablePrefix("tb_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}
```

运行代码生成器，在控制台上输入相应的表名，即可迅速快捷的编辑出数据库相应表对应的实体类层、数据层、服务层与控制层的基本代码。



#### 三、Vue+Element前端页面设计

##### 1、Element-ui

首先需要导⼊Element-ui，Element-ui安装的命令如下：

```
npm i element-ui -S
```

同时需要在mainjs全局引⼊

```
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
```

注意，我在安装Element-ui的时候产生了如下的报错:

```
npm ERR! code ERESOLVE
npm ERR! ERESOLVE unable to resolve dependency tree
npm ERR!
npm ERR! While resolving: vueweb@0.1.0
npm ERR! Found: vue@3.2.45
npm ERR! node_modules/vue
npm ERR!   vue@"^3.2.13" from the root project
npm ERR!
npm ERR! Could not resolve dependency:
npm ERR! peer vue@"^2.5.17" from element-ui@2.15.12
npm ERR! node_modules/element-ui
npm ERR!   element-ui@"*" from the root project
npm ERR!
npm ERR! Fix the upstream dependency conflict, or retry
npm ERR! this command with --force, or --legacy-peer-deps
npm ERR! to accept an incorrect (and potentially broken) dependency resolution.
npm ERR!
npm ERR! See C:\Users\Lulu\AppData\Local\npm-cache\eresolve-report.txt for a full report.

npm ERR! A complete log of this run can be found in:
npm ERR!     C:\Users\Lulu\AppData\Local\npm-cache\_logs\2023-01-24T14_39_52_798Z-debug-0.log
```

这是由于ERESOLVE无法解析依赖关系树，是npm版本不同造成的，解决方案是用--legacy-peer-deps忽略低版本问题，安装element-ui 命令改为： npm i element-ui -S --legacy-peer-deps即可

##### 2、安装axios进行跨域处理

安装命令如下

```
npm install axios --save
```

同时也需要在main.js全局引⼊axios

```
import axios from ‘axios’;
Vue.prototype.$axios =axios
```

同时后端也需要相应的配置类，即common包下的CorsConfig类

```java
package com.wms.common;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class CorsConfig implements WebMvcConfigurer {
 @Override
 public void addCorsMappings(CorsRegistry registry) {
 registry.addMapping("/**")
 //是否发送Cookie
 .allowCredentials(true)
 //放⾏哪些原始域
 .allowedOriginPatterns("*")
 .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"})
 .allowedHeaders("*")
 .exposedHeaders("*");
 }

}
```



##### 3、安装路由插件

安装命令如下

```
npm i vue-router@3.5.4
```

在router/创建路由文件index.js，在main.js中引入

```
import VueRouter from 'vue-router';
import router from './router'; 
```

##### 4、动态路由vuex

主要原理为数据库后端建立menu表，⽣成menu对应的后端代码，从而进行动态路由控制。安装命令

```
npm i vuex@3.0.0
```

在路由文件index.js下编写如下代码

```js
import vue from 'vue'
import Vuex from 'vuex'
import router, {resetRouter} from "../router";
import createPersistedState from 'vuex-persistedstate'
vue.use(Vuex)

function addNewRoute(menuList) {
    console.log(menuList)
    let routes = router.options.routes//路由列表
    console.log(routes)
    routes.forEach(routeItem=>{
        if(routeItem.path=="/Index"){
            menuList.forEach(menu=>{
                let childRoute =  {
                    path:'/'+menu.menuclick,
                    name:menu.menuname,
                    meta:{
                        title:menu.menuname
                    },
                    component:()=>import('../components/'+menu.menucomponent)
                }
                routeItem.children.push(childRoute)
            })
        }
    })

    resetRouter()
    router.addRoutes(routes)
}

export default new Vuex.Store({
    state: {
        menu: []
    },
    mutations: {
        setMenu(state,menuList) {
            state.menu = menuList

            addNewRoute(menuList)
        },
        setRouter(state,menuList) {
            addNewRoute(menuList)
        }
    },
    getters: {
        getMenu(state) {
            return state.menu
        }
    },
    plugins:[createPersistedState()]
})
```

main.js下进行注册

```
import store from "./store"
```

使用动态路由可以根据用户登录的不同身份显示不同的页面。

##### 5、页面布局拆分编写

我的页面主要拆分为三大部分，分别是Header头页面、菜单导航页面、主页面展示（如dept表数据展示以及增删改查），其它的还有登陆页面，时间显示页面等等，这里我就不再一一赘述。

##### 6、不同用户登录不同页面

不同用户登录不同页面主要由动态路由控制，实现的功能为如果登录者为超级管理员（数据库设置超级用户姓名为gpl），那么可以访问所有的页面。如果登陆者为管理员，（如管理员wzx），不能访问只有超级管理员gpl才能访问的管理员管理界面，其它的页面都可以访问。如果登陆者为普通用户（如数据库中的who1）,则只能访问首页。主要实现方式为在数据库中建立相应的菜单menu表来控制访问权限。

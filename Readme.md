DataBinder:在HttpServletRequest中的参数塞值到@RequestMapping的方法中。
将来利用反射调用@RequestMapping方法，返回值渲染到HttpServletRespinse中展现到页面
https://blog.csdn.net/u012410733/article/details/53368351
Spring @Validate主要用作controller的参数校验还有@Min @Max @NotBlank等注解

OOD 12字精髓总结：“变化的抽接口，相同的建模版” ----- 精辟
Environment： spring的xxx.properties或者xxx.yaml文件里定义的各种key - value都存在Environment里面，@Value就是从这些Environment中找到并且注入的  
BeanFactoryPostProcessor 应用场景是对xxx.properties或者xxx.yaml读出来的内容在运行时动态的增删改查
BeanFactoryPostProcessor在容器实例化任何其它bean之前读取配置元数据，并可以根据需要进行修改，例如可以把bean的scope从singleton改为prototype，  
也可以把property的值给修改掉。可以同时配置多个BeanFactoryPostProcessor，并通过设置'order'属性来控制各个BeanFactoryPostProcessor的执行次序。  

BeanPostProcessor，可以在spring容器实例化bean之后，在执行bean的初始化方法前后，添加一些自己的处理逻辑。这里说的初始化方法，指的是下面两种：
1）bean实现了InitializingBean接口，对应的方法为afterPropertiesSet

2）在bean定义的时候，通过init-method设置的方法

注意：BeanPostProcessor是在spring容器加载了bean的定义文件并且实例化bean之后执行的。BeanPostProcessor的执行顺序是在BeanFactoryPostProcessor之后。  

BeanFactoryPostProcessor在bean实例化之前执行，之后实例化bean（调用构造函数，并调用set方法注入属性值），然后在调用两个初始化方法前后，执行了BeanPostProcessor。  
初始化方法的执行顺序是，先执行afterPropertiesSet，再执行init-method  

BeanFactory是spring的根接口。每个接口都是不同的功能；多个借口在一起的时候，这个接口就有了其他接口的性质。BeanFactory就是个注册中心，就是保存信息的地方，其他组件统一获取  
什么是DI/IOC？依赖注入和控制反转，太简单，不说了。什么是AOP？Aspect Oriented Programming，实现原理是什么？反射 + 动态代理。在spring中是如何实现的？BeanPostProcessor（运行期增强）  
编译期增强：aspectJ lombok； 

prototype的bean不会被放入实例化对象池，每次都new新的（不缓存）；其他两种scope：request和session很少用到  
关注一下FeignClientFactoryBean, FeignClientsConfiguration, FeignClientRegistrar,这三个spring-cloud-openfeign的类  

https://blog.csdn.net/caihaijiang/article/details/35552859
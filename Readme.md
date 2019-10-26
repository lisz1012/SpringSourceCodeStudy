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

不要因为技术而技术，要明白为什么要有这个技术，技术的原理，因该怎么用这个技术  
拿着天使投资花在营销上，会赚的更多。很多人（中国）就在做营销，手段极其残忍。比如陌陌这个app，有正规渠道可以获知他：  
1. CCTV广告，陌陌让具有相同爱好的人聚在一起（其实就是解决一个晚上的问题，也可以让兴趣相同的人聚在一起）  
2. 百度买关键字排名  
3. 找微博大V给钱推广  
4. 制造话题营销   
已经从单纯关注流量转向了关注VIP用户和赚会员费  
更多的人下载使用服务，这就带来了高并发的问题  
渠道流量有时候容易被忽略：就是用户从哪个网站的链接上访问的我的网站。日志分析可以得到这个数据，再配合后台交易记录做Join，就可以分析出每个渠道的转化率或者购买力。然后下一轮融资再投广告的时候，  
就往转化率高的渠道投资。JD除了自己卖东西也可以做3P，提供平台服务第三方商家，这时候东哥就可以拿着这个数据给商家，说你为什么转化率少呢？我可以提供服务帮你提高转化率  

https://blog.csdn.net/caihaijiang/article/details/35552859
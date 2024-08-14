
# 开发文档


## 功能介绍

- 会员管理：管理系统可以录入、修改和查询会员的基本信息，如名称、性别、年龄、备注等。
- 类型管理：系统可以管理会员的类型信息，包括类型的名称等。
- 评论管理：管理和浏览整个网站的评论信息。
- 用户管理：管理和浏览网站的用户信息，可以新增、编辑和删除用户。
- 统计分析：系统可以根据会员的活动数据和用户参与度进行统计和分析，帮助管理员了解整个系统的状况。
- 消息管理：会员管理员可以在系统上发布消息，整个网站的用户都能收到。
- 广告管理：会员管理员可以在系统上发布广告消息，然后在详情页面右侧展示。
- 意见反馈：会员管理员可以在后台查看浏览用户提交的意见反馈信息。
- 系统信息：管理员可以查看系统的基本信息，包括系统名称、服务器信息、内存信息、cpu信息、软件信息等。
- 注册登录：用户通过注册和登录后，才能使用网站。
- 门户浏览：用户进入首页后，可以浏览会员列表信息，包括最新、最热。
- 热门推荐：基于协同过滤推荐算法的热门推荐。
- 用户中心：包括用户基本资料修改、用户基本信息、密码、收藏点赞等。
- 我的约会：包括我的约会信息。
- 意见反馈：包括用户提交意见反馈的入口页面。
- 模糊搜索：顶部搜索功能，支持模糊搜索会员信息。
- 会员评论：详情页下侧用户可以评论会员。
 
## 开发环境

- 后端： Java 1.8 + SpringBoot + MyBatis
- 前端： Javascript + Vue
- 数据库：MySQL 5.7
- 开发平台：Idea 2021 + vscode
- 运行环境：Windows 10/11

## 关键技术

- 前端技术栈 ES6、vue、vuex、vue-router、vue-cli、axios、antd
- 后端技术栈 SpringBoot、MyBatis、Maven

### 后端技术

#### Springboot框架

Spring Boot是一款开箱即用框架，提供各种默认配置来简化项目配置。让我们的Spring应用变的更轻量化、更快的入门。 在主程序执行main函数就可以运行。你也可以打包你的应用为jar并通过使用java -jar来运行你的Web应用。它遵循"约定优先于配置"的原则， 使用SpringBoot只需很少的配置，大部分的时候直接使用默认的配置即可。同时可以与Spring Cloud的微服务无缝结合。

优点：
- 使编码变得简单： 推荐使用注解。
- 使配置变得简单： 自动配置、快速集成新技术能力 没有冗余代码生成和XML配置的要求
- 使部署变得简单： 内嵌Tomcat、Jetty、Undertow等web容器，无需以war包形式部署
- 使监控变得简单： 提供运行时的应用监控
- 使集成变得简单： 对主流开发框架的无配置集成。
- 使开发变得简单： 极大地提高了开发快速构建项目、部署效率。

### 前端技术

- npm：node.js的包管理工具，用于统一管理我们前端项目中需要用到的包、插件、工具、命令等，便于开发和维护。
- ES6：Javascript的新版本，ECMAScript6的简称。利用ES6我们可以简化我们的JS代码，同时利用其提供的强大功能来快速实现JS逻辑。
- vue-cli：Vue的脚手架工具，用于自动生成Vue项目的目录及文件。
- vue-router： Vue提供的前端路由工具，利用其我们实现页面的路由控制，局部刷新及按需加载，构建单页应用，实现前后端分离。
- vuex：Vue提供的状态管理工具，用于统一管理我们项目中各种数据的交互和重用，存储我们需要用到数据对象。
- Ant-design：基于MVVM框架Vue开源出来的一套前端ui组件。

## 运行步骤

### 后端运行步骤

(1) 下载代码后，使用IntelliJ IDEA打开server目录

(2) 配置application.yml文件，配置数据库DB_NAME和BASE_LOCATION

(3) 安装mysql 5.7数据库后，创建数据库，创建SQL如下：
```
CREATE DATABASE IF NOT EXISTS [db_name] DEFAULT CHARSET utf8 COLLATE utf8_general_ci
```
(4) 恢复sql数据。在mysql下依次执行如下命令：

```
mysql> use [db_name]; // 你的数据库名字
mysql> source D:/xxx/xxx/java_xxxx.sql;
```

(5) 启动后端服务：点击IDEA顶部run按钮


### 前端运行步骤

(1) 安装node 16以上版本

(2) cmd进入web目录下，安装依赖，执行:
```
npm install 
```
(3) 运行项目
```
npm run dev
```

## 代码结构

### 后端结构

```
com.gk.study  
├── common            // 工具类
│       └── APIResponse                   // 返回体
│       └── ResponseCode                  // 状态码
├── controller         // 业务接口
│       └── ThingController               // 业务
│       └── ClassificationController      // 分类
│       └── CommentController             // 评论
│       └── UserController                // 用户
│       └── FeedbackController            // 意见
│       └── FeedbackController            // 意见
│       └── AdController                  // 广告
│       └── OrderController               // 约会
│       └── OpLogController               // 日志
├── entity            // 实体类
├── interceptor       // 拦截器
├── mapper            // 数据库映射
├── service           // 服务实现
├── utils             // 辅助代码
```

### 前端结构

```
├── build                      // 构建相关  
├── public                     // 公共文件
│   ├── favicon.ico            // favicon图标
│   └── index.html             // html模板
├── src                        // 源代码
│   ├── api                    // 所有请求
│   ├── assets                 // 主题 字体等静态资源
│   ├── router                 // 路由
│   ├── store                  // 全局 store管理
│   ├── utils                  // 全局公用方法
│   ├── views                  // view界面
│   ├── App.vue                // 入口页面
│   ├── main.js                // 入口 加载组件 初始化等
│   └── settings.js            // 系统配置
├── .eslintignore              // 忽略语法检查
├── .eslintrc.js               // eslint 配置项
├── .gitignore                 // git 忽略项
├── babel.config.js            // babel.config.js
├── package.json               // package.json
└── vite.config.js             // vue配置
```

## 数据库设计

详细的数据库设计，可见doc文件夹中的《表结构》word文件。


## 开发流程

下面是主要功能的开发流程，包括会员管理、用户管理、分类管理、评价管理、约会管理、消息管理、注册登录、热门推荐等。

### 会员管理功能的开发流程

第一步：编写实体类

在server的entity文件夹中，新建Thing.java文件。并编写实体代码。请参考Thing.java文件。


第二步：编写mapper数据库映射

在server的mapper文件夹下，新建ThingMapper.java文件，并写入代码。代码请参考ThingMapper.java文件。

第三步：编写service层

在server的service文件夹中，新建ThingService.java代码，并写入代码。
 
并在impl中编写它的实现类ThingServiceImpl.java。请参考这两个文件。

第四步： 编写controller层

在server的controller文件夹新建ThingController.java文件，实现增删改查接口，具体代码参考ThingController.java文件。


第五步：编写界面和API

打开前端web工程，在views文件夹下新建thing.vue文件，并编写页面代码，具体代码参考thing.vue文件。

### 分类管理功能的开发流程

第一步：编写实体类

在server的entity文件夹中，新建Classification.java文件。并编写实体代码。请参考Classification.java文件。


第二步：编写mapper数据库映射

在server的mapper文件夹下，新建ClassificationMapper.java文件，并写入代码。代码请参考ClassificationMapper.java文件。

第三步：编写service层

在server的service文件夹中，新建ClassificationService.java代码，并写入代码。

并在impl中编写它的实现类ClassificationServiceImpl.java。请参考这两个文件。

第四步： 编写controller层

在server的controller文件夹新建ClassificationController.java文件，实现增删改查接口，具体代码参考ClassificationController.java文件。


第五步：编写界面和API

打开前端web工程，在views文件夹下新建classification.vue文件，并编写页面代码，具体代码参考classification.vue文件。

### 用户管理功能的开发流程

第一步：编写实体类

在server的entity文件夹中，新建User.java文件。并编写实体代码。请参考User.java文件。


第二步：编写mapper数据库映射

在server的mapper文件夹下，新建UserMapper.java文件，并写入代码。代码请参考UserMapper.java文件。

第三步：编写service层

在server的service文件夹中，新建UserService.java代码，并写入代码。

并在impl中编写它的实现类UserServiceImpl.java。请参考这两个文件。

第四步： 编写controller层

在server的controller文件夹新建UserController.java文件，实现增删改查接口，具体代码参考UserController.java文件。


第五步：编写界面和API

打开前端web工程，在views文件夹下新建user.vue文件，并编写页面代码，具体代码参考user.vue文件。

### 评论管理功能的开发流程

第一步：编写实体类

在server的entity文件夹中，新建Comment.java文件。并编写实体代码。请参考Comment.java文件。


第二步：编写mapper数据库映射

在server的mapper文件夹下，新建CommentMapper.java文件，并写入代码。代码请参考CommentMapper.java文件。

第三步：编写service层

在server的service文件夹中，新建CommentService.java代码，并写入代码。

并在impl中编写它的实现类CommentServiceImpl.java。请参考这两个文件。

第四步： 编写controller层

在server的controller文件夹新建CommentController.java文件，实现增删改查接口，具体代码参考CommentController.java文件。


第五步：编写界面和API

打开前端web工程，在views文件夹下新建comment.vue文件，并编写页面代码，具体代码参考comment.vue文件。

### 约会管理功能的开发流程

第一步：编写实体类

在server的entity文件夹中，新建Order.java文件。并编写实体代码。请参考Order.java文件。


第二步：编写mapper数据库映射

在server的mapper文件夹下，新建OrderMapper.java文件，并写入代码。代码请参考OrderMapper.java文件。

第三步：编写service层

在server的service文件夹中，新建OrderService.java代码，并写入代码。

并在impl中编写它的实现类OrderServiceImpl.java。请参考这两个文件。

第四步： 编写controller层

在server的controller文件夹新建OrderController.java文件，实现增删改查接口，具体代码参考OrderController.java文件。


第五步：编写界面和API

打开前端web工程，在views文件夹下新建order.vue文件，并编写页面代码，具体代码参考order.vue文件。

### 消息管理功能的开发流程

第一步：编写实体类

在server的entity文件夹中，新建Notice.java文件。并编写实体代码。请参考Notice.java文件。


第二步：编写mapper数据库映射

在server的mapper文件夹下，新建NoticeMapper.java文件，并写入代码。代码请参考NoticeMapper.java文件。

第三步：编写service层

在server的service文件夹中，新建NoticeService.java代码，并写入代码。

并在impl中编写它的实现类NoticeServiceImpl.java。请参考这两个文件。

第四步： 编写controller层

在server的controller文件夹新建NoticeController.java文件，实现增删改查接口，具体代码参考NoticeController.java文件。


第五步：编写界面和API

打开前端web工程，在views文件夹下新建notice.vue文件，并编写页面代码，具体代码参考notice.vue文件。

### 注册登录功能开发流程

在后端的UserController.java中编写注册接口和登录接口。

在前端编写注册页面register.vue，和登录页面login.vue。

### 热门推荐功能开发流程

热门推荐功能使用的是协同过滤推荐算法。该模块分为两个步骤，分别是记录用户浏览数据，和给用户推荐物品。

记录用户浏览数据，采用的是ThingController的detail接口里面，将用户浏览记录保存在b_record表里。相关代码如下：

```
    // ------------------保存浏览记录--------------------
    String ip = IpUtils.getIpAddr(request);
    Record record = recordService.getRecord(thing.getId(), ip);
    if(record != null){
        record.setScore(record.getScore() + 1);
        recordService.updateRecord(record);
    }else {
        Record entity = new Record();
        entity.setThingId(thing.getId());
        entity.setIp(ip);
        entity.setScore(1);
        recordService.createRecord(entity);
    }
```

再就是 给用户推荐的流程，通过调用recommend接口的形式，代码如下。具体流程是：先获取用户的ip，然后构造出所有ip的浏览列表，然后调用recommend方法构造推荐数据，并返回给前端。

```
public APIResponse recommend(HttpServletRequest request){

        // 获取ip列表
        List<String> ips = recordService.getRecordIpList();


        List<UserCF> users = new ArrayList<>();
        for(String ip : ips){
            // 获取ip对于的物品
            List<Record> recordList = recordService.getRecordListByIp(ip);
            UserCF userCF = new UserCF(ip);
            for(Record record: recordList){
                userCF.set(record.thingId, record.score);
            }
            users.add(userCF);
        }

        List<Thing> thingList;

        if(users.size() <= 1){
            // 1个用户不满足协同推荐条件
            thingList = service.getDefaultThingList();
        }else {
            Recommend recommend = new Recommend();
            String currentIp = IpUtils.getIpAddr(request);
            List<RecEntity> recommendList = recommend.recommend(currentIp, users);
            List<Long> thingIdList = recommendList.stream().map(A -> A.thingId).collect(Collectors.toList());
            if(thingIdList.size() > 0){
                thingList = service.getThingListByThingIds(thingIdList);
                if(thingList == null || thingList.size() < 1){
                    // 如推荐量太少，则走默认
                    thingList = service.getDefaultThingList();
                }
            }else {
                thingList = service.getDefaultThingList();
            }
        }
        return new APIResponse(ResponeCode.SUCCESS, "查询成功", thingList);
    }
```

## 重要模块实现

### 分页实现

基于ant-design框架的a-table的分页插件。

```
// 分页变量

  const data = reactive({
    dataList: [],
    loading: false,
    keyword: '',
    selectedRowKeys: [] as any[],
    pageSize: 10,
    page: 1,
  });
  
// 分页插件
:pagination="{
          size: 'default',
          current: data.page,
          pageSize: data.pageSize,
          onChange: (current) => (data.page = current),
          showSizeChanger: false,
          showTotal: (total) => `共${total}条数据`,
        }"

```

### 请求工具实现

前端的请求工具是基于axios开发的，位于utils的http文件夹中。封装了request请求和拦截器。

```
const service: AxiosInstance = axios.create({
  // baseURL: import.meta.env.BASE_URL + '',
  baseURL: BASE_URL + '',
  timeout: 15000,
});

// axios实例拦截请求
service.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    config.headers.ADMINTOKEN = localStorage.getItem(ADMIN_USER_TOKEN);
    config.headers.TOKEN = localStorage.getItem(USER_TOKEN);

    return config;
  },
  (error: AxiosError) => {
    return Promise.reject(error);
  },
);

// axios实例拦截响应
service.interceptors.response.use(
  (response: AxiosResponse) => {
    if (response.status == 200) {
      if (response.data.code == 0 || response.data.code == 200) {
        return response;
      } else {
        return Promise.reject(response.data);
      }
    } else {
      return Promise.reject(response.data);
    }
  },
  // 请求失败
  (error: any) => {
    console.log(error.response.status);
    if (error.response.status == 404) {
      // todo
    } else if (error.response.status == 403) {
      // todo
    }
    return Promise.reject(error);
  },
);

```

### 权限控制模块

权限控制使用了Access和AccessLevel注解实现的，通过设置访问常量实现访问权限的控制，

```
// 关键代码
public enum AccessLevel {

    LOGIN(1, "all"), // 登录用户
    DEMO(2, "demo"), // 演示账号
    ADMIN(3, "admin"), // 管理员
```

每次使用的时候，可以在controller接口上面加入相应的注解来实现权限控制，例如下面加入了@Access(level = AccessLevel.ADMIN)
注解，表明当前接口只能管理员调用。

```
    @Access(level = AccessLevel.ADMIN)
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @Transactional
    public APIResponse update(Thing thing) throws IOException {
        System.out.println(thing);
        String url = saveThing(thing);
        if(!StringUtils.isEmpty(url)) {
            thing.cover = url;
        }

        service.updateThing(thing);
        return new APIResponse(ResponeCode.SUCCESS, "更新成功");
    }
```

底层原理实现是通过实现HandlerInterceptorAdapter接口来运行的。参见interceptor文件夹下的AccessInterceptor.java

```
@Component
public class AccessInterceptor extends HandlerInterceptorAdapter {

    private Logger logger = LoggerFactory.getLogger(AccessInterceptor.class);

    private static OpLogService service;

    private static UserService userService;

    @Autowired
    public void setOpLogService( OpLogService service) {
        // 为解决先@Component 后@Autowired失效的方案
        AccessInterceptor.service = service;
    }

    @Autowired
    public void setUserService( UserService userService) {
        // 为解决先@Component 后@Autowired失效的方案
        AccessInterceptor.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
            // 权限判断
    }   
```

### 路由模块实现

前端的路由是基于vue-router框架实现的，路由文件位于src的rooter的root.js文件中。预览如下：

```
  {
    path: '/admin',
    name: 'admin',
    redirect: '/admin/thing',
    component: () => import('/@/views/admin/main.vue'),
    children: [
      { path: 'overview', name: 'overview', component: () => import('/@/views/admin/overview.vue') },
      { path: 'order', name: 'order', component: () => import('/@/views/admin/order.vue') },
      { path: 'thing', name: 'thing', component: () => import('/@/views/admin/thing.vue') },
      { path: 'comment', name: 'comment', component: () => import('/@/views/admin/comment.vue') },
      { path: 'user', name: 'user', component: () => import('/@/views/admin/user.vue') },
      { path: 'classification', name: 'classification', component: () => import('/@/views/admin/classification.vue') },
      { path: 'ad', name: 'ad', component: () => import('/@/views/admin/ad.vue') },
      { path: 'notice', name: 'notice', component: () => import('/@/views/admin/notice.vue') },
      { path: 'loginLog', name: 'loginLog', component: () => import('/@/views/admin/login-log.vue') },
      { path: 'opLog', name: 'opLog', component: () => import('/@/views/admin/op-log.vue') },
      { path: 'errorLog', name: 'errorLog', component: () => import('/@/views/admin/error-log.vue') },
      { path: 'sysInfo', name: 'sysInfo', component: () => import('/@/views/admin/sys-info.vue') },
    ]
  },
```

## 常见问题

- 数据库版本有要求吗？

需要mysql 5.7以上

- 前端 npm install 失败怎么办？

使用国内镜像安装，设置命令为：
```
npm config set registry https://registry.npm.taobao.org
```

- 提示"演示账号无法操作"，怎么办？

将用户的权限提高，修改b_user表的role字段

- 如何更换后端请求地址

修改store文件夹下的constants.js文件中的BASE_URL，改成你自己的后端地址

- 如何新增页面

在views文件夹下创建新的vue文件，写入界面代码，然后在router的root.js中添加路由即可。






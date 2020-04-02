### 基于FEBS的新型教学管理系统

![https://img.shields.io/badge/license-Apache%202.0-blue.svg?longCache=true&style=flat-square](https://img.shields.io/badge/license-Apache%202.0-blue.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/springboot-2.2.1-yellow.svg?style=flat-square](https://img.shields.io/badge/springboot-2.2.1-yellow.svg?style=flat-square)
![https://img.shields.io/badge/shiro-1.4.2-orange.svg?longCache=true&style=flat-square](https://img.shields.io/badge/shiro-1.4.2-orange.svg?longCache=true&style=flat-square)
![https://img.shields.io/badge/layui-2.5.5-brightgreen.svg?longCache=true&style=flat-square](https://img.shields.io/badge/layui-2.5.5-brightgreen.svg?longCache=true&style=flat-square)

FEBS-Shiro是一款简单高效的后台权限管理系统，使用Spring Boot，Shiro和Layui构建。FEBS意指：**F**ast，**E**asy use，**B**eautiful和**S**afe。相信无论作为企业级应用，私活开发脚手架或者权限系统构建学习，FEBS-Shiro都会是一个不错的选择。

### 系统模块
系统功能模块组成如下所示：
```
├─系统管理
│  ├─用户管理
│  ├─角色管理
│  ├─菜单管理
│  ├─部门管理
│  ├─公告管理
│  ├─教室管理
│  └─课程管理
├─系统监控
│  ├─在线用户
│  ├─系统日志
│  ├─登录日志
│  ├─请求追踪
│  └─系统信息
│     ├─JVM信息
│     └─服务器信息
├─任务调度
│  ├─定时任务
│  └─调度日志
├─随堂测验
│  ├─试题管理
│  ├─试卷管理
│  ├─开始测验
│  └─测验记录
├─课堂笔记
└─在线课表
   ├─课程安排管理
   └─我的课表
```

### 亮点功能

1. 自定义的首页公告管理

2. 试卷分析图表及个人测验记录分析图表

3. 以班级为单位的课表安排及展示

4. 自定义附件上传及下载的笔记模块

### 系统特点

1. 前后端请求参数校验

2. 支持Excel导入导出

3. 前端页面布局多样化，主题多样化

4. 支持多数据源

5. 多Tab页面

6. 用户权限动态刷新

7. 浏览器兼容性好，页面支持PC，Pad和移动端。

8. 代码简单，结构清晰

### 技术选型

#### 后端
- [Spring Boot 2.2.1](http://spring.io/projects/spring-boot/)
- [Mybatis-Plus](https://mp.baomidou.com/guide/)
- [Oracle 11g](https://www.oracle.com/technetwork/database/enterprise-edition/downloads/index.html),[Hikari](https://brettwooldridge.github.io/HikariCP/),[Redis](https://redis.io/)
- [Shiro 1.4.2](http://shiro.apache.org/)

#### 前端
- [Layui 2.5.5](https://www.layui.com/)
- [Nepadmin](https://gitee.com/june000/nep-admin)
- [formSelects 4.x 多选框](https://hnzzmsf.github.io/example/example_v4.html)
- [eleTree 树组件](https://layuiextend.hsianglee.cn/eletree/)
- [formSelect.js树形下拉](https://wujiawei0926.gitee.io/treeselect/docs/doc.html)
- [Apexcharts图表](https://apexcharts.com/)

### 系统预览

![1585810539421.jpg](https://github.com/zhoudalu10/ntms/blob/master/screenshot/1585810539421.jpg)

![1585810581326.jpg](https://github.com/zhoudalu10/ntms/blob/master/screenshot/1585810581326.jpg)

![1585810609800.jpg](https://github.com/zhoudalu10/ntms/blob/master/screenshot/1585810609800.jpg)

![1585810686661.jpg](https://github.com/zhoudalu10/ntms/blob/master/screenshot/1585810686661.jpg)

![1585810726559.jpg](https://github.com/zhoudalu10/ntms/blob/master/screenshot/1585810726559.jpg)

![1585810775143.jpg](https://github.com/zhoudalu10/ntms/blob/master/screenshot/1585810775143.jpg)

### 浏览器兼容
|[<img src="https://raw.github.com/alrra/browser-logos/master/src/archive/internet-explorer_9-11/internet-explorer_9-11_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>IE| [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/edge/edge_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Edge | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/firefox/firefox_48x48.png" alt="Firefox" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Firefox | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/chrome/chrome_48x48.png" alt="Chrome" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Chrome | [<img src="https://raw.githubusercontent.com/alrra/browser-logos/master/src/safari/safari_48x48.png" alt="Safari" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Safari |[<img src="https://raw.github.com/alrra/browser-logos/master/src/opera/opera_48x48.png" alt="Edge" width="24px" height="24px" />](http://godban.github.io/browsers-support-badges/)</br>Opera
| --------- | --------- | --------- | --------- | --------- |--------- |
|IE 10+| Edge| last 15 versions| last 15 versions| last 10 versions| last 15 versions

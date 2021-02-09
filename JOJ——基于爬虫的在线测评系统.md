# 一、 需求分析

Online Judge系统（简称OJ）是一个在线的判题系统。用户可以在线提交程序多种程序（如C、C++）源代码，系统对源代码进行编译和执行，并通过预先设计的测试数据来检验程序源代码的正确性。

一个用户提交的程序在Online Judge系统下执行时将受到比较严格的限制，包括运行时间限制，内存使用限制和安全限制等。用户程序执行的结果将被Online Judge系统捕捉并保存，然后再转交给一个裁判程序。该裁判程序或者比较用户程序的输出数据和标准输出样例的差别，或者检验用户程序的输出数据是否满足一定的逻辑条件。最后系统返回给用户一个状态：通过（Accepted）、答案错误(Wrong Answer)、超时(Time Limit Exceed)、超内存（Memory Limit Exceed）、运行时错误（Runtime Error）或是无法编译（Compile Error），并返回程序使用的内存、运行时间等信息。

Online Judge系统最初使用于ACM-ICPC国际大学生程序设计竞赛和OI信息学奥林匹克竞赛中的自动判题和排名。现广泛应用于世界各地高校学生程序设计的训练、参赛队员的训练和选拔、各种程序设计竞赛以及数据结构和算法的学习和作业的自动提交判断中。

Online Judge一般还提供比赛的功能，但只能由OJ的管理员引用本OJ题库中的题进行创建比赛。而普通用户想创建比赛或者引用多个OJ的题目则无法实现。本系统就是基于这个目的来实现的。

本系统可以让用户自行创建比赛，而且引用不同OJ的题源。

本系统还提供了博客模块，可以用户可以在本OJ系统上发布博客。可以用于赛后的题解编写，交流讨论。



**用例模型**

![](joj/20.png)



# 二、功能介绍

本系统主要可以分为几个模块

1. 用户
2. 题库
3. 比赛
4. 博客
5. 留言板
6. 后台
7. 其他

## 2.1 用户



用户注册时需要绑定邮箱。用户名必须唯一。若忘记密码，可以通过注册时的邮箱找回。



![](joj/1.png)



![](joj/2.png)

 

 ## 2.2 题库

可以对题库根据标签，源OJ名， 题号等进行查询。在题面界面，可以查看题面描述，样例信息等，

还有显示该题的提交结果情况等。

只有用户登陆后才能进行提交。否则只能查看题面信息。



![](joj/3.png)



在题目界面如果用户处于登录状态，回显示最近几次的提交记录。右上角有该题提交的统计信息。



![](joj/4.png)



## 2.3 比赛

游客、用户可以对根据比赛名进行查找。

用户可以从题库中选择题面创建比赛（题面不超过26道）。用户可以对题面的标题进行修改。

比赛权限分三种，public任何人都可以参加查看，private 需要用户输入相应密码才可以查看。protect 比赛进行时需要输入相应密码才可以进行参加，比赛结束则不需要。

比赛详情界面有题面的信息。比赛公告，比赛信息，比赛中的提交情况，榜单，留言。

排名规则：经典ACM规则

1. AC（通过题目）越多，排名越靠前。

2. AC相同，总用时越少，排名越靠前。
3. 总用时=∑(每一个题目的用时)
4. 每一个题目的用时=比赛开始到提交被通过的时间+罚时
5. 罚时=（通过前）错误代码提交次数*每次罚时（默认为20分钟）



![](joj/5.png)



![](joj/6.png)



![](joj/7.png)



![](joj/8.png)



![](joj/9.png)



![](joj/13.png)



## 2.3 博客

用户可以对博客进行增删改，游客可以查询访问博客。



![](joj/10.png)



![](joj/11.png)





![](joj/12.png)





## 2.5 留言板

游客，用户都可以添加留言，回复留言。用户可以删除自己的留言而游客不行。



![](joj/14.png)



## 2.6 其他

在首页显示本系统最近一个月，每天的提交数和AC数。

全网各OJ比赛的信息。

![](joj/15.png)



提交记录

![](joj/16.png)





可以查看提交后的代码。代码是公开的，游客用户均可查看。但是如果是在正在进行中的比赛提交的代码。除了本人和比赛创建者，其他人是无法查看的。

![](joj/17.png)



## 2.7 后台管理



具有admin身份的用户，可以管理系统的用户列表、博客列表、博客评论列表、题目列表、提交列表、比赛列表、留言列表。

![](joj/18.png)





# 三、 数据库设计（所涉及的表、ER图）



## 3.1 完整的E-R图



![](joj/21.png)



## 3.2 物理模型

![](joj/22.png)





## 3.3 各基表描述



| 基表英文名称: joj_blog |              |              |               |      |
| ---------------------- | ------------ | ------------ | ------------- | ---- |
| 字段编号               | 英文字段名   | 中文字段名   | 字段类型      | 备注 |
| 1                      | bid          | 博客编号     | int           | 主键 |
| 2                      | uid          | 用户编号     | int           | 外键 |
| 3                      | typeId       | 博客类型编号 | int           | 外键 |
| 4                      | title        | 博客标题     | nvarchar(200) |      |
| 5                      | content      | 博客内容     | text          |      |
| 6                      | summary      | 博客摘要     | text          |      |
| 7                      | firstPicture | 首图地址     | nvarchar(300) |      |
| 8                      | views        | 浏览量       | int           |      |
| 9                      | createTime   | 创建时间     | datetime      |      |
| 10                     | updateTime   | 更新时间     | datetime      |      |
| 11                     | published    | 是否发布     | tinyint       |      |
| 12                     | comment      | 是否可以评论 | tinyint       |      |



| 基表英文名称: joj_blog_comment |                 |                |          |                  |
| ------------------------------ | --------------- | -------------- | -------- | ---------------- |
| 字段编号                       | 英文字段名      | 中文字段名     | 字段类型 | 备注             |
| 1                              | bcid            | 博客评论编号   | int      | 主键             |
| 2                              | bid             | 博客编号       | int      |                  |
| 3                              | replyCommentId  | 目标评论编号   | int      | 外键，默认为null |
| 4                              | parentCommentId | 父评论节点编号 | int      | 外键，默认为null |
| 5                              | uid             | 用户编号       | int      | 外键             |
| 6                              | content         | 评论内容       | text     |                  |
| 7                              | createTime      | 评论时间       | datetime |                  |
| 8                              | email           | 邮箱           | varchar  |                  |
| 9                              | remind          | 回复是否提醒   | tinyint  |                  |



| 基表英文名称: joj_blog_type |            |              |              |      |
| --------------------------- | ---------- | ------------ | ------------ | ---- |
| 字段编号                    | 英文字段名 | 中文字段名   | 字段类型     | 备注 |
| 1                           | btid       | 博客类型编号 | int          | 主键 |
| 2                           | typeName   | 博客类型名称 | varchar(100) |      |





| 基表英文名称: joj_calendar_contest |            |            |              |      |
| ---------------------------------- | ---------- | ---------- | ------------ | ---- |
| 字段编号                           | 英文字段名 | 中文字段名 | 字段类型     | 备注 |
| 1                                  | oid        | 编号       | int          | 主键 |
| 2                                  | OJName     | OJ名称     | varchar      |      |
| 3                                  | title      | 比赛标题   | varchar(100) |      |
| 4                                  | beginTime  | 开始时间   | datetime     |      |
| 5                                  | endTime    | 结束时间   | datetime     |      |
| 6                                  | url        | 链接       | varchar      |      |





| 基表英文名称: joj_contest |              |            |          |                                      |
| ------------------------- | ------------ | ---------- | -------- | ------------------------------------ |
| 字段编号                  | 英文字段名   | 中文字段名 | 字段类型 | 备注                                 |
| 1                         | cid          | 比赛编号   | int      | 主键                                 |
| 2                         | uid          | 用户编号   | int      | 外键                                 |
| 3                         | title        | 比赛名称   | varchar  |                                      |
| 4                         | description  | 介绍       | varchar  |                                      |
| 5                         | announcement | 公共       | varchar  |                                      |
| 6                         | password     | 密码       | varchar  | 默认为null                           |
| 7                         | beginTime    | 开始时间   | datetime |                                      |
| 8                         | endTime      | 结束实现   | datetime |                                      |
| 9                         | auth         | 权限类型   | int      | 1 public 2 protect 3 private         |
| 10                        | type         | 比赛类型   | int      | 1 传统的ACM赛制                      |
| 11                        | status       | 比赛状态   | int      | 0 比赛未开始 1 比赛进行中 2 比赛结束 |
| 12                        | createTime   | 创建时间   | datetime |                                      |





| 基表英文名称: joj_contest_comment |                 |              |          |                  |
| --------------------------------- | --------------- | ------------ | -------- | ---------------- |
| 字段编号                          | 英文字段名      | 中文字段名   | 字段类型 | 备注             |
| 1                                 | ccid            | 比赛评论编号 | int      | 主键             |
| 2                                 | replyCommentId  | 回复评判编号 | int      | 外键，默认为null |
| 3                                 | uid             | 用户编号     | int      | 外键             |
| 4                                 | parentCommentId | 父评论编号   | int      | 外键，默认为null |
| 5                                 | cid             | 比赛编号     | int      | 外键             |
| 6                                 | content         | 评论内容     | text     |                  |
| 7                                 | createTime      | 创建时间     | datetime |                  |





| 基表英文名称: joj_contest_problem |            |              |          |      |
| --------------------------------- | ---------- | ------------ | -------- | ---- |
| 字段编号                          | 英文字段名 | 中文字段名   | 字段类型 | 备注 |
| 1                                 | cpid       | 比赛题目编号 | int      | 主键 |
| 2                                 | pid        | 题目编号     | int      | 外键 |
| 3                                 | cid        | 比赛编号     | int      | 外键 |
| 4                                 | alias      | 题目别名     | varchar  |      |
| 5                                 | num        | 题目号       | varchar  |      |



| 基表英文名称: joj_description |             |              |          |                                                              |
| ----------------------------- | ----------- | ------------ | -------- | ------------------------------------------------------------ |
| 字段编号                      | 英文字段名  | 中文字段名   | 字段类型 | 备注                                                         |
| 1                             | did         | 题目描述编号 | int      | 主键                                                         |
| 2                             | uid         | 用户编号     | int      | 外键                                                         |
| 3                             | pid         | 题目编号     | int      | 外键                                                         |
| 4                             | description | 题目描述内容 | text     |                                                              |
| 5                             | input       | 输入样例介绍 | text     |                                                              |
| 6                             | output      | 输出样例介绍 | text     |                                                              |
| 7                             | samples     | 题目样例     | text     | 用json数组格式保存。例如：[{'input':'input content','output':'output content'}] |
| 8                             | hint        | 提示信息     | text     |                                                              |
| 9                             | updateTime  | 更新时间     | datetime |                                                              |
| 10                            | author      | 作者         | varchar  |                                                              |
| 11                            | remarks     | 备注         | text     |                                                              |



| 基表英文名称: joj_message |                 |                |          |                  |
| ------------------------- | --------------- | -------------- | -------- | ---------------- |
| 字段编号                  | 英文字段名      | 中文字段名     | 字段类型 | 备注             |
| 1                         | mid             | 留言编号       | int      | 主键             |
| 2                         | replyMessageId  | 回复留言编号   | int      | 外键，默认为null |
| 3                         | parentMessageId | 父留言节点编号 | int      | 外键，默认为null |
| 4                         | uid             | 用户编号       | int      |                  |
| 5                         | createTime      | 留言创建时间   | datetime |                  |
| 6                         | content         | 留言内容       | text     |                  |
| 7                         | email           | 邮箱           | varchar  |                  |
| 8                         | nickName        | 昵称           | varchar  |                  |
| 9                         | remind          | 回复是否提醒   | tinyint  |                  |
| 10                        | avatar          | 头像地址       | varchar  |                  |



| 基表英文名称:joj_oj_daily_board |             |            |          |      |
| ------------------------------- | ----------- | ---------- | -------- | ---- |
| 字段编号                        | 英文字段名  | 中文字段名 | 字段类型 | 备注 |
| 1                               | odbid       | 编号       | int      | 主键 |
| 2                               | submitCount | 提交数     | int      |      |
| 3                               | accepted    | AC通过数   | int      |      |
| 4                               | collectTime | 日期       | date     |      |



| 基表英文名称: joj_participate |            |            |          |            |
| ----------------------------- | ---------- | ---------- | -------- | ---------- |
| 字段编号                      | 英文字段名 | 中文字段名 | 字段类型 | 备注       |
| 1                             | uid        | 用户编号   | int      | 主键，外键 |
| 2                             | cid        | 比赛编号   | int      | 主键，外键 |



| 基表英文名称: joj_problem |             |              |          |      |
| ------------------------- | ----------- | ------------ | -------- | ---- |
| 字段编号                  | 英文字段名  | 中文字段名   | 字段类型 | 备注 |
| 1                         | pid         | 题目编号     | int      | 主键 |
| 2                         | uid         | 用户编号     | int      | 外键 |
| 3                         | title       | 题目标题     | varchar  |      |
| 4                         | source      | 题目来源     | varchar  |      |
| 5                         | url         | 题目链接     | varchar  |      |
| 6                         | originOJ    | 源OJ名       | varchar  |      |
| 7                         | originProb  | 源OJ题目编号 | varchar  |      |
| 8                         | memoryLimit | 内存限制     | int      |      |
| 9                         | timeLimit   | 时间限制     | int      |      |
| 10                        | updateTime  | 更新时间     | datetime |      |



| 基表英文名称: joj_problem_tag |            |              |          |      |
| ----------------------------- | ---------- | ------------ | -------- | ---- |
| 字段编号                      | 英文字段名 | 中文字段名   | 字段类型 | 备注 |
| 1                             | id         | 题目标签编号 | int      | 主键 |
| 2                             | tagName    | 标签名       | varchar  |      |



| 基表英文名称: joj_problem_tags |            |              |          |            |
| ------------------------------ | ---------- | ------------ | -------- | ---------- |
| 字段编号                       | 英文字段名 | 中文字段名   | 字段类型 | 备注       |
| 1                              | id         | 题目标签编号 | int      | 主键、外键 |
| 2                              | pid        | 题目编号     | int      | 主键、外键 |



| 基表英文名称: joj_resource |              |            |          |      |
| -------------------------- | ------------ | ---------- | -------- | ---- |
| 字段编号                   | 英文字段名   | 中文字段名 | 字段类型 | 备注 |
| 1                          | resourceId   | 资源编号   | int      | 主键 |
| 2                          | resourceName | 资源名     | varchar  |      |



| 基表英文名称: joj_resource |            |            |          |      |
| -------------------------- | ---------- | ---------- | -------- | ---- |
| 字段编号                   | 英文字段名 | 中文字段名 | 字段类型 | 备注 |
| 1                          | roleId     | 角色编号   | int      | 主键 |
| 2                          | roleName   | 角色名     | varchar  |      |



| 基表英文名称:joj_role_resource |            |            |          |      |
| ------------------------------ | ---------- | ---------- | -------- | ---- |
| 字段编号                       | 英文字段名 | 中文字段名 | 字段类型 | 备注 |
| 1                              | roleId     | 角色编号   | int      | 主键 |
| 2                              | resourceId | 资源编号   | int      |      |



| 基表英文名称:joj_statusType |            |              |          |                                                              |
| --------------------------- | ---------- | ------------ | -------- | ------------------------------------------------------------ |
| 字段编号                    | 英文字段名 | 中文字段名   | 字段类型 | 备注                                                         |
| 1                           | id         | 状态编号     | int      | 主键                                                         |
| 2                           | type       | 状态类型的值 | int      | 该表需要插入这几种值，1. AC 2. PE 3. WA 4. TLE 5. MLE 6. OLE 7. RE 8. CE 9. UE 10. Queuing & Judging 11. Pending // 提交后，保存到数据库中的状态 |



| 基表英文名称:joj_submission |                 |                  |          |                                                              |
| --------------------------- | --------------- | ---------------- | -------- | ------------------------------------------------------------ |
| 字段编号                    | 英文字段名      | 中文字段名       | 字段类型 | 备注                                                         |
| 1                           | sid             | 提交编号         | int      | 主键                                                         |
| 2                           | uid             | 用户编号         | int      | 外键                                                         |
| 3                           | pid             | 题目编号         | int      | 外键                                                         |
| 4                           | status          | 状态名           | varchar  |                                                              |
| 5                           | statusType      | 状态类型         | int      | 1. AC 2. PE 3. WA 4. TLE 5. MLE 6. OLE 7. RE 8. CE 9. UE 10. Queuing & Judging 11. Pending // 提交后，保存到数据库中的状态 |
| 6                           | additionalInfo  | 备注信息         | text     | 存放编译错误等信息                                           |
| 7                           | realRunId       | 源OJ运行ID       | varchar  |                                                              |
| 8                           | time            | 运行时间         | int      |                                                              |
| 9                           | memory          | 内容消耗         | int      |                                                              |
| 10                          | subTime         | 本地OJ提交的时间 | datetime |                                                              |
| 11                          | language        | 语言类型         | varchar  | 填源OJ提交语言时对应的编号                                   |
| 12                          | displayLanguage | 显示的语言名     | varchar  |                                                              |
| 13                          | length          | 代码长度         | int      |                                                              |



| 基表英文名称:joj_user |            |            |          |      |
| --------------------- | ---------- | ---------- | -------- | ---- |
| 字段编号              | 英文字段名 | 中文字段名 | 字段类型 | 备注 |
| 1                     | uid        | 用户编号   | int      | 主键 |
| 2                     | userName   | 用户名     | varchar  |      |
| 3                     | nickName   | 用户昵称   | varchar  |      |
| 4                     | password   | 密码       | varchar  |      |
| 5                     | email      | 邮箱       | varchar  |      |
| 6                     | qq         | QQ         | varchar  |      |
| 7                     | avatar     | 头像地址   | varchar  |      |
| 8                     | createTime | 创建时间   | datetime |      |
| 9                     | updateTime | 更新时间   | datetime |      |





| 基表英文名称:joj_user_role |            |            |          |      |
| -------------------------- | ---------- | ---------- | -------- | ---- |
| 字段编号                   | 英文字段名 | 中文字段名 | 字段类型 | 备注 |
| 1                          | uid        | 用户编号   | int      |      |
| 2                          | roleId     | 角色编号   | int      |      |





# 四、系统使用说明

## 4.1 系统基本安装环境

开发环境：jdk1.8+tomcat9.0.33+IDEA2019.3+RabbitMQ 2.2.6 +MySQL8.0

## 4.2 项目部署

整个项目分为四大模块。

1. JOJ（主服务）：用于前端展示，与用户交互等
2. fileserver（文件服务器）：存放上传的文件信息。
3. judgeserver（判题机）：对用户提交的代码进行测评。
4. extraserver（拓展服务）：一些爬虫（爬取题目等）、定时任务（定时更新统计数据信息）、发送邮件

![](joj/25.png)

创建一个总工程`joj2020`。四个模块都继承自`joj2020`便于jar包版本管理。

四大模块打包成jar包后，通过下面命令运行

```
java -jar jarname
```



在mysql数据库中创建好数据库。

往数据库表joj_user插入一个Spider用户。往joj_problem_tag表中插入支持OJ对应的标签名

然后在**extraserver**和**joj**的配置文件中修改如下对应的配置文件，uid为Spider用户在数据库中的编号。tag.hdu和tag.codeforces为hdu和codeforces标签在数据库中的id

```yaml
joj:
  spider:
    uid: 1
    userName: "System Spider"

  default:
    tag:
      hdu: 1
```



安装好Rabbit MQ并创建交换机和消息队列

![](joj/26.png)



运行前需要求改一些相应的配置信息：

rabbitmq和mysql的配置信息。(四大部分均需要)

```yaml
spring:
  rabbitmq:
    host: ip
    port: 5672
    username: guest
    password: guest

  datasource:
    username: root
    password: root
    url: yourURL
    driver-class-name: com.mysql.cj.jdbc.Driver
    type: com.alibaba.druid.pool.DruidDataSource
    
```



发送邮件的相关配置 （extraserver部分）

```properties

#邮件发送的配置
spring.mail.username=your email adresss
spring.mail.password= password
spring.mail.host=smtp.qq.com

#配置465端口，保证在服务器上正常使用
spring.mail.properties.mail.smtp.socketFactory.port=465
spring.mail.default-encoding=UTF-8
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
spring.mail.properties.mail.smtp.starttls.required=true
spring.mail.port=465
spring.mail.properties.mail.smtp.socketFactory.class = javax.net.ssl.SSLSocketFactory
spring.mail.properties.mail.smtp.socketFactory.fallback = false
```



# 五、待完善部分。

由于开发时间优先，目前OJ暂时只支持HDU一个平台。如果您想拓展支持的OJ，可以根据定义号的爬虫的接口接着完善。

**Spider接口**

位于extraserver模块中

爬取题面信息

```java
package cn.jxj4869.joj.spider;

import cn.jxj4869.joj.entity.Description;
import cn.jxj4869.joj.entity.Problem;

import java.io.IOException;
import java.net.URISyntaxException;

public interface Spider {
    public Description crawl(Problem problem) throws Exception;
}

```

**Submitter接口**

提交代码的接口。具体实现可以参考**HDUSubmitter**或者有问题可以评论留言。

```java
package cn.jxj4869.joj.submitter;

import cn.jxj4869.joj.entity.Submission;
import cn.jxj4869.joj.mapper.ProblemMapper;
import cn.jxj4869.joj.mapper.SubmissionMapper;
import cn.jxj4869.joj.pojo.Result;
import cn.jxj4869.joj.utils.Tools;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

/**
 * @author JiangXiaoju
 * @date 2020-06-15 18:03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public abstract class Submitter {
    protected Submission submission;

    protected String userName;
    protected String password;

    @Autowired
    protected SubmissionMapper submissionMapper;
    @Autowired
    protected ProblemMapper problemMapper;

    protected CloseableHttpClient httpClient = HttpClients.createDefault();
    protected RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)
            .setConnectionRequestTimeout(500)
            .setSocketTimeout(10 * 1000)
            .build();


    public Submitter(String userName, String password) throws Exception {
        this.userName = userName;
        this.password = password;
        login();
    }
	// 登录
    protected abstract void login() throws Exception;

    // 代码提交
    protected abstract void submit() throws Exception;

    // 获取提交结果
    protected abstract void getAns() throws Exception;

    // 两次提交的冷却时间，视OJ而定
    public abstract void wait2SubmitTimeLimit();


    // 更新提交状态
    @Transactional
    protected void updateSubmission() {
        submissionMapper.updateById(submission);
    }

    // 获取编译错误等信息
    protected abstract void getAdditionalInfo() throws Exception;


    public void work() {
        try {
            try {
                submit();
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(2000);
                // 长时间未提交会自动注销，所以第一次提交失败后，先尝试登录一下。
                login();
                Thread.sleep(2000);
                submit();
            }
            submission.setStatus("Running & Judging");
            submission.setStatusType(Tools.findStatusType("Running & Judging"));
            updateSubmission();
            getAns();
            updateSubmission();
        } catch (Exception e) {
            submission.setStatus("Submit Fail");
            submission.setStatusType(Tools.findStatusType("Submit Fail"));
            updateSubmission();
            e.printStackTrace();
        }finally {
            this.submission = null;
        }


    }


}
```


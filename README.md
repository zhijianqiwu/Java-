# 项目简介

一个用于学生交流学习的社区，不仅实现了基本的注册、登录、发帖、评论、回复、私信等功能，同时使用前缀树实现敏感词过滤；使用 Redis 实现点赞与关注；使用 Kafka 处理发送评论、点赞和关注等系统通知；使用 Elasticsearch 实现全文搜索，关键词高亮显示等

# 技术架构

* Spring Boot 

* Spring、Spring MVC、MyBatis 

* **Redis、Kafka、Elasticsearch**

* Spring Security

# 功能列表

## 已经实现了的功能

- [x] 邮件发送
- [x] 注册
- [x] 验证码
- [x] 登录
- [x] 修改头像
- [x] 修改密码
- [x] 敏感词过滤
- [x] 发布帖子
- [x] 我的帖子
- [x] 帖子详情
- [x] 评论
- [x] 私信
- [x] 统一异常处理
- [x] 统一日志处理
- [x] 点赞
- [x] 关注
- [x] 系统通知
- [x] 搜索
- [x] 权限控制
- [x] 置顶、加精、删除

## TO DO List

- [ ] 网站统计
- [ ] 定时执行任务计算热门帖子
- [ ] 生成长图
- [ ] 文件上传至七牛云
- [ ] 监控
- [ ] 限流
- [ ] 积分模块
- [ ] 收藏模块
- [ ] 浏览量

# 运行

1. 安装JDK，Maven

2. 克隆代码到本地

   ```bash
   git clone https://github.com/zhengguohuang/community.git
   ```

3. 配置mysql、kafka、ElasticSearch

4. 启动zookeeper

5. 启动Kafka

6. 启动Elasticsearch

7. 运行打包命令

   ```bash
   mvn package
   ```

8. 运行项目

   ```bash
   java -jar xxx.jar
   ```

9. 访问项目

   ```
   http://localhost:8080
   ```


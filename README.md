![image](https://github.com/oraeorae/Recipe/blob/1/photo/11.png)

# 乡村特色菜谱
基于SpringBoot，Mybatis，Redis的乡村特色菜谱（本项目参加 2022年中国高校计算机大赛——微信小程序应用开发赛 ）

## 简介
在乡村振兴的大背景下，我们开创性地**以菜谱为切入点**，以**小切口推动大发展**。“乡村特色菜谱”是我们输出烹饪文化、菜品文化，同时推广乡村农产品、促进农民增收并构建乡村形象及乡村品牌的主要渠道。

### 功能点

1.  菜谱模块：

-   浏览菜谱：用户可以通过菜谱页面浏览系统中的菜谱，查看菜谱的图片、描述、材料和做法等信息。
-   搜索菜谱：用户可以通过菜谱搜索功能搜索自己感兴趣的菜谱，可以按名称、食材、口味等条件进行筛选。
-   上传菜谱：用户可以通过上传菜谱页面上传自己的菜谱，包括菜谱的名称、图片、描述、材料和做法等信息。
-   收藏菜谱：用户可以通过菜谱页面将自己喜欢的菜谱加入到收藏夹中，方便以后查看。

2.  商店模块：

-   浏览商品：用户可以通过商店页面浏览系统中的商品，查看商品的图片、描述、价格和库存等信息。
-   搜索商品：用户可以通过商店搜索功能搜索自己需要的商品，可以按名称、价格、库存等条件进行筛选。

3.  博物馆模块：

-    文章浏览：用户可以通过博物馆页面浏览系统中的文章，查看文章的标题、作者、摘要、内容等信息。
-   文章搜索： 用户可以通过博物馆搜索功能搜索自己感兴趣的文章，可以按标题、作者、标签等条件进行筛选。

4.  登录模块：
-   登录系统：用户可以通过登录页面进行系统登录，需要提供用户名和密码进行身份验证。

### 项目亮点
-   使用Redis进行缓存，将短时间内不会频繁改变的结果进行缓存一段时间，减少服务器压力；  
-   使用Log4j框架实现日志功能，记录服务器收到的请求，以及服务器的错误；
-   使用注解AOP进行接口限流。

### 项目结构
```
├─src
│ ├─main
│ │ ├─java
│ │ │ └─com
│ │ │ └─example
│ │ │ └─demo
│ │ │ ├─annotations
│ │ │ ├─aop
│ │ │ ├─controller
│ │ │ ├─dao
│ │ │ ├─pojo
│ │ │ ├─service
│ │ │ └─utils
```

### 架构设计
![image](https://github.com/oraeorae/Recipe/blob/1/photo/22.png)

### 类图
![image](https://github.com/oraeorae/Recipe/blob/1/photo/33.png)
![image](https://github.com/oraeorae/Recipe/blob/1/photo/44.png)
![image](https://github.com/oraeorae/Recipe/blob/1/photo/55.png)
![image](https://github.com/oraeorae/Recipe/blob/1/photo/66.png)
![image](https://github.com/oraeorae/Recipe/blob/1/photo/77.png)
### 数据库ER图
![image](https://github.com/oraeorae/Recipe/blob/1/photo/88.png)

## 开发环境

**开发环境**

小程序运行软件：微信开发者工具

小程序框架：vant框架

服务器端运行软件：Intellij IDEA

服务端框架：SpringBoot+MyBaits+Redis

**部署情况** 

服务器版本：Linux64位Ubuntu 20.04 64位

云服务所在：阿里云服务器

带宽：1Mbps按固定带宽

CPU：1核

内存：2GB

JDK版本：1.8

## API文档
https://console-docs.apipost.cn/preview/7dcbc893de8771bc/28fee78c52b142ce

## 更新日志
- 菜谱3.0版本（2022.6.17）

		版本特点：
			
			增加了日志功能
			
			增加了限流功能

- 菜谱2.0版本（功能基本完善版）（2022.5.24）

		版本特点
		
			新增了收藏功能
			
			添加了Redis来进行数据库信息的缓存

- 菜谱端基本接口1.0(2022.5.17)

-  基本雏形（2022.5.16）

## 小程序码
![image](https://github.com/oraeorae/Recipe/blob/1/photo/99.png)

## 页面展示
![image](https://github.com/oraeorae/Recipe/blob/1/photo/100.png)
![image](https://github.com/oraeorae/Recipe/blob/1/photo/111.png)
![image](https://github.com/oraeorae/Recipe/blob/1/photo/112.png)

# 📚 Novel Read Website（小说阅读网站）

一个 **大学实训项目**，基于前后端分离架构实现的小说阅读网站。  
项目采用 **Vue 3 + Spring Boot** 技术栈，实现了小说浏览、阅读、用户系统等核心功能，适合作为 Web 全栈开发实训与学习示例。

---

## 📌 项目简介

本项目为高校 Web 开发相关课程的 **综合实训项目**，采用当前主流的前后端分离开发模式，完整覆盖前端展示、后端业务处理与数据库设计等内容。

- **前端**：页面展示、用户交互、路由与状态管理  
- **后端**：RESTful API、业务逻辑、数据持久化与权限控制  
- **数据库**：MySQL  

项目结构清晰，技术选型规范，适合学习  
**Vue 3 + Spring Boot + MyBatis** 的完整开发流程。

---

## 🗂️ 项目结构

```text
├── NovelReadWebsite            # 前端项目（Vue 3）
├── NovelReadWebsiteServer      # 后端项目（Spring Boot）
├── _localhost-xxxx.sql         # 数据库示例数据
```

## 🖥️ 前端项目（NovelReadWebsite）

### 技术栈

- **Vue 3**
- **Vite**
- **Vue Router**
- **Pinia**（状态管理）
- **Element Plus**（UI 组件库）
- **Axios**（HTTP 请求）
- **Sass**
- **自动按需引入**
  - unplugin-auto-import
  - unplugin-vue-components

### 前端功能概述

- 页面路由管理与模块划分
- 用户状态管理（基于 Pinia）
- 与后端 RESTful API 进行数据交互
- 使用 Element Plus 构建统一的 UI 风格
- 基于 Vite 的快速开发与构建流程

### 前端启动方式

```
cd NovelReadWebsite
npm install
npm run dev
```

默认访问地址：

```
http://localhost:5173
```

------

## ⚙️ 后端项目（NovelReadWebsiteServer）

### 技术栈

- **Spring Boot**
- **MyBatis**
- **MySQL**
- **Redis**
- **JWT**（用户认证）
- **Lombok**
- **Hutool** 工具库
- **Redisson**（Redis 分布式操作）
- **Java Mail**（邮件服务）

### 后端功能概述

- 提供规范的 RESTful API 接口
- 用户注册、登录与身份认证（JWT）
- 小说数据的增删改查与业务处理
- Redis 缓存支持，提升系统性能
- MyBatis 实现持久层与数据库交互
- 统一异常处理与返回结果封装

### 后端启动方式

1. 创建并导入数据库

```
_localhost-xxxx.sql
```

1. 修改数据库配置
    `application.yml` 或 `application.properties`
2. 启动项目

```
mvn spring-boot:run
```

或直接运行 Spring Boot 启动类。

------

## 🔐 认证与安全

- 使用 **JWT** 实现用户身份认证
- 前后端分离架构，通过 Token 进行接口访问
- Redis 用于缓存与会话相关数据

------

## 📦 数据库设计

- 使用 **MySQL** 存储核心业务数据
- 提供示例 SQL 文件，便于快速部署与测试
- 使用 MyBatis 映射数据库表结构

------

## 🎯 项目特点

- 前后端分离，项目结构清晰
- 技术选型贴近真实企业开发场景
- 覆盖前端、后端、数据库、缓存等完整技术链路
- 适用于：
  - **大学课程设计 / 实训项目**
  - Java Web / Vue 技术学习
  - 个人项目与简历展示

------

## 📸 项目预览

> 可在此处补充系统界面截图或功能演示 GIF

------

## 📄 License

本项目仅用于 **学习、教学与交流**。
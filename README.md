
# 项目概述

智泊无忧项目为前后端分离架构，本仓库为后端源码仓库。

使用技术栈

| 技术名称       | 版本          | 用途描述                               |
|--------------|-------------|------------------------------------|
| Spring Boot  | 2.7.13      | 基本框架                               |
| MySQL        | 8.0.27      | 数据库                                |
| Knife4j      | 3.0.3       | 接口文档                               |
| MyBatis-Plus | 3.5.2       | 半ORM，简化CRUD操作                      |
| MapStruct    | 1.5.5.Final | 代码生成器，用于简化不同对象模型之间的映射转换。 |

前端使用Vue3.0 + TypeScript + Element-Plus搭建。

## 启动

1. 修改配置文件，更新数据库连接信息
2. 检查`pom.xml`文件，下载相关依赖`mvn clean mvn install`
3. 为了方便启动，可以不加载微信支付相关的`Bean`，设置`wxpay.enabled`为`false`

## 部署

部署文档存放在源码中，路径为：core/src/main/resources/doc

Linux环境部署启动步骤：

1. 先项目使用maven自带的package命令打包成jar包。

2. 将jar包和 dt.sh 脚本放在服务器的同级目录中。

3. 执行命令 ./dt.sh start 启动项目。

注意事项:  

1. 如果在输入 ./dt.sh start 命令的时候，提示没有权限，那么就先使用root账号权限，在 dt.sh 脚本所在的目录中，输入：chmod u+x dt.sh 命令，给dt.sh脚本赋予可以执行的权限。   

2. dt.sh脚本所在的目录中，只能允许存在一个 以 .jar 为结尾的 包存在，如果存在多个 jar 文件，就会存在识别不出具体要启动哪个jar包。

dt.sh 脚本命令说明：
      
./dt.sh start     ---  启动    
./dt.sh stop      ---  停止     
./dt.sh restart   ---  重启

建议使用：./dt.sh restart 

命令查看运行日志命令：在jar包所在的目录中，输入：tail -f nohup.out



## 版本说明

![输入图片说明](%E5%8A%9F%E8%83%BD%E6%B8%85%E5%8D%95.png)

## 在线演示

https://smartparking.xlbzone.com/#/login

【后台管理登录账号】

登录账号：admin

密码：123456

【商家登录账号】

登录账号：hotel

密码：123456




# 项目概述

智泊无忧项目为前后端分离架构，本仓库为后端源码仓库，前端基于Vue3.0版本结合TypeScript，UI框架采用elementPlus，后端使用java进行编写，以Springboot 2.6.0 + mysql 8.0.3 + mybatis plus 3.5.2 + knife4j 3.0.3 + mapstruct 1.3.1.Final 作为核心框架进行开发。

mysql           数据库
knife4j         作为接口文档
mybatis plus    作为数据层
mapstruct       作为数据转换

## 启动

1. 修改配置文件，更新数据库连接信息
2. 检查`pom.xml`文件，下载相关依赖`mvn clean mvn install`
3. 为了方便启动，可以不加载微信支付相关的`Bean`，设置`wxpay.enabled`为`false`

## 部署

部署文档存放在源码中，路径为：smartparking/src/main/resources/doc

脚本部署：

LINUX环境部署启动步骤：

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



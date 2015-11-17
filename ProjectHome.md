目标：前端基于组件的快速开发，平台基于引擎的逻辑开发，基于分享的RestApi。

# 移动互联快速开发平台 #
## 设计文档 ##

## rest-api ##
spring rest+spring data+mongodb
  1. 采用Mongodb为底层数据库：数据设计随需而变；
  1. 采用Mongodb集群，支撑大数据量，大并发实时查询，便于扩展；
  1. 采用SpringMongodb简化开发，简单得令人发指；
  1. 采用SpringRest提供JSON的输出，支持各种转换；
  1. 提供程序整合、兼容中文、跨域JSONP的支持；
  1. 进行了大数据量的压力测试，参数的最优配置；
  1. 各种最佳实践。

## client ##

### 安卓 ###
PhoneGap
### IOS ###
PhoneGap
### HTML5 ###
`快速开发的前端架构，专业化分工、快速开发、沉淀复用开发的View。`

  1. 适配主流的安卓和IOS浏览器；
  1. JQueryMobile组件构造界面；
  1. 使用模板引擎Mustache转换JSON数据，动态组合JQueryMobile组件；
  1. 使用Dreamweaver的模板和库进行HTML5代码的复用；
  1. 引入常用的图表展现工具；


## 最佳实践(todo) ##
  1. Mongodb的优化配置，Mongodb的分片集群的安装；
  1. Nginx and Keepalived 负责均衡；
  1. Tomcat优化配置，Tomcat集群共享Session MSM(memcached)配置；
  1. fastweb-mobile日志采集分析展现；
  1. Spring aop asyc jmx 等使用示例；

## 集群 ##
Tomcat优化配置，Tomcat集群共享Session MSM(memcached)配置；
  1. 启动Tomcat MSM的集成环境；
> 先手动启动mem-clu1.bat mem-clu2.bat mem-clu3.bat
    1. 启动集群
> 手动启动tomcat1.bat tomcat2.bat
    1. 测试集群Session,Session一致，集群环境OK
> http://localhost:8180/examples/jsp/sessions/my.jsp
> http://localhost:8280/examples/jsp/sessions/my.jsp
    1. 启动nginx.bat,测试返回session与tomcat的session一致
> http://localhost/examples/jsp/sessions/my.jsp
    1. 自己笔记本，6G内存，压力测试，并发3000无碍
    1. 扩充集群：
> 拷贝tomcat-2到tomcat-3,更改server.xml端口不冲突；
> 增加nginx配置
> upstream backend {
> ip\_hash;
> server 127.0.0.1:8180 weight=10;
> server 127.0.0.1:8280 weight=10;
> server 127.0.0.1:8380 weight=10;
}

## 日志采集到mongodb ##
1.设置日志格式
log\_format  json  '{'
> '"remote\_addr": "$remote\_addr",'
> '"remote\_user": "$remote\_user",'
> '"time\_local": "$time\_local",'
> '"request": "$request",'
> '"status": $status,'
> '"body\_bytes\_sent": $body\_bytes\_sent,'
> '"http\_referer": "$http\_referer",'
> '"http\_user\_agent": "$http\_user\_agent",'
> '"http\_x\_forwarded\_for": "$http\_x\_forwarded\_for"'
> '}';

2.设置输出文件名称
access\_log logs/access.log.json json;

3.导入到mongodb
more log2mongodb.sh
for i in `find /var/log/nginx -name "access.log*.gz"`
do
#echo $i |awk -F= '{print $1}'
filename=`echo $i|awk -F= '{print $1}'`
mkdir -p /var/log/nginx/done-2mongodb/
gunzip $filename
lname=${filename%.**}
echo $lname
echo 'todo import 2 mongodb '
mongoimport -d admin -c weblog -port 40000  $lname
mv  $lname  /var/log/nginx/done-2mongodb/
echo 'done $lname'
done**

#定时调度
#添加执行权限
#chmod +x log2mongodb.sh
#每天0点01分启动。
#01 00  **/路径/log2mongodb.sh**

https://code.google.com/p/fastweb-mobile/
#使用步骤

###1. 安装zookeeper
###2. mvn -install finagle-demos
###3. 启动server（Main.java）,zookeeper地址相关配置位于rpc.properties
###4. finagle-client中的HelloServiceClientTest开始通信测试
<hr>
####备注
######1.zookeeper作为服务注册中心，通信为client端与server端直接通信，如果没安装zookeeper，可以使用finagle-server中io.edr.example的两个类做测试
######2.zookeeper安装（windows）：http://blog.csdn.net/morning99/article/details/40426133
######下载：http://zookeeper.apache.org/releases.html
######修改conf下增加一个zoo.cfg并选择性修改相关设置
######3.相关资料
######https://groups.google.com/forum/#!forum/finaglers
######http://www.infoq.com/cn/news/2014/05/twitter-finagle-intro
######4.Maven相关（Twitter部分包在mvnrepository中无法找到）
######添加Twitter私库，url：http://maven.twttr.com
######若出现HTTPS证书问题[Fix https repository blocking by PKIX path building failed]，需要将Twitter证书导入到JDK中，具体可以参考如下：http://blog.csdn.net/wangjunjun2008/article/details/37662851，http://stackoverflow.com/questions/11617210/how-to-properly-import-a-selfsigned-certificate-into-java-keystore-that-is-avail/11617655#11617655


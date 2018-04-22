#Elasticsearch demo
###环境搭建（Centos）
1.安装JDK（先下载JDK 1.7以上的版本，对于2.0来说，要求JDK1.8以上）  
2.下载并解压wget tar -xvf elasticsearch-6.2.3.tar.gz  
3.执行.elasticsearch-6.2.3/elasticsearch 启动  
4.常见问题：es无法使用root启动，需要先创建新用户并为该用户分配读写权限
````
参考：Elasticsearch5.0 安装问题集锦http://www.cnblogs.com/woxpp/p/6061073.html
````
###插件安装
####中文分词插件（有版本要求）
https://github.com/medcl/elasticsearch-analysis-ik

###代码调用




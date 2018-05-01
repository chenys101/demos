#Elasticsearch demo
###环境搭建（Centos）
1.安装JDK（先下载JDK 1.7以上的版本，对于2.0来说，要求JDK1.8以上）  
2.下载并解压wget tar -xvf elasticsearch-6.2.3.tar.gz  
3.执行.elasticsearch-6.2.3/elasticsearch 启动  
4.常见问题：es无法使用root启动，需要先创建新用户并为该用户分配读写权限
````
参考：Elasticsearch5.0 安装问题集锦http://www.cnblogs.com/woxpp/p/6061073.html
````
####基础概念
````
ElasticSearch是文档型数据库
Index-->DB
Document type-->table
Document-->data
````
###插件安装
####中文分词插件（有版本要求）
https://github.com/medcl/elasticsearch-analysis-ik

###数据初始化
1.create a index  
````
curl -XPUT http://localhost:9200/index
````
2.create a mapping
````
curl -XPOST http://localhost:9200/index/fulltext/_mapping -H 'Content-Type:application/json' -d'
{
        "properties": {
            "content": {
                "type": "text",
                "analyzer": "ik_max_word",
                "search_analyzer": "ik_max_word"
            }
        }
    
}'
````
3.index some docs
````
curl -XPOST http://localhost:9200/index/fulltext/1 -H 'Content-Type:application/json' -d'
{"content":"美国留给伊拉克的是个烂摊子吗"}
'
curl -XPOST http://localhost:9200/index/fulltext/2 -H 'Content-Type:application/json' -d'
{"content":"公安部：各地校车将享最高路权"}
'
curl -XPOST http://localhost:9200/index/fulltext/3 -H 'Content-Type:application/json' -d'
{"content":"中韩渔警冲突调查：韩警平均每天扣1艘中国渔船"}
'
curl -XPOST http://localhost:9200/index/fulltext/4 -H 'Content-Type:application/json' -d'
{"content":"中国驻洛杉矶领事馆遭亚裔男子枪击 嫌犯已自首"}
'
````

####API
1.


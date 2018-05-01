package io.edr.es.factory;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

@Component
public class ElasticSearchClientFactoryBean implements FactoryBean<TransportClient>, InitializingBean,
        DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(ElasticSearchClientFactoryBean.class);

    private TransportClient transportClient;

    @Value("${elasticsearch.cluster.name:elasticsearch}")
    private String clusterName;

    @Value("${elasticsearch.cluster.hosts}")
    private String hostList;

    @Override
    public TransportClient getObject() throws Exception {
        if (transportClient == null){
            throw new IllegalStateException("初始化 ElasticSearch 客户端异常.");
        }
        return transportClient;
    }

    @Override
    public Class<?> getObjectType() {
        return TransportClient.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void destroy() throws Exception {
        transportClient.close();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (StringUtils.isEmpty(clusterName)){
            throw new IllegalStateException("ElasticSearch 集群名称为空.");
        }
        if (StringUtils.isEmpty(hostList)){
            throw new IllegalStateException("ElasticSearch 服务列表为空.");
        }
        logger.info("初始化 ElasticSearch 客户端, 集群名称 => {}",clusterName);
        logger.info("初始化 ElasticSearch 客户端, 集群列表 => {}",hostList);
        //设置集群名称
        Settings settings = Settings.builder().put("cluster.name", clusterName).build();// 集群名
        //创建client
        String[] hostAndPorts = hostList.split(",");
        Map<String,Integer> hostInfos =  parseHostAndPorts(hostAndPorts);
        transportClient = new PreBuiltTransportClient(settings);
        for (Map.Entry<String, Integer> entry : hostInfos.entrySet()) {
            transportClient.addTransportAddress(new TransportAddress(InetAddress.getByName(entry.getKey()),
                    entry.getValue()));
        }
    }

    private Map<String, Integer> parseHostAndPorts(String[] hostAndPorts) {
        Map<String,Integer> hostMap = new HashMap<>();
        if (hostAndPorts == null){
            return hostMap;
        }
        for (String hostAndPortStr : hostAndPorts) {
            String[] hostAndPort = hostAndPortStr.split(":");
            if (hostAndPort.length != 2){
                throw new IllegalStateException("配置信息不正确，请使用:符号分隔地址和端口 => " + hostAndPortStr);
            }
            if (!StringUtils.isNumeric(hostAndPort[1])){
                throw new IllegalStateException("端口号信息不正确 => " + hostAndPort[1]);
            }
            hostMap.put(hostAndPort[0],Integer.parseInt(hostAndPort[1]));
        }
        return hostMap;
    }
}

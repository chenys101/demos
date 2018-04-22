import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ESClient {

    public static void main(String[] args) {
        //设置集群名称
        Settings settings = Settings.builder().put("cluster.name", "chenys-es").build();// 集群名
        //创建client
        TransportClient client  = null;
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName("118.24.115.xx"),
                            9300));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        SearchRequestBuilder searchRequestBuilder =  client.prepareSearch("index")
                .setTypes("fulltext")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequestBuilder.setFrom(0);
        searchRequestBuilder.setSize(10);
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.multiMatchQuery("中国","content"));
        searchRequestBuilder.setQuery(queryBuilder);
        System.out.println("搜索 DSL =>"+ searchRequestBuilder.toString());

        SearchResponse response = searchRequestBuilder.get();
        long total = response.getHits().getTotalHits();
        System.out.println("搜索到符合条件的记录:"+total);
        System.out.println("命中结果 =>"+JSON.toJSONString(response.getHits()));
    }
}

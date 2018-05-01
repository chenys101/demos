package io.edr.es.service;

import com.alibaba.fastjson.JSON;
import io.edr.es.factory.ElasticSearchClientFactoryBean;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchService {

    @Autowired
    ElasticSearchClientFactoryBean elasticSearchClientFactoryBean;

    public void search(String keyWord) throws Exception {
        SearchRequestBuilder searchRequestBuilder =  elasticSearchClientFactoryBean.getObject()
                .prepareSearch("index")
                .setTypes("fulltext")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH);
        searchRequestBuilder.setFrom(0);
        searchRequestBuilder.setSize(10);
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
                .must(QueryBuilders.multiMatchQuery(keyWord,"content"));
        searchRequestBuilder.setQuery(queryBuilder);
        System.out.println("搜索 DSL =>"+ searchRequestBuilder.toString());

        SearchResponse response = searchRequestBuilder.get();
        long total = response.getHits().getTotalHits();
        System.out.println("搜索到符合条件的记录:"+total);
        System.out.println("命中结果 =>"+JSON.toJSONString(response.getHits()));
    }
}

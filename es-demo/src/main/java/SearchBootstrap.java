import io.edr.es.service.SearchService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SearchBootstrap {

    public static void main(String[] args) throws Exception {
        System.out.println("启动搜索服务...");
        ClassPathXmlApplicationContext
                context = new ClassPathXmlApplicationContext(new String[] {"spring/spring-context.xml" });
        context.start();
        System.out.println("启动搜索服务完成.");
        SearchService searchService = (SearchService)context.getBean("searchService");
        searchService.search("中国");
        Thread.sleep(3*1000);
    }
}

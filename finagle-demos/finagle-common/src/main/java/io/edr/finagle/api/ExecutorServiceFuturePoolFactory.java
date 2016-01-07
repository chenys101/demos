package io.edr.finagle.api;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;

import com.twitter.util.ExecutorServiceFuturePool;

public class ExecutorServiceFuturePoolFactory implements FactoryBean<ExecutorServiceFuturePool> {
	
	protected Logger logger = LoggerFactory.getLogger(ExecutorServiceFuturePoolFactory.class);
	
	private int poolSize = 10;
	private int maximumPoolSize = 20;
	private int queueSize = 100;
	private int keepAliveTime = 900;

	public ExecutorServiceFuturePool getObject() throws Exception {
		this.logger.info("Init FuturePool size:" + this.poolSize + " maximumPoolSize:" + this.maximumPoolSize
				+ " keepAliveTime:" + this.keepAliveTime);

		ExecutorService pool = new ThreadPoolExecutor(this.poolSize, this.maximumPoolSize, this.keepAliveTime,
				TimeUnit.SECONDS, new LinkedBlockingQueue(this.queueSize));

		ExecutorServiceFuturePool futurePool = new ExecutorServiceFuturePool(pool);
		return futurePool;
	}

	public Class<ExecutorServiceFuturePool> getObjectType() {
		return ExecutorServiceFuturePool.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public void setpoolSize(int avalue) {
		this.poolSize = avalue;
	}

	public void setMaximumPoolSize(int avalue) {
		this.maximumPoolSize = avalue;
	}

	public void setKeepAliveTime(int avalue) {
		this.keepAliveTime = avalue;
	}

	public void setQueueSize(int avalue) {
		this.queueSize = avalue;
	}
}

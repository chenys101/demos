package io.edr.finagle.api;

import java.lang.reflect.Constructor;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.twitter.common.quantity.Amount;
import com.twitter.common.quantity.Time;
import com.twitter.common.zookeeper.ZooKeeperClient;
import com.twitter.finagle.Service;
import com.twitter.finagle.builder.ClientBuilder;
import com.twitter.finagle.thrift.ThriftClientFramedCodec;
import com.twitter.finagle.thrift.ThriftClientRequest;
import com.twitter.util.Duration;

import io.edr.finagle.api.util.NetUtil;

public class FinagleClientRegister {
	private Logger logger = LoggerFactory.getLogger(FinagleClientRegister.class);
	private int connectTimeout = 5000;
	private int hostConnectionIdleTime = 600000;
	private int hostConnectionMaxIdleTime = 900000;
	private int timeout = 60000;
	private int maxRetry = 3;
	private String zookeeperAddress;
	private String namespace = "/";
	private ZooKeeperClient zooKeeperClient;
	private AtomicBoolean needInit = new AtomicBoolean(true);
	private static Map<String, ZooKeeperClient> zkClientMap = new HashMap();

	private synchronized void init() {
		if (!this.needInit.get()) {
			return;
		}
		this.logger.info("init zookeeper client.");
		if (this.zookeeperAddress == null) {
			this.logger.warn("zookeeperAddress is null.");
			return;
		}
		if (zkClientMap.get(this.zookeeperAddress) == null) {
			Set<InetSocketAddress> zkAddressSet = new HashSet();
			if (this.zookeeperAddress != null) {
				String[] address = this.zookeeperAddress.split(",");
				for (String ad : address) {
					InetSocketAddress ia = NetUtil.toAddress(ad);
					this.logger.info("zookeeper=" + ia.toString());
					zkAddressSet.add(ia);
				}
			}
			this.zooKeeperClient = new ZooKeeperClient(Amount.of(10000, Time.MILLISECONDS), zkAddressSet);
			zkClientMap.put(this.zookeeperAddress, this.zooKeeperClient);
		}

		this.needInit.compareAndSet(true, false);
	}

	public Object getService(String serviceName, String serviceClass) throws Exception {
		if (this.needInit.get()) {
			init();
		}
		Service<ThriftClientRequest, byte[]> service = ClientBuilder
				.safeBuild(ClientBuilder.get().codec(ThriftClientFramedCodec.get())
						.dest(getDest(serviceName))
						.timeout(Duration.apply(2, TimeUnit.SECONDS))
						.retries(4)
						.hostConnectionLimit(1));

		this.logger.info("Register rpc client:" + serviceName);

		Class<?> objectClass = Class.forName(serviceClass + "$ServiceToClient");
		Class<?> p1 = Class.forName(Service.class.getName());
		Class<?> p2 = Class.forName(TProtocolFactory.class.getName());
		Constructor<?> con = objectClass.getConstructor(new Class[] { p1, p2 });
		Object obj = con.newInstance(new Object[] { service, new TBinaryProtocol.Factory() });
		return obj;
	}
	
	private String getDest(String serviceName) {
		return "zk!" + zookeeperAddress + "!" + namespace + "/" + serviceName;
	}
	
	public int getConnectTimeout() {
		return connectTimeout;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public int getHostConnectionIdleTime() {
		return hostConnectionIdleTime;
	}

	public void setHostConnectionIdleTime(int hostConnectionIdleTime) {
		this.hostConnectionIdleTime = hostConnectionIdleTime;
	}

	public int getHostConnectionMaxIdleTime() {
		return hostConnectionMaxIdleTime;
	}

	public void setHostConnectionMaxIdleTime(int hostConnectionMaxIdleTime) {
		this.hostConnectionMaxIdleTime = hostConnectionMaxIdleTime;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public int getMaxRetry() {
		return maxRetry;
	}

	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}

	public String getZookeeperAddress() {
		return zookeeperAddress;
	}

	public void setZookeeperAddress(String zookeeperAddress) {
		this.zookeeperAddress = zookeeperAddress;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
}

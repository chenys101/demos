package io.edr.finagle.api;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;

import com.twitter.common.quantity.Amount;
import com.twitter.common.quantity.Time;
import com.twitter.common.zookeeper.ZooKeeperClient;
import com.twitter.finagle.ServerCodecConfig;
import com.twitter.finagle.Service;
import com.twitter.finagle.builder.Server;
import com.twitter.finagle.builder.ServerBuilder;
import com.twitter.finagle.thrift.ThriftServerFramedCodec;
import com.twitter.util.Duration;

import io.edr.finagle.api.util.FinagleRPC;
import io.edr.finagle.api.util.NetUtil;


public class FinagleServerRegister implements ApplicationContextAware, SmartLifecycle, DisposableBean, InitializingBean {
	
	protected Logger logger = LoggerFactory.getLogger(FinagleServerRegister.class);
	
	private int phase = 1;
	List<Server> servers = new ArrayList<Server>();
	private static Set<InetSocketAddress> zkAddressSet = new HashSet<InetSocketAddress>();
	private String zookeeperAddress;
	private String namespace = "/";
	private int startPort = 9090;
	private int maxConcurrentRequests = 200;
	private int hostConnectionMaxIdleTime = 900000;
	private int readTimeout = 10000;
	private int requestTimeout = 10000;
	private ApplicationContext applicationContext;
	private ZooKeeperClient zookeeperClient;
	private Map<String, Object> services = null;

	public void start() {
		if (this.services != null) {
			for (String key : this.services.keySet()) {
				Object impl = this.services.get(key);
				try {
					registerService(impl);
				} catch (Exception e) {
					this.logger.error("ERROR when register service:" + key, e);
				}
			}
		}
	}

	private void registerService(Object impl) throws ClassNotFoundException, SecurityException, NoSuchMethodException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		String clName = impl.getClass().getName();
		Class<?>[] processor1 = null;
		if (clName.indexOf("$") != -1) {
			processor1 = Class.forName(clName.substring(0, clName.indexOf("$"))).getInterfaces();
		} else {
			processor1 = Class.forName(clName).getInterfaces();
		}
		for (Class<?> iface : processor1) {
			String strIface = iface.getName();
			if (strIface.endsWith("$ServiceIface")) {
				String processName = strIface.substring(0, strIface.indexOf("$ServiceIface"));
				Class<?> Processor = Class.forName(processName + "$Service");
				Class<?> p1 = Class.forName(processName + "$ServiceIface");
				Class<?> p2 = Class.forName(TProtocolFactory.class.getName());
				Constructor<?> con = Processor.getConstructor(new Class[] { p1, p2 });

				Service<byte[], byte[]> service = (Service) con.newInstance(new Object[] { 
						impl, new TBinaryProtocol.Factory() });

				int port = NetUtil.getAvailablePort(this.startPort);
				String serviceName = processName.substring(processName.lastIndexOf(".") + 1);
				serviceName = StringUtils.uncapitalize(serviceName);
				InetSocketAddress endpoint = NetUtil.getLocalSocketAddress(NetUtil.getLocalHost(), port);
				ServerCodecConfig conf = new ServerCodecConfig(serviceName, endpoint);

				Server server = ServerBuilder.safeBuild(service,
						ServerBuilder.get().codec(new ThriftServerFramedCodec(conf, new TBinaryProtocol.Factory()))
								.maxConcurrentRequests(this.maxConcurrentRequests)
								.hostConnectionMaxIdleTime(Duration.fromMilliseconds(this.hostConnectionMaxIdleTime))
								.readTimeout(Duration.fromMilliseconds(this.readTimeout))
								.requestTimeout(Duration.fromMilliseconds(this.requestTimeout))
								.bindTo(endpoint)
								.name("finagle server")
								.keepAlive(true));
				server.announce("zk!" + this.zookeeperAddress + "!" + this.namespace+ "/" + serviceName + "!0");
				
				this.servers.add(server);
				this.logger.info("init Rpc server:" + processName + " zk:" + this.namespace + "/" + serviceName
						+ " endpoint:" + endpoint);
			}
		}
	}

	public void stop() {
		this.zookeeperClient.close();
	}

	public boolean isRunning() {
		return false;
	}

	public int getPhase() {
		return this.phase;
	}

	public void setPhase(int avalue) {
		this.phase = avalue;
	}

	public void afterPropertiesSet() throws Exception {
		this.logger.info("Init thrift server.");
		this.services = this.applicationContext.getBeansWithAnnotation(FinagleRPC.class);
		if (this.zookeeperAddress != null) {
			String[] address = this.zookeeperAddress.split(",");
			for (String ad : address) {
				InetSocketAddress ia = NetUtil.toAddress(ad);
				this.logger.info("zookeeper=" + ia.toString());
				zkAddressSet.add(ia);
			}
		}
		this.zookeeperClient = new ZooKeeperClient(Amount.of(10000, Time.MILLISECONDS), zkAddressSet);
	}

	public void destroy() throws Exception {
		for (Server s : this.servers) {
			s.close();
		}
		this.zookeeperClient.close();
	}

	public boolean isAutoStartup() {
		return true;
	}

	public void stop(Runnable callback) {
	}

	public void setApplicationContext(ApplicationContext avalue) throws BeansException {
		this.applicationContext = avalue;
	}

	public void setStartPort(int port) {
		this.startPort = port;
	}

	public void setZookeeperAddress(String avalue) {
		this.zookeeperAddress = avalue;
	}

	public void setnamespace(String avalue) {
		this.namespace = avalue;
	}

	public void setMaxConcurrentRequests(int avalue) {
		this.maxConcurrentRequests = avalue;
	}

	public void setHostConnectionMaxIdleTime(int avalue) {
		this.hostConnectionMaxIdleTime = avalue;
	}

	public void setReadTimeout(int avalue) {
		this.readTimeout = avalue;
	}

	public void setRequestTimeout(int avalue) {
		this.requestTimeout = avalue;
	}
}

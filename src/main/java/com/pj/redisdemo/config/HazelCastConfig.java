package com.pj.redisdemo.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.NetworkConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelCastConfig
{
	@Bean
	public Config config()
	{
		NetworkConfig networkConfig = new NetworkConfig();
		networkConfig.setPublicAddress("192.168.1.157").addOutboundPort(9701);
		networkConfig.setPublicAddress("192.168.1.157").addOutboundPort(9702);
		Config config = new Config();
		config.setNetworkConfig(networkConfig);
		return config;
	}
}

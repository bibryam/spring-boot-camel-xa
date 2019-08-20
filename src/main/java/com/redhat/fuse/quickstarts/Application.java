/*
 * Copyright (C) 2018 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.redhat.fuse.quickstarts;

import org.apache.camel.component.jms.JmsComponent;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.jms.ConnectionFactory;
import org.apache.activemq.artemis.jms.client.ActiveMQXAConnectionFactory;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

//    @Bean(name = "jms-component")
//    public JmsComponent jmsComponent(ConnectionFactory xaJmsConnectionFactory, PlatformTransactionManager jtaTransactionManager) {
//	    JmsComponent jms = new JmsComponent();
//	    jms.setConnectionFactory(xaJmsConnectionFactory);
//	    jms.setTransactionManager(jtaTransactionManager);
//	    jms.setTransacted(true);
//
//	    return jms;
//    }

	@Bean(name = "jms-component")
	public JmsComponent jmsComponent(PlatformTransactionManager jtaTransactionManager) {
		ConnectionFactory xaJmsConnectionFactory = new ActiveMQXAConnectionFactory("tcp://broker-amq-tcp:61616", "theuser", "Thepassword1!");

		JmsComponent jms = new JmsComponent();
		jms.setConnectionFactory(xaJmsConnectionFactory);
		jms.setTransactionManager(jtaTransactionManager);
		jms.setTransacted(true);

		return jms;
	}


}

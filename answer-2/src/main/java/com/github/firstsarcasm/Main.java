package com.github.firstsarcasm;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultRegistry;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.log4j.BasicConfigurator;

import javax.sql.DataSource;

/**
 * The answer to stackoverflow.com question:
 * https://stackoverflow.com/questions/68959547/how-to-safely-generate-sql-for-camel-jdbc-component
 */
public class Main {
	public static void main(String[] args) throws Exception {
		BasicConfigurator.configure();
		DefaultCamelContext camelContext = new DefaultCamelContext();

		RouteBuilder route = configureRoute();
		DataSource dataSource = setupDataSource();
		DefaultRegistry registry = (DefaultRegistry) camelContext.getRegistry();
		final ProducerTemplate template = camelContext.createProducerTemplate();

		registry.bind("h2DataSource", dataSource);
		camelContext.setRegistry(registry);
		camelContext.addRoutes(route);

		camelContext.start();
		insertData(template);
		template.sendBody("direct:select", "");
		camelContext.stop();
	}

	private static RouteBuilder configureRoute() {
		return new RouteBuilder() {
			public void configure() {
				from("direct:insert")
						.to("jdbc:h2DataSource?useHeadersAsParameters=true")
						.log("result = ${body}");

				from("direct:select")
						.setHeader("lic", constant("ASF"))
						.setHeader("min", constant(123))
						.setBody(simple("select * from projects where license = :?lic and id >= :?min order by id"))
						.to("jdbc:h2DataSource?useHeadersAsParameters=true")
						.log("select result - ${body}");
			}
		};
	}

	private static void insertData(ProducerTemplate template) {
		template.sendBody("direct:insert", "DROP TABLE IF EXISTS PROJECTS;");
		template.sendBody("direct:insert", """
				CREATE TABLE if not exists PROJECTS
				(
				    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
				    license  varchar2 NOT NULL
				);
				""");
		template.sendBody("direct:insert", "INSERT INTO projects (id, license) VALUES (123, 'ASF');");

	}

	private static DataSource setupDataSource() {
		String connectURI = "jdbc:h2:file:./data/db";
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("org.h2.Driver");
		ds.setUsername("username");
		ds.setPassword("password");
		ds.setUrl(connectURI);
		return ds;
	}
}

/**
 * Configuration class for GraphQL Scalars.
 */
package com.org.graphql.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Configuration
public class GraphQLScalarsConfig {
	
	@Bean
	public GraphQLScalarType object() {
		
		return ExtendedScalars.Object;
	}

}
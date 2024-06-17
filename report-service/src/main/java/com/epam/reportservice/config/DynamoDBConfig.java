//package com.epam.reportservice.config;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSCredentialsProvider;
//import com.amazonaws.auth.AWSStaticCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.client.builder.AwsClientBuilder;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
//import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//
//
//@Configuration
//@EnableDynamoDBRepositories(basePackages = "com.epam.reportservice.repository")
//public class DynamoDBConfig {
//    @Value("${amazon.dynamodb.endpoint}")
//    private String amazonDynamoDBEndpoint;
//    @Value("${aws.access.key}")
//    private String accessKey;
//    @Value("${aws.secret.key}")
//    private String secretKey;
//    @Value("${amazon.aws.region}")
//    private String region;
//
//    private AWSCredentialsProvider awsDynamoDBCredentials() {
//        return new AWSStaticCredentialsProvider(
//                new BasicAWSCredentials(accessKey, secretKey));
//    }
//
//    @Primary
//    @Bean
//    public DynamoDBMapperConfig dynamoDBMapperConfig() {
//        return DynamoDBMapperConfig.DEFAULT;
//    }
//
//    @Bean
//    @Primary
//    public DynamoDBMapper dynamoDBMapper(AmazonDynamoDB amazonDynamoDB,
//                                         DynamoDBMapperConfig config) {
//        return new DynamoDBMapper(amazonDynamoDB, config);
//    }
//
//    @Bean
//    public AmazonDynamoDB amazonDynamoDB() {
//
//        return AmazonDynamoDBClientBuilder.standard()
//                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(amazonDynamoDBEndpoint, region))
//                .withCredentials(awsDynamoDBCredentials()).build();
//    }
//}

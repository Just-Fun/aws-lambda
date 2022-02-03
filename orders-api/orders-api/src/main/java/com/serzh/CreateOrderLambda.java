package com.serzh;


import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serzh.dto.Order;

public class CreateOrderLambda {

    //    @Value("${aws.apiKey:}")
    private String key;
    //    @Value("${aws.apiSecret:}")
    private String secret;

    //    TODO use Dagger
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();
    /*private final AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
        .withCredentials(new AWSStaticCredentialsProvider(getCredentials()))
        .build();*/
    private final DynamoDB dynamoDB = new DynamoDB(client);

    public APIGatewayProxyResponseEvent createOrder(APIGatewayProxyRequestEvent request) throws JsonProcessingException {

        Order order = objectMapper.readValue(request.getBody(), Order.class);

        Table table = dynamoDB.getTable(System.getenv("ORDERS_TABLE"));
        Item item = new Item().withPrimaryKey("id", order.getId())
                .withString("itemName", order.getItemName())
                .withInt("quantity", order.getQuantity());
        table.putItem(item);

        return new APIGatewayProxyResponseEvent().withStatusCode(200).withBody("Order ID: " + order.getId());
    }

    private AWSCredentials getCredentials() {
        return new AWSCredentials() {
            @Override
            public String getAWSAccessKeyId() {
                return key;
            }

            @Override
            public String getAWSSecretKey() {
                return secret;
            }
        };
    }

}

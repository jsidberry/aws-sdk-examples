import com.example.gateway.*;
import software.amazon.awssdk.services.apigateway.ApiGatewayClient;
import org.junit.jupiter.api.*;
import software.amazon.awssdk.regions.Region;
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_METHOD)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class APIGatewayTest {

    private static ApiGatewayClient apiGateway;
    private static String restApiId = "";
    private static String resourceId = "";
    private static String httpMethod = "";
    private static String restApiName = "";
    private static String stageName = "";
    private static String newApiId = ""; // Gets dynamically set
    private static String deploymentId = "";  // Gets dynamically set


    @BeforeAll
    public static void setUp() throws IOException {

        // Run tests on Real AWS Resources
        Region region = Region.US_EAST_1;
        apiGateway = ApiGatewayClient.builder().region(region).build();
        try (InputStream input = APIGatewayTest.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            // Populate the data members required for all tests
            restApiId = prop.getProperty("restApiId");
            resourceId = prop.getProperty("resourceId");
            httpMethod = prop.getProperty("httpMethod");
            restApiName = prop.getProperty("restApiName");
            stageName = prop.getProperty("stageName");

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    @Order(1)
    public void whenInitializingAWSService_thenNotNull() {
        assertNotNull(apiGateway);
        System.out.println("Test 1 passed");
    }

    @Test
    @Order(2)
    public void CreateRestApi() {

        newApiId = CreateRestApi.createAPI(apiGateway, restApiId, restApiName);
        assertTrue(!newApiId.isEmpty());
        System.out.println("Test 2 passed");
    }

    @Test
    @Order(3)
    public void CreateDeployment() {
        deploymentId = CreateDeployment.createNewDeployment(apiGateway, newApiId, stageName);
        assertTrue(!deploymentId.isEmpty());
        System.out.println("Test 3 passed");
    }

    @Test
    @Order(4)
    public void GetDeployments() {
        GetDeployments.getAllDeployments(apiGateway, newApiId);
        System.out.println("Test 4 passed");
    }

    @Test
    @Order(6)
    public void GetMethod() {
        GetMethod.getSpecificMethod(apiGateway, restApiId, resourceId, httpMethod);
        System.out.println("Test 5 passed");
    }

    @Test
    @Order(7)
    public void GetStages() {

        GetStages.getAllStages(apiGateway, newApiId);
        System.out.println("Test 7 passed");
    }


    @Test
    @Order(8)
    public void DeleteRestApi() {

        Region region = Region.US_EAST_1;
        ApiGatewayClient apiGateway2 = ApiGatewayClient.builder()
                .region(region)
                .build();

        DeleteRestApi.deleteAPI(apiGateway2, newApiId);
        System.out.println("Test 9 passed");
    }
}

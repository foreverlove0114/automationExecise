package utilities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Utility class to provide test data from JSON files
 * Uses Jackson library for JSON parsing
 */
public class JsonDataProvider {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Generic method to read JSON file and return array of specified class type
     *
     * @param jsonFilePath Path to JSON file in resources
     * @param clazz        Class type to map JSON to
     * @param <T>          Generic type
     * @return Array of objects
     */
    public static <T> T[] getTestData(String jsonFilePath, Class<T> clazz) {
        try {
            InputStream inputStream = JsonDataProvider.class.getClassLoader()
                    .getResourceAsStream(jsonFilePath);

            if (inputStream == null) {
                throw new RuntimeException("Test data file not found: " + jsonFilePath);
            }

            JsonNode rootNode = objectMapper.readTree(inputStream);
            JsonNode testCasesNode = rootNode.get("testCases");

            if (testCasesNode == null || !testCasesNode.isArray()) {
                throw new RuntimeException("Invalid JSON format: 'testCases' array not found");
            }

            List<T> testDataList = new ArrayList<>();
            for (JsonNode node : testCasesNode) {
                T data = objectMapper.treeToValue(node, clazz);
                testDataList.add(data);
            }

            @SuppressWarnings("unchecked")
            T[] array = (T[]) java.lang.reflect.Array.newInstance(clazz, testDataList.size());
            return testDataList.toArray(array);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load test data from: " + jsonFilePath, e);
        }
    }

    /**
     * DataProvider for registration test data
     */
    @DataProvider(name = "registrationDataProvider")
    public static Iterator<Object[]> registrationDataProvider() {
        return createDataProviderIterator("testdata/registration_data.json", 
                datamodels.RegistrationData.class);
    }

    /**
     * DataProvider for login test data
     */
    @DataProvider(name = "loginDataProvider")
    public static Iterator<Object[]> loginDataProvider() {
        return createDataProviderIterator("testdata/login_data.json", 
                datamodels.LoginData.class);
    }

    /**
     * DataProvider for contact us test data
     */
    @DataProvider(name = "contactUsDataProvider")
    public static Iterator<Object[]> contactUsDataProvider() {
        return createDataProviderIterator("testdata/contact_us_data.json", 
                datamodels.ContactUsData.class);
    }

    /**
     * Generic helper to create DataProvider iterator
     */
    private static <T> Iterator<Object[]> createDataProviderIterator(String jsonPath, Class<T> clazz) {
        T[] dataArray = getTestData(jsonPath, clazz);
        List<Object[]> testData = new ArrayList<>();
        
        for (T data : dataArray) {
            testData.add(new Object[]{data});
        }
        
        return testData.iterator();
    }

    /**
     * Filter test data by expected result
     *
     * @param dataArray      Original data array
     * @param expectedResult Expected result to filter by ("success" or "failure")
     * @param <T>            Generic type with getExpectedResult method
     * @return Filtered array
     */
    public static <T> List<T> filterByExpectedResult(T[] dataArray, String expectedResult) {
        List<T> filtered = new ArrayList<>();
        for (T data : dataArray) {
            try {
                String result = (String) data.getClass().getMethod("getExpectedResult").invoke(data);
                if (expectedResult.equals(result)) {
                    filtered.add(data);
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed to filter test data", e);
            }
        }
        return filtered;
    }
}

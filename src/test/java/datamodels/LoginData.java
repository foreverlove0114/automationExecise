package datamodels;

/**
 * Data model for login test data
 */
public class LoginData {
    private String testName;
    private String email;
    private String password;
    private String expectedResult;
    private String description;

    // Getters and Setters
    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "testName='" + testName + '\'' +
                ", email='" + email + '\'' +
                ", expectedResult='" + expectedResult + '\'' +
                '}';
    }
}

# franchiseEntity-network-management

API for Franchise Network Management Services

### Operations

- Create a franchise
- Add a branch to a franchise
- Add and remove products from a branch
- Modify the stock of a product in a branch
- Get the top product of a branch

### Diagram de Operaciones

![franchises-network.drawio.png](franchises-network.drawio.png)

### How to run the project

1. Clone the repository
2. Navigate to the project directory
3. Run the following command to start the application:
   ```
   ./gradlew bootRun
   ```
4. The application will start on port 8080 by default
5. You can access the API documentation at `http://localhost:8080/webjars/swagger-ui/index.html`


### API Documentation

The API documentation is available at the following file path: ***swagger.json***

### Postman Collection

The Postman collection is available at the following file path: ***franchise.postman_collection.json***

### Test Cases

The test cases are located in the `src/test/java/com/network/franchise/...` directory. The test cases are written using JUnit 5 and can be run using the following command:
```
./gradlew test
```

### Code Coverage

The code coverage report is generated using JaCoCo and can be found in the `build/reports/tests/test/index.html` directory. You can open the `index.html` file in your web browser to view the code coverage report.
```
./gradlew clean test jacocoTestReport
```

### Author

- **[Raul Bolivar Navas](https://www.linkedin.com/in/rasysbox)** - [GitHub](https://github.com/raulrobinson/franchise-network-management)

### License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

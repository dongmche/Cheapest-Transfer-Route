
## Build and Run Instructions

To build and run the project, follow these steps:

### 1. Build the Project
1. Navigate to the project's root directory.
2. Run the following Maven command to clean and package the application:
   ```bash
   mvn clean package
   ```
3. Once the build is complete, you will find the JAR file in the `target` directory:
   ```plaintext
   target/MaxWeightAssignment-0.0.1-SNAPSHOT.jar
   ```

### 2. Run the Application
To run the application, execute the following command in the terminal:
```bash
java -jar target/MaxWeightAssignment-0.0.1-SNAPSHOT.jar
```

---

## Example cURL Requests and Responses

### Request Example
Use the following cURL command to send a POST request to the API:
```bash
curl -X POST http://localhost:8080/api/transfers/calculate \
-H "Content-Type: application/json" \
-d '{
  "transfers": [
    { "weight": 5, "cost": 10 },
    { "weight": 10, "cost": 20 },
    { "weight": 20, "cost": 50 }
  ],
  "maxWeight": 15
}'
```

### Request Body
The input JSON structure for the API:
```json
{
  "transfers": [
    { "weight": 5, "cost": 10 },
    { "weight": 10, "cost": 20 },
    { "weight": 20, "cost": 50 }
  ],
  "maxWeight": 15
}
```

### Response Example
The server processes the input and returns the optimized path for the given constraints:
```json
{
  "selectedTransfers": [
    {
      "weight": 5,
      "cost": 10
    },
    {
      "weight": 10,
      "cost": 20
    }
  ],
  "totalCost": 30,
  "totalWeight": 15
}
```

---

## Explanation of the Response
- **`selectedTransfers`**: A list of transfers that fit within the specified `maxWeight` constraint.
  - Each object includes:
    - **`weight`**: The weight of the transfer.
    - **`cost`**: The cost associated with the transfer.
- **`totalCost`**: The total cost of the selected transfers.
- **`totalWeight`**: The combined weight of the selected transfers.

---

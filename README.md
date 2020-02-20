# account-transfers

(c) 2020 Erik Giron

## Description

Microservice to make money transfers between accounts using Helidon + Jakarta EE. Used only for educational and training purposes

## Build and run

With JDK11+
```bash
cd account-transfers
mvn clean package
mvn flyway:migrate
java -jar target/account-transfers.jar
```


## Usage

Application runs in port 8080 by default at http://localhost:8080

### Endpoints

We expose the following endpoints:

#### accounts

* GET /v1/transfers/account/{id} : Get the account stored with ID


#### transfers

* POST /v1/transfers/transfer : make a transfer between accounts specified in the payload:


More information about the endpoints can be found in openapi at http://localhost:8080/openapi (Swagger yet to be implemented)


### Example

* *GIVEN* the following two sample accounts in the default database migration:

```bash
curl --location --request GET 'http://localhost:8080/v1/transfers/account/1'
```
```json
{
    "balance": 50.00,
    "id": 1,
    "name": "John Smith",
    "number": "11111111"
}
```
```bash
curl --location --request GET 'http://localhost:8080/v1/transfers/account/2'
```
```json
{
    "balance": 100.00,
    "id": 2,
    "name": "Peter Johnson",
    "number": "22222222"
}
```
* *WHEN* we make a transfer of 10 units from account 1 identified with account number "11111111" to account 2 identified with account number "22222222" 
```bash
curl --location --request POST 'http://localhost:8080/v1/transfers/transfer' \
--header 'Content-Type: application/json' \
--data-raw '{
	"origin" : {
		"number" : "11111111"
	},
	"destination" : {
		"number" : "22222222"
	},
	"amount" : 10.0
}'
```
* *THEN* we should debit 10 from from acccount 1 balance, and credit 10 to the account 2 balance, and the transfer should be in status COMPLETED

```json
{
    "amount": 10.0,
    "destination": {
        "balance": 110.00,
        "id": 2,
        "name": "Peter Johnson",
        "number": "22222222"
    },
    "id": 0,
    "origin": {
        "balance": 40.00,
        "id": 1,
        "name": "John Smith",
        "number": "11111111"
    },
    "status": {
        "status": "COMPLETED"
    }
}
```


### Open API
We expose an open-api OAI at http://localhost:8080/openapi for endpoint information

### health and metrics

```
curl -s -X GET http://localhost:8080/health
{"outcome":"UP",...
. . .

# Prometheus Format
curl -s -X GET http://localhost:8080/metrics
# TYPE base:gc_g1_young_generation_count gauge
. . .

# JSON Format
curl -H 'Accept: application/json' -X GET http://localhost:8080/metrics
{"base":...
. . .

```

## Container Support (WIP)

```
docker build -t account-transfers .
```

### Start the application with Docker

```
docker run --rm -p 8080:8080 account-transfers:latest
```

### Deploy the application to Kubernetes

```
kubectl cluster-info                         # Verify which cluster
kubectl get pods                             # Verify connectivity to cluster
kubectl create -f app.yaml               # Deploy application
kubectl get service account-transfers  # Verify deployed service
```



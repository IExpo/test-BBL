# test-BBL

test Spring Boot + H2 demo

## Build & Run
```bash
docker build -t test-bbl:local .
docker run -p 8080:8080 test-bbl:local
```

## Test
```bash
curl http://localhost:8080/users
```
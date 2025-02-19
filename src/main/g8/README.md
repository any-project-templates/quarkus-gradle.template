$application$

# Build & Run
```shell
make clean
make docker-build

docker run --rm -p 9090:9090 $organization$/$name$:latest
# or using compose
docker-compose up -d
```

# Api
- http://localhost:9090/docs/
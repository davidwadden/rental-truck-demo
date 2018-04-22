# Rental Trucks Event-Driven Architecture example


## Setup

```bash
$ brew install kafka
$ brew install cassandra
```

### Run using Homebrew services
```bash
$ brew services start kafka
$ brew services start cassandra
```

> Some people have had issues getting Homebrew Cassandra working, follow Docker instructions 
    alternatively: (don't forget to `brew services stop {cassandra|kafka}`)

### Run using Docker
```bash
$ docker run -d --name kafka -p 2181:2181 -p 9092:9092 --env ADVERTISED_HOST=localhost --env ADVERTISED_PORT=9092 spotify/kafka
$ docker run -d --name cassandra -p 9042:9042 cassandra:latest
```

## Provision keyspace
```bash
$ cqlsh -f cql/create-keyspace.cql
```

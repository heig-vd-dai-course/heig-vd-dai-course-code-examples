# whoami with Traefik `PathPrefix` rule

This [whoami](https://github.com/traefik/whoami) example shows how to access the
whoami server with Traefik
[`PathPrefix`](https://doc.traefik.io/traefik/routing/routers/#rule) rule
locally with Docker and Docker Compose.

## Prerequisites

The [`traefik-insecure`](../traefik-insecure/README.md) example must be running.

## Run the example

Start the containers:

```sh
# Start the containers
docker-compose up
```

Open your browser and go to <http://localhost/whoami> to access whoami.

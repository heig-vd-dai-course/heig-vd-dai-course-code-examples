# whoami with Traefik `PathPrefix` rule and `StripPrefix` middleware

This [whoami](https://github.com/traefik/whoami) example shows how to access the
whoami server with Traefik
[`PathPrefix`](https://doc.traefik.io/traefik/routing/routers/#rule) rule and
[`StripPrefix`](https://doc.traefik.io/traefik/middlewares/http/stripprefix/)
middleware locally with Docker and Docker Compose.

## Prerequisites

The [`traefik-insecure`](../traefik-insecure/README.md) example must be running.

## Run the example

Start the containers:

```sh
# Start the containers
docker compose up
```

Open your browser and go to <http://localhost/whoami-without-stripprefix> to
access whoami without the `StripPrefix` middleware.

Open your browser and go to <http://localhost/whoami-with-stripprefix> to access
whoami with the `StripPrefix` middleware.

# whoami with Traefik `Host` and `PathPrefix` rules

This [whoami](https://github.com/traefik/whoami) example shows how to access the
whoami server with Traefik
[`Host`](https://doc.traefik.io/traefik/routing/routers/#rule) and
[`PathPrefix`](https://doc.traefik.io/traefik/routing/routers/#rule) rules
locally with Docker and Docker Compose.

## Prerequisites

The [`traefik-insecure`](../traefik-insecure/README.md) example must be running.

You must add the `whoami.localhost` hostname to your `/etc/hosts` file.

You can change the fully qualified domain name of the whoami service in the
[`.env`](.env) file if needed. If you do so, do not forget to update your
`/etc/hosts` file accordingly.

## Run the example

Start the containers:

```sh
# Start the containers
docker-compose up
```

Open your browser and go to <http://whoami.localhost/whoami> to access whoami.

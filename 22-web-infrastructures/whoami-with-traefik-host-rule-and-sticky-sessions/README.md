# whoami with Traefik `Host` rule and sticky sessions

This [whoami](https://github.com/traefik/whoami) example shows how to access the
whoami server with Traefik
[`Host`](https://doc.traefik.io/traefik/routing/routers/#rule) rule and
[sticky sessions](https://doc.traefik.io/traefik/routing/services/#sticky-sessions)
locally with Docker and Docker Compose.

## Prerequisites

The [`traefik-insecure`](../traefik-insecure/README.md) example must be running.

You must add the `whoami1.localhost` and `whoami2.localhost` hostnames to your
`/etc/hosts` file.

You can change the fully qualified domain names of the whoami services in the
[`.env`](.env) file if needed. If you do so, do not forget to update your
`/etc/hosts` file accordingly.

## Run the example

Start the containers with 3 replicas of the `whoami-without-sticky-sessions`
service and 3 replicas of the `whoami-with-sticky-sessions` service:

```sh
# Start the containers
docker compose up --scale whoami-without-sticky-sessions=3 --scale whoami
-with-sticky-sessions=3
```

Open your browser and go to <http://whoami1.localhost> to access whoami.

Refresh the page several times. You should notice the request being served by
different containers in a round-robin fashion (one request per container).

Access a new tab in your browser and go to <http://whoami2.localhost> to access
whoami.

Refresh the page several times. You should see the same container ID each time.

Open the developer tools of your browser and go to the "Application" or
"Storage" tab. You should see a cookie named `whoami` with its value.

If you delete the cookie, you will see a different container ID.

The cookie is sent to the server with each request. This is how Traefik knows
which container to send the request to.

You can try the same thing with a private window in your browser.

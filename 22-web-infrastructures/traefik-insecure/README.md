# Traefik insecure

This [Traefik](https://traefik.io/traefik/) example shows how to use Traefik
locally with Docker and Docker Compose.

This configuration is **insecure** and therefore **not suitable for
production**.

## Run the example

### Access the Traefik dashboard

Start the containers:

```sh
# Start the containers
docker-compose up
```

Open your browser and go to <http://localhost:8080> to access the Traefik
dashboard.

### Add the Traefik service fully qualified domain name to your `/etc/hosts` file

Add the
[fully qualified domain name (FQDN)](https://en.wikipedia.org/wiki/Fully_qualified_domain_name)
of the Traefik service to your `/etc/hosts` file:

```text
127.0.0.1 traefik.localhost
```

### Access the Traefik dashboard with its fully qualified domain name

Open your browser and go to <traefik.localhost> to access the Traefik dashboard
with its fully qualified domain name.

You can change the fully qualified domain name of the Traefik service in the
[`.env`](.env) file if needed. If you do so, do not forget to update your
`/etc/hosts` file accordingly.

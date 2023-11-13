# whoami with Traefik secure

This [whoami](https://github.com/traefik/whoami) example shows how to access the
whoami server with Traefik
[`Host`](https://doc.traefik.io/traefik/routing/routers/#rule) rule on the
virtual machine with Docker and Docker Compose.

## Prerequisites

The [`traefik-secure`](../traefik-secure/README.md) example must be running.

### Update the `.env` file

Update the [`.env`](.env) file with your own values:

- `WHOAMI_FULLY_QUALIFIED_DOMAIN_NAME`: the fully qualified domain name to
  access whoami

### Add the whoami service fully qualified domain name to DNS zone

Add an `A` record to the DNS zone of your domain name provider to point the
fully qualified domain name of the whoami service to the IP address of the
virtual machine.

### Test the DNS resolution

Test the DNS resolution of the fully qualified domain name of the Traefik
service:

```sh
# Test the DNS resolution
nslookup WHOAMI_FULLY_QUALIFIED_DOMAIN_NAME
```

You might have to wait a few minutes for the DNS record to be propagated.

## Run the example

### Access the whoami dashboard

Start the containers on the virtual machine:

```sh
# Start the containers
docker-compose up -d
```

Open your browser and go to the fully qualified domain name of the who service
you defined in the `.env` file with the `http` protocol, for example
`http://WHOAMI_FULLY_QUALIFIED_DOMAIN_NAME`.

You should notice that the request is automatically redirected to the `https`
protocol. This is because the whoami container uses the `http-to-https-redirect`
middleware.

Depending on the time it takes for the certificate to be generated, you might
see a warning about the certificate not being valid. If this happens, wait a few
minutes and refresh the page.

The page should now be secured with a valid certificate and a lock icon should
be displayed in the address bar.

You should now be able to access whoami with a valid HTTPS certificate.

# Traefik secure

This [Traefik](https://traefik.io/traefik/) example shows how to use Traefik on
the virtual machine with Docker and Docker Compose.

## Prerequisites

### Have a domain name ready

You must have a domain name ready.

### Update the `.env` file

Update the [`.env`](.env) file with your own values:

- `TRAEFIK_ACME_EMAIL`: your email address
- `TRAEFIK_FULLY_QUALIFIED_DOMAIN_NAME`: the fully qualified domain name to
  access Traefik

### Add the Traefik service fully qualified domain name to DNS zone

Add an `A` record to the DNS zone of your domain name provider to point the
fully qualified domain name of the Traefik service to the IP address of the
virtual machine.

### Test the DNS resolution

Test the DNS resolution of the fully qualified domain name of the Traefik
service:

```sh
# Test the DNS resolution
nslookup TRAEFIK_FULLY_QUALIFIED_DOMAIN_NAME
```

You might have to wait a few minutes for the DNS record to be propagated.

### Create the users file

The Traefik dashboard is protected with a
[`BasicAuth`](https://doc.traefik.io/traefik/middlewares/http/basicauth/)
middleware (more information about basic authentication on Wikipedia at
<https://en.wikipedia.org/wiki/Basic_access_authentication>). You must create a
`auth-users.txt` file with the username and password of the users allowed to
access the Traefik dashboard.

Docker Compose allows the use of
[Docker Secrets](https://docs.docker.com/compose/use-secrets/) to pass sensitive
information to a service.

The [`docker-compose.yml`](./docker-compose.yml) file is already configured to
use the `secrets/auth-users.txt` file as a Docker Secret. You must create the
`auth-users.txt` file in the `secrets` directory.

To create the `auth-users.txt` file, you can use the
[`htpasswd`](https://httpd.apache.org/docs/2.4/programs/htpasswd.html) command
line tool. For example, to create a user named `admin` with the password
`admin`, you can run the following command:

```sh
# Create the secrets directory
mkdir secrets

# Create the auth-users.txt file
htpasswd -c secrets/auth-users.txt admin
```

`htpasswd` will ask you to enter the password for the user. You can add more
users to the `auth-users.txt` file by running the same command without the `-c`
option.

The `auth-users.txt` file must be created before starting the containers.

## Run the example

### Access the Traefik dashboard

Start the containers on the virtual machine:

```sh
# Start the containers
docker compose up -d
```

Open your browser and go to the fully qualified domain name of the Traefik
service you defined in the `.env` file with the `http` protocol, for example
`http://TRAEFIK_FULLY_QUALIFIED_DOMAIN_NAME`.

You should notice that the request is automatically redirected to the `https`
protocol. This is because the Traefik container uses the
[`http-to-https-redirect`](https://doc.traefik.io/traefik/middlewares/http/redirectscheme/)
middleware.

Depending on the time it takes for the certificate to be generated, you might
see a warning about the certificate not being valid. If this happens, wait a few
minutes and refresh the page.

The page should now be secured with a valid certificate and a lock icon should
be displayed in the address bar.

A username and password prompt should appear thanks to the `BasicAuth`
middleware. Enter the username and password you defined in the `auth-users.txt`
file.

You should now be able to access the Traefik dashboard with a valid HTTPS
certificate and in a secure way with the help of the `BasicAuth` middleware.

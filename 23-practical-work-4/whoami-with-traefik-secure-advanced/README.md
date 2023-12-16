# whoami with Traefik secure (advanced)

## Disclaimer

This configuration was meant to be used in my (Ludovic) personal homelab and I
wanted to share my findings with you if you are interested in doing something
similar.

Therefore, this example is **optional** to follow. It is here to show you how to
use Traefik in a more advanced way and for your own culture.

For more information about my modest setup, please refer to the
[Personal notes](../traefik-secure-advanced/README.md#personal-notes) section.

## Introduction

This configuration is an improvement of the
[`whoami-with-traefik-secure`](../whoami-with-traefik-secure/README.md) example.
It adds the following features:

- New services can be added without having to generate a new certificate and
  updating the DNS zone
- Much lighter configuration files as we do not need to define the
  `http-to-https-redirect` middleware anymore nor any configuration for the
  `http` entrypoint

## Prerequisites

The [`traefik-secure-advanced`](../traefik-secure-advanced/README.md) example
must be running.

### Update the `.env` file

Update the [`.env`](.env) file with your own values:

- `WHOAMI_FULLY_QUALIFIED_DOMAIN_NAME`: the fully qualified domain name to
  access whoami

## Run the example

### Access the whoami dashboard

Start the containers on the virtual machine:

```sh
# Start the containers
docker compose up -d
```

Open your browser and go to the fully qualified domain name of the who service
you defined in the `.env` file with the `http` protocol, for example
`http://WHOAMI_FULLY_QUALIFIED_DOMAIN_NAME`.

You should notice that the request is automatically redirected to the `https`
protocol. This is because the all containers have HTTP to HTTPS redirection
enabled at the entrypoint level.

The page should now be secured with a valid certificate and a lock icon should
be displayed in the address bar.

You should now be able to access whoami with a valid HTTPS certificate.

## Advantages of this configuration

The advantages of this configuration reside in the configuration of the Docker
Compose files and how versatile you can add new services.

If you compare both solutions, you will notice that the `docker-compose.yaml`
files are cleaner and easier to read in the current example.

You can now add as many services as you want without having to worry about
updating the DNS zone of your domain name provider.

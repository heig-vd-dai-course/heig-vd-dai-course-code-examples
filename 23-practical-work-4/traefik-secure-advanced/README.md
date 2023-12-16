# Traefik secure (avanced)

## Disclaimer

This configuration was meant to be used in my (Ludovic) personal homelab and I
wanted to share my findings with you if you are interested in doing something
similar.

Therefore, this example is **optional** to follow. It is here to show you how to
use Traefik in a more advanced way and for your own culture.

For more information about my modest setup, please refer to the
[Personal notes](#personal-notes) section.

## Introduction

This configuration is an improvement of the
[`traefik-secure`](../traefik-secure/README.md) example. It adds the following
features:

- Make use of the
  [DNS challenge](https://doc.traefik.io/traefik/https/acme/#dnschallenge) to
  generate HTTPS certificates, even if Traefik is not publicly accessible
- Enable HTTPS globally for all services (entrypoint level)
- HTTP is not accessible anymore
- Generate a common HTTPS certificate for all the services (wildcard
  certificate)
- Redirect all HTTP requests to HTTPS at the entrypoint level instead of using
  the `http-to-https-redirect` middleware (cleaner configuration)

## Prerequisites

### Rename or remove the `letsencrypt` folder

As you will be using the DNS challenge with a new configuration, you must rename
or remove the `letsencrypt` folder from the previous example.

```sh
# Rename the folder
mv letsencrypt letsencrypt.old
```

### Have a domain name ready and a DNS provider that supports the ACME DNS challenge

You must have a domain name ready.

Your DNS provider must support the
[ACME DNS challenge](https://doc.traefik.io/traefik/https/acme/#dnschallenge).
You can find a list of supported DNS providers at
<https://doc.traefik.io/traefik/https/acme/#providers>.

For this example, we will use [deSEC](https://desec.io/) as our DNS provider.

### Create the API key(s) for the DNS provider

You must create the API key(s) for the DNS provider. For deSEC, you can follow
the instructions at <https://desec.io/api/v1/docs/>.

### Update the `.env` and `dns-challenge.env` files

Update the [`.env`](.env) file with your own values:

- `TRAEFIK_ACME_EMAIL`: your email address
- `TRAEFIK_ACME_DNS_PROVIDER`: the name of your DNS provider - check the list of
  supported DNS providers at
  <https://doc.traefik.io/traefik/https/acme/#providers>
- `TRAEFIK_ROOT_FULLY_QUALIFIED_DOMAIN_NAME`: the root fully qualified domain
  name to access all your services - for example, if you want to access your
  services with the `https://whoami.my-domain-name.dedyn.io` URL, you must set
  `TRAEFIK_ROOT_FULLY_QUALIFIED_DOMAIN_NAME=my-domain-name.dedyn.io`
- `TRAEFIK_FULLY_QUALIFIED_DOMAIN_NAME`: the fully qualified domain name to
  access Traefik - by default, Traefik will be accessible at
  `https://traefik.TRAEFIK_ROOT_FULLY_QUALIFIED_DOMAIN_NAME`

Update the [`dns-challenge.env`](./dns-challenge.env) file with the values
needed by your DNS provider based on the
[list of supported DNS providers](https://doc.traefik.io/traefik/https/acme/#providers).

For deSEC, you must set the following environment variables:

- `DESEC_TOKEN`: the token to access the deSEC API

### Add the wildcard DNS entry

Add two `A` records to the DNS zone of your domain name provider to point to the
IP address of the virtual machine:

- `my-domain-name.dedyn.io` -> to the IP address of the virtual machine: this
  will allow access to a service hosted under the root of your domain name
  (`https://my-domain-name.dedyn.io`)
- `*.my-domain-name.dedyn.io` -> to the IP address of the virtual machine: this
  will allow access to all your services hosted under a subdomain of your domain
  name (`https://whoami.my-domain-name.dedyn.io` or
  `https://my-awesome-service.my-domain-name.dedyn.io`)

### Test the DNS resolution

Test the DNS resolution of the fully qualified domain names:

```sh
# Test the DNS resolution for the root fully qualified domain name
dig my-domain-name.dedyn.io

# Test the DNS resolution for the wildcard fully qualified domain name
dig "*.my-domain-name.dedyn.io"
```

You could even try any domain name as you have a wildcard DNS entry:

```sh
# Test the DNS resolution for the wildcard fully qualified domain name
dig a-dummy-placeholder.my-domain-name.dedyn.io
```

You might have to wait a few minutes for the DNS record to be propagated.

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

You should now be able to access the Traefik dashboard just like in the previous
example.

## Advantages of this configuration

The advantages of this configuration reside in the configuration of the Docker
Compose files.

If you compare both solutions, you will notice that the `docker-compose.yaml`
files are cleaner and easier to read in the current example.

Only two Let's Encrypt certificates are generated over the N number of services
you have. This is because we are using a root + wildcard certificates and not a
certificate per service.

You can now add as many services as you want without having to worry about
generating a new certificate for each service.

Using the wildcard certificate, you can even use the same certificate for
services that are not publicly accessible and still use HTTPS on your local
network. Check the [Personal notes](#personal-notes) section for more
information.

## Personal notes

My personal homelab consists of a NAS running
[Proxmox VE](https://www.proxmox.com/en/proxmox-ve) with a few
[LXC](https://linuxcontainers.org/lxc/) containers running under Debian 12.

I do not expose all my services publicly. To access my private services, I use
[WireGuard](https://www.wireguard.com/) to connect to my home network.

I have a domain name registered with [Infomaniak](https://www.infomaniak.com/)
under the name `ld0.ch` that I use for my homelab (the shorter the DNS name, the
less I have to type 😉).

I have a DNS server running
[AdGuard Home](https://github.com/AdguardTeam/AdguardHome) that is used as the
DNS server for my local network.

Using Traefik, I can access all my services with a common fully qualified domain
name.

In my DNS server, I have some DNS entries that points to the IP address of my
homelab (`*.lan.ld0.ch`).

This allows me to access all my services with a subdomain such as
`traefik.lan.ld0.ch` for when I am connected to my home network or
`whoami.ld0.ch` for the public services.

All this setup works quite well for me, but I was interested in having HTTPS
certificates for all my services, even locally. I wanted to keep things simple
without having to manually generate certificates and/or using other tools such
as [mkcert](https://github.com/FiloSottile/mkcert) or moving my homelab to the
cloud or Kubernetes.

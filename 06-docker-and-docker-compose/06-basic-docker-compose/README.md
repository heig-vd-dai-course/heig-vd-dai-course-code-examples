# Basic Docker Compose

This Docker Compose example uses one service, a simple hello-world service that
prints a message to the console.

It uses two important keys:

- `services`:
- `image`: specifies the image to use for a given service. I

The `image` key specifies the image to use for a given service. It can be a
local image or a remote image from a registry like Docker Hub.

It does the same thing as the command `docker run hello-world` but with Docker
Compose.

While not very useful for this simple example, Docker Compose can be very
powerful for more complex applications with multiple services.

## Run the Docker Compose

To run the Docker Compose, run the following command:

```sh
# Run the Docker Compose
docker compose up
```

The output should be similar to the one from the `hello-world` image you ran in
the course material.

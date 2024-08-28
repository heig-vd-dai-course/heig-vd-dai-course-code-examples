# Dockerfile with build arguments

This Dockerfile example uses one new instruction:

- `ARG`

The `ARG` instruction defines a build-time variable that can be used in the
Dockerfile. It is similar to a shell variable and can be used to pass values to
the Dockerfile at build time.

In this example, we use the `archlinux:base-20240825.0.257728` image. It means
that the Docker image will be based on the Arch Linux image and have all the
tools and libraries provided by this image, such as `bash` as the default shell
and `pacman` to install new packages.

## Build the Docker image

To build the Docker image, run the following command:

```sh
# Build the Docker image
docker build -t dockerfile-with-build-arguments .
```

## Run the Docker container

If you run the Docker container, you will notice the default output is the same
as the previous run: `Hello, World!`.

## Rebuid the Docker image with a different argument

To rebuild the Docker image with a different argument, run the following
command:

```sh
# Rebuild the Docker image with a different argument
docker build -t dockerfile-with-build-arguments --build-arg MESSAGE="Hello, DAI student!" .
```

This will change the default output of the Docker container to
`Hello, DAI student!`. Try to run the Docker container again to see the new
output.

Build arguments can be useful to pass values to the Dockerfile at build time.
They can be used to customize the build process and the resulting Docker image,
such as the packages versions to install, the default configuration, or the
default command to run.

## Cleanup

To remove the Docker image, run the following command:

```sh
# Remove the Docker image
docker rmi dockerfile-with-build-arguments
```

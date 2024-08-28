# Dockerfile with run commands

This Dockerfile example uses one new instruction:

- `RUN`

The `RUN` instruction executes a command in a new layer on top of the current
image and commits the results. It is used to install new packages, update the
system, or run any command that modifies the image.

In this example, we use the `alpine:3.12` image. It means that the Docker image
will be based on the Alpine Linux image and have all the tools and libraries
provided by this image, such as `ash` as the default shell and `apk` to install
new packages.

`apk` will be used to install the `tree` package.

`tree` is a command-line utility that displays an indented directory tree for
the current directory or the specified directory, such as:

```text
/
├── app
├── bin
├── dev
├── etc
├── home
├── lib
├── media
├── mnt
├── opt
├── proc
├── root
├── run
├── sbin
├── srv
├── sys
├── tmp
├── usr
└── var

19 directories, 0 files
```

## Build the Docker image

To build the Docker image, run the following command:

```sh
# Build the Docker image
docker build -t dockerfile-with-run-commands .
```

## Run the Docker container

To run the Docker container, run the following command:

```sh
# Run the Docker container
docker run --rm dockerfile-with-run-commands
```

The output should display the help message of the `tree` command.

Execute the following command to see the directory tree of the root directory:

```sh
# Run the Docker container with the tree command
docker run --rm dockerfile-with-run-commands /
```

The output should display the directory tree of the root directory with all the
directories and files.

To see the directory tree of the current directory, run the following command:

```sh
# Run the Docker container with the tree command
docker run --rm -v "$(pwd):/app" dockerfile-with-run-commands /app
```

The `-v $(pwd):/app` option mounts the current host directory to the `/app`
directory in the Docker container. The `tree` command will display the directory
tree of the current directory.

Volumes are used to share data between the host and the container. They can be
used to persist data, share configuration files, or share code between the host
and the container.

## Cleanup

To remove the Docker image, run the following command:

```sh
# Remove the Docker image
docker rmi dockerfile-with-run-commands
```

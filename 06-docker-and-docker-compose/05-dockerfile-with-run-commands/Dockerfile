# Base image
FROM alpine:3.20

# Working directory
WORKDIR /app

# Update and install packages
RUN apk update
RUN apk add --no-cache tree

# Entrypoint
ENTRYPOINT ["tree"]

# Command to run
CMD ["--help"]

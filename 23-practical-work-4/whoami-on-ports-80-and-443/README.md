# whoami on ports 80 and 443

This [whoami](https://github.com/traefik/whoami) example shows how to access the
whoami server running on ports 80 and 443 on the virtual machine with Docker and
Docker Compose.

This will ensure that the virtual machine is accessible on port 80 and 443.

## Run the example

Start the containers:

```sh
# Start the containers
docker compose up -d
```

Open your browser and go to public IP address of your virtual machine with the
`http` protocol, for example `http://VIRTUAL_MACHINE_PUBLIC_IP`.

You should now be able to access whoami on port 80.

Go to public IP address of your virtual machine with the `https` protocol, for
example `https://VIRTUAL_MACHINE_PUBLIC_IP`.

You should now be able to access whoami on port 443.

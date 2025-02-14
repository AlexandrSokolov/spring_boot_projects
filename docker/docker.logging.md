https://sematext.com/guides/docker-logs/
https://www.papertrail.com/solution/tips/how-to-live-tail-docker-logs/
https://betterstack.com/community/guides/logging/how-to-start-logging-with-docker/
https://docs.docker.com/engine/logging/configure/
https://logdy.dev/blog/post/docker-logs-web-browser-ui
https://dozzle.dev/
https://medium.com/@posinsk/docker-logs-viewer-ui-24eff2c25070

https://grafana.com/docs/loki/latest/get-started/
https://grafana.com/docs/loki/latest/send-data/docker-driver/

https://stackoverflow.com/questions/75968347/how-can-i-stream-a-docker-container-log-to-webbrowser-using-express-without-relo

* How long are Docker container logs retained?
* Default logs format
* Docker host logs files location 
* Logs snapshot
* Realtime logs
* Time-Based Filtering
* Export Docker logs to a file
* Log rotation
* Manage Log File Sizes

## Troubleshooting Common Docker Logging Issues
* Missing Logs
* Log Driver Conflicts
* Storage Space Issues
* Application-Specific Logging 

Log drivers:
log driver (e.g., syslog, json-file, fluentd)

TOOD:

https://signoz.io/guides/docker-view-logs/

* log aggregation
* filtering
* visualization
* alerting

tools:
* SigNoz
* 

Use Agents or Sidecars: Deploy an agent or sidecar container that collects logs from the Docker environment and sends them to the logging service.

Set Up Dashboards and Alerts: Use the provided tools to create custom dashboards and alerts tailored to your application's needs.

### How long are Docker container logs retained?

Docker retains logs indefinitely by default. However, you can configure log rotation to manage log file sizes and retention periods.

### 

Docker containers emit logs to the stdout and stderr output streams. 
Because containers are stateless, the logs are stored on the Docker host in JSON files by default. 

The default logging driver is json-file.

###

Containers are Multi-Tiered
This is one of the biggest challenges to Docker logging. However basic your Docker installation is, 
you will have to work with two levels of aggregation. 
One refers to the logs from the Dockerized application inside the container. 
The other involves the logs from the host servers, which consist of the system logs, 
as well as the Docker Daemon logs which are usually located in /var/log or a subdirectory within this directory.

A simple log aggregator that has access to the host can't just pull application log files as if they were host log files. 
Instead, it must be able to access the file system inside the container to collect the logs. 
Furthermore, your infrastructure will, inevitably, extend to more containers and you'll need 
to find a way to correlate log events to processes rather than their respective containers.

### Default logs format

Docker stores logs in JSON format by default. 

### Docker host logs files location

```
/var/lib/docker/containers/<container-id>/<container-id>-json.log
```

### View logs for a stopped container

The docker logs command works for both running and stopped containers, as long as the container hasn't been removed.

### GUI tool to see the logs

The Docker Desktop application features a GUI where you can select a running container and view its logs.

### Logs snapshot

```bash
docker ps
docker logs <container_name_or_id>
docker logs --tail <lines_number> <container_name_or_id>
```

### Realtime logs

Lets you stream logs to the console as they are being generated, 
which is vital for live debugging and monitoring the changes in real time.

```bash
docker logs --follow <container_name_or_id>
docker logs --tail 20 --follow <container_name_or_id>
```

### Time-Based Filtering

```bash
docker logs --since <YYYY-MM-DDTHH:MM:SS> --until <YYYY-MM-DDTHH:MM:SS> <container_name_or_id>
```

### Multi-Container Logging for containers running via Docker compose file

```bash
docker compose logs
```

### Export Docker logs to a file

you can redirect log output to a file:


docker logs <container-id> > container_log.txt

### Log rotation

You can use Docker’s built-in drivers like json-file or syslog for seamless log rotation. 
This integration allows for automatic management of log files without manual intervention.

https://signoz.io/blog/docker-log-rotation/

### Manage Log File Sizes

Use the max-size and max-file options with Docker's json-file logging driver 
to manage log rotation and prevent disk space overrun. 
In a production environment, logging without constraints can lead to massive log files 
that consume all available disk space, potentially crashing the service.

```json
{
  "log-driver": "json-file",
  "log-opts": {
    "max-size": "10m",
    "max-file": "3",
    "labels": "production_status",
    "env": "os,customer"
  }
}
```

### 



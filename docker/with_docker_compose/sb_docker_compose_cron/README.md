This project is based on [Build the docker image with docker compose](../sb_docker_compose/README.md)

- [Base docker image for a cron](#base-docker-images-for-a-cron-job)
- [`crond` installation on `Alpine` (preferable)](#crond-installation-on-alpine)
- [`crond` installation on `Ubuntu`](#crond-installation-on-ubuntu)
- [`crond` installation on `CentOS`](#crond-installation-on-centos)
- [`cron` jobs setting](#cron-jobs-setting-without-the-interactive-editor)
- [`cron` service running]
- [Running docker container with `cron`]
- [`cron` health checks]
- [Running `cron` jobs if the system is suspended or powered off]






console is the `cron` default output (preferable for running `cron` via `Docker`).
```text
crond[17]: USER root pid  20 cmd echo hello
crond[17]: USER root pid  21 cmd echo hello
crond[17]: USER root pid  22 cmd echo hello
```
`BusyBox` uses the "classic" [`syslogd`](https://en.wikipedia.org/wiki/Syslog)
To stop writing it to the console and log, run `syslogd`:
```bash
ps aux | grep syslogd
syslogd
ps aux | grep syslogd
cat /var/log/messages
```
Running `syslogd` creates `/var/log/messages` file:
```bash
cat /var/log/messages
cat /var/log/messages
Mar 24 09:44:51 a562f61e7e53 syslog.info syslogd started: BusyBox v1.37.0
Mar 24 09:45:00 a562f61e7e53 cron.info crond[17]: USER root pid  30 cmd run-parts /etc/periodic/15min
Mar 24 09:45:00 a562f61e7e53 cron.info crond[17]: USER root pid  31 cmd echo hello
```

### `cron` jobs setting without the interactive editor:

Cron line explanation:
```text
* * * * * "command to be executed"
- - - - -
| | | | |
| | | | ----- Day of week (0 - 7) (Sunday=0 or 7)
| | | ------- Month (1 - 12)
| | --------- Day of month (1 - 31)
| ----------- Hour (0 - 23)
------------- Minute (0 - 59)
```

2. [Add a cron job via a temporary script](https://stackoverflow.com/questions/878600/how-to-create-a-cron-job-using-bash-automatically-without-the-interactive-editor):

3. [Add command into a permanent script, locate the script under specific folder](https://wiki.alpinelinux.org/wiki/Cron)

    This solution depends on the `crond` implementation. 
    
    #### [For `Alpine` default `BusyBox crond` package](https://wiki.alpinelinux.org/wiki/Cron):
    
    

4. Run a permanent script as a cron job:

5. todo



### `cron` service running

CMD crond -f

### Running docker container with `cron`
### `cron` health checks


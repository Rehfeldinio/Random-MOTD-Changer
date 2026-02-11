# Random MOTD Changer

[![Release](https://img.shields.io/badge/release-1.1-red)](https://github.com/Rehfeldinio/Random-MOTD-Changer/releases/tag/1.1)

## Features
Randomly changes the MOTD of the Server.

## Enable the Plugin
- Put the `RandomMOTD.jar` in your `plugins` folder  
- (Re)start your Server  
- Modify the `plugins/RandomMOTD/config.yml`

## Commands

### `/motd-reload`
Reloads the configuration file without restarting the server.

**Permission:**  
- `motdchange.reload`  
- or server **OP**

## Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/motd-reload` | Reloads the config | `motdchange.reload` |

## Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `motdchange.reload` | Allows reloading the config | OP |


## Config

```yml
Motd:
  Enabled: true
  Texts:
    - "&bA minecraft server"
    - "&4A minecraft server"
    - "&1A minecraft server"
```
## Found a Bug? Report it [Here](https://github.com/Rehfeldinio/Random-MOTD-Changer/issues)

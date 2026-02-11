# Random MOTD Changer

[![Release](https://img.shields.io/badge/release-1.2-red)](https://github.com/Rehfeldinio/Random-MOTD-Changer/releases/tag/1.2)

A Paper plugin that randomly selects the server MOTD from a configurable list on each ping. Supports gradient colors, hex colors, and classic color codes.

## Features

- Random MOTD from a configurable list
- Gradient colors with 2 or 3 colors (client 1.16+)
- Single hex colors
- Classic `&` color codes
- Reload command to reload the config without restarting the server

## Installation

1. Place the `RandomMOTD.jar` into the `plugins` folder
2. Start or restart the server
3. Edit `plugins/RandomMOTD/config.yml` to your liking

## Config

```yml
Motd:
  Enabled: true
  Texts:
    - "&bA minecraft server"
    - "&4A minecraft server"
    - "&1A minecraft server"
```

### Gradient and Hex Colors

Starting from client version 1.16, gradient and hex colors are supported:

```yml
Motd:
  Enabled: true
  Texts:
    # 2-color gradient:
    - "<gradient:#FF0000:#00FF00>Welcome to the server!</gradient>"
    # 3-color gradient:
    - "<gradient:#FF0000:#FFFF00:#00FF00>Rainbow Text</gradient>"
    # Single hex color:
    - "<hex:#FF5555>Red Text</hex>"
    # Combined with normal color codes:
    - "&lBold &r<gradient:#00AAFF:#AA00FF>and Gradient!</gradient>"
```

### Color Code Reference

| Code | Color |
|------|-------|
| `&0` - `&9` | Black to Blue |
| `&a` - `&f` | Green to White |
| `&l` | **Bold** |
| `&o` | *Italic* |
| `&n` | Underline |
| `&m` | ~~Strikethrough~~ |
| `&r` | Reset |
| `\n` | Line break |

## Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/motd-reload` | Reloads the config | `motdchange.reload` |

## Permissions

| Permission | Description | Default |
|------------|-------------|---------|
| `motdchange.reload` | Allows reloading the config | OP |

## License

[MIT License](LICENSE.md) - Copyright (c) 2026 Rehfeldinio

## Found a bug?

Report it [here](https://github.com/Rehfeldinio/Random-MOTD-Changer/issues).


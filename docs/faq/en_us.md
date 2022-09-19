# Frequently Asked Questions

## About the game

### Use the modpack
1. [Download the latest version from Releases;](https://github.com/JieningYu/Cabricality/releases)
2. [Install the pack like this.](https://docs.modrinth.com/docs/modpacks/playing_modpacks/)

### Can't launch the modpack
- Use `build.461` of KubeJS and `build.182` of Rhino, or update them to the latest versions.
- `macOS ONLY` Clean all `.DS_Store` files under your `.minecraft` path. (shouldn't be caused by this if you didn't touch the KubeJS assets files)

### Some items from KubeJS have missing textures
This is a KubeJS issue, so it can't be fixed at our side. Restart Minecraft client may work.

### Can't transfer items and fluids within machines from Indrev mod

Use the target machine with a `Screwdriver` to configure its input and output faces.

### Can't view Infernal Mechanism's recipe

It's a `Sequenced Assembly` recipe and need 2 steps of `Filling` recipe so REI can't display it.

These steps are:

- `167mB / 13500dP` of `Liquid Soul`,
- `1000mB / 81000dP` of `Lava`;

And repeat them for 3 times.
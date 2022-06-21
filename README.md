<p align="center"> 
  <img src="https://imgur.com/RZDlCop.png"/>
</p>

# Description

Artifacts is built to be a game enhancing addition to any PvP or PvE server. Much like Hypixel talismans, this plugin allows you to build your own custom in-game items with potion buffs, debuffs, and custom effects made available through easy file configuration. Server commands and custom item meta allow you to offer these items through Crates, Koths, Player Shops, and many other server events of your choosing.

# Usage

When a player has an artifact in their inventory, they will be able to RIGHT CLICK the artifact to toggle it into an “active state”. When in an active state, the artifact will glow then the player will have all potion effects and custom effects applied. Admins can customize the item type, display name, and item lore of any artifact. There is no limit to the amount of artifacts that can be configured. Settings for custom effects can be changed in settings.yml

  - Can have multiple artifacts active
  - No limit to number of effects on an artifact
  - Artifacts are toggled off when moved from inventory or dropped
  - Settings and chances for custom effects are found in settings.yml


<p align="center"> 
  <img src="https://i.imgur.com/Dgqxwbu.gif"/>  
</p>

# Commands

    /artifact - Display available commands
    /artifact list - List configured artifacts
    /artifact give <artifact> <player> - Spawn artifact
    /artifact enable <artifact> - Enables disabled artifacts
    /artifact disable <artifact> - Disabled enabled artifacts
    /artifact reload - Reloads file configuration
    
# Custom Effects

**Ninja** -> When artifact is active, shifting for x seconds gives invisibility until unshifted<br>
**Rebound** -> When brought down to 1 heart, there’s a chance of getting set to half health<br>
**Bounce** -> Each hit from enemy gives a chance of knocking them back<br>
**Friend** -> Player wont take melee damage from mobs<br>
**Haunt** -> When a player gets hit, enemy has a chance to get blindness for 5s<br>
**Dolphin** -> When in water, player will swim faster<br>
**Immune** -> Player won’t take poison damage<br>
**Escape** -> Player has chance to be granted speed 2 when damaged<br>
**Mercy** -> Player has chance to be granted regen when damaged<br>
**Feather** -> Disables fall damage<br>
**Fat** -> Consuming edible items gives absorption for 5 mins<br>
**Stun** -> Player has chance to stun enemy when hit<br>
**Starve** -> player has chance to give enemy hunger<br>
**Blacksmith** -> Using tools will progressively add durability to them<br>
**Venom** -> Player have chance to give poison to enemy when hit<br>
**ExpSteal** -> Player has chance to steal enemies exp when hitting them<br>

## Example Configurations

<details>
  <summary> Click to expand config.yml </summary>
  <br>

    coin:
      name: '&e&lLucky Coin'
      id: 'DOUBLE_PLANT'
      enabled: true
      desc:
      - '&7Example artifact'
      potion_effects:
      - NIGHT_VISION
      custom_effects:
      - ESCAPE
      - MERCY
      - REBOUND
    defense_stone:
      name: '&f&lStone of Defense'
      id: 'STONE'
      enabled: true
      desc:
      - '&7Example artifact'
      potion_effects:
      - ABSORPTION
      - DAMAGE_RESISTANCE
      custom_effects:
      - BOUNCE
    neptune_soul:
      name: '&b&lNeptune Soul'
      id: 'WATER_BUCKET'
      enabled: true
      desc:
      - '&7Example artifact'
      potion_effects:
      - WATER_BREATHING
      custom_effects:
      - DOLPHIN 
 </details>
 
 
<details>
  <summary> Click to expand settings.yml </summary>
  <br>

    settings:
     prefix: '&cArtifacts &8&l>&7 '
     bounceChance: 20
     hauntChance: 25
     reboundChance: 10
     ninjaSeconds: 3
     escapeChance: 15
     escapeSpeedDuration: 5
     mercyChance: 20
     mercyDuration: 5
     stunChance: 20
     stunDuration: 5
     starveChance: 20
     starveLevel: 1
     starveDuration: 5
     venomChance: 20
     venomLevel: 1
     venomDuration: 5
     expStealChance: 20
</details>

# Permissions
  
- artifacts.* - All permissions
- artifacts.use - Toggle on artifacts to use them
- artifacts.admin - Allows use of artifact commands
- artifacts.effects - Access to all custom effects
- artifacts.effect.ninja - Access to ninja effect
- artifacts.effect.rebound - Access to rebound effect
- artifacts.effect.haunt - Access to haunt effect
- artifacts.effect.friend - Access to friend effect
- artifacts.effect.bounce - Access to bounce effect
- artifacts.effect.dolphin - Access to dolphin effect
- artifacts.effect.immune - Access to immune effect
- artifacts.effect.mercy - Access to mercy effect
- artifacts.effect.escape - Access to escape effect
- artifacts.effect.feather - Access to feather effect
- artifacts.effect.fat - Access to fat effect
- artifacts.effect.stun - Access to stun effect
- artifacts.effect.starve - Access to starve effect
- artifacts.effect.venom - Access to venom effect
- artifacts.effect.blacksmith - Access to blacksmith effect
- artifacts.effect.expsteal- Access to expsteal effect

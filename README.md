# ElytraHover
允许玩家消耗鞘翅的耐久度，在空中飞行。

默认的耐久消耗速度是每秒2点（即每10 tick一点），是鞘翅滑翔的两倍，可通过配置文件修改。耐久附魔有效。

支持的MC版本为1.16.5，Spigot。

## 使用方式
### 指令
```
/efly on - 开启鞘翅飞行模式
/efly off - 关闭鞘翅飞行模式
/efly reload - 重载配置文件
```
### 权限节点
```
elytrahover.toggle - /efly on 和 /efly off。默认全玩家都有。
elytrahover.manage - /efly reload。默认op权限。
```
## 默认配置文件
```yaml
message:
  FlyingOn: "Elytra Hovering turned on."
  FlyingOff: "Elytra Hovering turned off."
  noelytra: "You took your elytra off. Flying will be disabled immediately."
  warning: "Your elytra has less than 10% durability. Consider landing now."
  OnWithoutElytra: "You tried to turn flying on without elytra. Please wear the elytra before this command."
  NoTogglePerm: "You do not have the permission (elytrahover.toggle) to turn elytra hovering on or off"
  NoManagePerm: "You do not have the permission (elytrahover.manage) to manage settings."
  ConfigReloaded: "Configuration Reloaded."
TickPerDamage: 10
```

## 参考中文配置文件
```yaml
message:
  FlyingOn: "§9§l[鞘翅飞行] §r飞行模式已开启"
  FlyingOff: "§9§l[鞘翅飞行] §r飞行模式已关闭"
  noelytra: "§9§l[鞘翅飞行] §c§l[警告] §r你把鞘翅脱下来了，或鞘翅耐久度已耗尽！飞行功能将立刻禁用。"
  warning: "§9§l[鞘翅飞行] §c§l[警告] §r你的鞘翅剩余耐久低于§c10%§r。请考虑立刻降落"
  OnWithoutElytra: "§9§l[鞘翅飞行] §r请先装备耐久度未耗尽的鞘翅再启用飞行"
  NoTogglePerm: "§9§l[鞘翅飞行] §r 你没有权限开关鞘翅飞行模式 (elytrahover.toggle)"
  NoManagePerm: "§9§l[鞘翅飞行] §r 你没有权限管理设置 (elytrahover.manage)"
  ConfigReloaded: "§9§l[鞘翅飞行] §r 配置已重载"
  DurabilityOut: "§9§l[鞘翅飞行] §r 鞘翅耐久度耗尽，飞行模式已关闭。"
TickPerDamage: 10
```
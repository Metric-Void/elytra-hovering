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

## 配置文件解析
```yaml
message:
  FlyingOn: "鞘翅飞行模式开启"
  FlyingOff: "鞘翅飞行模式关闭"
  noelytra: "空中飞行的时候把鞘翅拿掉了，飞行模式将立刻禁用"
  warning: "警告，鞘翅耐久度低于10%"
  OnWithoutElytra: "在没有鞘翅时执行/efly on"
  NoTogglePerm: "没有开关飞行的权限"
  NoManagePerm: "没有重载配置文件的权限"
  ConfigReloaded: "配置文件重载成功的消息"
TickPerDamage: 10 # 每多少tick消耗一点鞘翅的耐久。
```
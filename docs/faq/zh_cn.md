# 常见问题

## 关于游戏

### 如何游玩
1. [从Github Releases下载带最新版本](https://github.com/JieningYu/Cabricality/releases)
2. [参考此文章安装](https://docs.modrinth.com/docs/modpacks/playing_modpacks/)

### 无法启动整合包
- 将KubeJS和Rhino模组更新到最新版，或使用KubeJS的`build.461`和Rhino的`build.182`（设备间可能存在差异）。
- `仅macOS` 启动时短暂显示资源加载界面并崩溃：移除`.minecraft`文件夹下的所有`.DS_Store`文件。

### 一些物品或方块失去材质
已知KubeJS模组问题，无法修复。重启Minecraft客户端有概率解决。

### 无法在工业革命模组的机器中传输物品与流体

对目标机器使用`螺丝刀`以配置它的输入输出面。

### 无法查看酷热构件的配方

它是一个`序列装配`的配方，共有2个`注液`步骤，所以无法正常使用REI查看它。

这些步骤分别是：

- `167mB / 13500dP` 的`液态灵魂`，
- `1000mB / 81000dP` 的`熔岩`。

并将这些步骤重复三次。
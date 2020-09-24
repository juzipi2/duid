# 生成Android设备唯一识别码(DEVICE_UNIQUE_ID)
### 起因
- 在用户不授权的情况下，无法获取IMEI。
- Android 10 禁止访问设备ID（imei、build serial）。
### 方案
- 在8.0以下使用android_id生成UUID。
- 在8.0及以上
  * 首先获取imei，如果能获取到使用imei生成UUID。
  * 获取wifi mac，如果能获取到使用mac生成UUID。
  * 如果imei跟mac都获取失败，仍然使用android_id。
- 如果以上方法都失败，通过UUID随机。
### 下载
[历史版本](DOWNLOAD.md)
### 用法
``` groovy
String duid = DUID.getDUID(context);
```
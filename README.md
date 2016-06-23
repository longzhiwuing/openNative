#ionic android打开Activity的插件

在国内和国外找不到相应的插件，所以自己写了一个简单的（比较low）。功能很简单，可以在ionic的controller里打开原生Activity的小插件。可以向Activity里传值，因为项目需要，还在这个插件里开发了一个打开pdf功能，不过这个是需要打开本地pdf查看程序的那种，不是很通用，不过我也实现了一个内置pdf查看器的插件，地址：可以参考下

##调用方法
```javascript
 var user={};
 user["name"]='zhangsan';
 user["sfzh"]='123456190001010000';
 window.plugins.OpenNative.openActivity("com.xxx.xxx.MainActivity","0",JSON.stringify(user),function(data){
     console.log('success data:'+data);
 },function(data){
     console.log('error data:'+data);
 });
 ```

总共三个参数，
- 第一个参数是需要打开的Activity的包路径，
- 第二个参数是字符串'1'和非'1',1是用startActivityForResult打开的，非1是用starActivity打开的
- 第三个参数是需要往Activity传递的参数，是一个json对象的字符串在activity中这个json字符串的key叫params
在Activity中可以如下获取参数
```java
String userInfo = bundle.getString("params");
JSONObject userObject = new JSONObject(userInfo);
String name = userObject.getString("name"));
String sfzh = userObject.getString("sfzh"));
```
这样就可以在activity中获取到ionic传递的参数
因为第一次写，可能会有很多bug，我只是希望能够给大家提供一个思路，还有这是针对ndroid的，所以ios不能用，违反了ionic的跨平台的初衷。。。



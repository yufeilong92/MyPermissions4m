##MyPermissions4m
国产 Android 权限申请最佳适配方案 —— permissions4m

* 本篇文章已授权微信公众号 guolin_blog （郭霖）独家发布

* [前言](https://juejin.im/post/59b5e1a76fb9a00a6a7410d6)
* [情景再现](https://juejin.im/post/59b5e1a76fb9a00a6a7410d6)
* [permissions4m 简介](https://juejin.im/post/59b5e1a76fb9a00a6a7410d6)
* [后记](https://juejin.im/post/59b5e1a76fb9a00a6a7410d6)
* [求职](https://juejin.im/post/59b5e1a76fb9a00a6a7410d6)
## 前言


permissions4m 最初的设计是仅仅做成一个编译器注解框架，在1.0.0版本时，它纯粹地实现了原生 Android 请求流程，关于它的设计思路可以查看这篇如何打造一个 Android 编译时注解框架。但是当投入笔者自己项目中使用的时候，笔者发现国产手机有许多适配缺陷，例如：

1. ActivityCompat.shouldShowRequestPermissionRationale(Activity, String) 无法弹出权限申请对话框
2. 明明用户点击拒绝授权，却回调的是权限申请成功方法
3. 只能有一次权限是否授予选择，拒绝后就无法再有提示

相信做过权限适配的小伙伴们都知道适配国产 Android 机的权限会有多少坑，而国内也并没有任何权限申请框架解决这些问题，于是笔者在 1.0.2 版本中增强了权限申请功能的适配，现在对于这三个问题 permissions4m 都有良好的解决：

1. 权限申请必定弹出对话框
2. 拒绝授权时回调的就是授权失败方法，接受授权时回调的就是授权成功方法，让它一定回调正确的方法
3. 当系统权限申请对话框不再弹出时，函数可返回一个 Intent，跳转到系统设置页面或者手机管家界面

情景再现
 <p> 
 Boss: "mmp，为什么展示联系人这块在小米手机显示不出来？"
 Programmer: "boss，其他手机都没问题，我这块做了权限申请的，但是小米就是不弹出权限申请对话框，与此同时小米默认授权失败，所以不能读取通讯录。"
Boss: "mmp，那这块呢，明明说了读取日历权限成功了，为什么还是没读取到？"
Programmer: "boss，其他手机都没问题，我这块做了权限申请的，但是小米就是不弹出权限申请对话框，与此同时小米默认授权成功，但是实际上是授权失败的。"
Boss: "mmp，那这块呢，明明我拒绝授予权限，为什么你提示我授权成功？"
Programmer: "boss，其他手机都没问题，我这块做了权限申请的，小米弹出权限申请对话框，与此同时你点了拒绝，但是小米做了手脚，实际上调用了授权成功的方法。"
Boss: "你有个毛用？测试机我都给你买好了，还这么菜，收拾收拾滚蛋吧。"
Programmer: "f**k 小米！"
</p>
> 原生 Android 请求方式在小米等国内机型上适配的情形，相信有部分读者已经有过经历，这里就不做原生测试了，拿出国内一个比较有名的权限申请框架我们来看看：

>小米申请地理位置：<br />
 ![](https://github.com/yufeilong92/MyPermissions4m/blob/master/icon/a.gif)<br />
>小米申请联系人：<br />
![](https://github.com/yufeilong92/MyPermissions4m/blob/master/icon/b.gif)<br />
>小米申请手机状态：<br />
![](https://github.com/yufeilong92/MyPermissions4m/blob/master/icon/c.gif)<br />

其实不仅仅是小米，国内其它手机也会有一样的问题，笔者再做了一份 oppo a57 的截图：
![](https://github.com/yufeilong92/MyPermissions4m/blob/master/icon/d.gif)<br />

可以看到，在申请过程中并没有任何弹窗弹出，并且提示授权成功，而实际上我们到权限管理界面可以看到并未得到权限。

下图是使用 permissions4m 的效果：

permissions4m 申请地理位置（小米）：<br />
![](https://github.com/yufeilong92/MyPermissions4m/blob/master/icon/e.gif)<br />


permissions4m 申请联系人（小米）：<br />
![](https://github.com/yufeilong92/MyPermissions4m/blob/master/icon/q.gif)<br />
permissions4m 申请手机状态（小米）：<br />
这里写图片描述<br />
![](https://github.com/yufeilong92/MyPermissions4m/blob/master/icon/r.gif)<br />
permissions4m 申请短信、日历（OPPO A57）<br />

这里写图片描述<br />
![](https://github.com/yufeilong92/MyPermissions4m/blob/master/icon/f.gif)<br />

我们可以看到弹出了权限申请对话框，而且授予权限的情况下确实获得了权限。

生活中，无论是作为开发者还是普通用户，应该都有接触到过 5.0+ 的小米/魅族手机，使用过这些手机的读者们应该还有些许印象——部分国产手机早在 android 6.0 之前，也就是在 google 推出动态权限之前就有了权限申请，而国产的 5.0 权限申请使用 6.0 的权限申请代码是行不通的，理由很简单——在5.0的系统源码里没有6.0权限申请的源码，这个问题在 permissions4m 2.0.0 版本中已经迎刃而解了，这意味着从 2.0.0 版本开始， permissions4m 开始支持国产手机 5.0 权限申请了。

permissions4m 简介

简介中只是节选了部分内容，更详细完整的请移至项目：github.com/jokermonn/p…。

注：截止笔者发布博客为止，permissions4m 最新版本为 2.0.0

##引入依赖
>Gradle 依赖
<pre><code>
project 中的 build.gradle：

buildscript {
  // ...
}

allprojects {
  repositories {
    // 请添加如下一行
    maven { url 'https://jitpack.io' }
  }
}
</code></pre>
##app 中的 build.gradle：
<pre><code>
dependencies {
  compile 'com.github.jokermonn:permissions4m:2.0.0-lib'
  annotationProcessor 'com.github.jokermonn:permissions4m:2.0.0-processor'
}
</code></pre>
##注解回调
>>在需要权限申请的地方调用
<pre><code>
Permissions4M.get(MainActivity.this)
            // 是否强制弹出权限申请对话框，建议为 true
            .requestForce(true)
            // 权限
            .requestPermission(Manifest.permission.RECORD_AUDIO)
            // 权限码
            .requestCode(AUDIO_CODE)
            // 如果需要使用 @PermissionNonRationale 注解的话，建议添加如下一行
            // 返回的 intent 是跳转至**系统设置页面**
            // .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
            // 返回的 intent 是跳转至**手机管家页面**
            // .requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
            .request();
如：

button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Permissions4M.get(MainActivity.this)
                .requestForce(true)
                .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
                .requestPermission(Manifest.permission.RECORD_AUDIO)
                .requestCode(AUDIO_CODE)
                .request();
    }
});
</code></pre>
然后将会回调相应的 @PermissionsGranted、@PermissionsDenied、@PermissionsRationale/PermissionsCustomRationale、@PermissionsNonRationale 所修饰的方法

@PermissionsGranted

授权成功时回调，注解中需要传入参数，分为两种情况：

单参数：@PermissionsGranted(LOCATION_CODE)，被修饰函数无需传入参数，例：
<pre><code>
  @PermissionsGranted(LOCATION_CODE)
  public void granted() {
      ToastUtil.show("地理位置授权成功");
  }
  </code></pre>
多参数：@PermissionsGranted({LOCATION_CODE, SENSORS_CODE, CALENDAR_CODE})，被修饰函数需要传入一个 int 参数，例：
<pre><code>
  @PermissionsGranted({LOCATION_CODE, SENSORS_CODE, CALENDAR_CODE})
  public void granted(int code) {
      switch (code) {
          case LOCATION_CODE:
              ToastUtil.show("地理位置权限授权成功");
              break;
          case SENSORS_CODE:
              ToastUtil.show("传感器权限授权成功");
              break;
          case CALENDAR_CODE:
              ToastUtil.show("读取日历权限授权成功");
              break;
          default:
              break;
      }
  }
  </code></pre>
@PermissionsDenied

授权失败时回调，注解中需要传入参数，分为两种情况：

单参数：@PermissionsDenied(LOCATION_CODE)，被修饰函数无需传入参数，例：
<pre><code>
@PermissionsDenied(LOCATION_CODE)

  public void denied() {
  }
  </code></pre>
多参数：@PermissionsDenied({LOCATION_CODE, SENSORS_CODE, CALENDAR_CODE})，被修饰函数需要传入一个 int 参数，例：
<pre><code>
  @PermissionsDenied({LOCATION_CODE, SENSORS_CODE, CALENDAR_CODE})
  public void denied(int code) {
      switch (code) {
          case LOCATION_CODE:
              ToastUtil.show("地理位置权限授权失败");
              break;
          case SENSORS_CODE:
              ToastUtil.show("传感器权限授权失败");
              break;
          case CALENDAR_CODE:
              ToastUtil.show("读取日历权限授权失败");
              break;
          default:
              break;
      }
  }
  </code></pre>
@PermissionsRationale

二次授权时回调，用于解释为何需要此权限，注解中需要传入参数，分为两种情况：

单参数：@PermissionsRationale(LOCATION_CODE)，被修饰函数无需传入参数，例：
<pre><code>
  @PermissionsRationale(LOCATION_CODE)
  public void rationale() {
      ToastUtil.show("请开启读取地理位置权限");
  }
  </code></pre>
多参数：@PermissionsRationale({LOCATION_CODE, SENSORS_CODE, CALENDAR_CODE})，被修饰函数需要传入一个 int 参数，例：
<pre><code>
  @PermissionsRationale({LOCATION_CODE, SENSORS_CODE, CALENDAR_CODE})
  public void rationale(int code) {
      switch (code) {
          case LOCATION_CODE:
              ToastUtil.show("请开启地理位置权限授权");
              break;
          case SENSORS_CODE:
              ToastUtil.show("请开启传感器权限授权");
              break;
          case CALENDAR_CODE:
              ToastUtil.show("请开启读取日历权限授权");
              break;
          default:
              break;
      }
      </code></pre>
注：系统弹出权限申请 dialog 与 toast 提示是异步操作，所以如果存在希望自行弹出一个 dialog 后（或其他同步需求）再弹出系统对话框，那么请使用 @PermissionsCustomRationale

@PermissionsCustomRationale

二次授权时回调，用于解释为何需要此权限，注解中需要传入参数，分为两种情况：

单参数：@PermissionsCustomRationale(LOCATION_CODE)，被修饰函数无需传入参数，例：
<pre><code>
    @PermissionsCustomRationale(LOCATION_CODE)
    public void cusRationale() {
        new AlertDialog.Builder(this)
                        .setMessage("读取地理位置权限申请：\n我们需要您开启读取地理位置权限(in activity with annotation)")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Permissions4M.get(MainActivity.this)
                                        // 注意添加 .requestOnRationale()
                                        .requestOnRationale()
                                        .requestPermission(Manifest.permission.READ_SMS)
                                        .requestCode(SMS_CODE)
                                        .request();
                            }
                        })
                        .show();
    }
    </code></pre>
多参数：@PermissionsCustomRationale({LOCATION_CODE, SENSORS_CODE, CALENDAR_CODE})，被修饰函数需要传入一个 int 参数，例：
  <pre><code>
    @PermissionsCustomRationale({SMS_CODE, AUDIO_CODE})
    public void cusRationale(int code) {
        switch (code) {
            case SMS_CODE:
                new AlertDialog.Builder(this)
                        .setMessage("短信权限申请：\n我们需要您开启短信权限(in activity with annotation)")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Permissions4M.get(MainActivity.this)
                                        // 注意添加 .requestOnRationale()
                                        .requestOnRationale()
                                        .requestPermission(Manifest.permission.READ_SMS)
                                        .requestCode(SMS_CODE)
                                        .request();
                            }
                        })
                        .show();
                break;
            case AUDIO_CODE:
                new AlertDialog.Builder(this)
                        .setMessage("录音权限申请：\n我们需要您开启录音权限(in activity with annotation)")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Permissions4M.get(MainActivity.this)
                                        // 注意添加 .requestOnRationale()
                                        .requestOnRationale()
                                        .requestPermission(Manifest.permission.RECORD_AUDIO)
                                        .requestCode(AUDIO_CODE)
                                        .request();
                            }
                        })
                        .show();
                break;
            default:
                break;
        }
        </code></pre>
注：除上述以外的 dialog，开发者可以自定义其他展示效果，调用权限申请时请使用，否则会陷入无限调用自定义 Rationale 循环中：
<pre><code>
Permissions4M.get(MainActivity.this)
        // 务必添加下列一行
      .requestOnRationale()
      .requestPermission(Manifest.permission.RECORD_AUDIO)
      .requestCode(AUDIO_CODE)
      .request();
      </code></pre>
@PermissionsNonRationale

当用户点击拒绝权限且不再提示（国产畸形权限适配扩展）情况下调用，此时意味着无论是 @PermissionsCustomRationale 或者 @PermissionsRationale 都不会被调用，无法给予用户提示，此时该注解修饰的函数被调用，注解中需要传入参数，分为两种情况：

单参数：@PermissionsNonRationale(LOCATION_CODE)，被修饰函数只需传入 Intent 参数，例：
<pre><code>
  @PermissionsNonRationale({LOCATION_CODE})
  public void nonRationale(Intent intent) {
      startActivity(intent);
  }
  </code></pre>
多参数：@PermissionsNonRationale(AUDIO_CODE, CALL_LOG_CODE)，被修饰函数需传入 int 参数和 Intent 参数，例：
<pre><code>
  @PermissionsNonRationale({AUDIO_CODE, CALL_LOG_CODE})
  public void nonRationale(int code, final Intent intent) {
      switch (code) {
          case AUDIO_CODE:
              new AlertDialog.Builder(MainActivity.this)
                      .setMessage("读取录音权限申请：\n我们需要您开启读取录音权限")
                      .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              startActivity(intent);
                          }
                      })
                      .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              dialog.cancel();
                          }
                      })
                      .show();
              break;
          case CALL_LOG_CODE:
              new AlertDialog.Builder(MainActivity.this)
                      .setMessage("读取通话记录权限申请：\n我们需要您开启读取通话记录权限")
                      .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              startActivity(intent);
                          }
                      })
                      .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                          @Override
                          public void onClick(DialogInterface dialog, int which) {
                              dialog.cancel();
                          }
                      })
                      .show();
              break;
          default:
              break;
      }
  }
  </code></pre>
Intent 类型为两种，一种是跳转至系统设置页面，另一种是跳至手机管家页面，而具体的设置方法请参考 注解回调 中 .requestPageType(int) 设置方法。

Listener 回调
例：
<pre><code>
Permissions4M.get(MainActivity.this)
    // 是否强制弹出权限申请对话框
    .requestForce(true)
    // 权限
    .requestPermission(Manifest.permission.READ_CONTACTS)
    // 权限码
    .requestCode(READ_CONTACTS_CODE)
    // 权限请求结果
    .requestCallback(new Wrapper.PermissionRequestListener() {
           @Override
        public void permissionGranted() {
            ToastUtil.show("读取通讯录权限成功 in activity with listener");
        }

        @Override
        public void permissionDenied() {
            ToastUtil.show("读取通讯录权失败 in activity with listener");
        }

        @Override
        public void permissionRationale() {
            ToastUtil.show("请打开读取通讯录权限 in activity with listener");
        }
    })
    // 权限完全被禁时回调函数中返回 intent 类型（手机管家界面）
    .requestPageType(Permissions4M.PageType.MANAGER_PAGE)
    // 权限完全被禁时回调函数中返回 intent 类型（系统设置界面）
    //.requestPageType(Permissions4M.PageType.ANDROID_SETTING_PAGE)
    // 权限完全被禁时回调，接口函数中的参数 Intent 是由上一行决定的
    .requestPage(new Wrapper.PermissionPageListener() {
        @Override
        public void pageIntent(final Intent intent) {
            new AlertDialog.Builder(MainActivity.this)
            .setMessage("读取通讯录权限申请：\n我们需要您开启读取通讯录权限(in activity with listener)")
            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(intent);
                }
            })
            .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            })
            .show();
        }
    })
    .request();
    </code></pre>
同步申请
使用 @PermissionsRequestSync 修饰 Activity 或 Fragment

传入两组参数

value 数组：请求码
permission 数组：请求权限
使用 Permissions4M.get(MainActivity.this).requestSync(); 进行同步权限申请

例：参考 sample 中 MainActivity 上的设置 ——
<pre><code>
@PermissionsRequestSync(
    permission = {Manifest.permission.BODY_SENSORS, 
                    Manifest.permission.ACCESS_FINE_LOCATION, 
                        Manifest.permission.READ_CALENDAR},
    value = {SENSORS_CODE, 
                LOCATION_CODE, 
                    CALENDAR_CODE})
                    </code></pre>
public class MainActivity extends AppCompatActivity
后记


permissions4m 的目标是适配尽可能多的国产机型，包括但不限于小米、魅族、OPPO、VIVO、华为等机型，不仅是6.0+版本，后期也会支持到小米、魅族等低版本也有权限申请的手机。但是由于笔者个人能力有限，所以希望尽可能多的开发者参与到此项目的开发当中，更多详情请移至 permissions4m。
求职


笔者目前刚刚大四，想找一份关于 android 的实习，最好是在杭州，如果贵司正在招实习，望告知 jokerzoc.cn@gmail.com，谢谢。
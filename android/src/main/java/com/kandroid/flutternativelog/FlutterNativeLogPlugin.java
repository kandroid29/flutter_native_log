package com.kandroid.flutternativelog;

import android.util.Log;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;

/** FlutterNativeLogPlugin */
public class FlutterNativeLogPlugin implements MethodCallHandler {
  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_native_log");
    channel.setMethodCallHandler(new FlutterNativeLogPlugin());
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    } else if(call.method.equals("printLog")){

      String msg = call.argument("msg");
      String tag = call.argument("tag");
      String logType = call.argument("logType");

      switch (logType) {
        case "warning":
          Log.w(tag, msg);
          break;
        case "error":
          Log.e(tag, msg);
          break;
        default:
          Log.d(tag, msg);
          break;
      }

      result.success("Logged Successfully!");

    } else {
      result.notImplemented();
    }
  }
}

# Inheritence Bug

I am extending a class such as LinkedHashMap which has **putAll** and **put** methods. The putAll method internally calls put by iterating through the items, so ideally any method overloaded i.e. if an inheriting class overloads the **put** method then it should call this instead.

Running this on a Samsung SM-T815Y Tablet running Android 7.0, API 24, running this returns:
```
2020-10-06 23:25:43.112 17256-17256/com.tempestronics.inheritancebug I/InheritanceBug: Adding element: 5
2020-10-06 23:25:43.113 17256-17256/com.tempestronics.inheritancebug I/InheritanceBug: Player List changed
2020-10-06 23:25:43.113 17256-17256/com.tempestronics.inheritancebug I/InheritanceBug: Adding element: 7
2020-10-06 23:25:43.113 17256-17256/com.tempestronics.inheritancebug I/InheritanceBug: Player List changed
2020-10-06 23:25:43.113 17256-17256/com.tempestronics.inheritancebug I/InheritanceBug: Adding element: 15
2020-10-06 23:25:43.113 17256-17256/com.tempestronics.inheritancebug I/InheritanceBug: Player List changed
2020-10-06 23:25:43.113 17256-17256/com.tempestronics.inheritancebug I/InheritanceBug: Adding element: 14
2020-10-06 23:25:43.113 17256-17256/com.tempestronics.inheritancebug I/InheritanceBug: Player List changed
```

Which is as expected i.e. it does call the overriting **put** method.

However, running the same on LENOVO TB-X605L tablet running Android 9, API 28, it fails and nothing is logged.

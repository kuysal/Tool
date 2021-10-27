# Version Number Comparator

This example is for comparing version numbers given as objects.

### Usage

```java
Version v = new Version("1.00.00");
Version v2 = new Version("1.00.02");

System.out.println(v.compareTo(v2));
```

Allows "-SNAPSHOT" tags to be compared. Same versions with "-SNAPSHOT" (Case Sensitive) addition will be considered a lower version.

```java
Version v1Test = new Version("12.34.15");
Version v2Test = new Version("12.34.15-SNAPSHOT");

System.out.println(v1Test.compareTo(v2Test)); // Will return -1
```
# Data

Data is a very simple android library that can store data on internal storage.  
You can store data very simply just one line of code using SharedPreferences, File and SQLite database.

## Setup
### Step 1:  
Add the JitPack repository to your build file  
Add it in your root build.gradle at the end of repositories:
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
```
### Step 2:  
Add the dependency  
```
dependencies {
	        implementation 'com.github.p4rv3z:Data:1.0.0'
	}
```

## How to use:

### Using SharedPreferences
One line of code to save data on your storage:
```
        _Data.preferences.saveString(context, data, key); // return boolean value

```

One line of code to read data from your storage:
```
        _Data.preferences.loadString(context, key); //return data

```

### Using File
One line of code to save data on your storage:
```
        _Data.file.saveString(context, data, key); // return boolean value

```

One line of code to read data from your storage:
```
        _Data.file.loadString(context, key); //return data

```

### Using Database
One line of code to save data on your storage:
```
        _Data.db.saveString(context, data, key); // return boolean value

```

One line of code to read data from your storage:
```
        _Data.db.loadString(context, key); //return data

```


#### Functions:
To save string value you have to pass **Context, Data and Key** value as parameter **on saveString()** function. This function will return true if data store successfully.
```
public static boolean saveString(@NonNull Context context, String data, @NonNull String key)
```
If you want to save your data safe as encrypted, you can use one extra parameter as password. So your data will encrypted with your password. And password will be 16 Character, you can also set password as null then your data will encrypted with default password.  
```
public static boolean saveString(@NonNull Context context, String data, @NonNull String key, @Nullable String password)
  
```

Other functions:  
To save data
```
public static boolean saveInt(@NonNull Context context, int data, @NonNull String key)
public static boolean saveInt(@NonNull Context context, int data, @NonNull String key, @Nullable String password)

public static boolean saveBoolean(@NonNull Context context, boolean data, @NonNull String key) 
public static boolean saveBoolean(@NonNull Context context, boolean data, @NonNull String key, @Nullable String password)

public static boolean saveFloat(@NonNull Context context, float data, @NonNull String key)
public static boolean saveFloat(@NonNull Context context, float data, @NonNull String key, @Nullable String password)

public static boolean saveLong(@NonNull Context context, long data, @NonNull String key)
public static boolean saveLong(@NonNull Context context, long data, @NonNull String key, @Nullable String password)
```

To read data
```
public static boolean loadInt(@NonNull Context context, @NonNull String key)
public static boolean loadInt(@NonNull Context context, @NonNull String key, @Nullable String password)

public static boolean loadBoolean(@NonNull Context context, @NonNull String key) 
public static boolean loadBoolean(@NonNull Context context, @NonNull String key, @Nullable String password)

public static boolean loadFloat(@NonNull Context context, @NonNull String key)
public static boolean loadFloat(@NonNull Context context, @NonNull String key, @Nullable String password)

public static boolean loadLong(@NonNull Context context, @NonNull String key)
public static boolean loadLong(@NonNull Context context, @NonNull String key, @Nullable String password)
```
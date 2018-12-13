package ee.icefire.modelgen.utils;

import java.util.Collection;
import java.util.Iterator;

public class StringUtils {

  public static boolean isEmpty(String s) {
    return s == null || s.isEmpty();
  }

  public static String toCamelCase(String s) {
    String[] parts = s.split("_");
    String camelCaseString = "";
    for (String part : parts){
      camelCaseString = camelCaseString + toProperCase(part);
    }
    return camelCaseString;
  }

  public static boolean startsWithLower(String s) {
    return Character.isLowerCase(s.codePointAt(0));
  }

  public static boolean startsWithUpper(String s) {
    return Character.isUpperCase(s.codePointAt(0));
  }

  public static boolean startsWith(String s, String ...prefixes) {
    for (String prefix : prefixes) {
      if (s.startsWith(prefix)) return true;
    }
    return false;
  }

  public static String toProperCase(String s) {
    return s.substring(0, 1).toUpperCase() + s.substring(1).toLowerCase();
  }

  public static String capitalize(String s) {
    return s.substring(0, 1).toUpperCase() + s.substring(1);
  }

  public static String decapitalize(String s) {
    return s.substring(0, 1).toLowerCase() + s.substring(1);
  }

  public static String join(Iterator iterator, String separator) {

    // handle null, zero and one elements before building source buffer
    if (iterator == null) {
      return null;
    }
    if (!iterator.hasNext()) {
      return "";
    }
    Object first = iterator.next();
    if (!iterator.hasNext()) {
      return first.toString();
    }

    // two or more elements
    StringBuffer buf = new StringBuffer(256); // Java default is 16, probably too small
    if (first != null) {
      buf.append(first);
    }

    while (iterator.hasNext()) {
      if (separator != null) {
        buf.append(separator);
      }
      Object obj = iterator.next();
      if (obj != null) {
        buf.append(obj);
      }
    }
    return buf.toString();
  }

  public static String join(Collection collection, String separator) {
    if (collection == null) {
      return null;
    }
    return join(collection.iterator(), separator);
  }

  public static String quot(String s) {
    return "\"" + s + "\"";
  }

}

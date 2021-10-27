package org.kuysal;

import java.util.Comparator;
import  java.util.regex.*;

public class Version implements Comparable<Version> {

    private final Character versionDelimiter = '.';
    boolean isSnapshot;
    String version;

    public Version(String version) {
        Pattern versionPattern = Pattern.compile("\\d+(\\.\\d+){0,2}(-SNAPSHOT)?");
        Matcher versionMatcher = versionPattern.matcher(version);

        if (!versionMatcher.matches()) {
            throw new IllegalArgumentException("Invalid version!");
        } else {
            this.version = version;
            this.isSnapshot = isSnapShot(version);
        }
    }

    @Override
    public int compareTo(Version v) {
        String versionToCompare = v.getVersion();
        String version = this.getVersion();

        if (v.isSnapshot()) {
            versionToCompare = versionToCompare.substring(0, versionToCompare.indexOf("-"));
        }

        if (isSnapshot) {
            version = this.getVersion().substring(0, this.getVersion().indexOf("-"));
        }

        String[] versionNumbersToCompare = versionToCompare.split("\\.");
        String[] versionNumbers = version.split("\\.");

        for (int i = 0; i < versionNumbers.length; i++) {
            Integer v1 = Integer.parseInt(versionNumbers[i]);
            Integer v2 = Integer.parseInt(versionNumbersToCompare[i]);

            if (v1 > v2) {
                return 1;
            } else if (v1 < v2) {
                return -1;
            }
        }

        if (v.isSnapshot() && !this.isSnapshot) {
            return 1;
        }
        if (!v.isSnapshot() && this.isSnapshot) {
            return -1;
        }
        return 0;
    }

    public boolean isSnapShot(String version) {
        if (version != null) {
            if (version.endsWith("-SNAPSHOT"))
                return true;
        }
        return false;
    }

    public Character getVersionDelimiter() {
        return versionDelimiter;
    }

    public boolean isSnapshot() {
        return isSnapshot;
    }

    public void setSnapshot(boolean snapshot) {
        isSnapshot = snapshot;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public static void main(String[] args) {
        Version v1Test = new Version("12.34.14");
        Version v2Test = new Version("12.34.15");

        System.out.println(v1Test.compareTo(v2Test));
    }
}

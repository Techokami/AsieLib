package pl.asie.lib.util;

import java.io.File;

import pl.asie.lib.AsieLibMod;

public class MiscUtils {
	public static int getSideFromName(String endpoint) {
		endpoint = endpoint.toLowerCase();
		if(endpoint.equalsIgnoreCase("up") || endpoint.equalsIgnoreCase("top")) return 1;
		if(endpoint.equalsIgnoreCase("down") || endpoint.equalsIgnoreCase("bottom")) return 0;
		if(endpoint.equalsIgnoreCase("left") || endpoint.equalsIgnoreCase("west")) return 4;
		if(endpoint.equalsIgnoreCase("right") || endpoint.equalsIgnoreCase("east")) return 5;
		if(endpoint.equalsIgnoreCase("front") || endpoint.equalsIgnoreCase("forward") || endpoint.equalsIgnoreCase("north")) return 2;
		if(endpoint.equalsIgnoreCase("back") || endpoint.equalsIgnoreCase("south")) return 3;
		return -1;
	}
	
	public static File getMinecraftDirectory() {
		return AsieLibMod.proxy.getMinecraftDirectory();
	}
	
	// Slightly modified from http://stackoverflow.com/questions/9655181/convert-from-byte-array-to-hex-string-in-java
	protected static final char[] hexArray = "0123456789abcdef".toCharArray();
	public static String asHexString(byte[] bytes) {
	    char[] hexChars = new char[bytes.length * 2];
	    for (int j = 0; j < bytes.length; j++) {
	        int v = bytes[j] & 0xFF;
	        hexChars[j * 2] = hexArray[v >>> 4];
	        hexChars[j * 2 + 1] = hexArray[v & 0x0F];
	    }
	    return new String(hexChars);
	}
}

package com.alodiga.hsm.util;

import java.util.Vector;
import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Stack;

	public final class Convert {
	   private static final short[] ebcdic_to_ascii = new short[256];
	   private static final short[] ascii_to_ebcdic = new short[256];
	   private static String[] html_entities = new String[256];
	   private static final int positive_byte_mask = 255;
	   private static final long positive_int_mask = -1L;

	   public static final int fromMsgTypeToInt(String var0) {
	      return fromMsgTypeDataToInt(getData(var0));
	   }

	   /** @deprecated */
	   public static final void convertEbcdicToAscii(byte[] var0, int var1, int var2) {
	      int var3 = var1 + var2;

	      for(int var4 = var1; var4 < var3; ++var4) {
	         if (var0[var4] < 0) {
	            var0[var4] = (byte)ebcdic_to_ascii[256 + var0[var4]];
	         } else {
	            var0[var4] = (byte)ebcdic_to_ascii[var0[var4]];
	         }
	      }

	   }

	   /** @deprecated */
	   public static void getBinFromHexInAscii(String var0, byte[] var1, int var2) {
	      int var3 = var0.length();
	      char[] var4 = new char[var3];
	      var0.getChars(0, var3, var4, 0);

	      for(int var5 = 0; var5 < var3; ++var5) {
	         byte var6;
	         if (var4[var5] <= '9') {
	            var6 = (byte)((byte)var4[var5] - 48 & 15);
	         } else {
	            var6 = (byte)((byte)var4[var5] - 65 + 10 & 15);
	         }

	         if (var5 % 2 == 0) {
	            var1[var2 + var5 / 2] = (byte)(var6 << 4);
	         } else {
	            var1[var2 + var5 / 2] += var6;
	         }
	      }

	   }

	   /** @deprecated */
	   public static final int toInt(byte[] var0, int var1, int var2) {
	      int var3;
	      for(var3 = 0; var2 > 0; --var2) {
	         var3 = var3 * 10 + (var0[var1++] & 15);
	      }

	      return var3;
	   }

	   /** @deprecated */
	   public static final int toInt(byte[] var0) {
	      return toInt(var0, 0, var0.length);
	   }

	   public static long fromStringToLong(String var0) {
	      return Long.parseLong(var0);
	   }

	   /** @deprecated */
	   public static final long fromMsgTypeToLong(String var0) {
	      return fromMsgTypeDataToLong(getData(var0));
	   }

	   /** @deprecated */
	   public static final void convertAsciiToEbcdic(byte[] var0, int var1, int var2) {
	      int var3 = var1 + var2;

	      for(int var4 = var1; var4 < var3; ++var4) {
	         if (var0[var4] < 0) {
	            var0[var4] = (byte)ascii_to_ebcdic[256 + var0[var4]];
	         } else {
	            var0[var4] = (byte)ascii_to_ebcdic[var0[var4]];
	         }
	      }

	   }

	   public static void main(String[] var0) {
	      System.out.println("--------- Convert --------");
	      String var1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+;'\\,.:\"|<>";
	      String var2 = "09AF";
	      System.out.println(fromDoubleToString(100.0D, 5));
	      System.out.println(fromDoubleToString(-100.0D, 5));
	      if (var1.equals(fromAsciiToEbcdic(fromEbcdicToAscii(var1))) && var1.equals(fromHexToBin(fromBinToHex(var1))) && var1.equals(fromPexToBin(fromBinToPex(var1))) && var2.equals(fromLongToMsgType(fromMsgTypeToLong(var2))) && "".equals(strip("", '0', false, 0)) && "".equals(strip("00", '0', false, 0)) && "12".equals(strip("12", '0', false, 0)) && "12".equals(strip("0012", '0', false, 0)) && "00".equals(strip("", '0', false, 2)) && "00".equals(strip("00", '0', false, 2)) && "00".equals(strip("000", '0', false, 2)) && "01".equals(strip("1", '0', false, 2)) && "01".equals(strip("01", '0', false, 2)) && "01".equals(strip("001", '0', false, 2)) && "123".equals(strip("123", '0', false, 2)) && "123".equals(strip("00123", '0', false, 2)) && "0000".equals(resize("", 4, '0', false)) && "0000".equals(resize("00", 4, '0', false)) && "0000".equals(resize("0000", 4, '0', false)) && "0012".equals(resize("12", 4, '0', false)) && "0012".equals(resize("012", 4, '0', false)) && "0012".equals(resize("0012", 4, '0', false)) && "1234".equals(resize("1234", 4, '0', false)) && "1234".equals(resize("0001234", 4, '0', false)) && "00100".equals(fromDoubleToString(100.0D, 5)) && "00100".equals(fromDoubleToString(-100.0D, 5))) {
	         try {
	            System.in.read();
	         } catch (Throwable var5) {
	            System.out.println(var5.toString());
	         }

	         try {
	            System.in.read();
	         } catch (Throwable var4) {
	            System.out.println(var4.toString());
	         }

	         System.out.println(fromDoubleToUnsignedLongString(0.0D, 0));
	         System.out.println(fromDoubleToUnsignedLongString(-1.0D, 0));
	         System.out.println(fromDoubleToUnsignedLongString(1.234567890123E12D, 0));
	         System.out.println(fromDoubleToUnsignedLongString(-1.2345678901231235E12D, 0));
	      } else {
	         throw new RuntimeException();
	      }
	   }

	   /** @deprecated */
	   public static final long fromMsgTypeDataToLong(byte[] var0) {
	      long var1 = 0L;
	      int var3 = var0.length;

	      for(int var4 = 0; var4 < var3; ++var4) {
	         var1 = var1 << 4 | (long)getHexNibble(var0[var4]);
	      }

	      return var1;
	   }

	   /** @deprecated */
	   public static final byte[] toDataHex(byte[] var0, int var1, int var2) {
	      var2 += var2;
	      byte[] var3 = new byte[var2];

	      for(int var4 = 0; var4 < var2; ++var4) {
	         if ((var0[var1] & 128) == 0) {
	            var3[var4] = (byte)((var0[var1] & 112) >> 4);
	         } else {
	            var3[var4] = (byte)((var0[var1] & 112) >> 4 | 8);
	         }

	         if (var3[var4] < 10) {
	            var3[var4] = (byte)(var3[var4] + 48);
	         } else {
	            var3[var4] = (byte)(var3[var4] + 55);
	         }

	         ++var4;
	         var3[var4] = (byte)(var0[var1] & 15);
	         if (var3[var4] < 10) {
	            var3[var4] = (byte)(var3[var4] + 48);
	         } else {
	            var3[var4] = (byte)(var3[var4] + 55);
	         }

	         ++var1;
	      }

	      return var3;
	   }

	   /** @deprecated */
	   public static final void toDataHex(long var0, byte[] var2, int var3, int var4) {
	      for(var3 += var4; var4 > 0; --var4) {
	         --var3;
	         var2[var3] = (byte)((int)(var0 & 15L | 48L));
	         if (var2[var3] > 57) {
	            var2[var3] = (byte)(var2[var3] + 7);
	         }

	         var0 >>= 4;
	      }

	   }

	   /** @deprecated */
	   public static final String toStringHex(long var0, int var2) {
	      byte[] var3;
	      for(var3 = new byte[var2]; var2 > 0; var0 >>= 4) {
	         --var2;
	         long var4 = var0 & 15L;
	         if (var4 < 10L) {
	            var3[var2] = (byte)((int)(var4 + 48L));
	         } else {
	            var3[var2] = (byte)((int)(var4 + 55L));
	         }
	      }

	      return new String(var3, 0, 0, var3.length);
	   }

	   public static double fromStringToDouble(String var0) {
	      return (double)fromStringToLong(var0);
	   }

	   private static final byte getPexNibble(byte var0) {
	      return var0 >= 48 && var0 <= 63 ? (byte)(var0 & 15) : 0;
	   }

	   /** @deprecated */
	   public static final String toStringHex(long var0) {
	      int var2 = (int)Math.round(Math.floor(Math.log((double)var0) / Math.log(16.0D))) + 1;
	      return toStringHex(var0, var2);
	   }

	   /** @deprecated */
	   public static final String toStringHex(String var0) {
	      byte[] var1 = toData(var0);
	      return toStringHex(var1, 0, var1.length);
	   }

	   /** @deprecated */
	   public static final String toStringHex(byte[] var0, int var1, int var2) {
	      byte[] var3 = toDataHex(var0, var1, var2);
	      return new String(var3, 0, 0, var3.length);
	   }

	   /** @deprecated */
	   public static final String toStringHex(byte[] var0) {
	      return toStringHex(var0, 0, var0.length);
	   }

	   private static final int getPexChar(int var0) {
	      return var0 + 48;
	   }

	   /** @deprecated */
	   public static final String setLength(String var0, int var1, boolean var2) {
	      return setLength(var0, var1, (byte)32, var2);
	   }

	   /** @deprecated */
	   public static final String setLength(String var0, int var1, byte var2, boolean var3) {
	      int var4 = var0.length();
	      if (var4 == var1) {
	         return var0;
	      } else if (var4 < var1) {
	         byte[] var5 = new byte[var4];
	         var0.getBytes(0, var4, var5, 0);
	         var5 = toDataPadded(var5, var1, var2, var3);
	         return new String(var5, 0, 0, var1);
	      } else {
	         return var3 ? var0.substring(0, var1) : var0.substring(var4 - var1);
	      }
	   }

	   /** @deprecated */
	   public static Vector splitString(String var0, int var1) {
	      Vector var2 = new Vector();
	      boolean var3 = false;
	      int var4 = var3 ? 1 : 0;

	      while(true) {
	         int var5 = var0.indexOf(var1, var4);
	         if (var5 == -1) {
	            if (var0.length() > 0) {
	               var2.addElement(var0.substring(var4));
	            }

	            return var2;
	         }

	         var2.addElement(var0.substring(var4, var5));
	         var4 = var5 + 1;
	      }
	   }

	   /** @deprecated */
	   public static final boolean isEqual(byte[] var0, byte[] var1) {
	      int var2 = var0.length;
	      if (var2 != var1.length) {
	         return false;
	      } else {
	         for(int var3 = 0; var3 < var2; ++var3) {
	            if (var0[var3] != var1[var3]) {
	               return false;
	            }
	         }

	         return true;
	      }
	   }

	   /** @deprecated */
	   public static String fromDoubleToString(double var0, int var2) {
	      return fromDoubleToUnsignedLongString(var0, var2);
	   }

	   public static final int fromMsgTypeDataToInt(byte[] var0) {
	      int var1 = 0;
	      int var2 = var0.length;

	      for(int var3 = 0; var3 < var2; ++var3) {
	         var1 = var1 << 4 | getHexNibble(var0[var3]);
	      }

	      return var1;
	   }

	   public static final boolean isEqual(Object var0, Object var1) {
	      if (var0 == null ^ var1 == null) {
	         return false;
	      } else {
	         return var0 == null ? true : var0.equals(var1);
	      }
	   }

	   /** @deprecated */
	   public static final String toString(byte[] var0) {
	      return new String(var0, 0, 0, var0.length);
	   }

	   /** @deprecated */
	   public static final String toString(long var0, int var2) {
	      byte[] var3;
	      for(var3 = new byte[var2]; var2 > 0; var0 /= 10L) {
	         --var2;
	         var3[var2] = (byte)((int)(var0 % 10L + 48L));
	      }

	      return toString(var3);
	   }

	   public static final boolean isEqualData(byte[] var0, byte[] var1) {
	      if (var0 == null ^ var1 == null) {
	         return false;
	      } else if (var0 == null) {
	         return true;
	      } else {
	         int var2 = var0.length;
	         if (var2 != var1.length) {
	            return false;
	         } else {
	            for(int var3 = 0; var3 < var2; ++var3) {
	               if (var0[var3] != var1[var3]) {
	                  return false;
	               }
	            }

	            return true;
	         }
	      }
	   }

	   public static final byte[] fromPexDataToBinData(byte[] var0) {
	      int var1 = var0.length / 2;
	      byte[] var2 = new byte[var1];
	      int var3 = 0;

	      for(int var4 = 0; var4 < var1; ++var4) {
	         var2[var4] = (byte)(getPexNibble(var0[var3++]) << 4 | getPexNibble(var0[var3++]));
	      }

	      return var2;
	   }

	   public static final byte[] fromBinDataToPexData(byte[] var0) {
	      int var1 = var0.length;
	      byte[] var2 = new byte[var1 * 2];
	      int var3 = 0;

	      for(int var4 = 0; var4 < var1; ++var4) {
	         int var5 = var0[var4] & 255;
	         var2[var3++] = (byte)getPexChar(var5 >> 4);
	         var2[var3++] = (byte)getPexChar(var5 & 15);
	      }

	      return var2;
	   }

	   public static final byte[] stripData(byte[] var0, byte var1, boolean var2, int var3) {
	      int var4 = var0.length;
	      if (var2) {
	         --var4;

	         while(var4 >= var3 && var0[var4] == var1) {
	            --var4;
	         }

	         byte[] var8;
	         if (var4 >= var3) {
	            ++var4;
	            var8 = new byte[var4];
	            System.arraycopy(var0, 0, var8, 0, var4);
	            return var8;
	         } else {
	            ++var4;
	            var8 = new byte[var3];
	            System.arraycopy(var0, 0, var8, 0, var4);

	            while(var4 < var3) {
	               var8[var4] = var1;
	               ++var4;
	            }

	            return var8;
	         }
	      } else {
	         int var5 = 0;

	         int var6;
	         for(var6 = var4 - var3; var5 < var6 && var0[var5] == var1; ++var5) {
	         }

	         byte[] var7;
	         if (var5 <= var6) {
	            var4 -= var5;
	            var7 = new byte[var4];
	            System.arraycopy(var0, var5, var7, 0, var4);
	            return var7;
	         } else {
	            var4 -= var5;
	            var7 = new byte[var3];
	            System.arraycopy(var0, var5, var7, var3 - var4, var4);
	            var3 -= var4;
	            --var3;

	            while(var3 >= 0) {
	               var7[var3] = var1;
	               --var3;
	            }

	            return var7;
	         }
	      }
	   }

	   public static final String strip(String var0, char var1, boolean var2, int var3) {
	      return new String(stripData(var0.getBytes(), (byte)var1, var2, var3));
	   }

	   /** @deprecated */
	   public static final byte[] toData(String var0) {
	      int var1 = var0.length();
	      byte[] var2 = new byte[var1];
	      var0.getBytes(0, var1, var2, 0);
	      return var2;
	   }

	   public static final String fromPexToBin(String var0) {
	      return getString(fromPexDataToBinData(getData(var0)));
	   }

	   /** @deprecated */
	   public static final void toData(long var0, byte[] var2, int var3, int var4) {
	      for(var3 += var4; var4 > 0; --var4) {
	         --var3;
	         var2[var3] = (byte)((int)(var0 % 10L | 48L));
	         var0 /= 10L;
	      }

	   }

	   /** @deprecated */
	   public static final byte[] toData(long var0) {
	      int var2 = (int)Math.round(Math.floor(Math.log((double)var0) / Math.log(10.0D))) + 1;
	      byte[] var3 = new byte[var2];
	      toData(var0, var3, 0, var2);
	      return var3;
	   }

	   public static String[] splitParams(String var0) {
	      if (var0 == null) {
	         return null;
	      } else {
	         int var1 = var0.length();
	         char[] var2 = new char[]{' ', '\t'};
	         char[] var3 = new char[]{'"', "'".charAt(0)};
	         Vector var4 = new Vector();
	         int var5 = 0;

	         int var6;
	         while(var5 < var1) {
	            var6 = var1;

	            int var7;
	            int var8;
	            for(var7 = 0; var7 < var2.length; ++var7) {
	               var8 = var0.indexOf(var2[var7], var5);
	               if (var8 != -1 && var8 < var6) {
	                  var6 = var8;
	               }
	            }

	            var7 = var1;

	            int var9;
	            for(var8 = 0; var8 < var3.length; ++var8) {
	               var9 = var0.indexOf(var3[var8], var5);
	               if (var9 != -1 && var9 < var7) {
	                  var7 = var9;
	               }
	            }

	            if (var7 == var5) {
	               char var11 = var0.charAt(var5);
	               var9 = var0.indexOf(var11, var5 + 1);
	               if (var9 == -1) {
	                  var9 = var1;
	               }

	               var4.addElement(var0.substring(var5 + 1, var9));
	               var5 = var9 + 1;
	            } else if (var6 == var5) {
	               ++var5;
	            } else {
	               var4.addElement(var0.substring(var5, var6));
	               var5 = var6;
	            }
	         }

	         String[] var10 = new String[var4.size()];

	         for(var6 = 0; var6 < var4.size(); ++var6) {
	            var10[var6] = (String)var4.elementAt(var6);
	         }

	         return var10;
	      }
	   }

	   public static final String fromBinToPex(String var0) {
	      return getString(fromBinDataToPexData(getData(var0)));
	   }

	   /** @deprecated */
	   public static final byte[] fromDataPadded(byte[] var0, byte var1, boolean var2, int var3) {
	      int var4 = var0.length;
	      if (var2) {
	         --var4;

	         while(var4 >= var3 && var0[var4] == var1) {
	            --var4;
	         }

	         byte[] var8;
	         if (var4 >= var3) {
	            ++var4;
	            var8 = new byte[var4];
	            System.arraycopy(var0, 0, var8, 0, var4);
	            return var8;
	         } else {
	            ++var4;
	            var8 = new byte[var3];
	            System.arraycopy(var0, 0, var8, 0, var4);

	            while(var4 < var3) {
	               var8[var4] = var1;
	               ++var4;
	            }

	            return var8;
	         }
	      } else {
	         int var5 = 0;

	         int var6;
	         for(var6 = var4 - var3; var5 < var6 && var0[var5] == var1; ++var5) {
	         }

	         byte[] var7;
	         if (var5 <= var6) {
	            var4 -= var5;
	            var7 = new byte[var4];
	            System.arraycopy(var0, var5, var7, 0, var4);
	            return var7;
	         } else {
	            var4 -= var5;
	            var7 = new byte[var3];
	            System.arraycopy(var0, var5, var7, var3 - var4, var4);
	            var3 -= var5;
	            --var3;

	            while(var3 >= 0) {
	               var7[var3] = var1;
	               --var3;
	            }

	            return var7;
	         }
	      }
	   }

	   /** @deprecated */
	   public static final String fromStringPadded(String var0, byte var1, boolean var2, int var3) {
	      byte[] var4 = toData(var0);
	      return toString(fromDataPadded(var4, var1, var2, var3));
	   }

	   public static final String fromStringToHTMLSpecialChars(String var0) {
	      byte[] var1 = getData(var0);
	      StringBuffer var2 = new StringBuffer(var1.length);

	      for(int var3 = 0; var3 < var1.length; ++var3) {
	         byte var4 = var1[var3];
	         if (var4 < 0) {
	            var2.append((char)var4);
	         } else {
	            var2.append(html_entities[var4]);
	         }
	      }

	      return new String(var2);
	   }

	   /** @deprecated */
	   public static final byte[] toDataPadded(String var0, int var1, byte var2) {
	      byte[] var3 = new byte[var1];
	      int var4 = var0.length();
	      if (var4 >= var1) {
	         var0.getBytes(0, var1, var3, 0);
	      } else {
	         var0.getBytes(0, var4, var3, 0);

	         while(var4 < var1) {
	            var3[var4] = var2;
	            ++var4;
	         }
	      }

	      return var3;
	   }

	   /** @deprecated */
	   public static final byte[] toDataPadded(byte[] var0, int var1, byte var2, boolean var3) {
	      byte[] var4 = new byte[var1];
	      int var5 = var0.length;
	      if (var3) {
	         if (var5 >= var1) {
	            System.arraycopy(var0, 0, var4, 0, var1);
	         } else {
	            System.arraycopy(var0, 0, var4, 0, var5);

	            while(var5 < var1) {
	               var4[var5] = var2;
	               ++var5;
	            }
	         }
	      } else if (var5 >= var1) {
	         System.arraycopy(var0, var5 - var1, var4, 0, var1);
	      } else {
	         var5 = var1 - var5;
	         System.arraycopy(var0, 0, var4, var5, var0.length);
	         --var5;

	         while(var5 >= 0) {
	            var4[var5] = var2;
	            --var5;
	         }
	      }

	      return var4;
	   }

	   /** @deprecated */
	   public static final String toStringPadded(String var0, int var1, byte var2, boolean var3) {
	      return toString(toDataPadded(toData(var0), var1, var2, var3));
	   }

	   public static final String getString(byte[] var0) {
	      return new String(var0, 0, 0, var0.length);
	   }

	   public static String fromLongToString(long var0, int var2) {
	      if (var0 < 0L) {
	         var0 = -var0;
	      }

	      String var3 = String.valueOf(var0);
	      return resize(var3, var2, '0', false);
	   }

	   public static final String fromDataToStringHexDump(byte[] var0, String var1) {
	      int var2 = 0;
	      int var3 = var0.length;
	      StringBuffer var4 = new StringBuffer(var3 * 4);
	      String var5 = "   ";
	      boolean var6 = false;
	      int var7 = var2 + var3;

	      while(var2 < var7) {
	         var4.append(var1 + toString((long)var2, 4) + "(" + toStringHex((long)var2, 4) + ")  ");

	         int var9;
	         for(var9 = 0; var9 < 16; ++var2) {
	            if (var2 < var7) {
	               if (var0[var2] > 0) {
	                  var4.append(toStringHex((long)var0[var2], 2) + " ");
	               } else {
	                  var4.append(toStringHex((long)(256 + var0[var2]), 2) + " ");
	               }
	            } else {
	               var4.append(var5);
	            }

	            if (var9 == 7) {
	               var4.append(" ");
	            }

	            ++var9;
	         }

	         var2 = var2;
	         var4.append("  ");

	         for(var9 = 0; var9 < 16 && var2 < var7; ++var2) {
	            if (var0[var2] < 32) {
	               var4.append(".");
	            } else {
	               var4.append(new String(var0, var2, 1));
	            }

	            ++var9;
	         }

	         var4.append("\n");
	      }

	      return new String(var4);
	   }

	   public static final String fromStringToXmlDump(String var0, String var1) {
	      byte var2 = 2;
	      StringBuffer var3 = new StringBuffer(1000);
	      Stack var4 = new Stack();
	      String var5 = var0;
	      int var6 = 0;

	      String var7;
	      for(String var8 = " "; var5.length() > 0; var8 = var7) {
	         var5 = var5.trim();
	         int var9 = var5.indexOf(60);
	         if (var9 != 0) {
	            break;
	         }

	         int var10 = var5.indexOf(62) + 1;
	         if (var10 == 0) {
	            break;
	         }

	         var7 = var5.substring(var9, var10);
	         String var11 = var5.substring(var10);
	         int var12 = var11.indexOf(60);
	         if (var12 == -1) {
	            break;
	         }

	         String var13 = var11.substring(0, var12);
	         if (var7.charAt(1) == '/') {
	            if (var4.isEmpty()) {
	               break;
	            }

	            var4.pop();
	            if (!var13.trim().equals("")) {
	               break;
	            }

	            if (var8.charAt(1) != '/' && var8.charAt(1) != '!' && var8.charAt(1) != '?' && var8.charAt(var8.length() - 2) != '/') {
	               var3.append(var7);
	            } else {
	               var3.append("\n" + var1 + resize("", var6, ' ', true) + var7);
	            }

	            var6 -= var2;
	         } else if (var7.charAt(1) != '?' && var7.charAt(1) != '!') {
	            boolean var14 = var7.charAt(var7.length() - 2) == '/';
	            var6 += var2;
	            if (!var14) {
	               var4.push(var7);
	            }

	            var3.append("\n" + var1 + resize("", var6, ' ', true) + var7 + var13);
	            if (var14) {
	               var6 -= var2;
	            }
	         } else {
	            var3.append("\n" + var1 + resize("", var6, ' ', true) + var7);
	         }

	         var5 = var5.substring(var10 + var12);
	      }

	      if (var5.length() <= 0 && !var4.isEmpty()) {
	      }

	      var3.append("\n" + var1 + resize("", var6, ' ', true) + var5);
	      return var3.toString();
	   }

	   /** @deprecated */
	   public static final String toStringHexDump(byte[] var0, int var1, int var2, String var3) {
	      StringBuffer var4 = new StringBuffer(var0.length * 4);
	      String var5 = "   ";
	      boolean var6 = false;
	      int var7 = var1 + var2;

	      while(var1 < var7) {
	         var4.append(var3 + toString((long)var1, 4) + "(" + toStringHex((long)var1, 4) + ")  ");

	         int var9;
	         for(var9 = 0; var9 < 16; ++var1) {
	            if (var1 < var7) {
	               if (var0[var1] > 0) {
	                  var4.append(toStringHex((long)var0[var1], 2) + " ");
	               } else {
	                  var4.append(toStringHex((long)(256 + var0[var1]), 2) + " ");
	               }
	            } else {
	               var4.append(var5);
	            }

	            if (var9 == 7) {
	               var4.append(" ");
	            }

	            ++var9;
	         }

	         var1 = var1;
	         var4.append("  ");

	         for(var9 = 0; var9 < 16 && var1 < var7; ++var1) {
	            if (var0[var1] < 32) {
	               var4.append(".");
	            } else {
	               var4.append(new String(var0, var1, 1));
	            }

	            ++var9;
	         }

	         var4.append("\n");
	      }

	      return new String(var4);
	   }

	   /** @deprecated */
	   public static final String toStringHexDump(byte[] var0, int var1, int var2) {
	      return toStringHexDump(var0, var1, var2, "");
	   }

	   /** @deprecated */
	   public static final String toStringHexDump(byte[] var0) {
	      return toStringHexDump(var0, 0, var0.length, "");
	   }

	   public static final String fromIntToMsgType(int var0) {
	      return getString(fromIntToMsgTypeData(var0));
	   }

	   /** @deprecated */
	   public static final String toStringPadded(String var0, int var1, char var2, boolean var3) {
	      char[] var4 = new char[var1];
	      boolean var5 = false;
	      if (var0 != null && var0.length() > var1) {
	         return var3 ? var0.substring(0, var1) : var0.substring(var0.length() - var1, var0.length());
	      } else {
	         int var6;
	         if (var0 != null) {
	            var6 = var0.length();
	            int var7;
	            if (var3) {
	               var0.getChars(0, var6, var4, 0);

	               for(var7 = var6; var7 < var1; ++var7) {
	                  var4[var7] = var2;
	               }
	            } else {
	               var0.getChars(0, var6, var4, var1 - var6);

	               for(var7 = 0; var7 < var1 - var6; ++var7) {
	                  var4[var7] = var2;
	               }
	            }
	         } else {
	            for(var6 = 0; var6 < var1; ++var6) {
	               var4[var6] = var2;
	            }
	         }

	         return new String(var4);
	      }
	   }

	   public static String toStackBackTrace(Throwable var0) {
	      if (var0 instanceof SQLException) {
	         return toSQLStackBackTrace((SQLException)var0);
	      } else {
	         ByteArrayOutputStream var1 = new ByteArrayOutputStream(512);
	         var0.printStackTrace(new PrintWriter(var1, true));
	         return var1.toString();
	      }
	   }

	   private static String toSQLStackBackTrace(SQLException var0) {
	      ByteArrayOutputStream var1 = new ByteArrayOutputStream(1024);
	      PrintWriter var2 = new PrintWriter(var1, true);
	      SQLException var3 = var0;

	      int var4;
	      for(var4 = 0; var3 != null; var3 = var3.getNextException()) {
	         ++var4;
	         var2.println("Chained SQLException: " + var4);
	         var3.printStackTrace(var2);
	         var2.println();
	         var2.println();
	      }

	      ByteArrayOutputStream var5 = new ByteArrayOutputStream(200);
	      PrintWriter var6 = new PrintWriter(var5, true);
	      var6.println("A SQLException was generated consisting of " + var4 + " chained exceptions.  The exceptions are" + " listed in chronological order.");
	      var6.println();
	      return var5.toString() + var1.toString();
	   }

	   public static final void putData(byte[] var0, byte[] var1, int var2) {
	      System.arraycopy(var0, 0, var1, var2, var0.length);
	   }

	   public static final byte[] getData(byte[] var0, int var1, int var2) {
	      byte[] var3 = new byte[var2];
	      System.arraycopy(var0, var1, var3, 0, var2);
	      return var3;
	   }

	   public static final byte[] getData(String var0) {
	      int var1 = var0.length();
	      byte[] var2 = new byte[var1];
	      var0.getBytes(0, var1, var2, 0);
	      return var2;
	   }

	   public static final byte[] fromAsciiDataToEbcdicData(byte[] var0) {
	      int var1 = var0.length;
	      byte[] var2 = new byte[var1];

	      for(int var3 = 0; var3 < var1; ++var3) {
	         var2[var3] = (byte)ascii_to_ebcdic[var0[var3] & 255];
	      }

	      return var2;
	   }

	   public static final byte[] fromEbcdicDataToAsciiData(byte[] var0) {
	      int var1 = var0.length;
	      byte[] var2 = new byte[var1];

	      for(int var3 = 0; var3 < var1; ++var3) {
	         var2[var3] = (byte)ebcdic_to_ascii[var0[var3] & 255];
	      }

	      return var2;
	   }

	   public static final byte[] resizeData(byte[] var0, int var1, byte var2, boolean var3) {
	      byte[] var4 = new byte[var1];
	      int var5 = var0.length;
	      if (var3) {
	         if (var5 >= var1) {
	            System.arraycopy(var0, 0, var4, 0, var1);
	         } else {
	            System.arraycopy(var0, 0, var4, 0, var5);

	            while(var5 < var1) {
	               var4[var5] = var2;
	               ++var5;
	            }
	         }
	      } else if (var5 >= var1) {
	         System.arraycopy(var0, var5 - var1, var4, 0, var1);
	      } else {
	         var5 = var1 - var5;
	         System.arraycopy(var0, 0, var4, var5, var0.length);
	         --var5;

	         while(var5 >= 0) {
	            var4[var5] = var2;
	            --var5;
	         }
	      }

	      return var4;
	   }

	   public static Vector split(String var0, char var1) {
	      Vector var2 = new Vector();
	      boolean var3 = false;
	      int var4 = var3 ? 1 : 0;

	      while(true) {
	         int var5 = var0.indexOf(var1, var4);
	         if (var5 == -1) {
	            if (var0.length() > 0) {
	               var2.addElement(var0.substring(var4));
	            }

	            return var2;
	         }

	         var2.addElement(var0.substring(var4, var5));
	         var4 = var5 + 1;
	      }
	   }

	   public static final byte[] fromIntToMsgTypeData(int var0) {
	      byte[] var1 = new byte[4];

	      for(int var2 = 3; var2 >= 0; --var2) {
	         var1[var2] = (byte)getHexChar(var0 & 15);
	         var0 >>= 4;
	      }

	      return var1;
	   }

	   /** @deprecated */
	   public static final String fromLongToMsgType(long var0) {
	      return getString(fromLongToMsgTypeData(var0));
	   }

	   public static final String fromEbcdicToAscii(String var0) {
	      return getString(fromEbcdicDataToAsciiData(getData(var0)));
	   }

	   private static final byte getHexNibble(byte var0) {
	      if (var0 >= 48 && var0 <= 57) {
	         return (byte)(var0 & 15);
	      } else if (var0 >= 65 && var0 <= 70) {
	         return (byte)(var0 - 55);
	      } else {
	         return var0 >= 97 && var0 <= 102 ? (byte)(var0 - 87) : 0;
	      }
	   }

	   public static final String fromHexToBin(String var0) {
	      return getString(fromHexDataToBinData(getData(var0)));
	   }

	   /** @deprecated */
	   public static String getHexInAsciiFromBin(byte[] var0, int var1, int var2) {
	      StringBuffer var3 = new StringBuffer(var2 * 2);

	      for(int var4 = var1 + var2; var1 < var4; ++var1) {
	         byte var5 = (byte)(var0[var1] >> 4 & 15);
	         if (var5 <= 9) {
	            var3.append((char)(var5 + 48));
	         } else {
	            var3.append((char)(var5 - 10 + 65));
	         }

	         var5 = (byte)(var0[var1] & 15);
	         if (var5 <= 9) {
	            var3.append((char)(var5 + 48));
	         } else {
	            var3.append((char)(var5 - 10 + 65));
	         }
	      }

	      return var3.toString();
	   }

	   public static final String fromAsciiToEbcdic(String var0) {
	      return getString(fromAsciiDataToEbcdicData(getData(var0)));
	   }

	   private Convert() {
	   }

	   public static final String fromBinToHex(String var0) {
	      return getString(fromBinDataToHexData(getData(var0)));
	   }

	   /** @deprecated */
	   public static final long fromDataHex(byte[] var0, int var1, int var2) {
	      long var3;
	      for(var3 = 0L; var2 > 0; --var2) {
	         if (var0[var1] >= 48 && var0[var1] <= 57) {
	            var3 = var3 << 4 | (long)(var0[var1] & 15);
	         } else if (var0[var1] >= 65 && var0[var1] <= 70) {
	            var3 = var3 << 4 | (long)(var0[var1] - 55);
	         } else {
	            if (var0[var1] < 97 || var0[var1] > 102) {
	               return -1L;
	            }

	            var3 = var3 << 4 | (long)(var0[var1] - 87);
	         }

	         ++var1;
	      }

	      return var3;
	   }

	   /** @deprecated */
	   public static final String fromStringHex(String var0) {
	      byte[] var1 = toData(var0);
	      int var2 = var0.length() / 2;
	      byte[] var3 = new byte[var2];
	      int var4 = 0;

	      for(int var5 = 0; var4 < var2; ++var4) {
	         if (var1[var5] >= 48 && var1[var5] <= 57) {
	            var3[var4] = (byte)((var1[var5] & 15) << 4);
	         } else if (var1[var5] >= 65 && var1[var5] <= 70) {
	            var3[var4] = (byte)(var1[var5] - 55 << 4);
	         } else {
	            if (var1[var5] < 97 || var1[var5] > 102) {
	               return null;
	            }

	            var3[var4] = (byte)(var1[var5] - 87 << 4);
	         }

	         ++var5;
	         if (var1[var5] >= 48 && var1[var5] <= 57) {
	            var3[var4] = (byte)(var3[var4] | var1[var5] & 15);
	         } else if (var1[var5] >= 65 && var1[var5] <= 70) {
	            var3[var4] = (byte)(var3[var4] | var1[var5] - 55);
	         } else {
	            if (var1[var5] < 97 || var1[var5] > 102) {
	               return null;
	            }

	            var3[var4] = (byte)(var3[var4] | var1[var5] - 87);
	         }

	         ++var5;
	      }

	      return new String(var3, 0, 0, var2);
	   }

	   private static final int getHexChar(int var0) {
	      return var0 < 10 ? var0 + 48 : var0 + 55;
	   }

	   public static final String resize(String var0, int var1, char var2, boolean var3) {
	      return getString(resizeData(getData(var0), var1, (byte)var2, var3));
	   }

	   public static String fromDoubleToUnsignedLongString(double var0, int var2) {
	      var0 = Math.abs(var0);
	      String var3 = String.valueOf((long)var0);
	      return var3;
	   }

	   public static final byte[] fromHexDataToBinData(byte[] var0) {
	      int var1 = var0.length / 2;
	      byte[] var2 = new byte[var1];
	      int var3 = 0;

	      for(int var4 = 0; var4 < var1; ++var4) {
	         var2[var4] = (byte)(getHexNibble(var0[var3++]) << 4 | getHexNibble(var0[var3++]));
	      }

	      return var2;
	   }

	   public static final byte[] fromBinDataToHexData(byte[] var0) {
	      int var1 = var0.length;
	      byte[] var2 = new byte[var1 * 2];
	      int var3 = 0;

	      for(int var4 = 0; var4 < var1; ++var4) {
	         int var5 = var0[var4] & 255;
	         var2[var3++] = (byte)getHexChar(var5 >> 4);
	         var2[var3++] = (byte)getHexChar(var5 & 15);
	      }

	      return var2;
	   }

	   public static byte[] fromHexStringToHexData(String var0) {
	      if (var0.length() % 2 == 1) {
	         var0 = '0' + var0;
	      }

	      byte[] var1 = new byte[var0.length() / 2];

	      for(int var2 = 0; var2 < var1.length; ++var2) {
	         String var3 = var0.substring(var2 * 2, var2 * 2 + 2);
	         var1[var2] = (byte)Integer.parseInt(var3, 16);
	      }

	      return var1;
	   }

	   /** @deprecated */
	   public static final byte[] fromLongToMsgTypeData(long var0) {
	      byte[] var2 = new byte[4];

	      for(int var3 = 3; var3 >= 0; --var3) {
	         var2[var3] = (byte)getHexChar((int)var0 & 15);
	         var0 >>= 4;
	      }

	      return var2;
	   }

	   static {
	      ascii_to_ebcdic[0] = 0;
	      ascii_to_ebcdic[1] = 1;
	      ascii_to_ebcdic[2] = 2;
	      ascii_to_ebcdic[3] = 3;
	      ascii_to_ebcdic[4] = 55;
	      ascii_to_ebcdic[5] = 45;
	      ascii_to_ebcdic[6] = 46;
	      ascii_to_ebcdic[7] = 47;
	      ascii_to_ebcdic[8] = 22;
	      ascii_to_ebcdic[9] = 5;
	      ascii_to_ebcdic[10] = 37;
	      ascii_to_ebcdic[11] = 11;
	      ascii_to_ebcdic[12] = 12;
	      ascii_to_ebcdic[13] = 13;
	      ascii_to_ebcdic[14] = 14;
	      ascii_to_ebcdic[15] = 15;
	      ascii_to_ebcdic[16] = 16;
	      ascii_to_ebcdic[17] = 17;
	      ascii_to_ebcdic[18] = 18;
	      ascii_to_ebcdic[19] = 19;
	      ascii_to_ebcdic[20] = 60;
	      ascii_to_ebcdic[21] = 61;
	      ascii_to_ebcdic[22] = 50;
	      ascii_to_ebcdic[23] = 38;
	      ascii_to_ebcdic[24] = 24;
	      ascii_to_ebcdic[25] = 25;
	      ascii_to_ebcdic[26] = 63;
	      ascii_to_ebcdic[27] = 39;
	      ascii_to_ebcdic[28] = 28;
	      ascii_to_ebcdic[29] = 29;
	      ascii_to_ebcdic[30] = 30;
	      ascii_to_ebcdic[31] = 31;
	      ascii_to_ebcdic[32] = 64;
	      ascii_to_ebcdic[33] = 90;
	      ascii_to_ebcdic[34] = 127;
	      ascii_to_ebcdic[35] = 123;
	      ascii_to_ebcdic[36] = 91;
	      ascii_to_ebcdic[37] = 108;
	      ascii_to_ebcdic[38] = 80;
	      ascii_to_ebcdic[39] = 125;
	      ascii_to_ebcdic[40] = 77;
	      ascii_to_ebcdic[41] = 93;
	      ascii_to_ebcdic[42] = 92;
	      ascii_to_ebcdic[43] = 78;
	      ascii_to_ebcdic[44] = 107;
	      ascii_to_ebcdic[45] = 96;
	      ascii_to_ebcdic[46] = 75;
	      ascii_to_ebcdic[47] = 97;
	      ascii_to_ebcdic[48] = 240;
	      ascii_to_ebcdic[49] = 241;
	      ascii_to_ebcdic[50] = 242;
	      ascii_to_ebcdic[51] = 243;
	      ascii_to_ebcdic[52] = 244;
	      ascii_to_ebcdic[53] = 245;
	      ascii_to_ebcdic[54] = 246;
	      ascii_to_ebcdic[55] = 247;
	      ascii_to_ebcdic[56] = 248;
	      ascii_to_ebcdic[57] = 249;
	      ascii_to_ebcdic[58] = 122;
	      ascii_to_ebcdic[59] = 94;
	      ascii_to_ebcdic[60] = 76;
	      ascii_to_ebcdic[61] = 126;
	      ascii_to_ebcdic[62] = 110;
	      ascii_to_ebcdic[63] = 111;
	      ascii_to_ebcdic[64] = 124;
	      ascii_to_ebcdic[65] = 193;
	      ascii_to_ebcdic[66] = 194;
	      ascii_to_ebcdic[67] = 195;
	      ascii_to_ebcdic[68] = 196;
	      ascii_to_ebcdic[69] = 197;
	      ascii_to_ebcdic[70] = 198;
	      ascii_to_ebcdic[71] = 199;
	      ascii_to_ebcdic[72] = 200;
	      ascii_to_ebcdic[73] = 201;
	      ascii_to_ebcdic[74] = 209;
	      ascii_to_ebcdic[75] = 210;
	      ascii_to_ebcdic[76] = 211;
	      ascii_to_ebcdic[77] = 212;
	      ascii_to_ebcdic[78] = 213;
	      ascii_to_ebcdic[79] = 214;
	      ascii_to_ebcdic[80] = 215;
	      ascii_to_ebcdic[81] = 216;
	      ascii_to_ebcdic[82] = 217;
	      ascii_to_ebcdic[83] = 226;
	      ascii_to_ebcdic[84] = 227;
	      ascii_to_ebcdic[85] = 228;
	      ascii_to_ebcdic[86] = 229;
	      ascii_to_ebcdic[87] = 230;
	      ascii_to_ebcdic[88] = 231;
	      ascii_to_ebcdic[89] = 232;
	      ascii_to_ebcdic[90] = 233;
	      ascii_to_ebcdic[91] = 173;
	      ascii_to_ebcdic[92] = 224;
	      ascii_to_ebcdic[93] = 189;
	      ascii_to_ebcdic[94] = 95;
	      ascii_to_ebcdic[95] = 109;
	      ascii_to_ebcdic[96] = 121;
	      ascii_to_ebcdic[97] = 129;
	      ascii_to_ebcdic[98] = 130;
	      ascii_to_ebcdic[99] = 131;
	      ascii_to_ebcdic[100] = 132;
	      ascii_to_ebcdic[101] = 133;
	      ascii_to_ebcdic[102] = 134;
	      ascii_to_ebcdic[103] = 135;
	      ascii_to_ebcdic[104] = 136;
	      ascii_to_ebcdic[105] = 137;
	      ascii_to_ebcdic[106] = 145;
	      ascii_to_ebcdic[107] = 146;
	      ascii_to_ebcdic[108] = 147;
	      ascii_to_ebcdic[109] = 148;
	      ascii_to_ebcdic[110] = 149;
	      ascii_to_ebcdic[111] = 150;
	      ascii_to_ebcdic[112] = 151;
	      ascii_to_ebcdic[113] = 152;
	      ascii_to_ebcdic[114] = 153;
	      ascii_to_ebcdic[115] = 162;
	      ascii_to_ebcdic[116] = 163;
	      ascii_to_ebcdic[117] = 164;
	      ascii_to_ebcdic[118] = 165;
	      ascii_to_ebcdic[119] = 166;
	      ascii_to_ebcdic[120] = 167;
	      ascii_to_ebcdic[121] = 168;
	      ascii_to_ebcdic[122] = 169;
	      ascii_to_ebcdic[123] = 192;
	      ascii_to_ebcdic[124] = 79;
	      ascii_to_ebcdic[125] = 208;
	      ascii_to_ebcdic[126] = 161;
	      ascii_to_ebcdic[127] = 7;
	      ascii_to_ebcdic[128] = 32;
	      ascii_to_ebcdic[129] = 33;
	      ascii_to_ebcdic[130] = 34;
	      ascii_to_ebcdic[131] = 35;
	      ascii_to_ebcdic[132] = 36;
	      ascii_to_ebcdic[133] = 21;
	      ascii_to_ebcdic[134] = 6;
	      ascii_to_ebcdic[135] = 23;
	      ascii_to_ebcdic[136] = 40;
	      ascii_to_ebcdic[137] = 41;
	      ascii_to_ebcdic[138] = 42;
	      ascii_to_ebcdic[139] = 43;
	      ascii_to_ebcdic[140] = 44;
	      ascii_to_ebcdic[141] = 9;
	      ascii_to_ebcdic[142] = 10;
	      ascii_to_ebcdic[143] = 27;
	      ascii_to_ebcdic[144] = 48;
	      ascii_to_ebcdic[145] = 49;
	      ascii_to_ebcdic[146] = 26;
	      ascii_to_ebcdic[147] = 51;
	      ascii_to_ebcdic[148] = 52;
	      ascii_to_ebcdic[149] = 53;
	      ascii_to_ebcdic[150] = 54;
	      ascii_to_ebcdic[151] = 8;
	      ascii_to_ebcdic[152] = 56;
	      ascii_to_ebcdic[153] = 57;
	      ascii_to_ebcdic[154] = 58;
	      ascii_to_ebcdic[155] = 59;
	      ascii_to_ebcdic[156] = 4;
	      ascii_to_ebcdic[157] = 20;
	      ascii_to_ebcdic[158] = 62;
	      ascii_to_ebcdic[159] = 255;
	      ascii_to_ebcdic[160] = 65;
	      ascii_to_ebcdic[161] = 170;
	      ascii_to_ebcdic[162] = 74;
	      ascii_to_ebcdic[163] = 177;
	      ascii_to_ebcdic[164] = 159;
	      ascii_to_ebcdic[165] = 178;
	      ascii_to_ebcdic[166] = 106;
	      ascii_to_ebcdic[167] = 181;
	      ascii_to_ebcdic[168] = 187;
	      ascii_to_ebcdic[169] = 180;
	      ascii_to_ebcdic[170] = 154;
	      ascii_to_ebcdic[171] = 138;
	      ascii_to_ebcdic[172] = 176;
	      ascii_to_ebcdic[173] = 202;
	      ascii_to_ebcdic[174] = 175;
	      ascii_to_ebcdic[175] = 188;
	      ascii_to_ebcdic[176] = 144;
	      ascii_to_ebcdic[177] = 143;
	      ascii_to_ebcdic[178] = 234;
	      ascii_to_ebcdic[179] = 250;
	      ascii_to_ebcdic[180] = 190;
	      ascii_to_ebcdic[181] = 160;
	      ascii_to_ebcdic[182] = 182;
	      ascii_to_ebcdic[183] = 179;
	      ascii_to_ebcdic[184] = 157;
	      ascii_to_ebcdic[185] = 218;
	      ascii_to_ebcdic[186] = 155;
	      ascii_to_ebcdic[187] = 139;
	      ascii_to_ebcdic[188] = 183;
	      ascii_to_ebcdic[189] = 184;
	      ascii_to_ebcdic[190] = 185;
	      ascii_to_ebcdic[191] = 171;
	      ascii_to_ebcdic[192] = 100;
	      ascii_to_ebcdic[193] = 101;
	      ascii_to_ebcdic[194] = 98;
	      ascii_to_ebcdic[195] = 102;
	      ascii_to_ebcdic[196] = 99;
	      ascii_to_ebcdic[197] = 103;
	      ascii_to_ebcdic[198] = 158;
	      ascii_to_ebcdic[199] = 104;
	      ascii_to_ebcdic[200] = 116;
	      ascii_to_ebcdic[201] = 113;
	      ascii_to_ebcdic[202] = 114;
	      ascii_to_ebcdic[203] = 115;
	      ascii_to_ebcdic[204] = 120;
	      ascii_to_ebcdic[205] = 117;
	      ascii_to_ebcdic[206] = 118;
	      ascii_to_ebcdic[207] = 119;
	      ascii_to_ebcdic[208] = 172;
	      ascii_to_ebcdic[209] = 105;
	      ascii_to_ebcdic[210] = 237;
	      ascii_to_ebcdic[211] = 238;
	      ascii_to_ebcdic[212] = 235;
	      ascii_to_ebcdic[213] = 239;
	      ascii_to_ebcdic[214] = 236;
	      ascii_to_ebcdic[215] = 191;
	      ascii_to_ebcdic[216] = 128;
	      ascii_to_ebcdic[217] = 253;
	      ascii_to_ebcdic[218] = 254;
	      ascii_to_ebcdic[219] = 251;
	      ascii_to_ebcdic[220] = 252;
	      ascii_to_ebcdic[221] = 186;
	      ascii_to_ebcdic[222] = 174;
	      ascii_to_ebcdic[223] = 89;
	      ascii_to_ebcdic[224] = 68;
	      ascii_to_ebcdic[225] = 69;
	      ascii_to_ebcdic[226] = 66;
	      ascii_to_ebcdic[227] = 70;
	      ascii_to_ebcdic[228] = 67;
	      ascii_to_ebcdic[229] = 71;
	      ascii_to_ebcdic[230] = 156;
	      ascii_to_ebcdic[231] = 72;
	      ascii_to_ebcdic[232] = 84;
	      ascii_to_ebcdic[233] = 81;
	      ascii_to_ebcdic[234] = 82;
	      ascii_to_ebcdic[235] = 83;
	      ascii_to_ebcdic[236] = 88;
	      ascii_to_ebcdic[237] = 85;
	      ascii_to_ebcdic[238] = 86;
	      ascii_to_ebcdic[239] = 87;
	      ascii_to_ebcdic[240] = 140;
	      ascii_to_ebcdic[241] = 73;
	      ascii_to_ebcdic[242] = 205;
	      ascii_to_ebcdic[243] = 206;
	      ascii_to_ebcdic[244] = 203;
	      ascii_to_ebcdic[245] = 207;
	      ascii_to_ebcdic[246] = 204;
	      ascii_to_ebcdic[247] = 225;
	      ascii_to_ebcdic[248] = 112;
	      ascii_to_ebcdic[249] = 221;
	      ascii_to_ebcdic[250] = 222;
	      ascii_to_ebcdic[251] = 219;
	      ascii_to_ebcdic[252] = 220;
	      ascii_to_ebcdic[253] = 141;
	      ascii_to_ebcdic[254] = 142;
	      ascii_to_ebcdic[255] = 223;
	      ebcdic_to_ascii[0] = 0;
	      ebcdic_to_ascii[1] = 1;
	      ebcdic_to_ascii[2] = 2;
	      ebcdic_to_ascii[3] = 3;
	      ebcdic_to_ascii[4] = 156;
	      ebcdic_to_ascii[5] = 9;
	      ebcdic_to_ascii[6] = 134;
	      ebcdic_to_ascii[7] = 127;
	      ebcdic_to_ascii[8] = 151;
	      ebcdic_to_ascii[9] = 141;
	      ebcdic_to_ascii[10] = 142;
	      ebcdic_to_ascii[11] = 11;
	      ebcdic_to_ascii[12] = 12;
	      ebcdic_to_ascii[13] = 13;
	      ebcdic_to_ascii[14] = 14;
	      ebcdic_to_ascii[15] = 15;
	      ebcdic_to_ascii[16] = 16;
	      ebcdic_to_ascii[17] = 17;
	      ebcdic_to_ascii[18] = 18;
	      ebcdic_to_ascii[19] = 19;
	      ebcdic_to_ascii[20] = 157;
	      ebcdic_to_ascii[21] = 133;
	      ebcdic_to_ascii[22] = 8;
	      ebcdic_to_ascii[23] = 135;
	      ebcdic_to_ascii[24] = 24;
	      ebcdic_to_ascii[25] = 25;
	      ebcdic_to_ascii[26] = 146;
	      ebcdic_to_ascii[27] = 143;
	      ebcdic_to_ascii[28] = 28;
	      ebcdic_to_ascii[29] = 29;
	      ebcdic_to_ascii[30] = 30;
	      ebcdic_to_ascii[31] = 31;
	      ebcdic_to_ascii[32] = 128;
	      ebcdic_to_ascii[33] = 129;
	      ebcdic_to_ascii[34] = 130;
	      ebcdic_to_ascii[35] = 131;
	      ebcdic_to_ascii[36] = 132;
	      ebcdic_to_ascii[37] = 10;
	      ebcdic_to_ascii[38] = 23;
	      ebcdic_to_ascii[39] = 27;
	      ebcdic_to_ascii[40] = 136;
	      ebcdic_to_ascii[41] = 137;
	      ebcdic_to_ascii[42] = 138;
	      ebcdic_to_ascii[43] = 139;
	      ebcdic_to_ascii[44] = 140;
	      ebcdic_to_ascii[45] = 5;
	      ebcdic_to_ascii[46] = 6;
	      ebcdic_to_ascii[47] = 7;
	      ebcdic_to_ascii[48] = 144;
	      ebcdic_to_ascii[49] = 145;
	      ebcdic_to_ascii[50] = 22;
	      ebcdic_to_ascii[51] = 147;
	      ebcdic_to_ascii[52] = 148;
	      ebcdic_to_ascii[53] = 149;
	      ebcdic_to_ascii[54] = 150;
	      ebcdic_to_ascii[55] = 4;
	      ebcdic_to_ascii[56] = 152;
	      ebcdic_to_ascii[57] = 153;
	      ebcdic_to_ascii[58] = 154;
	      ebcdic_to_ascii[59] = 155;
	      ebcdic_to_ascii[60] = 20;
	      ebcdic_to_ascii[61] = 21;
	      ebcdic_to_ascii[62] = 158;
	      ebcdic_to_ascii[63] = 26;
	      ebcdic_to_ascii[64] = 32;
	      ebcdic_to_ascii[65] = 160;
	      ebcdic_to_ascii[66] = 226;
	      ebcdic_to_ascii[67] = 228;
	      ebcdic_to_ascii[68] = 224;
	      ebcdic_to_ascii[69] = 225;
	      ebcdic_to_ascii[70] = 227;
	      ebcdic_to_ascii[71] = 229;
	      ebcdic_to_ascii[72] = 231;
	      ebcdic_to_ascii[73] = 241;
	      ebcdic_to_ascii[74] = 162;
	      ebcdic_to_ascii[75] = 46;
	      ebcdic_to_ascii[76] = 60;
	      ebcdic_to_ascii[77] = 40;
	      ebcdic_to_ascii[78] = 43;
	      ebcdic_to_ascii[79] = 124;
	      ebcdic_to_ascii[80] = 38;
	      ebcdic_to_ascii[81] = 233;
	      ebcdic_to_ascii[82] = 234;
	      ebcdic_to_ascii[83] = 235;
	      ebcdic_to_ascii[84] = 232;
	      ebcdic_to_ascii[85] = 237;
	      ebcdic_to_ascii[86] = 238;
	      ebcdic_to_ascii[87] = 239;
	      ebcdic_to_ascii[88] = 236;
	      ebcdic_to_ascii[89] = 223;
	      ebcdic_to_ascii[90] = 33;
	      ebcdic_to_ascii[91] = 36;
	      ebcdic_to_ascii[92] = 42;
	      ebcdic_to_ascii[93] = 41;
	      ebcdic_to_ascii[94] = 59;
	      ebcdic_to_ascii[95] = 94;
	      ebcdic_to_ascii[96] = 45;
	      ebcdic_to_ascii[97] = 47;
	      ebcdic_to_ascii[98] = 194;
	      ebcdic_to_ascii[99] = 196;
	      ebcdic_to_ascii[100] = 192;
	      ebcdic_to_ascii[101] = 193;
	      ebcdic_to_ascii[102] = 195;
	      ebcdic_to_ascii[103] = 197;
	      ebcdic_to_ascii[104] = 199;
	      ebcdic_to_ascii[105] = 209;
	      ebcdic_to_ascii[106] = 166;
	      ebcdic_to_ascii[107] = 44;
	      ebcdic_to_ascii[108] = 37;
	      ebcdic_to_ascii[109] = 95;
	      ebcdic_to_ascii[110] = 62;
	      ebcdic_to_ascii[111] = 63;
	      ebcdic_to_ascii[112] = 248;
	      ebcdic_to_ascii[113] = 201;
	      ebcdic_to_ascii[114] = 202;
	      ebcdic_to_ascii[115] = 203;
	      ebcdic_to_ascii[116] = 200;
	      ebcdic_to_ascii[117] = 205;
	      ebcdic_to_ascii[118] = 206;
	      ebcdic_to_ascii[119] = 207;
	      ebcdic_to_ascii[120] = 204;
	      ebcdic_to_ascii[121] = 96;
	      ebcdic_to_ascii[122] = 58;
	      ebcdic_to_ascii[123] = 35;
	      ebcdic_to_ascii[124] = 64;
	      ebcdic_to_ascii[125] = 39;
	      ebcdic_to_ascii[126] = 61;
	      ebcdic_to_ascii[127] = 34;
	      ebcdic_to_ascii[128] = 216;
	      ebcdic_to_ascii[129] = 97;
	      ebcdic_to_ascii[130] = 98;
	      ebcdic_to_ascii[131] = 99;
	      ebcdic_to_ascii[132] = 100;
	      ebcdic_to_ascii[133] = 101;
	      ebcdic_to_ascii[134] = 102;
	      ebcdic_to_ascii[135] = 103;
	      ebcdic_to_ascii[136] = 104;
	      ebcdic_to_ascii[137] = 105;
	      ebcdic_to_ascii[138] = 171;
	      ebcdic_to_ascii[139] = 187;
	      ebcdic_to_ascii[140] = 240;
	      ebcdic_to_ascii[141] = 253;
	      ebcdic_to_ascii[142] = 254;
	      ebcdic_to_ascii[143] = 177;
	      ebcdic_to_ascii[144] = 176;
	      ebcdic_to_ascii[145] = 106;
	      ebcdic_to_ascii[146] = 107;
	      ebcdic_to_ascii[147] = 108;
	      ebcdic_to_ascii[148] = 109;
	      ebcdic_to_ascii[149] = 110;
	      ebcdic_to_ascii[150] = 111;
	      ebcdic_to_ascii[151] = 112;
	      ebcdic_to_ascii[152] = 113;
	      ebcdic_to_ascii[153] = 114;
	      ebcdic_to_ascii[154] = 170;
	      ebcdic_to_ascii[155] = 186;
	      ebcdic_to_ascii[156] = 230;
	      ebcdic_to_ascii[157] = 184;
	      ebcdic_to_ascii[158] = 198;
	      ebcdic_to_ascii[159] = 164;
	      ebcdic_to_ascii[160] = 181;
	      ebcdic_to_ascii[161] = 126;
	      ebcdic_to_ascii[162] = 115;
	      ebcdic_to_ascii[163] = 116;
	      ebcdic_to_ascii[164] = 117;
	      ebcdic_to_ascii[165] = 118;
	      ebcdic_to_ascii[166] = 119;
	      ebcdic_to_ascii[167] = 120;
	      ebcdic_to_ascii[168] = 121;
	      ebcdic_to_ascii[169] = 122;
	      ebcdic_to_ascii[170] = 161;
	      ebcdic_to_ascii[171] = 191;
	      ebcdic_to_ascii[172] = 208;
	      ebcdic_to_ascii[173] = 91;
	      ebcdic_to_ascii[174] = 222;
	      ebcdic_to_ascii[175] = 174;
	      ebcdic_to_ascii[176] = 172;
	      ebcdic_to_ascii[177] = 163;
	      ebcdic_to_ascii[178] = 165;
	      ebcdic_to_ascii[179] = 183;
	      ebcdic_to_ascii[180] = 169;
	      ebcdic_to_ascii[181] = 167;
	      ebcdic_to_ascii[182] = 182;
	      ebcdic_to_ascii[183] = 188;
	      ebcdic_to_ascii[184] = 189;
	      ebcdic_to_ascii[185] = 190;
	      ebcdic_to_ascii[186] = 221;
	      ebcdic_to_ascii[187] = 168;
	      ebcdic_to_ascii[188] = 175;
	      ebcdic_to_ascii[189] = 93;
	      ebcdic_to_ascii[190] = 180;
	      ebcdic_to_ascii[191] = 215;
	      ebcdic_to_ascii[192] = 123;
	      ebcdic_to_ascii[193] = 65;
	      ebcdic_to_ascii[194] = 66;
	      ebcdic_to_ascii[195] = 67;
	      ebcdic_to_ascii[196] = 68;
	      ebcdic_to_ascii[197] = 69;
	      ebcdic_to_ascii[198] = 70;
	      ebcdic_to_ascii[199] = 71;
	      ebcdic_to_ascii[200] = 72;
	      ebcdic_to_ascii[201] = 73;
	      ebcdic_to_ascii[202] = 173;
	      ebcdic_to_ascii[203] = 244;
	      ebcdic_to_ascii[204] = 246;
	      ebcdic_to_ascii[205] = 242;
	      ebcdic_to_ascii[206] = 243;
	      ebcdic_to_ascii[207] = 245;
	      ebcdic_to_ascii[208] = 125;
	      ebcdic_to_ascii[209] = 74;
	      ebcdic_to_ascii[210] = 75;
	      ebcdic_to_ascii[211] = 76;
	      ebcdic_to_ascii[212] = 77;
	      ebcdic_to_ascii[213] = 78;
	      ebcdic_to_ascii[214] = 79;
	      ebcdic_to_ascii[215] = 80;
	      ebcdic_to_ascii[216] = 81;
	      ebcdic_to_ascii[217] = 82;
	      ebcdic_to_ascii[218] = 185;
	      ebcdic_to_ascii[219] = 251;
	      ebcdic_to_ascii[220] = 252;
	      ebcdic_to_ascii[221] = 249;
	      ebcdic_to_ascii[222] = 250;
	      ebcdic_to_ascii[223] = 255;
	      ebcdic_to_ascii[224] = 92;
	      ebcdic_to_ascii[225] = 247;
	      ebcdic_to_ascii[226] = 83;
	      ebcdic_to_ascii[227] = 84;
	      ebcdic_to_ascii[228] = 85;
	      ebcdic_to_ascii[229] = 86;
	      ebcdic_to_ascii[230] = 87;
	      ebcdic_to_ascii[231] = 88;
	      ebcdic_to_ascii[232] = 89;
	      ebcdic_to_ascii[233] = 90;
	      ebcdic_to_ascii[234] = 178;
	      ebcdic_to_ascii[235] = 212;
	      ebcdic_to_ascii[236] = 214;
	      ebcdic_to_ascii[237] = 210;
	      ebcdic_to_ascii[238] = 211;
	      ebcdic_to_ascii[239] = 213;
	      ebcdic_to_ascii[240] = 48;
	      ebcdic_to_ascii[241] = 49;
	      ebcdic_to_ascii[242] = 50;
	      ebcdic_to_ascii[243] = 51;
	      ebcdic_to_ascii[244] = 52;
	      ebcdic_to_ascii[245] = 53;
	      ebcdic_to_ascii[246] = 54;
	      ebcdic_to_ascii[247] = 55;
	      ebcdic_to_ascii[248] = 56;
	      ebcdic_to_ascii[249] = 57;
	      ebcdic_to_ascii[250] = 179;
	      ebcdic_to_ascii[251] = 219;
	      ebcdic_to_ascii[252] = 220;
	      ebcdic_to_ascii[253] = 217;
	      ebcdic_to_ascii[254] = 218;
	      ebcdic_to_ascii[255] = 0;
	      html_entities = new String[128];

	      for(int var0 = 0; var0 < 128; ++var0) {
	         html_entities[var0] = new String(new byte[]{(byte)var0});
	      }

	      html_entities[34] = new String("&quot;");
	      html_entities[38] = new String("&amp;");
	      html_entities[39] = new String("&#039;");
	      html_entities[60] = new String("&lt;");
	      html_entities[62] = new String("&gt;");
	   }
	}